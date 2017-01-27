package com.burrsutter.reactiveworkshop;

import org.apache.http.HttpRequest;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.NumberFormat;

public class TwitterScreenscraper {

    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.custom()
            .setConnectionManager(new PoolingHttpClientConnectionManager())
            .build();

    private static final Pattern FOLLOWER_PATTERN = Pattern.compile("(?<count>[\\d,]+) Followers");

    public static int getFollowers(String twitterHandle) {
        try {
            HttpGet request = new HttpGet(String.format("https://twitter.com/%s", twitterHandle));
            request.addHeader("Accept-Language", "en");
            String response = EntityUtils.toString(HTTP_CLIENT.execute(request).getEntity());

            Matcher m = FOLLOWER_PATTERN.matcher(response);
            if (m.find()) {
                return Integer.parseInt(m.group("count").replaceAll(",", ""));
            } else {
                throw new RuntimeException("Not Found");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        System.out.println("Finding Followers");
        System.out.println(getFollowers("burrsutter"));
        System.out.println(getFollowers("yanaga"));
        System.out.println(getFollowers("realDonaldTrump"));
        System.out.println(getFollowers("Dolph_Lundgren"));
        System.out.println(getFollowers("MickeNyqvist"));
    }

}