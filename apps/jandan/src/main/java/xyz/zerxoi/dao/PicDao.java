package xyz.zerxoi.dao;

import java.util.List;

import xyz.zerxoi.pojo.Pic;

public interface PicDao {
    List<Pic> queryByCommentId(Integer commentId);
    int insert(Pic pic);
}
