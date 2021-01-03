// package xyz.zerxoi.service;

// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import xyz.zerxoi.pojo.User;
// import xyz.zerxoi.service.impl.UserServiceImpl;

// public class UserServiceTest {
//     UserService userService = new UserServiceImpl();

//     @Test
//     public void register() {
//         User user = new User(null, "486", "re0", "486@re0.com");
//         if (userService.existsUsername(user.getUsername())) {
//             System.err.println("该用户名已存在");
//             return;
//         }
//         userService.register(user);
//     }

//     @Test
//     public void login() {
//         assertTrue("登陆失败", userService.login(new User(null, "486", "re0", null)) != null);
//         assertTrue("用户名或者密码错误", userService.login(new User(null, "486", "abaaba", null)) == null);
//     }

//     @Test
//     public void existsUsername() {
//         assertTrue("注册失败", userService.existsUsername("486"));
//     }
// }
