package xyz.zerxoi.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import xyz.zerxoi.pojo.Tucao;

public class TucaoService {
    private static String tucaoApi;
    private JSONArray tucaoArray;
    private JSONArray hotTucaoArray;
    private URL url;

    static {
        tucaoApi = "http://jandan.net/api/tucao/all/";
    }

    public TucaoService(int id) {
        try {
            url = new URL(tucaoApi + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private Object JsonGet(String key) {
        Object obj = null;
        HttpURLConnection conn = null;
        while (obj == null || obj.equals(null)) {
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("User-Agent", "Chrome/85.0.4183.121");
                obj = new JSONObject(new JSONTokener(conn.getInputStream())).get(key);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
            }
        }
        return obj;
    }

    private void getTucaoArray() {
        Object obj = JsonGet("tucao");
        if (obj instanceof JSONArray) {
            tucaoArray = (JSONArray) obj;
        }
    }

    private void getHotTucaoArray() {
        Object obj = JsonGet("hot_tucao");
        if (obj instanceof JSONArray) {
            hotTucaoArray = (JSONArray) obj;
        }
    }

    public int getTucaoNum() {
        if (tucaoArray == null) {
            getTucaoArray();
        }
        return tucaoArray.length();
    }

    private List<Tucao> parseArray(JSONArray array) {
        List<Tucao> tucaos = new ArrayList<>();
        for (int i = 0, size = array.length(); i < size; i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            int id = jsonObject.getInt("comment_ID");
            String author = jsonObject.getString("comment_author");
            String content = jsonObject.getString("comment_content");
            long date = jsonObject.getLong("comment_date_int");
            int oo = jsonObject.getInt("vote_positive");
            int xx = jsonObject.getInt("vote_negative");
            tucaos.add(new Tucao(id, author, content, date, oo, xx));
        }
        return tucaos;
    }

    public List<Tucao> parseTucao() {
        if (tucaoArray == null) {
            getTucaoArray();
        }
        return parseArray(tucaoArray);
    }

    public List<Tucao> parseHotTucao() {
        if (hotTucaoArray == null) {
            getHotTucaoArray();
        }
        return parseArray(hotTucaoArray);
    }
}
