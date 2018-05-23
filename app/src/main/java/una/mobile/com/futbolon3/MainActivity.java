package una.mobile.com.futbolon3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnclickDelButton(R.id.Premier);
        OnclickDelButton(R.id.SerieA);
    }

    public void OnclickDelButton(int ref) {

        View view = findViewById(ref);
        Button miButton = (Button) view;
        miButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Premier:
                        Intent intento = new Intent(getApplicationContext(), TableActivity.class);
                        intento.putExtra("liga", 445);
                        startActivity(intento);
                        break;
                    case R.id.SerieA:
                        intento = new Intent(getApplicationContext(), TableActivity.class);
                        intento.putExtra("liga", 456);
                        startActivity(intento);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}


