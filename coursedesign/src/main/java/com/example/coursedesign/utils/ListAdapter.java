package com.example.coursedesign.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.coursedesign.R;
import com.example.coursedesign.TeaAnsListActivity;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<String> strings;

    public ListAdapter(Context context, List<String> contents) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.strings = contents;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView){
            convertView = inflater.inflate(R.layout.list_edit,null);
            holder =new ViewHolder(convertView,position);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.editText.setText(getItem(position).toString());
        return convertView;

    }

    class ViewHolder{
        EditText editText;
        public ViewHolder(View view,int pisition){
            editText= (EditText) view.findViewById(R.id.editText);
            editText.setTag(pisition);//存tag值
            editText.addTextChangedListener(new TextSwitcher(this));
        }
    }

    class TextSwitcher implements TextWatcher {
        private ViewHolder mHolder;

        public TextSwitcher(ViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) mHolder.editText.getTag();//取tag值
            ((TeaAnsListActivity)context).saveEditData(position, s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
