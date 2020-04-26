package ua.nure.bolhov.SummaryTask4.web.service;

import ua.nure.bolhov.SummaryTask4.db.dto.UserDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.web.bean.AuthBean;
import ua.nure.bolhov.SummaryTask4.web.bean.RegistrationBean;
import ua.nure.bolhov.SummaryTask4.web.bean.SettingBean;
import ua.nure.bolhov.SummaryTask4.web.bean.UpdateStatusBean;

import java.util.List;

public interface UserService {

    void updateUserStatus(UpdateStatusBean updateStatusBean);

    void updateUserInfo(SettingBean settingBean);

    void createClient(RegistrationBean registrationBean);

    void createManager(RegistrationBean registrationBean);

    List<UserDTO> getUserDTO();

    User findUser(AuthBean authBean);

    User findUser(RegistrationBean registrationBean);

    User findUser(User user);

}
