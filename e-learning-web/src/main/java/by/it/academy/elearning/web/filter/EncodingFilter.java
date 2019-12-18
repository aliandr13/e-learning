package by.it.academy.elearning.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(EncodingFilter.class);
    public static final String UTF_8 = "UTF-8";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        logger.debug("EncodingFilter");
        req.setCharacterEncoding(UTF_8);
        res.setCharacterEncoding(UTF_8);
        super.doFilter(req, res, chain);
    }
}
