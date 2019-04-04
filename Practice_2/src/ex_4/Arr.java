package ex_4;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

public class Arr{

    Class type;

    private Object array;

    Arr(String classname, int... size) {
        try {
            switch (classname){
                case "int":
                    type=int.class;
                    array = Array.newInstance(int.class, size);
                    break;
                case "long":
                    type=long.class;
                    array = Array.newInstance(long.class, size);
                    break;
                case "double":
                    type=double.class;
                    array = Array.newInstance(double.class, size);
                    break;
                case "short":
                    type = short.class;
                    array = Array.newInstance(short.class, size);
                    break;
                case "boolean":
                    type = boolean.class;
                    array = Array.newInstance(boolean.class, size);
                    break;
                case "char":
                    type = char.class;
                    array = Array.newInstance(char.class, size);
                    break;
                case "byte":
                    type = byte.class;
                    array = Array.newInstance(byte.class, size);
                    break;
                case "float":
                    type=  float.class;
                    array = Array.newInstance(float.class, size);
                    break;
                    default:
                        type=Class.forName(classname);
                        array = Array.newInstance(Class.forName(classname),size);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class not found!");
        }
    }

    public String toString(){
        String result = array.getClass().toGenericString()+" = { ";
        if(array.getClass().toGenericString().substring(array.getClass().toGenericString().length()-4, array.getClass().toGenericString().length()).equals("[][]")){
            for (int i = 0; i < Array.getLength(array); i++) {
                result+="{ ";
                for (int j = 0; j <Array.getLength(Array.get(array, i)) ; j++) {
                    result +=  Array.get(Array.get(array,i),j)+" ";
                }
                result+=" } ";
            }
        }else{
            for (int i = 0; i < Array.getLength(array); i++) {
                result += Array.get(array, i)+" ";
            }

        }

        return result+" }";
    }

    public void setSize(int height, int width){
        Object tmp = Array.newInstance(type, height, width);

        for (int i = 0; i < Math.min(Array.getLength(array),Array.getLength(tmp)); i++) {
            System.arraycopy(Array.get(array, i) ,0, Array.get(tmp,i),0,Math.min(Array.getLength(Array.get(array,i)), Array.getLength(Array.get(tmp,i))));
        }
        array=tmp;
    }

    public void setSize(int size){
        Object tmp = Array.newInstance(type, size);
        System.arraycopy(array,0,tmp,0, Math.min(Array.getLength(array),Array.getLength(tmp)));
        array=tmp;
    }

    public void createMatrix(int i, int j) throws NoSuchFieldException, IllegalAccessException {
        array = Array.newInstance(type, i,j);
    }

    public int getSize(){
        return Array.getLength(array);
    }

    public int getSize(int i){
        return Array.getLength(Array.get(array, i));
    }

    public void set(Object value, int i){
        Array.set(array, i, value);
    }

    public void set(Object value, int submass, int elemrnt){
        Array.set(Array.get(array,submass),elemrnt,value);
    }

    public Object get(int i){
        return Array.get(array, i);
    }

    public Object get(int i, int j){
        return Array.get(Array.get(array, i), j);
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Arr arr = new Arr("boolean", 5);
        System.out.println(arr);

    }
}
