/**
 * 
 */
package com.rest.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.rest.entity.Employee;

/**
 * @author k.suba
 *
 */
public interface EmployeeDao extends CrudRepository<Employee, Serializable> {

}
