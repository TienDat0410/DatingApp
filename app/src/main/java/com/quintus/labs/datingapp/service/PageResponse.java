package com.quintus.labs.datingapp.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PageResponse<T> {
    private List<T> list;
    private long totalElement;

    public PageResponse() {
        list = new ArrayList<>();
        totalElement = 0;
    }
}
