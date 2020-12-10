package com.manoranjan.newshunt1.Model;

import java.util.List;

public class NewsData {
    private List<News_itemsModel> news_items;

    private String total_page;

    private String page;

    private String total_record;

    public List<News_itemsModel> getNews_items() {
        return news_items;
    }

    public void setNews_items(List<News_itemsModel> news_items) {
        this.news_items = news_items;
    }

    public String getTotal_page() {
        return total_page;
    }

    public void setTotal_page(String total_page) {
        this.total_page = total_page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_record() {
        return total_record;
    }

    public void setTotal_record(String total_record) {
        this.total_record = total_record;
    }

    @Override
    public String toString() {
        return "ClassPojo [news_items = " + news_items + ", total_page = " + total_page + ", page = " + page + ", total_record = " + total_record + "]";
    }
}


