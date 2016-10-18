package github.crazydais.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class InterceptFilter extends GenericFilterBean {

  private final Log log = LogFactory.getLog(InterceptFilter.class);
  private FilterConfig config = null;

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res,  final FilterChain chain) throws IOException, ServletException {
      final HttpServletRequest request = (HttpServletRequest) req;

      final String authHeader = request.getHeader("Authorization");
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        throw new ServletException("Missing or invalid Authorization header.");
      }

      final String token = authHeader.substring(7);

      try {
        final Claims claims = Jwts.parser().setSigningKey("secretkey")
                .parseClaimsJws(token).getBody();
        request.setAttribute("claims", claims);
      }
      catch (final SignatureException e) {
        throw new ServletException("Invalid token.");
      }

      chain.doFilter(req, res);
    }

}
