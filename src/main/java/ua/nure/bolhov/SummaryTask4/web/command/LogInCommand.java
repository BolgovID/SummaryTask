package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.Role;
import ua.nure.bolhov.SummaryTask4.db.UserStatus;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.web.bean.AuthBean;
import ua.nure.bolhov.SummaryTask4.web.service.UserService;
import ua.nure.bolhov.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


public class LogInCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(LogInCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERRORS = "errors";
    private static final String ERROR_MESSAGE = "errorMessage";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        HttpSession session = request.getSession();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        UserService userService = (UserService) servletContext.getAttribute(Constants.USER_SERVICE_MANAGER);

        AuthBean authBean = new AuthBean();
        authBean.setLogin(login);
        authBean.setPassword(password);

        User user = userService.findUser(authBean);
        authBean.setUser(user);

        Validator validator = (Validator) servletContext.getAttribute(Constants.VALIDATOR);
        Map<String, String> errors = validator.validate(authBean);
        request.setAttribute(ERRORS, errors);
        String forward = Path.PAGE_LOGIN;
        if (errors.isEmpty()) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            Role userRole = Role.getRole(user);
            UserStatus userStatus = UserStatus.getStatus(user);

            if (userRole == Role.MANAGER) {
                forward = Path.COMMAND_MANAGER_LIST_ORDERS;
            }
            if (userRole == Role.CLIENT) {
                forward = Path.COMMAND_CLIENT_CAR_LIST;
            }
            if (userRole == Role.ADMIN) {
                forward = Path.COMMAND_ADMIN_PANEL;
            }

            if (userStatus.equals(UserStatus.BUNNED)) {
                request.setAttribute(ERROR_MESSAGE, Constants.BANNED_MESSAGE);
                forward = Path.PAGE_ERROR_PAGE;
            } else {
                session.setAttribute(Constants.USER_STATUS, userStatus);
                session.setAttribute(Constants.USER, user);
                session.setAttribute(Constants.USER_ROLE, userRole);
            }
        } else {
            request.setAttribute(LOGIN, login);
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return forward;
    }
}
