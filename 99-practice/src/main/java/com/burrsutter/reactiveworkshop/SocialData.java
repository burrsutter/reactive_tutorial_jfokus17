package com.burrsutter.reactiveworkshop;

public class SocialData {

    private final String id;

    private final int followers;

    private SocialData(String id, int followers) {
        this.id = id;
        this.followers = followers;
    }

    public static SocialData load(String id) {
        return new SocialData(id, TwitterScreenscraper.getFollowers(id));
    }

    @Override
    public String toString() {
        return String.format("%s has %d Followers", id, followers);
    }

}