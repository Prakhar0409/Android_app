package prakhar_squared_mayank.android_a;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;


public class Result extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        FacebookSdk.sdkInitialize(getApplicationContext());


        Button share = (Button) findViewById(R.id.facebookShare);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareUrl();
            }
        });

        //facebook building model of shre content
//        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
//                .setContentTitle("COP290 Registeration")
//                .setContentDescription("This app registers you to the challenging COP290")
//                .setContentUrl(Uri.parse("http://www.cse.iitd.ac.in/~vinay"))
//                .build();
//    }
    }

    private void shareUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        //share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        share.putExtra(Intent.EXTRA_SUBJECT, "COP290 Registeration");
        share.putExtra(Intent.EXTRA_TEXT, "https://www.cse.iitd.ac.in");

        startActivity(Intent.createChooser(share, "Share link!"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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

