package prakhar_squared_mayank.android_a;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    AutoCompleteTextView entr1;
    AutoCompleteTextView entr2;
    AutoCompleteTextView entr3;
    Button sendButton;

    private static final Pattern entryNumbersPat=Pattern.compile("201[234][A-Z][A-Z][1257]0[0-9]{3}",Pattern.CASE_INSENSITIVE );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<String> entries=new ArrayList<String>();

        try {
            InputStream is = getApplicationContext().getResources().openRawResource(R.raw.entry_number_list);//am.open("entry_number_list.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(inputStreamReader);
            String entry;
            //br=new BufferedReader(new FileReader("entryNumberList.txt"));
            while((entry=br.readLine())!=null){
                entries.add(entry);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] entryNumbers= (String[]) entries.toArray(new String[entries.size()]);


        entr1 = (AutoCompleteTextView) findViewById(R.id.entry1);
        entr2 = (AutoCompleteTextView) findViewById(R.id.entry2);
        entr3 = (AutoCompleteTextView) findViewById(R.id.entry3);

        ArrayAdapter <String> aa=new ArrayAdapter<String>(this,R.layout.select_dialog_item_material,entryNumbers);
        entr1.setThreshold(1);entr1.setAdapter(aa);
        entr2.setThreshold(1);entr2.setAdapter(aa);
        entr3.setThreshold(1);entr3.setAdapter(aa);

        sendButton = (Button)findViewById(R.id.send);
        sendButton.setOnClickListener(this);
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
            case R.id.send:
                if(checkData())
                {
                    sendData();
                }
                break;
        }
    }

    public boolean checkData() {
        return true;
    }

    public void sendData() {

    }
}
