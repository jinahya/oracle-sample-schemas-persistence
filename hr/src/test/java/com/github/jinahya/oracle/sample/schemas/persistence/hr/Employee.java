package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJob;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * .
 *
 * @author Jaehan Lim
 */
// https://github.com/eclipse-ee4j/eclipselink/issues/778
@NamedQuery(
        name = "Employee.selectManagersAndNumberOfSubordinates__OrderByNumberOfSubordinatesDesc",
        query = """
                SELECT e, SIZE(e.subordinates) AS numberOfSubordinates
                FROM Employee AS e
                WHERE SIZE(e.subordinates) > 0
                ORDER BY numberOfSubordinates DESC"""
)
@NamedQuery(
        name = "Employee.selectManagersAndNumberOfSubordinates__OrderByNumberOfSubordinatesAsc",
        query = """
                SELECT e, SIZE(e.subordinates) AS numberOfSubordinates
                FROM Employee AS e
                WHERE SIZE(e.subordinates) > 0
                ORDER BY SIZE(e.subordinates) ASC"""
)
@NamedQuery(
        name = "Employee.selectList_WhereJobEqualTo",
        query = """
                SELECT e
                FROM Employee e
                WHERE e.job = :job"""
)
@NamedQuery(
        name = "Employee.selectList_WhereJobIdEqualTo",
        query = """
                SELECT e 
                FROM Employee e
                WHERE e.jobId = :jobId"""
)
@Entity
@Table(name = MappedEmployee.TABLE_NAME)
class Employee extends MappedEmployee {

    // -------------------------------------------------------------------------------------------------------- BUILDERS
    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR

    /**
     * Creates a new instance.
     */
    protected Employee() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder from which a new instance is built.
     */
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
//    @jakarta.validation.constraints.AssertTrue
    @Override
    protected boolean isCommissionPctNonNegative() {
        return super.isCommissionPctNonNegative();
    }

    //    @jakarta.validation.constraints.AssertTrue
    @Override
    protected boolean isSalaryGreaterThanOrEqualToJobMinSalary() {
        return super.isSalaryGreaterThanOrEqualToJobMinSalary();
    }

    // ----------------------------------------------------------------------------------------------------- super.jobId

    // ---------------------------------------------------------------------------------------------- super.jobMinSalary
    @Nullable
    @Override
    protected BigDecimal getJobMinSalary() {
//        return super.getJobMinSalary();
        return Optional.ofNullable(job) // accessing the LAZY-fetching attribute !!!!
                .map(MappedJob::getMinSalary)
                .map(BigDecimal::valueOf)
                .orElse(null);
    }

    // ---------------------------------------------------------------------------------------------- super.jobMaxSalary
    @Nullable
    @Override
    protected BigDecimal getJobMaxSalary() {
//        return super.getJobMaxSalary();
        return Optional.ofNullable(job) // accessing the LAZY-fetching attribute !!!!
                .map(MappedJob::getMaxSalary)
                .map(BigDecimal::valueOf)
                .orElse(null);
    }

    // ------------------------------------------------------------------------------------------------- super.managerId

    // ---------------------------------------------------------------------------------------------- super.departmentId

    // ------------------------------------------------------------------------------------------------------------- job
    @Nonnull
    public Job getJob() {
        return job;
    }

    public void setJob(@Nonnull final Job job) {
        this.job = job;
        setJobId(
                Optional.ofNullable(this.job)
                        .map(MappedJob::getJobId)
                        .orElse(null)
        );
        // TODO: adjust current salary to between job.minSalary and job.maxSalary
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

    /**
     * Returns <em>subordinates</em> of this <em>manager</em>.
     *
     * @return <em>subordinates</em> of this <em>manager</em>
     */
    List<Employee> getSubordinates() {
        return subordinates;
    }

    /**
     * Replaces current <em>subordinates</em> of this <em>manager</em> with specified value.
     *
     * @param subordinates new <em>subordinates</em> of this <em>manager</em>.
     */
    void setSubordinates(final List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false,
               fetch = FetchType.LAZY,
               cascade = {
               }
    )
    @JoinColumn(name = COLUMN_NAME_JOB_ID,
                referencedColumnName = MappedJob.COLUMN_NAME_JOB_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Job job;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true,
               fetch = FetchType.LAZY,
               cascade = {
               }
    )
    @JoinColumn(name = COLUMN_NAME_MANAGER_ID,
                referencedColumnName = COLUMN_NAME_EMPLOYEE_ID,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Employee manager;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true,
               fetch = FetchType.LAZY,
               cascade = {
               }
    )
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID,
                referencedColumnName = MappedDepartment.COLUMN_NAME_DEPARTMENT_ID,
                nullable = true,
                insertable = false,
                updatable = false
    )
    private Department department;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = MappedEmployee.ATTRIBUTE_NAME_MANAGER,
               fetch = FetchType.LAZY,
               cascade = {
               },
               orphanRemoval = false
    )
    private List<@Valid @NotNull Employee> subordinates;
}
