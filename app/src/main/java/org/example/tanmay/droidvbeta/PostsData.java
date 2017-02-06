package org.example.tanmay.droidvbeta;

import java.util.Arrays;

/**
 * Created by Tanmay on 8/3/2016.
 */
public class PostsData {
    private String link;
    private String title;
    private String pubDate;
    private String[] categories = new String[5];

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Link: " + link + '\n' +
                "Title: " + title + '\n' +
                "PubDate: " + pubDate + '\n' +
                "Categories: " + Arrays.toString(categories);
    }
}
