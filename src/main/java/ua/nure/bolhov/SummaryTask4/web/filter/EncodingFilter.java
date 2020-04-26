package ua.nure.bolhov.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class EncodingFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    public void destroy() {
        LOGGER.debug(LoggerUtils.FILTER_DESTROY_START);
        LOGGER.debug(LoggerUtils.FILTER_DESTROY_END);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        LOGGER.debug(LoggerUtils.FILTER_START);

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        LOGGER.trace(LoggerUtils.FILTER_REQUEST_URI + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (Objects.isNull(requestEncoding)) {
            LOGGER.trace(LoggerUtils.FILTER_SET_DEFAULT_ENCODING + encoding);
            request.setCharacterEncoding(encoding);
        }

        LOGGER.debug(LoggerUtils.FILTER_END);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig){
        LOGGER.debug(LoggerUtils.FILTER_INIT_START);
        encoding = fConfig.getInitParameter(Constants.ENCODING);
        LOGGER.trace(LoggerUtils.FILTER_ENCODING_FROM_XML + encoding);
        LOGGER.debug(LoggerUtils.FILTER_INIT_END);
    }

}