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
}

