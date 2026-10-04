package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedEmpDetailsView#TABLE_NAME} view.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedEmpDetailsView {

    /**
     * The name of the database view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "EMP_DETAILS_VIEW";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EMPLOYEE_ID";

    /**
     * The precision of the {@value #COLUMN_NAME_EMPLOYEE_ID} column, which the view projects from the {@code EMPLOYEES}
     * table. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_EMPLOYEE_ID = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    // ---------------------------------------------------------------------------------------------------------- JOB_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_JOB_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_ID} column, which the view projects from the {@code JOBS} table. The
     * value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_ID = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_JOB_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    // ------------------------------------------------------------------------------------------------------ MANAGER_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MANAGER_ID = "MANAGER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER_ID = "managerId";

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LOCATION_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_ID} column, which the view projects from the {@code COUNTRIES}
     * table. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_ID = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    // ------------------------------------------------------------------------------------------------------ FIRST_NAME

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_FIRST_NAME} column, which the view projects from the {@code EMPLOYEES}
     * table. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FIRST_NAME = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_FIRST_NAME = COLUMN_LENGTH_FIRST_NAME;

    // ------------------------------------------------------------------------------------------------------- LAST_NAME

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_LAST_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_LAST_NAME} column, which the view projects from the {@code EMPLOYEES}
     * table. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LAST_NAME = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_LAST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_LAST_NAME = COLUMN_LENGTH_LAST_NAME;

    // ---------------------------------------------------------------------------------------------------------- SALARY

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_SALARY} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALARY = "SALARY";

    /**
     * The precision of the {@value #COLUMN_NAME_SALARY} column, which the view projects from the {@code EMPLOYEES}
     * table. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SALARY = 8;

    /**
     * The scale of the {@value #COLUMN_NAME_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SALARY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALARY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALARY = "salary";

    // -------------------------------------------------------------------------------------------------- COMMISSION_PCT

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COMMISSION_PCT = "COMMISSION_PCT";

    /**
     * The precision of the {@value #COLUMN_NAME_COMMISSION_PCT} column, which the view projects from the
     * {@code EMPLOYEES} table. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_COMMISSION_PCT = 2;

    /**
     * The scale of the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_COMMISSION_PCT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMISSION_PCT = "commissionPct";

    // ------------------------------------------------------------------------------------------------- DEPARTMENT_NAME

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT_NAME = "DEPARTMENT_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_DEPARTMENT_NAME} column, which the view projects from the
     * {@code DEPARTMENTS} table. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DEPARTMENT_NAME = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT_NAME = "departmentName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_DEPARTMENT_NAME = COLUMN_LENGTH_DEPARTMENT_NAME;

    // ------------------------------------------------------------------------------------------------------- JOB_TITLE

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_JOB_TITLE = "JOB_TITLE";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_TITLE} column, which the view projects from the {@code JOBS} table.
     * The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_TITLE = 35;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_TITLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_TITLE = "jobTitle";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_JOB_TITLE = COLUMN_LENGTH_JOB_TITLE;

    // ------------------------------------------------------------------------------------------------------------ CITY

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_CITY} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY = "CITY";

    /**
     * The length of the {@value #COLUMN_NAME_CITY} column, which the view projects from the {@code LOCATIONS} table.
     * The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CITY} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CITY = COLUMN_LENGTH_CITY;

    // -------------------------------------------------------------------------------------------------- STATE_PROVINCE

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE = "STATE_PROVINCE";

    /**
     * The length of the {@value #COLUMN_NAME_STATE_PROVINCE} column, which the view projects from the {@code LOCATIONS}
     * table. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE_PROVINCE = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE = "stateProvince";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_STATE_PROVINCE = COLUMN_LENGTH_STATE_PROVINCE;

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_NAME} column, which the view projects from the {@code COUNTRIES}
     * table. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_NAME = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_NAME = COLUMN_LENGTH_COUNTRY_NAME;

    // ----------------------------------------------------------------------------------------------------- REGION_NAME

    /**
     * The name of the view column to which the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_REGION_NAME = "REGION_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_REGION_NAME} column, which the view projects from the {@code REGIONS}
     * table. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REGION_NAME = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_NAME = "regionName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_REGION_NAME = COLUMN_LENGTH_REGION_NAME;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedEmpDetailsView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "employeeId=" + employeeId +
               ",jobId=" + jobId +
               ",managerId=" + managerId +
               ",departmentId=" + departmentId +
               ",locationId=" + locationId +
               ",countryId=" + countryId +
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

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedEmpDetailsView that)) {
            return false;
        }
        return Objects.equals(getEmployeeId(), that.getEmployeeId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getEmployeeId());
    }

    // ------------------------------------------------------------------------------------------------------ employeeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute with the specified value.
     *
     * @param employeeId new value for {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    protected void setEmployeeId(@Nonnull final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ----------------------------------------------------------------------------------------------------------- jobId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
     */
    @Nonnull
    public String getJobId() {
        return jobId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_ID} attribute with the specified value.
     *
     * @param jobId new value for {@value #ATTRIBUTE_NAME_JOB_ID} attribute.
     */
    public void setJobId(@Nonnull final String jobId) {
        this.jobId = jobId;
    }

    // ------------------------------------------------------------------------------------------------------- managerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute.
     */
    @Nullable
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute with the specified value.
     *
     * @param managerId new value for {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute.
     */
    public void setManagerId(@Nullable final Integer managerId) {
        this.managerId = managerId;
    }

    // ---------------------------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     */
    @Nullable
    public Short getDepartmentId() {
        return departmentId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute with the specified value.
     *
     * @param departmentId new value for {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     */
    public void setDepartmentId(@Nullable final Short departmentId) {
        this.departmentId = departmentId;
    }

    // ------------------------------------------------------------------------------------------------------ locationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    @Nullable
    public Short getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute with the specified value.
     *
     * @param locationId new value for {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    public void setLocationId(@Nullable final Short locationId) {
        this.locationId = locationId;
    }

    // ------------------------------------------------------------------------------------------------------- countryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    @Nullable
    public String getCountryId() {
        return countryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute with the specified value.
     *
     * @param countryId new value for {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    public void setCountryId(@Nullable final String countryId) {
        this.countryId = countryId;
    }

    // ------------------------------------------------------------------------------------------------------- firstName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    @Nullable
    public String getFirstName() {
        return firstName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute with the specified value.
     *
     * @param firstName new value for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    public void setFirstName(@Nullable final String firstName) {
        this.firstName = firstName;
    }

    // -------------------------------------------------------------------------------------------------------- lastName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    @Nonnull
    public String getLastName() {
        return lastName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute with the specified value.
     *
     * @param lastName new value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    public void setLastName(@Nonnull final String lastName) {
        this.lastName = lastName;
    }

    // ---------------------------------------------------------------------------------------------------------- salary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALARY} attribute.
     */
    @Nullable
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALARY} attribute with the specified value.
     *
     * @param salary new value for {@value #ATTRIBUTE_NAME_SALARY} attribute.
     */
    public void setSalary(@Nullable final BigDecimal salary) {
        this.salary = salary;
    }

    // --------------------------------------------------------------------------------------------------- commissionPct

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     */
    @Nullable
    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute with the specified value.
     *
     * @param commissionPct new value for {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     */
    public void setCommissionPct(@Nullable final BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    // -------------------------------------------------------------------------------------------------- departmentName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute.
     */
    @Nonnull
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute with the specified value.
     *
     * @param departmentName new value for {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute.
     */
    public void setDepartmentName(@Nonnull final String departmentName) {
        this.departmentName = departmentName;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    @Nonnull
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute with the specified value.
     *
     * @param jobTitle new value for {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    public void setJobTitle(@Nonnull final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // ------------------------------------------------------------------------------------------------------------ city

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    @Nonnull
    public String getCity() {
        return city;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CITY} attribute with the specified value.
     *
     * @param city new value for {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    public void setCity(@Nonnull final String city) {
        this.city = city;
    }

    // --------------------------------------------------------------------------------------------------- stateProvince

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute.
     */
    @Nullable
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute with the specified value.
     *
     * @param stateProvince new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute.
     */
    public void setStateProvince(@Nullable final String stateProvince) {
        this.stateProvince = stateProvince;
    }

    // ----------------------------------------------------------------------------------------------------- countryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    @Nullable
    public String getCountryName() {
        return countryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute with the specified value.
     *
     * @param countryName new value for {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    public void setCountryName(@Nullable final String countryName) {
        this.countryName = countryName;
    }

    // ------------------------------------------------------------------------------------------------------ regionName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION_NAME} attribute.
     */
    @Nullable
    public String getRegionName() {
        return regionName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION_NAME} attribute with the specified value.
     *
     * @param regionName new value for {@value #ATTRIBUTE_NAME_REGION_NAME} attribute.
     */
    public void setRegionName(@Nullable final String regionName) {
        this.regionName = regionName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Nonnull
    @NotNull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
    // insertable=false on an @Id -- "There should be one non-read-only mapping defined for the
    // primary key field", EclipseLink-46. Nothing writes to a view anyway.
    @Column(name = COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
            insertable = true,
            updatable = false,
            precision = COLUMN_PRECISION_EMPLOYEE_ID
    )
    private Integer employeeId;

    @Nonnull
    @Size(max = SIZE_MAX_JOB_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_ID,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_JOB_ID
    )
    private String jobId;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MANAGER_ID, nullable = true, insertable = false, updatable = false)
    private Integer managerId;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID, nullable = true, insertable = false, updatable = false)
    private Short departmentId;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = true, insertable = false, updatable = false)
    private Short locationId;

    @Nullable
    @Size(max = SIZE_MAX_COUNTRY_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY_ID,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_COUNTRY_ID
    )
    private String countryId;

    @Nullable
    @Size(max = SIZE_MAX_FIRST_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FIRST_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_FIRST_NAME
    )
    private String firstName;

    @Nonnull
    @Size(max = SIZE_MAX_LAST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_LAST_NAME
    )
    private String lastName;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALARY,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_SALARY,
            scale = COLUMN_SCALE_SALARY
    )
    private BigDecimal salary;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMMISSION_PCT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_COMMISSION_PCT,
            scale = COLUMN_SCALE_COMMISSION_PCT
    )
    private BigDecimal commissionPct;

    @Nonnull
    @Size(max = SIZE_MAX_DEPARTMENT_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DEPARTMENT_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DEPARTMENT_NAME
    )
    private String departmentName;

    @Nonnull
    @Size(max = SIZE_MAX_JOB_TITLE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_TITLE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_JOB_TITLE
    )
    private String jobTitle;

    @Nonnull
    @Size(max = SIZE_MAX_CITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CITY,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CITY
    )
    private String city;

    @Nullable
    @Size(max = SIZE_MAX_STATE_PROVINCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STATE_PROVINCE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_STATE_PROVINCE
    )
    private String stateProvince;

    @Nullable
    @Size(max = SIZE_MAX_COUNTRY_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_COUNTRY_NAME
    )
    private String countryName;

    @Nullable
    @Size(max = SIZE_MAX_REGION_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REGION_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_REGION_NAME
    )
    private String regionName;
}
