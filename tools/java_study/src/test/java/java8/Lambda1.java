package java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author mawandong
 * @date 2019/3/20 22:11
 */
public class Lambda1 {

    @Test
    public void test01() {
        Runnable r1 = () -> System.out.println("xxx");
        List<String> list = Arrays.asList("x", "xxxx");

        list.forEach(System.out::println);


    }

}
