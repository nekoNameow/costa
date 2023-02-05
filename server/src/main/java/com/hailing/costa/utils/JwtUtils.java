package com.hailing.costa.utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtUtils {
  private static final String JWT_ID = UUID.randomUUID().toString();
  private static final String JWT_ISSUER = "Hailing.Zhang";
  private static final String JWT_SECRET = "Hailing.Zhang";
  private static final int EXPIRE_TIME = 60 * 60 * 24 * 30 * 1000;

  private static SecretKey generalKey() {
    byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    return key;
  }

  public static String encrypt(String subject) throws Exception {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    String jwtId = JWT_ID;
    long nowTime = System.currentTimeMillis();
    Date issuedAt = new Date(nowTime);
    SecretKey key = generalKey();
    JwtBuilder builder = Jwts.builder()
        .setId(jwtId)
        .setIssuedAt(issuedAt)
        .setIssuer(JWT_ISSUER)
        .setSubject(subject)
        .signWith(signatureAlgorithm, key);
    long expTime = EXPIRE_TIME;
    if (expTime >= 0) {
      long exp = nowTime + expTime;
      builder.setExpiration(new Date(exp));
    }
    return builder.compact();
  }

  public static String decrypt(String jwt) {
    try {
      SecretKey key = generalKey();
      Claims claims = Jwts.parser()
          .setSigningKey(key)
          .parseClaimsJws(jwt).getBody();
      return claims.getSubject();
    } catch (Exception e) {
      return "";
    }
  }
}
