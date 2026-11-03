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

import com.github.jinahya.oracle.sample.persistence.hr.mapped.MappedEmpDetailsView;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * An entity class for mapping the {@value MappedEmpDetailsView#TABLE_NAME} view.
 * <p>
 * Every column is mapped read-only, as {@link MappedEmpDetailsView} describes.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "EmpDetailsView.selectOneByEmployeeId",
            query = """
                    SELECT e
                    FROM EmpDetailsView e
                    WHERE e.employeeId = :employeeId"""
)
@NamedQuery(name = "EmpDetailsView.selectListOrderByEmployeeIdAsc",
            query = """
                    SELECT e
                    FROM EmpDetailsView e
                    ORDER BY e.employeeId ASC"""
)
@NamedQuery(name = "EmpDetailsView.selectListOrderByEmployeeIdAscEmployeeIdGt",
            query = """
                    SELECT e
                    FROM EmpDetailsView e
                    WHERE e.employeeId > :employeeIdMinExclusive
                    ORDER BY e.employeeId ASC"""
)
@NamedQuery(name = "EmpDetailsView.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM EmpDetailsView e"""
)
@Entity
@Table(name = MappedEmpDetailsView.TABLE_NAME)
public class EmpDetailsView extends MappedEmpDetailsView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected EmpDetailsView() {
        super();
    }
}
