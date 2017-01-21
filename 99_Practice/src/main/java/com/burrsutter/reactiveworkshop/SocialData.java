package com.burrsutter.reactiveworkshop;

public class SocialData {
  public final String id;
  public final int followers;
  
  public SocialData(String id, int followers) {
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