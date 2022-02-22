package top.ambtwill.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ambtwill.dao.EmployeeMapper;
import top.ambtwill.pojo.Employee;

import java.util.List;

/*
    2022/2/13 16:09
    @author 张渭
    Project Name:ssm_crud
     
    theme:
*/
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> getAll() {

        List<Employee> list = employeeMapper.selectAllWithDept();
        for (Employee employee : list) {
            System.out.println(employee);
        }
        return list;
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insert(employee);
    }

    /**
     * 查看empName是否重名，这里写死的，要优化
     * @param empName
     * @return
     */
    public boolean checkUser(String empName) {
        // return employeeMapper.checkEmpName(empName);
        if(empName.equals("zhangsan"))
            return false;
        return true;
    }

    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKeyWithDept(id);
    }

    public int updateEmp(Employee employee) {
        int i = employeeMapper.updateByPrimaryKey(employee);
        return i;
    }

    public int deleteEmp(int id) {
        int i = employeeMapper.deleteByPrimaryKey(id);
        return i;
    }
}
