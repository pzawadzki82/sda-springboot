package pl.sda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.domain.Department;

import java.util.List;

/**
 * Created by pzawa on 02.02.2017.
 */
public interface DeptDAO extends JpaRepository<Department, Integer>{
    List<Department> findByDname(String dname);
}
