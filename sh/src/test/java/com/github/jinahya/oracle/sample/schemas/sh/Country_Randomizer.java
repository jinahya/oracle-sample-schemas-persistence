package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

class Country_Randomizer extends __MappedEntity_Randomizer<Country> {

    Country_Randomizer() {
        super(Country.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
        return super.classInfoStrategy();
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected Country manufacturePojo() {
        return super.manufacturePojo();
    }
}