package org.manikanta.repository;

import org.manikanta.model.Employee;
import org.springframework.data.repository.CrudRepository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeRepository implements CrudRepository<Employee, Integer> {

    private final DataSource dataSource;

    public EmployeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <S extends Employee> S save(S entity) {
        String sql = "INSERT INTO employee(id, name, salary) VALUES(?, ?, ?)";

        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setDouble(3, entity.getSalary());

            int resultCount = preparedStatement.executeUpdate();

            System.out.println(resultCount + "inserted employee");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public <S extends Employee> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        String sql = "select id,name,salary from employee where id=?";

        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement selectStatement = conn.prepareStatement(sql)) {

            selectStatement.setInt(1,id);


            ResultSet resultSet =  selectStatement.executeQuery();
            while (resultSet.next()) {
               int Empid = resultSet.getInt(1);
               String name = resultSet.getString(2);
                Double salary = resultSet.getDouble(3);
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setSalary(resultSet.getDouble(3));

                System.out.println(Optional.of(employee));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Employee> findAll() {
        return null;
    }

    @Override
    public Iterable<Employee> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM employee where id = ?";
        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement deleteStatement = conn.prepareStatement(sql)) {
            deleteStatement.setInt(1,id);
            int del =deleteStatement.executeUpdate();
            System.out.println(del + " deleted record");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Employee> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
