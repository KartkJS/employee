/**
 * 
 */
package com.rest;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

/**
 * @author k.suba
 *
 */
public interface EmployeeDao extends CrudRepository<Employee, Serializable> {

}
