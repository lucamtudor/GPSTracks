package ro.tudorluca.gpstracks.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends Activity {

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);

        findViewById(R.id.post_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postLocation();
            }
        });
    }

    private void postLocation() {

        String url = "http://192.168.2.3:8080/position";

        Position position = new Position();
        position.setDate(new Date());
        position.setLatitude("12.432");
        position.setLongitude("43.23");
        position.setUserId(1);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String positionString = mapper.writeValueAsString(position);

            JSONObject jsonObject = new JSONObject(positionString);
            JsonObjectRequest positionRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Position Response", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    Log.e("Position Error", error.getMessage());
                    error.getCause().printStackTrace();
                }
            });

            queue.add(positionRequest);
        } catch (JsonProcessingException | JSONException e) {
            e.printStackTrace();
        }

    }
}
