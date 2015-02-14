package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;

/**
 * Created by Jhordan on 25/01/15.
 */
public class SwipeRefreshLayout extends Fragment {

    public SwipeRefreshLayout() {
    }

    public static SwipeRefreshLayout newInstance() {

        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout();
        Bundle extraArguments = new Bundle();

        swipeRefreshLayout.setArguments(extraArguments);
        return swipeRefreshLayout;
    }

    private ListView listView;
    private android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;
    private List<String> comunity;
    private List<String> datos;
    private int counter = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_swipe_refresh_layout, container, false);

        swipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout)
                root.findViewById(R.id.swipe_refresh_layout);
        listView = (ListView) root.findViewById(R.id.listview);
        comunity = Arrays.asList(getResources().getStringArray(R.array.comunity));
        datos = Arrays.asList(getResources().getStringArray(R.array.languages));
        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, comunity));
        swipeRefreshLayout.setColorSchemeResources(R.color.primary_dark, R.color.primary, R.color.accent);
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeListener();
    }

    /**
     * SwipeListener make refresh UI
     */
    private void swipeListener() {

        swipeRefreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        counter += 1;
                        if (counter == 1) {
                            getComunityRandom();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            getLanguagesRefresh();
                            counter = 0;
                        }

                    }
                }, 2500);

            }

        });
    }

    private void getComunityRandom() {

        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, makeComunityRandom()));
        swipeRefreshLayout.setRefreshing(false);
    }

    private List<String> makeComunityRandom() {
        int randomComunityNameIndex;
        List<String> comunityRandom = new ArrayList<>();
        for (int i = 0; i < comunity.size(); i++) {
            randomComunityNameIndex = new Random().nextInt(comunity.size() - 1);
            comunityRandom.add(comunity.get(randomComunityNameIndex));
        }
        return comunityRandom;
    }

    private void getLanguagesRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, datos));
                swipeRefreshLayout.setRefreshing(false);

            }
        }, 2500);


    }
}
