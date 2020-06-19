package com.example.sy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sy4_addCard extends AppCompatActivity {


    private Button sy4_btn2;
    private EditText tv1;
    private EditText tv2;
    private EditText tv3;
    private EditText tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy4_add_card);


        sy4_btn2 = findViewById(R.id.sy4_btn2);

        sy4_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1 = findViewById(R.id.sy4_ed1);
                tv2 = findViewById(R.id.sy4_ed2);
                tv3 = findViewById(R.id.sy4_ed3);
                tv4 = findViewById(R.id.sy4_ed4);
                final Card card = new Card(tv1.getText().toString(),tv2.getText().toString(),tv3.getText().toString(),tv4.getText().toString());

                if(sy4.insert(card) != 0){
                    Toast.makeText(sy4_addCard.this,"提交成功，即将返回主页面",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(sy4_addCard.this,sy4.class));
                }else{
                    Toast.makeText(sy4_addCard.this,"提交失败，请检查",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
