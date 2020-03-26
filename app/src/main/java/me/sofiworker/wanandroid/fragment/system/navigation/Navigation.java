package me.sofiworker.wanandroid.fragment.system.navigation;

import java.util.List;

import me.sofiworker.wanandroid.fragment.home.Article;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/21 19:21
 */
public class Navigation {

    private int cid;
    private String name;
    private List<Article> articles;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
