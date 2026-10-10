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

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * A mapped superclass which holds one of the two name/identifier pairs of the {@value MappedChannel#TABLE_NAME} table.
 * <p>
 * The table carries two such pairs, and this class maps either of them.
 * <table>
 *   <caption>Pairs mapped by this class</caption>
 *   <thead>
 *     <tr><th>pair</th><th>{@code key}</th><th>{@code value}</th></tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td>channel class</td>
 *       <td>{@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_CLASS} ({@value MappedChannel#COLUMN_NAME_CHANNEL_CLASS})</td>
 *       <td>{@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_CLASS_ID}
 *           ({@value MappedChannel#COLUMN_NAME_CHANNEL_CLASS_ID})</td>
 *     </tr>
 *     <tr>
 *       <td>channel total</td>
 *       <td>{@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_TOTAL} ({@value MappedChannel#COLUMN_NAME_CHANNEL_TOTAL})</td>
 *       <td>{@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_TOTAL_ID}
 *           ({@value MappedChannel#COLUMN_NAME_CHANNEL_TOTAL_ID})</td>
 *     </tr>
 *   </tbody>
 * </table>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see MappedChannel
 */
@MappedSuperclass
public abstract class MappedChannelKeyValue {

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR

    /**
     * Creates a new instance.
     */
    protected MappedChannelKeyValue() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
               "key=" + key +
               ",value=" + value +
               '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var that = (MappedChannelKeyValue) obj;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(key, value);
    }

    // ------------------------------------------------------------------------------------------------------------- key

    /**
     * Returns current value of {@code key} attribute; the name of the pair, either a
     * {@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_CLASS} or a {@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_TOTAL}.
     *
     * @return current value of {@code key} attribute.
     */
    public String getKey() {
        return key;
    }

    /**
     * Replaces current value of {@code key} attribute with the specified value.
     *
     * @param key new value for {@code key} attribute; either a {@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_CLASS} or a
     *            {@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_TOTAL}.
     */
    public void setKey(final String key) {
        this.key = key;
    }

    // ----------------------------------------------------------------------------------------------------------- value

    /**
     * Returns current value of {@code value} attribute; the identifier of the pair, either a
     * {@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_CLASS_ID} or a
     * {@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_TOTAL_ID}.
     *
     * @return current value of {@code value} attribute.
     */
    public Long getValue() {
        return value;
    }

    /**
     * Replaces current value of {@code value} attribute with the specified value.
     *
     * @param value new value for {@code value} attribute; either a
     *              {@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_CLASS_ID} or a
     *              {@value MappedChannel#ATTRIBUTE_NAME_CHANNEL_TOTAL_ID}.
     */
    public void setValue(final Long value) {
        this.value = value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    private String key;

    @NotNull
    private Long value;
}
