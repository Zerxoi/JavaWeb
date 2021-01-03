package xyz.zerxoi.dao.impl;

import java.util.List;

import xyz.zerxoi.dao.PicDao;
import xyz.zerxoi.pojo.Pic;

public class PicDaoImpl extends BaseDao implements PicDao {

    @Override
    public List<Pic> queryByCommentId(Integer commentId) {
        String sql = "select * from pic where `comment_id` = ?";
        return queryForList(Pic.class, sql, commentId);
    }

    @Override
    public int insert(Pic pic) {
        String sql = "insert into pic(`comment_id`, `url`) values (?, ?)";
        return update(sql, pic.getCommentId(), pic.getUrl());
    }

}
