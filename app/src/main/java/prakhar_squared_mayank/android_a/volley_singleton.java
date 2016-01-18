package prakhar_squared_mayank.android_a;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Prakhar on 1/16/2016.
 */

public class volley_singleton extends Application {
    private RequestQueue requestQueue;
    private static volley_singleton insta;

    public static final String TAG=volley_singleton.class.getName();
    @Override
    public void onCreate(){
        super.onCreate();
        insta=this;
        requestQueue= Volley.newRequestQueue(getApplicationContext());
    }

    public static synchronized volley_singleton getInstance(){
        return insta;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public <T> void add(Request<T> req){
        if(requestQueue==null){
            requestQueue=Volley.newRequestQueue(getApplicationContext());
        }
        getRequestQueue().add(req);
    }
    public void cancel(){
        requestQueue.cancelAll(TAG);
    }


}
