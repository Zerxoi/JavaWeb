package xyz.zerxoi;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String password;
    private String phone;

    public User() {
    }

    public User(Integer id, String name, Integer age, String password, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User [age=" + age + ", id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone
                + "]";
    }
}
