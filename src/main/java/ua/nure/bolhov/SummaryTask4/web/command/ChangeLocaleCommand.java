package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.Role;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.nure.bolhov.SummaryTask4.db.Role.ADMIN;
import static ua.nure.bolhov.SummaryTask4.db.Role.MANAGER;

public class ChangeLocaleCommand extends Command {

    public static final Logger LOGGER = Logger.getLogger(ChangeLocaleCommand.class);
    private static final String USER_ROLE = "userRole";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        Role role = (Role) request.getSession().getAttribute(USER_ROLE);
        String forward;
        if (ADMIN.equals(role)) {
            forward = Path.COMMAND_ADMIN_PANEL;
        } else if (MANAGER.equals(role)) {
            forward = Path.COMMAND_MANAGER_LIST_ORDERS;
        } else {
            forward = Path.COMMAND_CLIENT_CAR_LIST;
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return forward;
    }
}
