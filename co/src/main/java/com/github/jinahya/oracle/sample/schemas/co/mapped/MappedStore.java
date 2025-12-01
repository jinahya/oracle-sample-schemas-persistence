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
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
public abstract class MappedStore extends _MappedCoEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "STORES";

    // ---------------------------------------------------------------------------------------------- STORE_ID / storeId
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    // ------------------------------------------------------------------------------------------ STORE_NAME / storeName
    public static final String COLUMN_NAME_STORE_NAME = "STORE_NAME";

    public static final int COLUMN_LENGTH_STORE_NAME = 255;

    public static final int SIZE_MAX_STORE_NAME = COLUMN_LENGTH_STORE_NAME;

    public static final String ATTRIBUTE_NAME_STORE_NAME = "storeName";

    // ---------------------------------------------------------------------------------------- WEB_ADDRESS / webAddress
    public static final String COLUMN_NAME_WEB_ADDRESS = "WEB_ADDRESS";

    public static final int COLUMN_LENGTH_WEB_ADDRESS = 100;

    public static final int SIZE_MAXH_WEB_ADDRESS = COLUMN_LENGTH_WEB_ADDRESS;

    public static final String ATTRIBUTE_NAME_WEB_ADDRESS = "webAddress";

    // ------------------------------------------------------------------------------ PHYSICAL_ADDRESS / physicalAddress
    public static final String COLUMN_NAME_PHYSICAL_ADDRESS = "PHYSICAL_ADDRESS";

    public static final int COLUMN_LENGTH_PHYSICAL_ADDRESS = 512;

    public static final int SIZE_MAXH_PHYSICAL_ADDRESS = COLUMN_LENGTH_PHYSICAL_ADDRESS;

    public static final String ATTRIBUTE_NAME_PHYSICAL_ADDRESS = "physicalAddress";

    // --------------------------------------------------------------------------------------------- LATITUDE / latitude
    public static final String COLUMN_NAME_LATITUDE = "LATITUDE";

    public static final int COLUMN_PRECISION_LATITUDE = 9;

    public static final int COLUMN_SCALE_LATITUDE = 6;

    public static final String ATTRIBUTE_NAME_LATITUDE = "latitude";

    // ------------------------------------------------------------------------------------------- LONGITUDE / longitude
    public static final String COLUMN_NAME_LONGITUDE = "LONGITUDE";

    public static final int COLUMN_PRECISION_LONGITUDE = 9;

    public static final int COLUMN_SCALE_LONGITUDE = 6;

    public static final String ATTRIBUTE_NAME_LONGITUDE = "longitude";

    // ----------------------------------------------------------------------------------------------------- LOGO / logo
    public static final String COLUMN_NAME_LOGO = "LOGO";

    public static final String ATTRIBUTE_NAME_LOGO = "logo";

    // ----------------------------------------------------------------------------------- LOGO_MIME_TYPE / logoMimeType
    public static final String COLUMN_NAME_LOGO_MIME_TYPE = "LOGO_MIME_TYPE";

    public static final int COLUMN_LENGTH_LOGO_MIME_TYPE = 512;

    public static final String ATTRIBUTE_NAME_LOGO_MIME_TYPE = "logoMimeType";

    public static final int SIZE_MAX_LOGO_MIME_TYPE = COLUMN_LENGTH_LOGO_MIME_TYPE;

    // ------------------------------------------------------------------------------------ LOGO_FILENAME / logoFilename
    public static final String COLUMN_NAME_LOGO_FILENAME = "LOGO_FILENAME";

    public static final int COLUMN_LENGTH_LOGO_FILENAME = 512;

    public static final String ATTRIBUTE_NAME_LOGO_FILENAME = "logoFilename";

    public static final int SIZE_MAX_LOGO_FILENAME = COLUMN_LENGTH_LOGO_FILENAME;

    // -------------------------------------------------------------------------------------- LOGO_CHARSET / logoCharset
    public static final String COLUMN_NAME_LOGO_CHARSET = "LOGO_CHARSET";

    public static final int COLUMN_LENGTH_LOGO_CHARSET = 512;

    public static final String ATTRIBUTE_NAME_LOGO_CHARSET = "logoCharset";

    public static final int SIZE_MAX_LOGO_CHARSET = COLUMN_LENGTH_LOGO_CHARSET;

    // ----------------------------------------------------------------------------- LOGO_LAST_UPDATED / logoLastUpdated
    public static final String COLUMN_NAME_LOGO_LAST_UPDATED = "LOGO_LAST_UPDATED";

    public static final int COLUMN_LENGTH_LOGO_LAST_UPDATED = 512;

    public static final String ATTRIBUTE_NAME_LOGO_LAST_UPDATED = "logoLastUpdated";

    public static final int SIZE_MAX_LOGO_LAST_UPDATED = COLUMN_LENGTH_LOGO_LAST_UPDATED;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedStore() {
        super();
    }

    /**
     * Creates a new instance built from the specified builder.
     *
     * @param builder the builder to build from.
     */
    protected MappedStore(@Nonnull final MappedStoreBuilder<?, ?> builder) {
        super(builder);
        storeName = builder.storeName();
        webAddress = builder.webAddress();
        physicalAddress = builder.physicalAddress();
        latitude = builder.latitude();
        longitude = builder.longitude();
        logo = builder.logo();
        logoMimeType = builder.logoMimeType();
        logoFilename = builder.logoFilename();
        logoCharset = builder.logoCharset();
        logoLastUpdated = builder.logoLastUpdated();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
               "storeId=" + storeId +
               ",storeName=" + storeName +
               ",webAddress=" + webAddress +
               ",physicalAddress=" + physicalAddress +
               ",latitude=" + latitude +
               ",longitude=" + longitude +
//                ",logo=" + Arrays.toString(logo) + // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
               ",logoMimeType=" + logoMimeType +
               ",logoFilename=" + logoFilename +
               ",logoCharset=" + logoCharset +
               ",logoLastUpdated=" + logoLastUpdated +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedStore that)) {
            return false;
        }
        return Objects.equals(storeName, that.storeName);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(storeName);
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation
    // constraint STORE_AT_LEAST_ONE_ADDRESS_C
    //        check (web_address IS NOT NULL or physical_address IS NOT NULL)
    @AssertTrue(message = "either webAddress or physicalAddress must be specified")
    private boolean isEitherWebAddressOrPhysicalAddressSpecified() {
        return webAddress != null || physicalAddress != null;
    }

    // --------------------------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@link MappedStore_#storeId storeId} attribute.
     *
     * @return current value of the {@link MappedStore_#storeId storeId} attribute.
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * Replaces current value of {@link MappedStore_#storeId storeId} attribute with specified value.
     *
     * @param storeId new value for the {@link MappedStore_#storeId storeId} attribute.
     */
    protected void setStoreId(final Long storeId) {
        this.storeId = storeId;
    }

    // ------------------------------------------------------------------------------------------------------- storeName

    /**
     * Returns current value of {@link MappedStore_#storeName storeName} attribute.
     *
     * @return current value of the {@link MappedStore_#storeName storeName} attribute.
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Replaces current value of {@link MappedStore_#storeName storeName} attribute with specified value.
     *
     * @param storeName new value for the {@link MappedStore_#storeName storeName} attribute.
     */
    public void setStoreName(final String storeName) {
        this.storeName = storeName;
    }

    // ------------------------------------------------------------------------------------------------------ webAddress
    @Nullable
    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(@Nullable final String webAddress) {
        this.webAddress = webAddress;
    }

    // ------------------------------------------------------------------------------------------------- physicalAddress
    @Nullable
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(@Nullable final String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    // -------------------------------------------------------------------------------------------------------- latitude
    @Nullable
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(@Nullable final BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns current value of {@link #getLatitude() latitude} attribute as a {@code double} value.
     *
     * @return current value of {@link #getLatitude() latitude} attribute as a {@code double} value.
     * @see #getLatitude()
     * @see BigDecimal#doubleValue()
     */
    @Nullable
    @Transient
    public Double getLatitudeAsDouble() {
        return Optional.ofNullable(getLatitude())
                .map(BigDecimal::doubleValue)
                .orElse(null);
    }

    /**
     * Replaces current value of {@link MappedStore_#latitude latitude} attribute with specified value.
     *
     * @param latitude     new value for the {@link #setLatitude(BigDecimal) latitude} attribute.
     * @param roundingMode a rounding mode.
     * @see BigDecimal#valueOf(double)
     * @see BigDecimal#setScale(int, RoundingMode)
     * @see #setLatitude(BigDecimal)
     */
    public void setLatitudeFromDouble(@Nullable final Double latitude, @Nullable final RoundingMode roundingMode) {
        setLatitude(
                Optional.ofNullable(latitude)
                        .map(BigDecimal::valueOf)
                        .map(v -> roundingMode == null ? v : v.setScale(COLUMN_SCALE_LATITUDE, roundingMode))
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------- longitude
    @Nullable
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(@Nullable final BigDecimal longitude) {
        this.longitude = longitude;
    }

    // ------------------------------------------------------------------------------------------------------------ logo
    @Nullable
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(@Nullable final byte[] logo) {
        this.logo = logo;
    }

    // ---------------------------------------------------------------------------------------------------- logoMimeType
    @Nullable
    public String getLogoMimeType() {
        return logoMimeType;
    }

    public void setLogoMimeType(@Nullable final String logoMimeType) {
        this.logoMimeType = logoMimeType;
    }

    // ---------------------------------------------------------------------------------------------------- logoFilename
    @Nullable
    public String getLogoFilename() {
        return logoFilename;
    }

    public void setLogoFilename(@Nullable final String logoFilename) {
        this.logoFilename = logoFilename;
    }

    // ----------------------------------------------------------------------------------------------------- logoCharset
    @Nullable
    public String getLogoCharset() {
        return logoCharset;
    }

    public void setLogoCharset(@Nullable final String logoCharset) {
        this.logoCharset = logoCharset;
    }

    // ------------------------------------------------------------------------------------------------- logoLastUpdated
    @Nullable
    public LocalDate getLogoLastUpdated() {
        return logoLastUpdated;
    }

    public void setLogoLastUpdated(@Nullable final LocalDate logoLastUpdated) {
        this.logoLastUpdated = logoLastUpdated;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false,
//            insertable = false,
            insertable = true, // EclipseLink
            updatable = false)
    private Long storeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_STORE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_STORE_NAME, unique = true)
    private String storeName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = SIZE_MAXH_WEB_ADDRESS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WEB_ADDRESS, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_WEB_ADDRESS)
    private String webAddress;

    @Nullable
    @Size(max = SIZE_MAXH_PHYSICAL_ADDRESS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PHYSICAL_ADDRESS, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_PHYSICAL_ADDRESS)
    private String physicalAddress;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @DecimalMax(value = __MappedCoConstants.DECIMAL_MAX_LATITUDE, inclusive = true)
    @DecimalMin(value = __MappedCoConstants.DECIMAL_MIN_LATITUDE, inclusive = true)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATITUDE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LATITUDE,
            scale = COLUMN_SCALE_LATITUDE
    )
    private BigDecimal latitude;

    @Nullable
    @DecimalMax(value = __MappedCoConstants.DECIMAL_MAX_LONGITUDE, inclusive = true)
    @DecimalMin(value = __MappedCoConstants.DECIMAL_MIN_LONGITUDE, inclusive = true)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LONGITUDE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LONGITUDE,
            scale = COLUMN_SCALE_LONGITUDE
    )
    private BigDecimal longitude;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Lob
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Column(name = COLUMN_NAME_LOGO, nullable = true, insertable = true, updatable = true)
    private byte[] logo;

    @Nullable
    @Size(max = SIZE_MAX_LOGO_MIME_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGO_MIME_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LOGO_MIME_TYPE
    )
    private String logoMimeType;

    @Nullable
    @Size(max = SIZE_MAX_LOGO_FILENAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGO_FILENAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LOGO_FILENAME
    )
    private String logoFilename;

    @Nullable
    @Size(max = SIZE_MAX_LOGO_CHARSET)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGO_CHARSET,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LOGO_CHARSET
    )
    private String logoCharset;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGO_LAST_UPDATED, nullable = true, insertable = true, updatable = true)
    private LocalDate logoLastUpdated;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Resets {@link MappedStore_#logo logo} attribute, {@link MappedStore_#logoMimeType logoMimeType} attribute,
     * {@link MappedStore_#logoFilename logoFilename} attribute, and {@link MappedStore_#logoCharset logoCharset}
     * attribute, while updating {@link MappedStore_#logoLastUpdated logoLastUpdated} attribute with
     * {@link LocalDate#now() now}.
     */
    public void resetLogo() {
        setLogo(null);
        setLogoMimeType(null);
        setLogoCharset(null);
        setLogoLastUpdated(LocalDate.now());
    }

    /**
     * Updates logo information from the specified file.
     *
     * @param path the path to the file to read.
     * @throws IOException if an I/O error occurs.
     * @see Files#probeContentType(Path)
     */
    public void setLogoFromFile(@Nonnull final Path path) throws IOException {
        Objects.requireNonNull(path, "path is null");
        setLogo(Files.readAllBytes(path));
        setLogoMimeType(Files.probeContentType(path));
        setLogoLastUpdated(LocalDate.now());
    }

    /**
     * Updates logo information from the specified file.
     *
     * @param path    the path to the file to read.
     * @param options an array of options specifying how symbolic links are handled for the search.
     * @throws IOException              if an I/O error occurs.
     * @throws IllegalArgumentException if {@code path} is not a regular file checked with {@code options}.
     * @see Files#probeContentType(Path)
     */
    public void setLogoFromFile(@Nonnull final Path path, @Nonnull final LinkOption... options)
            throws IOException {
        Objects.requireNonNull(path, "path is null");
        Objects.requireNonNull(options, "options is null");
        if (!Files.isRegularFile(path, options)) {
            throw new IllegalArgumentException(
                    "not a regular file; path: " + path + ", options: " + Arrays.toString(options)
            );
        }
        setLogo(Files.readAllBytes(path));
        setLogoMimeType(Files.probeContentType(path));
        setLogoLastUpdated(LocalDate.now());
    }

    public void setLogoFrom(@Nullable final _MappedCoBinary coBinary) {
        setLogo(
                Optional.ofNullable(coBinary)
                        .map(_MappedCoBinary::getBytes)
                        .orElse(null)
        );
        setLogoMimeType(
                Optional.ofNullable(coBinary)
                        .map(_MappedCoBinary::getMimeType)
                        .orElse(null)
        );
        setLogoFilename(
                Optional.ofNullable(coBinary)
                        .map(_MappedCoBinary::getFilename)
                        .orElse(null)
        );
        setLogoCharset(
                Optional.ofNullable(coBinary)
                        .map(_MappedCoBinary::getCharset)
                        .orElse(null)
        );
        setLogoLastUpdated(
                Optional.ofNullable(coBinary)
                        .map(_MappedCoBinary::getLastUpdated)
                        .orElseGet(LocalDate::now)
        );
    }
}
