package prakhar_squared_mayank.android_a;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Prakhar on 1/16/2016.
 */

public class volley_singleton{//} extends Application {
    private RequestQueue requestQueue;
    private static volley_singleton insta;
    private static Context con;

    public static final String TAG=volley_singleton.class.getName();

    public volley_singleton(Context context){
        con=context;
        //super.onCreate();

        requestQueue= getRequestQueue();  //Volley.newRequestQueue(getApplicationContext());
      //  insta=this;
    }

    public static synchronized volley_singleton getInstance(Context context){
        if (insta== null) {
            insta= new volley_singleton(context);
        }
        return insta;

    }

    public RequestQueue getRequestQueue(){
        if (requestQueue== null) {
            requestQueue = Volley.newRequestQueue(con, 10 * 1024 * 1024); // this for caching
        }

        return requestQueue;
    }

    public <T> void add(Request<T> req){
//        if(requestQueue==null){
//            requestQueue=Volley.newRequestQueue(con.getApplicationContext(), 10 * 1024 * 1024);
//        }
        getRequestQueue().add(req);
    }
    public void cancel(){
        requestQueue.cancelAll(TAG);
    }


}
