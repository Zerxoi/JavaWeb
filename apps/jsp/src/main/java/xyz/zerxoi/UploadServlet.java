package xyz.zerxoi;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.text.*;

public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = -7044380706449941487L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(req)) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for (FileItem item : list) {
                    if (item.isFormField()) {
                        System.out.println(item.getFieldName() +": " + item.getString("utf-8"));
                    } else {
                        System.out.println(item.getFieldName() +": ");
                        System.out.println();
                        System.out.println(item.getName());
                        item.write(new File("C:/Users/Zerxoi/Desktop/"+StringEscapeUtils.unescapeHtml4(item.getName())));
                    }
                    System.out.println();
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
