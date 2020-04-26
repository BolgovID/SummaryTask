package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.bean.RegistrationBean;
import ua.nure.bolhov.SummaryTask4.web.service.UserService;
import ua.nure.bolhov.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class AddManagerCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(AddManagerCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String AGE = "age";
    private static final String ERRORS = "errorsManager";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String loginParameter = request.getParameter(LOGIN);
        String passwordParameter = request.getParameter(PASSWORD);
        String firstNameParameter = request.getParameter(FIRST_NAME);
        String lastNameParameter = request.getParameter(LAST_NAME);
        String ageParameter = request.getParameter(AGE);

        UserService userService = (UserService) servletContext.getAttribute(Constants.USER_SERVICE_MANAGER);
        Validator validator = (Validator) servletContext.getAttribute(Constants.VALIDATOR);
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);

        RegistrationBean registrationBean = new RegistrationBean();
        registrationBean.setLogin(loginParameter);
        registrationBean.setPassword(passwordParameter);
        registrationBean.setConfirm(passwordParameter);
        registrationBean.setFirstName(firstNameParameter);
        registrationBean.setLastName(lastNameParameter);
        registrationBean.setAge(ageParameter);
        registrationBean.setUser(userService.findUser(registrationBean));
        Locale locale = (Locale) request.getSession().getAttribute(Constants.LOCALE);

        Map<String, String> errors = validator.validate(registrationBean, locale);
        request.setAttribute(ERRORS, errors);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            userService.createManager(registrationBean);
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_ADMIN_PANEL;
    }
}
