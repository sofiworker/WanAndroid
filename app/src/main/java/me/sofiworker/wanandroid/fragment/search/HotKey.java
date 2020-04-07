package me.sofiworker.wanandroid.fragment.search;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/29 21:41
 */
public class HotKey {

    private int id;
    private String link;
    private String name;
    private int order;
    private int visible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
