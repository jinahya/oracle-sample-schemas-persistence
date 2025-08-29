package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__Mapped;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;

import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public abstract class _MappedCoBinary extends __Mapped {

    // --------------------------------------------------------------------------------------------------- BYTES / bytes
    public static final String COLUMN_NAME_BYTES = "BYTES";

    // -------------------------------------------------------------------------------------------- MIME_TYPE / mimeType
    public static final String COLUMN_NAME_MIME_TYPE = "MIME_TYPE";

    // --------------------------------------------------------------------------------------------- FILENAME / filename
    public static final String COLUMN_NAME_FILENAME = "FILENAME";

    // ----------------------------------------------------------------------------------------------- CHARSET / charset
    public static final String COLUMN_NAME_CHARSET = "CHARSET";

    // -------------------------------------------------------------------------------------- LAST_UPDATED / lastUpdated
    public static final String COLUMN_NAME_LAST_UPDATED = "LAST_UPDATED";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCoBinary() {
        super();
    }

    protected _MappedCoBinary(final _MappedCoBinaryBuilder<?, ?> builder) {
        super();
        Objects.requireNonNull(builder, "builder is null");
        bytes = builder.bytes();
        mimeType = builder.mimeType();
        filename = builder.filename();
        charset = builder.charset();
        lastUpdated = builder.lastUpdated();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ----------------------------------------------------------------------------------------------------------- bytes
    @Nullable
    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(@Nullable final byte[] bytes) {
        this.bytes = bytes;
    }

    // -------------------------------------------------------------------------------------------------------- mimeType
    @Nullable
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(@Nullable final String mimeType) {
        this.mimeType = mimeType;
    }

    // -------------------------------------------------------------------------------------------------------- filename
    @Nullable
    public String getFilename() {
        return filename;
    }

    public void setFilename(@Nullable final String filename) {
        this.filename = filename;
    }

    // --------------------------------------------------------------------------------------------------------- charset
    @Nullable
    public String getCharset() {
        return charset;
    }

    public void setCharset(@Nullable final String charset) {
        this.charset = charset;
    }

    // ----------------------------------------------------------------------------------------------------- lastUpdated
    @Nullable
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(@Nullable final LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Lob
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Column(
            name = COLUMN_NAME_BYTES,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] bytes;

    @Nullable
    @Basic(optional = true)
    @Column(
            name = COLUMN_NAME_MIME_TYPE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String mimeType;

    @Nullable
    @Basic(optional = true)
    @Column(
            name = COLUMN_NAME_FILENAME,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String filename;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CHARSET,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String charset;

    @Nullable
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_UPDATED,
            insertable = true,
            updatable = true
    )
    private LocalDate lastUpdated;
}
