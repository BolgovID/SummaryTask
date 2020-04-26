package ua.nure.bolhov.SummaryTask4.web.command;

import ua.nure.bolhov.SummaryTask4.exception.AppException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    protected static ServletContext servletContext;

    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException, AppException;

    public static void setContext(ServletContext servletContext) {
        Command.servletContext = servletContext;
    }
}
