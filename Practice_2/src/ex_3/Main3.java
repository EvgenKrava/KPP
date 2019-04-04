package ex_3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main3{

    public static Object method(Object o, String methodname, Object... parametrs) {
        Class[] c = new Class[parametrs.length];
        for (int i = 0; i < c.length ; i++) {
            c[i]=parametrs[i].getClass();
            //System.out.println(c[i].getName());
        }
        try {
            Method m = o.getClass().getDeclaredMethod(methodname,c);
            m.setAccessible(true);
            System.out.println("Все прошло успешно, метод обнаружен");
            return m.invoke(o, parametrs);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static void main(String[] args) {
        CL cl =new CL();
        System.out.println(method(cl,"print", 585.0, 2.0));
    }
}
