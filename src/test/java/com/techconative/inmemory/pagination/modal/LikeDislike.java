package com.techconative.inmemory.pagination.modal;

public class LikeDislike {

    public int likes;
    public int dislikes;
    public int userAction;

    public int getLikes() {

        return likes;
    }

    public void setLikes(int likes) {

        this.likes = likes;
    }

    public int getDislikes() {

        return dislikes;
    }

    public void setDislikes(int dislikes) {

        this.dislikes = dislikes;
    }

    public int getUserAction() {

        return userAction;
    }

    public void setUserAction(int userAction) {

        this.userAction = userAction;
    }
}
