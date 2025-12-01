package com.github.jinahya.oracle.sample.schemas.co.mapped;

/*-
 * #%L
 * co
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

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedCustomerBuilder<
        SELF extends MappedCustomerBuilder<SELF, ENTITY>,
        ENTITY extends MappedCustomer
        >
        extends _MappedCoEntityBuilder<SELF, ENTITY> {

    // -----------------------------------------------------------------------------------------------------------------
    protected MappedCustomerBuilder(final Class<ENTITY> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------------ customerId
    @Deprecated(forRemoval = true)
    public Long customerId() {
        return customerId;
    }

    @Deprecated(forRemoval = true)
    public SELF customerId(final Long customerId) {
        this.customerId = customerId;
        return (SELF) this;
    }

    // ---------------------------------------------------------------------------------------------------- emailAddress
    public String emailAddress() {
        return emailAddress;
    }

    public SELF emailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- fullName
    public String fullName() {
        return fullName;
    }

    public SELF fullName(final String fullName) {
        this.fullName = fullName;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long customerId;

    // -----------------------------------------------------------------------------------------------------------------
    private String emailAddress;

    private String fullName;
}
