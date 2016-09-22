package com.rest.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.dao.EmployeeDao;
import com.rest.entity.Employee;

@Path("ui")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("/create/employee")
    public Response createEmployee(final Employee employee) {
        Employee createdEmployee = this.employeeDao.save(employee);
        Employee selectedEmp = this.employeeDao.findOne(Long.valueOf(createdEmployee.getEmpId()));
        return Response.ok(selectedEmp)
                .entity(selectedEmp)
                .build();
    }

    @DELETE
    @Path("/employee/{id}")
    public Response deleteEmployee(@PathParam("id") final String id) {
        Employee deleteEmp = this.employeeDao.findOne(Long.valueOf(id));
        this.employeeDao.delete(deleteEmp);
        return Response.noContent()
                .build();
    }

    @GET
    @Path("/employee/{id}")
    public Response getEmployee(@PathParam("id") final String id) {
        Employee selectedEmp = this.employeeDao.findOne(Long.valueOf(id));
        if (selectedEmp == null) {
            throw new NotFoundException();
        }
        return Response.ok(selectedEmp)
                .build();
    }

    @GET
    @Path("/employees")
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = (ArrayList<Employee>) this.employeeDao.findAll();
        return employees;
    }
}