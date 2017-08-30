package com.example.akshar.largejson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<MyBean> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.mylist);

        AsyncLoader asyncLoader=new AsyncLoader(MainActivity.this, "http://ergast.com/api/f1/2004/1/results.json", new Onacysnkloader() {
            @Override
            public void Onresult(String result) {
                try {

                    Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                    MyBean myBean=new MyBean();
                    HashMap<String,String> hashMap=new HashMap<>();
                    arrayList=new ArrayList<>();
                    //main
                    JSONObject mainJsonObject=new JSONObject(result);

                    //MRData
                    JSONObject jsonObject2=mainJsonObject.getJSONObject("MRData");

                    //serise value
                    myBean.setSeries1(jsonObject2.getString("series"));
                    //RaceTable
                    JSONObject jsonObject3=jsonObject2.getJSONObject("RaceTable");

                    //Races
                    JSONArray jsonArray=jsonObject3.getJSONArray("Races");
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject jsonObject4=jsonArray.getJSONObject(i);

                        //circute
                        JSONObject jsonObject5=jsonObject4.getJSONObject("Circuit");

                            //Location
                            JSONObject jsonObject6=jsonObject5.getJSONObject("Location");

                            //values
                            myBean.setLet(jsonObject6.getString("lat"));
                            myBean.setLongitude(jsonObject6.getString("long"));
                            myBean.setLocalitty(jsonObject6.getString("locality"));

                        //Result
                        JSONArray jsonArray1=jsonObject4.getJSONArray("Results");
                        for (int ij = 0; ij <= jsonArray1.length() ; ij++) {
                            JSONObject jsonObject7=jsonArray1.getJSONObject(ij);

                            //driver
                            JSONObject jsonObject8=jsonObject7.getJSONObject("Driver");

                            //value
                            myBean.setNationality(jsonObject8.getString("nationality"));

                            Log.e("Hashmap", "Onresult: "+hashMap.toString() );
                            arrayList.add(myBean);
                            Log.e("Main ", "Mybean: "+myBean.toString() );
                            Log.e("Main " , "Onresult: "+arrayList.toString());
                            MyAdpt myAdpt=new MyAdpt(MainActivity.this,arrayList);
                            listView.setAdapter(myAdpt);


                        }

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        asyncLoader.execute();

    }
}
