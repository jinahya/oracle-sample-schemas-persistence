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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * A mapped superclass which holds the mappings of the {@value MappedCountry#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedCountry {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "COUNTRIES";

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

    // ------------------------------------------------------------------------------------------------ COUNTRY_ISO_CODE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_ISO_CODE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ISO_CODE = "COUNTRY_ISO_CODE";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_ISO_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_ISO_CODE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ISO_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ISO_CODE = "countryIsoCode";

    /**
     * The minimum size of the {@value #ATTRIBUTE_NAME_COUNTRY_ISO_CODE} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_COUNTRY_ISO_CODE = COLUMN_LENGTH_COUNTRY_ISO_CODE;

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_ISO_CODE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_ISO_CODE = SIZE_MIN_COUNTRY_ISO_CODE;

    // ---------------------------------------------------------------------------------------------------- COUNTRY_NAME

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_NAME = "COUNTRY_NAME";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_NAME = 40;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_NAME = COLUMN_LENGTH_COUNTRY_NAME;

    // ----------------------------------------------------------------------------------------------- COUNTRY_SUBREGION

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_SUBREGION = "COUNTRY_SUBREGION";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_SUBREGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_SUBREGION = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_SUBREGION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_SUBREGION = "countrySubregion";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_SUBREGION = COLUMN_LENGTH_COUNTRY_SUBREGION;

    // -------------------------------------------------------------------------------------------- COUNTRY_SUBREGION_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_SUBREGION_ID = "COUNTRY_SUBREGION_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_SUBREGION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID = "countrySubregionId";

    // -------------------------------------------------------------------------------------------------- COUNTRY_REGION

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_REGION = "COUNTRY_REGION";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_REGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_REGION = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_REGION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION = "countryRegion";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_REGION = COLUMN_LENGTH_COUNTRY_REGION;

    // ----------------------------------------------------------------------------------------------- COUNTRY_REGION_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_REGION_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_REGION_ID = "COUNTRY_REGION_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_REGION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION_ID = "countryRegionId";

    // --------------------------------------------------------------------------------------------------- COUNTRY_TOTAL

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_TOTAL = "COUNTRY_TOTAL";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_TOTAL = 11;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_TOTAL = "countryTotal";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COUNTRY_TOTAL = COLUMN_LENGTH_COUNTRY_TOTAL;

    // ------------------------------------------------------------------------------------------------ COUNTRY_TOTAL_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_TOTAL_ID = "COUNTRY_TOTAL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_TOTAL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_TOTAL_ID = "countryTotalId";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCountry() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
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

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedCountry that)) {
            return false;
        }
        return Objects.equals(getCountryId(), that.getCountryId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getCountryId());
    }

    // ------------------------------------------------------------------------------------------------------- countryId

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
    protected void setCountryId(final Long countryId) {
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
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute with the specified value.
     *
     * @param countryName new value for {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    // ------------------------------------------------------------------------------------------------ countrySubregion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION} attribute.
     */
    public String getCountrySubregion() {
        return countrySubregion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION} attribute with the specified value.
     *
     * @param countrySubregion new value for {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION} attribute.
     */
    public void setCountrySubregion(final String countrySubregion) {
        this.countrySubregion = countrySubregion;
    }

    // ---------------------------------------------------------------------------------------------- countrySubregionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID} attribute.
     */
    public Long getCountrySubregionId() {
        return countrySubregionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID} attribute with the specified value.
     *
     * @param countrySubregionId new value for {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID} attribute.
     */
    public void setCountrySubregionId(final Long countrySubregionId) {
        this.countrySubregionId = countrySubregionId;
    }

    // --------------------------------------------------------------------------------------------------- countryRegion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute.
     */
    public String getCountryRegion() {
        return countryRegion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute with the specified value.
     *
     * @param countryRegion new value for {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute.
     */
    public void setCountryRegion(final String countryRegion) {
        this.countryRegion = countryRegion;
    }

    // ------------------------------------------------------------------------------------------------- countryRegionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_ID} attribute.
     */
    public Long getCountryRegionId() {
        return countryRegionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_ID} attribute with the specified value.
     *
     * @param countryRegionId new value for {@value #ATTRIBUTE_NAME_COUNTRY_REGION_ID} attribute.
     */
    public void setCountryRegionId(final Long countryRegionId) {
        this.countryRegionId = countryRegionId;
    }

    // ---------------------------------------------------------------------------------------------------- countryTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} attribute.
     */
    public String getCountryTotal() {
        return countryTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} attribute with the specified value.
     *
     * @param countryTotal new value for {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} attribute.
     */
    public void setCountryTotal(final String countryTotal) {
        this.countryTotal = countryTotal;
    }

    // -------------------------------------------------------------------------------------------------- countryTotalId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL_ID} attribute.
     */
    public Long getCountryTotalId() {
        return countryTotalId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL_ID} attribute with the specified value.
     *
     * @param countryTotalId new value for {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL_ID} attribute.
     */
    public void setCountryTotalId(final Long countryTotalId) {
        this.countryTotalId = countryTotalId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = true, updatable = false)
    private Long countryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_COUNTRY_ISO_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_ISO_CODE, nullable = false, insertable = true, updatable = false,
            length = COLUMN_LENGTH_COUNTRY_ISO_CODE)
    private String countryIsoCode;

    @Size(max = SIZE_MAX_COUNTRY_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_NAME, nullable = false, length = COLUMN_LENGTH_COUNTRY_NAME)
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_COUNTRY_SUBREGION)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_SUBREGION, nullable = false, length = COLUMN_LENGTH_COUNTRY_SUBREGION)
    private String countrySubregion;

    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_SUBREGION_ID, nullable = false)
    private Long countrySubregionId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_COUNTRY_REGION)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_REGION, nullable = false, length = COLUMN_LENGTH_COUNTRY_REGION)
    private String countryRegion;

    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_REGION_ID, nullable = false)
    private Long countryRegionId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_COUNTRY_TOTAL)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_TOTAL, nullable = false, length = COLUMN_LENGTH_COUNTRY_TOTAL)
    private String countryTotal;

    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_TOTAL_ID, nullable = false)
    private Long countryTotalId;

    // --------------------------------------------------------------------------- countrySubregion / countrySubregionId

    /**
     * Applies the current values of {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION} and
     * {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID} attributes to the specified function, and returns the result.
     *
     * @param function the function to be applied with the current values of {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION}
     *                 and {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID} attributes.
     * @param <T>      result type parameter
     * @return the result of the {@code function}.
     */
    @Transient
    public <T extends MappedCountrySection> T getCountrySubregionSection(
            final BiFunction<? super String, ? super Long, ? extends T> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(getCountrySubregion(), getCountrySubregionId());
    }

    /**
     * Replaces current values of {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION} and
     * {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID} attributes with the {@link MappedCountrySection#getName() name} and
     * the {@link MappedCountrySection#getId() id} of the specified section, or with {@code null}s when the section is
     * {@code null}.
     *
     * @param countrySubregionSection the section whose name and id replace current values of
     *                                {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION} and
     *                                {@value #ATTRIBUTE_NAME_COUNTRY_SUBREGION_ID} attributes; may be {@code null}.
     */
    public void setCountrySubregionSection(final MappedCountrySection countrySubregionSection) {
        setCountrySubregion(
                Optional.ofNullable(countrySubregionSection).map(MappedCountrySection::getName).orElse(null)
        );
        setCountrySubregionId(
                Optional.ofNullable(countrySubregionSection).map(MappedCountrySection::getId).orElse(null)
        );
    }

    // --------------------------------------------------------------------------------- countryRegion / countryRegionId

    /**
     * Applies the current values of {@value #ATTRIBUTE_NAME_COUNTRY_REGION} and
     * {@value #ATTRIBUTE_NAME_COUNTRY_REGION_ID} attributes to the specified function, and returns the result.
     *
     * @param function the function to be applied with the current values of {@value #ATTRIBUTE_NAME_COUNTRY_REGION} and
     *                 {@value #ATTRIBUTE_NAME_COUNTRY_REGION_ID} attributes.
     * @param <T>      result type parameter
     * @return the result of the {@code function}.
     */
    @Transient
    public <T extends MappedCountrySection> T getCountryRegionSection(
            final BiFunction<? super String, ? super Long, ? extends T> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(getCountryRegion(), getCountryRegionId());
    }

    /**
     * Replaces current values of {@value #ATTRIBUTE_NAME_COUNTRY_REGION} and {@value #ATTRIBUTE_NAME_COUNTRY_REGION_ID}
     * attributes with the {@link MappedCountrySection#getName() name} and the {@link MappedCountrySection#getId() id}
     * of the specified section, or with {@code null}s when the section is {@code null}.
     *
     * @param countryRegionSection the section whose name and id replace current values of
     *                             {@value #ATTRIBUTE_NAME_COUNTRY_REGION} and
     *                             {@value #ATTRIBUTE_NAME_COUNTRY_REGION_ID} attributes; may be {@code null}.
     */
    public void setCountryRegionSection(final MappedCountrySection countryRegionSection) {
        setCountryRegion(
                Optional.ofNullable(countryRegionSection).map(MappedCountrySection::getName).orElse(null)
        );
        setCountryRegionId(
                Optional.ofNullable(countryRegionSection).map(MappedCountrySection::getId).orElse(null)
        );
    }

    // ----------------------------------------------------------------------------------- countryTotal / countryTotalId

    /**
     * Applies the current values of {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} and
     * {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL_ID} attributes to the specified function, and returns the result.
     *
     * @param function the function to be applied with the current values of {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} and
     *                 {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL_ID} attributes.
     * @param <T>      result type parameter
     * @return the result of the {@code function}.
     */
    @Transient
    public <T extends MappedCountrySection> T getCountryTotalSection(
            final BiFunction<? super String, ? super Long, ? extends T> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(getCountryTotal(), getCountryTotalId());
    }

    /**
     * Replaces current values of {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} and {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL_ID}
     * attributes with the {@link MappedCountrySection#getName() name} and the {@link MappedCountrySection#getId() id}
     * of the specified section, or with {@code null}s when the section is {@code null}.
     *
     * @param countryTotalSection the section whose name and id replace current values of
     *                            {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL} and {@value #ATTRIBUTE_NAME_COUNTRY_TOTAL_ID}
     *                            attributes; may be {@code null}.
     */
    public void setCountryTotalSection(final MappedCountrySection countryTotalSection) {
        setCountryTotal(
                Optional.ofNullable(countryTotalSection).map(MappedCountrySection::getName).orElse(null)
        );
        setCountryTotalId(
                Optional.ofNullable(countryTotalSection).map(MappedCountrySection::getId).orElse(null)
        );
    }
}
