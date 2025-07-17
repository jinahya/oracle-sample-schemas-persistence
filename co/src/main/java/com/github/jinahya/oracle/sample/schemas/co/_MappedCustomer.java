package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@MappedSuperclass
abstract class _MappedCustomer<SELF extends _MappedCustomer<SELF>> extends __MappedEntity<SELF, Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CUSTOMERS";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the table column to which the {@link _MappedCustomer_#customerId customerId} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    // --------------------------------------------------------------------------------------------------- EMAIL_ADDRESS

    /**
     * The name of the table column to which the {@link _MappedCustomer_#emailAddress emailAddress} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EMAIL_ADDRESS";

    public static final int COLUMN_LENGTH_NAME_EMAIL_ADDRESS = 255;

    public static final int SIZE_MAX_NAME_EMAIL_ADDRESS = COLUMN_LENGTH_NAME_EMAIL_ADDRESS;

    // ------------------------------------------------------------------------------------------------------- FULL_NAME

    /**
     * The name of the table column to which the {@link _MappedCustomer_#fullName fullName} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FULL_NAME = "FULL_NAME";

    public static final int COLUMN_LENGTH_NAME_FULL_NAME = 255;

    public static final int SIZE_MAX_NAME_FULL_NAME = COLUMN_LENGTH_NAME_FULL_NAME;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    _MappedCustomer() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final String toString() {
        return super.toString() + '{' +
                "customerId=" + customerId +
                ",emailAddress=" + emailAddress +
                ",fullName=" + fullName +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof _MappedCustomer)) {
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
        return getCustomerId();
    }

    @Override
    protected final void setId__(final Long id__) {
        setCustomerId(id__);
    }

    // ------------------------------------------------------------------------------------------------------ customerId
    public Long getCustomerId() {
        return customerId;
    }

    void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- emailAddress
    @Nonnull
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@Nonnull final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // -------------------------------------------------------------------------------------------------------- fullName
    @Nonnull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(@Nonnull final String fullName) {
        this.fullName = fullName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Positive // ???
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true /* EclipseLink */, updatable = false)
    private Long customerId;

    @Nonnull
    @Size(max = SIZE_MAX_NAME_EMAIL_ADDRESS)
    @NotNull
    @Column(name = COLUMN_NAME_EMAIL_ADDRESS, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_NAME_EMAIL_ADDRESS, unique = true)
    private String emailAddress;

    @Nonnull
    @Size(max = SIZE_MAX_NAME_FULL_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_FULL_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_NAME_FULL_NAME)
    private String fullName;
}
