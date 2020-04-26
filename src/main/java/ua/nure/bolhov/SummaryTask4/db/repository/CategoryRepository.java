package ua.nure.bolhov.SummaryTask4.db.repository;

import ua.nure.bolhov.SummaryTask4.db.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getCategoryList();
}
