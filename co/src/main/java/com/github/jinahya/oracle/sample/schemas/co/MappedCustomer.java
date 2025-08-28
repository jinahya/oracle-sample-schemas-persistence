package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass
public abstract class MappedCustomer extends __MappedEntity<Long> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CUSTOMERS";

    // ---------------------------------------------------------------------------------------- CUSTOMER_ID / customerId

    /**
     * The name of the table column to which the {@link MappedCustomer_#customerId customerId} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    // ------------------------------------------------------------------------------------ EMAIL_ADDRESS / emailAddress

    /**
     * The name of the table column to which the {@link MappedCustomer_#emailAddress emailAddress} attribute maps. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EMAIL_ADDRESS";

    public static final int COLUMN_LENGTH_NAME_EMAIL_ADDRESS = 255;

    public static final String ATTRIBUTE_NAME_EMAIL_ADDRESS = "emailAddress";

    public static final int SIZE_MAX_NAME_EMAIL_ADDRESS = COLUMN_LENGTH_NAME_EMAIL_ADDRESS;

    // -------------------------------------------------------------------------------------------- FULL_NAME / fullName

    /**
     * The name of the table column to which the {@link MappedCustomer_#fullName fullName} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FULL_NAME = "FULL_NAME";

    public static final int COLUMN_LENGTH_NAME_FULL_NAME = 255;

    public static final String ATTRIBUTE_NAME_FULL_NAME = "fullName";

    public static final int SIZE_MAX_NAME_FULL_NAME = COLUMN_LENGTH_NAME_FULL_NAME;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCustomer() {
        super();
    }

    MappedCustomer(@Nonnull final MappedCustomerBuilder<?, ?> builder) {
        super(builder);
        Objects.requireNonNull(builder, "builder is null");
        emailAddress = builder.emailAddress();
        fullName = builder.fullName();
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
        if (!(obj instanceof MappedCustomer customer)) {
            return false;
        }
        return Objects.equals(emailAddress, customer.emailAddress);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(emailAddress);
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

    void setEmailAddress(@Nonnull final String emailAddress) {
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
    @Column(
            name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = true /* EclipseLink */,
            updatable = false
    )
    private Long customerId;

    @Nonnull
    @Size(max = SIZE_MAX_NAME_EMAIL_ADDRESS)
    @NotNull
    @Column(name = COLUMN_NAME_EMAIL_ADDRESS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_EMAIL_ADDRESS,
            unique = true
    )
    private String emailAddress;

    @Nonnull
    @Size(max = SIZE_MAX_NAME_FULL_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_FULL_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME_FULL_NAME
    )
    private String fullName;
}
