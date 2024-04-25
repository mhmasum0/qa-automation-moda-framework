package com.moda.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class API {

  private API() {
    throw new IllegalStateException("Utility class");
  }

  public static String parseBaseURL(String appUrl) {
    try {
      URL url = new URL(appUrl);
      return url.getProtocol() + "://" + url.getHost();
    } catch (MalformedURLException e) {
      LogHelper.getLogger().info(e.getMessage());
    }
    return "";
  }

  public static String extractEnvironment(String url){
    String pattern = "env=(\\w+)";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(url);
    if (m.find()) {
      return m.group(1);
    }
    return null;
  }
}
