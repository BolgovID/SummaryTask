package ua.nure.bolhov.SummaryTask4.web.tag;

import ua.nure.bolhov.SummaryTask4.db.Role;
import ua.nure.bolhov.SummaryTask4.util.Constants;
import ua.nure.bolhov.SummaryTask4.util.Path;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuTag extends SimpleTagSupport {
    private Role role;
    private List<MenuOption> menuOptions;
    private int counter;
    private static Locale locale;


    public void setRole(String role) {
        this.role = Role.valueOf(role);
        menuOptions = MenuOption.getMenuOption(this.role);
        counter = menuOptions.size();
    }

    public void setLocale(String locale) {
        if (Objects.isNull(locale) || locale.isEmpty()) {
            locale = Constants.DEFAULT_LOCALE_TAG;
        }
        MenuTag.locale = Locale.forLanguageTag(locale);

    }

    public void doTag() throws IOException {
        if (Objects.nonNull(role) && counter != 0) {
            JspWriter out = getJspContext().getOut();
            out.println(linkBuilder(menuOptions.get(--counter)));
            doTag();
        }
    }

    private String linkBuilder(MenuOption option) {
        String tagSpan = "<li><span class=\"material-icons\">";
        String tagSpanEnd = "</span><a href=\"";
        String tagEnd = "\">";
        String endHref = "</a ></li>";
        return tagSpan + option.icon + tagSpanEnd + option.command + tagEnd + option.option + endHref;
    }

    private static class MenuOption {
        private String option;
        private String command;
        private String icon;

        public static List<MenuOption> getMenuOption(Role role) {
            List<MenuOption> options = new ArrayList<>();
            switch (role) {
                case CLIENT:
                    MenuOption makeOrder = new MenuOption();
                    makeOrder.option = getOption("make_order");
                    makeOrder.command = Path.COMMAND_CLIENT_CAR_LIST.substring(1);
                    makeOrder.icon = "shopping_basket";

                    MenuOption payCar = new MenuOption();
                    payCar.option = getOption("pay_car");
                    payCar.command = Path.COMMAND_CLIENT_ORDER_LIST.substring(1);
                    payCar.icon = "attach_money";

                    options.add(makeOrder);
                    options.add(payCar);
                    break;
                case MANAGER:
                    MenuOption checkOrders = new MenuOption();
                    checkOrders.option = getOption("check_orders");
                    System.out.println(checkOrders.option);
                    checkOrders.command = Path.COMMAND_MANAGER_LIST_ORDERS.substring(1);
                    checkOrders.icon = "list_alt";

                    MenuOption checkCars = new MenuOption();
                    checkCars.option = getOption("check_cars");
                    checkCars.command = Path.COMMAND_MANAGER_CHECK_CARS.substring(1);
                    checkCars.icon = "view_headline";

                    options.add(checkOrders);
                    options.add(checkCars);
                    break;
                case ADMIN:
                    MenuOption control = new MenuOption();
                    control.option = getOption("admin");
                    control.command = Path.COMMAND_ADMIN_PANEL.substring(1);
                    control.icon = "";
                    options.add(control);
            }
            return options;
        }

        private MenuOption() {
        }

        private static String getOption(String value) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE, locale);
            return resourceBundle.getString("menu." + value);

        }


    }
}
