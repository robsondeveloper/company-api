package company.api.authentication;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import company.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenManager {

	private String secret;
	private long expirationInMillis;

	public JwtTokenManager(@Value("${COMPANY_JWT_SECRET}") String secret,
			@Value("${COMPANY_JWT_EXPIRATION}") long expirationInMillis) {
		this.secret = secret;
		this.expirationInMillis = expirationInMillis;
	}

	public String generateToken(User user) {
		final Date now = new Date();
		final Date expiration = new Date(now.getTime() + this.expirationInMillis);
		return Jwts.builder().setIssuer("Issuer").setSubject(user.getUsername()).setIssuedAt(now)
				.setExpiration(expiration).signWith(SignatureAlgorithm.HS256, this.secret).compact();
	}

	public boolean isValid(String jwt) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public String getUsernameFromToken(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt).getBody();
		return claims.getSubject();
	}

}
