package ua.nure.bolhov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.bolhov.SummaryTask4.db.dto.CarDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.Brand;
import ua.nure.bolhov.SummaryTask4.db.entity.Category;
import ua.nure.bolhov.SummaryTask4.exception.AppException;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.LoggerUtils;
import ua.nure.bolhov.SummaryTask4.util.Path;
import ua.nure.bolhov.SummaryTask4.web.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchCarCommand extends Command {


    private static final Logger LOGGER = Logger.getLogger(SearchCarCommand.class);
    private static final String BRAND = "brand";
    private static final String CATEGORY = "category";
    private static final String FILTER = "filter";
    private static final String MIN_DATE = "minDate";
    private static final String BRANDS = "brandList";
    private static final String CATEGORIES = "categoryList";
    private static final String CARS = "carDTOList";
    private static final String SELECTED_BRAND = "selectedBrand";
    private static final String SELECTED_CATEGORY = "selectedCategory";
    private static final String SELECTED_FILTER = "selectedFilter";
    private static final int ONE = 1;
    private static final String COST = "cost";
    private static final String NAME = "name";
    private static final String ALL = "all";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        CarService carService = (CarService) servletContext.getAttribute(Constants.CAR_SERVICE_MANAGER);
        List<CarDTO> carDTOList = carService.getAllCarDTO();
        LOGGER.debug(LoggerUtils.COMMAND_STARTS);

        List<Category> categoryList = carService.getCategoryList();
        List<Brand> brandList = carService.getBrandList();

        String brandParameter = request.getParameter(BRAND);
        String categoryParameter = request.getParameter(CATEGORY);
        String filterParameter = request.getParameter(FILTER);

        List<CarDTO> resultCarDTO = makeResultList(carDTOList, brandParameter, categoryParameter);

        sortCar(filterParameter, resultCarDTO);

        LocalDate currentDate = LocalDate.now();

        String minDate = currentDate.plusDays(ONE).toString();

        request.setAttribute(MIN_DATE, minDate);

        request.setAttribute(BRANDS, brandList);
        request.setAttribute(CATEGORIES, categoryList);
        request.setAttribute(CARS, resultCarDTO);

        request.setAttribute(SELECTED_BRAND, brandParameter);
        request.setAttribute(SELECTED_CATEGORY, categoryParameter);
        request.setAttribute(SELECTED_FILTER, filterParameter);

        LOGGER.debug(LoggerUtils.COMMAND_STARTS);
        return Path.PAGE_CLIENT_CAR_LIST;
    }

    private void sortCar(String filterParameter, List<CarDTO> resultCarDTO) {
        if (Objects.equals(COST, filterParameter)) {
            resultCarDTO.sort((o1, o2) -> (int) (o1.getCost() - o2.getCost()));
        } else if (Objects.equals(NAME, filterParameter)) {
            resultCarDTO.sort(CarDTO::compareTo);
        }
    }


    private List<CarDTO> makeResultList(List<CarDTO> carDTOList, String brand, String category) {
        List<CarDTO> list = new ArrayList<>();
        if (isNull(brand, category)) {
            return carDTOList;
        } else if (!Objects.equals(ALL, brand) && !Objects.equals(ALL, category)) {
            for (CarDTO carDTO : carDTOList) {
                if (carDTO.getBrand().equals(brand) && carDTO.getCategory().equals(category)) {
                    list.add(carDTO);
                }
            }
        } else {
            for (CarDTO carDTO : carDTOList) {
                if (carDTO.getBrand().equals(brand) || carDTO.getCategory().equals(category)) {
                    list.add(carDTO);
                }
            }
        }
        return list;
    }

    private boolean isNull(String brand, String category) {
        return (Objects.isNull(brand) && Objects.isNull(category))
                || (Objects.equals(brand, ALL) && Objects.equals(category, ALL));
    }
}
