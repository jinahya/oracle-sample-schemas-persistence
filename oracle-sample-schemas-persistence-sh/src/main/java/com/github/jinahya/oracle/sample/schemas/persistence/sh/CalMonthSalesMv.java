package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * sh
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

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.MappedCalMonthSalesMv;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * An entity class for mapping the {@value CalMonthSalesMv#TABLE_NAME} materialized view.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = CalMonthSalesMv.TABLE_NAME)
public class CalMonthSalesMv extends MappedCalMonthSalesMv implements __DomainEntity<String> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CalMonthSalesMv() {
        super();
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute with the specified value.
     *
     * @param calendarMonthDesc new value for {@value #ATTRIBUTE_NAME_CALENDAR_MONTH_DESC} attribute.
     */
    @Override
    public void setCalendarMonthDesc(final String calendarMonthDesc) {
        super.setCalendarMonthDesc(calendarMonthDesc);
    }
}
