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

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * An abstract class for building instance of a specific subclass of {@link MappedStore} class.
 *
 * @param <SELF>  self type parameter
 * @param <STORE> entity type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedStoreBuilder<
        SELF extends MappedStoreBuilder<SELF, STORE>,
        STORE extends MappedStore
        >
        extends _MappedCoEntityBuilder<SELF, STORE> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for building the specified entity class.
     *
     * @param entityClass the entity class.
     */
    protected MappedStoreBuilder(final Class<STORE> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------------- storeName
    public String storeName() {
        return storeName;
    }

    /**
     * Replaces current value of {@code storeName} property with specified value, and returns this builder.
     *
     * @param storeName the value for the {@code storeName} property.
     * @return this builder.
     */
    public SELF storeName(final String storeName) {
        this.storeName = storeName;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ webAddress
    public String webAddress() {
        return webAddress;
    }

    public SELF webAddress(final String webAddress) {
        this.webAddress = webAddress;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------- physicalAddress
    public String physicalAddress() {
        return physicalAddress;
    }

    public SELF physicalAddress(final String physicalAddress) {
        this.physicalAddress = physicalAddress;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- latitude
    public BigDecimal latitude() {
        return latitude;
    }

    public SELF latitude(final BigDecimal latitude) {
        this.latitude = latitude;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- longitude
    public BigDecimal longitude() {
        return longitude;
    }

    public SELF longitude(final BigDecimal longitude) {
        this.longitude = longitude;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------------ logo
    public byte[] logo() {
        return logo;
    }

    public SELF logo(final byte[] logo) {
        this.logo = logo;
        return (SELF) this;
    }

    // ---------------------------------------------------------------------------------------------------- logoMimeType
    public String logoMimeType() {
        return logoMimeType;
    }

    public SELF logoMimeType(final String logoMimeType) {
        this.logoMimeType = logoMimeType;
        return (SELF) this;
    }

    // ---------------------------------------------------------------------------------------------------- logoFilename
    public String logoFilename() {
        return logoFilename;
    }

    public SELF logoFilename(final String logoFilename) {
        this.logoFilename = logoFilename;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------- logoCharset
    public String logoCharset() {
        return logoCharset;
    }

    public SELF logoCharset(final String logoCharset) {
        this.logoCharset = logoCharset;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------- logoLastUpdated
    public LocalDate logoLastUpdated() {
        return logoLastUpdated;
    }

    public SELF logoLastUpdated(final LocalDate logoLastUpdated) {
        this.logoLastUpdated = logoLastUpdated;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String storeName;

    // -----------------------------------------------------------------------------------------------------------------
    private String webAddress;

    private String physicalAddress;

    // -----------------------------------------------------------------------------------------------------------------
    private BigDecimal latitude;

    private BigDecimal longitude;

    // -----------------------------------------------------------------------------------------------------------------
    private byte[] logo;

    private String logoMimeType;

    private String logoFilename;

    private String logoCharset;

    private LocalDate logoLastUpdated;
}
