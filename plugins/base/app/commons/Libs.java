package commons;

/**
 * Created by zhangmeng on 16-7-25.
 */
public class Libs {
    public static Integer toInt(Object o, Integer defa){
        try{
            return Integer.valueOf(String.valueOf(o));
        }catch(Exception e){
            return defa;
        }
    }
}
