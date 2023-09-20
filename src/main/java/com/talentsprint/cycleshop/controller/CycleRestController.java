package com.talentsprint.cycleshop.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.entity.User;
import com.talentsprint.cycleshop.repository.UserRepository;
import com.talentsprint.cycleshop.service.CycleService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CycleRestController {

	@Autowired
	private CycleService cycleService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * For example, /1/borrow?count=3 borrows 3 cycles of id 1.
	 */

	@GetMapping("/registration")

	public String registrationForm(Model model) {

		return "userRegistration";

	}
	@PostMapping("/{id}/borrow")
    public List<Cycle> borrowCycle(@PathVariable long id, @RequestParam(required=false, defaultValue="1") int count) {
        cycleService.borrowCycle(id, count);
        //just a comment
        return all(); //TODO: redirect to List handler
    }

    @PostMapping("/{id}/return")
    public List<Cycle> returnCycle(@PathVariable long id, @RequestParam(required=false, defaultValue="1") int count) {
        cycleService.returnCycle(id, count);
        return all();//TODO: redirect to list handler
    }

    @PostMapping("/{id}/restock")
    public List<Cycle> restockCycle(@PathVariable long id, @RequestParam(required=false, defaultValue="1") int count) {
        cycleService.restockBy(id, count);
        return all();
    }

    @GetMapping("/list")
    public List<Cycle> listAvailableCycles(Model model) {
        var allCycles = cycleService.listAvailableCycles();
        model.addAttribute("allCycles", allCycles);
        return all();
    }

    @GetMapping("/{id}")
    public String cycleDetail(@PathVariable long id, Model model) {
        var cycle = cycleService.findByIdOrThrow404(id);
        model.addAttribute("cycle", cycle);
        return "cycleDetail";
    }

    @GetMapping("/cart")
    public List<Cycle> getRentedCycles(Model model){
    	var allCycles = cycleService.listAvailableCycles();
    	model.addAttribute("allCycles", allCycles);
    	return all();

    }
    
//	@PostMapping("/register")
//
//	public ResponseEntity<String> registerUser(@RequestBody User user) {
//
//		try {
//
//			if (userRepository.existsByName(user.getName())) {
//
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
//
//			}
//
//			user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//			userRepository.save(user);
//
//			return ResponseEntity.ok("User registered successfully");
//
//		} catch (Exception e) {
//
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
//
//		}
//
//	}
//
//	@GetMapping("/login")
//
//	public String LoginForm(Model model) {
//
//		return "userLogin";
//
//	}

//	@PostMapping("/login")
//
//	public String LoginonSubmit(@RequestParam String username, @RequestParam String password, Model model) {
//
//		Optional<User> user = userRepository.findByName(username);
//
//		if (user != null && userMatchesPassword(user.get(), password)) {
//
//			return "redirect:/restock";
//
//		} else {
//
//			model.addAttribute("error", "Invalid Crudentials");
//
//			return "userLogin";
//
//		}
//
//	}

//	private boolean userMatchesPassword(User user, String password) {
//
//		return user.getPassword().equals(password);
//
//	}

	@GetMapping("/health")
	public String checkhealth() {
		return "healthy";
	}

	@GetMapping("/cycle/list")
	public List<Cycle> all() {
		//Jwt jwt = (Jwt) authentication.getPrincipal();
		//System.out.println(jwt.getClaimAsString("scope"));
		return cycleService.listCycles();
	}
}
