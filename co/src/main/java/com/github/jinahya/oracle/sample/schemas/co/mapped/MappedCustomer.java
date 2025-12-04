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

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass
public abstract class MappedCustomer extends _MappedCoEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CUSTOMERS";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the table column to which the {@link MappedCustomer_#CUSTOMER_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    // --------------------------------------------------------------------------------------------------- EMAIL_ADDRESS

    /**
     * The name of the table column to which the {@value MappedCustomer_#EMAIL_ADDRESS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EMAIL_ADDRESS";

    public static final int COLUMN_LENGTH_NAME_EMAIL_ADDRESS = 255;

    public static final String ATTRIBUTE_NAME_EMAIL_ADDRESS = "emailAddress";

    public static final int SIZE_MIN_NAME_EMAIL_ADDRESS = 0;

    public static final int SIZE_MAX_NAME_EMAIL_ADDRESS = COLUMN_LENGTH_NAME_EMAIL_ADDRESS;

    // -------------------------------------------------------------------------------------------- FULL_NAME / fullName

    /**
     * The name of the table column to which the {@link MappedCustomer_#FULL_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FULL_NAME = "FULL_NAME";

    public static final int COLUMN_LENGTH_NAME_FULL_NAME = 255;

    public static final String ATTRIBUTE_NAME_FULL_NAME = "fullName";

    public static final int SIZE_MIN_NAME_FULL_NAME = 0;

    public static final int SIZE_MAX_NAME_FULL_NAME = COLUMN_LENGTH_NAME_FULL_NAME;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCustomer() {
        super();
    }

    protected MappedCustomer(@Nonnull final MappedCustomerBuilder<?, ?> builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final String toString() {
        return super.toString() + '{' +
               "customerId=" + customerId +
               ",emailAddress=" + emailAddress +
               ",fullName=" + fullName +
               '}';
    }

    protected final boolean equalsWithEmailAddress(final Object obj) {
        if (!(obj instanceof MappedCustomer that)) {
            return false;
        }
        return Objects.equals(emailAddress, that.emailAddress);
    }

    protected final int hashCodeWithEmailAddress() {
        return Objects.hashCode(emailAddress);
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@link MappedCustomer_#CUSTOMER_ID} attribute.
     *
     * @return current value of {@link MappedCustomer_#CUSTOMER_ID} attribute.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@link MappedCustomer_#CUSTOMER_ID} attribute with the specified value.
     *
     * @param customerId new value for {@link MappedCustomer_#CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- emailAddress
    @Nonnull
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@Nonnull final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // -------------------------------------------------------------------------------------------------------- fullName
    @Nonnull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(@Nonnull final String fullName) {
        this.fullName = fullName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Positive // ???
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
//            insertable = false,
            insertable = true /* eclipseLink */,
            updatable = false
    )
    private Long customerId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Email
    @Size(min = SIZE_MIN_NAME_EMAIL_ADDRESS, max = SIZE_MAX_NAME_EMAIL_ADDRESS)
    @NotNull
    @Column(name = COLUMN_NAME_EMAIL_ADDRESS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_EMAIL_ADDRESS,
            unique = true
    )
    private String emailAddress;

    @Nonnull
    @Size(min = SIZE_MIN_NAME_FULL_NAME, max = SIZE_MAX_NAME_FULL_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_FULL_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_FULL_NAME
    )
    private String fullName;
}
