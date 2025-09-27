package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

@SuppressWarnings({
        "unchecked",
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class MappedJobBuilder<
        SELF extends MappedJobBuilder<SELF, TARGET>,
        TARGET extends MappedJob
        >
        extends _MappedHrEntityBuilder<SELF, TARGET> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedJobBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // ----------------------------------------------------------------------------------------------------------- jobId
    public String jobId() {
        return jobId;
    }

    public SELF jobId(final String jobId) {
        this.jobId = jobId;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle
    public String jobTitle() {
        return jobTitle;
    }

    public SELF jobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- minSalary
    public Integer minSalary() {
        return minSalary;
    }

    public SELF minSalary(final Integer minSalary) {
        this.minSalary = minSalary;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------- maxSalary
    public Integer maxSalary() {
        return maxSalary;
    }

    public SELF maxSalary(final Integer maxSalary) {
        this.maxSalary = maxSalary;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    private String jobTitle;

    private Integer minSalary;

    private Integer maxSalary;
}
