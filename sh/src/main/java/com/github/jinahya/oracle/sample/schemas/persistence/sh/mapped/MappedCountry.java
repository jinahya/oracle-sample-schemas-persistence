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

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * An abstract mapped-superclass for mapping the {@value MappedCountry#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedCountry extends _MappedShEntity<Long> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "COUNTRIES";

    // ------------------------------------------------------------------------------------------------------ COUNTRY_ID
    public static final String COLUMN_NAME_COUNTRY_ID = "COUNTRY_ID";

    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    // ------------------------------------------------------------------------------------------------ COUNTRY_ISO_CODE
    public static final String COLUMN_NAME_COUNTRY_ISO_CODE = "COUNTRY_ISO_CODE";

    public static final int COLUMN_LENGTH_COUNTRY_ISO_CODE = 2;

    public static final String ATTRIBUTE_NAME_COUNTRY_ISO_CODE = "countryIsoCode";

    public static final int SIZE_MIN_COUNTRY_ISO_CODE = COLUMN_LENGTH_COUNTRY_ISO_CODE;

    public static final int SIZE_MAX_COUNTRY_ISO_CODE = SIZE_MIN_COUNTRY_ISO_CODE;

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME

    // ----------------------------------------------------------------------------------------------- COUNTRY_SUBREGION

    // -------------------------------------------------------------------------------------------- COUNTRY_SUBREGION_ID

    // -------------------------------------------------------------------------------------------------- COUNTRY_REGION

    // ----------------------------------------------------------------------------------------------- COUNTRY_REGION_ID

    // --------------------------------------------------------------------------------------------------- COUNTRY_TOTAL

    // ------------------------------------------------------------------------------------------------ COUNTRY_TOTAL_ID

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedCountry() {
        super();
    }

    protected MappedCountry(final MappedCountryBuilder<?, ?> builder) {
        super(builder);
        id = Objects.requireNonNull(builder, "builder is null").id();
        countryIsoCode = builder.countryIsoCode();
        countryName = builder.countryName();
        countrySubregion = builder.countrySubregion();
        countrySubregionId = builder.countrySubregionId();
        countryRegion = builder.countryRegion();
        countryRegionId = builder.countryRegionId();
        countryTotal = builder.countryTotal();
        countryTotalId = builder.countryTotalId();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "id=" + id +
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
        if (!(obj instanceof MappedCountry that)) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(id);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(final String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountrySubregion() {
        return countrySubregion;
    }

    public void setCountrySubregion(String countrySubregion) {
        this.countrySubregion = countrySubregion;
    }

    public Long getCountrySubregionId() {
        return countrySubregionId;
    }

    public void setCountrySubregionId(Long countrySubregionId) {
        this.countrySubregionId = countrySubregionId;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public Long getCountryRegionId() {
        return countryRegionId;
    }

    public void setCountryRegionId(Long countryRegionId) {
        this.countryRegionId = countryRegionId;
    }

    public String getCountryTotal() {
        return countryTotal;
    }

    public void setCountryTotal(String countryTotal) {
        this.countryTotal = countryTotal;
    }

    public Long getCountryTotalId() {
        return countryTotalId;
    }

    public void setCountryTotalId(Long countryTotalId) {
        this.countryTotalId = countryTotalId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = false)
    private Long id;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_COUNTRY_ISO_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_ISO_CODE, nullable = false, insertable = true, updatable = false,
            length = COLUMN_LENGTH_COUNTRY_ISO_CODE)
    private String countryIsoCode;

    @Size(max = 40)
    @NotNull
    @Column(name = "COUNTRY_NAME", nullable = false, length = 40)
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = 30)
    @NotNull
    @Column(name = "COUNTRY_SUBREGION", nullable = false, length = 30)
    private String countrySubregion;

    @NotNull
    @Column(name = "COUNTRY_SUBREGION_ID", nullable = false)
    private Long countrySubregionId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = 20)
    @NotNull
    @Column(name = "COUNTRY_REGION", nullable = false, length = 20)
    private String countryRegion;

    @NotNull
    @Column(name = "COUNTRY_REGION_ID", nullable = false)
    private Long countryRegionId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = 11)
    @NotNull
    @Column(name = "COUNTRY_TOTAL", nullable = false, length = 11)
    private String countryTotal;

    @NotNull
    @Column(name = "COUNTRY_TOTAL_ID", nullable = false)
    private Long countryTotalId;
}
