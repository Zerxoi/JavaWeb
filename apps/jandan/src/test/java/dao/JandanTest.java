package dao;

import static org.junit.Assert.assertFalse;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import xyz.zerxoi.dao.CommentDao;
import xyz.zerxoi.dao.PicDao;
import xyz.zerxoi.dao.impl.CommentDaoImpl;
import xyz.zerxoi.dao.impl.PicDaoImpl;
import xyz.zerxoi.pojo.Comment;
import xyz.zerxoi.pojo.Pic;
import xyz.zerxoi.service.BaseService;
import xyz.zerxoi.service.CommentRandom;
import xyz.zerxoi.service.CommentService;
import xyz.zerxoi.service.TucaoService;
import xyz.zerxoi.utils.JdbcUtils;

public class JandanTest {
    @Test
    public void pageParserTest() {
        new CommentService(10);
    }

    // @Test
    // public void rankTest() {
    //     CommentDao commentDao = new CommentDaoImpl();
    //     Comment comment = commentDao.queryKthOrderByScore(232);
    //     assertFalse(commentDao.queryRankOrderByScore(comment) != 232);
    // }

    // @Test
    // public void randomTest() {
    //     CommentRandom commentRandom = new CommentRandom();
    //     for (int i = 0; i < 1000; i++) {
    //         Comment comment = commentRandom.nextComment();
    //         System.out.println(comment.getId() + ": " + comment.getScore());
    //     }
    // }

    // @Test
    // public void tucaoTest() {
    //     TucaoService tucaoService = new TucaoService(4751610);
    //     System.out.println(tucaoService.parseTucao());
    //     System.out.println(tucaoService.parseHotTucao());
    // }

    // @Test
    // public void picTest() {
    //     PicDao picDao = new PicDaoImpl();
    //     List<Pic> pics = picDao.queryByCommentId(4748164);
    //     for (Pic pic : pics) {
    //         System.out.println(pic);
    //     }
    // }

    // @Test
    // public void baseServiceTest() {
    //     System.out.println(BaseService.getComment());
    //     System.out.println(BaseService.getCommentPics());
    //     System.out.println(BaseService.getCommentHotTucaos());
    //     System.out.println(BaseService.getCommentTucaos());
    // }

}
