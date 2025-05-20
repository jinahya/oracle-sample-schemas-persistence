package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;

@Entity
@Table(name = Channel.TABLE_NAME)
public class Channel extends __MappedEntity<Channel, Long> {

    @Serial
    private static final long serialVersionUID = -6066869450903519163L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "CHANNELS";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    // ---------------------------------------------------------------------------------------------------- CHANNEL_DESC

    // --------------------------------------------------------------------------------------------------- CHANNEL_CLASS

    // ------------------------------------------------------------------------------------------------ CHANNEL_CLASS_ID

    // --------------------------------------------------------------------------------------------------- CHANNEL_TOTAL

    // ------------------------------------------------------------------------------------------------ CHANNEL_TOTAL_ID

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Channel() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ super._id_

    @Override
    protected Long _id_() {
        return getChannelId();
    }

    @Override
    protected void _id_(final Long _id_) {
        setChannelId(_id_);
    }

    // ------------------------------------------------------------------------------------------------------- channelId
    @Nonnull
    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(@Nonnull final Long channelId) {
        this.channelId = channelId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CHANNEL_ID, nullable = false, insertable = true, updatable = false)
    private Long channelId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = 20)
    @NotNull
    @Column(name = "CHANNEL_DESC", nullable = false, length = 20)
    private String channelDesc;

    @Size(max = 20)
    @NotNull
    @Column(name = "CHANNEL_CLASS", nullable = false, length = 20)
    private String channelClass;

    @NotNull
    @Column(name = "CHANNEL_CLASS_ID", nullable = false)
    private Long channelClassId;

    @Size(max = 13)
    @NotNull
    @Column(name = "CHANNEL_TOTAL", nullable = false, length = 13)
    private String channelTotal;

    @NotNull
    @Column(name = "CHANNEL_TOTAL_ID", nullable = false)
    private Long channelTotalId;
}