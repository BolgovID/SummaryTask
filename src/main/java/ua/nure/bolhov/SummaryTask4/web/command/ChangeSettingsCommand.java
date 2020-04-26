package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.bean.SettingBean;
import ua.nure.bolhov.SummaryTask4.web.service.UserService;
import ua.nure.bolhov.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class ChangeSettingsCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ChangeSettingsCommand.class);
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String AGE = "age";
    private static final String USER = "user";
    private static final String ERRORS = "errors";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String ageParameter = request.getParameter(AGE);
        User user = (User) request.getSession().getAttribute(USER);

        SettingBean settingBean = new SettingBean();
        settingBean.setId(user.getId());
        settingBean.setFirstName(firstName);
        settingBean.setLastName(lastName);
        settingBean.setAge(ageParameter);

        Validator validator = (Validator) Command.servletContext.getAttribute(Constants.VALIDATOR);
        Locale locale = (Locale) request.getSession().getAttribute(Constants.LOCALE);
        Map<String, String> error = validator.validate(settingBean, locale);
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);
        request.setAttribute(ERRORS, error);

        if (error.isEmpty() && util.isNotReSubmitting(request)) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            UserService userService = (UserService) servletContext.getAttribute(Constants.USER_SERVICE_MANAGER);
            userService.updateUserInfo(settingBean);
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_SETTINGS;
    }
}
