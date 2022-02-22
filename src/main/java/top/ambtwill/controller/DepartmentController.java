package top.ambtwill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ambtwill.pojo.Department;
import top.ambtwill.pojo.Msg;
import top.ambtwill.service.DepartmentService;

import java.util.List;

/*
    2022/2/14 11:19
    @author 张渭
    Project Name:ssm_crud
     
    theme:

     * 通过AJAX获取到json字符串

*/
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * 添加按钮中查询所有部门
     * @return
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        return Msg.success().add("depts",departmentService.getDepts());
    }

}
