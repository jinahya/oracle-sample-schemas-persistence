package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployeeBuilder;

class EmployeeBuilder extends MappedEmployeeBuilder<EmployeeBuilder, Employee> {

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR
    EmployeeBuilder() {
        super(Employee.class);
    }

    // ----------------------------------------------------------------------------------------------------- super.jobId
    @Override
    public String jobId() {
        return super.jobId();
    }

    // overridden to public
    @Override
    public EmployeeBuilder jobId(final String jobId) {
        return super.jobId(jobId);
    }

    // ------------------------------------------------------------------------------------------------- super.managerId
    @Override
    public Integer managerId() {
        return super.managerId();
    }

    // overridden to public
    @Override
    public EmployeeBuilder managerId(final Integer managerId) {
        return super.managerId(managerId);
    }

    // ---------------------------------------------------------------------------------------------- super.departmentId
    @Override
    public Integer departmentId() {
        return super.departmentId();
    }

    // overridden to public
    @Override
    public EmployeeBuilder departmentId(final Integer departmentId) {
        return super.departmentId(departmentId);
    }

    // ------------------------------------------------------------------------------------------------------------- job
    public Job job() {
        return job;
    }

    public EmployeeBuilder job(final Job job) {
        this.job = job;
        return this;
    }

    // ------------------------------------------------------------------------------------------------------ department
    public Department department() {
        return department;
    }

    public EmployeeBuilder department(final Department department) {
        this.department = department;
        return this;
    }

    // --------------------------------------------------------------------------------------------------------- manager
    public EmployeeBuilder manager() {
        return manager;
    }

    public EmployeeBuilder manager(final EmployeeBuilder manager) {
        this.manager = manager;
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Job job;

    private EmployeeBuilder manager;

    private Department department;
}
