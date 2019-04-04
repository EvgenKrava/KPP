package ex_5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main5 {
    public static void main(String[] args) {
        Class[] classes = {InterfaceMath.class};
        InvocationHandler invocationHandler = new SomeInvocationHandler(new ClassMath());
        Object proxy = Proxy.newProxyInstance( ClassMath.class.getClassLoader(), classes, invocationHandler);
        System.out.println(((InterfaceMath)proxy).coat(10.0, 2));
    }


}
