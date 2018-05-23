package una.mobile.com.futbolon3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

import una.mobile.com.futbolon3.model.Team;
import una.mobile.com.futbolon3.service.ServiceHandler;

public class TeamActivity extends AppCompatActivity {

    ServiceHandler handler;
    TextView txt;
    Map<String, Object> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        txt= (TextView) findViewById(R.id.text);
        handler = new ServiceHandler();
        params = new ArrayMap<>();
        params.put("apikey", "064ef724a5824949a8b19dd81eb805d2");

        handler.objectRequest("http://api.football-data.org/v1/teams/66", Request.Method.GET, params,
                Team.class, new Response.Listener<Team>() {
                    @Override
                    public void onResponse(Team response) {
                        //Si todo sale bien ejecuto aqui
                        Log.d("Manchester United", response.getCode());
                        txt.setText(response.getCode());


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Si todo sale mal ejecuto esto
                        Log.e("Premier LeagueTable", error.toString());
                        txt.setText("Error");
                    }
                });
    }
}
