package xyz.zerxoi;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import xyz.zerxoi.pojo.Person;

public class JsonTest {
    private Gson gson = new Gson();

    @Test
    public void TestList() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Tohru"));
        persons.add(new Person(2, "Kaguya"));
        String jsonString = gson.toJson(persons);
        System.out.println(jsonString);

        List<Person> jsonList = gson.fromJson(jsonString, new TypeToken<List<Person>>() {
        }.getType());
        System.out.println(jsonList);

        System.out.println(jsonList.get(0));
    }
}
