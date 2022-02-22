package top.ambtwill.pojo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public class Employee {

    private Integer empId;

    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})",message = "用户名必须是2-5位中文或者6-16位英文和数字的组合")
    private String empName;

    private String gender;

    @Email(message = "邮箱格式不正确")
    // @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",
    //         message="邮箱格式不正确")
    private String email;

    //外部依赖的部门（要查出部门的名字）
    private Department department;

    private Integer dId;

    //依赖的部门不要注入
    public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
    }

}