package ua.nure.bolhov.SummaryTask4.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Util {

    private static final Logger LOGGER = Logger.getLogger(Util.class);
    private static final String PATTERN = "yyyy-MM-dd";

    public boolean isNotReSubmitting(HttpServletRequest request) {
        String checkFromRequest = request.getParameter(Constants.RESUBMITTING_PARAMETER);
        String checkFromSession = (String) request.getSession().getAttribute(Constants.RESUBMITTING_PARAMETER);
        request.getSession().setAttribute(Constants.RESUBMITTING_PARAMETER, checkFromRequest);
        return !checkFromRequest.equals(checkFromSession);
    }

    public Timestamp convertStringToTimestamp(String strDate) {
        if (Objects.nonNull(strDate)) {
            try {
                DateFormat formatter = new SimpleDateFormat(PATTERN);
                Date date = formatter.parse(strDate);
                return new Timestamp(date.getTime());
            } catch (ParseException e) {
                LOGGER.error(LoggerUtils.ERR_CANNOT_CONVERT_STRING_TO_TIMESTAMP);
                return null;
            }
        }
        return null;
    }

    public Locale toLocale(String localeStr) {
        return new Locale(localeStr);
    }

    public String getTranslate(Locale locale, String prefix, String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE, locale);
        return resourceBundle.getString(prefix + "." + key);
    }


}
