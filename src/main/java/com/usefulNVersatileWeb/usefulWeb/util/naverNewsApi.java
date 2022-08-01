package com.usefulNVersatileWeb.usefulWeb.util;

import lombok.SneakyThrows;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class naverNewsApi {

    private final static String BASEURL = "https://openapi.naver.com/v1/search/news.json?query=";
    private final static String ID = "DzXt1NqAAm0gzO4sSP1r";
    private final static String SECRET = "u_sjmtAKb3";

    @SneakyThrows
    public String search(String searchData) {
        HttpURLConnection con = null;
        String result = "";
        String _url = URLEncoder.encode(searchData, "UTF-8");

        try {
            URL url = new URL(BASEURL + _url);
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", ID);
            con.setRequestProperty("X-Naver-Client-Secret", SECRET);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) result = readBody(con.getInputStream());
            else result = readBody(con.getErrorStream());

        } catch (Exception e) {
            System.out.println("연결 오류 : " + e);
        } finally {
            con.disconnect();
        }
        return result;
    }

    /**
     * 결과를 읽는다
     *
     * @param body
     * @return
     */
    public String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
