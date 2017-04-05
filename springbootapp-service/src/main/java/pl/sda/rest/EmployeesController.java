package pl.sda.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.sda.dao.EmpDAO;
import pl.sda.domain.Employee;

import java.util.List;

/**
 * Created by pzawa on 05.04.2017.
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmpDAO empDAO;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return empDAO.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createEmployee(@RequestBody Employee employee) {
        empDAO.save(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{empno}")
    public void updateEmployee(@PathVariable int empno, @RequestBody Employee employee) {
        if(empno != employee.getEmpno()){
            throw new IllegalArgumentException("empno mismatch");
        }
        empDAO.save(employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{empno}")
    public void deleteEmployee(@PathVariable int empno) {
        empDAO.delete(empno);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{empno}")
    public Employee getEmployee(@PathVariable int empno) {
        return empDAO.getOne(empno);
    }
}
