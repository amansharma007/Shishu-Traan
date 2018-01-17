package activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ncpcr.visionaries.sishutraan.R;

public class Home2 extends AppCompatActivity /*implements FragmentDrawer.FragmentDrawerListener*/ {

    private Toolbar mToolbar;
    //private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        mToolbar = (Toolbar) findViewById(R.id.toolbar2);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //HAVE A LOOK AT THIS FOLLOWING PART LATER ON
        /*try {
            int titleId = getResources().getIdentifier("action_bar_title", "id",
                    "android");
            TextView yourTextView = (TextView) findViewById(titleId);
            yourTextView.setTextColor(ContextCompat.getColor(this, R.color.white));
            Typeface face = Typeface.createFromAsset(getAssets(), "fonts/hinglish.ttf");
            yourTextView.setTypeface(face);
        }catch(java.lang.NullPointerException e){
        }
        finally{}
        */

        /*drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);*/
        //displayView(0);
        // set the toolbar title
        getSupportActionBar().setTitle("Shishu Traan");
        Fragment fragment=null;
        fragment=new ComplaintFragment2();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body2, fragment);
            fragmentTransaction.commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
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

    /*@Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new ForumFragment();
                title = getString(R.string.title_forum);
                break;
            case 2:
                fragment = new HelpFragment();
                title = getString(R.string.title_help);
                break;
            case 3:
                fragment = new ComplaintFragment1();
                title = getString(R.string.title_complaint);
                break;
            case 4:
                fragment = new AboutUsFragment();
                title = getString(R.string.title_aboutus);
                break;
            case 5:
                fragment = new LoginFragment();
                title = getString(R.string.title_login);
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }*/
}
