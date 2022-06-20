package com.jy.restDemo.dto;

import java.util.Objects;

public class DataItem {

    private String id;

    private String title;

    private String content;

    private int views;

    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataItem dataItem = (DataItem) o;
        return views == dataItem.views && timestamp == dataItem.timestamp && Objects.equals(id, dataItem.id) && Objects.equals(title, dataItem.title) && Objects.equals(content, dataItem.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, views, timestamp);
    }
}
