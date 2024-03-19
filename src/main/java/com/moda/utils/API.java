package com.moda.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * API
 */
public class API {

  public static String parseBaseURL(String appUrl) {
    try {
      URL url = new URL(appUrl);
      String baseURL = url.getProtocol() + "://" + url.getHost();
      return baseURL;
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
