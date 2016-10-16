package github.crazydais.filter;

import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InterceptFilter implements Filter {

  private final Log log = LogFactory.getLog(InterceptFilter.class);
  private FilterConfig config = null;

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    // Get the IP address of client machine.   
    String ipAddress = request.getRemoteAddr();

    Cookie reqCookies[] = request.getCookies();

    Cookie customCookie = new Cookie("custom-cookie-key", "custom-cookie-value");
    customCookie.setDomain(req.getServerName());
    customCookie.setPath(request.getContextPath());
    customCookie.setSecure(request.isSecure());
    customCookie.setHttpOnly(false);
    response.addCookie(customCookie);

    // Log the IP address and current timestamp.
    log.info("IP " + ipAddress + ", Time " + new Date().toString());

    chain.doFilter(req, res);
  }

  public void init(FilterConfig config) throws ServletException {
    this.config = config;
    config.getServletContext().log("Initializing InterceptFilter");
  }

  public void destroy() {
    config.getServletContext().log("Destroying InterceptFilter");
  }

}
