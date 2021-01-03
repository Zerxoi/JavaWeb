package xyz.zerxoi.pojo;

public class Comment {
    private Integer id;
    private String author;
    private Long date;
    private String content;
    private Integer oo;
    private Integer xx;
    private Integer tucao;
    private Integer read;
    private Double score;

    public static double calcuScore(Long date, Integer oo, Integer xx, Integer tucao, Integer read) {
        double score = 12.0 / (((System.currentTimeMillis() - date) >> 21) + 4);
        score += (double) oo / (xx + 1) + tucao * 0.3;
        for (int tmp = oo + xx; tmp > 0; tmp = tmp >> 1) {
            score++;
        }
        score /= 1 << (read);
        return score;
    }

    public Comment() {
    }

    public Comment(Integer id, String author, Long date, String content, Integer oo, Integer xx, Integer tucao,
            Integer read, Double score) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.content = content;
        this.oo = oo;
        this.xx = xx;
        this.tucao = tucao;
        this.read = read;
        this.score = score;
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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getTucao() {
        return tucao;
    }

    public void setTucao(Integer tucao) {
        this.tucao = tucao;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Comment [author=" + author + ", content=" + content + ", date=" + date + ", id=" + id + ", oo=" + oo
                + ", read=" + read + ", score=" + score + ", tucao=" + tucao + ", xx=" + xx + "]";
    }

}
