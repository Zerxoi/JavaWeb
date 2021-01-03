package xyz.zerxoi.dao.impl;

import java.math.BigInteger;
import java.util.List;

import xyz.zerxoi.dao.CommentDao;
import xyz.zerxoi.pojo.Comment;

public class CommentDaoImpl extends BaseDao implements CommentDao {

    @Override
    public Comment queryById(Integer id) {
        String sql = "select * from comment where id = ?";
        return queryForOne(Comment.class, sql, id);
    }

    @Override
    public List<Comment> queryAll() {
        String sql = "select * from comment";
        return queryForList(Comment.class, sql, (Object[]) null);
    }

    @Override
    public Comment queryKthOrderByScore(int k) {
        String sql = "select * from comment order by score limit ?, 1";
        return queryForOne(Comment.class, sql, k - 1);
    }

    @Override
    public int queryNum() {
        String sql = "select count(*) from comment";
        return queryForSingleValue(Long.class, sql, (Object[]) null).intValue();
    }

    @Override
    public int queryRankOrderByScore(Comment comment) {
        String sql = "select (row_num) from (select id, rank () over (order by score) as row_num from comment) score_rank where id = ?";
        return queryForSingleValue(BigInteger.class, sql, comment.getId()).intValue();
    }

    @Override
    public int deleteOneDayAgo() {
        long oneDayAgo = System.currentTimeMillis() - 86400000;
        String sql = "delete from comment where date < ?";
        return update(sql, oneDayAgo);
    }

    @Override
    public int insert(Comment comment) {
        String sql = "insert into comment(`id`, `author`, `date`, `content`, `oo`, `xx`, `tucao`, `read`, `score`) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return update(sql, comment.getId(), comment.getAuthor(), comment.getDate(), comment.getContent(), comment.getOo(), comment.getXx(),
                comment.getTucao(), comment.getRead(), comment.getScore());
    }

    @Override
    public int update(Comment comment) {
        String sql = "update comment set `oo` = ?, `xx` = ?, `tucao` = ?, `read` = ?, `score` = ? where `id` = ?";
        return update(sql, comment.getOo(), comment.getXx(), comment.getTucao(), comment.getRead(), comment.getScore(),
                comment.getId());
    }

}
