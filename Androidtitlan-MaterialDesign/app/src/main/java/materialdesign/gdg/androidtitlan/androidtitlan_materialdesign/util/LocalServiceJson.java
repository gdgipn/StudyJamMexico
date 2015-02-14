package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.models.Persona;


/**
 * Created by Jhordan  on 25/01/15.
 */

public class LocalServiceJson {

    private String json = "";
    private ArrayList<Persona> data = new ArrayList<>();
    private BufferedReader bufferedReader;
    private StringBuilder builder;
    private static String NAME_JSON = "developers.json";
    private static String ID_PEOPLE = "id";
    private static String ID_NAME = "name";
    private static String ID_COMUNITY = "comunity";


    public ArrayList<Persona> getPeople(Context context) {


        try {

            builder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(NAME_JSON)));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

            bufferedReader.close();
            json = builder.toString();


            JSONArray jsonArray = new JSONArray(json);

            for (int index = 0; index < jsonArray.length(); index++) {
                Persona persona = new Persona();
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                persona.setId(jsonObject.getInt(ID_PEOPLE));
                persona.setName(jsonObject.getString(ID_NAME));
                persona.setComunity(jsonObject.getString(ID_COMUNITY));
                data.add(persona);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(context, context.getResources().getString(R.string.msg), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, context.getResources().getString(R.string.msg), Toast.LENGTH_SHORT).show();
        }

        return data;
    }


}
