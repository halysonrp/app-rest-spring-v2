package br.com.api.restful.securitys.filters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import br.com.api.restful.entities.User;
import br.com.api.restful.services.IAuthService;
import br.com.api.restful.services.impl.AuthServiceImpl;
import br.com.api.restful.services.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Component
public class JwtAuthenticationManager implements AuthenticationManager {

		//@Autowired
	    //private UserService userService;

	    //@Autowired
	    //private AuthService authService;
	
		@Autowired
		private UserServiceImpl userService;
		
		@Autowired
		private IAuthService loginService;
		
	    //@Autowired
	   // private PasswordEncoder passwordEncoder;
	
		//@Autowired
	  //  private TokenStore tokenStore;

		/*
		 * Caso o token n�o exista, retornar erro com status apropriado com a mensagem "N�o autorizado".
			Caso o token exista, buscar o usu�rio pelo id passado no path e comparar se o token no modelo � igual ao token passado no header.
			Caso n�o seja o mesmo token, retornar erro com status apropriado e mensagem "N�o autorizado"
			Caso seja o mesmo token, verificar se o �ltimo login foi a MENOS que 30 minutos atr�s. Caso n�o seja a MENOS que 30 minutos atr�s, retornar erro com status apropriado com mensagem "Sess�o inv�lida".
			Caso tudo esteja ok, retornar o usu�rio no mesmo formato do retorno do Login.
			(non-Javadoc)
		 * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
		 */
	    @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	        String principal = (String) authentication.getPrincipal();
	        String password = null;
	        String token = null;
	        User user = null;
	        
	        
	        if (authentication instanceof UsernamePasswordAuthenticationToken) {
	        	user = loginService.findByEmail(principal);
	            password = (String) authentication.getCredentials();
	        } else {
	            token = principal;
	           // OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(principal);
	          //  UUID id = UUID.fromString((String) auth2Authentication.getPrincipal());
	           // user = userService.findById(id);
	        }
   
	        if (user != null) {
	            if (user.getToken() == null) {
	                new UnauthorizedUserException("N�o autorizado");   	
	            }
	            if (password != null) {
	                if (token != null) {
	                    user.setToken(token);
	                }
	                userService.save(user);
	                return new UsernamePasswordAuthenticationToken(user.getId().toString(), user.getPassword(),
	                        authentication.getAuthorities());
	            }
	        }
	        throw new AccessDeniedException("Usuario e/ou senha invalidos");
	    } 

}
