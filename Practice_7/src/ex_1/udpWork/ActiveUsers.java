package ex_1.udpWork;

import java.io.Serializable;
import java.util.ArrayList;

public class ActiveUsers implements Serializable {
    ArrayList<User> users;

    public ActiveUsers(){
        users = new ArrayList<>();
    }

    public boolean add(User user){
        return users.add(user);
    }

    public boolean isEmpty(){
        return users.isEmpty();
    }

    public int size(){
        return users.size();
    }

    public boolean contains(User user){
        return users.contains(user);
    }

    public User get(int i){
        return users.get(i);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (User u : users)
            buf.append(u).append("\n");
        return buf.toString();
    }
}
