package br.com.api.restful.controllers.impl.br.com.api.restful.controllers.impl;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.controllers.abstracts.AbstractController;
import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.responses.Response;
import br.com.api.restful.securitys.utils.JwtTokenUtil;
import br.com.api.restful.services.impl.LoginServiceImpl;
import br.com.api.restful.utils.PasswordUtils;

@RequestMapping("/auth")
@RestController
public class AuthControllerImpl extends AbstractController<LoginDTO, User, LoginServiceImpl> {
	
	@Autowired(required=true)
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping
	public ResponseEntity<Response<User>> authenticate(@Valid @RequestBody LoginDTO authDTO, BindingResult eResult) {
		
		Response<User> response = new Response<User>();
		
		if(eResult.hasErrors()) {
			for (ObjectError error : eResult.getAllErrors()) {
				response.getMensagem().add(error.getDefaultMessage());
			};
			return ResponseEntity.badRequest().body(response);
		}
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authDTO.getEmail(),authDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetail = userDetailsService.loadUserByUsername(authDTO.getEmail());
		
		String token = jwtTokenUtil.obterToken(userDetail);
		response.setData(userDetail);
		
		return ResponseEntity.ok(response);
	}
}
