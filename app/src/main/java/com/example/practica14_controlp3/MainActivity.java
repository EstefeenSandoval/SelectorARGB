package com.example.practica14_controlp3;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.actividad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SelectColor sc = findViewById(R.id.selectorColor);

        sc.setOnColorListener(new OnSelectedColorListener() {
            @Override
            public void onSelectedColor(int alpha, int red, int green, int blue) {
                Toast.makeText(getApplicationContext(), "ARGB: " + alpha + ", " + red + ", " + green + ", " + blue ,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}