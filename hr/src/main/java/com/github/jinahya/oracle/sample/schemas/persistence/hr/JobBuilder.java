package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

public class JobBuilder
        extends __MappedEntityBuilder<JobBuilder, Job> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    JobBuilder() {
        super(Job.class);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------- Bean-Validation

    // ----------------------------------------------------------------------------------------------------------- jobId
    public String jobId() {
        return jobId;
    }

    public JobBuilder jobId(final String jobId) {
        this.jobId = jobId;
        return this;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle
    public String jobTitle() {
        return jobTitle;
    }

    public JobBuilder jobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    // ------------------------------------------------------------------------------------------------------- minSalary
    public Integer minSalary() {
        return minSalary;
    }

    public JobBuilder minSalary(final Integer minSalary) {
        this.minSalary = minSalary;
        return this;
    }

    // ------------------------------------------------------------------------------------------------------- maxSalary
    public Integer maxSalary() {
        return maxSalary;
    }

    public JobBuilder maxSalary(final Integer maxSalary) {
        this.maxSalary = maxSalary;
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String jobId;

    // -----------------------------------------------------------------------------------------------------------------
    private String jobTitle;

    private Integer minSalary;

    private Integer maxSalary;
}
