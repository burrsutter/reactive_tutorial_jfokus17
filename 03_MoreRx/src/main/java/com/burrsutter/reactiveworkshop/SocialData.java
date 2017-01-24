package com.burrsutter.reactiveworkshop;

/**
 * Created by burr on 1/23/17.
 */
public class SocialData {
    public final String id;
    public final int followers;

    public SocialData(String id, int followers) {
        this.id = id;
        this.followers = followers;
    }

    public static SocialData load(String id) {
        // System.out.println("getting some data for " + id);
        // return new SocialData(id, 1);
        return new SocialData(id,TwitterScreenscraper.getFollowers(id));
    }

    public String toString() {
        return id + " has " + followers + " followers";
    }
}
