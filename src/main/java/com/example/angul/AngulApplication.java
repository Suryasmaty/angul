package com.example.angul;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.angul.dao.UserRepository;
import com.example.angul.model.User;

@SpringBootApplication
@EnableWebSecurity
@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/")
public class AngulApplication  {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/")
	public String login()
	{
		return "authenticated successfully";
	}
	
	@PostMapping("/register")
	public String resgister(@RequestBody User user)
	{
		
		repo.save(user);
		return "Hi "+user.getName()+" Your registration was sucessful";
	}
	
	@GetMapping("/getAllUsers")
	public List<User> gindAllUsers()
	{
		return repo.findAll();	
	}
	
	@GetMapping("/findUser/{email}")
	public List<User> findUser(@PathVariable String email)
	{
		return repo.findByEmail(email);
	}
	
	@GetMapping("/getUser/{id}")
	public User getUser(@PathVariable int id) throws Exception
	{
		return repo.findById(id).orElseThrow(()->new Exception("error"));
	}
	
	@DeleteMapping("/cancel/{id}")
	public List<User> cancelRegistration(@PathVariable int id)
	{
		repo.deleteById(id);
		return repo.findAll();
	}
	
	@PutMapping("/update/{id}") 
	public ResponseEntity<User> cancelRegistration(@PathVariable int id, @RequestBody User user) throws Exception
	{
		User u=repo.findById(id).orElseThrow(()->new Exception("error"));
		u.setName(user.getName());
		u.setSalary(user.getSalary());
		User updatedUser=repo.save(u);
		return ResponseEntity.ok(updatedUser);
	}
	
	

	
	public static void main(String[] args) {
		SpringApplication.run(AngulApplication.class, args);
	}

}
