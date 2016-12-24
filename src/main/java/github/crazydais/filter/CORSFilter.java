package github.crazydais.filter;

import github.crazydais.constants.Resources;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CORSFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal (HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        Properties props = new Properties();
        try (InputStream is = new FileInputStream(
            Resources.APPLICATION.file())) {
            props.load(is);
        }
        String domain = props.getProperty("domain");
        if (request.getHeader("Origin") == null || request.getHeader("Origin")
            .equalsIgnoreCase("null")) {
            response.setHeader("Access-Control-Allow-Origin", domain);
            response.setHeader("Access-Control-Allow-Methods",
                "POST, PUT, PATCH, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Max-Age", "3600");

            String reqHead =
                request.getHeader("Access-Control-Request-Headers");
            if (!StringUtils.isEmpty(reqHead)) {
                response.addHeader("Access-Control-Allow-Headers", reqHead);
            }
        }
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus((domain.equalsIgnoreCase("*")) ?
                HttpServletResponse.SC_OK :
                HttpServletResponse.SC_FORBIDDEN);
        } else {
            filterChain.doFilter(request, response);
        }
    }


}
