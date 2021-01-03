package xyz.zerxoi;

public class Greeting {
    private String name;
    private Hello hello;

    public Greeting(String name) {
        this.name = name;
        this.hello = new Hello();
    }

    public String greeting(String friendName) {
        return hello.sayHello(friendName) + " I am " + this.name + ".";
    }
}
