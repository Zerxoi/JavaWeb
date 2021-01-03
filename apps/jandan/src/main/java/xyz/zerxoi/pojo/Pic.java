package xyz.zerxoi.pojo;

public class Pic {
    private Integer commentId;
    private String url;

    public Pic() {
    }

    public Pic(Integer commentId, String url) {
        this.commentId = commentId;
        this.url = url;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Pic [commentId=" + commentId + ", url=" + url + "]";
    }

}
