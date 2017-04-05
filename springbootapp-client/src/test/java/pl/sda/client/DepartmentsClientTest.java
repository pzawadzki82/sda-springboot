package pl.sda.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import pl.sda.domain.Department;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by pzawa on 05.04.2017.
 */
public class DepartmentsClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    private DepartmentsClient departmentsClient;

    @Before
    public void setupClient(){
        departmentsClient = new DepartmentsClient("http://localhost:8080");
    }

    @Test
    public void shoudGetDepartmentByName() throws JsonProcessingException {
        Department department = new Department(77, "HR", "Vegas", new ArrayList<>());
        List<Department> departments = new ArrayList<>();
        departments.add(department);

        ObjectMapper om = new ObjectMapper();
        String serializedDepartment = om.writeValueAsString(departments);

        WireMock.stubFor(get(urlEqualTo("/departments/queries/byDname?dname=HR"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(serializedDepartment)));

        List<Department> resDepartments = departmentsClient.getDepartments("HR");
        assertTrue(resDepartments.size() == 1);
        assertEquals(departments, resDepartments);

        verify(getRequestedFor(urlEqualTo("/departments/queries/byDname?dname=HR")));
    }

}
