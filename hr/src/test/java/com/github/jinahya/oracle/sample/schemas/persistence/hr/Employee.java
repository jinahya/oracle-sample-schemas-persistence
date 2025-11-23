package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = MappedEmployee.TABLE_NAME)
class Employee extends MappedEmployee {

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR
    protected Employee() {
        super();
    }

    Employee(final EmployeeBuilder builder) {
        super(builder);
        if (getDepartmentId() == null) {
            if (builder.departmentId() != null) {
                setDepartmentId(builder.departmentId());
            } else {
                setDepartment(builder.department());
            }
        }
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public boolean equals(final Object obj) {
        return equalsWithEmployeeId(obj);
    }

    @Override
    public int hashCode() {
        return hashCodeWithEmployeeId();
    }

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence

    //    @AssertTrue
    @Override
    protected boolean isCommissionPctNonNegative() {
        return super.isCommissionPctNonNegative();
    }

    //@AssertTrue
    @Override
    protected boolean isSalaryGreaterThanOrEqualToJobMinSalary() {
        return super.isSalaryGreaterThanOrEqualToJobMinSalary();
    }

    private BigDecimal getJobMinSalary() {
        // return job.getMinSalary();
        return null;
    }

    @Nullable
    @Override
    public Integer getManagerId() {
        return super.getManagerId();
    }

    @Override
    public void setManagerId(@Nullable Integer managerId) {
        super.setManagerId(managerId);
    }

    // --------------------------------------------------------------------------------------------------------- manager

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     *
     * @return current value of the {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    @Nonnull
    public Employee getManager() {
        return manager;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute with specified value.
     *
     * @param manager new value for the {@value #ATTRIBUTE_NAME_MANAGER}.
     */
    public void setManager(@Nonnull final Employee manager) {
        this.manager = manager;
        setManagerId(
                Optional.ofNullable(this.manager)
                        .map(MappedEmployee::getEmployeeId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------ department
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(@Nonnull final Department department) {
        this.department = department;
        setDepartmentId(
                Optional.ofNullable(this.department)
                        .map(MappedDepartment::getDepartmentId)
                        .orElse(null)
        );
    }

    // ---------------------------------------------------------------------------------------------------- subordinates
    List<Employee> getSubordinates() {
        return subordinates;
    }

    void setSubordinates(final List<Employee> subordinates) {
        this.subordinates = subordinates;
        // SELECT e FROM Employee e ORDER BY COUNT(e.subordinates)
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = COLUMN_NAME_MANAGER_ID, referencedColumnName = COLUMN_NAME_EMPLOYEE_ID,
                nullable = true, insertable = false, updatable = false)
    private Employee manager;

    @Nullable
    @Valid
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID, referencedColumnName = MappedDepartment.COLUMN_NAME_DEPARTMENT_ID,
                nullable = true, insertable = false, updatable = false)
    private Department department;

    @OneToMany(mappedBy = MappedEmployee.ATTRIBUTE_NAME_MANAGER, fetch = FetchType.LAZY, cascade = {})
    private List<@Valid @NotNull Employee> subordinates;
}
