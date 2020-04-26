package ua.nure.bolhov.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.UserStatus;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.web.command.Command;
import ua.nure.bolhov.SummaryTask4.web.command.CommandContainer;
import ua.nure.bolhov.SummaryTask4.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);
    public static final String SELECTED_LANGUAGE = "selectedLanguage";
    private static final String COMMAND = "command";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String LANGUAGE = "language";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug(LoggerUtils.CONTROLLER_STARTS);

        Command command = getCommand(request);
        LOG.trace(Constants.OBTAINED_COMMAND + command);

        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (AppException ex) {
            request.setAttribute(ERROR_MESSAGE, ex.getMessage());
        }
        LOG.trace(LoggerUtils.COMMAND_FORWARD + forward);


        String language = request.getParameter(LANGUAGE);
        request.setAttribute(SELECTED_LANGUAGE, language);
        LOG.debug(LoggerUtils.COMMAND_GO_FORWARD + forward);
        request.getRequestDispatcher(forward).forward(request, response);
    }

    private Command getCommand(HttpServletRequest request) {
        String commandName = request.getParameter(COMMAND);
        LOG.trace(LoggerUtils.COMMAND_COMMAND_PARAMETER + commandName);
        Command command = CommandContainer.getCommand(commandName);
        UserService userService = (UserService) getServletContext().getAttribute(Constants.USER_SERVICE_MANAGER);
        User user = (User) request.getSession().getAttribute(Constants.USER);
        if (Objects.nonNull(user)) {
            user = userService.findUser(user);
            request.getSession().setAttribute(Constants.USER, user);
            if (UserStatus.BUNNED.getNumber() == user.getIdStatus()) {
                command = CommandContainer.getCommand(Constants.LOGOUT_COMMAND);
            }
        }
        return command;
    }
}
