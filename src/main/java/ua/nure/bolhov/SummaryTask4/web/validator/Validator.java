package ua.nure.bolhov.SummaryTask4.web.validator;

import ua.nure.bolhov.SummaryTask4.db.OrderStatus;
import ua.nure.bolhov.SummaryTask4.util.Util;
import ua.nure.bolhov.SummaryTask4.web.bean.AcceptCarBean;
import ua.nure.bolhov.SummaryTask4.web.bean.AuthBean;
import ua.nure.bolhov.SummaryTask4.web.bean.MakeOrderBean;
import ua.nure.bolhov.SummaryTask4.web.bean.OperationCarBean;
import ua.nure.bolhov.SummaryTask4.web.bean.PayOrderBean;
import ua.nure.bolhov.SummaryTask4.web.bean.RegistrationBean;
import ua.nure.bolhov.SummaryTask4.web.bean.SettingBean;
import ua.nure.bolhov.SummaryTask4.web.bean.TreatmentOrderBean;
import ua.nure.bolhov.SummaryTask4.web.bean.UpdateStatusBean;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Validator {

    private final Util util;
    private final String PREFIX = "valid_";
    private static final int MIN_AGE = 18;
    private static final int ONE = 1;
    private static final String SETTINGS = "settings";
    private static final String ADMIN = "admin";
    private static final String ORDER = "order";
    private static final String PAY = "pay";
    private static final String TREATMENT = "treatment";
    private static final String ACCEPT = "accept";

    public Validator(Util util) {
        this.util = util;
    }

    public Map<String, String> validate(SettingBean settingBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        if (Objects.isNull(settingBean.getFirstName()) || settingBean.getFirstName().isEmpty()) {
            errors.put("firstName", util.getTranslate(locale, PREFIX + SETTINGS, "firstName"));
        }
        if (Objects.isNull(settingBean.getLastName()) || settingBean.getLastName().isEmpty()) {
            errors.put("lastName", util.getTranslate(locale, PREFIX + SETTINGS, "lastName"));
        }
        if (settingBean.getAge() == 0) {
            errors.put("age", util.getTranslate(locale, PREFIX + SETTINGS, "age"));
        } else {
            if (settingBean.getAge() < 18) {
                errors.put("age", util.getTranslate(locale, PREFIX + SETTINGS, "young"));
            }
        }
        return errors;
    }

    public Map<String, String> validate(RegistrationBean registrationBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        if (Objects.isNull(registrationBean.getLogin()) || registrationBean.getLogin().isEmpty()) {
            errors.put("login", util.getTranslate(locale, PREFIX + ADMIN, "login"));
        } else {
            if (Objects.nonNull(registrationBean.getUser())) {
                errors.put("login", util.getTranslate(locale, PREFIX + ADMIN, "exist"));
            }
        }
        if (Objects.isNull(registrationBean.getPassword()) || registrationBean.getPassword().isEmpty()) {
            errors.put("password", util.getTranslate(locale, PREFIX + ADMIN, "password"));
        } else {
            if (Objects.isNull(registrationBean.getConfirm()) || registrationBean.getConfirm().isEmpty()) {
                errors.put("confirm", "Fill confirm field");
            } else {
                if (!registrationBean.getPassword().equals(registrationBean.getConfirm())) {
                    errors.put("confirm", util.getTranslate(locale, PREFIX + ADMIN, "mismatch"));
                }
            }
        }
        if (Objects.isNull(registrationBean.getFirstName()) || registrationBean.getFirstName().isEmpty()) {
            errors.put("firstName", util.getTranslate(locale, PREFIX + ADMIN, "firstName"));
        }
        if (Objects.isNull(registrationBean.getLastName()) || registrationBean.getLastName().isEmpty()) {
            errors.put("lastName", util.getTranslate(locale, PREFIX + ADMIN, "lastName"));
        }
        if (registrationBean.getAge() == 0) {
            errors.put("age", util.getTranslate(locale, PREFIX + ADMIN, "age"));
        } else {
            if (registrationBean.getAge() < MIN_AGE) {
                errors.put("age", util.getTranslate(locale, PREFIX + ADMIN, "young"));
            }
        }
        return errors;
    }

    public Map<String, String> validate(AuthBean authBean) {
        Map<String, String> errors = new HashMap<>();
        if (Objects.isNull(authBean.getLogin()) || authBean.getLogin().isEmpty()) {
            errors.put("login", "Fill login field");
        } else {
            if (Objects.nonNull(authBean.getUser())) {
                if (!authBean.getPassword().equals(authBean.getUser().getPassword())) {
                    errors.put("password", "The password is incorrect");
                }
            } else {
                errors.put("login", "User does not exist");
            }
        }
        if (Objects.isNull(authBean.getPassword()) || authBean.getPassword().isEmpty()) {
            errors.put("password", "Fill password field");
        }
        return errors;
    }

    public Map<String, String> validate(OperationCarBean operationCarBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        if (operationCarBean.getId() < ONE) {
            errors.put("id", util.getTranslate(locale, PREFIX + ADMIN, "id"));
        }
        if (Objects.isNull(operationCarBean.getModel()) || operationCarBean.getModel().isEmpty()) {
            errors.put("model", util.getTranslate(locale, PREFIX + ADMIN, "model"));
        }
        if (operationCarBean.getCost() < ONE) {
            errors.put("cost", util.getTranslate(locale, PREFIX + ADMIN, "cost"));
        }
        return errors;
    }

    public Map<String, String> validate(UpdateStatusBean statusBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        if (statusBean.getId() < 1) {
            errors.put("id", util.getTranslate(locale, PREFIX + ADMIN, "id"));
        }
        return errors;
    }

    public Map<String, String> validate(MakeOrderBean makeOrderBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        if (makeOrderBean.getIdCar() == 0) {
            errors.put("car", util.getTranslate(locale, PREFIX + ORDER, "car"));
        }
        if (makeOrderBean.getTo() == 0 || makeOrderBean.getFrom() == 0) {
            errors.put("date", util.getTranslate(locale, PREFIX + ORDER, "empty"));
        }
        if (makeOrderBean.getTo() <= makeOrderBean.getFrom()) {
            errors.put("date", util.getTranslate(locale, PREFIX + ORDER, "incorrect"));
        }
        return errors;
    }

    public Map<String, String> validate(PayOrderBean payOrderBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        if (payOrderBean.getIdOrder() == 0 || payOrderBean.getIdBill() == 0) {
            errors.put("bill", util.getTranslate(locale, PREFIX + PAY, "bill"));
        }
        if (Objects.isNull(payOrderBean.getStatus()) || payOrderBean.getStatus().isEmpty()) {
            errors.put("bill", util.getTranslate(locale, PREFIX + PAY, "bill"));
        }
        return errors;
    }

    public Map<String, String> validate(TreatmentOrderBean treatmentOrderBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        if (OrderStatus.REJECTED.equals(treatmentOrderBean.getStatus()) &&
                (Objects.isNull(treatmentOrderBean.getComment()) || treatmentOrderBean.getComment().isEmpty())) {
            errors.put("comment", util.getTranslate(locale, PREFIX + TREATMENT, "comment"));
        }
        return errors;
    }

    public Map<String, String> validate(AcceptCarBean acceptCarBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        if (Objects.equals("penalty", acceptCarBean.getDecide())) {
            if (Objects.isNull(acceptCarBean.getComment()) || acceptCarBean.getComment().isEmpty()) {
                errors.put("comment", util.getTranslate(locale, PREFIX + ACCEPT, "comment"));
            }
            if (acceptCarBean.getCost() == 0) {
                errors.put("cost", util.getTranslate(locale, PREFIX + ACCEPT, "cost"));
            }
        }
        return errors;
    }
}