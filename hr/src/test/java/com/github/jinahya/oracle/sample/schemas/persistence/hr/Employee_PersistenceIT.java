package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntity_PersistenceIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Employee_PersistenceIT extends _MappedHrEntity_PersistenceIT<Employee, Integer> {

    Employee_PersistenceIT() {
        super(Employee.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("Job updated -> JobHistory added")
    @Test
    void _JobHistoryAdded_JobUpdated() {
        applyEntityManagerInTransactionAndRollback(em -> {
            return null;
        });
    }
}
