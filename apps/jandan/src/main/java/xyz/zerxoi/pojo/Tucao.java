package xyz.zerxoi.pojo;

public class Tucao {
    private Integer id;
    private String author;
    private String content;
    private Long date;
    private Integer oo;
    private Integer xx;

    public Tucao() {
    }

    public Tucao(Integer id, String author, String content, Long date, Integer oo, Integer xx) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
        this.oo = oo;
        this.xx = xx;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getOo() {
        return oo;
    }

    public void setOo(Integer oo) {
        this.oo = oo;
    }

    public Integer getXx() {
        return xx;
    }

    public void setXx(Integer xx) {
        this.xx = xx;
    }

    @Override
    public String toString() {
        return "Tucao [author=" + author + ", content=" + content + ", date=" + date + ", id=" + id + ", oo=" + oo
                + ", xx=" + xx + "]";
    }
}
