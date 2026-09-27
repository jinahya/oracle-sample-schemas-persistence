package com.github.jinahya.oracle.sample.schemas.persistence.hr;

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
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * An entity class for mapping the {@value EmpDetailsView#TABLE_NAME} view.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = EmpDetailsView.TABLE_NAME)
public class EmpDetailsView {

    /**
     * The name of the database view to which this entity is mapped. The value is {@value}.
     */
    public static final String TABLE_NAME = "EMP_DETAILS_VIEW";

    // ----------------------------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * The name of the table column to which the {@code employeeId} attribute maps.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = Employee.COLUMN_NAME_EMPLOYEE_ID;

    /**
     * The precision of the {@code EMPLOYEE_ID} column.
     */
    public static final int COLUMN_PRECISION_EMPLOYEE_ID = Employee.COLUMN_PRECISION_EMPLOYEE_ID;

    /**
     * The name of the attribute which maps the {@code EMPLOYEE_ID} column.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = Employee.ATTRIBUTE_NAME_EMPLOYEE_ID;

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_JOB_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_ID = "JOB_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_ID = "jobId";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_MANAGER_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_MANAGER_ID = "MANAGER_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER_ID = "managerId";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LOCATION_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_LAST_NAME} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SALARY} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALARY = "SALARY";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALARY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALARY = "salary";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMMISSION_PCT = "COMMISSION_PCT";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMISSION_PCT = "commissionPct";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT_NAME = "DEPARTMENT_NAME";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT_NAME = "departmentName";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_TITLE = "JOB_TITLE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_TITLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_TITLE = "jobTitle";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CITY} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY = "CITY";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE = "STATE_PROVINCE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE = "stateProvince";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_REGION_NAME = "REGION_NAME";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION_NAME = "regionName";
    /**
     * The length of the {@value #COLUMN_NAME_JOB_ID} column, which the view projects from
     * {@link Job}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_ID = Job.COLUMN_LENGTH_JOB_ID;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_JOB_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_JOB_ID = COLUMN_LENGTH_JOB_ID;

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_ID} column, which the view projects from
     * {@link Country}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_ID = Country.COLUMN_LENGTH_COUNTRY_ID;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_ID = COLUMN_LENGTH_COUNTRY_ID;

    /**
     * The length of the {@value #COLUMN_NAME_FIRST_NAME} column, which the view projects from
     * {@link Employee}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FIRST_NAME = Employee.COLUMN_LENGTH_FIRST_NAME;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_FIRST_NAME = COLUMN_LENGTH_FIRST_NAME;

    /**
     * The length of the {@value #COLUMN_NAME_LAST_NAME} column, which the view projects from
     * {@link Employee}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LAST_NAME = Employee.COLUMN_LENGTH_LAST_NAME;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_LAST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_LAST_NAME = COLUMN_LENGTH_LAST_NAME;

    /**
     * The length of the {@value #COLUMN_NAME_DEPARTMENT_NAME} column, which the view projects from
     * {@link Department}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DEPARTMENT_NAME = Department.COLUMN_LENGTH_DEPARTMENT_NAME;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_DEPARTMENT_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_DEPARTMENT_NAME = COLUMN_LENGTH_DEPARTMENT_NAME;

    /**
     * The length of the {@value #COLUMN_NAME_JOB_TITLE} column, which the view projects from
     * {@link Job}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_TITLE = Job.COLUMN_LENGTH_JOB_TITLE;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_JOB_TITLE = COLUMN_LENGTH_JOB_TITLE;

    /**
     * The length of the {@value #COLUMN_NAME_CITY} column, which the view projects from
     * {@link Location}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY = Location.COLUMN_LENGTH_CITY;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CITY} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CITY = COLUMN_LENGTH_CITY;

    /**
     * The length of the {@value #COLUMN_NAME_STATE_PROVINCE} column, which the view projects from
     * {@link Location}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE_PROVINCE = Location.COLUMN_LENGTH_STATE_PROVINCE;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_STATE_PROVINCE = COLUMN_LENGTH_STATE_PROVINCE;

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_NAME} column, which the view projects from
     * {@link Country}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_NAME = Country.COLUMN_LENGTH_COUNTRY_NAME;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_NAME = COLUMN_LENGTH_COUNTRY_NAME;

    /**
     * The length of the {@value #COLUMN_NAME_REGION_NAME} column, which the view projects from
     * {@link Region}. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REGION_NAME = Region.COLUMN_LENGTH_REGION_NAME;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_REGION_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_REGION_NAME = COLUMN_LENGTH_REGION_NAME;

    /**
     * The precision of the {@value #COLUMN_NAME_SALARY} column, which the view projects from {@link Employee}. The
     * value is {@value}.
     */
    public static final int COLUMN_PRECISION_SALARY = Employee.COLUMN_PRECISION_SALARY;

    /**
     * The scale of the {@value #COLUMN_NAME_SALARY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SALARY = Employee.COLUMN_SCALE_SALARY;

    /**
     * The precision of the {@value #COLUMN_NAME_COMMISSION_PCT} column, which the view projects from
     * {@link Employee}. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_COMMISSION_PCT = Employee.COLUMN_PRECISION_COMMISSION_PCT;

    /**
     * The scale of the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_COMMISSION_PCT = Employee.COLUMN_SCALE_COMMISSION_PCT;
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
               ",jobId=" + jobId +
               ",firstName=" + firstName +
               ",lastName=" + lastName +
               ",departmentName=" + departmentName +
               ",jobTitle=" + jobTitle +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by {@value #COLUMN_NAME_EMPLOYEE_ID}, which the view carries one row per.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof EmpDetailsView that)) {
            return false;
        }
        return Objects.equals(employeeId, that.employeeId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over {@value #COLUMN_NAME_EMPLOYEE_ID}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(employeeId);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Id
    @Nonnull
    @Basic(optional = false)
    // insertable=true is the JPA default; it is spelled out because EclipseLink rejects insertable=false on an @Id --
    // "There should be one non-read-only mapping defined for the primary key field", EclipseLink-46. Nothing writes to
    // a view anyway; every other column here stays insertable=false. Verified on 5.0.1.
    @Column(name = COLUMN_NAME_EMPLOYEE_ID, nullable = false, insertable = true, updatable = false)
    private Integer employeeId;

    @Nonnull
    @Basic(optional = false)
    @Size(max = SIZE_MAX_JOB_ID)
    @Column(name = COLUMN_NAME_JOB_ID, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_JOB_ID)
    private String jobId;

    @jakarta.annotation.Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MANAGER_ID, nullable = true, insertable = false, updatable = false)
    private Integer managerId;

    @jakarta.annotation.Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID, nullable = true, insertable = false, updatable = false)
    private Short departmentId;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = true, insertable = false, updatable = false)
    private Short locationId;

    @Basic(optional = true)
    @Size(max = SIZE_MAX_COUNTRY_ID)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_COUNTRY_ID)
    private String countryId;

    @Size(max = SIZE_MAX_FIRST_NAME)
    @Column(name = COLUMN_NAME_FIRST_NAME, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_FIRST_NAME)
    private String firstName;

    @Basic(optional = false)
    @Size(max = SIZE_MAX_LAST_NAME)
    @Column(name = COLUMN_NAME_LAST_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_LAST_NAME)
    private String lastName;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALARY, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_SALARY, scale = COLUMN_SCALE_SALARY)
    private BigDecimal salary;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMMISSION_PCT, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_COMMISSION_PCT, scale = COLUMN_SCALE_COMMISSION_PCT)
    private BigDecimal commissionPct;

    @Size(max = SIZE_MAX_DEPARTMENT_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DEPARTMENT_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_DEPARTMENT_NAME)
    private String departmentName;

    @Size(max = SIZE_MAX_JOB_TITLE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_TITLE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_JOB_TITLE)
    private String jobTitle;

    @Size(max = SIZE_MAX_CITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CITY, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_CITY)
    private String city;

    @Size(max = SIZE_MAX_STATE_PROVINCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STATE_PROVINCE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_STATE_PROVINCE)
    private String stateProvince;

    @Size(max = SIZE_MAX_COUNTRY_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY_NAME, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_COUNTRY_NAME)
    private String countryName;

    @Size(max = SIZE_MAX_REGION_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REGION_NAME, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_REGION_NAME)
    private String regionName;
}
