package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Table(name = EmpDetailsView.VIEW_NAME)
public class EmpDetailsView extends __MappedEntity<Integer> {

    @Serial
    private static final long serialVersionUID = 6927019108269489324L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String VIEW_NAME = "EMP_DETAILS_VIEW";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID
    public static final String COLUMN_NAME_EMPLOYEE_ID = Employee.COLUMN_NAME_EMPLOYEE_ID;

    // ---------------------------------------------------------------------------------------------------------- JOB_ID
    public static final String COLUMN_NAME_JOB_ID = MappedJob.COLUMN_NAME_JOB_ID;

    // ------------------------------------------------------------------------------------------------------ MANAGER_ID
    public static final String COLUMN_NAME_MANAGER_ID = Employee.COLUMN_NAME_MANAGER_ID;

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID
    public static final String COLUMN_NAME_DEPARTMENT_ID = Employee.COLUMN_NAME_DEPARTMENT_ID;

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID
    public static final String COLUMN_NAME_LOCATION_ID = Department.COLUMN_NAME_LOCATION_ID;

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID
    public static final String COLUMN_NAME_COUNTRY_ID = MappedLocation.COLUMN_NAME_COUNTRY_ID;

    // ------------------------------------------------------------------------------------------------------ FIRST_NAME

    // ------------------------------------------------------------------------------------------------------- LAST_NAME

    // ---------------------------------------------------------------------------------------------------------- SALARY

    // -------------------------------------------------------------------------------------------------- COMMISSION_PCT

    // ------------------------------------------------------------------------------------------------- DEPARTMENT_NAME

    // ------------------------------------------------------------------------------------------------------- JOB_TITLE

    // ------------------------------------------------------------------------------------------------------------ CITY

    // -------------------------------------------------------------------------------------------------- STATE_PROVINCE

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME

    // ----------------------------------------------------------------------------------------------------- REGION_NAME

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected EmpDetailsView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "employeeId=" + employeeId +
//                ",employee=" + employee +
                ",jobId=" + jobId +
//                ",job=" + job +
                ",managerId=" + managerId +
//                ",manager=" + manager +
                ",departmentId=" + departmentId +
//                ",department=" + department +
                ",locationId=" + locationId +
//                ",location=" + location +
                ",countryId=" + countryId +
//                ",country=" + country +
                ",firstName=" + firstName +
                ",lastName=" + lastName +
                ",salary=" + salary +
                ",commissionPct=" + commissionPct +
                ",departmentName=" + departmentName +
                ",jobTitle=" + jobTitle +
                ",city=" + city +
                ",stateProvince=" + stateProvince +
                ",countryName=" + countryName +
                ",regionName=" + regionName +
                '}';
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    // --------------------------------------------------------------------------------------------------------- employee
    @Nonnull
    public Employee getEmployee() {
        return employee;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    // ------------------------------------------------------------------------------------------------------------- job
    @Nonnull
    public MappedJob getJob() {
        return job;
    }

    // ------------------------------------------------------------------------------------------------------- managerId
    @Nullable
    public Integer getManagerId() {
        return managerId;
    }

    // --------------------------------------------------------------------------------------------------------- manager
    @Nonnull
    public Employee getManager() {
        return manager;
    }

    // ---------------------------------------------------------------------------------------------------- departmentId
    @Nullable
    public Short getDepartmentId() {
        return departmentId;
    }

    // ------------------------------------------------------------------------------------------------------ department
    @Nullable
    public Department getDepartment() {
        return department;
    }

    // ------------------------------------------------------------------------------------------------------ locationId
    @Nullable
    public Integer getLocationId() {
        return locationId;
    }

    // -------------------------------------------------------------------------------------------------------- location
    @Nullable
    public MappedLocation getLocation() {
        return location;
    }

    // ------------------------------------------------------------------------------------------------------- countryId
    @Nullable
    public String getCountryId() {
        return countryId;
    }

    // --------------------------------------------------------------------------------------------------------- country
    @Nullable
    public MappedCountry getCountry() {
        return country;
    }

    // ------------------------------------------------------------------------------------------------------- firstName
    @Nullable
    public String getFirstName() {
        return firstName;
    }

    // -------------------------------------------------------------------------------------------------------- lastName
    @Nonnull
    public String getLastName() {
        return lastName;
    }

    // ---------------------------------------------------------------------------------------------------------- salary
    @Nullable
    public BigDecimal getSalary() {
        return salary;
    }

    // --------------------------------------------------------------------------------------------------- commissionPct
    @Nullable
    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    // -------------------------------------------------------------------------------------------------- departmentName
    @Nonnull
    public String getDepartmentName() {
        return departmentName;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle
    @Nonnull
    public String getJobTitle() {
        return jobTitle;
    }

    // ------------------------------------------------------------------------------------------------------------ city
    @Nonnull
    public String getCity() {
        return city;
    }

    // --------------------------------------------------------------------------------------------------- stateProvince
    @Nullable
    public String getStateProvince() {
        return stateProvince;
    }

    // ----------------------------------------------------------------------------------------------------- countryName
    @Nullable
    public String getCountryName() {
        return countryName;
    }

    // ------------------------------------------------------------------------------------------------------ regionName
    @Nullable
    public String getRegionName() {
        return regionName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_EMPLOYEE_ID, nullable = false, insertable = false, updatable = false)
    private Integer employeeId;

    @Nonnull
    @Valid
    @NotNull
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_EMPLOYEE_ID, referencedColumnName = Employee.COLUMN_NAME_EMPLOYEE_ID,
                nullable = false, insertable = false, updatable = false)
    private Employee employee;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_ID, nullable = false, insertable = false, updatable = false)
    private String jobId;

    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_JOB_ID, referencedColumnName = MappedJob.COLUMN_NAME_JOB_ID, nullable = false,
                insertable = false, updatable = false)
    private MappedJob job;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MANAGER_ID, nullable = true, insertable = false, updatable = false)
    private Integer managerId;

    @Nonnull
    @Valid
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_MANAGER_ID, nullable = true, insertable = false, updatable = false)
    private Employee manager;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID, nullable = true, insertable = false, updatable = false)
    private Short departmentId;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID, nullable = true, insertable = false, updatable = false)
    private Department department;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = true, insertable = false, updatable = false)
    private Integer locationId;

    @Nullable
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID, nullable = true, insertable = false, updatable = false)
    private MappedLocation location;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = true, insertable = false, updatable = false)
    private String countryId;

    @Nullable
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COUNTRY_ID, nullable = true, insertable = false, updatable = false)
    private MappedCountry country;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Basic(optional = true)
    @Column(name = Employee.COLUMN_NAME_FIRST_NAME, nullable = true, insertable = false, updatable = false)
    private String firstName;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = Employee.COLUMN_NAME_LAST_NAME, nullable = false, insertable = false, updatable = false)
    private String lastName;

    @Nullable
    @Basic(optional = true)
    @Column(name = Employee.COLUMN_NAME_SALARY, nullable = true, insertable = false, updatable = false)
    private BigDecimal salary;

    @Nullable
    @Basic(optional = true)
    @Column(name = Employee.COLUMN_NAME_COMMISSION_PCT, nullable = true, insertable = false, updatable = false)
    private BigDecimal commissionPct;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = Department.COLUMN_NAME_DEPARTMENT_NAME, nullable = false, insertable = false, updatable = false)
    private String departmentName;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = MappedJob.COLUMN_NAME_JOB_TITLE, nullable = false, insertable = false, updatable = false)
    private String jobTitle;

    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = MappedLocation.COLUMN_NAME_CITY, nullable = false, insertable = false, updatable = false)
    private String city;

    @Nullable
    @Basic(optional = true)
    @Column(name = MappedLocation.COLUMN_NAME_STATE_PROVINCE, nullable = true, insertable = false, updatable = false)
    private String stateProvince;

    @Nullable
    @Basic(optional = true)
    @Column(name = MappedCountry.COLUMN_NAME_COUNTRY_NAME, nullable = true, insertable = false, updatable = false)
    private String countryName;

    @Nullable
    @Basic(optional = true)
    @Column(name = MappedRegion.COLUMN_NAME_REGION_NAME, nullable = true, insertable = false, updatable = false)
    private String regionName;
}
