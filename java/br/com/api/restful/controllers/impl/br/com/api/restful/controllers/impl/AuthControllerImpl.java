package br.com.api.restful.controllers.impl.br.com.api.restful.controllers.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.controllers.abstracts.AbstractControllerImpl;
import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.securitys.utils.JwtTokenUtil;
import br.com.api.restful.services.impl.LoginServiceImpl;

@RequestMapping("/auth")
@RestController
public class AuthControllerImpl extends AbstractControllerImpl<LoginDTO, User, LoginServiceImpl> {

	@Autowired(required = true)
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping
	public ResponseEntity<User> authenticate(@Valid @RequestBody LoginDTO authDTO, BindingResult eResult) {

		User user = new User();

		if (eResult.hasErrors()) {
			for (ObjectError error : eResult.getAllErrors()) {
				user.getMensagem().add(error.getDefaultMessage());
			};
			return ResponseEntity.badRequest().body(user);
		}

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		user = service.login(authDTO);
		user.setToken(jwtTokenUtil.obterToken(authDTO.getEmail()));
		return ResponseEntity.ok(user);
	}
}
