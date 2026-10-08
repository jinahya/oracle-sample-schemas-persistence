package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/*-
 * #%L
 * sh
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedChannel#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedChannel {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CHANNELS";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_ID = "channelId";

    // ---------------------------------------------------------------------------------------------------- CHANNEL_DESC

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CHANNEL_DESC} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_DESC = "CHANNEL_DESC";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_DESC} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_DESC = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_DESC} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_DESC = "channelDesc";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CHANNEL_DESC} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CHANNEL_DESC = COLUMN_LENGTH_CHANNEL_DESC;

    // --------------------------------------------------------------------------------------------------- CHANNEL_CLASS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CHANNEL_CLASS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_CLASS = "CHANNEL_CLASS";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_CLASS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_CLASS = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_CLASS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_CLASS = "channelClass";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CHANNEL_CLASS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CHANNEL_CLASS = COLUMN_LENGTH_CHANNEL_CLASS;

    // ------------------------------------------------------------------------------------------------ CHANNEL_CLASS_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CHANNEL_CLASS_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_CLASS_ID = "CHANNEL_CLASS_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_CLASS_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_CLASS_ID = "channelClassId";

    // --------------------------------------------------------------------------------------------------- CHANNEL_TOTAL

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_TOTAL = "CHANNEL_TOTAL";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_TOTAL = 13;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_TOTAL = "channelTotal";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_CHANNEL_TOTAL = COLUMN_LENGTH_CHANNEL_TOTAL;

    // ------------------------------------------------------------------------------------------------ CHANNEL_TOTAL_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_TOTAL_ID = "CHANNEL_TOTAL_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_TOTAL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_TOTAL_ID = "channelTotalId";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedChannel() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "channelId=" + channelId +
               ",channelDesc=" + channelDesc +
               ",channelClass=" + channelClass +
               ",channelClassId=" + channelClassId +
               ",channelTotal=" + channelTotal +
               ",channelTotalId=" + channelTotalId +
               '}';
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the {@code @Id} alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof MappedChannel that)) {
            return false;
        }
        return Objects.equals(getChannelId(), that.getChannelId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getChannelId());
    }

    // ------------------------------------------------------------------------------------------------------- channelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute with the specified value.
     *
     * @param channelId new value for {@value #ATTRIBUTE_NAME_CHANNEL_ID} attribute.
     */
    protected void setChannelId(final Long channelId) {
        this.channelId = channelId;
    }

    // ----------------------------------------------------------------------------------------------------- channelDesc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_DESC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_DESC} attribute.
     */
    public String getChannelDesc() {
        return channelDesc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_DESC} attribute with the specified value.
     *
     * @param channelDesc new value for {@value #ATTRIBUTE_NAME_CHANNEL_DESC} attribute.
     */
    public void setChannelDesc(final String channelDesc) {
        this.channelDesc = channelDesc;
    }

    // ---------------------------------------------------------------------------------------------------- channelClass

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_CLASS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_CLASS} attribute.
     */
    public String getChannelClass() {
        return channelClass;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_CLASS} attribute with the specified value.
     *
     * @param channelClass new value for {@value #ATTRIBUTE_NAME_CHANNEL_CLASS} attribute.
     */
    public void setChannelClass(final String channelClass) {
        this.channelClass = channelClass;
    }

    // -------------------------------------------------------------------------------------------------- channelClassId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_CLASS_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_CLASS_ID} attribute.
     */
    public Long getChannelClassId() {
        return channelClassId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_CLASS_ID} attribute with the specified value.
     *
     * @param channelClassId new value for {@value #ATTRIBUTE_NAME_CHANNEL_CLASS_ID} attribute.
     */
    public void setChannelClassId(final Long channelClassId) {
        this.channelClassId = channelClassId;
    }

    // ---------------------------------------------------------------------------------------------------- channelTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL} attribute.
     */
    public String getChannelTotal() {
        return channelTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL} attribute with the specified value.
     *
     * @param channelTotal new value for {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL} attribute.
     */
    public void setChannelTotal(final String channelTotal) {
        this.channelTotal = channelTotal;
    }

    // -------------------------------------------------------------------------------------------------- channelTotalId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL_ID} attribute.
     */
    public Long getChannelTotalId() {
        return channelTotalId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL_ID} attribute with the specified value.
     *
     * @param channelTotalId new value for {@value #ATTRIBUTE_NAME_CHANNEL_TOTAL_ID} attribute.
     */
    public void setChannelTotalId(final Long channelTotalId) {
        this.channelTotalId = channelTotalId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_CHANNEL_DESC)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_DESC,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CHANNEL_DESC
    )
    private String channelDesc;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_CHANNEL_CLASS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_CLASS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CHANNEL_CLASS
    )
    private String channelClass;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_CLASS_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long channelClassId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_CHANNEL_TOTAL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_TOTAL,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CHANNEL_TOTAL
    )
    private String channelTotal;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_TOTAL_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Long channelTotalId;
}
