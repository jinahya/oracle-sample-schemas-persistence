package com.github.jinahya.oracle.sample.schemas.persistence.sh;

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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * An entity class for mapping the {@value Country#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Country.TABLE_NAME)
public class Country {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "COUNTRIES";

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID

    /**
     * The name of the table column to which the {@code countryId} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    /**
     * The name of the attribute which maps the {@code COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    // ------------------------------------------------------------------------------------------------ COUNTRY_ISO_CODE

    /**
     * The name of the table column to which the {@code countryIsoCode} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ISO_CODE = "COUNTRY_ISO_CODE";

    /**
     * The length of the {@code COUNTRY_ISO_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_ISO_CODE = 2;

    /**
     * The name of the attribute which maps the {@code COUNTRY_ISO_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ISO_CODE = "countryIsoCode";

    /**
     * The minimum size of the {@code countryIsoCode} attribute.
     */
    public static final int SIZE_MIN_COUNTRY_ISO_CODE = COLUMN_LENGTH_COUNTRY_ISO_CODE;

    /**
     * The maximum size of the {@code countryIsoCode} attribute.
     */
    public static final int SIZE_MAX_COUNTRY_ISO_CODE = SIZE_MIN_COUNTRY_ISO_CODE;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Country() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "countryId=" + countryId +
               ",countryIsoCode=" + countryIsoCode +
               ",countryName=" + countryName +
               ",countrySubregion=" + countrySubregion +
               ",countrySubregionId=" + countrySubregionId +
               ",countryRegion=" + countryRegion +
               ",countryRegionId=" + countryRegionId +
               ",countryTotal=" + countryTotal +
               ",countryTotalId=" + countryTotalId +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Country that)) {
            return false;
        }
        return Objects.equals(countryId, that.countryId);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(countryId);
    }
    // ------------------------------------------------------------------------------------------------------ countryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute with the specified value.
     *
     * @param countryId new value for {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    public void setCountryId(final Long countryId) {
        this.countryId = countryId;
    }

    // -------------------------------------------------------------------------------------------------- countryIsoCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_ISO_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_ISO_CODE} attribute.
     */
    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_ISO_CODE} attribute with the specified value.
     *
     * @param countryIsoCode new value for {@value #ATTRIBUTE_NAME_COUNTRY_ISO_CODE} attribute.
     */
    public void setCountryIsoCode(final String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    // ----------------------------------------------------------------------------------------------------- countryName

    /**
     * Returns current value of {@code countryName} attribute.
     *
     * @return current value of {@code countryName} attribute.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Replaces current value of {@code countryName} attribute with the specified value.
     *
     * @param countryName new value for {@code countryName} attribute.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    // ------------------------------------------------------------------------------------------------ countrySubregion

    /**
     * Returns current value of {@code countrySubregion} attribute.
     *
     * @return current value of {@code countrySubregion} attribute.
     */
    public String getCountrySubregion() {
        return countrySubregion;
    }

    /**
     * Replaces current value of {@code countrySubregion} attribute with the specified value.
     *
     * @param countrySubregion new value for {@code countrySubregion} attribute.
     */
    public void setCountrySubregion(String countrySubregion) {
        this.countrySubregion = countrySubregion;
    }

    // ---------------------------------------------------------------------------------------------- countrySubregionId

    /**
     * Returns current value of {@code countrySubregionId} attribute.
     *
     * @return current value of {@code countrySubregionId} attribute.
     */
    public Long getCountrySubregionId() {
        return countrySubregionId;
    }

    /**
     * Replaces current value of {@code countrySubregionId} attribute with the specified value.
     *
     * @param countrySubregionId new value for {@code countrySubregionId} attribute.
     */
    public void setCountrySubregionId(Long countrySubregionId) {
        this.countrySubregionId = countrySubregionId;
    }

    // --------------------------------------------------------------------------------------------------- countryRegion

    /**
     * Returns current value of {@code countryRegion} attribute.
     *
     * @return current value of {@code countryRegion} attribute.
     */
    public String getCountryRegion() {
        return countryRegion;
    }

    /**
     * Replaces current value of {@code countryRegion} attribute with the specified value.
     *
     * @param countryRegion new value for {@code countryRegion} attribute.
     */
    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    // ------------------------------------------------------------------------------------------------- countryRegionId

    /**
     * Returns current value of {@code countryRegionId} attribute.
     *
     * @return current value of {@code countryRegionId} attribute.
     */
    public Long getCountryRegionId() {
        return countryRegionId;
    }

    /**
     * Replaces current value of {@code countryRegionId} attribute with the specified value.
     *
     * @param countryRegionId new value for {@code countryRegionId} attribute.
     */
    public void setCountryRegionId(Long countryRegionId) {
        this.countryRegionId = countryRegionId;
    }

    // ---------------------------------------------------------------------------------------------------- countryTotal

    /**
     * Returns current value of {@code countryTotal} attribute.
     *
     * @return current value of {@code countryTotal} attribute.
     */
    public String getCountryTotal() {
        return countryTotal;
    }

    /**
     * Replaces current value of {@code countryTotal} attribute with the specified value.
     *
     * @param countryTotal new value for {@code countryTotal} attribute.
     */
    public void setCountryTotal(String countryTotal) {
        this.countryTotal = countryTotal;
    }

    // -------------------------------------------------------------------------------------------------- countryTotalId

    /**
     * Returns current value of {@code countryTotalId} attribute.
     *
     * @return current value of {@code countryTotalId} attribute.
     */
    public Long getCountryTotalId() {
        return countryTotalId;
    }

    /**
     * Replaces current value of {@code countryTotalId} attribute with the specified value.
     *
     * @param countryTotalId new value for {@code countryTotalId} attribute.
     */
    public void setCountryTotalId(Long countryTotalId) {
        this.countryTotalId = countryTotalId;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = false)
    private Long countryId;

    @Size(max = SIZE_MAX_COUNTRY_ISO_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_ISO_CODE, nullable = false, insertable = true, updatable = false,
            length = COLUMN_LENGTH_COUNTRY_ISO_CODE)
    private String countryIsoCode;

    @Size(max = 40)
    @NotNull
    @Column(name = "COUNTRY_NAME", nullable = false, length = 40)
    private String countryName;

    @Size(max = 30)
    @NotNull
    @Column(name = "COUNTRY_SUBREGION", nullable = false, length = 30)
    private String countrySubregion;

    @NotNull
    @Column(name = "COUNTRY_SUBREGION_ID", nullable = false)
    private Long countrySubregionId;

    @Size(max = 20)
    @NotNull
    @Column(name = "COUNTRY_REGION", nullable = false, length = 20)
    private String countryRegion;

    @NotNull
    @Column(name = "COUNTRY_REGION_ID", nullable = false)
    private Long countryRegionId;

    @Size(max = 11)
    @NotNull
    @Column(name = "COUNTRY_TOTAL", nullable = false, length = 11)
    private String countryTotal;

    @NotNull
    @Column(name = "COUNTRY_TOTAL_ID", nullable = false)
    private Long countryTotalId;
}
