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
public abstract class MappedCustomer {

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CUSTOMERS";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the table column to which the {@code customerId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * The name of the attribute which maps the {@code CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // --------------------------------------------------------------------------------------------------- EMAIL_ADDRESS

    /**
     * The name of the table column to which the {@code emailAddress} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EMAIL_ADDRESS";

    /**
     * The length of the {@code EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_EMAIL_ADDRESS = 255;

    /**
     * The name of the attribute which maps the {@code EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL_ADDRESS = "emailAddress";

    /**
     * The minimum size of the {@code emailAddress} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_NAME_EMAIL_ADDRESS = 0;

    /**
     * The maximum size of the {@code emailAddress} attribute.
     */
    public static final int SIZE_MAX_NAME_EMAIL_ADDRESS = COLUMN_LENGTH_NAME_EMAIL_ADDRESS;

    // -------------------------------------------------------------------------------------------- FULL_NAME / fullName

    /**
     * The name of the table column to which the {@code fullName} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_FULL_NAME = "FULL_NAME";

    /**
     * The length of the {@code FULL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME_FULL_NAME = 255;

    /**
     * The name of the attribute which maps the {@code FULL_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FULL_NAME = "fullName";

    /**
     * The minimum size of the {@code fullName} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_NAME_FULL_NAME = 0;

    /**
     * The maximum size of the {@code fullName} attribute.
     */
    public static final int SIZE_MAX_NAME_FULL_NAME = COLUMN_LENGTH_NAME_FULL_NAME;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCustomer() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "customerId=" + customerId +
               ",emailAddress=" + emailAddress +
               ",fullName=" + fullName +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedCustomer that)) {
            return false;
        }
        return Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(emailAddress);
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@code customerId} attribute.
     *
     * @return current value of {@code customerId} attribute.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@code customerId} attribute with the specified value.
     *
     * @param customerId new value for {@code customerId} attribute.
     */
    protected void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- emailAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    @Nonnull
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute with the specified value.
     *
     * @param emailAddress new value for {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    public void setEmailAddress(@Nonnull final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // -------------------------------------------------------------------------------------------------------- fullName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    @Nonnull
    public String getFullName() {
        return fullName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute with the specified value.
     *
     * @param fullName new value for {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    public void setFullName(@Nonnull final String fullName) {
        this.fullName = fullName;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Positive // ???
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            // insertable=true is the JPA default; it is spelled out because EclipseLink rejects
            // insertable=false on a @GeneratedValue @Id -- it treats the mapping as read-only and
            // fails descriptor initialisation with EclipseLink-46/EclipseLink-41. Verified on 5.0.1.
            insertable = true,
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
