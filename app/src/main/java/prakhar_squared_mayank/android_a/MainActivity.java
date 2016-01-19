package prakhar_squared_mayank.android_a;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    AutoCompleteTextView entr1;
    AutoCompleteTextView entr2;
    AutoCompleteTextView entr3;
    ProgressDialog progressDialog;
    private AutoCompleteTextView team,name1,name2,name3;

    public String res_code="RESPONSE_SUCCESS";
    public String res_msg="RESPONSE_MESSAGE";
    Button sendButton;
    private static final Pattern entryNumbersPat=Pattern.compile("201[234][A-Z][A-Z][1257]0[0-9]{3}",Pattern.CASE_INSENSITIVE );

    private MediaPlayer sound_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
* Logic for adding autocomplete feature*
* */
        final List<String> entries=new ArrayList<String>();
        final List<String> names=new ArrayList<String>();
        try {
            InputStream is = getApplicationContext().getResources().openRawResource(R.raw.entry_number_list);//am.open("entry_number_list.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(inputStreamReader);
            String entry;
            while((entry=br.readLine())!=null){
                String entryNum="";
                int index;
                for(index=0;entry.charAt(index)!='%';index++) {
                    entryNum += entry.charAt(index);
                }
                entries.add(entryNum);

                String name="";
                index++;
                for(;index<entry.length();index++) {
                    name += entry.charAt(index);
                }
                names.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String[] nameAdapter= (String[]) names.toArray(new String[names.size()]);


        team=(AutoCompleteTextView) findViewById(R.id.team);
        name1=(AutoCompleteTextView) findViewById(R.id.name1);
        name2=(AutoCompleteTextView) findViewById(R.id.name2);
        name3=(AutoCompleteTextView) findViewById(R.id.name3);
        entr1 = (AutoCompleteTextView) findViewById(R.id.entry1);
        entr2 = (AutoCompleteTextView) findViewById(R.id.entry2);
        entr3 = (AutoCompleteTextView) findViewById(R.id.entry3);

        ArrayAdapter <String> aa=new ArrayAdapter<String>(this,R.layout.select_dialog_item_material,nameAdapter);
        name1.setThreshold(1);name1.setAdapter(aa);
        name2.setThreshold(1);name2.setAdapter(aa);
        name3.setThreshold(1);name3.setAdapter(aa);

        name1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                int index = names.indexOf(name1.getText().toString());
                entr1.setText(entries.get(index));
                //System.out.println("id of the selected string: "+index);
            }
        });
        name2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                int index = names.indexOf(name2.getText().toString());
                entr2.setText(entries.get(index));
                //System.out.println("id of the selected string: "+index);
            }
        });
        name3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                int index = names.indexOf(name3.getText().toString());
                entr3.setText(entries.get(index));
                //System.out.println("id of the selected string: "+index);
            }
        });

        sendButton = (Button) findViewById(R.id.register);
        sendButton.setOnClickListener(this);
/*Autocomplete textboxes logic end
*
* When button is pressed
* */
    }
    public void register(){
        final String teams=team.getText().toString().trim();
        final String name1s=name1.getText().toString().trim();
        final String name2s=name2.getText().toString().trim();
        final String name3s=name3.getText().toString().trim();
        final String entry1s=entr1.getText().toString().trim();
        final String entry2s=entr2.getText().toString().trim();
        final String entry3s=entr3.getText().toString().trim();

        String url="http://agni.iitd.ernet.in/cop290/assign0/register/";
//        String url="http://cse.iitd.ac.in/scripts/test.php";
        
//        JsonObjectRequest req=new JsonObjectRequest(Request.Method.POST,url,param, new Response.Listener<JSONObject>() {
        StringRequest req=new StringRequest(Request.Method.POST,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                   // Log.d("Response", "Got Response");
                    JSONObject res = new JSONObject(response);
                    String success = res.getString(res_code);
                    String msg = res.getString(res_msg);
                    displayMessage(success, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                error.printStackTrace();
                if(error instanceof TimeoutError) {
                    showToast("The connection timed out.");
                    sound_player = MediaPlayer.create(MainActivity.this, R.raw.check_data_fail);
                } else if(error instanceof NoConnectionError) {
                    showToast("No internet connection available.");
                    sound_player = MediaPlayer.create(MainActivity.this, R.raw.check_data_fail);
                } else if(error instanceof NetworkError) {
                    showToast("A network error occurred.");
                    sound_player = MediaPlayer.create(MainActivity.this, R.raw.check_data_fail);
                } else if(error instanceof ServerError) {
                    showToast("A server error occurred.");
                    sound_player = MediaPlayer.create(MainActivity.this, R.raw.check_data_fail);
                } else {
                    showToast("An unidentified error occurred.");
                    sound_player = MediaPlayer.create(MainActivity.this, R.raw.check_data_fail);
                }
            }
        }){
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


    public void shar(View view){
        goToNextActivity();
    }

    public void displayMessage(String code, String msg)
    {
        if(code.equals("1"))
        {
            showToast("Success with message: "+msg);
            goToNextActivity();//code,msg);
        }
        else
        {
            showToast("Failure with message: "+msg);
        }
    }

    private void goToNextActivity(){//String code, String msg) {
        Intent intent=new Intent(getApplicationContext(),Result.class);
        startActivity(intent);
    }


    public void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.register:
                hideKeyboard();
                if(checkData())
                {
                    sound_player = MediaPlayer.create(MainActivity.this, R.raw.check_data_sucess);
                    showProgressDialog();
                    register();
                }
                else
                {
                    showToast("Input is invalid");
                    sound_player = MediaPlayer.create(MainActivity.this, R.raw.check_data_fail);
                }
                sound_player.setLooping(false);
                sound_player.start();
                break;
        }
    }


    public void hideKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean checkData() {
        String[] name = new String[3];
        String[] entry = new String[3];
        String teamName=team.getText().toString().trim();
        name[0]=name1.getText().toString().trim();
        name[1]=name2.getText().toString().trim();
        name[2]=name3.getText().toString().trim();
        entry[0]=entr1.getText().toString().trim();
        entry[1]=entr2.getText().toString().trim();
        entry[2]=entr3.getText().toString().trim();

        if(teamName.length() == 0)
        {
            showToast("Enter a team name.");
            return false;
        }
        for(int index=0;index<3;index++)
        {
            if(name[index].length() == 0)
            {
                showToast("Enter all names.");
                return false;
            }
            else
            {
                for(int nameIndex=0;nameIndex<name[index].length();nameIndex++)
                {
                    int charASCII = (int)(name[index].charAt(nameIndex));
                    if(!((charASCII >= (int)'a' && charASCII <= (int)'z')||(charASCII >= (int)'A' && charASCII <= (int)'Z')||(charASCII == (int)' ')))
                    {
                        showToast("Enter valid names.");
                        return false;
                    }
                }
            }
            if(entry[index].length() == 0)
            {
                showToast("Enter all entry numbers.");
                return false;
            }
            else
            {
                if(!checkEntryNumber(entry[index]))
                {
                    showToast("Enter valid entry number.");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkEntryNumber(String number)
    {
        Matcher matcher = entryNumbersPat.matcher(number);
        if(matcher.matches())
        {
            return true;
        }
        return false;
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.Base_Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Registering team...");
        progressDialog.show();
    }
}
