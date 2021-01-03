package xyz.zerxoi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;

/**
 * Test
 */
public class GsonTest {

    @Test
    public void gosnTest() {
        new TypeToken<List<String>>() {
        };
    }
}

class TypeToken<T> {
    Type type;

    protected TypeToken() {
        ParameterizedType parameterized = (ParameterizedType) getClass().getGenericSuperclass();
        this.type = parameterized.getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}