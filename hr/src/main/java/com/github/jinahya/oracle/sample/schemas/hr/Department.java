package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Optional;

@Entity
@Table(name = Department.TABLE_NAME)
public class Department extends __MappedEntity<Department, Integer> {

    @Serial
    private static final long serialVersionUID = 713808132457900386L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "DEPARTMENTS";

    // --------------------------------------------------------------------------------------------------- DEPARTMENT_ID
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DEPARTMENT_ID";

    public static final int COLUMN_PRECISION_DEPARTMENT_ID = 4;

    public static final int COLUMN_VALUE_DEPARTMENT_ID = 0x0000;

    public static final int COLUMN_VALUE_MAX_DEPARTMENT_ID = 0x270F; // 9999

    public static final int MIN_DEPARTMENT_ID = COLUMN_VALUE_DEPARTMENT_ID;

    public static final int MAX_DEPARTMENT_ID = COLUMN_VALUE_MAX_DEPARTMENT_ID;

    // ------------------------------------------------------------------------------------------------- DEPARTMENT_NAME
    public static final String COLUMN_NAME_DEPARTMENT_NAME = "DEPARTMENT_NAME";

    public static final int COLUMN_LENGTH_DEPARTMENT_NAME = 30;

    // ------------------------------------------------------------------------------------------------------ MANAGER_ID
    public static final String COLUMN_NAME_MANAGER_ID = "MANAGER_ID";

    public static final int COLUMN_PRECISION_MANAGER_ID = 6;

    public static final int COLUMN_VALUE_MIN_MANAGER_ID = 0x000000;

    public static final int COLUMN_VALUE_MAX_MANAGER_ID = 0x0F423F; // 999999

    public static final int MIN_MANAGER_ID = COLUMN_VALUE_MIN_MANAGER_ID;

    public static final int MAX_MANAGER_ID = COLUMN_VALUE_MAX_MANAGER_ID;

    // ----------------------------------------------------------------------------------------------------- LOCATION_ID
    public static final String COLUMN_NAME_LOCATION_ID = "LOCATION_ID";

    public static final int COLUMN_PRECISION_LOCATION_ID = 4;

    public static final int COLUMN_VALUE_MIN_LOCATION_ID = 0x0000;

    public static final int COLUMN_VALUE_MAX_LOCATION_ID = 0x270F;

    public static final int MIN_LOCATION_ID = COLUMN_VALUE_MIN_LOCATION_ID;

    public static final int MAX_LOCATION_ID = COLUMN_VALUE_MAX_LOCATION_ID;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Department() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ super._id_
    @Override
    protected final Integer _id_() {
        return getDepartmentId();
    }

    @Override
    protected final void _id_(final Integer _id_) {
        setDepartmentId(_id_);
    }

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // ---------------------------------------------------------------------------------------------------- departmentId
    @Nonnull
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(@Nonnull final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // -------------------------------------------------------------------------------------------------- departmentName
    @Nonnull
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(@Nonnull final String departmentName) {
        this.departmentName = departmentName;
    }

    // ------------------------------------------------------------------------------------------------------- managerId
    @Deprecated(forRemoval = true)
    @Nullable
    protected Integer getManagerId() {
        return managerId;
    }

    @Deprecated(forRemoval = true)
    protected void setManagerId(@Nullable final Integer managerId) {
        this.managerId = managerId;
    }

    // --------------------------------------------------------------------------------------------------------- manager
    @Nullable
    public Employee getManager() {
        return manager;
    }

    public void setManager(@Nullable final Employee manager) {
        this.manager = manager;
        setManagerId(
                Optional.ofNullable(this.manager)
                        .map(Employee::getEmployeeId)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------ locationId
    @Deprecated(forRemoval = true)
    @Nullable
    protected Integer getLocationId() {
        return locationId;
    }

    @Deprecated(forRemoval = true)
    protected void setLocationId(@Nullable final Integer locationId) {
        this.locationId = locationId;
    }

    // -------------------------------------------------------------------------------------------------------- location
    @Nullable
    public Location getLocation() {
        return location;
    }

    public void setLocation(@Nullable final Location location) {
        this.location = location;
        setLocationId(
                Optional.ofNullable(this.location)
                        .map(Location::getLocationId)
                        .orElse(null)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @jakarta.annotation.Nonnull
    @Max(MAX_DEPARTMENT_ID)
    @Min(MIN_DEPARTMENT_ID)
    @Id
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DEPARTMENT_ID, nullable = false, insertable = true, updatable = false,
            precision = COLUMN_PRECISION_DEPARTMENT_ID)
    private Integer departmentId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DEPARTMENT_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_DEPARTMENT_NAME)
    private String departmentName;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @Nullable
    @Max(MAX_MANAGER_ID)
    @Min(MIN_MANAGER_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MANAGER_ID, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_MANAGER_ID)
    private Integer managerId;

    @Nullable
    @Valid
    @ManyToOne(optional = true)
    @JoinColumn(name = COLUMN_NAME_MANAGER_ID, nullable = true, insertable = false, updatable = false)
    private Employee manager;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    @Nullable
    @Max(MAX_LOCATION_ID)
    @Min(MIN_LOCATION_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_LOCATION_ID)
    private Integer locationId;

    @Nullable
    @Valid
    @ManyToOne(optional = true)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID, nullable = true, insertable = false, updatable = false)
    private Location location;
}