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
    @Column(name = "JOB_ID", nullable = false, insertable = false, updatable = false)
    private String jobId;

    @jakarta.annotation.Nullable
    @Basic(optional = true)
    @Column(name = "MANAGER_ID", nullable = true, insertable = false, updatable = false)
    private Integer managerId;

    @jakarta.annotation.Nullable
    @Basic(optional = true)
    @Column(name = "DEPARTMENT_ID", nullable = true, insertable = false, updatable = false)
    private Short departmentId;

    @Basic(optional = true)
    @Column(name = "LOCATION_ID", nullable = true, insertable = false, updatable = false)
    private Short locationId;

    @Basic(optional = true)
    @Column(name = "COUNTRY_ID", nullable = true, insertable = false, updatable = false)
    private String countryId;

    @Column(name = "FIRST_NAME", nullable = true, insertable = false, updatable = false)
    private String firstName;

    @Basic(optional = false)
    @Column(name = "LAST_NAME", nullable = false, insertable = false, updatable = false)
    private String lastName;

    @Basic(optional = true)
    @Column(name = "SALARY", nullable = true, insertable = false, updatable = false, precision = 8, scale = 2)
    private BigDecimal salary;

    @Basic(optional = true)
    @Column(name = "COMMISSION_PCT", nullable = true, insertable = false, updatable = false, precision = 2, scale = 2)
    private BigDecimal commissionPct;

    @Size(max = 30)
    @NotNull
    @Basic(optional = false)
    @Column(name = "DEPARTMENT_NAME", nullable = false, insertable = false, updatable = false, length = 30)
    private String departmentName;

    @Size(max = 35)
    @NotNull
    @Basic(optional = false)
    @Column(name = "JOB_TITLE", nullable = false, insertable = false, updatable = false, length = 35)
    private String jobTitle;

    @Size(max = 30)
    @NotNull
    @Basic(optional = false)
    @Column(name = "CITY", nullable = false, insertable = false, updatable = false, length = 30)
    private String city;

    @Size(max = 25)
    @Basic(optional = true)
    @Column(name = "STATE_PROVINCE", nullable = true, insertable = false, updatable = false, length = 25)
    private String stateProvince;

    @Size(max = 60)
    @Basic(optional = true)
    @Column(name = "COUNTRY_NAME", nullable = true, insertable = false, updatable = false, length = 60)
    private String countryName;

    @Size(max = 25)
    @Basic(optional = true)
    @Column(name = "REGION_NAME", nullable = true, insertable = false, updatable = false, length = 25)
    private String regionName;
}
