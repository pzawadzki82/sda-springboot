package pl.sda;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.client.DepartmentsClient;
import pl.sda.domain.Department;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by pzawa on 05.04.2017.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TestConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DepartmentsIntegrationTest {

    private DepartmentsClient departmentsClient;

    @Before
    public void setup(){
        departmentsClient = new DepartmentsClient("http://localhost:8080/springbootapp-service");
    }

    @Test
    public void shouldGetDepartments(){
        List<Department> departmentList = departmentsClient.getDepartments();

        assertNotNull(departmentList);
        assertTrue(departmentList.size() == 4);
    }


}
