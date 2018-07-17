import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mawandong
 * @date 2018/3/1 0:02
 */
public class test01 {
    private static final Logger logger=LoggerFactory.getLogger(test01.class);
    Gson gson=new Gson();

    @Before
    public void before(){

    }

    @Test
    public void test01(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("1","123");
        System.out.println(gson.toJson(map));
        logger.info(gson.toJson(map));
    }
    @Test
    public void test02(){
        Date date = new Date();
        String date01= DateUtil.format(date);
        System.out.println(date01);
    }
}
