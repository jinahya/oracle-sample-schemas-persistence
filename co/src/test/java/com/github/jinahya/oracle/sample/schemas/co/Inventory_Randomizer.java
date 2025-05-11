package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import uk.co.jemos.podam.api.AbstractClassInfoStrategy;
import uk.co.jemos.podam.api.ClassAttribute;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class Inventory_Randomizer extends __MappedEntity_Randomizer<Inventory> {

    Inventory_Randomizer() {
        super(Inventory.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy();
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
//        return super.classInfoStrategy();
        return new AbstractClassInfoStrategy() {
            // https://github.com/mtedone/podam/pull/84
            @Override
            public boolean approve(final ClassAttribute attribute) {
                return !Objects.equals(attribute.getName(), "inventoryId");
            }
        };
    }

    @Override
    protected Inventory manufacturePojo() {
        final var pojo =  super.manufacturePojo();
        assertThat(pojo.getInventoryId()).isNull();
        return pojo;
    }
}