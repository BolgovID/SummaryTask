package ua.nure.bolhov.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.Role;
import ua.nure.bolhov.SummaryTask4.db.UserStatus;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class CommandAccessFilter implements Filter {

    private static final String ADMIN = "admin";
    private static final String CLIENT = "client";
    private static final String MANAGER = "manager";
    private static final String COMMON = "common";
    private static final String OUT_OF_CONTROL = "out-of-control";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String COMMAND = "command";
    private static final String USER_ROLE = "userRole";
    private static final String USER_STATUS = "userStatus";

    private Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> outOfControl = new ArrayList<>();

    Logger LOG = Logger.getLogger(CommandAccessFilter.class);

    @Override
    public void init(FilterConfig fConfig) {
        LOG.debug(LoggerUtils.FILTER_INIT_START);

        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter(ADMIN)));
        accessMap.put(Role.CLIENT, asList(fConfig.getInitParameter(CLIENT)));
        accessMap.put(Role.MANAGER, asList(fConfig.getInitParameter(MANAGER)));
        commons = asList(fConfig.getInitParameter(COMMON));
        outOfControl = asList(fConfig.getInitParameter(OUT_OF_CONTROL));

        LOG.trace(LoggerUtils.FILTER_ACCESS_MAP + accessMap);
        LOG.trace(LoggerUtils.FILTER_COMMON_COMMANDS + commons);
        LOG.trace(LoggerUtils.FILTER_OUT_OF_CONTROL_COMMANDS + outOfControl);
        LOG.debug(LoggerUtils.FILTER_INIT_END);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (accessAllowed(servletRequest)) {
            LOG.debug(LoggerUtils.FILTER_START);
            LOG.debug(LoggerUtils.FILTER_END);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String errorMessage = Constants.ERR_DO_NOT_HAVE_PERMISSION;
            servletRequest.setAttribute(ERROR_MESSAGE, errorMessage);
            LOG.trace(LoggerUtils.FILTER_SET_ERR_ATTRIBUTE + errorMessage);
            servletRequest.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        LOG.debug(LoggerUtils.FILTER_DESTROY_START);
        LOG.debug(LoggerUtils.FILTER_END);
    }

    private boolean accessAllowed(ServletRequest servletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String commandName = servletRequest.getParameter(COMMAND);
        if (Objects.isNull(commandName) || commandName.isEmpty()) {
            return false;
        }
        HttpSession session = httpRequest.getSession(false);
        if (outOfControl.contains(commandName)) {
            return true;
        }
        if (Objects.isNull(session)) {
            return false;
        }

        Role userRole = (Role) session.getAttribute(USER_ROLE);
        UserStatus userStatus = (UserStatus) session.getAttribute(USER_STATUS);
        if (Objects.isNull(userRole) || Objects.isNull(userStatus)) {
            return false;
        }
        if (userStatus.equals(UserStatus.BUNNED)) {
            return false;
        }
        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
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
