package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.ManufacturingContext;

public class _EnumTypeManufacturer extends AbstractTypeManufacturer<Enum<?>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Enum<?> getType(final DataProviderStrategy strategy, final AttributeMetadata attributeMetadata,
                           final ManufacturingContext manufacturingCtx) {
        final var realAttributeType = attributeMetadata.getAttributeType();
        Object[] enumConstants = realAttributeType.getEnumConstants();
        if (null == enumConstants) {
            enumConstants = Thread.State.class.getEnumConstants();
        }
        Enum<?> retValue = null;
        final int enumConstantsLength = enumConstants.length;
        if (enumConstantsLength > 0) {
            int enumIndex = PodamUtils.getIntegerInRange(0, enumConstantsLength)
                    % enumConstantsLength;
            retValue = (Enum<?>) enumConstants[enumIndex];
        }
        return retValue;
    }
}
