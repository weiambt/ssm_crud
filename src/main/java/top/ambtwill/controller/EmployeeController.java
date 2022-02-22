package top.ambtwill.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.ambtwill.pojo.Department;
import top.ambtwill.pojo.Employee;
import top.ambtwill.pojo.Msg;
import top.ambtwill.service.EmployeeService;

import java.util.HashMap;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/list2")
    public String getByAjax(){
        return "list2";
    }

    @RequestMapping("/tes")
    @ResponseBody
    public String tes(@RequestParam("data") String n){
        return n+"1";
    }

    /**
     * 删除员工
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del/{ids}")
    public Msg deleteEmp(@PathVariable("ids") String  ids){
        if(ids.contains("-")){//有短横线就批量删除
            String[] str = ids.split("-");
            for (String s : str) {
                int i = Integer.parseInt(s);
                employeeService.deleteEmp(i);
            }
        }
        else{//删除一个
            employeeService.deleteEmp(Integer.parseInt(ids));
        }
        return Msg.success();
    }

    /**
     * 更新员工，这里的ＳＱＬ有改动
     * @param employee
     * @return
     */
    @RequestMapping(value = "/update/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee){
        System.out.println(employee);

        employeeService.updateEmp(employee);
        return Msg.success();
    }


    /**
     * 根据id回显数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id")Integer id){
        Employee employee = employeeService.getEmp(id);
        System.out.println(employee);
        return Msg.success().add("emp",employee);
    }


    /**
     * 保存员工（后端校验呀优化）
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveEmp")
    public Msg saveEmp(@Valid Employee employee,BindingResult result){//@Valid表示使用JSR303校验emp，校验的信息在result中
        if(result.hasErrors()){
            System.out.println(0);
            //校验失败，在模态框显示检验失败的错误信息
            HashMap<String, Object> map = new HashMap<String, Object>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println("错误字段："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }else {
            System.out.println(2);
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    /**
     * 检查用户名是否唯一（要优化）
     * @param empName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUser")
    public Msg checkUser(String empName){
        boolean res = employeeService.checkUser(empName);
        if(res==true)
            return Msg.success();
        else
            return Msg.fail().add("va_msg","用户名已被注册");
    }

    /**
     * 分页查员工数据，el表达式方式
     * @return
     */
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        //引入PageHelper分页插件
        PageHelper.startPage(pn,5);//第pn页（页码为pn），每页5条数据

        List<Employee> emps = employeeService.getAll();
        // for (Employee emp : emps) {
        //     System.out.println(emp);
        // }
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括有我们查询的数据,传入连续显示的页数
        PageInfo page=new PageInfo(emps,5);
        model.addAttribute("pageInfo",page);

        return "list";
    }

    /**
     * 分页查员工数据，json对象
     * @return
     */
    @ResponseBody
    @RequestMapping("/emps3")
    public Msg getEmps3(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        //引入PageHelper分页插件
        PageHelper.startPage(pn,5);//第pn页（页码为pn），每页5条数据

        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括有我们查询的数据,传入连续显示的页数
        PageInfo page=new PageInfo(emps,5);
        return Msg.success().add("pageInfo",page);
    }

}
