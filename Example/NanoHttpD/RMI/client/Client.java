package client;

import compute.Compute;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        if(System.getSecurityManager()==null){
            System.setSecurityManager(new SecurityManager());
        }
        try{
            Registry registry = LocateRegistry.getRegistry(args[0]);
            String name = "Compute";
            Compute compute = (Compute)registry.lookup(name);
            Pi task = new Pi(Integer.valueOf(args[1]));
            BigDecimal pi = compute.executeTask(task);
            System.out.println(pi);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
