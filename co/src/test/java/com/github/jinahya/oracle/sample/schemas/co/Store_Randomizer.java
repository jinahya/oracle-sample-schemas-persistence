package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

@Slf4j
class Store_Randomizer extends __MappedEntity_Randomizer<Store> {

    Store_Randomizer() {
        super(Store.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy()
                .addOrReplaceAttributeStrategy(entityClass, "storeId", (t, a) -> {
                    return null;
                });
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
        return super.classInfoStrategy();
//        return new AbstractClassInfoStrategy() {
//            // https://github.com/mtedone/podam/pull/84
//            @Override
//            public boolean approve(final ClassAttribute attribute) {
//                return !Objects.equals(attribute.getName(), "storeId");
//            }
//        };
    }

    @Override
    protected Store manufacturePojo() {
        return super.manufacturePojo();
    }
}