package ua.nure.bolhov.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.StringTokenizer;

public class LocaleFilter implements Filter {

    List<String> supportedLanguages;
    private String defaultLanguage;
    private ServletContext servletContext;

    private final Logger LOGGER = Logger.getLogger(Locale.class);

    public void destroy() {
        LOGGER.debug(LoggerUtils.FILTER_DESTROY_START);
        LOGGER.debug(LoggerUtils.FILTER_DESTROY_END);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        LOGGER.debug(LoggerUtils.FILTER_START);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpRequest.getSession();
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);

        String localeStr = httpRequest.getParameter(Constants.LOCALE_PARAMETER);
        Locale locale = (Locale) httpSession.getAttribute(Constants.LOCALE);
        LOGGER.trace(LoggerUtils.FIlTER_SESSION_LOCALE + locale);
        if (Objects.isNull(locale)) {
            locale = util.toLocale(defaultLanguage);
            httpSession.setAttribute(Constants.LOCALE, locale);
            LOGGER.trace(LoggerUtils.FILTER_SET_DEFAULT_LOCALE + Constants.DEFAULT_LOCALE_TAG);
        }
        if (Objects.nonNull(localeStr)) {
            if (supportedLanguages.contains(localeStr)) {
                httpSession.setAttribute(Constants.LOCALE, util.toLocale(localeStr));
                LOGGER.trace(LoggerUtils.FILTER_SET_LOCALE + localeStr);
            } else {
                httpSession.setAttribute(Constants.LOCALE, util.toLocale(defaultLanguage));
                LOGGER.trace(LoggerUtils.FILTER_SET_DEFAULT_LOCALE + Constants.DEFAULT_LOCALE_TAG);
            }
        }
        servletResponse.setContentType(Constants.CONTENT_TYPE);
        servletResponse.setCharacterEncoding(Constants.UTF_8);
        LOGGER.debug(LoggerUtils.FILTER_END);
        chain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig config){
        LOGGER.debug(LoggerUtils.FILTER_INIT_START);
        supportedLanguages = asList(config.getInitParameter(Constants.SUPPORTED_LOCALES));
        defaultLanguage = config.getInitParameter(Constants.DEFAULT_LOCALE);
        servletContext = config.getServletContext();
        LOGGER.debug(LoggerUtils.FILTER_INIT_END);
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
