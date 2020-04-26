package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.bean.UpdateStatusBean;
import ua.nure.bolhov.SummaryTask4.web.service.UserService;
import ua.nure.bolhov.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class UpdateUserStatusCommand extends Command {

    public static final Logger LOGGER = Logger.getLogger(UpdateUserStatusCommand.class);
    private static final String id_user = "id_user";
    private static final String STATUS = "status";
    private static final String ERRORS = "errorsStatus";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String idParameter = request.getParameter(id_user);
        String statusParameter =request.getParameter(STATUS);

        int idStatus = Integer.parseInt(statusParameter);

        UpdateStatusBean statusBean = new UpdateStatusBean();
        statusBean.setId(idParameter);
        statusBean.setStatus(idStatus);
        Locale locale = (Locale) request.getSession().getAttribute(Constants.LOCALE);

        Validator validator = (Validator) servletContext.getAttribute(Constants.VALIDATOR);
        Map<String, String> errors = validator.validate(statusBean,locale);
        request.setAttribute(ERRORS, errors);
        Util util = (Util) servletContext.getAttribute(Constants.UTIL);
        if (errors.isEmpty()&&util.isNotReSubmitting(request)) {
            LOGGER.trace(LoggerUtils.ALL_FIELDS_PASSED_VALIDATION);
            UserService userService = (UserService) servletContext.getAttribute(Constants.USER_SERVICE_MANAGER);
            userService.updateUserStatus(statusBean);
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.COMMAND_ADMIN_PANEL;
    }
}

