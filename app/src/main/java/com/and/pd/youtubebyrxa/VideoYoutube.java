package com.and.pd.youtubebyrxa;

public class VideoYoutube {
    private String videoId;
    private String title;
    private String description;
    private String thumbnail;

    public VideoYoutube() {
    }
    public VideoYoutube(String title, String thumbnail, String videoId, String description) {

        this.title = title;
        this.thumbnail = thumbnail;
        this.videoId = videoId;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

}
