package xyz.zerxoi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class DownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 8161355376137625999L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ServletContext servletContext = getServletContext();

        resp.setContentType(servletContext.getMimeType(name));
        // resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(name, "utf-8")+"\"");
        resp.setHeader("Content-Disposition", "attachment; filename*=utf-8''" + URLEncoder.encode(name, "utf-8"));
        InputStream is = servletContext.getResourceAsStream(name);
        IOUtils.copy(is, resp.getOutputStream());
    }
}
