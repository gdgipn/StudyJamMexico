package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.activities.DetailMaterial;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.drawer.MainActivity;

/**
 * Created by Jhordan on 07/11/14.
 */
public class FloatingButton extends Fragment {

    public FloatingButton() {
    }

    public static FloatingButton newInstance() {

        FloatingButton floatingButton = new FloatingButton();
        Bundle extraArguments = new Bundle();
        floatingButton.setArguments(extraArguments);
        return floatingButton;
    }

    private ListView listView;
    private List<String> comunity;
    private FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_floating_button, container, false);

        listView = (ListView) root.findViewById(R.id.listView);
        comunity = Arrays.asList(getResources().getStringArray(R.array.comunity));
        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, comunity));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String n = (String) listView.getItemAtPosition(position);
                Toast.makeText(getActivity(), n, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetailMaterial.class);
                intent.putExtra("dato",n);
               startActivity(intent);
            }
        });


        /**
         * floatingActionButton use this library compile 'com.getbase:floatingactionbutton:1.6.0'
         */

        floatingActionButton = (com.getbase.floatingactionbutton.FloatingActionButton) root.findViewById(R.id.normal_plus);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getResources().getString(R.string.msg), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
