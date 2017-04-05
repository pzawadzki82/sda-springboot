package pl.sda.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Created by pzawa on 02.02.2017.
 */

@Entity
@Table(name = "Emp")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "empno")
public class Employee {

    @Id
    private int empno;

    private String ename;

    private String job;

    private Integer manager;

    @Temporal(TemporalType.DATE)
    private Date hiredate;

    private BigDecimal salary;

    private BigDecimal commision;

    @ManyToOne
    @JoinColumn(name = "deptno")
    private Department dept;

    public Employee() {
    }

    public Employee(int empno, String ename, String job, Integer manager, Date hiredate, BigDecimal salary, BigDecimal commision, Department department) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.manager = manager;
        this.hiredate = hiredate;
        this.salary = salary;
        this.commision = commision;
        this.dept = department;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }


    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empno == employee.empno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", manager='" + manager + '\'' +
                ", hiredate=" + hiredate +
                ", salary=" + salary +
                ", commision=" + commision +'}';
    }
}
