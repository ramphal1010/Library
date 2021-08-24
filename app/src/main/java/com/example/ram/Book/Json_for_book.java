package com.example.ram.Book;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
@SuppressWarnings("deprecation")

public class Json_for_book extends AsyncTask<Void,Void,String> {
    private ArrayList<String> books_link ;

    @SuppressLint("StaticFieldLeak")
    private  Spinner spinner;
    private RequestQueue mQueue;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private Dialog dialog;
    private String url;
    private String objects;


        public Json_for_book(RequestQueue mQueue, Spinner spinner, Context context, String url, String objects,Dialog dialog){
           this.mQueue=mQueue;
           this.spinner=spinner;
           this.context=context;
           this.url=url;
           this.objects=objects;
           this.dialog=dialog;
       }


    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        jsonParse();
    }



    private void jsonParse(){


          books_link= new ArrayList<>();

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray(objects);
                    System.out.println("into response method");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject Branch=jsonArray.getJSONObject(i);

                        books_link.add(Branch.getString("book_link"));

                    }
                    spinner.setAdapter(new ArrayAdapter<>(context,android.R.layout.simple_spinner_dropdown_item, books_link));
                    dialog.dismiss();
                  } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);




    }


}

