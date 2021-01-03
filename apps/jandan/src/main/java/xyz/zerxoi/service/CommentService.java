package xyz.zerxoi.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xyz.zerxoi.dao.CommentDao;
import xyz.zerxoi.dao.PicDao;
import xyz.zerxoi.dao.impl.CommentDaoImpl;
import xyz.zerxoi.dao.impl.PicDaoImpl;
import xyz.zerxoi.pojo.Comment;
import xyz.zerxoi.pojo.Pic;

public class CommentService {
    private String baseUrl;
    private static Pattern pattern;
    private static Encoder encoder;

    static {
        pattern = Pattern.compile("^@(\\d+)(.*) ago$");
        encoder = Base64.getEncoder();
    }

    public CommentService(int pageNum) {
        baseUrl = "http://jandan.net/pic/";
        String url = baseUrl;
        try {
            int page = 0;
            while (pageNum > 0) {
                Document document = Jsoup.connect(url).userAgent("Chrome/85.0.4183.121").get();
                if (page == 0)
                    page = parseCurrentPage(document);
                System.out.println(page);
                Elements elements = document.select("li[id^=comment]");
                for (Element element : elements) {
                    Comment comment = parseElement(element);
                    saveComment(comment, element);
                }
                url = baseUrl + encoder.encodeToString(new String("-" + --page).getBytes());
                pageNum--;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int parseCurrentPage(Document document) {
        String pageStr = document.selectFirst(".current-comment-page").text();
        int page = Integer.parseInt(pageStr.substring(1, pageStr.length() - 1));
        return page;
    }

    private Comment parseElement(Element element) {
        int id = Integer.parseInt(element.attr("id").substring(8));
        String author = element.selectFirst("strong").text();
        long date = parseDate(element);
        String content = parseContent(element);
        int oo = Integer.parseInt(element.selectFirst(".tucao-like-container > span").text());
        int xx = Integer.parseInt(element.selectFirst(".tucao-unlike-container > span").text());
        int tucao = new TucaoService(id).getTucaoNum();
        int read = 0;
        double score = Comment.calcuScore(date, oo, xx, tucao, read);
        return new Comment(id, author, date, content, oo, xx, tucao, read, score);
    }

    private long parseDate(Element element) {
        String ago = element.selectFirst("a[href=#footer]").text();
        Matcher m = pattern.matcher(ago);
        long date = System.currentTimeMillis();
        if (m.find()) {
            int n = Integer.parseInt(m.group(1));
            switch (m.group(2)) {
                case "秒":
                    date -= n * 1_000;
                case "分钟":
                    date -= n * 60_000;
                    break;
                case "小时":
                    date -= n * 3_600_000;
                    break;
                case "天":
                    date -= n * 86_400_000;
                    break;
                case "周":
                    date -= n * 604_800_000;
                    break;
            }
        }
        return date;
    }

    private String parseContent(Element element) {
        String content = element.selectFirst(".text p").text();
        int index = content.indexOf("[查看原图]");
        if (index>0) {
            return content.substring(0, index-1);
        }
        return null;
    }

    private void saveComment(Comment comment, Element element) {
        CommentDao commentDao = new CommentDaoImpl();
        if (commentDao.queryById(comment.getId()) == null) {
            commentDao.insert(comment);
            parseAndSavePic(comment, element);
            return;
        }
        commentDao.update(comment);
    }

    private void parseAndSavePic(Comment comment, Element element) {
        PicDao picDao = new PicDaoImpl();
        Elements es = element.select("a.view_img_link");
        for (Element e : es) {
            String url = "http:" + e.attr("href");
            picDao.insert(new Pic(comment.getId(), url));
        }
    }
}
