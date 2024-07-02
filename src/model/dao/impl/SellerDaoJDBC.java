package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller department) {

    }

    @Override
    public void update(Seller department) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = instanciateDepartment(rs);
                Seller seller = instanciateSeller(rs, dep);
                return seller;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller seller = new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate").toLocalDate(), rs.getDouble("BaseSalary"), dep);
        return seller;
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
