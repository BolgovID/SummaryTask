package ua.nure.bolhov.SummaryTask4.web.service.impl;

import ua.nure.bolhov.SummaryTask4.db.Role;
import ua.nure.bolhov.SummaryTask4.db.UserStatus;
import ua.nure.bolhov.SummaryTask4.db.dto.UserDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.db.repository.UserRepository;
import ua.nure.bolhov.SummaryTask4.web.bean.AcceptCarBean;
import ua.nure.bolhov.SummaryTask4.web.bean.AuthBean;
import ua.nure.bolhov.SummaryTask4.web.bean.RegistrationBean;
import ua.nure.bolhov.SummaryTask4.web.bean.SettingBean;
import ua.nure.bolhov.SummaryTask4.web.bean.UpdateStatusBean;
import ua.nure.bolhov.SummaryTask4.web.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository USER_REPOSITORY;

    public UserServiceImpl(UserRepository USER_REPOSITORY) {
        this.USER_REPOSITORY = USER_REPOSITORY;
    }


    @Override
    public void updateUserStatus(UpdateStatusBean updateStatusBean) {
        USER_REPOSITORY.updateUserStatus(getEntity(updateStatusBean));
    }


    @Override
    public void updateUserInfo(SettingBean settingBean) {
        USER_REPOSITORY.updateUser(getEntity(settingBean));
    }

    @Override
    public void createClient(RegistrationBean registrationBean) {
        USER_REPOSITORY.createUser(getEntity(registrationBean,Role.CLIENT));
    }


    @Override
    public void createManager(RegistrationBean registrationBean) {
        USER_REPOSITORY.createUser(getEntity(registrationBean,Role.MANAGER));
    }

    @Override
    public List<UserDTO> getUserDTO() {
        return USER_REPOSITORY.getUserDTO();
    }

    @Override
    public User findUser(AuthBean authBean) {
        return USER_REPOSITORY.getUser(getEntity(authBean));
    }

    @Override
    public User findUser(RegistrationBean registrationBean) {
        return USER_REPOSITORY.getUser(getEntity(registrationBean));
    }

    @Override
    public User findUser(User user) {
        return USER_REPOSITORY.getUser(user);
    }


    private User getEntity(RegistrationBean registrationBean) {
        User user = new User();
        user.setLogin(registrationBean.getLogin());
        user.setFirstName(registrationBean.getFirstName());
        user.setLastName(registrationBean.getLastName());
        user.setAge(registrationBean.getAge());
        return user;
    }

    private User getEntity(UpdateStatusBean updateStatusBean) {
        User user = new User();
        user.setId(updateStatusBean.getId());
        user.setIdStatus(updateStatusBean.getStatus().getNumber());
        return user;
    }

    private User getEntity(SettingBean settingBean) {
        User user = new User();
        user.setId(settingBean.getId());
        user.setAge(settingBean.getAge());
        user.setFirstName(settingBean.getFirstName());
        user.setLastName(settingBean.getLastName());
        return user;
    }

    private User getEntity(RegistrationBean registrationBean, Role role) {
        User user = new User();
        user.setLogin(registrationBean.getLogin());
        user.setPassword(registrationBean.getPassword());
        user.setFirstName(registrationBean.getFirstName());
        user.setLastName(registrationBean.getLastName());
        user.setAge(registrationBean.getAge());
        user.setIdStatus(UserStatus.UNBUNNED.getNumber());
        user.setIdRole(role.getNumber());
        return user;
    }

    private User getEntity(AuthBean authBean) {
        User user = new User();
        user.setLogin(authBean.getLogin());
        return user;
    }
}
