package com.example.sy;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

    public class sy4 extends AppCompatActivity {

        public static MyHelper helper;

        private ListView lv;
        private List<Card> cards;
        private Button sy4_btn1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sy4);

            helper = new MyHelper(this);
            cards = this.selectAll();
            System.out.print(cards.size()+"\n"+cards);

            lv = findViewById(R.id.sy4_lv);
            lv.setAdapter(new MyBaseAdapter());

            sy4_btn1 = findViewById(R.id.sy4_btn1);
            sy4_btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(sy4.this,sy4_addCard.class));
                }
            });
        }

        class MyBaseAdapter extends BaseAdapter{

            @Override
            public int getCount() {
                return cards.size();
            }

            @Override
            public Object getItem(int position) {
                return cards.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = View.inflate(sy4.this,R.layout.list_item,null);
                TextView tv1 = view.findViewById(R.id.sy4_tv1);
                TextView tv2 = view.findViewById(R.id.sy4_tv2);
                TextView tv3 = view.findViewById(R.id.sy4_tv3);
                TextView tv4 = view.findViewById(R.id.sy4_tv4);
                if(cards!=null) {
                    if(tv1 != null) tv1.setText(cards.get(position).getName());
                    if(tv2 != null) tv2.setText(cards.get(position).getTel());
                    if(tv3 != null) tv3.setText(cards.get(position).getDepartment());
                    if(tv4 != null) tv4.setText(cards.get(position).getTitle());
                }
                return view;
            }
        }

        class MyHelper extends SQLiteOpenHelper{

            public MyHelper(Context context){
                super(context,"sy4.db",null,2);
            }
            //数据库第一次被创建时调用该方法
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("Create Table Card(" +
                        "_id integer primary key autoincrement," +
                        "name varchar(10)," +
                        "tel varchar(15)," +
                        "department varchar(10)," +
                        "title varchar(10))");
            }
            //当数据库的版本号增加时调用
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        }
        public static int insert(Card card){
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",card.getName());
            values.put("tel",card.getTel());
            values.put("department",card.getDepartment());
            values.put("title",card.getTitle());
            long id = db.insert("Card",null,values);
            db.close();

            return (int)id;
        }

        public  List<Card> selectAll(){
            List<Card> cards = new ArrayList<>();

            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.query("Card",null,null,null,null,null,null);

            if(cursor.getCount() == 0){
                Toast.makeText(this,"没有数据",Toast.LENGTH_SHORT).show();
            }else{
                while(cursor.moveToNext()){
                    Card card = new Card(cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4));
                    cards.add(card);
                }
            }
            cursor.close();
            db.close();

            return cards;
        }

    }
