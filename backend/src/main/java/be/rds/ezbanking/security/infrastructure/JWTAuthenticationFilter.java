package be.rds.ezbanking.security.infrastructure;

import static be.rds.ezbanking.security.infrastructure.SecurityConstants.EXPIRATION_TIME_10DAYS;
import static be.rds.ezbanking.security.infrastructure.SecurityConstants.HEADER_STRING;
import static be.rds.ezbanking.security.infrastructure.SecurityConstants.SECRET;
import static be.rds.ezbanking.security.infrastructure.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.rds.ezbanking.security.domain.model.ApplicationUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			ApplicationUser user = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
			UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(
					user.getUsername(), user.getPassword());
			return authenticationManager.authenticate(usernamePasswordToken);

		} catch (IOException e) {
			throw new InternalAuthenticationServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	
		User user = ((User)authResult.getPrincipal());
		Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME_10DAYS); 
		
		String jwt = Jwts.builder()
				.setSubject(user.getUsername())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
		
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
	}

}
