package br.com.api.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.WritingConverter;

import br.com.api.restful.entities.Phone;
import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;

@SpringBootApplication
public class AutenticationRestfulApplication {

	@Autowired
	IUserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AutenticationRestfulApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			testePersistencia();

		};
	}

	public void testePersistencia() {
		User user = new User();
		user.setEmail("halysonrp@gmail.com");
		user.setName("Halyson");
		user.setPassword("123456");
		user.setToken("123456");
		Phone phone = new Phone();
		phone.setDdd("081");
		phone.setNumber("998322120");
		user = userRepository.save(user);
		System.out.println("User id: " + user.getId());
		System.out.println("User Object: " + userRepository.findAll());

	}

}
