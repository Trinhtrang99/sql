package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Video> list;
    List<Video> list1l;

    List<Video> all;

    String API_KEY = "AIzaSyCFi6Ctl4thP43H3PkOYJyV9ipOYDiSPAY";
    String ID_PLAYLIST = "PLxu-S05deEMY6IM0Ey4sFljOPSTf2vPeM";
    String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + ID_PLAYLIST +
            "&key=" + API_KEY + "&maxResults=50";
    GetData get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        all = new ArrayList<>();
        get.getJsonData(url, all, getBaseContext());
        Toast.makeText(getBaseContext(), all.size() + "", Toast.LENGTH_SHORT).show();
    }

    class getData extends AsyncTask<String, Void, String> {

        StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStream = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        public void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonItem = jsonArray.getJSONObject(i);
                    JSONObject jsonsnippet = jsonItem.getJSONObject("snippet");
                    String publishedAt = jsonsnippet.getString("publishedAt");
                    String playlistId = jsonsnippet.getString("playlistId");
                    String title = jsonsnippet.getString("title");
                    String description = jsonsnippet.getString("description");

                    JSONObject jsonthumbnails = jsonsnippet.getJSONObject("thumbnails");

                    JSONObject jsonmedium = jsonthumbnails.getJSONObject("medium");
                    String urlimg = jsonmedium.getString("url");

                    JSONObject jsonresourceId = jsonsnippet.getJSONObject("resourceId");
                    String kind = jsonresourceId.getString("kind");
                    String videoId = jsonresourceId.getString("videoId");
                    Video v = new Video(publishedAt, title, description, urlimg, kind, videoId, playlistId);
                    list.add(v);

                }
                Toast.makeText(MainActivity.this, list.size() + "", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    class getData1 extends AsyncTask<String, Void, String> {

        StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStream = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        public void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                List<Video> list = new ArrayList<>();
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonItem = jsonArray.getJSONObject(i);
                    JSONObject jsonsnippet = jsonItem.getJSONObject("snippet");
                    String publishedAt = jsonsnippet.getString("publishedAt");
                    String playlistId = jsonsnippet.getString("playlistId");
                    String title = jsonsnippet.getString("title");
                    String description = jsonsnippet.getString("description");

                    JSONObject jsonthumbnails = jsonsnippet.getJSONObject("thumbnails");

                    JSONObject jsonmedium = jsonthumbnails.getJSONObject("medium");
                    String urlimg = jsonmedium.getString("url");

                    JSONObject jsonresourceId = jsonsnippet.getJSONObject("resourceId");
                    String kind = jsonresourceId.getString("kind");
                    String videoId = jsonresourceId.getString("videoId");
                    Video v = new Video(publishedAt, title, description, urlimg, kind, videoId, playlistId);
                    list1l.add(v);

                }
                Toast.makeText(MainActivity.this, list.size() + "", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}