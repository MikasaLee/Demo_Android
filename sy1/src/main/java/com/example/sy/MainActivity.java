package com.example.sy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_sy1;
    Button btn_sy2;
    Button btn_sy3;
    Button btn_sy4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sy1 = findViewById(R.id.btn_sy1);
        btn_sy2 = findViewById(R.id.btn_sy2);
        btn_sy3 = findViewById(R.id.btn_sy3);
        btn_sy4 = findViewById(R.id.btn_sy4);
        addListener();
    }

    public void addListener(){
        if(btn_sy1 != null) btn_sy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,sy1.class));
            }
        });
        if(btn_sy2 != null) btn_sy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,sy2.class));
            }
        });
        if(btn_sy3 != null) btn_sy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,sy3.class));
            }
        });
        if(btn_sy4 != null) btn_sy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,sy4.class));
            }
        });


    }
}
