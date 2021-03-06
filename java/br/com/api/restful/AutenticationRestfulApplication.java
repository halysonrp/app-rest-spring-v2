package br.com.api.restful;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.api.restful.entities.Phone;
import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;
import br.com.api.restful.securitys.utils.JwtTokenUtil;
import br.com.api.restful.utils.PasswordUtils;

@SpringBootApplication
public class AutenticationRestfulApplication {

	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	JwtTokenUtil jwtToken;

	public static void main(String[] args) {
		SpringApplication.run(AutenticationRestfulApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			persistFirstUser();

		};
	}
	
	  

	public void persistFirstUser() {
	       		
		User user = new User();
		user.setEmail("halysonrp@gmail.com");
		user.setName("Halyson");
		user.setPassword(PasswordUtils.generatePasswordBCrypt("123"));
		user.setToken(jwtToken.obterToken(user.getEmail()));
		
		Phone phone = new Phone();
		phone.setDdd("081");
		phone.setNumber("998322120");
		phone.setUser(user);
		
		List<Phone> phones= new ArrayList<Phone>();
		phones.add(phone);
		user.setPhones(phones);
		
		user = userRepository.save(user);
		System.out.println("User id: " + user.getId());
		System.out.println("User Object: " + userRepository.findAll());
		
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
