package com.example.demo01.model;

public class Thesis {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column THESIS.ID
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column THESIS.TITTLE
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    private String tittle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column THESIS.GMT_CREATE
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column THESIS.GMT_MODIFIED
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column THESIS.CREATOR
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    private Long creator;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column THESIS.ID
     *
     * @return the value of THESIS.ID
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column THESIS.ID
     *
     * @param id the value for THESIS.ID
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column THESIS.TITTLE
     *
     * @return the value of THESIS.TITTLE
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column THESIS.TITTLE
     *
     * @param tittle the value for THESIS.TITTLE
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public void setTittle(String tittle) {
        this.tittle = tittle == null ? null : tittle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column THESIS.GMT_CREATE
     *
     * @return the value of THESIS.GMT_CREATE
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column THESIS.GMT_CREATE
     *
     * @param gmtCreate the value for THESIS.GMT_CREATE
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column THESIS.GMT_MODIFIED
     *
     * @return the value of THESIS.GMT_MODIFIED
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column THESIS.GMT_MODIFIED
     *
     * @param gmtModified the value for THESIS.GMT_MODIFIED
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column THESIS.CREATOR
     *
     * @return the value of THESIS.CREATOR
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column THESIS.CREATOR
     *
     * @param creator the value for THESIS.CREATOR
     *
     * @mbg.generated Tue Apr 14 20:26:11 CST 2020
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
}