package com.quintus.labs.datingapp.service.upload.image;

import java.util.List;

@lombok.Data
public class Data {
    private String id;
    private String deletehash;
    private Object account_id;
    private Object account_url;
    private Object ad_type;
    private Object ad_url;
    private Object title;
    private Object description;
    private String name;
    private String type;
    private int width;
    private int height;
    private int size;
    private int views;
    private Object section;
    private Object vote;
    private int bandwidth;
    private boolean animated;
    private boolean favorite;
    private boolean in_gallery;
    private boolean in_most_viral;
    private boolean has_sound;
    private boolean is_ad;
    private Object nsfw;
    private String link;
    private List<Object> tags;
    private int datetime;
    private String mp4;
    private String hls;
}

