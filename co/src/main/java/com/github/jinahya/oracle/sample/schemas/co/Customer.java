package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;

/**
 * An entity class maps to {@value Customer#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(
        name = "Customer.selectListWhereFullNameLike",
        query = """
                SELECT e
                FROM Customer AS e
                WHERE e.fullName LIKE :fullNamePattern"""
)
@NamedQuery(
        name = "Customer.selectSingleWhereFullNameEqual",
        query = """
                SELECT e
                FROM Customer AS e
                WHERE e.fullName = :fullName"""
)
@NamedQuery(
        name = "Customer.selectListWhereEmailAddressLike",
        query = """
                SELECT e
                FROM Customer AS e
                WHERE e.emailAddress LIKE :emailAddressPattern"""
)
@NamedQuery(
        name = "Customer.selectSingleWhereEmailAddressEqual",
        query = """
                SELECT e
                FROM Customer AS e
                WHERE e.emailAddress = :emailAddress"""
)
@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer extends __MappedEntity<Customer, Long> {

    @Serial
    private static final long serialVersionUID = 8488065550644505527L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the database table to which this entity class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CUSTOMERS";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID

    /**
     * The name of the table column to which the {@link Customer_#customerId customerId} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    // --------------------------------------------------------------------------------------------------- EMAIL_ADDRESS

    /**
     * The name of the table column to which the {@link Customer_#emailAddress emailAddress} attribute maps. The value
     * is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EMAIL_ADDRESS";

    // ------------------------------------------------------------------------------------------------------- FULL_NAME

    /**
     * The name of the table column to which the {@link Customer_#fullName fullName} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_FULL_NAME = "FULL_NAME";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Customer() {
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

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Long _id_() {
        return getCustomerId();
    }

    @Override
    protected final void _id_(final Long _id_) {
        setCustomerId(_id_);
    }

    // ------------------------------------------------------------------------------------------------------ customerId
    public Long getCustomerId() {
        return customerId;
    }

    void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- emailAddress
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // -------------------------------------------------------------------------------------------------------- fullName
    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false,
//            insertable = false,
            insertable = true, // EclipseLink
            updatable = false)
    private Long customerId;

    @Size(max = 255)
    @NotNull
    @Column(name = COLUMN_NAME_EMAIL_ADDRESS, nullable = false, insertable = true, updatable = true,
            unique = true, // !!!
            length = 255)
    private String emailAddress;

    @Size(max = 255)
    @NotNull
    @Column(name = COLUMN_NAME_FULL_NAME, nullable = false, insertable = true, updatable = true, length = 255)
    private String fullName;
}