package com.hailing.costa.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequest {
    private boolean debug;

    public HttpRequest(boolean debug) {
        this.debug = debug;
    }

    public HttpRequest() {
        this.debug = false;
    }

    public String doGet(String httpUrl) {
        HttpURLConnection connection;
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer result = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                if (null != is) {
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String temp;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                    }
                }
            }
            connection.disconnect();
        } catch (IOException e) {
            if (this.debug) {
                e.printStackTrace();
            }
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    if (this.debug) {
                        e.printStackTrace();
                    }
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    if (this.debug) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result.toString();
    }

    public String doPost(String httpUrl, String param) {
        HttpURLConnection connection;
        OutputStream os = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            if (null != param && !param.equals("")) {
                os = connection.getOutputStream();
                os.write(param.getBytes("UTF-8"));
            }
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                if (null != is) {
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String temp;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                        result.append("\r\n");
                    }
                }
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            if (this.debug) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            if (this.debug) {
                e.printStackTrace();
            }
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    if (this.debug) {
                        e.printStackTrace();
                    }
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    if (this.debug) {
                        e.printStackTrace();
                    }
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    if (this.debug) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result.toString();
    }

}
