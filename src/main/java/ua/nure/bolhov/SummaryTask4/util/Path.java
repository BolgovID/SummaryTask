package ua.nure.bolhov.SummaryTask4.util;


public final class Path {

   
    public static final String PAGE_CLIENT_CAR_LIST = "/WEB-INF/jsp/client/car_list.jsp";
	public static final String PAGE_CLIENT_ORDER_LIST = "/WEB-INF/jsp/client/pay_order_list.jsp";
   
    public static final String PAGE_MANAGER_ORDER_LIST = "/WEB-INF/jsp/manager/accept_order.jsp";
	public static final String PAGE_MANAGER_RETURN_CAR = "/WEB-INF/jsp/manager/return_car.jsp";
  
    public static final String PAGE_ADMIN_CONTROL = "/WEB-INF/jsp/admin/admin_panel.jsp";
    
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/common/settings.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/common/error_page.jsp";
    public static final String PAGE_LOGIN ="/index.jsp" ;
    public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/out-of-control/registration.jsp";
    
    public static final String COMMAND_CLIENT_ORDER_LIST ="/controller?command=orderClientList";
    public static final String COMMAND_CLIENT_CAR_LIST = "/controller?command=searchCar";

    public static final String COMMAND_MANAGER_CHECK_CARS = "/controller?command=checkCar" ;
    public static final String COMMAND_MANAGER_LIST_ORDERS = "/controller?command=listOrders";

    public static final String COMMAND_ADMIN_PANEL = "/controller?command=adminPanel";

    public static final String COMMAND_SETTINGS ="/controller?command=viewSettings" ;

    public static final String COMMAND_LOGIN = "/controller?command=login";
}