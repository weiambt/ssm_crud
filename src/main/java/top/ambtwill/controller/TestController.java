package top.ambtwill.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ambtwill.dao.EmployeeMapper;
import top.ambtwill.pojo.Department;
import top.ambtwill.pojo.Employee;

import java.util.List;

/*
    2022/2/18 11:26
    @author 张渭
    Project Name:ssm_crud
     
    theme:
*/
@Controller
public class TestController {
    @Autowired
    EmployeeMapper employeeService;

    @RequestMapping("/t1/{id}")
    public void t1(@PathVariable int id){
        System.out.println(id);
    }

    @RequestMapping("/t2/{id}")
    public void t2(int id){
        System.out.println(id);
    }

    //json测试
    @RequestMapping("/test")
    @ResponseBody
    public Department test(){
        return new Department(1,"a");
    }

    //json测试
    @ResponseBody
    @RequestMapping("/emps2")
    public PageInfo getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        //引入PageHelper分页插件
        PageHelper.startPage(pn,5);//第pn页（页码为pn），每页5条数据

        List<Employee> emps = employeeService.selectAllWithDept();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括有我们查询的数据,传入连续显示的页数
        PageInfo page=new PageInfo(emps,5);
        return page;
    }


}
