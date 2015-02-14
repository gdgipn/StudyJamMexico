package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.models.Persona;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private ArrayList<Persona> personas;
    private int itemLayout;
    ItemRecycleClickListener itemRecycleClickListener;


    public RecycleAdapter(ArrayList<Persona> data, int itemLayout) {
        personas = data;
        this.itemLayout = itemLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView name;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemRecycleClickListener != null) {

                        Persona course = personas.get(getPosition());



                        itemRecycleClickListener.itemRecycleClicked(getPosition(),course.getName());


                    }
                }
            });


            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.comunity);
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Persona course = personas.get(position);
        viewHolder.name.setText(course.getName());
        viewHolder.description.setText(course.getComunity());

        switch (course.getId()) {
            case 1:

                viewHolder.image.setImageResource(R.drawable.erik);
                break;

            case 2:

                viewHolder.image.setImageResource(R.drawable.pett);
                break;

            case 3:
                viewHolder.image.setImageResource(R.drawable.t);
                break;

            case 4:
                viewHolder.image.setImageResource(R.drawable.kike);
                break;

            case 5:
                viewHolder.image.setImageResource(R.drawable.sandy);
                break;
        }
        viewHolder.itemView.setTag(course);
    }


    @Override
    public int getItemCount() {
        return personas.size();
    }


    public interface ItemRecycleClickListener{
        /**
         * This method will invoke when an item from the list be clicked
         * */
        public void itemRecycleClicked(int position,String name);
    }

    public void setItemRecycleClickListener(ItemRecycleClickListener itemRecycleClickListener) {
        this.itemRecycleClickListener = itemRecycleClickListener;
    }

}
