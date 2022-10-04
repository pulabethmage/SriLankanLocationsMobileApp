package lk.pula.srilankaslocationsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements Response.Listener<String>,Response.ErrorListener {

    ArrayList<String> provincesArrayKeys,districtsArrayKeys,dsdivisionsArrayKeys,gndivissionsArrayKeys;
    ArrayList<String> provincesArrayValues,districtsArrayValues,dsdivisionsArrayValues,gndivissionsArrayValues;

    AutoCompleteTextView auto_tv_provinces,auto_tv_districts,auto_tv_dsdivisions,auto_tv_gndivissions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auto_tv_provinces = findViewById(R.id.dropdown_menu_province);
        auto_tv_districts = findViewById(R.id.dropdown_menu_district);
        auto_tv_dsdivisions = findViewById(R.id.dropdown_menu_dsdivision);
        auto_tv_gndivissions = findViewById(R.id.dropdown_menu_gndivision);

        loadProvinces();

        auto_tv_provinces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,provincesArrayKeys.get(i) , Toast.LENGTH_SHORT).show();
                loadDistricts(provincesArrayKeys.get(i));
            }
        });

        auto_tv_districts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,districtsArrayKeys.get(i) , Toast.LENGTH_SHORT).show();
                loadDsDivisions(districtsArrayKeys.get(i));
            }
        });

        auto_tv_dsdivisions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,dsdivisionsArrayKeys.get(i) , Toast.LENGTH_SHORT).show();
                loadGnDivisions(dsdivisionsArrayKeys.get(i));
            }
        });
        auto_tv_gndivissions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,gndivissionsArrayKeys.get(i) , Toast.LENGTH_SHORT).show();
                //loadGnDivisions(gndivissionsArrayKeys.get(i));
            }
        });

    }

    public void loadProvinces()
    {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url =new DbUrls().ServerIpAddress +"/SLLA/all_provinces.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                       setProvinces(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"No Data Found!",Toast.LENGTH_LONG).show();

                    }
                });

        queue.add(request);

    }
    public void setProvinces(JSONArray response) {

        provincesArrayKeys = new ArrayList<String>();
        provincesArrayValues = new ArrayList<String>();

        for (int i = 0; i<response.length();i++)
        {
            try {


                JSONObject obj = response.getJSONObject(i);

                String pro_code =  obj.getString("pro_Code");
                String pro_name =  obj.getString("Pro_Name");


                provincesArrayKeys.add(pro_code);
                provincesArrayValues.add(pro_name);
               //hash_provinces.put(pro_code,pro_name);


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        auto_tv_provinces.setInputType(InputType.TYPE_NULL);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.designation_drop_item, provincesArrayValues);
        auto_tv_provinces.setAdapter(adapter);

    }

    public void loadDistricts(String pro_id)
    {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url =new DbUrls().ServerIpAddress +"/SLLA/all_districts.php?pro_id="+pro_id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setDistricts(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"No Data Found!",Toast.LENGTH_LONG).show();

                    }
                });

        queue.add(request);

    }
    public void setDistricts(JSONArray response) {

        districtsArrayKeys = new ArrayList<String>();
        districtsArrayValues = new ArrayList<String>();

        for (int i = 0; i<response.length();i++)
        {
            try {

                JSONObject obj = response.getJSONObject(i);

                String dis_code =  obj.getString("dis_Code");
                String dis_name =  obj.getString("Dis_Name");

                districtsArrayKeys.add(dis_code);
                districtsArrayValues.add(dis_name);


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        auto_tv_districts.setInputType(InputType.TYPE_NULL);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.designation_drop_item, districtsArrayValues);
        auto_tv_districts.setAdapter(adapter);

    }

    public void loadDsDivisions(String dis_id)
    {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url =new DbUrls().ServerIpAddress +"/SLLA/all_dsdivisions.php?dis_id="+dis_id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setDsDivisions(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"No Data Found!",Toast.LENGTH_LONG).show();

                    }
                });

        queue.add(request);

    }
    public void setDsDivisions(JSONArray response) {

        dsdivisionsArrayKeys = new ArrayList<String>();
        dsdivisionsArrayValues = new ArrayList<String>();

        for (int i = 0; i<response.length();i++)
        {
            try {

                JSONObject obj = response.getJSONObject(i);

                String ds_code =  obj.getString("ds_Code");
                String ds_name =  obj.getString("Ds_Name");

                dsdivisionsArrayKeys.add(ds_code);
                dsdivisionsArrayValues.add(ds_name);


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        auto_tv_dsdivisions.setInputType(InputType.TYPE_NULL);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.designation_drop_item, dsdivisionsArrayValues);
        auto_tv_dsdivisions.setAdapter(adapter);

    }

    public void loadGnDivisions(String ds_id)
    {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url =new DbUrls().ServerIpAddress +"/SLLA/all_gndivisions.php?ds_id="+ds_id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setGnDivisions(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"No Data Found!",Toast.LENGTH_LONG).show();

                    }
                });

        queue.add(request);

    }
    public void setGnDivisions(JSONArray response) {

        gndivissionsArrayKeys = new ArrayList<String>();
        gndivissionsArrayValues = new ArrayList<String>();

        for (int i = 0; i<response.length();i++)
        {
            try {

                JSONObject obj = response.getJSONObject(i);

                String gn_code =  obj.getString("gn_Code");
                String gn_name =  obj.getString("Gnd_Name");

                gndivissionsArrayKeys.add(gn_code);
                gndivissionsArrayValues.add(gn_name);


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        auto_tv_gndivissions.setInputType(InputType.TYPE_NULL);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.designation_drop_item, gndivissionsArrayValues);
        auto_tv_gndivissions.setAdapter(adapter);

    }



    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"Error!"+error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {

    }
}