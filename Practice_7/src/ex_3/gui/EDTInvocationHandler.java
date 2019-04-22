package ex_3.gui;

import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EDTInvocationHandler implements InvocationHandler {
    private Object invocationResult = null;

    private UITasks ui;

    public EDTInvocationHandler(UITasks ui) {
        this.ui = ui;
    }

    @Override
    public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
        if (SwingUtilities.isEventDispatchThread()) {
            invocationResult = method.invoke(ui, args);
        } else {
            Runnable shell = () -> {
                try {
                    invocationResult = method.invoke(ui, args);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);

                }
            };
            SwingUtilities.invokeAndWait(shell);
        }
        return invocationResult;
    }
}
