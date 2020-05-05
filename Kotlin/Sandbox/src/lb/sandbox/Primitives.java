package lb.sandbox;

import java.util.HashMap;
import java.util.Map;

public abstract class Primitives {

    public static final Map<Class<?>, Class<?>> wrappers;

    static  {
        wrappers = new HashMap<>(8);
        wrappers.put(boolean.class, Boolean.class);
        wrappers.put(char.class, Character.class);
        wrappers.put(byte.class, Byte.class);
        wrappers.put(short.class, Short.class);
        wrappers.put(int.class, Integer.class);
        wrappers.put(long.class, Long.class);
        wrappers.put(float.class, Float.class);
        wrappers.put(double.class, Double.class);
    }


}
