package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);
    private static final String ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        String errorMessage = Constants.NO_SUCH_COMMAND;
        request.setAttribute(ERROR_MESSAGE, errorMessage);

        LOGGER.debug(LoggerUtils.COMMAND_FINISHED);
        return Path.PAGE_ERROR_PAGE;
    }
}
