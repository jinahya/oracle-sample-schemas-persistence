package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocation;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = MappedDepartment.TABLE_NAME)
public class Department extends MappedDepartment {

    protected Department() {
        super();
    }

    private Department(@Nullable final DepartmentBuilder builder) {
        super(builder);
        if (getLocationId() == null) {
            setLocation(builder.location());
        }
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof Department that)) {
            return false;
        }
        return Objects.equals(getDepartmentId(), that.getDepartmentId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getDepartmentId());
    }

    @Nullable
    public Location getLocation() {
        return location;
    }

    public void setLocation(@Nullable final Location location) {
        this.location = location;
        super.setLocationId(
                Optional.ofNullable(this.location)
                        .map(MappedLocation::getLocationId)
                        .orElse(null)
        );
    }

//    @ManyToOne(optional = true, fetch = jakarta.persistence.FetchType.LAZY)
//    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID", nullable = true, insertable = false,
//                updatable = false)
//    private Employee employee;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID, referencedColumnName = MappedLocation.COLUMN_NAME_LOCATION_ID,
                nullable = true, insertable = false, updatable = false)
    private Location location;
}
