package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;


public class DetailMaterial extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment {


        String value;
        TextView textView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_launcher);
            textView = (TextView) rootView.findViewById(R.id.textView);

            Intent intent = getActivity().getIntent();
            if (intent != null) {

                String dato = intent.getStringExtra("dato");
                textView.setText(dato);
                toolbar.setSubtitle(dato);


            }
            ((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);


            return rootView;

        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);


        }


    }


}
