package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedJobHistory_PersistenceIT;
import com.github.jinahya.persistence.mapped.test.__Disable_PersistEntityInstance_Test;
import com.github.jinahya.persistence.mapped.test.___PersisterUtils;
import jakarta.annotation.Nonnull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

//@org.junit.jupiter.api.Disabled
@__Disable_PersistEntityInstance_Test
class JobHistoryWithEmbeddedId_PersistenceIT
        extends MappedJobHistory_PersistenceIT<JobHistoryWithEmbeddedId> {

    JobHistoryWithEmbeddedId_PersistenceIT() {
        super(JobHistoryWithEmbeddedId.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void selectRandomEntityInstance(@Nonnull final JobHistoryWithEmbeddedId entityInstance) {
        super.selectRandomEntityInstance(entityInstance);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectList_WhereIdEmployeeIdEqualTo_OrderByIdStartDateDesc")
    @Nested
    class SelectList_WhereIdEmployeeIdEqualTo_OrderByIdStartDateDesc_Test {

        @Test
        void NamedQuery__() {
            applyEntityManagerInTransactionAndRollback(em -> {
                // ----------------------------------------------------------------------------------------------- given
                final var employee = ___PersisterUtils.newPersistedInstanceOf(em, Employee.class);
                // ------------------------------------------------------------------------------------------------ when
                if (ThreadLocalRandom.current().nextBoolean()) {
                    employee.setJob(___PersisterUtils.newPersistedInstanceOf(em, Job.class));
                } else {
                    employee.setDepartment(___PersisterUtils.newPersistedInstanceOf(em, Department.class));
                }
                em.merge(employee);
                em.flush();
                final var query = em.createNamedQuery(
                        "JobHistory.selectList_WhereIdEmployeeIdEqualTo_OrderByIdStartDateDesc",
                        JobHistoryWithEmbeddedId.class
                );
                query.setParameter("idEmployeeId", employee.getEmployeeId());
                final var result = query.getResultList();
                // ---------------------------------------------------------------------------------------------------------
                assertThat(result).hasSize(1).allSatisfy(jh -> {
                    assertThat(jh.getId().getEmployeeId()).isEqualTo(employee.getEmployeeId());
                });
                return null;
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("selectList_WhereEmployeeEqualTo_OrderByStartDateDesc")
    @Nested
    class SelectList_WhereEmployeeEqualTo_OrderByStartDateDesc_Test {

        @Test
        void NamedQuery__() {
            applyEntityManagerInTransactionAndRollback(em -> {
                // ----------------------------------------------------------------------------------------------- given
                final var employee = ___PersisterUtils.newPersistedInstanceOf(em, Employee.class);
                // ------------------------------------------------------------------------------------------------ when
                if (ThreadLocalRandom.current().nextBoolean()) {
                    employee.setJob(___PersisterUtils.newPersistedInstanceOf(em, Job.class));
                } else {
                    employee.setDepartment(___PersisterUtils.newPersistedInstanceOf(em, Department.class));
                }
                em.merge(employee);
                em.flush();
                final var query = em.createNamedQuery(
                        "JobHistory.selectList_WhereIdEmployeeIdEqualTo_OrderByIdStartDateDesc",
                        JobHistoryWithEmbeddedId.class
                );
                query.setParameter("idEmployeeId", employee.getEmployeeId());
                final var result = query.getResultList();
                // ---------------------------------------------------------------------------------------------------------
                assertThat(result).hasSize(1).allSatisfy(jh -> {
                    assertThat(jh.getId().getEmployeeId()).isEqualTo(employee.getEmployeeId());
                });
                return null;
            });
        }
    }
}
