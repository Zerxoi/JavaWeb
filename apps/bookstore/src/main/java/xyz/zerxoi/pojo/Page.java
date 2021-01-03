package xyz.zerxoi.pojo;

import java.util.List;

public class Page<T> {
    public static final Integer DEFAULT_PAGE_SIZE = 4;
    private Integer page;
    private Integer pageTotal;
    private Integer total;
    private Integer size;
    private List<T> items;
    private String url;

    public Page() {
    }

    public Page(Integer page, Integer pageTotal, Integer total, Integer size, List<T> items, String url) {
        this.page = page;
        this.pageTotal = pageTotal;
        this.total = total;
        this.size = size;
        this.items = items;
        this.url = url;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page [items=" + items + ", page=" + page + ", pageTotal=" + pageTotal + ", size=" + size + ", total="
                + total + ", url=" + url + "]";
    }

}
