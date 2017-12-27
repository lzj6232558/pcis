import cn.wolfcode.crm.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestD {
    @Autowired
    private EmployeeMapper mapper;

    @Test
    public void testMethod() throws  Exception{
        mapper.selectByPrimaryKey(1L);
    }

    @Test
    public void de() throws  Exception{
        ThreadLocalRandom current = ThreadLocalRandom.current();
        int i = current.nextInt(34, 10000000);
        System.out.println(i);
    }
    @Test
    public void d() throws  Exception{
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);    //  泛型.  表示要
    }




}
