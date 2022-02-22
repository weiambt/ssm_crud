package top.ambtwill.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import top.ambtwill.dao.DepartmentMapper;
import top.ambtwill.dao.EmployeeMapper;
import top.ambtwill.pojo.Department;
import top.ambtwill.pojo.Employee;

import java.util.List;

/*
    2022/2/14 11:20
    @author 张渭
    Project Name:ssm_crud
     
    theme:
*/
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts(){
         return departmentMapper.selectAll();
    }

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void t(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DepartmentMapper mapper1 = context.getBean(DepartmentMapper.class);

        System.out.println(mapper1.selectByPrimaryKey(1));

        // List<Department> departments= mapper.selectAll();

        // for (Department department : departments) {
        //     System.out.println(department);
        // }

        EmployeeMapper mapper2 = context.getBean(EmployeeMapper.class);
        Employee employee = mapper2.selectByPrimaryKeyWithDept(10001);
        System.out.println(employee);
    }

}
