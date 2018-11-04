package com.and.pd.youtubebyrxa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends YouTubeBaseActivity {

    String API_KEY = "AIzaSyDJSXOGPw8RRTdCOOmi6TQZjuwYJVrrqXE";
    String ID_PLAYLIST = "PL8nBsyFC_gGNaiqfhe71b9R6P9yhhe8HQ";
    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" +
            "PL8nBsyFC_gGNaiqfhe71b9R6P9yhhe8HQ&key=AIzaSyDJSXOGPw8RRTdCOOmi6TQZjuwYJVrrqXE&maxResults=50";
    YouTubePlayerView youTubePlayerView;
    int REQUEST_VIDEO = 123;

    ListView lvVideo;
    ArrayList<VideoYoutube> arrayVideo;
    VideoYoutubeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvVideo = findViewById(R.id.lv_listVideoYoutube);
        arrayVideo = new ArrayList<>();
        adapter = new VideoYoutubeAdapter(this, R.layout.row_video_youtube, arrayVideo);
        lvVideo.setAdapter(adapter);

        GetJsonYoutube(urlGetJson);

        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, PlayVideoActivity.class);
                intent.putExtra("idVideoYoutube", arrayVideo.get(i).getVideoId());
                intent.putExtra("API_KEY", API_KEY);
                startActivity(intent);
            }
        });
//        youTubePlayerView = findViewById(R.id.player_youtube);
//        youTubePlayerView.initialize(API_KEY, this);
    }

    private void GetJsonYoutube(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            String tittle = "";
                            String url = "";
                            String videoId = "";

                            for (int i = 0; i<jsonItems.length(); i++){
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                                JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");

                                tittle = jsonSnippet.getString("title");
                                url = jsonMedium.getString("url");
                                videoId = jsonResourceId.getString("videoId");
                                arrayVideo.add(new VideoYoutube(tittle, url, videoId));
                            }

                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "errorrrr", Toast.LENGTH_SHORT);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider,
//                                        YouTubePlayer youTubePlayer, boolean b) {
//        youTubePlayer.cueVideo("RZaY3cWJjU4");
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider,
//                                        YouTubeInitializationResult youTubeInitializationResult) {
//        if (youTubeInitializationResult.isUserRecoverableError()) {
//            youTubeInitializationResult.getErrorDialog(MainActivity.this, REQUEST_VIDEO);
//        } else {
//            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_VIDEO) {
//            youTubePlayerView.initialize(API_KEY, MainActivity.this);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
