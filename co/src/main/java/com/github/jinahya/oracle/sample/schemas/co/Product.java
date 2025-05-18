package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.IOException;
import java.io.Serial;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;

/**
 * An entity class for mapping {@value Product#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProductDetails
 */
@Entity
@Table(name = Product.TABLE_NAME)
public class Product extends __MappedEntity<Product, Long> {

    @Serial
    private static final long serialVersionUID = 7545920963762942919L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PRODUCTS";

    // ------------------------------------------------------------------------------------------------------ PRODUCT_ID

    /**
     * The name of the table column to which the {@link Product_#productId productId} attribute maps. The value is
     * {@value}.
     */
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
//                ",productDetails=" + Objects.hashCode(productDetails) +
//                ",productImage=" + Objects.hashCode(productImage) +
                ",imageMimeType=" + imageMimeType +
                ",imageFilename=" + imageFilename +
                ",imageCharset=" + imageCharset +
                ",imageLastUpdated=" + imageLastUpdated +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Long _id_() {
        return getProductId();
    }

    @Override
    protected final void _id_(final Long _id_) {
        setProductId(_id_);
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@link Product_#productId productId} attribute.
     *
     * @return current value of the {@link Product_#productId productId} attribute.
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@link Product_#productId productId} attribute with specified value.
     *
     * @param productId new value for the {@link Product_#productId productId} attribute.
     */
    protected void setProductId(final Long productId) {
        this.productId = productId;
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

//    /**
//     * Returns current value of {@link Product_#productDetails productDetails} attribute as a {@link JsonNode}
//     *
//     * @param objectReader an object reader for deserializing the value of
//     *                     {@link Product_#productDetails productDetails} attribute.
//     * @return current value of {@link Product_#productDetails productDetails} attribute deserialized as a
//     * {@link JsonNode}.
//     * @see ObjectReader#readTree(byte[])
//     */
//    @Nullable
//    public JsonNode getProductDetailsAsJsonNode(final ObjectReader objectReader) {
//        Objects.requireNonNull(objectReader, "objectReader is null");
//        return Optional.ofNullable(getProductDetails())
//                .map(pd -> {
//                    try {
//                        return objectReader.readTree(pd);
//                    } catch (final IOException ioe) {
//                        throw new RuntimeException("failed to read tree from current value of productImage", ioe);
//                    }
//                })
//                .orElse(null);
//    }
//
//    /**
//     * Replaces current value of {@link Product_#productDetails productDetails} attribute with specified value.
//     *
//     * @param objectWriter       an object writer for serializing the specified value.
//     * @param productDetailsNode the value to be set.
//     * @see #getProductDetailsAsJsonNode(ObjectReader)
//     * @see ObjectWriter#writeValueAsBytes(Object)
//     * @see #setProductDetails(byte[])
//     */
//    public void setProductDetailsAsJsonNode(final ObjectWriter objectWriter,
//                                            @Nullable final JsonNode productDetailsNode) {
//        Objects.requireNonNull(objectWriter, "objectWriter is null");
//        setProductDetails(
//                Optional.ofNullable(productDetailsNode)
//                        .map(pdn -> {
//                            try {
//                                return objectWriter.writeValueAsBytes(pdn);
//                            } catch (final JsonProcessingException jpe) {
//                                throw new RuntimeException("failed to write " + productDetailsNode + " as bytes", jpe);
//                            }
//                        })
//                        .orElse(null)
//        );
//    }
//
//    /**
//     * Returns current value of {@link Product_#productDetails productDetails} attribute as a {@link ObjectNode}.
//     *
//     * @param objectReader an object reader for deserializing the value of
//     *                     {@link Product_#productDetails productDetails} attribute.
//     * @return current value of {@link Product_#productDetails productDetails} attribute deserialized as a
//     * {@link ObjectNode}.
//     * @see #getProductDetailsAsJsonNode(ObjectReader)
//     * @see JsonNode#isObject()
//     */
//    @Nullable
//    public ObjectNode getProductDetailsAsObjectNode(final ObjectReader objectReader) {
//        Objects.requireNonNull(objectReader, "objectReader is null");
//        if (false) {
//            return Optional.ofNullable(getProductDetailsAsJsonNode(objectReader))
//                    .map(jn -> {
//                        assert jn.isObject();
//                        return (ObjectNode) jn;
//                    })
//                    .orElse(null);
//        }
//        // TODO: implement
//        throw new UnsupportedOperationException("not yet implemented");
//    }
//
//    /**
//     * Replaces current value of {@link Product_#productDetails productDetails} attribute with specified value.
//     *
//     * @param objectWriter       an object writer for serializing the specified value.
//     * @param productDetailsNode the value to be set.
//     * @see #setProductDetailsAsJsonNode(ObjectWriter, JsonNode)
//     */
//    public void setProductDetailsAsObjectNode(final ObjectWriter objectWriter,
//                                              @Nullable final ObjectNode productDetailsNode) {
//        Objects.requireNonNull(objectWriter, "objectWriter is null");
//        if (false) {
//            setProductDetailsAsJsonNode(objectWriter, productDetailsNode);
//        }
//        // TODO: implement
//        throw new UnsupportedOperationException("not yet implemented");
//    }
//
//    /**
//     * Returns current value of {@link Product_#productDetails productDetails} attribute as a {@link ProductDetails}
//     * object.
//     *
//     * @param objectMapper an object mapper for deserializing the value of
//     *                     {@link Product_#productDetails productDetails} attribute.
//     * @return current value of {@link Product_#productDetails productDetails} attribute deserialized as a
//     * {@link ProductDetails} object.
//     * @see ObjectMapper#reader()
//     * @see #getProductDetailsAsObjectNode(ObjectReader)
//     * @see ObjectMapper#treeToValue(TreeNode, Class)
//     */
//    @Nullable
//    public ProductDetails getProductDetailsAsMapped(final ObjectMapper objectMapper) {
//        Objects.requireNonNull(objectMapper, "objectMapper is null");
//        if (false) {
//            return Optional.ofNullable(getProductDetailsAsObjectNode(objectMapper.reader()))
//                    .map(on -> {
//                        try {
//                            return objectMapper.treeToValue(on, ProductDetails.class);
//                        } catch (final JsonProcessingException jpe) {
//                            throw new RuntimeException("failed to deserialize productDetails node", jpe);
//                        }
//                    })
//                    .orElse(null);
//        }
//        // TODO: implement
//        throw new UnsupportedOperationException("not yet implemented");
//    }
//
//    /**
//     * Replaces current value of {@link Product_#productDetails productDetails} attribute with specified value.
//     *
//     * @param objectMapper         an object mapper for serializing the specified value.
//     * @param productDetailsObject the value to be set.
//     * @see ObjectMapper#writer()
//     * @see ObjectMapper#valueToTree(Object)
//     * @see #setProductDetailsAsObjectNode(ObjectWriter, ObjectNode)
//     */
//    public void setProductDetailsAsMapped(final ObjectMapper objectMapper,
//                                          @Nullable final ProductDetails productDetailsObject) {
//        Objects.requireNonNull(objectMapper, "objectMapper is null");
//        if (false) {
//            setProductDetailsAsObjectNode(
//                    objectMapper.writer(),
//                    Optional.ofNullable(productDetailsObject)
//                            .<ObjectNode>map(pdm -> objectMapper.valueToTree(productDetailsObject))
//                            .orElse(null)
//            );
//        }
//        // TODO: implement
//        throw new UnsupportedOperationException("not yet implemented");
//    }

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

    @Transient
    @Nullable
    public Charset getImageCharsetAsCharset() {
        return Optional.ofNullable(getImageCharset())
                .map(Charset::forName)
                .orElse(null);
    }

    public void setImageCharsetAsCharset(@Nullable final Charset charset) {
        setImageCharset(
                Optional.ofNullable(charset)
                        .map(Charset::name)
                        .orElse(null)
        );
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
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false,
//            insertable = false,
            insertable = true, // EclipseLInk
            updatable = false)
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
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Column(name = "PRODUCT_DETAILS", nullable = true, insertable = true, updatable = true)
    private byte[] productDetails;

    @jakarta.annotation.Nullable
    @Lob
    @Basic(optional = true, fetch = FetchType.LAZY)
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