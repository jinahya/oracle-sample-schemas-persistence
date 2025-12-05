package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class MappedJob_Test<ENTITY extends MappedJob> extends _MappedHrEntity_Test<ENTITY, String> {

    protected MappedJob_Test(final Class<ENTITY> entityClass) {
        super(entityClass, String.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("setMinSalaryWhileAdjustingMaxSalary(minSalary)")
    @Nested
    final class SetMinSalaryWhileAdjustingMaxSalary_Test {

        @DisplayName("(null)")
        @Test
        void __MinSalaryIsNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newInstantiatedTargetInstanceSpy();
            assertThat(instance.getMinSalary()).isNull();
            assertThat(instance.getMaxSalary()).isNull();
            // ---------------------------------------------------------------------------------------------------- when
            instance.setMinSalaryWhileAdjustingMaxSalary(null);
            // ---------------------------------------------------------------------------------------------------- then
            verify(instance, never()).setMaxSalary(any());
        }

        @DisplayName(("[maxSalary == null]...(!null)"))
        @Test
        void __NotNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newInstantiatedTargetInstanceSpy();
            assertThat(instance.getMinSalary()).isNull();
            assertThat(instance.getMaxSalary()).isNull();
            final var minSalary = MappedJob_TestUtils.newRandomPositiveMinSalary();
            // ---------------------------------------------------------------------------------------------------- when
            instance.setMinSalaryWhileAdjustingMaxSalary(minSalary);
            // ---------------------------------------------------------------------------------------------------- then
            verify(instance, atMostOnce()).setMaxSalary(intThat(v -> v == null || v >= minSalary));
            assertThat(instance.isMinSalaryLessThanOrEqualToMaxSalary()).isTrue();
        }

        @DisplayName(("[maxSalary != null]...(!null"))
        @Test
        void __NotNull_() {
            // --------------------------------------------------------------------------------------------------- given
            final var minSalary = MappedJob_TestUtils.newRandomPositiveMinSalary();
            final var instance = newInstantiatedTargetInstanceSpy(v -> {
                v.setMaxSalary(minSalary - 1);
                return v;
            });
            // ---------------------------------------------------------------------------------------------------- when
            instance.setMinSalaryWhileAdjustingMaxSalary(minSalary);
            // ---------------------------------------------------------------------------------------------------- then
            verify(instance, times(1)).setMaxSalary(intThat(v -> v >= minSalary));
            assertThat(instance.isMinSalaryLessThanOrEqualToMaxSalary()).isTrue();
        }
    }
}
