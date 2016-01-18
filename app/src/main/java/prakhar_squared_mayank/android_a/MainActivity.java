package prakhar_squared_mayank.android_a;

import android.app.DownloadManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {

    private static final Pattern entryNumbersPat=Pattern.compile("201[234][A-Z][A-Z][1257]0[0-9]{3}",Pattern.CASE_INSENSITIVE );

    private EditText team,name1,name2,name3,entry1,entry2,entry3;
    //private String url="http://agni.iitd.ernet.in/cop290/assign0/register/";

//    private static final String[] ent=new String[];//{"prakhar","shubham","mohit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
* Logic for adding autocomplete feature*
* */
        List<String> entries=new ArrayList<String>();
        try {
            InputStream is = getApplicationContext().getResources().openRawResource(R.raw.entry_number_list);//am.open("entry_number_list.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(inputStreamReader);
            String entry;
            //br=new BufferedReader(new FileReader("entryNumberList.txt"));
            while((entry=br.readLine())!=null){
             //   System.out.println(entry);
                entries.add(entry);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] entryNumbers= (String[]) entries.toArray(new String[entries.size()]);

        AutoCompleteTextView entr1=(AutoCompleteTextView) findViewById(R.id.entry1);
        AutoCompleteTextView entr2=(AutoCompleteTextView) findViewById(R.id.entry2);
        AutoCompleteTextView entr3=(AutoCompleteTextView) findViewById(R.id.entry3);
        //AutoCompleteTextView entr1=(AutoCompleteTextView) findViewById(R.id.entry1auto);
        ArrayAdapter <String> aa=new ArrayAdapter<String>(this,R.layout.select_dialog_item_material,entryNumbers);
        entr1.setThreshold(1);
        entr1.setAdapter(aa);
        entr2.setThreshold(1);entr2.setAdapter(aa);
        entr3.setThreshold(1);entr3.setAdapter(aa);
/*Autocomplete textboxes logic end
*
* When button is pressed
* */
//        Button register=(Button) findViewById(R.id.register);
//        register.setOnClickListener(this);
    }

    public void register(View view){
        team=(AutoCompleteTextView) findViewById(R.id.team);
        //String t=team.getText().toString();
        name1=(AutoCompleteTextView) findViewById(R.id.name1);
        name2=(AutoCompleteTextView) findViewById(R.id.name2);
        name3=(AutoCompleteTextView) findViewById(R.id.name3);
        entry1=(AutoCompleteTextView) findViewById(R.id.entry1);
        entry2=(AutoCompleteTextView) findViewById(R.id.entry2);
        entry3=(AutoCompleteTextView) findViewById(R.id.entry3);
        final String teams="abc";//team.getText().toString().trim();
        final String name1s="abc";//name1.getText().toString().trim();
        final String name2s="abc";//name2.getText().toString().trim();
        final String name3s="abc";//name3.getText().toString().trim();
        final String entry1s="abc";//entry1.getText().toString().trim();
        final String entry2s="abc";//entry2.getText().toString().trim();
        final String entry3s="abc";//entry3.getText().toString().trim();


        Map<String,String> params=new HashMap<String,String>();
        params.put("teamname",teams);
        params.put("name1",name1s);
        params.put("name2",name2s);
        params.put("name3",name3s);
        params.put("entry1",entry1s);
        params.put("entry2",entry2s);
        params.put("entry3", entry3s);

        JSONObject param=new JSONObject(params);
       // String url="http://agni.iitd.ernet.in/cop290/assign0/register/";
        String url="http://cse.iitd.ac.in/scripts/test.php";

        System.out.println("Someone clicked");

     //   JsonObjectRequest req=new JsonObjectRequest(Request.Method.POST,url,param, new Response.Listener<JSONObject>() {
            StringRequest req=new StringRequest(Request.Method.POST,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("You are in yupieee");
                    System.out.println("teamname"+teams);
                    System.out.println(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }

        ){

            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("teamname", teams);
                params.put("name1", name1s);
                params.put("name2", name2s);
                params.put("name3", name3s);
                params.put("entry1", entry1s);
                params.put("entry2", entry2s);
                params.put("entry3", entry3s);
                return params;
            }

            @Override
            public String getBodyContentType(){
                return "application/x-www-form-urlencoded;";
            }
        };

        Volley.newRequestQueue(this).add(req);
//        if(volley_singleton.getInstance()==null) {
//            volley_singleton a = new volley_singleton();
//        }
      //  volley_singleton b=volley_singleton.getInstance();
       // b.getRequestQueue().add(req);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
