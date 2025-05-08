package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = Product.TABLE_NAME)
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 7545920963762942919L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "PRODUCTS";

    // ------------------------------------------------------------------------------------------------------ PRODUCT_ID
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    // ---------------------------------------------------------------------------------------------------- PRODUCT_NAME
    public static final String COLUMN_NAME_PRODUCT_NAME = "PRODUCT_NAME";

    // ------------------------------------------------------------------------------------------------------ UNIT_PRICE
    public static final String COLUMN_NAME_UNIT_PRICE = "UNIT_PRICE";

    // ------------------------------------------------------------------------------------------------- PRODUCT_DETAILS
    public static final String COLUMN_NAME_PRODUCT_DETAILS = "PRODUCT_DETAILS";

    // --------------------------------------------------------------------------------------------------- PRODUCT_IMAGE
    public static final String COLUMN_NAME_PRODUCT_IMAGE = "PRODUCT_IMAGE";

    // ------------------------------------------------------------------------------------------------- IMAGE_MIME_TYPE
    public static final String COLUMN_NAME_IMAGE_MIME_TYPE = "IMAGE_MIME_TYPE";

    // -------------------------------------------------------------------------------------------------- IMAGE_FILENAME
    public static final String COLUMN_NAME_IMAGE_FILENAME = "IMAGE_FILENAME";

    // --------------------------------------------------------------------------------------------------- IMAGE_CHARSET
    public static final String COLUMN_NAME_IMAGE_CHARSET = "IMAGE_CHARSET";

    // ---------------------------------------------------------------------------------------------- IMAGE_LAST_UPDATED
    public static final String COLUMN_NAME_IMAGE_LAST_UPDATED = "IMAGE_LAST_UPDATED";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public Product() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "productId=" + productId +
                ",productName=" + productName +
                ",unitPrice=" + unitPrice +
                ",productDetails=" + Objects.hashCode(productDetails) +
                ",productImage=" + Objects.hashCode(productImage) +
                ",imageMimeType=" + imageMimeType +
                ",imageFilename=" + imageFilename +
                ",imageCharset=" + imageCharset +
                ",imageLastUpdated=" + imageLastUpdated +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Product)) {
            return false;
        }
        return Objects.equals(
                getProductId(),
                ((Product) obj).getProductId()
        );
    }

    @Override
    public final int hashCode() {
        return Objects.hash(
                getProductId()
        );
    }

    // ------------------------------------------------------------------------------------------------------- productId
    public Long getProductId() {
        return productId;
    }

    // ----------------------------------------------------------------------------------------------------- productName
    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice
    @Nullable
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(@Nullable final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -------------------------------------------------------------------------------------------------- productDetails
    @Nullable
    public byte[] getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(@Nullable final byte[] productDetails) {
        this.productDetails = productDetails;
    }

    // ---------------------------------------------------------------------------------------------------- productImage
    @Nullable
    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(@Nullable final byte[] productImage) {
        this.productImage = productImage;
    }

    // --------------------------------------------------------------------------------------------------- imageMimeType
    @Nullable
    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(@Nullable final String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    // --------------------------------------------------------------------------------------------------- imageFilename
    @Nullable
    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(@Nullable final String imageFilename) {
        this.imageFilename = imageFilename;
    }

    // ---------------------------------------------------------------------------------------------------- imageCharset
    @Nullable
    public String getImageCharset() {
        return imageCharset;
    }

    public void setImageCharset(@Nullable final String imageCharset) {
        this.imageCharset = imageCharset;
    }

    // ------------------------------------------------------------------------------------------------ imageLastUpdated
    @Nullable
    public LocalDate getImageLastUpdated() {
        return imageLastUpdated;
    }

    public void setImageLastUpdated(@Nullable final LocalDate imageLastUpdated) {
        this.imageLastUpdated = imageLastUpdated;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false)
    private Long productId;

    @Size(max = 255)
    @NotNull
    @Column(name = COLUMN_NAME_PRODUCT_NAME, nullable = false, insertable = true, updatable = true, length = 255)
    private String productName;

    @jakarta.annotation.Nullable
    @DecimalMax(value = "99999999.99", inclusive = true)
    @DecimalMin(value = "00000000.00", inclusive = true)
    @PositiveOrZero // redundant?
    @Basic(optional = true)
    @Column(name = "UNIT_PRICE", nullable = true, insertable = true, updatable = true, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @jakarta.annotation.Nullable
    @Lob
    @Basic(optional = true)
    @Column(name = "PRODUCT_DETAILS", nullable = true, insertable = true, updatable = true)
    private byte[] productDetails;

    @jakarta.annotation.Nullable
    @Lob
    @Basic(optional = true)
    @Column(name = "PRODUCT_IMAGE", nullable = true, insertable = true, updatable = true)
    private byte[] productImage;

    @jakarta.annotation.Nullable
    @Size(max = 512)
    @Basic(optional = true)
    @Column(name = "IMAGE_MIME_TYPE", nullable = true, insertable = true, updatable = true, length = 512)
    private String imageMimeType;

    @jakarta.annotation.Nullable
    @Size(max = 512)
    @Basic(optional = true)
    @Column(name = "IMAGE_FILENAME", nullable = true, insertable = true, updatable = true, length = 512)
    private String imageFilename;

    @jakarta.annotation.Nullable
    @Size(max = 512)
    @Basic(optional = true)
    @Column(name = "IMAGE_CHARSET", nullable = true, insertable = true, updatable = true, length = 512)
    private String imageCharset;

    @jakarta.annotation.Nullable
    @Basic(optional = true)
    @Column(name = "IMAGE_LAST_UPDATED", insertable = true, updatable = true)
    private LocalDate imageLastUpdated;

    // -----------------------------------------------------------------------------------------------------------------
    public void setImage(@Nullable final Path path, final LinkOption... options) throws IOException {
        if (path == null) {
            setProductImage(null);
            setImageMimeType(null);
            setImageFilename(null);
            setImageCharset(null);
            setImageLastUpdated(LocalDate.now());
            return;
        }
        if (!Files.isRegularFile(path, options)) {
            throw new IllegalArgumentException("not a regular file: " + path);
        }
        setProductImage(Files.readAllBytes(path));
        setImageMimeType(Files.probeContentType(path));
        setImageFilename(path.getFileName().toString());
        setImageCharset(null);
        setImageLastUpdated(LocalDate.now());
    }
}