package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedDepartment;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedEmployee;
import com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped.MappedLocation;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
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

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
        setManagerId(
                Optional.ofNullable(this.manager)
                        .map(MappedEmployee::getEmployeeId)
                        .orElse(null)
        );
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

    List<@Valid @NotNull Employee> getEmployees() {
        return employees;
    }

    void setEmployees(
            List<@Valid @NotNull Employee> employees) {
        this.employees = employees;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = MappedDepartment.COLUMN_NAME_MANAGER_ID, nullable = true, insertable = false,
                updatable = false)
    private Employee manager;

    @Nullable
    @Valid
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID, nullable = true, insertable = false, updatable = false)
    private Location location;

    // -----------------------------------------------------------------------------------------------------------------
    @OneToMany(mappedBy = MappedEmployee.ATTRIBUTE_NAME_DEPARTMENT, fetch = FetchType.LAZY)
    private List<@Valid @NotNull Employee> employees; // what about 'staffs'?
}
