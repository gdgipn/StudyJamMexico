package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.drawer;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.adapters.AdapterNavigationDrawer;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.fragments.FloatingActionButton;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.fragments.FloatingButton;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.fragments.RecycleViewCardView;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.fragments.SwipeRefreshLayout;


public class MainActivity extends ActionBarActivity implements AdapterNavigationDrawer.ItemRecycleClickListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    Toolbar toolbar;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mTitle != null) {
            toolbar.setTitle(mTitle);
        } else {
            toolbar.setTitle(getResources().getString(R.string.app_name));
        }
        setSupportActionBar(toolbar);

        mTitle = toolbar.getTitle();

        /**
         *  Set up the drawerList add DrawerLayout .
         */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar, this);
        fragmentInitial();


    }


    @Override
    public void itemRecycleClicked(int position) {

   
        switch (position) {

            case 0:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, FloatingActionButton.newInstance())
                        .commit();


                break;
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, FloatingActionButton.newInstance())
                        .commit();


                break;

            case 2:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, SwipeRefreshLayout.newInstance())
                        .commit();


                break;
            case 3:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, FloatingButton.newInstance())
                        .commit();


                break;
            case 4:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, RecycleViewCardView.newInstance())
                        .commit();


                break;


        }
        onSectionAttached(position);
    }


    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.floating_action_button);
                break;
            case 1:
                mTitle = getString(R.string.floating_action_button);
                break;
            case 2:
                mTitle = getString(R.string.swipe_refresh);
                break;
            case 3:
                mTitle = getString(R.string.floating_button);
                break;
            case 4:
                mTitle = getString(R.string.recycle_view);
                break;
        }


        if (toolbar != null) {
            toolbar.setTitle(mTitle);
            drawerLayout.closeDrawers();
        }
    }

    private void fragmentInitial(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FloatingActionButton.newInstance())
                .commit();
        toolbar.setTitle(getResources().getString(R.string.floating_action_button));
    }
}
