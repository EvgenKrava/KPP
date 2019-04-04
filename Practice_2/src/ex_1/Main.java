package ex_1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void method(Class clazz) {

        System.out.println(clazz.getName());
        if(clazz.isPrimitive()) return;
        System.out.println(clazz.getPackage());
        System.out.println(clazz.getSuperclass().getName());
        System.out.println("Список полей:");
        for (Field f:clazz.getDeclaredFields()) {
            System.out.println("\t" + f.toGenericString());
        }
        System.out.println("Список конструкторов:");
        for (Constructor c:clazz.getDeclaredConstructors()
             ) {
            System.out.println("\t"+c.toGenericString());
        }

        System.out.println("Список методов:");
        for (Method m:clazz.getDeclaredMethods()
        ) {
            System.out.println("\t"+m.toGenericString());
        }



        System.out.println();
    }

    public static void main(String[] args) {
        method(Double.class);

    }
}
