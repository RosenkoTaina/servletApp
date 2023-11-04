package com.rosenko.demo.repository;

import com.rosenko.demo.entity.Employee;
import com.rosenko.demo.enums.DataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements Repository {
    private final DataSource dataSource;

    public EmployeeRepository() {
        try {
            this.dataSource = DataSourceFactory.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
@Override
    public int save(Employee employee) {
        int status = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO users(name,email,country) VALUES (?,?,?)")) {
            ps.setString(1, employee.name());
            ps.setString(2, employee.email());
            ps.setString(3, employee.country());

            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }


@Override
    public int update(Employee employee) {
        int status = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE users SET name=?,email=?,country=? WHERE id=?")) {

            ps.setString(1, employee.name());
            ps.setString(2, employee.email());
            ps.setString(3, employee.country());
            ps.setInt(4, employee.id());

            status = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
@Override
    public  int delete(int id) {
        int status = 0;
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

@Override
    public  Optional<Employee> getEmployeeById(int id) {
        Optional<Employee> optionalEmployee = Optional.empty();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                optionalEmployee = Optional.of(employee);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return optionalEmployee;
    }

@Override
    public  List<Employee> getAllEmployees() {
        List<Employee> listEmployees = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                listEmployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEmployees;
    }
}
