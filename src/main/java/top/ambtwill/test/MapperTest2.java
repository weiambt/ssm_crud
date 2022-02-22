package top.ambtwill.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ambtwill.dao.DepartmentMapper;
import top.ambtwill.dao.EmployeeMapper;
import top.ambtwill.pojo.Department;
import top.ambtwill.pojo.Employee;

//造数据

@RunWith(SpringJUnit4ClassRunner.class)//运行时使用Spring单元测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"})//绑定Spring配置文件
public class MapperTest2 {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void test(){
        //1.获取Spring容器
        //2.从容器中拿到bean
        //都不用我们自己写了

        System.out.println(departmentMapper);
        //3.执行mapper的方法
        // departmentMapper.insert(new Department(null,"bc"));
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 50 ; i++) {
            // mapper.insert(new Employee(null,"zhang"+i,"M","zhang"+i+"@163.com",1));
        }
        System.out.println("批量完成");

    }
    @Test
    public void test2(){

        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        for (int i = 0; i < 5 ; i++) {
            mapper.insert(new Department(null,"测试部"));
        }
        System.out.println("批量完成");
    }

    @Test
    public void test3(){

        DepartmentMapper mapperd = sqlSession.getMapper(DepartmentMapper.class);
        EmployeeMapper mappere = sqlSession.getMapper(EmployeeMapper.class);
        // for (int i = 1; i < 10 ; i++) {
        //     mapperd.deleteByPrimaryKey(i);
        // }
        for (int i = 1; i <=500 ; i++) {
            // mappere.deleteByPrimaryKey(i);
            String gender="M",name="zhang"+i;
            Integer did=i%5+1;
            if(i%2==1){
                gender="F";
                name="li"+i;
            }
            Employee e=new Employee(10000+i,name,gender,"2222@qq.com",did);
            mappere.insert(e);
        }
        System.out.println("批量完成");
    }
}
