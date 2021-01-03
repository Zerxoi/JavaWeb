package xyz.zerxoi;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class I18nTest {
    @Test
    public void localTest() {
        Locale locale = Locale.getDefault();
        System.out.println(locale);
        System.out.println(Locale.CHINA);
        System.out.println(Locale.SIMPLIFIED_CHINESE);
        System.out.println(Locale.TRADITIONAL_CHINESE);
        System.out.println(Locale.US);
    }

    @Test
    public void i18nTest() {
        Locale locale = Locale.US;
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);
        System.out.println("username: " + bundle.getString("username"));
        System.out.println("password: "+bundle.getString("password"));
        System.out.println("sex: "+bundle.getString("sex"));
        System.out.println("age: "+bundle.getString("age"));
    }
    
}
