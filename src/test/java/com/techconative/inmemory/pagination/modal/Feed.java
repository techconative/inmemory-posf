package com.techconative.inmemory.pagination.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Feed {

    public int id;
    public String title;
    public String description;
    public String location;
    public int lng;
    public int lat;
    public int userId;
    public String name;

    @JsonProperty("isdeleted")
    public boolean deleted;

    public String profilePicture;
    public String videoUrl;
    public String images;
    public int mediatype;
    public String imagePaths;
    public String feedsComment;
    public int commentCount;
    public List<MultiMedium> multiMedia;
    public LikeDislike likeDislike;
    public Date createdAt;
    public int code;
    public String msg;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {

        this.location = location;
    }

    public int getLng() {

        return lng;
    }

    public void setLng(int lng) {

        this.lng = lng;
    }

    public int getLat() {

        return lat;
    }

    public void setLat(int lat) {

        this.lat = lat;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public boolean isDeleted() {

        return deleted;
    }

    public void setDeleted(boolean deleted) {

        this.deleted = deleted;
    }

    public String getProfilePicture() {

        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {

        this.profilePicture = profilePicture;
    }

    public String getVideoUrl() {

        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {

        this.videoUrl = videoUrl;
    }

    public String getImages() {

        return images;
    }

    public void setImages(String images) {

        this.images = images;
    }

    public int getMediatype() {

        return mediatype;
    }

    public void setMediatype(int mediatype) {

        this.mediatype = mediatype;
    }

    public String getImagePaths() {

        return imagePaths;
    }

    public void setImagePaths(String imagePaths) {

        this.imagePaths = imagePaths;
    }

    public String getFeedsComment() {

        return feedsComment;
    }

    public void setFeedsComment(String feedsComment) {

        this.feedsComment = feedsComment;
    }

    public int getCommentCount() {

        return commentCount;
    }

    public void setCommentCount(int commentCount) {

        this.commentCount = commentCount;
    }

    public List<MultiMedium> getMultiMedia() {

        return multiMedia;
    }

    public void setMultiMedia(ArrayList<MultiMedium> multiMedia) {

        this.multiMedia = multiMedia;
    }

    public LikeDislike getLikeDislike() {

        return likeDislike;
    }

    public void setLikeDislike(LikeDislike likeDislike) {

        this.likeDislike = likeDislike;
    }

    public Date getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {

        this.createdAt = createdAt;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }
}
