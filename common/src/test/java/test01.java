import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mawandong
 * @date 2018/3/1 0:02
 */
public class test01 {

    Gson gson;

    @Before
    public void before(){
        gson=new Gson();
    }

    @Test
    public void test01(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("1","123");
        System.out.println(gson.toJson(map));
    }
    @Test
    public void test02(){
        Date date = new Date();
        String date01= DateUtil.format(date);
        System.out.println(date01);
    }
}
