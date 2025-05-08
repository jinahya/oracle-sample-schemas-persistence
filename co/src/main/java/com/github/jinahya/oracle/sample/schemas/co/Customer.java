package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * An entity class maps to {@value Customer#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer {

    // -----------------------------------------------------------------------------------------------------------------
    static final String TABLE_NAME = "CUSTOMERS";

    // ----------------------------------------------------------------------------------------------------- CUSTOMER_ID
    public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

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
        if (!(obj instanceof Customer)) {
            return false;
        }
        return Objects.equals(
                getCustomerId(),
                ((Customer) obj).getCustomerId()
        );
    }

    @Override
    public final int hashCode() {
        return Objects.hash(
                getCustomerId()
        );
    }

    // ------------------------------------------------------------------------------------------------------ customerId
    public Long getCustomerId() {
        return customerId;
    }

    // ------------------------------------------------------------------------------------------------------- emailAddress
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // ------------------------------------------------------------------------------------------------------- fullName
    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    private Long customerId;

    @Size(max = 255)
    @NotNull
    @Column(name = "EMAIL_ADDRESS", nullable = false, insertable = true, updatable = true, unique = true)
    private String emailAddress;

    @Size(max = 255)
    @NotNull
    @Column(name = "FULL_NAME", nullable = false, insertable = true, updatable = true)
    private String fullName;
}