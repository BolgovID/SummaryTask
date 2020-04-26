package ua.nure.bolhov.SummaryTask4.web.bean;

import ua.nure.bolhov.SummaryTask4.db.entity.User;

public class AuthBean {
    private String login;
    private String password;
    private User user;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}
