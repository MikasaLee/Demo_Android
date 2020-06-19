package com.example.sy;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sy2 extends AppCompatActivity {

    Button sy2_btn;
    EditText sy2_ed;
    TextView sy2_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy2);



        sy2_tv = findViewById(R.id.sy2_tv1);
        sy2_ed = findViewById(R.id.sy2_et1);
        sy2_btn = findViewById(R.id.sy2_btn1);



        sy2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sy2.this,sy2_answer.class);
                Bundle bundle = new Bundle();
                String question = sy2_ed.getText().toString();
                bundle.putString("question",question);
                intent.putExtras(bundle);


//                原来不用startActivityForResult的代码
//                startActivity(intent);
                startActivityForResult(intent,548);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle= data.getExtras();
        if(bundle != null) {

            if(requestCode == 548 && resultCode == 479 ) {
                String answer = bundle.getString("answer");
                if (answer != null)
                    sy2_tv.setText(answer);
            }
        }
    }
}
