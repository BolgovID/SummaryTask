package ua.nure.bolhov.SummaryTask4.db.repository.impl;

import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dao.UserDAORepository;
import ua.nure.bolhov.SummaryTask4.db.dto.UserDTO;
import ua.nure.bolhov.SummaryTask4.db.entity.User;
import ua.nure.bolhov.SummaryTask4.db.repository.UserRepository;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final UserDAORepository USER_DAO_REPOSITORY;
    private final DBManager DB_MANAGER;

    public UserRepositoryImpl(UserDAORepository userDAORepository, DBManager dbManager) {
        USER_DAO_REPOSITORY = userDAORepository;
        DB_MANAGER = dbManager;
    }


    @Override
    public void updateUserStatus(User user) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return USER_DAO_REPOSITORY.updateUserStatus(user);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public void updateUser(User user) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return USER_DAO_REPOSITORY.updateUser(user);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public void createUser(User user) {
        DB_MANAGER.doTransaction(() -> {
            try {
                return USER_DAO_REPOSITORY.insertUser(user);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public List<UserDTO> getUserDTO() {
        return DB_MANAGER.doTransaction(() -> {
            try {
                return USER_DAO_REPOSITORY.getUserDTO();
            } catch (DBException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public User getUser(User user) {
        return DB_MANAGER.doTransaction(() -> {
            try {
                return USER_DAO_REPOSITORY.getUser(user);
            } catch (DBException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
