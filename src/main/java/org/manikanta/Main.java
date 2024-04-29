package org.manikanta;

import org.manikanta.model.Employee;
import org.manikanta.repository.EmployeeRepository;
import org.postgresql.ds.PGSimpleDataSource;

public class Main {
    public static void main(String[] args) {

        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("gurukulams_db");
        ds.setUser("tom");
        ds.setPassword("password");


        EmployeeRepository repository = new EmployeeRepository(ds);
        Employee employee = new Employee();

        employee.setId(323443);
        employee.setName("Manikanta");
        employee.setSalary(10000000L);


        repository.save(employee);
        repository.findById(323443);
        repository.deleteById(323443);

    }
}