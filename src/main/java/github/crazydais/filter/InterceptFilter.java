package github.crazydais.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class InterceptFilter extends GenericFilterBean {

    private final Log log = LogFactory.getLog(InterceptFilter.class);

    @Override
    public void doFilter (final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header.");
        }

        final String token = authHeader.substring(7);

        try {
            // the signingkey is base64 encoded (ie. secretkey = c2VjcmV0a2V5)
            final Claims claims = Jwts.parser().setSigningKey("c2VjcmV0a2V5").parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        } catch (final SignatureException e) {
            throw new ServletException("Invalid token: ", e);
        }

        chain.doFilter(req, res);
    }


}
