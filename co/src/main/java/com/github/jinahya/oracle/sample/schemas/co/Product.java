package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * An entity class for mapping {@value _MappedProduct#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ProductDetails
 */
@Entity
@Table(name = _MappedProduct.TABLE_NAME)
public class Product extends _MappedProduct<Product> {

    protected Product() {
        super();
    }
}
