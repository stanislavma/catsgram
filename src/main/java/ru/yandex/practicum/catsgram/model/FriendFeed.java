package ru.yandex.practicum.catsgram.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FriendFeed {
    private String sort;
    private int size;
    private List<String> friends;
//
//    @JsonCreator
//    public FriendFeed(
//            @JsonProperty("sort") String sort,
//            @JsonProperty("size") int size,
//            @JsonProperty("friends") List<String> friends) {
//        this.sort = sort;
//        this.size = size;
//        this.friends = friends;
//    }

    public FriendFeed() {
        System.out.println();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "FriendFeed{" +
                "sort='" + sort + '\'' +
                ", size=" + size +
                ", friends=" + friends +
                '}';
    }
}