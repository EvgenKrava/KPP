package ex_2;


import ex_5.ClassMath;

import java.lang.reflect.Field;

public class Main {

    public static void method(Object o){
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields) {
            f.setAccessible(true);
            try {
                System.out.println(f.getName()+" "+f.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        String s = "";
        method(s);
    }
}
