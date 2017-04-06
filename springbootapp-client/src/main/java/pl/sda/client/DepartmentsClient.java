package pl.sda.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sda.domain.Department;

import java.util.*;

/**
 * Created by pzawa on 05.04.2017.
 */
public class DepartmentsClient {
    private final String url;
    private final RestTemplate restTemplate;

    public DepartmentsClient(String url){
        this.url = url;
        this.restTemplate = new RestTemplate();
    }

    public List<Department> getDepartments(String dname){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/departments/queries/byDname")
                .queryParam("dname", dname);

        Department[] departments = restTemplate.getForObject(builder.build().encode().toUri(), Department[].class);

        return Arrays.asList(departments);
    }

    public List<Department> getDepartments(){
        Department[] departments = restTemplate.getForObject(url+"/departments", Department[].class);

        return Arrays.asList(departments);
    }

    public void createDepartment(Department department){
        ResponseEntity<Void> response = restTemplate.postForEntity(url+"/departments",department, Void.class);
    }

    public void updateDepartment(Department department){
        Map<String, String> params = new HashMap<String, String>();
        params.put("deptno", String.valueOf(department.getDeptno()));

        restTemplate.put(url+"/departments/{deptno}",department, params);
    }

    public void deleteDepartment(int deptno){
        Map<String, String> params = new HashMap<String, String>();
        params.put("deptno", String.valueOf(deptno));

        restTemplate.delete(url+"/departments/{deptno}", params);
    }
}
