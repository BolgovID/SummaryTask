package ua.nure.bolhov.SummaryTask4.util;

public final class LoggerUtils {


    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Can't obtain data source.";
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Can't obtain connection.";
    public static final String ERR_FAIL_TRANSACTION = "Cannot do transaction.";
    public static final Object ERR_CANNOT_CONVERT_STRING_TO_TIMESTAMP = "Cannot convert string to Timestamp type.";

    public static final String FILTER_INIT_START = "Filter initialization starts";
    public static final String FILTER_DESTROY_START = "Filter destruction starts";
    public static final String FILTER_INIT_END = "Filter initialization ends";
    public static final String FILTER_DESTROY_END = "Filter destruction ends";
    public static final String FILTER_START = "Filter starts";
    public static final String FILTER_END = "Filter ends";
    public static final String FILTER_SET_ERR_ATTRIBUTE = "Set the request attribute: errorMessage --> ";
    public static final String FILTER_ACCESS_MAP = "Access map --> ";
    public static final String FILTER_COMMON_COMMANDS = "Common commands --> ";
    public static final String FILTER_OUT_OF_CONTROL_COMMANDS = "Out of control commands --> ";
    public static final String FILTER_SET_DEFAULT_ENCODING = "Request encoding = null, set encoding --> ";
    public static final String FILTER_ENCODING_FROM_XML = "Encoding from web.xml --> ";
    public static final String FILTER_REQUEST_URI = "Request uri --> ";
    public static final String FIlTER_SESSION_LOCALE = "Session locale--> ";
    public static final String FILTER_SET_DEFAULT_LOCALE = "Set default locale: ";
    public static final String FILTER_SET_LOCALE = "Set locale: ";
    public static final String CONTROLLER_STARTS = "Controller starts";
    public static final String COMMAND_FORWARD = "Forward address--> ";
    public static final String COMMAND_GO_FORWARD="Go to forward address-->";
    public static final String COMMAND_COMMAND_PARAMETER="Request parameter: command --> ";
    public static final String DATA_SOURCE = "Data source -->";
    public static final String ERR_ROLLBACK_TRANSACTION = "Cannot rollback transaction";
    public static final String ALL_FIELDS_PASSED_VALIDATION = "All fields passed validation";
    public static final String COMMAND_STARTS = "Command starts";
    public static final String COMMAND_FINISHED = "Command finished";

    private LoggerUtils() {
    }
}
