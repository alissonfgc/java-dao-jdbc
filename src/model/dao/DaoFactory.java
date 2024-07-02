package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

import java.sql.Connection;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
