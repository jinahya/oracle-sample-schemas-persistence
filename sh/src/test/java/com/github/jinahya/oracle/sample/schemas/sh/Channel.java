package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = Channel.TABLE_NAME)
public class Channel implements __MappedEntity<Long> {

    public static final String TABLE_NAME = "CHANNELS";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_ID
    public static final String COLUMN_NAME_CHANNEL_ID = "CHANNEL_ID";

    public static final String ATTRIBUTE__NAME_CHANNEL_ID = "CHANNEL_ID";

    // ------------------------------------------------------------------------------------------------------ CHANNEL_DESC
    public static final String COLUMN_NAME_CHANNEL_DESC = "CHANNEL_DESC";

    public static final int COLUMN_LENGTH_CHANNEL_DESC = 20;

    public static final String ATTRIBUTE__NAME_CHANNEL_DESC = "CHANNEL_DESC";

    public static final int SIZE_MAX_CHANNEL_DESC = COLUMN_LENGTH_CHANNEL_DESC;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected Channel() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "id=" + id +
                ",channelDesc=" + channelDesc +
                ",channelClass=" + channelClass +
                ",channelClassId=" + channelClassId +
                ",channelTotal=" + channelTotal +
                ",channelTotalId=" + channelTotalId +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Channel channel)) return false;
        return Objects.equals(getId(), channel.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    // -------------------------------------------------------------------------------------------------------------- id
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    // ----------------------------------------------------------------------------------------------------- channelDesc
    @Nonnull
    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(@Nonnull final String channelDesc) {
        this.channelDesc = channelDesc;
    }

    // ---------------------------------------------------------------------------------------------------- channelClass
    @Nonnull
    public String getChannelClass() {
        return channelClass;
    }

    public void setChannelClass(@Nonnull final String channelClass) {
        this.channelClass = channelClass;
    }

    // ---------------------------------------------------------------------------------------------------- channelClass
    @Nonnull
    public Long getChannelClassId() {
        return channelClassId;
    }

    public void setChannelClassId(@Nonnull final Long channelClassId) {
        this.channelClassId = channelClassId;
    }

    // ---------------------------------------------------------------------------------------------------- channelTotal
    @Nonnull
    public String getChannelTotal() {
        return channelTotal;
    }

    public void setChannelTotal(@Nonnull final String channelTotal) {
        this.channelTotal = channelTotal;
    }

    // -------------------------------------------------------------------------------------------------- channelTotalId
    @Nonnull
    public Long getChannelTotalId() {
        return channelTotalId;
    }

    public void setChannelTotalId(@Nonnull final Long channelTotalId) {
        this.channelTotalId = channelTotalId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_CHANNEL_ID, nullable = false)
    private Long id;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = SIZE_MAX_CHANNEL_DESC)
    @NotNull
    @Column(name = COLUMN_NAME_CHANNEL_DESC, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_CHANNEL_DESC)
    private String channelDesc;

    @Nonnull
    @Size(max = 20)
    @NotNull
    @Column(name = "CHANNEL_CLASS", nullable = false, length = 20)
    private String channelClass;

    @Nonnull
    @NotNull
    @Column(name = "CHANNEL_CLASS_ID", nullable = false)
    private Long channelClassId;

    @Nonnull
    @Size(max = 13)
    @NotNull
    @Column(name = "CHANNEL_TOTAL", nullable = false, length = 13)
    private String channelTotal;

    @Nonnull
    @NotNull
    @Column(name = "CHANNEL_TOTAL_ID", nullable = false)
    private Long channelTotalId;
}
