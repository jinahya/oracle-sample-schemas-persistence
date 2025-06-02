package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas._MappedEntity_Randomizer;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Store_Randomizer extends _MappedEntity_Randomizer<Store> {

    Store_Randomizer() {
        super(Store.class,
              "storeId",
              "logo", "logoMimeType", "logoFilename", "logoCharset", "logoLastUpdated"
        );
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
        return super.classInfoStrategy();
    }

    @Override
    protected Store manufacturePojo() {
        log.debug("manufacturing store...");
        final var store = super.manufacturePojo();
        log.debug("store manufactured: {}", store);
//        assertThat(store.getStoreId()).isNull();
        store.setStoreId(null);
//        assertThat(store.getLogo()).isNull();
//        assertThat(store.getLogoMimeType()).isNull();
//        assertThat(store.getLogoFilename()).isNull();
//        assertThat(store.getLogoCharset()).isNull();
//        assertThat(store.getLogoLastUpdated()).isNull();
        return store;
    }
}