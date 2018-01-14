package be.rds.ezbanking.application.security;

import static be.rds.ezbanking.application.security.SecurityConstants.HEADER_STRING;
import static be.rds.ezbanking.application.security.SecurityConstants.SECRET;
import static be.rds.ezbanking.application.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(!authenticationHeaderExists(request)) {
			chain.doFilter(request, response);
			return;
		}
		
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
	}


	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String bearerString = request.getHeader(HEADER_STRING);
		String jsonWebToken = bearerString.replace(TOKEN_PREFIX, "");
		
		String userName = Jwts.parser().setSigningKey(SECRET.getBytes())
			.parseClaimsJws(jsonWebToken)
			.getBody().getSubject();
		if(userName != null) {
			return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
		}
		return null;
	}


	private boolean authenticationHeaderExists(HttpServletRequest request) {
		String authenticationHeader = request.getHeader(HEADER_STRING);
		return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_PREFIX);
	}
}
