package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * An entity class maps to {@value Store#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(
        name = "Store.findAllByStoreNameLike",
        query = """
                SELECT e
                FROM Store AS e
                WHERE e.storeName LIKE :storeNamePattern"""
)
@NamedQuery(
        name = "Store.findByStoreName",
        query = """
                SELECT e
                FROM Store AS e
                WHERE e.storeName = :storeName"""
)
@Entity
@Table(name = Store.TABLE_NAME)
public class Store extends __MappedEntity<Store, Long> {

    @Serial
    private static final long serialVersionUID = 5174571093186516676L;

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

    // ----------------------------------------------------------------------------------------------------- WEB_ADDRESS
    public static final String COLUMN_NAME_WEB_ADDRESS = "WEB_ADDRESS";

    // ------------------------------------------------------------------------------------------------ PHYSICAL_ADDRESS
    public static final String COLUMN_NAME_PHYSICAL_ADDRESS = "PHYSICAL_ADDRESS";

    // -------------------------------------------------------------------------------------------------------- LATITUDE
    public static final String COLUMN_NAME_LATITUDE = "LATITUDE";

    // ------------------------------------------------------------------------------------------------------- LONGITUDE
    public static final String COLUMN_NAME_LONGITUDE = "LONGITUDE";

    // ------------------------------------------------------------------------------------------------------------ LOGO
    public static final String COLUMN_NAME_LOGO = "LOGO";

    // -------------------------------------------------------------------------------------------------- LOGO_MIME_TYPE
    public static final String COLUMN_NAME_LOGO_MIME_TYPE = "LOGO_MIME_TYPE";

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
                ",logoMimeType=" + logoMimeType +
                ",logoFilename=" + logoFilename +
                ",logoCharset=" + logoCharset +
                ",logoLastUpdated=" + logoLastUpdated +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Long _id_() {
        return getStoreId();
    }

    @Override
    protected final void _id_(final Long _id_) {
        setStoreId(_id_);
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
    @Size(max = 255)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_NAME, nullable = false, insertable = true, updatable = true, unique = true)
    private String storeName;

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nullable
    @Size(max = 100)
    @Basic(optional = true)
    @Column(name = "WEB_ADDRESS", nullable = true, insertable = true, updatable = true)
    private String webAddress;

    @jakarta.annotation.Nullable
    @Size(max = 512)
    @Basic(optional = true)
    @Column(name = "PHYSICAL_ADDRESS", nullable = true, insertable = true, updatable = true)
    private String physicalAddress;

    @jakarta.annotation.Nullable
    @DecimalMax(value = "+090.0000000", inclusive = true)
    @DecimalMin(value = "-090.0000000", inclusive = true)
    @Basic(optional = true)
    @Column(name = "LATITUDE", nullable = true, insertable = true, updatable = true, precision = 9, scale = 6)
    private BigDecimal latitude;

    @jakarta.annotation.Nullable
    @DecimalMax(value = "+180.0000000", inclusive = true)
    @DecimalMin(value = "-180.0000000", inclusive = true)
    @Basic(optional = true)
    @Column(name = "LONGITUDE", nullable = true, insertable = true, updatable = true, precision = 9, scale = 6)
    private BigDecimal longitude;

    @jakarta.annotation.Nullable
    @Lob
    @Basic(optional = true)
    @Column(name = "LOGO", nullable = true, insertable = true, updatable = true)
    private byte[] logo;

    @jakarta.annotation.Nullable
    @Size(max = 512)
    @Basic(optional = true)
    @Column(name = "LOGO_MIME_TYPE", nullable = true, insertable = true, updatable = true)
    private String logoMimeType;

    @jakarta.annotation.Nullable
    @Size(max = 512)
    @Basic(optional = true)
    @Column(name = "LOGO_FILENAME", nullable = true, insertable = true, updatable = true)
    private String logoFilename;

    @jakarta.annotation.Nullable
    @Size(max = 512)
    @Basic(optional = true)
    @Column(name = "LOGO_CHARSET", nullable = true, insertable = true, updatable = true)
    private String logoCharset;

    @jakarta.annotation.Nullable
    @Basic(optional = true)
    @Column(name = "LOGO_LAST_UPDATED", nullable = true, insertable = true, updatable = true)
    private LocalDate logoLastUpdated;
}