package ua.nure.bolhov.SummaryTask4.db.repository.impl;

import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dao.CategoryDAORepository;
import ua.nure.bolhov.SummaryTask4.db.entity.Category;
import ua.nure.bolhov.SummaryTask4.db.repository.CategoryRepository;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final DBManager DB_MANAGER;
    private final CategoryDAORepository CATEGORY_DAO_REPOSITORY;

    public CategoryRepositoryImpl(DBManager DB_MANAGER, CategoryDAORepository CATEGORY_DAO_REPOSITORY) {
        this.DB_MANAGER = DB_MANAGER;
        this.CATEGORY_DAO_REPOSITORY = CATEGORY_DAO_REPOSITORY;
    }

    @Override
    public List<Category> getCategoryList() {
        return DB_MANAGER.doTransaction(() -> {
            try {
                return CATEGORY_DAO_REPOSITORY.getCategoryList();
            } catch (DBException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
