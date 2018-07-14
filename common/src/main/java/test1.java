import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class test1 {

    public static void main(String[] args){
        List<String>list=new ArrayList<String>();
        list.add("1223456");
        list.add("qwer");
        list.add("zxcv");
        String s=list.toString();
//        String[] ss=(String[])list.toArray();
        System.out.print(s);
//        System.out.print(ss.toString());
        String s1 = JSON.toJSONString(list);
        System.out.print(s1);
        StringUtils.isEmpty("");
        CollectionUtils.isEmpty(null);

    }
}
