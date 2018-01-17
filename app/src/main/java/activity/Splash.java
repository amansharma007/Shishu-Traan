package activity;


/**
 * Created by Aman Sharma on 3/21/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ncpcr.visionaries.sishutraan.R;

public class Splash extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        Thread inThread=new Thread(){
            public void run()
            {
                try
                {
                    sleep(3000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent startingPt=new Intent(Splash.this,Home.class);
                    startActivity(startingPt);
                }
            }
        };
        inThread.start();
    }





    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
