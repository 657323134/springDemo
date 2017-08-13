import com.alibaba.fastjson.JSONObject;

/**
 * Created by liuyan on 2017/7/24.
 */
public class Test {
    public static void main(String[] args) {
        /*String s= "{ \"relation\":[{\"goodsId\":19,\"map\":[{\"sim\":DDDFDFFD,\"imei\":DDD}]}]}";
        JSONObject simJson = JSONObject.parseObject(s);
        System.out.println(simJson);*/
        new Test().test("刘岩","student1","student2");
    }

    private void test(String name ,String... students) {
        System.out.println(name);

//        for (String student: students
//             ) {
//            System.out.println(student);
//        }
//        for (int i=0;i<)
    }
}
