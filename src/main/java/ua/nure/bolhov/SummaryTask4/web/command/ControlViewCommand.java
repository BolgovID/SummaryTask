package ua.nure.bolhov.SummaryTask4.web.command;


import ua.nure.bolhov.SummaryTask4.db.dto.CarDTO;
import ua.nure.bolhov.SummaryTask4.db.dto.UserDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Brand;
import ua.nure.bolhov.SummaryTask4.db.entity.Category;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.web.service.CarService;
import ua.nure.bolhov.SummaryTask4.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ControlViewCommand extends Command {

    private static final String USER_DTO_LIST = "userDTOList";
    private static final String CAR_DTO_LIST = "carDTOList";
    private static final String BRAND_LIST = "brandList";
    private static final String CATEGORY_LIST = "categoryList";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        UserService userService = (UserService) servletContext.getAttribute(Constants.USER_SERVICE_MANAGER);
        CarService carService = (CarService) servletContext.getAttribute(Constants.CAR_SERVICE_MANAGER);

        List<UserDTO> userDTOList = userService.getUserDTO();
        List<CarDTO> carDTOList = carService.getAllCarDTO();
        List<Brand> brandList = carService.getBrandList();
        List<Category> categoryList = carService.getCategoryList();
        request.setAttribute(USER_DTO_LIST, userDTOList);
        request.setAttribute(CAR_DTO_LIST, carDTOList);
        request.setAttribute(BRAND_LIST, brandList);
        request.setAttribute(CATEGORY_LIST, categoryList);
        userDTOList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        carDTOList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return Path.PAGE_ADMIN_CONTROL;
    }
}
