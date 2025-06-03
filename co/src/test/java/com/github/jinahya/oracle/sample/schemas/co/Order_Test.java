package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Test;
import com.github.jinahya.oracle.sample.schemas.util.__DateTime_Test_Utils;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
class Order_Test extends __MappedEntity_Test<Order, Long> {

    // -----------------------------------------------------------------------------------------------------------------
    Order_Test() {
        super(Order.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<Order> equalsVerifier() {
        return super.equalsVerifier()
                .withPrefabValues(
                        List.class, Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)
                );
    }

    @DisplayName("getOrderTmsAsZonedDateTime(zone)ZonedDateTime")
    @Nested
    class GetOrderTmsAsZonedDateTime_Test {

        @DisplayName("()null <- getOrderTms()null")
        @Test
        void _Null_Null() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newRandomizedEntitySpy().orElseThrow();
            given(instance.getOrderTms()).willReturn(null);
            // ---------------------------------------------------------------------------------------------------- when
            final var zonedDateTime = instance.getOrderTmsAsZonedDatetime(ZoneId.systemDefault());
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(zonedDateTime).isNull();
        }

        @DisplayName("()!null <- getOrderTms()!null")
        @Test
        void __NotNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            final var orderTms = LocalDateTime.now();
            given(instance.getOrderTms()).willReturn(orderTms);
            final var zone = __DateTime_Test_Utils.getRandomZoneId();
            // ---------------------------------------------------------------------------------------------------- when
            final var zonedDateTime = instance.getOrderTmsAsZonedDatetime(zone);
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(zonedDateTime).isEqualTo(orderTms.atZone(zone));
        }
    }

    @DisplayName("setOrderTmsFromZonedDateTime(zonedDateTime)")
    @Nested
    class SetOrderTmsFromZonedDateTime_Test {

        @DisplayName("(null) -> setOrderTms(null)")
        @Test
        void _Null_Null() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            // ---------------------------------------------------------------------------------------------------- when
            instance.setOrderTmsFromZonedDateTime(null);
            // ---------------------------------------------------------------------------------------------------- then
            verify(instance, times(1)).setOrderTms(null);
        }

        @DisplayName("(!null) -> setOrderTms(!null)")
        @Test
        void __NotNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newRandomizedEntitySpy().orElseThrow();
            final var zone = __DateTime_Test_Utils.getRandomZoneId();
            final var zonedDateTime = LocalDateTime.now().atZone(zone);
            // ---------------------------------------------------------------------------------------------------- when
            instance.setOrderTmsFromZonedDateTime(zonedDateTime);
            // ---------------------------------------------------------------------------------------------------- then
            final var captor = ArgumentCaptor.forClass(LocalDateTime.class);
            verify(instance, times(1)).setOrderTms(captor.capture());
            final var orderTms = captor.getValue();
            assertThat(orderTms).isEqualTo(zonedDateTime.toLocalDateTime());
        }
    }

    @DisplayName("getOrderTmsFromOffsetDateTime(offset)OffsetDateTime")
    @Nested
    class GetOrderTmsFromOffsetDateTime_Test {

        @DisplayName("()null <- getOrderTms()null")
        @Test
        void _Null_Null() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newRandomizedEntitySpy().orElseThrow();
            given(instance.getOrderTms()).willReturn(null);
            final var offset = mock(ZoneOffset.class);
            // ---------------------------------------------------------------------------------------------------- when
            final var offsetDateTime = instance.getOrderTmsAsOffsetDatetime(offset);
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(offsetDateTime).isNull();
        }

        @DisplayName("()!null <- getOrderTms()!null")
        @Test
        void __NotNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newRandomizedEntitySpy().orElseThrow();
            final var orderTms = LocalDateTime.now();
            given(instance.getOrderTms()).willReturn(orderTms);
            final var offset = __DateTime_Test_Utils.getRandomZoneOffset();
            // ---------------------------------------------------------------------------------------------------- when
            final var offsetDateTime = instance.getOrderTmsAsOffsetDatetime(offset);
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(offsetDateTime).isEqualTo(orderTms.atOffset(offset));
        }
    }

    @DisplayName("setOrderTmsFromOffsetDateTime(offsetDateTime)")
    @Nested
    class SetOrderTmsFromOffsetDateTime_Test {

        @DisplayName("(null) -> setOrderTims(null)")
        @Test
        void _Null_Null() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newRandomizedEntitySpy().orElseThrow();
            // ---------------------------------------------------------------------------------------------------- when
            instance.setOrderTmsFromOffsetDateTime(null);
            // ---------------------------------------------------------------------------------------------------- then
            verify(instance, times(1)).setOrderTms(null);
        }

        @DisplayName("(!null) -> setOrderTms(!null)")
        @Test
        void __NotNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newRandomizedEntitySpy().orElseThrow();
            final var offset = __DateTime_Test_Utils.getRandomZoneOffset();
            final var offsetDateTime = LocalDateTime.now().atOffset(offset);
            // ---------------------------------------------------------------------------------------------------- when
            instance.setOrderTmsFromOffsetDateTime(offsetDateTime);
            // ---------------------------------------------------------------------------------------------------- then
            final var captor = ArgumentCaptor.forClass(LocalDateTime.class);
            verify(instance, times(1)).setOrderTms(captor.capture());
            final var orderTms = captor.getValue();
            assertThat(orderTms).isNotNull();
            assertThat(orderTms).isEqualTo(offsetDateTime.toLocalDateTime());
        }
    }

    @DisplayName("getOrderTmsAsInstant(zone)")
    @Nested
    class GetOrderTmsAsInstantWithZone_Test {

        @DisplayName("()null <- getOrderTmsAsZonedDateTime(any)null")
        @Test
        void _Null_Null() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            given(instance.getOrderTmsAsZonedDatetime(any())).willReturn(null);
            final var zone = (ZoneId) null;
            // ---------------------------------------------------------------------------------------------------- when
            final var instant = instance.getOrderTmsAsInstant(zone);
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(instant).isNull();
        }

        @DisplayName("()!null <- getOrderTmsAsZonedDateTime(given)!null")
        @Test
        void __NotNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            final var zone = __DateTime_Test_Utils.getRandomZoneId();
            final var zonedDateTime = LocalDateTime.now().atZone(zone);
            given(instance.getOrderTmsAsZonedDatetime(zone)).willReturn(zonedDateTime);
            // ---------------------------------------------------------------------------------------------------- when
            final var instant = instance.getOrderTmsAsInstant(zone);
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(instant).isEqualTo(zonedDateTime.toInstant());
        }
    }

    @DisplayName("setOrderTmsFromInstant(instant, zone)")
    @Nested
    class SetOrderTmsFromInstantWithZone_Test {

        @DisplayName("(null, ) -> setOrderTmsFromZonedDateTime(null)")
        @Test
        void _Null_Null() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            final var zone = __DateTime_Test_Utils.getRandomZoneId();
            given(instance.getOrderTmsAsZonedDatetime(zone)).willReturn(null);
            // ---------------------------------------------------------------------------------------------------- when
            instance.setOrderTmsFromInstant(null, zone);
            // ---------------------------------------------------------------------------------------------------- then
            verify(instance, times(1)).setOrderTmsFromZonedDateTime(null);
        }

        @DisplayName("(!null, ) -> setOrderTmsFromZonedDateTime(!null)")
        @Test
        void __NotNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            final var instant = Instant.now();
            final var zone = __DateTime_Test_Utils.getRandomZoneId();
            // ---------------------------------------------------------------------------------------------------- when
            instance.setOrderTmsFromInstant(instant, zone);
            // ---------------------------------------------------------------------------------------------------- then
            verify(instance, times(1)).setOrderTmsFromZonedDateTime(instant.atZone(zone));
        }
    }

    @DisplayName("getOrderTmsAsInstant(offset)")
    @Nested
    class GetOrderTmsAsInstant_WithOffset_Test {

        @DisplayName("()null <- getOrderTmsAsOffsetDateTime(any)null")
        @Test
        void _Null_Null() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            given(instance.getOrderTmsAsOffsetDatetime(any())).willReturn(null);
            final var zoneOffset = ZoneOffset.systemDefault();
            // ---------------------------------------------------------------------------------------------------- when
            final var orderTmsAsInstant = instance.getOrderTmsAsInstant(zoneOffset);
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(orderTmsAsInstant).isNull();
        }

        @DisplayName("()null <- getOrderTmsAsOffsetDateTime(any)null")
        @Test
        void __NotNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            final var offset = __DateTime_Test_Utils.getRandomZoneOffset();
            final var orderTmsAsOffsetDateTime = LocalDateTime.now().atOffset(offset);
            given(instance.getOrderTmsAsOffsetDatetime(offset)).willReturn(orderTmsAsOffsetDateTime);
            // ---------------------------------------------------------------------------------------------------- when
            final var orderTmsAsInstant = instance.getOrderTmsAsInstant(offset);
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(orderTmsAsInstant).isEqualTo(orderTmsAsOffsetDateTime.toInstant());
        }
    }

    @DisplayName("setOrderTmsAsInstant(zoneOffset)")
    @Nested
    class SetOrderTmsAsInstant_WithOffset_Test {

        @DisplayName("(null, ) -> setOrderTmsFromOffsetDateTime(null)")
        @Test
        void _Null_Null() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            final var offset = __DateTime_Test_Utils.getRandomZoneOffset();
            given(instance.getOrderTmsAsOffsetDatetime(offset)).willReturn(null);
//            final var offset = OffsetDateTime.now().getOffset();
            // ---------------------------------------------------------------------------------------------------- when
            instance.setOrderTmsFromInstant(null, offset);
            // ---------------------------------------------------------------------------------------------------- then
            verify(instance, times(1)).setOrderTmsFromOffsetDateTime(null);
        }

        @DisplayName("(!null) -> setOrderTmsFromOffsetDateTime(!null, )")
        @Test
        void __NotNull() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = newEntitySpy();
            final var instant = Instant.now();
            final var offset = __DateTime_Test_Utils.getRandomZoneOffset();
            // ---------------------------------------------------------------------------------------------------- when
            instance.setOrderTmsFromInstant(instant, offset);
            // ---------------------------------------------------------------------------------------------------- then
            final var captor = ArgumentCaptor.forClass(OffsetDateTime.class);
            verify(instance, times(1)).setOrderTmsFromOffsetDateTime(captor.capture());
            final var orderTmsAsOffsetDateTime = captor.getValue();
            assertThat(orderTmsAsOffsetDateTime).isEqualTo(instant.atOffset(offset));
        }
    }
}