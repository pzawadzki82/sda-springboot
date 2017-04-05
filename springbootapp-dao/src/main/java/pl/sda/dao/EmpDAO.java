package pl.sda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.domain.Employee;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by pzawa on 02.02.2017.
 */
public interface EmpDAO extends JpaRepository<Employee, Integer> {
}
