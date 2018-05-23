package una.mobile.com.futbolon3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

import una.mobile.com.futbolon3.model.League;
import una.mobile.com.futbolon3.service.ServiceHandler;

public class InformationActivity extends AppCompatActivity {
    ServiceHandler handler;
    TextView logo,name,year, numgames, totalgames,numteams;
    Map<String, Object> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        String idLeague= getIntent().getExtras().getString("id");
        //Crear la tabla
        logo = (TextView)findViewById(R.id.logo);
        name = (TextView)findViewById(R.id.name);
        year = (TextView)findViewById(R.id.year);
        numgames= (TextView)findViewById(R.id.numgames);
        totalgames = (TextView)findViewById(R.id.totalgames);
        numteams = (TextView)findViewById(R.id.numteams);

        handler = new ServiceHandler();
        params = new ArrayMap<>();
        params.put("apikey", "064ef724a5824949a8b19dd81eb805d2");

        //League 445
        handler.objectRequest("http://api.football-data.org/v1/competitions/"+idLeague, Request.Method.GET, params,
                League.class, new Response.Listener<League>() {
                    @Override
                    public void onResponse(League response) {
                        //Si todo sale bien ejecuto aqui
                        Log.d("true", response.getCaption().toString());
                        name.setText(response.getCaption());
                        year.setText(response.getYear());
                        numgames.setText(response.getNumberOfMatchdays());
                        totalgames.setText(response.getNumberOfGames());
                        numteams.setText(response.getNumberOfTeams());


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Si todo sale mal ejecuto esto
                        Log.e("false", error.toString());
                        name.setText("Error");
                    }
                });

    }


}


