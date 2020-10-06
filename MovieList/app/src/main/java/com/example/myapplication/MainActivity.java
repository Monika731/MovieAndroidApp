package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name, page;
    Button btnReset, btnSearch;
    TextView tvName, tvPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (TextView) findViewById(R.id.name);
        tvPage = (TextView) findViewById(R.id.page);

        btnSearch = (Button) findViewById(R.id.search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.editname);
                String text = name.getText().toString();
                page = (EditText) findViewById(R.id.editpage);
                String no = page.getText().toString();
                String URL = "http://www.omdbapi.com/?s="+text+"&page="+no+"&apikey=d0fc3644";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = (String) response.get("Response");
                            if(result.equals("False")) {
                                Toast.makeText(getApplicationContext(), "Please enter valid movie name and page number", Toast.LENGTH_LONG).show();
                            } else {
                                JSONArray booking = response.getJSONArray("Search");
                                ArrayList<MovieData> arrayList = new ArrayList<>();

                                if (booking.length() > 0) {
                                    for (int i = 0; i < booking.length(); i++) {
                                        JSONObject movieObj = booking.getJSONObject(i);
                                        String title = movieObj.isNull("Title") ? "" : movieObj.optString("Title");
                                        String year = movieObj.isNull("Year") ? "" : movieObj.optString("Year");
                                        String poster = movieObj.isNull("Poster") ? "" : movieObj.optString("Poster");
                                        arrayList.add(new MovieData(title, year, poster));
                                    }
                                    Intent intent = new Intent(getBaseContext(), DisplayActivity.class);
                                    intent.putExtra("moviedata", arrayList);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Rest response" , response.toString());
                    }
                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Rest response" , error.toString());
                            }
                        }
                );
                requestQueue.add(objectRequest);
            }
        });
        btnReset = (Button) findViewById(R.id.reset);

    }

    public void clear(View view) {
        name.setText(" ");
        page.setText(" ");
    }
}