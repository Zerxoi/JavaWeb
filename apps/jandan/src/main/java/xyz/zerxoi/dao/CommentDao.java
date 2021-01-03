package xyz.zerxoi.dao;

import java.util.List;

import xyz.zerxoi.pojo.Comment;

public interface CommentDao {
    public Comment queryById(Integer id);
    public List<Comment> queryAll();
    // k 要求大于 0
    public Comment queryKthOrderByScore(int k);
    public int queryNum();
    public int queryRankOrderByScore(Comment comment);
    public int deleteOneDayAgo();
    public int insert(Comment comment);
    public int update(Comment comment);
}
