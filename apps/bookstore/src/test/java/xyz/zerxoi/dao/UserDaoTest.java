// package xyz.zerxoi.dao;

// import static org.junit.Assert.assertTrue;

// import org.junit.Test;

// import xyz.zerxoi.dao.impl.BaseDao;
// import xyz.zerxoi.dao.impl.UserDaoImpl;
// import xyz.zerxoi.pojo.User;

// public class UserDaoTest {
//     UserDao userDao = new UserDaoImpl();

//     private String[] usernames = new String[] { "admin", "zerxoi", "未来" };
//     private String[] passwords = new String[] { "password", "password", "password" };
//     private User[] users = new User[] { new User(null, "foo", "foobar", "foo@foo.bar"),
//             new User(null, "bar", "foobar", "bar@foo.bar") };

//     @Test
//     public void queryUserByUsername() {
//         for (String username : usernames) {
//             assertTrue("用户名查询失败", userDao.queryUserByUsername(username) != null);
//         }
//     }

//     @Test
//     public void queryUserByUsernameAndPassword() {
//         for (int i = 0; i < usernames.length; i++) {
//             assertTrue("用户名密码查询失败", userDao.queryUserByUsernameAndPassword(usernames[i], passwords[i]) != null);
//         }
//     }

//     @Test
//     public void saveUser() {
//         for (int i = 0; i < users.length; i++) {
//             if (userDao.queryUserByUsername(users[i].getUsername()) != null) {
//                 System.out.println("用户名 " + users[i].getUsername() + " 已存在");
//                 continue;
//             }
//             assertTrue("用户创建失败", userDao.saveUser(users[i]) != -1);
//         }
//     }

//     @Test
//     public void queryForSingleValue() {
//         BaseDao baseDao = (BaseDao) userDao;
//         System.out.println(baseDao.queryForOne(String.class, "select (password) from t_user where username = ?",
//                 "zerxoi"));
//     }
// }