package com.adt.assignment1.controller;

import java.security.SecureRandom;


//import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
//import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adt.assignment1.model.User;
import com.adt.assignment1.repository.UserRepository;
//import com.adt.assignment1.service.UserService;
//import com.bol.crypt.CryptVault;
//import com.bol.secure.CachedEncryptionEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//UserService userService;
//	
//	private static final byte[] secretKey = Base64.getDecoder().decode("hqHKBLV83LpCqzKpf8OvutbCs+O5wX5BPu3btWpEvXA=");
//	private static final byte[] oldKey = Base64.getDecoder().decode("cUzurmCcL+K252XDJhhWI/A/+wxYXLgIm678bwsE2QM=");
//	@Bean
//	public CryptVault cryptVault() {
//	   return new CryptVault()
//	         .with256BitAesCbcPkcs5PaddingAnd128BitSaltKey(0, oldKey)
//	         .with256BitAesCbcPkcs5PaddingAnd128BitSaltKey(1, secretKey)
//	         // can be omitted if it's the highest version
//	         .withDefaultKeyVersion(1);
//	}
//
//	@Bean
//	public CachedEncryptionEventListener encryptionEventListener(CryptVault cryptVault) {
//	   return new CachedEncryptionEventListener(cryptVault);
//	}
	int strength = 10; // work factor of bcrypt
	 BCryptPasswordEncoder bCryptPasswordEncoder =
	  new BCryptPasswordEncoder(strength, new SecureRandom());
	 
	
	@PostMapping("/registerUser")
	public ResponseEntity<?>addUser(@RequestBody User user){
		System.out.println("inside add user");
		User addUser = null;
		if(user!=null) {
			System.out.println("inside if stmt");
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		System.out.println("encoded password"+encodedPassword);
		 user.setPassword(encodedPassword);
		addUser=this.userRepo.save(user);
		}
		else {
			//logger print
			System.out.println("request body is empty :"+addUser);
		}
		System.out.println("exiting add user");
		return ResponseEntity.ok(addUser);
		
	}
	  @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
		  System.out.println("inside login");
		  if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
			  System.out.println("checking conditions in if");
			  return new ResponseEntity<>("Username or password cannot be empty", HttpStatus.BAD_REQUEST);
		    }
		  Boolean isLogin=verifyUser(username,password);
		  if(isLogin)
		  {
			  System.out.println("checking login in if");
			  return new ResponseEntity<>("Login successful", HttpStatus.OK);
	        } else {
	        	return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
	        }
	    }
	
	public Boolean verifyUser(String username,String password){
		System.out.println("inside verify user");
		if(username!=null && password!=null) {
			User user=userRepo.findByUsername(username);
			System.out.println("user returned"+user.toString());
			 if (user != null) {
				 System.out.println("matching password");
				 System.out.println(user.getPassword());
				 System.out.println(passwordEncoder.encode(user.getPassword()));
				 System.out.println(passwordEncoder.encode(password));
				 System.out.println(passwordEncoder.matches(password, user.getPassword()));
			        return passwordEncoder.matches(password, user.getPassword());
			    }
			
			
		}
		System.out.println("exiting verify user");
		return false;
		
	}
}
