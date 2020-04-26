package ua.nure.bolhov.SummaryTask4.db.repository.impl;

import ua.nure.bolhov.SummaryTask4.db.DBManager;
import ua.nure.bolhov.SummaryTask4.db.dao.BrandDAORepository;
import ua.nure.bolhov.SummaryTask4.db.entity.Brand;
import ua.nure.bolhov.SummaryTask4.db.repository.BrandRepository;
import ua.nure.bolhov.SummaryTask4.exception.DBException;

import java.util.List;

public class BrandRepositoryImpl implements BrandRepository {

    private final DBManager DB_MANAGER;
    private final BrandDAORepository BRAND_DAO_REPOSITORY;

    public BrandRepositoryImpl(DBManager dbManager, BrandDAORepository brandDAORepository) {
        DB_MANAGER = dbManager;
        BRAND_DAO_REPOSITORY = brandDAORepository;
    }


    @Override
    public List<Brand> getBrandList() {
        return DB_MANAGER.doTransaction(() -> {
            try {
                return BRAND_DAO_REPOSITORY.getBrandList();
            } catch (DBException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
