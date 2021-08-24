package com.example.ram.Paper;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
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

public class Json extends AsyncTask<Void,Void,String> {
    ArrayList<String> branch_list;
    ArrayList<String> subject_link ;
    Spinner spinner1,spinner2;
    private RequestQueue mQueue;
    Context context;
    String url;
    String objects;
    Dialog dialog;



        public Json(RequestQueue mQueue, Spinner spinner1, Spinner spinner2, Context context, String url, String objects, Dialog dialog){
           this.mQueue=mQueue;
           this.spinner1=spinner1;
           this.spinner2=spinner2;
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
       System.out.println(spinner1.isEnabled());
       jsonParse();

       super.onPostExecute(s);

    }



    public void jsonParse (){


        branch_list=new ArrayList<>();
        subject_link= new ArrayList<>();

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray(objects);

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject Branch=jsonArray.getJSONObject(i);
                        branch_list.add(Branch.getString("name"));
                        subject_link.add(Branch.getString("link"));
                    }

                    spinner1.setAdapter(new ArrayAdapter<>(context,android.R.layout.simple_spinner_dropdown_item, branch_list));
                    spinner1.setVisibility(View.VISIBLE);
                    spinner2.setAdapter(new ArrayAdapter<>(context,android.R.layout.simple_dropdown_item_1line,subject_link));
                    dialog.dismiss();
                }
                catch (JSONException e)
                {
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

