package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * sh
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

/**
 * An abstract randomizer for the concrete pairs of {@link PromotionNamedId}: nothing is excluded, so both halves are
 * randomized, the id through the type argument the concrete class binds {@code N} to.
 *
 * @param <T> the pair class to randomize.
 * @param <N> the type of the pair's id.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S118" // Abstract class names should comply with a naming convention
})
abstract class PromotionNamedId_Randomizer<T extends PromotionNamedId<N>, N extends Number>
        extends PodamObjectRandomizer<T> {

    /**
     * Creates a new instance for the specified pair class.
     *
     * @param embeddableClass the pair class to randomize.
     */
    PromotionNamedId_Randomizer(final Class<T> embeddableClass) {
        super(embeddableClass, List.of(

        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Override
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Override
    public T get() {
        return super.get();
    }
}
