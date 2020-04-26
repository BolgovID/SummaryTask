package ua.nure.bolhov.SummaryTask4.db.repository;

import ua.nure.bolhov.SummaryTask4.db.dto.UserDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.User;

import java.util.List;

public interface UserRepository {

    void updateUserStatus(User user);

    void updateUser(User user);

    void createUser(User user);

    List<UserDTO> getUserDTO();

    User getUser(User user);

}
