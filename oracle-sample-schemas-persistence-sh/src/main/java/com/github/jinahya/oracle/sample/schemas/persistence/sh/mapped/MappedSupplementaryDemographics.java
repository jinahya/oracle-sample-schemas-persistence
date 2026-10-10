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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@value MappedSupplementaryDemographics#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class MappedSupplementaryDemographics implements __MappedDomainEntity<Long> {

    /**
     * The name of the database table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SUPPLEMENTARY_DEMOGRAPHICS";

    // --------------------------------------------------------------------------------------------------------- CUST_ID

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CUST_ID} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CUST_ID = "CUST_ID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUST_ID = "custId";

    // ------------------------------------------------------------------------------------------------------- EDUCATION

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_EDUCATION} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_EDUCATION = "EDUCATION";

    /**
     * The length of the {@value #COLUMN_NAME_EDUCATION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDUCATION = 21;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDUCATION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDUCATION = "education";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_EDUCATION} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_EDUCATION = COLUMN_LENGTH_EDUCATION;

    // ------------------------------------------------------------------------------------------------------ OCCUPATION

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_OCCUPATION} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_OCCUPATION = "OCCUPATION";

    /**
     * The length of the {@value #COLUMN_NAME_OCCUPATION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_OCCUPATION = 21;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OCCUPATION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_OCCUPATION = "occupation";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_OCCUPATION} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_OCCUPATION = COLUMN_LENGTH_OCCUPATION;

    // -------------------------------------------------------------------------------------------------- HOUSEHOLD_SIZE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_HOUSEHOLD_SIZE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_HOUSEHOLD_SIZE = "HOUSEHOLD_SIZE";

    /**
     * The length of the {@value #COLUMN_NAME_HOUSEHOLD_SIZE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_HOUSEHOLD_SIZE = 21;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HOUSEHOLD_SIZE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_HOUSEHOLD_SIZE = "householdSize";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_HOUSEHOLD_SIZE} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_HOUSEHOLD_SIZE = COLUMN_LENGTH_HOUSEHOLD_SIZE;

    // --------------------------------------------------------------------------------------------------- YRS_RESIDENCE

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_YRS_RESIDENCE} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_YRS_RESIDENCE = "YRS_RESIDENCE";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YRS_RESIDENCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_YRS_RESIDENCE = "yrsResidence";

    // --------------------------------------------------------------------------------------------------- AFFINITY_CARD

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_AFFINITY_CARD} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_AFFINITY_CARD = "AFFINITY_CARD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AFFINITY_CARD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AFFINITY_CARD = "affinityCard";

    /**
     * The precision of the {@value #COLUMN_NAME_AFFINITY_CARD} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AFFINITY_CARD = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_AFFINITY_CARD} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AFFINITY_CARD = 0;

    // --------------------------------------------------------------------------------------------------------- CRICKET

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_CRICKET} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CRICKET = "CRICKET";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CRICKET} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CRICKET = "cricket";

    /**
     * The precision of the {@value #COLUMN_NAME_CRICKET} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CRICKET = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_CRICKET} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CRICKET = 0;

    // -------------------------------------------------------------------------------------------------------- BASEBALL

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_BASEBALL} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_BASEBALL = "BASEBALL";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BASEBALL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BASEBALL = "baseball";

    /**
     * The precision of the {@value #COLUMN_NAME_BASEBALL} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_BASEBALL = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_BASEBALL} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_BASEBALL = 0;

    // ---------------------------------------------------------------------------------------------------------- TENNIS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_TENNIS} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_TENNIS = "TENNIS";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TENNIS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TENNIS = "tennis";

    /**
     * The precision of the {@value #COLUMN_NAME_TENNIS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TENNIS = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_TENNIS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TENNIS = 0;

    // ---------------------------------------------------------------------------------------------------------- SOCCER

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_SOCCER} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_SOCCER = "SOCCER";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SOCCER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SOCCER = "soccer";

    /**
     * The precision of the {@value #COLUMN_NAME_SOCCER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SOCCER = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_SOCCER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SOCCER = 0;

    // ------------------------------------------------------------------------------------------------------------ GOLF

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_GOLF} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_GOLF = "GOLF";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GOLF} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GOLF = "golf";

    /**
     * The precision of the {@value #COLUMN_NAME_GOLF} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_GOLF = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_GOLF} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_GOLF = 0;

    // --------------------------------------------------------------------------------------------------------- UNKNOWN

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_UNKNOWN} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_UNKNOWN = "UNKNOWN";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNKNOWN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNKNOWN = "unknown";

    /**
     * The precision of the {@value #COLUMN_NAME_UNKNOWN} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNKNOWN = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_UNKNOWN} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNKNOWN = 0;

    // ------------------------------------------------------------------------------------------------------------ MISC

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_MISC} attribute maps. The value is {@value}.
     */
    public static final String COLUMN_NAME_MISC = "MISC";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MISC} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MISC = "misc";

    /**
     * The precision of the {@value #COLUMN_NAME_MISC} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MISC = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_MISC} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MISC = 0;

    // -------------------------------------------------------------------------------------------------------- COMMENTS

    /**
     * The name of the table column to which the {@value #ATTRIBUTE_NAME_COMMENTS} attribute maps. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_COMMENTS = "COMMENTS";

    /**
     * The length of the {@value #COLUMN_NAME_COMMENTS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COMMENTS = 4000;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMENTS = "comments";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_COMMENTS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_COMMENTS = COLUMN_LENGTH_COMMENTS;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSupplementaryDemographics() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public final String toString() {
        return super.toString() + '{' +
               "custId=" + custId +
               ",education=" + education +
               ",occupation=" + occupation +
               ",householdSize=" + householdSize +
               ",yrsResidence=" + yrsResidence +
               ",affinityCard=" + affinityCard +
               ",cricket=" + cricket +
               ",baseball=" + baseball +
               ",tennis=" + tennis +
               ",soccer=" + soccer +
               ",golf=" + golf +
               ",unknown=" + unknown +
               ",misc=" + misc +
               ",comments=" + comments +
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
        if (!(obj instanceof MappedSupplementaryDemographics that)) {
            return false;
        }
        return Objects.equals(getCustId(), that.getCustId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the {@code @Id}, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(getCustId());
    }

    // ---------------------------------------------------------------------------------------------------------- custId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    public Long getCustId() {
        return custId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUST_ID} attribute with the specified value.
     *
     * @param custId new value for {@value #ATTRIBUTE_NAME_CUST_ID} attribute.
     */
    protected void setCustId(final Long custId) {
        this.custId = custId;
    }

    // ------------------------------------------------------------------------------------------------------- education

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDUCATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDUCATION} attribute.
     */
    public String getEducation() {
        return education;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDUCATION} attribute with the specified value.
     *
     * @param education new value for {@value #ATTRIBUTE_NAME_EDUCATION} attribute.
     */
    public void setEducation(final String education) {
        this.education = education;
    }

    // ------------------------------------------------------------------------------------------------------ occupation

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OCCUPATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OCCUPATION} attribute.
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OCCUPATION} attribute with the specified value.
     *
     * @param occupation new value for {@value #ATTRIBUTE_NAME_OCCUPATION} attribute.
     */
    public void setOccupation(final String occupation) {
        this.occupation = occupation;
    }

    // --------------------------------------------------------------------------------------------------- householdSize

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HOUSEHOLD_SIZE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HOUSEHOLD_SIZE} attribute.
     */
    public String getHouseholdSize() {
        return householdSize;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HOUSEHOLD_SIZE} attribute with the specified value.
     *
     * @param householdSize new value for {@value #ATTRIBUTE_NAME_HOUSEHOLD_SIZE} attribute.
     */
    public void setHouseholdSize(final String householdSize) {
        this.householdSize = householdSize;
    }

    // ---------------------------------------------------------------------------------------------------- yrsResidence

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YRS_RESIDENCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YRS_RESIDENCE} attribute.
     */
    public Long getYrsResidence() {
        return yrsResidence;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YRS_RESIDENCE} attribute with the specified value.
     *
     * @param yrsResidence new value for {@value #ATTRIBUTE_NAME_YRS_RESIDENCE} attribute.
     */
    public void setYrsResidence(final Long yrsResidence) {
        this.yrsResidence = yrsResidence;
    }

    // ---------------------------------------------------------------------------------------------------- affinityCard

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AFFINITY_CARD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AFFINITY_CARD} attribute.
     */
    public Long getAffinityCard() {
        return affinityCard;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AFFINITY_CARD} attribute with the specified value.
     *
     * @param affinityCard new value for {@value #ATTRIBUTE_NAME_AFFINITY_CARD} attribute.
     */
    public void setAffinityCard(final Long affinityCard) {
        this.affinityCard = affinityCard;
    }

    // --------------------------------------------------------------------------------------------------------- cricket

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CRICKET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CRICKET} attribute.
     */
    public Long getCricket() {
        return cricket;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CRICKET} attribute with the specified value.
     *
     * @param cricket new value for {@value #ATTRIBUTE_NAME_CRICKET} attribute.
     */
    public void setCricket(final Long cricket) {
        this.cricket = cricket;
    }

    // -------------------------------------------------------------------------------------------------------- baseball

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BASEBALL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BASEBALL} attribute.
     */
    public Long getBaseball() {
        return baseball;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BASEBALL} attribute with the specified value.
     *
     * @param baseball new value for {@value #ATTRIBUTE_NAME_BASEBALL} attribute.
     */
    public void setBaseball(final Long baseball) {
        this.baseball = baseball;
    }

    // ---------------------------------------------------------------------------------------------------------- tennis

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TENNIS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TENNIS} attribute.
     */
    public Long getTennis() {
        return tennis;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TENNIS} attribute with the specified value.
     *
     * @param tennis new value for {@value #ATTRIBUTE_NAME_TENNIS} attribute.
     */
    public void setTennis(final Long tennis) {
        this.tennis = tennis;
    }

    // ---------------------------------------------------------------------------------------------------------- soccer

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SOCCER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SOCCER} attribute.
     */
    public Long getSoccer() {
        return soccer;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SOCCER} attribute with the specified value.
     *
     * @param soccer new value for {@value #ATTRIBUTE_NAME_SOCCER} attribute.
     */
    public void setSoccer(final Long soccer) {
        this.soccer = soccer;
    }

    // ------------------------------------------------------------------------------------------------------------ golf

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GOLF} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GOLF} attribute.
     */
    public Long getGolf() {
        return golf;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GOLF} attribute with the specified value.
     *
     * @param golf new value for {@value #ATTRIBUTE_NAME_GOLF} attribute.
     */
    public void setGolf(final Long golf) {
        this.golf = golf;
    }

    // --------------------------------------------------------------------------------------------------------- unknown

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNKNOWN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNKNOWN} attribute.
     */
    public Long getUnknown() {
        return unknown;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNKNOWN} attribute with the specified value.
     *
     * @param unknown new value for {@value #ATTRIBUTE_NAME_UNKNOWN} attribute.
     */
    public void setUnknown(final Long unknown) {
        this.unknown = unknown;
    }

    // ------------------------------------------------------------------------------------------------------------ misc

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MISC} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MISC} attribute.
     */
    public Long getMisc() {
        return misc;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MISC} attribute with the specified value.
     *
     * @param misc new value for {@value #ATTRIBUTE_NAME_MISC} attribute.
     */
    public void setMisc(final Long misc) {
        this.misc = misc;
    }

    // -------------------------------------------------------------------------------------------------------- comments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute with the specified value.
     *
     * @param comments new value for {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    public void setComments(final String comments) {
        this.comments = comments;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_CUST_ID, nullable = false, insertable = true, updatable = false)
    private Long custId;

    @Size(max = SIZE_MAX_EDUCATION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EDUCATION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDUCATION
    )
    private String education;

    @Size(max = SIZE_MAX_OCCUPATION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_OCCUPATION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_OCCUPATION
    )
    private String occupation;

    @Size(max = SIZE_MAX_HOUSEHOLD_SIZE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_HOUSEHOLD_SIZE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_HOUSEHOLD_SIZE
    )
    private String householdSize;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YRS_RESIDENCE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long yrsResidence;

    @Digits(integer = COLUMN_PRECISION_AFFINITY_CARD - COLUMN_SCALE_AFFINITY_CARD,
            fraction = COLUMN_SCALE_AFFINITY_CARD)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AFFINITY_CARD,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long affinityCard;

    @Digits(integer = COLUMN_PRECISION_CRICKET - COLUMN_SCALE_CRICKET, fraction = COLUMN_SCALE_CRICKET)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CRICKET,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long cricket;

    @Digits(integer = COLUMN_PRECISION_BASEBALL - COLUMN_SCALE_BASEBALL, fraction = COLUMN_SCALE_BASEBALL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BASEBALL,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long baseball;

    @Digits(integer = COLUMN_PRECISION_TENNIS - COLUMN_SCALE_TENNIS, fraction = COLUMN_SCALE_TENNIS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TENNIS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long tennis;

    @Digits(integer = COLUMN_PRECISION_SOCCER - COLUMN_SCALE_SOCCER, fraction = COLUMN_SCALE_SOCCER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SOCCER,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long soccer;

    @Digits(integer = COLUMN_PRECISION_GOLF - COLUMN_SCALE_GOLF, fraction = COLUMN_SCALE_GOLF)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GOLF,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long golf;

    @Digits(integer = COLUMN_PRECISION_UNKNOWN - COLUMN_SCALE_UNKNOWN, fraction = COLUMN_SCALE_UNKNOWN)
    @Basic(optional = true)
    // quoted: UNKNOWN is a reserved identifier -- H2 rejects the generated DDL without the quotes, and the quoted
    // name is the upper-case one Oracle holds, so the mapping stays correct against the installed schema too
    @Column(name = '"' + COLUMN_NAME_UNKNOWN + '"',
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long unknown;

    @Digits(integer = COLUMN_PRECISION_MISC - COLUMN_SCALE_MISC, fraction = COLUMN_SCALE_MISC)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MISC,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long misc;

    @Size(max = SIZE_MAX_COMMENTS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMMENTS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COMMENTS
    )
    private String comments;
}
