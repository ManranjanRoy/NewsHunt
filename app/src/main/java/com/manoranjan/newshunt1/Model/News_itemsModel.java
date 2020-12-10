package com.manoranjan.newshunt1.Model;

public class News_itemsModel {
    private String image;

    private String news_type;

    private String sub_category;

    private String description;

    private String video;

    private String title;

    private String category;

    private String slug;

    private String bannerimage;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBannerimage() {
        return bannerimage;
    }

    public void setBannerimage(String bannerimage) {
        this.bannerimage = bannerimage;
    }

    @Override
    public String toString() {
        return "ClassPojo [image = " + image + ", news_type = " + news_type + ", sub_category = " + sub_category + ", description = " + description + ", video = " + video + ", title = " + title + ", category = " + category + ", slug = " + slug + ", bannerimage = " + bannerimage + "]";
    }
}
