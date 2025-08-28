package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__Builder;
import jakarta.annotation.Nonnull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings({
        "unchecked",
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedBinaryBuilder<
        SELF extends _MappedBinaryBuilder<SELF, TARGET>,
        TARGET extends _MappedBinary
        >
        extends __Builder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    protected static <B extends _MappedBinaryBuilder<B, ?>> B from(@Nonnull final Supplier<? extends B> instantiator,
                                                                   @Nonnull final Path path,
                                                                   @Nonnull final LinkOption... options)
            throws IOException {
        Objects.requireNonNull(instantiator, "instantiator is null");
        Objects.requireNonNull(path, "path is null");
        Objects.requireNonNull(options, "options is null");
        if (Files.isRegularFile(path, options)) {
            throw new IllegalArgumentException(
                    "not a regular file"
                            + "; path: " + path
                            + "; options: " + Arrays.toString(options)
            );
        }
        final var instance = Objects.requireNonNull(instantiator.get(), "null instantiated from " + instantiator);
        instance.bytes(Files.readAllBytes(path));
        instance.filename(path.getFileName().toString());
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected _MappedBinaryBuilder(final Class<TARGET> targetClass) {
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
