package top.ambtwill.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ambtwill.dao.DepartmentMapper;
import top.ambtwill.pojo.Department;

/*
    2022/2/12 17:35
    @author 张渭
    Project Name:ssm_crud
     
    theme:
*/

public class MapperTest {
    @Autowired
    private SqlSession sqlSession;

    @Test
    public void test(){
        //1.获取Spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从容器中拿到mapper
        DepartmentMapper mapper = context.getBean(DepartmentMapper.class);
        //3.执行mapper的方法
        mapper.insert(new Department(3,"a"));
    }
    /*
    思考：mapper接口什么时候注册到容器中的？
    答案：在applicationContext.xml中的语句：
            <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
                <!--扫描所以的dao接口的实现-->
                <property name="basePackage" value="top.ambtwill.dao"/>
            </bean>
     */

    @Test
    public void test2(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DepartmentMapper mapper = context.getBean(DepartmentMapper.class);

        // DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        for (int i = 0; i < 5 ; i++) {
            mapper.insert(new Department(null,"aa部"));
        }
        System.out.println("批量完成");
    }
}
