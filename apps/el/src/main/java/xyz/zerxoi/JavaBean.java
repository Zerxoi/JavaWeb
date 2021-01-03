package xyz.zerxoi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaBean {
    private int age;
    private String name;
    private String[] interests;
    private Map<String, String> map;
    private List<String> list;

    public JavaBean() {

        map = new HashMap<>();
    }

    public JavaBean(int age, String[] interests, Map<String, String> map, List<String> list) {
        this.age = age;
        this.interests = interests;
        this.map = map;
        this.list = list;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return 2077; 
    }

    @Override
    public String toString() {
        return "JavaBean [age=" + age + ", interests=" + Arrays.toString(interests) + ", list=" + list + ", map=" + map
                + "]";
    }

}
