package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployeeBuilder;

public class EmployeeBuilder extends MappedEmployeeBuilder<EmployeeBuilder, Employee> {

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR
    EmployeeBuilder() {
        super(Employee.class);
    }

    @Override
    public Integer departmentId() {
        return super.departmentId();
    }

    @Override
    public EmployeeBuilder departmentId(Integer departmentId) {
        return super.departmentId(departmentId);
    }

    public Department department() {
        return department;
    }

    public EmployeeBuilder department(Department department) {
        this.department = department;
        return this;
    }

    public EmployeeBuilder manager() {
        return manager;
    }

    public EmployeeBuilder manager(EmployeeBuilder manager) {
        this.manager = manager;
        return this;
    }

    private EmployeeBuilder manager;

    private Department department;
}
