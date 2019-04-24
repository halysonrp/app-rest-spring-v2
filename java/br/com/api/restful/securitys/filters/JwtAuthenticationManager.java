package br.com.api.restful.securitys.filters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.securitys.utils.JwtTokenUtil;
import br.com.api.restful.services.IAuthService;
import br.com.api.restful.services.impl.AuthServiceImpl;
import br.com.api.restful.services.impl.UserServiceImpl;
import br.com.api.restful.utils.PasswordUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


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
		
		@Autowired
		private JwtTokenUtil jwtTokenUtil;
		
	    //@Autowired
	   // private PasswordEncoder passwordEncoder;
	
		//@Autowired
		//private TokenStore tokenStore;

	
		 /* Caso o token não exista, retornar erro com status apropriado com a mensagem "Não autorizado". OK
			Caso o token( Header) exista, buscar o usuário pelo id passado no path e comparar se o token no modelo é igual ao token passado no header. - OK 
			Caso não seja o mesmo token, retornar erro com status apropriado e mensagem "Não autorizado" - OK
			Caso seja o mesmo token, verificar se o último login foi a MENOS que 30 minutos atrás. Caso não seja a MENOS que 30 minutos atrás, retornar erro com status apropriado com mensagem "Sessão inválida".
			Caso tudo esteja ok, retornar o usuário no mesmo formato do retorno do Login.
			(non-Javadoc)
		 * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
		 */
		
		private static final String AUTH_HEADER = "Authorization";
	    @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    	 final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
	    	 String token = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader(AUTH_HEADER);

	         
	    	
	        String principal = (String) authentication.getPrincipal();
	        String password = null;
	        User user = null;
	        
	        
	        if (authentication instanceof UsernamePasswordAuthenticationToken) {
	        	user = loginService.findByEmail(principal);
	            password = (String) authentication.getCredentials();
	        }else {
	            //token = principal;
	            //OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(principal);
	            //UUID id = UUID.fromString((String) auth2Authentication.getPrincipal());
	            //user = userService.findById(id);
	        }
	        
	        //validLogin(user);
   
	        if (user != null) {
	            if (password != null) {
	            	validLogin(user, password, token);
	                //user.setToken(token);
	                //userService.save(user);
	                return new UsernamePasswordAuthenticationToken(user.getId().toString(), user.getPassword(),
	                        authentication.getAuthorities());
	            }
	        }
	        throw new AccessDeniedException("Usuario e/ou senha invalidos");
	    }
	    
		public void validToken(User user, String password, String token) throws AuthenticationException {
			if (user.getToken() == null) {
				throw new UnauthorizedUserException("Não autorizado");
			} else if (password != null && token != null) {
				if (!token.equals(user.getToken())) {
					throw new UnauthorizedUserException("Não autorizado");
				} else if (!jwtTokenUtil.tokenValid(token)) {
					throw new AccessDeniedException("Sessão inválida");
				}
			}
		}
		/*
		 * 
			Caso o e-mail não exista, retornar erro com status apropriado mais a mensagem "Usuário e/ou senha inválidos" - OK
			Caso o e-mail exista mas a senha não bata, retornar o status apropriado 401 mais a mensagem "Usuário e/ou senha inválidos"
		*/
		public User validLogin(User user, String password, String token) throws AuthenticationException {
			if (!PasswordUtils.validPassword(password, user.getPassword()) && !password.equals(user.getPassword())){
				throw new UnauthorizedUserException("Usuario e/ou senha invalidos");
			}
			validToken(user, password, token);
			return user;
		}
	 
}
