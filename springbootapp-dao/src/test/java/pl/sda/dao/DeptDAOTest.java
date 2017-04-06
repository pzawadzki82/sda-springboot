package pl.sda.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pl.sda.domain.Department;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by zawadzki on 2017-04-06.
 */

@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
public class DeptDAOTest {

    @Configuration
    @PropertySource("dao.properties")
    @EnableJpaRepositories(basePackages = {"pl.sda.dao"})
    @EntityScan(basePackages = {"pl.sda.domain"})
    @Import({HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, DataSourceAutoConfiguration.class})
    public static class TestConfig {
    }

    @Autowired
    private DeptDAO deptDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    public void shouldFindByDname(){
        List<Department> departmentList = deptDAO.findByDname("Sales");
        assertNotNull(departmentList);
        assertTrue(departmentList.size() == 1);
    }

    @Before
    public void setup() throws SQLException, IOException {
        TestUtil.cleanUpDatabase(dataSource.getConnection());
    }

    @After
    public void clean() throws SQLException, IOException {
        TestUtil.cleanUpDatabase(dataSource.getConnection());
    }
}
