package com.github.jinahya.oracle.sample.schemas.co;

import java.time.LocalDate;

@SuppressWarnings({
        "unchecked",
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedCoBinaryBuilder<
        SELF extends _MappedCoBinaryBuilder<SELF, TARGET>,
        TARGET extends _MappedCoBinary
        >
        extends _MappedCoBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedCoBinaryBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ----------------------------------------------------------------------------------------------------------- bytes
    public byte[] bytes() {
        return bytes;
    }

    public SELF bytes(final byte[] bytes) {
        this.bytes = bytes;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- mimeType
    public String mimeType() {
        return mimeType;
    }

    public SELF mimeType(final String mimeType) {
        this.mimeType = mimeType;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- filename
    public String filename() {
        return filename;
    }

    public SELF filename(final String filename) {
        this.filename = filename;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- charset
    public String charset() {
        return charset;
    }

    public SELF charset(final String charset) {
        this.charset = charset;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------- lastUpdated
    public LocalDate lastUpdated() {
        return lastUpdated;
    }

    public SELF lastUpdated(final LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private byte[] bytes;

    private String mimeType;

    private String filename;

    private String charset;

    private LocalDate lastUpdated;
}
