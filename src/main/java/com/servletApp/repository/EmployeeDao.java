package com.servletApp.repository;
import com.servletApp.entity.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.servletApp.service.ConnectionProvider;


public class EmployeeDao {
    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, country, hashed_password, role) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET name=?, email=?, country=?, hashed_password=?, role=? WHERE id=?";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id=?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String CHECK_EMAIL_SQL = "SELECT COUNT(*) FROM users WHERE email = ?";

    private final Connection dataSource;

    public EmployeeDao() {
        this.dataSource = ConnectionProvider.getCon();
    }

    public Optional<Employee> authenticate(String email, String password) {
        try (Connection connection = ConnectionProvider.getCon();
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPasswordFromDB = resultSet.getString("hashed_password");
                    if (password == hashedPasswordFromDB) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String country = resultSet.getString("country");
                        String role = resultSet.getString("role");

                        Employee employee = new Employee(id, name, email, country, password, role);
                        return Optional.of(employee);
                    }
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException("An error occurred while authenticating the user.", exception);
        }
        return Optional.empty();
    }

    public int saveUser(Employee employee) {
        int status = 0;
        try (Connection connection = ConnectionProvider.getCon();
             PreparedStatement ps = connection.prepareStatement(INSERT_USERS_SQL)) {
            ps.setString(1, employee.name());
            ps.setString(2, employee.email());
            ps.setString(3, employee.country());
            ps.setString(4, employee.hashedPassword());
            ps.setString(5, employee.role());

            status = ps.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("An error occurred while saving the user.");
            exception.printStackTrace();
        }
        return status;
    }

    public Optional<Employee> getEmployeeById(int id) {
        Optional<Employee> optionalEmployee = Optional.empty();
        try (Connection connection = ConnectionProvider.getCon();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String country = rs.getString("country");
                    String hashedPassword = rs.getString("hashed_password");
                    String role = rs.getString("role");

                    Employee employee = new Employee(id, name, email, country, hashedPassword, role);
                    optionalEmployee = Optional.of(employee);
                }
            }
        } catch (SQLException exception) {
            System.out.println("An error occurred while retrieving the user.");
            exception.printStackTrace();
        }
        return optionalEmployee;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> listEmployees = new ArrayList<>();
        try (Connection connection = ConnectionProvider.getCon();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String hashedPassword = rs.getString("hashed_password");
                String role = rs.getString("role");

                Employee employee = new Employee(id, name, email, country, hashedPassword, role);
                listEmployees.add(employee);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("An error occurred while retrieving the users.", exception);
        }
        return listEmployees;
    }


    public int updateUser(Employee employee) {
        int status = 0;
        try (Connection connection = ConnectionProvider.getCon();
             PreparedStatement ps = connection.prepareStatement(UPDATE_USERS_SQL)) {
            ps.setString(1, employee.name());
            ps.setString(2, employee.email());
            ps.setString(3, employee.country());
            ps.setString(4, employee.hashedPassword());
            ps.setString(5, employee.role());

            status = ps.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("An error occurred while updating the user.", exception);
        }
        return status;
    }

    public int deleteUser(int id) {
        int status = 0;
        try (Connection connection = ConnectionProvider.getCon();
             PreparedStatement ps = connection.prepareStatement(DELETE_USERS_SQL)) {
            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("An error occurred while deleting the user.", exception);
        }
        return status;
    }


    public boolean isEmailRegistered(String email) {
        try (Connection connection = ConnectionProvider.getCon();
             PreparedStatement ps = connection.prepareStatement(CHECK_EMAIL_SQL)) {
            ps.setString(1, email);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // If count > 0, the email is registered
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException("An error occurred while checking if the email is registered.", exception);
        }
        return false;
    }
}


