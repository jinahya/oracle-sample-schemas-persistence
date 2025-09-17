package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

@SuppressWarnings({
        "unhecked"
})
public abstract class MappedJobBuilder<
        SELF extends MappedJobBuilder<SELF, JOB>,
        JOB extends MappedJob
        >
        extends __MappedEntityBuilder<SELF, JOB> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected MappedJobBuilder(final Class<JOB> entityClass) {
        super(entityClass);
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
