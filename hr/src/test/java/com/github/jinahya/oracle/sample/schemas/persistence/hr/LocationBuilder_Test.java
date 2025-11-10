package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped._MappedHrEntityBuilder_Test;

class LocationBuilder_Test extends _MappedHrEntityBuilder_Test<LocationBuilder, Location, Integer> {

    LocationBuilder_Test() {
        super(LocationBuilder.class, Location.class, Integer.class);
    }
}
