package com.codingstuff.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codingstuff.movielist.utils.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;
    //private   TextView textViewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // textViewData=findViewById(R.id.textView);

        recyclerView = findViewById(R.id.recyclerview);

    //    recyclerView.setHasFixedSize(true);
    // recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        movieList = new ArrayList<>();
       // textViewData.setText("BJr");
        fetchMovies();


    }

    private void fetchMovies() {

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=c8697268acc5406f1d3c61343bbfd606&page=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                            try{
                                //for (int i = 0; i < response.length(); i++) {}
                                JSONArray jsonArrayResponse=response.getJSONArray("results");
                                for (int i = 0; i < jsonArrayResponse.length(); i++) {

                                    JSONObject movie=jsonArrayResponse.getJSONObject(i);
                                    int id= movie.getInt("id");
                                    double rating= movie.getDouble("popularity");
                                    String title= movie.getString("original_title");
                                    String overview= movie.getString("overview");
                                    String poster= movie.getString("poster_path");

                                    Movie newMovie = new Movie(title , poster , overview , rating);
                                     movieList.add(newMovie);
                                    //textViewData.setText("Response: " + response.toString().substring(0,500));
                                    //textViewData.append("id : " +String.valueOf(id) +  " , "+String.valueOf(adult) +  " , " +String.valueOf(title) +  " , "+"\n\n");
                                }
                                //JSONObject jsonObject = response.getJSONObject(i);
                              //  String title = jsonObject.getString("title");
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }

                         MovieAdapter adapter = new MovieAdapter(MainActivity.this , movieList);

                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

// Request a string response from the provided URL.
        /*
        *
        * RequestQueue queue = Volley.newRequestQueue(this);
        * Envoyer une demande simple
        *
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textViewData.setText("Response is: " + response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewData.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);
        *
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0 ; i < response.length() ; i ++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        //textViewData.setText(title);
                        String overview = jsonObject.getString("overview");
                        String poster = jsonObject.getString("poster");
                        Double rating = jsonObject.getDouble("rating");

                        System.out.println(poster);

                        Movie movie = new Movie(title , poster , overview , rating);
                      //  movieList.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                   // MovieAdapter adapter = new MovieAdapter(MainActivity.this , movieList);

                   // recyclerView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);*/
    }
}