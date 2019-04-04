package ex_5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SomeInvocationHandler implements InvocationHandler{

    InterfaceMath interfaceMath;

    SomeInvocationHandler(InterfaceMath interfaceMath){
        this.interfaceMath=interfaceMath;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.print( objects[0]+" в степени " +objects[1]+ " равно ");
        return method.invoke(interfaceMath, objects);
    }
}