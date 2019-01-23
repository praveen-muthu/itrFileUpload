package com.perficient.finance.itr;

import java.util.Objects;

public class Employee {
    private String empId;
    private String empName;
    private String email;

    public Employee() {}

    public Employee(String empId, String empName, String email) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empName, employee.empName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empName);
    }

    @Override
    public String toString() {
        return "[\"" +
                 empId +
                "\", \"" + empName +
                "\", \"" + email +
                "\"]";
    }
}
