package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand extends Command {

    public static final Logger LOGGER = Logger.getLogger(LogOutCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.PAGE_LOGIN;
    }

}
