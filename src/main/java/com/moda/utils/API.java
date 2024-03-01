package com.moda.utils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * API
 */
public class API {

  public static String parseBaseURL(String URL) {
    try {
      URL url = new URL(URL);
      String baseURL = url.getProtocol() + "://" + url.getHost();
      return baseURL;
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return "";
  }
}
