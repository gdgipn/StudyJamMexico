package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.drawer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.adapters.AdapterNavigationDrawer;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.interfaces.RecycleViewItemInterface;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.models.ItemNavigationDrawer;

/**
 * Created by Jhordan on 23/01/15.
 */
public class NavigationDrawerFragment extends Fragment {


    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";


    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private String name = "GDG Androidtitlan";
    private String email = "androidtitlan@gmail.com";
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    AdapterNavigationDrawer adapterNavigationDrawer;
    int PROFILE = R.drawable.ll;
    private List<RecycleViewItemInterface> listViewItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listViewItems = new ArrayList<>();
        listViewItems.add(new ItemNavigationDrawer(0, getResources().getColor(R.color.accent), R.string.app_name));
        listViewItems.add(new ItemNavigationDrawer(0, getResources().getColor(R.color.primary_dark), R.string.floating_action_button));
        listViewItems.add(new ItemNavigationDrawer(0, getResources().getColor(R.color.primary_dark), R.string.swipe_refresh));
        listViewItems.add(new ItemNavigationDrawer(0, getResources().getColor(R.color.primary_dark), R.string.floating_button));
        listViewItems.add(new ItemNavigationDrawer(0, getResources().getColor(R.color.primary_dark), R.string.recycle_view));

        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        adapterNavigationDrawer = new AdapterNavigationDrawer(listViewItems, name, email, PROFILE);
        mRecyclerView.setAdapter(adapterNavigationDrawer);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * This method set the navigation drawer behavior when it is open and close
     *
     * @param fragmentId   The id that we need find to manipulate de drawer.
     * @param drawerLayout The drawerlayout that contains the main container and the nav drawer view
     */

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar,
                      AdapterNavigationDrawer.ItemRecycleClickListener itemRecycleClickListener) {
        mDrawerLayout = drawerLayout;
        mFragmentContainerView = getActivity().findViewById(fragmentId);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        adapterNavigationDrawer.setItemRecycleClickListener(itemRecycleClickListener);

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }


            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //We fade the toolbar
                if (slideOffset < 0.5)
                    toolbar.setAlpha(1 - slideOffset);
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}
