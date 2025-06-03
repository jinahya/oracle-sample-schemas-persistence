package com.github.jinahya.oracle.sample.schemas.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

public final class __DateTime_Test_Utils {

    // --------------------------------------------------------------------------------------------------------- ZoneId
    public static Stream<ZoneId> getZoneIdStream() {
        return ZoneId.getAvailableZoneIds().stream().map(ZoneId::of);
    }

    public static ZoneId getRandomZoneId() {
        return getZoneIdStream().findAny().orElseThrow();
    }

    // ------------------------------------------------------------------------------------------------------ ZoneOffset
    public static Stream<ZoneOffset> getZoneOffsetStream() {
        final var now = LocalDateTime.now();
        return getZoneIdStream()
                .map(now::atZone)
                .map(ZonedDateTime::getOffset);
    }

    public static ZoneOffset getRandomZoneOffset() {
        return getZoneOffsetStream().findAny().orElseThrow();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __DateTime_Test_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
