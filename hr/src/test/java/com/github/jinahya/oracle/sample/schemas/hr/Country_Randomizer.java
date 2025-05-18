package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.JavaUtilLocale_TestUtils;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.common.ManufacturingContext;
import uk.co.jemos.podam.typeManufacturers.StringTypeManufacturerImpl;

class Country_Randomizer extends __MappedEntity_Randomizer<Country> {

    Country_Randomizer() {
        super(Country.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        final var strategy = super.dataProviderStrategy();
        strategy.addOrReplaceTypeManufacturer(String.class, new StringTypeManufacturerImpl() {
            @Override
            public String getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata,
                                  ManufacturingContext manufacturingCtx) {
                if (Country.class.isAssignableFrom(attributeMetadata.getPojoClass()) &&
                        attributeMetadata.getAttributeName().equals("countryId")) {
                    return JavaUtilLocale_TestUtils.randomLocale().getCountry();
                }
                return super.getType(strategy, attributeMetadata, manufacturingCtx);
            }
        });
        return strategy;
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