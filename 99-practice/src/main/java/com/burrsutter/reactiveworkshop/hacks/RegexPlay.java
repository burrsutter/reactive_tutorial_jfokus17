package com.burrsutter.reactiveworkshop.hacks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

public class RegexPlay {
   private static final String SOURCE =
   " and a bunch of other stuff js-nonNavigable u-textUserColor\" title=\"3,702 Followers\" data-nav=\"followers\"";
  
    public static void main(String args[]) {
       Pattern pattern = Pattern.compile(".*?([\\d,]+ Followers).*");
       String filename = "output.txt";
       try
       {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuffer file = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null)
            {
                file.append(line);
            }
            reader.close();
    
            Matcher m = pattern.matcher(file.toString());

            while (m.find()) {
                        System.out.println("Start index: " + m.start());
                        System.out.println("End index: " + m.end());
                        System.out.println("Group Count: " + m.groupCount());
                        String groupResults = m.group(1);
                        System.out.println(groupResults);
                        String[] justNumber = groupResults.split(" ");
                        System.out.println(justNumber[0]);
                        Number x = NumberFormat.getNumberInstance(java.util.Locale.US).parse(justNumber[0]);
                        int y = x.intValue();
                        System.out.println(new Integer(y));

            } // while
       } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
       }
    } // main
} // RegexPlay