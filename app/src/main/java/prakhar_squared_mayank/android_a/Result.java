package prakhar_squared_mayank.android_a;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//import com.facebook.FacebookSdk;
//import com.facebook.share.model.ShareLinkContent;


public class Result extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


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

    //build the url or the data to be shared

    private void shareUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        //share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        share.putExtra(Intent.EXTRA_SUBJECT, "COP290 Registeration");
        share.putExtra(Intent.EXTRA_TEXT, "Hey, I have registered for the COP290 challenge. Have you? "+" https://docs.google.com/document/d/1UDYSaeB0RJVeDF-_ZYYH_Zi9Gl9iqICKlaO1-wf4mNg/pub");

        startActivity(Intent.createChooser(share, "Share link!"));
    }
}

