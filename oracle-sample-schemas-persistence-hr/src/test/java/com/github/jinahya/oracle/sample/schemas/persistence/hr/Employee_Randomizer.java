package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A randomizer which produces randomized {@link Employee} instances.
 * <p>
 * The {@code job}, {@code manager}, {@code department}, {@code subordinates}, {@code managedDepartments} and
 * {@code jobHistories} associations are excluded from randomization; {@link Employee_Persister} supplies the
 * {@code job} and the {@code department}.
 * <p>
 * The {@code hireDate} is set a year in the past rather than randomized. The {@code UPDATE_JOB_HISTORY} trigger of the
 * installed schema copies it, as {@code START_DATE}, into a {@code JOB_HISTORY} row whose {@code END_DATE} is
 * {@code SYSDATE}, and {@code JHIST_DATE_INTERVAL} requires the latter to be after the former; a randomized date may be
 * today, or later.
 * <p>
 * The {@code commissionPct} Podam generates does not always fit the {@code NUMBER(2,2)} column -- a {@code 0}, for
 * one, has an integer digit, which {@code @Digits} rejects -- so one which does not is replaced by a random value
 * which does, from {@code 0.01} to {@code 0.99}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Employee_Randomizer extends PodamObjectRandomizer<Employee> {

    Employee_Randomizer() {
        super(Employee.class, List.of(
                Employee.ATTRIBUTE_NAME_JOB,
                Employee.ATTRIBUTE_NAME_MANAGER,
                Employee.ATTRIBUTE_NAME_DEPARTMENT,
                Employee.ATTRIBUTE_NAME_SUBORDINATES,
                Employee.ATTRIBUTE_NAME_MANAGED_DEPARTMENTS,
                Employee.ATTRIBUTE_NAME_JOB_HISTORIES
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Override
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Override
    public Employee get() {
        final var instance = super.get();
        instance.setHireDate(LocalDateTime.now().minusYears(1L).truncatedTo(ChronoUnit.DAYS));
        final var commissionPct = instance.getCommissionPct();
        if (commissionPct != null && !fitsCommissionPctColumn(commissionPct)) {
            instance.setCommissionPct(BigDecimal.valueOf(ThreadLocalRandom.current().nextLong(1L, 100L),
                                                         Employee.COLUMN_SCALE_COMMISSION_PCT));
        }
        return instance;
    }

    /**
     * Tests whether the specified value fits the {@value Employee#COLUMN_NAME_COMMISSION_PCT} column, counting its
     * integer and fraction digits the way {@link jakarta.validation.constraints.Digits @Digits} does.
     *
     * @param commissionPct the value to test.
     * @return {@code true} when the value fits; {@code false} otherwise.
     */
    private static boolean fitsCommissionPctColumn(final BigDecimal commissionPct) {
        final var stripped = commissionPct.stripTrailingZeros();
        final var integerDigits = Math.max(stripped.precision() - stripped.scale(), 0);
        final var fractionDigits = Math.max(stripped.scale(), 0);
        return integerDigits <= Employee.COLUMN_PRECISION_COMMISSION_PCT - Employee.COLUMN_SCALE_COMMISSION_PCT
               && fractionDigits <= Employee.COLUMN_SCALE_COMMISSION_PCT;
    }
}
