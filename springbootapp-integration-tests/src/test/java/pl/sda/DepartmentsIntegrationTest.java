package pl.sda;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.client.DepartmentsClient;
import pl.sda.domain.Department;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
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

    @Autowired
    private DataSource dataSource;

    @Value("${client.url}")
    private String clientUrl;

    @Before
    public void setup() throws SQLException, IOException {
        this.departmentsClient = new DepartmentsClient(clientUrl);
        TestUtil.cleanUpDatabase(dataSource.getConnection());
    }

    @After
    public void clean() throws SQLException, IOException {
        TestUtil.cleanUpDatabase(dataSource.getConnection());
    }

    @Test
    public void shouldGetDepartments(){
        List<Department> departmentList = departmentsClient.getDepartments();

        assertNotNull(departmentList);
        assertTrue(departmentList.size() == 4);
    }


}
