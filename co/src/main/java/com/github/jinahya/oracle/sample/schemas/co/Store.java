package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = Store.TABLE_NAME)
public class Store extends __MappedEntity<Store, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    static final String TABLE_NAME = "STORES";

    // -------------------------------------------------------------------------------------------------------- STORE_ID

    /**
     * The name of the table column to which the {@link Store_#storeId storeId} maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    // ------------------------------------------------------------------------------------------------------ STORE_NAME
    public static final String COLUMN_NAME_STORE_NAME = "STORE_NAME";

    public static final int COLUMN_LENGTH_STORE_NAME = 255;

    public static final int SIZE_MAX_STORE_NAME = COLUMN_LENGTH_STORE_NAME;

    // ----------------------------------------------------------------------------------------------------- WEB_ADDRESS
    public static final String COLUMN_NAME_WEB_ADDRESS = "WEB_ADDRESS";

    public static final int COLUMN_LENGTH_WEB_ADDRESS = 100;

    public static final int SIZE_MAXH_WEB_ADDRESS = COLUMN_LENGTH_WEB_ADDRESS;

    // ------------------------------------------------------------------------------------------------ PHYSICAL_ADDRESS
    public static final String COLUMN_NAME_PHYSICAL_ADDRESS = "PHYSICAL_ADDRESS";

    public static final int COLUMN_LENGTH_PHYSICAL_ADDRESS = 512;

    public static final int SIZE_MAXH_PHYSICAL_ADDRESS = COLUMN_LENGTH_PHYSICAL_ADDRESS;

    // -------------------------------------------------------------------------------------------------------- LATITUDE
    public static final String COLUMN_NAME_LATITUDE = "LATITUDE";

    public static final int COLUMN_PRECISION_LATITUDE = 9;

    public static final int COLUMN_SCALE_LATITUDE = 6;

    // ------------------------------------------------------------------------------------------------------- LONGITUDE
    public static final String COLUMN_NAME_LONGITUDE = "LONGITUDE";

    public static final int COLUMN_PRECISION_LONGITUDE = 9;

    public static final int COLUMN_SCALE_LONGITUDE = 6;

    // ------------------------------------------------------------------------------------------------------------ LOGO
    public static final String COLUMN_NAME_LOGO = "LOGO";

    // -------------------------------------------------------------------------------------------------- LOGO_MIME_TYPE
    public static final String COLUMN_NAME_LOGO_MIME_TYPE = "LOGO_MIME_TYPE";

    public static final int COLUMN_LENGTH_LOGO_MIME_TYPE = 512;

    public static final int SIZE_MAX_LOGO_MIME_TYPE = COLUMN_LENGTH_LOGO_MIME_TYPE;

    // --------------------------------------------------------------------------------------------------- LOGO_FILENAME
    public static final String COLUMN_NAME_LOGO_FILENAME = "LOGO_FILENAME";

    // ---------------------------------------------------------------------------------------------------- LOGO_CHARSET
    public static final String COLUMN_NAME_LOGO_CHARSET = "LOGO_CHARSET";

    // ----------------------------------------------------------------------------------------------- LOGO_LAST_UPDATED
    public static final String COLUMN_NAMES_LOGO_LAST_UPDATED = "LOGO_LAST_UPDATED";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Store() {
        super();
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
//                ",logo=" + Arrays.toString(logo) +
                ",logoMimeType=" + logoMimeType +
                ",logoFilename=" + logoFilename +
                ",logoCharset=" + logoCharset +
                ",logoLastUpdated=" + logoLastUpdated +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (false && !(obj instanceof Store)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    // ------------------------------------------------------------------------------------------------------ super.id__
    @Override
    protected final Long getId__() {
        return getStoreId();
    }

    @Override
    protected final void setId__(final Long id__) {
        setStoreId(id__);
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
     * Returns current value of {@link Store_#storeId storeId} attribute.
     *
     * @return current value of the {@link Store_#storeId storeId} attribute.
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * Replaces current value of {@link Store_#storeId storeId} attribute with specified value.
     *
     * @param storeId new value for the {@link Store_#storeId storeId} attribute.
     */
    protected void setStoreId(final Long storeId) {
        this.storeId = storeId;
    }

    // ------------------------------------------------------------------------------------------------------- storeName

    /**
     * Returns current value of {@link Store_#storeName storeName} attribute.
     *
     * @return current value of the {@link Store_#storeName storeName} attribute.
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Replaces current value of {@link Store_#storeName storeName} attribute with specified value.
     *
     * @param storeName new value for the {@link Store_#storeName storeName} attribute.
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
    @DecimalMax(value = _DomainConstants.DECIMAL_MAX_LATITUDE, inclusive = true)
    @DecimalMin(value = _DomainConstants.DECIMAL_MIN_LATITUDE, inclusive = true)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATITUDE, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_LATITUDE, scale = COLUMN_SCALE_LATITUDE)
    private BigDecimal latitude;

    @Nullable
    @DecimalMax(value = _DomainConstants.DECIMAL_MAX_LONGITUDE, inclusive = true)
    @DecimalMin(value = _DomainConstants.DECIMAL_MIN_LONGITUDE, inclusive = true)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LONGITUDE, nullable = true, insertable = true, updatable = true, precision = 9,
            scale = 6)
    private BigDecimal longitude;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Lob
    @Basic(optional = true)
    @Column(name = "LOGO", nullable = true, insertable = true, updatable = true)
    private byte[] logo;

    @Nullable
    @Size(max = SIZE_MAX_LOGO_MIME_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGO_MIME_TYPE, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_LOGO_MIME_TYPE)
    private String logoMimeType;

    @Nullable
    @Size(max = 512)
    @Basic(optional = true)
    @Column(name = "LOGO_FILENAME", nullable = true, insertable = true, updatable = true)
    private String logoFilename;

    @Nullable
    @Size(max = 512)
    @Basic(optional = true)
    @Column(name = "LOGO_CHARSET", nullable = true, insertable = true, updatable = true)
    private String logoCharset;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAMES_LOGO_LAST_UPDATED, nullable = true, insertable = true, updatable = true)
    private LocalDate logoLastUpdated;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Resets {@link Store_#logo logo} attribute, {@link Store_#logoMimeType logoMimeType} attribute,
     * {@link Store_#logoFilename logoFilename} attribute, and {@link Store_#logoCharset logoCharset} attribute, while
     * updating {@link Store_#logoLastUpdated logoLastUpdated} attribute with {@link LocalDate#now() now}.
     */
    public void removeLogo() {
        setLogo(null);
        setLogoMimeType(null);
        setLogoCharset(null);
        setLogoLastUpdated(LocalDate.now());
    }

    /**
     * Updates logo information from the specified file.
     *
     * @param path    the path to the file to read.
     * @param charset a value for {@link Store#logoCharset logoCharset} attribute.
     * @param options an array of options specifying how symbolic links are handled for the search.
     * @throws IOException              if an I/O error occurs.
     * @throws IllegalArgumentException if {@code path} is not a regular file checked with {@code options}.
     */
    public void setLogoFromFile(@Nonnull final Path path, @Nullable final String charset,
                                @Nonnull final LinkOption... options)
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
        setLogoCharset(charset);
        setLogoLastUpdated(LocalDate.now());
    }

    public void setLogoFromFile(@Nonnull final Path path, @Nonnull final LinkOption... options) throws IOException {
        setLogoFromFile(path, null, options);
    }
}
