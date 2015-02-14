package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;

/**
 * Created by Jhordan on 07/11/14.
 */
public class FloatingActionButton extends Fragment {

    public FloatingActionButton() {
    }

    public static FloatingActionButton newInstance() {

        FloatingActionButton floatingActionButton = new FloatingActionButton();
        Bundle extraArguments = new Bundle();
        floatingActionButton.setArguments(extraArguments);
        return floatingActionButton;
    }

    private ListView listView;
    private List<String> languages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_floating_action_button, container, false);

        listView = (ListView) root.findViewById(R.id.listView);
        languages = Arrays.asList(getResources().getStringArray(R.array.languages));
        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, languages));
        /**
         * floatingActionButton use this library compile 'com.melnykov:floatingactionbutton:1.0.6'
         */

        com.melnykov.fab.FloatingActionButton fab = (com.melnykov.fab.FloatingActionButton) root.findViewById(R.id.fab);
        fab.setShadow(true);
        fab.attachToListView(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String n = (String) listView.getItemAtPosition(position);
               Toast.makeText(getActivity(),n,Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), getResources().getString(R.string.msg), Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
}
