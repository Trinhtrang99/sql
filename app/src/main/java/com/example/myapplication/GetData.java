package com.example.myapplication;

import android.content.Context;
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

import java.util.List;

public class GetData {

    public void getJsonData(final String url, final List<Video> list, final Context context) {
        //

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArrayItems = response.getJSONArray("items");
                    for (int i = 0; i < jsonArrayItems.length(); i++) {
                        JSONObject jsonItem = jsonArrayItems.getJSONObject(i);
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

                    Toast.makeText(context, list.size() + "avc", Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}
