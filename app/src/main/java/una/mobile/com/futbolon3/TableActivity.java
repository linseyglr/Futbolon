package una.mobile.com.futbolon3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

import una.mobile.com.futbolon3.model.LeagueTable;
import una.mobile.com.futbolon3.model.Standing;
import una.mobile.com.futbolon3.service.ServiceHandler;

public class TableActivity extends AppCompatActivity implements View.OnClickListener {

    ServiceHandler handler;
    Map<String, Object> params;
    int liga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        addHeaders();
        handler = new ServiceHandler();
        params = new ArrayMap<>();
        params.put("apikey", "064ef724a5824949a8b19dd81eb805d2");
        liga = getIntent().getIntExtra("liga", 0);
        addData();
    }

    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(40, 40, 40, 40);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setOnClickListener(this);
        return tv;
    }

    @NonNull
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }

    /**
     * This function add the headers to the table
     **/
    public void addHeaders() {
        TableLayout tl = findViewById(R.id.table);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "#", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "Team", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "J", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "G", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "E", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "P", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "GF", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "GC", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "GD", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tr.addView(getTextView(0, "Pts", Color.WHITE, Typeface.BOLD, Color.BLUE));
        tl.addView(tr, getTblLayoutParams());
    }

    /**
     * This function add the data to the table
     **/
    public void addData(){
        handler.objectRequest("http://api.football-data.org/v1/competitions/"+liga+"/leagueTable", Request.Method.GET, params,
                LeagueTable.class, new Response.Listener<LeagueTable>() {
                    @Override
                    public void onResponse(LeagueTable response) {
                        int i = 0;
                        TableLayout tl = findViewById(R.id.table);
                        for(Standing team : response.getStanding()){
                            TableRow tr = new TableRow(getThis());
                            tr.setLayoutParams(getLayoutParams());
                            tr.addView(getTextView(i + 1, team.getPosition(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tr.addView(getTextView(i + response.getStanding().size(), team.getTeamName(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tr.addView(getTextView(i + response.getStanding().size(), team.getPlayedGames(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tr.addView(getTextView(i + response.getStanding().size(), team.getWins(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tr.addView(getTextView(i + response.getStanding().size(), team.getDraws(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tr.addView(getTextView(i + response.getStanding().size(), team.getLosses(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tr.addView(getTextView(i + response.getStanding().size(), team.getGoals(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tr.addView(getTextView(i + response.getStanding().size(), team.getGoalsAgainst(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tr.addView(getTextView(i + response.getStanding().size(), team.getGoalDifference(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tr.addView(getTextView(i + response.getStanding().size(), team.getPoints(), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(getThis(), R.color.colorAccent)));
                            tl.addView(tr, getTblLayoutParams());
                            i++;
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }


    public Context getThis(){
        return this;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        TextView tv = findViewById(id);
        if (null != tv) {
            Log.i("onClick", "Clicked on row :: " + id);
            Toast.makeText(this, "Clicked on row :: " + id + ", Text :: " + tv.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
