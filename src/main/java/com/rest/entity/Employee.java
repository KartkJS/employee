package com.rest.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(
        name = "emp1000")
@XmlRootElement
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long empId;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private BigDecimal salary;

    public Long getEmpId() {
        return this.empId;
    }

    public void setEmpId(final Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(final BigDecimal salary) {
        this.salary = salary;
    }
}