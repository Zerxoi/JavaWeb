package xyz.zerxoi.service;

import java.util.Random;

import xyz.zerxoi.dao.CommentDao;
import xyz.zerxoi.dao.impl.CommentDaoImpl;
import xyz.zerxoi.pojo.Comment;

public class CommentRandom {
    private Random r = new Random();
    private int length;
    private int k1;
    private double p;
    private Comment comment;

    public CommentRandom() {
        updateAndRandom();
    }

    // 数据库更新时调用
    public void updateAndRandom() {
        CommentDao commentDao = new CommentDaoImpl();
        length = commentDao.queryNum();
        p = 4.0 / length;
        k1 = r.nextInt(length) + 1;
        comment = commentDao.queryKthOrderByScore(k1);
    }

    public Comment getComment() {
        return comment;
    }

    public Comment nextComment() {
        Comment tmpComm = comment;
        int k2 = r.nextInt(length) + 1;
        while (k2 <= k1 && r.nextDouble() > Math.pow(1 - p, k1 - k2)) {
            k2 = r.nextInt(length) + 1;
        }
        tmpComm.setRead(tmpComm.getRead() + 1);

        double score = Comment.calcuScore(tmpComm.getDate(), tmpComm.getOo(), tmpComm.getXx(), tmpComm.getTucao(),
                tmpComm.getRead());
        tmpComm.setScore(score);
        CommentDao commentDao = new CommentDaoImpl();
        commentDao.update(tmpComm);
        comment = commentDao.queryKthOrderByScore(k2);
        k1 = commentDao.queryRankOrderByScore(comment);
        return tmpComm;
    }

}
