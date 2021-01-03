package xyz.zerxoi.service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import xyz.zerxoi.dao.CommentDao;
import xyz.zerxoi.dao.PicDao;
import xyz.zerxoi.dao.impl.CommentDaoImpl;
import xyz.zerxoi.dao.impl.PicDaoImpl;
import xyz.zerxoi.pojo.Comment;
import xyz.zerxoi.pojo.Pic;
import xyz.zerxoi.pojo.Tucao;

public class BaseService {
    private static CommentRandom commentRandom;
    private static Comment comment;
    private static TucaoService tucaoService;
    private static List<Pic> pics;
    private static Timer timer;

    public final static int NEXT_INTERVAL = 5_000;
    public final static int FETCH_INTERVAL = 900_000;
    public final static int CLEAN_INTERVAL = 86_400_000;

    static {
        // 数据随机
        commentRandom = new CommentRandom();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BaseService.nextComment();
            }
        }, NEXT_INTERVAL, NEXT_INTERVAL);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fetch();
            }
        }, FETCH_INTERVAL, FETCH_INTERVAL);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clean();
            }
        }, CLEAN_INTERVAL, CLEAN_INTERVAL);
    }

    public static void nextComment() {
        commentRandom.nextComment();
    }

    public static Comment getComment() {
        comment = commentRandom.getComment();
        PicDao picDao = new PicDaoImpl();
        pics = picDao.queryByCommentId(comment.getId());
        tucaoService = new TucaoService(comment.getId());
        return comment;
    }

    public static List<Tucao> getCommentTucaos() {
        return tucaoService.parseTucao();
    }

    public static List<Tucao> getCommentHotTucaos() {
        return tucaoService.parseHotTucao();
    }

    public static List<Pic> getCommentPics() {
        return pics;
    }

    public static void clean() {
        CommentDao commentDao = new CommentDaoImpl();
        commentDao.deleteOneDayAgo();
        commentRandom.updateAndRandom();
    }

    public static void fetch() {
        new CommentService(1);
        commentRandom.updateAndRandom();
    }
}
