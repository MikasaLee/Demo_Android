package com.example.sy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class sy3 extends AppCompatActivity {

    Button sy3_btn_1,sy3_btn_2,sy3_btn_3;

    String name;
    String imageViewR;
    final String dir = "color";     //所有图片都在res/color子目录中
    ImageView imageView;
    String context;
    TextView sy3_tv1;

    List<Plant> plants;
    Plant plant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy3);

        try {
            getPlants();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setButton();
    }

    public void setButton(){
        sy3_btn_1 = findViewById(R.id.sy3_btn_1);
        sy3_btn_2 = findViewById(R.id.sy3_btn_2);
        sy3_btn_3 = findViewById(R.id.sy3_btn_3);

        sy3_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPlant(sy3_btn_1.getText().toString());
            }
        });
        sy3_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPlant(sy3_btn_2.getText().toString());
            }
        });
        sy3_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPlant(sy3_btn_3.getText().toString());
            }
        });
    }

    public void getPlants() throws Exception {
        InputStream is = this.getResources().openRawResource(R.raw.plant);

        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is,"utf-8");
        int type = parser.getEventType();
        while(type != XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if("info".equals(parser.getName())){
                        plants = new ArrayList<>(10);
                    }else if("plant".equals(parser.getName())){
                        plant = new Plant();
                    }else if("name".equals(parser.getName())){
                        name = parser.nextText();
                        plant.setName(name);
                    }else if("image".equals(parser.getName())){
                        imageViewR = parser.nextText();
                        plant.setImageViewR(imageViewR);
                    }else if("context".equals(parser.getName())){
                        context = parser.nextText();
                        plant.setContext(context);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("plant".equals(parser.getName())) {
                        plants.add(plant);
                        plant = null;
                    }
                    break;
            }
            type = parser.next();
        }
    }
    @SuppressLint("ResourceType")
    public void ShowPlant(String name){
        for(Plant plant:plants){
            if(plant.getName().equals(name)){
                imageView = findViewById(R.id.sy3_img1);

                imageView.setImageResource(getResource(plant.getImageViewR(),dir));
                sy3_tv1 = findViewById(R.id.sy3_tv1);
                sy3_tv1.setText(plant.getContext());
            }
        }
    }
    public int getResource(String imageName,String dir){
        Context ctx=getBaseContext();
        int resId = getResources().getIdentifier(imageName, dir, ctx.getPackageName());
        return resId;
    }

}
