package prakhar_squared_mayank.android_a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    //function to return to registration page
    public void returnReg() {
        Intent intent = new Intent(this, MainActivity.class);
	startActivity(intent);
    }
}
