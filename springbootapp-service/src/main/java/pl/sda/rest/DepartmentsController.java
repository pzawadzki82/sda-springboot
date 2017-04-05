package pl.sda.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.sda.dao.DeptDAO;
import pl.sda.domain.Department;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by pzawa on 05.04.2017.
 */
@RestController
@RequestMapping("/departments")
public class DepartmentsController {

    @Autowired
    private DeptDAO deptDAO;

    @RequestMapping(method = RequestMethod.GET)
    public List<Department> getDepartments() {
        return deptDAO.findAll();
    }

    @RequestMapping(path = "/queries/byDname", method = RequestMethod.GET)
    public List<Department> getDepartments(@RequestParam(value = "dname") String dname) {
        return deptDAO.findByDname(dname);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDepartament(@RequestBody Department department) {
        deptDAO.save(department);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{deptno}")
    public void updateDepartment(@PathVariable int deptno, @RequestBody Department department) {
        if(deptno != department.getDeptno()){
            throw new IllegalArgumentException("deptno mismatch");
        }
        deptDAO.save(department);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{deptno}")
    public void deleteDepartment(@PathVariable int deptno) {
        deptDAO.delete(deptno);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{deptno}")
    public Department getDepartment(@PathVariable int deptno) {
        return deptDAO.getOne(deptno);
    }
}
