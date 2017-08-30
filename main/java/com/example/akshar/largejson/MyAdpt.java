package com.example.akshar.largejson;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdpt extends BaseAdapter {

  Context context;
  ArrayList<MyBean> arrayList;

  public MyAdpt(Context context, ArrayList<MyBean> arrayList) {
    this.context = context;
    this.arrayList = arrayList;
  }

  @Override
  public int getCount() {
    return arrayList.size();
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    convertView = layoutInflater.inflate(R.layout.mylayout, null);



    TextView txtSerrirs,txtLetitude,txtLongitude,txtLocality,txtNationality;



    txtSerrirs= (TextView)convertView.findViewById(R.id.txtseris);
    txtLetitude= (TextView)convertView.findViewById(R.id.txtlet);
    txtLongitude= (TextView)convertView.findViewById(R.id.txtlong);
    txtLocality= (TextView)convertView.findViewById(R.id.txtlocality);
    txtNationality= (TextView)convertView.findViewById(R.id.txtnationaliti);


    MyBean myBean = arrayList.get(position);

    //Log.e("Adapter", "getView: "+myBean.toString() + position);
    txtSerrirs.setText("Seris: "+myBean.getSeries1());
    txtLetitude.setText("Letitude: "+myBean.getLet());
    txtLongitude.setText("Longitude: "+myBean.getLongitude());
    txtLocality.setText("Locality: "+myBean.getLocalitty());
    txtNationality.setText("Nationality: "+myBean.getNationality());


    return convertView;
  }
}