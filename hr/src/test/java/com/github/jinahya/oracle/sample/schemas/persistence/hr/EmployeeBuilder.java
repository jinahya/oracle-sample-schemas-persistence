package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployeeBuilder;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntityBuilder;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceIT;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

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

































