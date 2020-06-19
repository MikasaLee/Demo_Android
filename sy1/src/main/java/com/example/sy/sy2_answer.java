package com.example.sy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sy2_answer extends AppCompatActivity {

    Button sy2_btn;
    EditText sy2_et;
    TextView sy2_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy2_answer);

        Bundle bundle = this.getIntent().getExtras();

        sy2_et = findViewById(R.id.sy2_et2);
        sy2_tv = findViewById(R.id.sy2_tv2);
        sy2_btn = findViewById(R.id.sy2_btn2);

        if(bundle != null) {
            String question = bundle.getString("question");
            if (question !=null)
                sy2_tv.setText(question);
        }
        sy2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sy2_answer.this,sy2.class);
                Bundle bundle = new Bundle();
                bundle.putString("answer",sy2_et.getText().toString());

                intent.putExtras(bundle);



//                原来不用startActivityForResult的代码
//                startActivity(intent);
                setResult(479,intent);
                finish();           //close this activity
            }
        });
    }
}
