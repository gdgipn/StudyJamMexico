package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.adapters.RecycleAdapter;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.models.Persona;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.util.LocalServiceJson;

/**
 * Created by Jhordan on 07/11/14.
 */
public class RecycleViewCardView extends Fragment implements RecycleAdapter.ItemRecycleClickListener{

    public RecycleViewCardView() {
    }

    public static RecycleViewCardView newInstance() {

        RecycleViewCardView recycleViewCardView = new RecycleViewCardView();
        Bundle extraArguments = new Bundle();
        recycleViewCardView.setArguments(extraArguments);
        return recycleViewCardView;
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recycle_view_card_view, container, false);
        return root;
    }

    RecycleAdapter recycleAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Persona> personaArrayList;
        LocalServiceJson readLocalJSON = new LocalServiceJson();
        personaArrayList = readLocalJSON.getPeople(getActivity());
        recycleAdapter = new RecycleAdapter(personaArrayList, R.layout.item_card_view);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.setItemRecycleClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        com.melnykov.fab.FloatingActionButton fab = (com.melnykov.fab.FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setShadow(true);


        fab.attachToRecyclerView(recyclerView);





    }

    @Override
    public void itemRecycleClicked(int position,String name) {

       String n = Integer.toString(position);
        Toast.makeText(getActivity(),n + name,Toast.LENGTH_SHORT).show();

    }
}
