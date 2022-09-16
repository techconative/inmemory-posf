package com.techconative.inmemory.pagination.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class MultiMedium {

    public int id;
    public String name;
    public String description;
    public String url;

    @JsonProperty("mediatype")
    public int mediaType;

    public int likeCount;
    public String place;
    public Date createAt;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public int getMediaType() {

        return mediaType;
    }

    public void setMediaType(int mediaType) {

        this.mediaType = mediaType;
    }

    public int getLikeCount() {

        return likeCount;
    }

    public void setLikeCount(int likeCount) {

        this.likeCount = likeCount;
    }

    public String getPlace() {

        return place;
    }

    public void setPlace(String place) {

        this.place = place;
    }

    public Date getCreateAt() {

        return createAt;
    }

    public void setCreateAt(Date createAt) {

        this.createAt = createAt;
    }
}
