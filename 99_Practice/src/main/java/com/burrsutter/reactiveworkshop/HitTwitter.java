package com.burrsutter.reactiveworkshop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.NumberFormat;

public class HitTwitter {
    public static Integer getFollowers(String twitterHandle) {
        try {
            final URL theURL = 
              new URL("https://twitter.com/" + twitterHandle);
            final BufferedReader reader = 
              new BufferedReader(new InputStreamReader(theURL.openStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;

            // suck in the page
		    while ((inputLine = reader.readLine()) != null) {
			  response.append(inputLine);
		    }
		    reader.close();

            Pattern pattern = Pattern.compile(".*?([\\d,]+ Followers).*");
            Matcher m = pattern.matcher(response.toString());
            if (m.find()) {
                String groupResults = m.group(1);
                String[] justTheNumber = groupResults.split(" ");
                Number x = NumberFormat.getNumberInstance(java.util.Locale.US).parse(justTheNumber[0]);
                int y = x.intValue();
                return y;
            } else {
                throw new RuntimeException("Not Found");
            }
        } catch(Exception ex) {
          throw new RuntimeException(ex);
        }
        
    }

    public static void main(String[] args) {
        System.out.println("Finding Followers");
        // System.out.println(getFollowers("burrsutter"));
        // System.out.println(getFollowers("yanaga"));
        // System.out.println(getFollowers("realDonaldTrump"));
        System.out.println(getFollowers("Dolph_Lundgren"));
        System.out.println(getFollowers("MickeNyqvist"));
    }

}