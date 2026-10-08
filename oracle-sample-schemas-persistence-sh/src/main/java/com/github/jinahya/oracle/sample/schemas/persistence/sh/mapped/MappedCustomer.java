package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/*-
 * #%L
 * sh
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

import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedCustomer#TABLE_NAME} table.
 * <p>
 * The {@value MappedCustomer#COLUMN_NAME_COUNTRY_ID} column is mapped read-only
 * ({@code insertable = false, updatable = false}), with a {@code protected} getter and no setter, for
 * {@link #toString()} and for queries. How the relationship behind each is mapped -- fetch type, cascade, whether there
 * is an association at all -- is the extending entity's decision, so the extending entity also owns its writable
 * mapping, by an association's {@link jakarta.persistence.JoinColumn @JoinColumn} or by an
 * {@link jakarta.persistence.AttributeOverride @AttributeOverride}. <strong>An extending entity which maps neither
 * never writes it.</strong> Being read-only, it is populated by a load or a refresh only.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedCustomer {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CUSTOMERS";

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    // ------------------------------------------------------------------------------------------------- CUST_FIRST_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_FIRST_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_FIRST_NAME = "CUST_FIRST_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_FIRST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_FIRST_NAME = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_FIRST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_FIRST_NAME = "custFirstName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_FIRST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_FIRST_NAME = COLUMN_LENGTH_CUST_FIRST_NAME;

    // -------------------------------------------------------------------------------------------------- CUST_LAST_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_LAST_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_LAST_NAME = "CUST_LAST_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_LAST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_LAST_NAME = 40;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_LAST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_LAST_NAME = "custLastName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_LAST_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_LAST_NAME = COLUMN_LENGTH_CUST_LAST_NAME;

    // ----------------------------------------------------------------------------------------------------- CUST_GENDER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_GENDER} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_GENDER = "CUST_GENDER";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_GENDER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_GENDER = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_GENDER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_GENDER = "custGender";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_GENDER} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_GENDER = COLUMN_LENGTH_CUST_GENDER;

    // ---------------------------------------------------------------------------------------------- CUST_YEAR_OF_BIRTH

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_YEAR_OF_BIRTH} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_CUST_YEAR_OF_BIRTH = "CUST_YEAR_OF_BIRTH";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_YEAR_OF_BIRTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_YEAR_OF_BIRTH = "custYearOfBirth";

    /**
     * The precision of the {@value #COLUMN_NAME_CUST_YEAR_OF_BIRTH} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CUST_YEAR_OF_BIRTH = 4;

    /**
     * The scale of the {@value #COLUMN_NAME_CUST_YEAR_OF_BIRTH} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CUST_YEAR_OF_BIRTH = 0;

    // --------------------------------------------------------------------------------------------- CUST_MARITAL_STATUS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_MARITAL_STATUS} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_CUST_MARITAL_STATUS = "CUST_MARITAL_STATUS";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_MARITAL_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_MARITAL_STATUS = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_MARITAL_STATUS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_MARITAL_STATUS = "custMaritalStatus";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_MARITAL_STATUS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_MARITAL_STATUS = COLUMN_LENGTH_CUST_MARITAL_STATUS;

    // --------------------------------------------------------------------------------------------- CUST_STREET_ADDRESS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_STREET_ADDRESS} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_CUST_STREET_ADDRESS = "CUST_STREET_ADDRESS";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_STREET_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_STREET_ADDRESS = 40;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_STREET_ADDRESS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_STREET_ADDRESS = "custStreetAddress";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_STREET_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_STREET_ADDRESS = COLUMN_LENGTH_CUST_STREET_ADDRESS;

    // ------------------------------------------------------------------------------------------------ CUST_POSTAL_CODE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_POSTAL_CODE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_POSTAL_CODE = "CUST_POSTAL_CODE";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_POSTAL_CODE = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_POSTAL_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_POSTAL_CODE = "custPostalCode";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_POSTAL_CODE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_POSTAL_CODE = COLUMN_LENGTH_CUST_POSTAL_CODE;

    // ------------------------------------------------------------------------------------------------------- CUST_CITY

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_CITY} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_CITY = "CUST_CITY";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_CITY = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_CITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_CITY = "custCity";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_CITY} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_CITY = COLUMN_LENGTH_CUST_CITY;

    // ---------------------------------------------------------------------------------------------------- CUST_CITY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_CITY_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_CITY_ID = "CUST_CITY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_CITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_CITY_ID = "custCityId";

    // --------------------------------------------------------------------------------------------- CUST_STATE_PROVINCE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_CUST_STATE_PROVINCE = "CUST_STATE_PROVINCE";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_STATE_PROVINCE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_STATE_PROVINCE = 40;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_STATE_PROVINCE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_STATE_PROVINCE = "custStateProvince";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_STATE_PROVINCE = COLUMN_LENGTH_CUST_STATE_PROVINCE;

    // ------------------------------------------------------------------------------------------ CUST_STATE_PROVINCE_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE_ID} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_STATE_PROVINCE_ID = "CUST_STATE_PROVINCE_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_STATE_PROVINCE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_STATE_PROVINCE_ID = "custStateProvinceId";

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    // ------------------------------------------------------------------------------------------ CUST_MAIN_PHONE_NUMBER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_MAIN_PHONE_NUMBER} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_CUST_MAIN_PHONE_NUMBER = "CUST_MAIN_PHONE_NUMBER";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_MAIN_PHONE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_MAIN_PHONE_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_MAIN_PHONE_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_MAIN_PHONE_NUMBER = "custMainPhoneNumber";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_MAIN_PHONE_NUMBER} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_MAIN_PHONE_NUMBER = COLUMN_LENGTH_CUST_MAIN_PHONE_NUMBER;

    // ----------------------------------------------------------------------------------------------- CUST_INCOME_LEVEL

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_INCOME_LEVEL} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_INCOME_LEVEL = "CUST_INCOME_LEVEL";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_INCOME_LEVEL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_INCOME_LEVEL = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_INCOME_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_INCOME_LEVEL = "custIncomeLevel";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_INCOME_LEVEL} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_INCOME_LEVEL = COLUMN_LENGTH_CUST_INCOME_LEVEL;

    // ----------------------------------------------------------------------------------------------- CUST_CREDIT_LIMIT

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_CREDIT_LIMIT} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_CREDIT_LIMIT = "CUST_CREDIT_LIMIT";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_CREDIT_LIMIT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_CREDIT_LIMIT = "custCreditLimit";

    // ------------------------------------------------------------------------------------------------------ CUST_EMAIL

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_EMAIL} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_EMAIL = "CUST_EMAIL";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_EMAIL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_EMAIL = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_EMAIL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_EMAIL = "custEmail";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_EMAIL} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_EMAIL = COLUMN_LENGTH_CUST_EMAIL;

    // ------------------------------------------------------------------------------------------------------ CUST_TOTAL

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_TOTAL} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_TOTAL = "CUST_TOTAL";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_TOTAL = 14;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_TOTAL = "custTotal";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_TOTAL} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_TOTAL = COLUMN_LENGTH_CUST_TOTAL;

    // --------------------------------------------------------------------------------------------------- CUST_TOTAL_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_TOTAL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_TOTAL_ID = "CUST_TOTAL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_TOTAL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_TOTAL_ID = "custTotalId";

    // ----------------------------------------------------------------------------------------------------- CUST_SRC_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_SRC_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_SRC_ID = "CUST_SRC_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_SRC_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_SRC_ID = "custSrcId";

    // --------------------------------------------------------------------------------------------------- CUST_EFF_FROM

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_EFF_FROM} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_EFF_FROM = "CUST_EFF_FROM";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_EFF_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_EFF_FROM = "custEffFrom";

    // ----------------------------------------------------------------------------------------------------- CUST_EFF_TO

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_EFF_TO} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_EFF_TO = "CUST_EFF_TO";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_EFF_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_EFF_TO = "custEffTo";

    // ------------------------------------------------------------------------------------------------------ CUST_VALID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_VALID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_VALID = "CUST_VALID";

    /**
     * The length of the {@value #COLUMN_NAME_CUST_VALID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUST_VALID = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_VALID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_VALID = "custValid";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CUST_VALID} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CUST_VALID = COLUMN_LENGTH_CUST_VALID;

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
               "custId=" + custId +
               ",custFirstName=" + custFirstName +
               ",custLastName=" + custLastName +
               ",custGender=" + custGender +
               ",custYearOfBirth=" + custYearOfBirth +
               ",custMaritalStatus=" + custMaritalStatus +
               ",custStreetAddress=" + custStreetAddress +
               ",custPostalCode=" + custPostalCode +
               ",custCity=" + custCity +
               ",custCityId=" + custCityId +
               ",custStateProvince=" + custStateProvince +
               ",custStateProvinceId=" + custStateProvinceId +
               ",countryId=" + countryId +
               ",custMainPhoneNumber=" + custMainPhoneNumber +
               ",custIncomeLevel=" + custIncomeLevel +
               ",custCreditLimit=" + custCreditLimit +
               ",custEmail=" + custEmail +
               ",custTotal=" + custTotal +
               ",custTotalId=" + custTotalId +
               ",custSrcId=" + custSrcId +
               ",custEffFrom=" + custEffFrom +
               ",custEffTo=" + custEffTo +
               ",custValid=" + custValid +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedCustomer that)) {
            return false;
        }
        return Objects.equals(getCustId(), that.getCustId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getCustId());
    }

    // ---------------------------------------------------------------------------------------------------------- custId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    public Long getCustId() {
        return custId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute with the specified value.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    protected void setCustId(final Long custId) {
        this.custId = custId;
    }

    // --------------------------------------------------------------------------------------------------- custFirstName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_FIRST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_FIRST_NAME} attribute.
     */
    public String getCustFirstName() {
        return custFirstName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_FIRST_NAME} attribute with the specified value.
     *
     * @param custFirstName new value for {@value #ATTRIBUTE_NAME_CUST_FIRST_NAME} attribute.
     */
    public void setCustFirstName(final String custFirstName) {
        this.custFirstName = custFirstName;
    }

    // ---------------------------------------------------------------------------------------------------- custLastName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_LAST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_LAST_NAME} attribute.
     */
    public String getCustLastName() {
        return custLastName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_LAST_NAME} attribute with the specified value.
     *
     * @param custLastName new value for {@value #ATTRIBUTE_NAME_CUST_LAST_NAME} attribute.
     */
    public void setCustLastName(final String custLastName) {
        this.custLastName = custLastName;
    }

    // ------------------------------------------------------------------------------------------------------ custGender

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_GENDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_GENDER} attribute.
     */
    public String getCustGender() {
        return custGender;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_GENDER} attribute with the specified value.
     *
     * @param custGender new value for {@value #ATTRIBUTE_NAME_CUST_GENDER} attribute.
     */
    public void setCustGender(final String custGender) {
        this.custGender = custGender;
    }

    // ------------------------------------------------------------------------------------------------- custYearOfBirth

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_YEAR_OF_BIRTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_YEAR_OF_BIRTH} attribute.
     */
    public Integer getCustYearOfBirth() {
        return custYearOfBirth;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_YEAR_OF_BIRTH} attribute with the specified value.
     *
     * @param custYearOfBirth new value for {@value #ATTRIBUTE_NAME_CUST_YEAR_OF_BIRTH} attribute.
     */
    public void setCustYearOfBirth(final Integer custYearOfBirth) {
        this.custYearOfBirth = custYearOfBirth;
    }

    // ----------------------------------------------------------------------------------------------- custMaritalStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_MARITAL_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_MARITAL_STATUS} attribute.
     */
    public String getCustMaritalStatus() {
        return custMaritalStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_MARITAL_STATUS} attribute with the specified value.
     *
     * @param custMaritalStatus new value for {@value #ATTRIBUTE_NAME_CUST_MARITAL_STATUS} attribute.
     */
    public void setCustMaritalStatus(final String custMaritalStatus) {
        this.custMaritalStatus = custMaritalStatus;
    }

    // ----------------------------------------------------------------------------------------------- custStreetAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_STREET_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_STREET_ADDRESS} attribute.
     */
    public String getCustStreetAddress() {
        return custStreetAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_STREET_ADDRESS} attribute with the specified value.
     *
     * @param custStreetAddress new value for {@value #ATTRIBUTE_NAME_CUST_STREET_ADDRESS} attribute.
     */
    public void setCustStreetAddress(final String custStreetAddress) {
        this.custStreetAddress = custStreetAddress;
    }

    // -------------------------------------------------------------------------------------------------- custPostalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_POSTAL_CODE} attribute.
     */
    public String getCustPostalCode() {
        return custPostalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_POSTAL_CODE} attribute with the specified value.
     *
     * @param custPostalCode new value for {@value #ATTRIBUTE_NAME_CUST_POSTAL_CODE} attribute.
     */
    public void setCustPostalCode(final String custPostalCode) {
        this.custPostalCode = custPostalCode;
    }

    // -------------------------------------------------------------------------------------------------------- custCity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_CITY} attribute.
     */
    public String getCustCity() {
        return custCity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_CITY} attribute with the specified value.
     *
     * @param custCity new value for {@value #ATTRIBUTE_NAME_CUST_CITY} attribute.
     */
    public void setCustCity(final String custCity) {
        this.custCity = custCity;
    }

    // ------------------------------------------------------------------------------------------------------ custCityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_CITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_CITY_ID} attribute.
     */
    public Long getCustCityId() {
        return custCityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_CITY_ID} attribute with the specified value.
     *
     * @param custCityId new value for {@value #ATTRIBUTE_NAME_CUST_CITY_ID} attribute.
     */
    public void setCustCityId(final Long custCityId) {
        this.custCityId = custCityId;
    }

    // ----------------------------------------------------------------------------------------------- custStateProvince

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE} attribute.
     */
    public String getCustStateProvince() {
        return custStateProvince;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE} attribute with the specified value.
     *
     * @param custStateProvince new value for {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE} attribute.
     */
    public void setCustStateProvince(final String custStateProvince) {
        this.custStateProvince = custStateProvince;
    }

    // --------------------------------------------------------------------------------------------- custStateProvinceId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE_ID} attribute.
     */
    public Long getCustStateProvinceId() {
        return custStateProvinceId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE_ID} attribute with the specified value.
     *
     * @param custStateProvinceId new value for {@value #ATTRIBUTE_NAME_CUST_STATE_PROVINCE_ID} attribute.
     */
    public void setCustStateProvinceId(final Long custStateProvinceId) {
        this.custStateProvinceId = custStateProvinceId;
    }

    // ------------------------------------------------------------------------------------------------------- countryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute, which is {@code null} until this instance
     * is loaded.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     * @apiNote This method is {@code protected}, not package-private, so that a lazy proxy, which is a subclass in
     * another package, can override it.
     */
    @Nullable
    protected Long getCountryId() {
        return countryId;
    }

    // --------------------------------------------------------------------------------------------- custMainPhoneNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_MAIN_PHONE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_MAIN_PHONE_NUMBER} attribute.
     */
    public String getCustMainPhoneNumber() {
        return custMainPhoneNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_MAIN_PHONE_NUMBER} attribute with the specified value.
     *
     * @param custMainPhoneNumber new value for {@value #ATTRIBUTE_NAME_CUST_MAIN_PHONE_NUMBER} attribute.
     */
    public void setCustMainPhoneNumber(final String custMainPhoneNumber) {
        this.custMainPhoneNumber = custMainPhoneNumber;
    }

    // ------------------------------------------------------------------------------------------------- custIncomeLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_INCOME_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_INCOME_LEVEL} attribute.
     */
    public String getCustIncomeLevel() {
        return custIncomeLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_INCOME_LEVEL} attribute with the specified value.
     *
     * @param custIncomeLevel new value for {@value #ATTRIBUTE_NAME_CUST_INCOME_LEVEL} attribute.
     */
    public void setCustIncomeLevel(final String custIncomeLevel) {
        this.custIncomeLevel = custIncomeLevel;
    }

    // ------------------------------------------------------------------------------------------------- custCreditLimit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_CREDIT_LIMIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_CREDIT_LIMIT} attribute.
     */
    public Long getCustCreditLimit() {
        return custCreditLimit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_CREDIT_LIMIT} attribute with the specified value.
     *
     * @param custCreditLimit new value for {@value #ATTRIBUTE_NAME_CUST_CREDIT_LIMIT} attribute.
     */
    public void setCustCreditLimit(final Long custCreditLimit) {
        this.custCreditLimit = custCreditLimit;
    }

    // ------------------------------------------------------------------------------------------------------- custEmail

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_EMAIL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_EMAIL} attribute.
     */
    public String getCustEmail() {
        return custEmail;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_EMAIL} attribute with the specified value.
     *
     * @param custEmail new value for {@value #ATTRIBUTE_NAME_CUST_EMAIL} attribute.
     */
    public void setCustEmail(final String custEmail) {
        this.custEmail = custEmail;
    }

    // ------------------------------------------------------------------------------------------------------- custTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_TOTAL} attribute.
     */
    public String getCustTotal() {
        return custTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_TOTAL} attribute with the specified value.
     *
     * @param custTotal new value for {@value #ATTRIBUTE_NAME_CUST_TOTAL} attribute.
     */
    public void setCustTotal(final String custTotal) {
        this.custTotal = custTotal;
    }

    // ----------------------------------------------------------------------------------------------------- custTotalId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_TOTAL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_TOTAL_ID} attribute.
     */
    public Long getCustTotalId() {
        return custTotalId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_TOTAL_ID} attribute with the specified value.
     *
     * @param custTotalId new value for {@value #ATTRIBUTE_NAME_CUST_TOTAL_ID} attribute.
     */
    public void setCustTotalId(final Long custTotalId) {
        this.custTotalId = custTotalId;
    }

    // ------------------------------------------------------------------------------------------------------- custSrcId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_SRC_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_SRC_ID} attribute.
     */
    public Long getCustSrcId() {
        return custSrcId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_SRC_ID} attribute with the specified value.
     *
     * @param custSrcId new value for {@value #ATTRIBUTE_NAME_CUST_SRC_ID} attribute.
     */
    public void setCustSrcId(final Long custSrcId) {
        this.custSrcId = custSrcId;
    }

    // ----------------------------------------------------------------------------------------------------- custEffFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_EFF_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_EFF_FROM} attribute.
     */
    public LocalDateTime getCustEffFrom() {
        return custEffFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_EFF_FROM} attribute with the specified value.
     *
     * @param custEffFrom new value for {@value #ATTRIBUTE_NAME_CUST_EFF_FROM} attribute.
     */
    public void setCustEffFrom(final LocalDateTime custEffFrom) {
        this.custEffFrom = custEffFrom;
    }

    // ------------------------------------------------------------------------------------------------------- custEffTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_EFF_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_EFF_TO} attribute.
     */
    public LocalDateTime getCustEffTo() {
        return custEffTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_EFF_TO} attribute with the specified value.
     *
     * @param custEffTo new value for {@value #ATTRIBUTE_NAME_CUST_EFF_TO} attribute.
     */
    public void setCustEffTo(final LocalDateTime custEffTo) {
        this.custEffTo = custEffTo;
    }

    // ------------------------------------------------------------------------------------------------------- custValid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_VALID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_VALID} attribute.
     */
    public String getCustValid() {
        return custValid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_VALID} attribute with the specified value.
     *
     * @param custValid new value for {@value #ATTRIBUTE_NAME_CUST_VALID} attribute.
     */
    public void setCustValid(final String custValid) {
        this.custValid = custValid;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_CUST_ID, nullable = false, insertable = true, updatable = false)
    private Long custId;

    @Size(max = SIZE_MAX_CUST_FIRST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_FIRST_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_FIRST_NAME
    )
    private String custFirstName;

    @Size(max = SIZE_MAX_CUST_LAST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_LAST_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_LAST_NAME
    )
    private String custLastName;

    @Size(max = SIZE_MAX_CUST_GENDER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_GENDER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_GENDER
    )
    private String custGender;

    @NotNull
    @Digits(integer = COLUMN_PRECISION_CUST_YEAR_OF_BIRTH - COLUMN_SCALE_CUST_YEAR_OF_BIRTH,
            fraction = COLUMN_SCALE_CUST_YEAR_OF_BIRTH)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_YEAR_OF_BIRTH,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer custYearOfBirth;

    @Size(max = SIZE_MAX_CUST_MARITAL_STATUS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUST_MARITAL_STATUS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_MARITAL_STATUS
    )
    private String custMaritalStatus;

    @Size(max = SIZE_MAX_CUST_STREET_ADDRESS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_STREET_ADDRESS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_STREET_ADDRESS
    )
    private String custStreetAddress;

    @Size(max = SIZE_MAX_CUST_POSTAL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_POSTAL_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_POSTAL_CODE
    )
    private String custPostalCode;

    @Size(max = SIZE_MAX_CUST_CITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_CITY,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_CITY
    )
    private String custCity;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_CITY_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long custCityId;

    @Size(max = SIZE_MAX_CUST_STATE_PROVINCE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_STATE_PROVINCE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_STATE_PROVINCE
    )
    private String custStateProvince;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_STATE_PROVINCE_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long custStateProvinceId;

    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Column(name = COLUMN_NAME_COUNTRY_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Long countryId;

    @Size(max = SIZE_MAX_CUST_MAIN_PHONE_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_MAIN_PHONE_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_MAIN_PHONE_NUMBER
    )
    private String custMainPhoneNumber;

    @Size(max = SIZE_MAX_CUST_INCOME_LEVEL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUST_INCOME_LEVEL,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_INCOME_LEVEL
    )
    private String custIncomeLevel;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUST_CREDIT_LIMIT,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long custCreditLimit;

    @Size(max = SIZE_MAX_CUST_EMAIL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUST_EMAIL,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_EMAIL
    )
    private String custEmail;

    @Size(max = SIZE_MAX_CUST_TOTAL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_TOTAL,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_TOTAL
    )
    private String custTotal;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUST_TOTAL_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long custTotalId;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUST_SRC_ID,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long custSrcId;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUST_EFF_FROM,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime custEffFrom;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUST_EFF_TO,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime custEffTo;

    @Size(max = SIZE_MAX_CUST_VALID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUST_VALID,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUST_VALID
    )
    private String custValid;
}
