package una.mobile.com.futbolon3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeagueActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private List<String> headerInfo;
    String idLeague="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);

        //inicio el adaptador de los titulos
        headerInfo = new ArrayList<String>();
        headerInfo.add("Premier League Inglaterra");
        headerInfo.add("Primera División España");
        headerInfo.add("Serie A Italia");
        headerInfo.add("UEFA Champions League");

        HashMap<String, List<String>> allChildItems = returnGroupedItems();
        expandableListView = (ExpandableListView) findViewById(R.id.expandableList);
        final CustomAdapter expandableListViewAdapter = new CustomAdapter(getApplicationContext(), headerInfo, allChildItems);
        expandableListView.setAdapter(expandableListViewAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //collapsed automatically
                CustomAdapter customExpandAdapter = (CustomAdapter) expandableListView.getExpandableListAdapter();
                if (customExpandAdapter == null) {return;}
                for (int i = 0; i < customExpandAdapter.getGroupCount(); i++) {
                    if (i != groupPosition) {
                        expandableListView.collapseGroup(i);
                    }
                }
                //inicializar el id
                if(headerInfo.get(groupPosition).equals("Premier League Inglaterra"))
                    idLeague="445";
                else if(headerInfo.get(groupPosition).equals("Primera División España"))
                    idLeague="455";
                else if(headerInfo.get(groupPosition).equals("Serie A Italia"))
                    idLeague="456";
                else if(headerInfo.get(groupPosition).equals("UEFA Champions League"))
                    idLeague="464";
            }
    });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch(childPosition) {
                    case 0://informacion
                        // Go to child #0 specific class.
                        Intent intent0 = new Intent(getApplicationContext(), InformationActivity.class);
                        intent0.putExtra("id",idLeague);
                        startActivity(intent0);
                        break;

                        //acomodar bien estos
                    case 1://equipos
                        // Go to child #1 specific class.
                        Intent intent1 = new Intent(getApplicationContext(), InformationActivity.class);
                        intent1.putExtra("id",idLeague);
                        startActivity(intent1);
                        break;

                    case 2://partidos
                        // Go to child #1 specific class.
                        Intent intent2 = new Intent(getApplicationContext(), InformationActivity.class);
                        intent2.putExtra("id",idLeague);
                        startActivity(intent2);
                        break;

                    case 3://posiciones
                        // Go to child #1 specific class.
                        Intent intent3 = new Intent(getApplicationContext(), TableActivity.class);
                        intent3.putExtra("liga",445);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }

    //Información al expandir
    private HashMap<String, List<String>> returnGroupedItems(){
        HashMap<String, List<String>> childContent = new HashMap<String, List<String>>();

        List<String> pl = new ArrayList<String>();
        pl.add("Información");
        pl.add("Equipos");
        pl.add("Partidos");
        pl.add("Tabla de posiciones");

        List<String> pd = new ArrayList<String>();
        pd.add("Información");
        pd.add("Equipos");
        pd.add("Partidos");
        pd.add("Tabla de posiciones");

        List<String> sa = new ArrayList<String>();
        sa.add("Información");
        sa.add("Equipos");
        sa.add("Partidos");
        sa.add("Tabla de posiciones");

        List<String> cl = new ArrayList<String>();
        cl.add("Información");
        cl.add("Equipos");
        cl.add("Partidos");
        cl.add("Tabla de posiciones");

        childContent.put(headerInfo.get(0),pl);
        childContent.put(headerInfo.get(1),pd);
        childContent.put(headerInfo.get(2),sa);
        childContent.put(headerInfo.get(3),cl);

        return childContent;
    }








}


