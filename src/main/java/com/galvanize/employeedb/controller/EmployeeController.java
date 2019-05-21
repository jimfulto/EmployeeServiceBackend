package com.galvanize.employeedb.controller;

import com.galvanize.employeedb.model.Employee;
import com.galvanize.employeedb.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Api is used for Swagger Doc.
 */
@Api(value = "Employee Controller", description = "Operations to manipulate Employees data")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    /**
     * Swagger doc
     *
     * @return
     * @ApiOperation and  @ApiResponses helps putting description in Swagger doc.
     */
    @ApiOperation(value = "Get list of all Employees", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Employee> allEmployees() {

        return empService.getAllEmployees();

    }

}
