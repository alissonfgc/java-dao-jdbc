package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, department.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    department.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department department) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, department.getName());
            st.setInt(2, department.getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected < 0) {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected < 0) {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT department.* FROM department WHERE department.Id = ?");
            st.setInt(1, id);


            rs = st.executeQuery();

            if (rs.next()) {
                Department department = instanciateDepartment(rs);
                return department;
            } else {
                throw new DbException("Unexpected error! No rows corresponding!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT department.* FROM department ORDER BY Name");
            rs = st.executeQuery();

            List<Department> departments = new ArrayList<>();

            while (rs.next()) {
                departments.add(instanciateDepartment(rs));
            }

            return departments;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department(rs.getInt("Id"), rs.getString("Name"));
        return department;
    }
}
