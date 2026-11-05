package com.github.jinahya.oracle.sample.persistence.hr;

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

import com.github.jinahya.oracle.sample.persistence.hr.mapped.JobHistoryId;
import com.github.jinahya.oracle.sample.persistence.hr.mapped.MappedJobHistory;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * An entity class for mapping the {@value MappedJobHistory#TABLE_NAME} table, whose composite
 * primary key is mapped with an {@link jakarta.persistence.IdClass @IdClass}.
 * <p>
 * The mapping is read-only, as {@link MappedJobHistory} describes; so are the associations this
 * class adds.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see JobHistoryId
 */
@Entity(name = JobHistory.ENTITY_NAME)
@IdClass(JobHistoryId.class)
@Table(name = MappedJobHistory.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               JobHistory.COLUMN_NAME_EMPLOYEE_ID,
                               JobHistory.COLUMN_NAME_START_DATE
                       }
               )
       },
       comment = "Table that stores job history of the employees. If an employee\n" +
               "changes departments within the job or changes jobs within the department,\n" +
               "new rows get inserted into this table with old job information of the\n" +
               "employee. Contains a complex primary key: employee_id+start_date.\n" +
               "References with jobs, employees, and departments tables."
)
public class JobHistory
        extends MappedJobHistory {

    /**
     * The name of the entity. The value is {@value}.
     */
    public static final String ENTITY_NAME = "JobHistory";

    // --------------------------------------------------------------------------------- EMPLOYEE_ID

    /**
     * The name of the attribute which maps the {@link Employee employee} joined on the
     * {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     * <p>
     * The association is neither insertable nor updatable; the {@value #ATTRIBUTE_NAME_EMPLOYEE_ID}
     * attribute writes the column.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE = "employee";

    // -------------------------------------------------------------------------------------- JOB_ID

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB = "job";

    // ------------------------------------------------------------------------------- DEPARTMENT_ID

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT = "department";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected JobHistory() {
        super();
    }

    // ------------------------------------------------------------------------------------ employee

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMPLOYEE} attribute.
     */
    @Nonnull
    public Employee getEmployee() {
        return employee;
    }

    // ----------------------------------------------------------------------------------------- job

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB} attribute.
     */
    @Nonnull
    public Job getJob() {
        return job;
    }

    // ---------------------------------------------------------------------------------- department

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     */
    @Nullable
    public Department getDepartment() {
        return department;
    }

    // ---------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_EMPLOYEE_ID,
                referencedColumnName = Employee.COLUMN_NAME_EMPLOYEE_ID,
                nullable = COLUMN_NULLABLE_EMPLOYEE_ID,
                insertable = false,
                updatable = false
    )
    private Employee employee;

    // ---------------------------------------------------------------------------------------------
    @Nonnull
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_JOB_ID,
                referencedColumnName = Job.COLUMN_NAME_JOB_ID,
                nullable = COLUMN_NULLABLE_JOB_ID,
                insertable = false,
                updatable = false
    )
    private Job job;

    // ---------------------------------------------------------------------------------------------
    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID,
                referencedColumnName = Department.COLUMN_NAME_DEPARTMENT_ID,
                nullable = COLUMN_NULLABLE_DEPARTMENT_ID,
                insertable = false,
                updatable = false
    )
    private Department department;
}
