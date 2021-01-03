package xyz.zerxoi.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;

import xyz.zerxoi.pojo.User;
import xyz.zerxoi.service.UserService;
import xyz.zerxoi.service.impl.UserServiceImpl;
import xyz.zerxoi.utils.WebUtils;

public class UserServlet extends BaseServlet {

    private static final long serialVersionUID = 2832896465540493468L;

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(new User(), req.getParameterMap());
        String username = user.getUsername();
        UserService userService = new UserServiceImpl();
        user = userService.login(user);
        System.out.println(user);
        if (user == null) {
            req.setAttribute("msg", "用户名或者密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        // 保存到 Session 会话中
        req.getSession().setAttribute("user", user);
        req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        User user = WebUtils.copyParamToBean(new User(), req.getParameterMap());
        String code = req.getParameter("code");

        if (token != null && !token.equalsIgnoreCase(code)) {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            return;
        }
        UserService userService = new UserServiceImpl();
        if (userService.existsUsername(user.getUsername())) {
            req.setAttribute("msg", "用户名 " + user.getUsername() + " 已存在");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            return;
        }
        userService.register(user);
        req.getSession().setAttribute("user", user);
        req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        UserService userService = new UserServiceImpl();
        Map<String, Object> map = new HashMap<>();
        boolean exists = userService.existsUsername(username);
        map.put("exists", exists);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }
}
