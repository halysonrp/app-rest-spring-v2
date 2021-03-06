package br.com.api.restful.securitys.filters;


import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.api.restful.entities.User;
import br.com.api.restful.securitys.utils.JwtTokenUtil;
import br.com.api.restful.services.IAuthService;
import br.com.api.restful.services.impl.UserServiceImpl;
import br.com.api.restful.utils.PasswordUtils;


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

	
		 /* Caso o token n�o exista, retornar erro com status apropriado com a mensagem "N�o autorizado". OK
			Caso o token( Header) exista, buscar o usu�rio pelo id passado no path e comparar se o token no modelo � igual ao token passado no header. - OK 
			Caso n�o seja o mesmo token, retornar erro com status apropriado e mensagem "N�o autorizado" - OK
			Caso seja o mesmo token, verificar se o �ltimo login foi a MENOS que 30 minutos atr�s. Caso n�o seja a MENOS que 30 minutos atr�s, retornar erro com status apropriado com mensagem "Sess�o inv�lida".
			Caso tudo esteja ok, retornar o usu�rio no mesmo formato do retorno do Login.
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
	    
		public void validToken(User user, String password, String token) {
			if (user.getToken() == null) {
				throw new UnauthorizedUserException("N�o autorizado");
			} else if (password != null && token != null) {
				if (!token.equals(user.getToken())) {
					throw new UnauthorizedUserException("N�o autorizado");
				} else if (!jwtTokenUtil.tokenValid(token)) {
					throw new AccessDeniedException("Sess�o inv�lida");
				}
			}
		}
		/*
		 * 
			Caso o e-mail n�o exista, retornar erro com status apropriado mais a mensagem "Usu�rio e/ou senha inv�lidos" - OK
			Caso o e-mail exista mas a senha n�o bata, retornar o status apropriado 401 mais a mensagem "Usu�rio e/ou senha inv�lidos"
		*/
		public User validLogin(User user, String password, String token) {
			if (!PasswordUtils.validPassword(password, user.getPassword()) && !password.equals(user.getPassword())){
				throw new UnauthorizedUserException("Usuario e/ou senha invalidos");
			}
			validToken(user, password, token);
			return user;
		}
	 
}
