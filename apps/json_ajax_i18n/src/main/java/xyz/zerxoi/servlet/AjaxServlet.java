package xyz.zerxoi.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import xyz.zerxoi.pojo.Person;

public class AjaxServlet extends BaseServlet {

    private static final long serialVersionUID = 863852638343796360L;

    protected void ajax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = new Person(1, "zerxoi");
        Gson gson = new Gson();
        String json = gson.toJson(person);
        resp.getWriter().write(json);
    }

    protected void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = new Person(1, "zerxoi");
        Gson gson = new Gson();
        String json = gson.toJson(person);
        resp.getWriter().write(json);
    }

    protected void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = new Person(1, "zerxoi");
        Gson gson = new Gson();
        String json = gson.toJson(person);
        resp.getWriter().write(json);
    }

    protected void getJson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = new Person(1, "zerxoi");
        Gson gson = new Gson();
        String json = gson.toJson(person);
        resp.getWriter().write(json);
    }

    protected void formSerialize(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("username: " + req.getParameter("username"));
        System.out.println("password: " + req.getParameter("password"));
        System.out.println("single: " + req.getParameter("single"));
        System.out.println("multiple: " + Arrays.toString(req.getParameterValues("multiple")));
        System.out.println("check: " + Arrays.toString(req.getParameterValues("check")));
        System.out.println("radio: " + req.getParameter("radio"));

        Person person = new Person(1, "zerxoi");
        Gson gson = new Gson();
        String json = gson.toJson(person);
        resp.getWriter().write(json);
    }

}
