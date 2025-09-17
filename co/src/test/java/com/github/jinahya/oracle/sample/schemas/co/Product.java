package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NamedQuery(
        name = "Product.selectListOrderByProductIdAscProductIdGt",
        query = """
                SELECT e
                FROM Product e
                WHERE e.productId > :productIdMinExclusive
                ORDER BY e.productId ASC"""
)
@NamedQuery(
        name = "Product.selectListOrderByProductIdAsc",
        query = """
                SELECT e
                FROM Product e
                ORDER BY e.productId ASC"""
)
@Entity
@Table(name = Product.TABLE_NAME)
class Product extends MappedProduct {

    // -----------------------------------------------------------------------------------------------------------------
    static MappedProductBuilder<?, Product> builder() {
        return new ProductBuilder();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Product() {
        super();
    }

    Product(final ProductBuilder builder) {
        super(builder);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
//    @Override
//    public final boolean equals(final Object obj) {
//        if (!(obj instanceof Product that)) {
//            return false;
//        }
//        return Objects.equals(getProductId(), that.getProductId());
//    }
//
//    @Override
//    public final int hashCode() {
//        return Objects.hash(getProductId());
//    }
}
