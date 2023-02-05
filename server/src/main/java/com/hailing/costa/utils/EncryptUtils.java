package com.hailing.costa.utils;

import java.security.MessageDigest;

public class EncryptUtils {
  private final static char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

  public static String md5(String str) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(str.getBytes());
      byte[] bytes = md5.digest();
      int len = bytes.length;
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < len; i++) {
        byte byte0 = bytes[i];
        result.append(HEX[byte0 >>> 4 & 0xf]);
        result.append(HEX[byte0 & 0xf]);
      }
      return result.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

}
