package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.R;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.interfaces.RecycleViewItemInterface;
import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.models.ItemNavigationDrawer;

/**
 * Created by Jhordan on 23/01/15.
 */
public class AdapterNavigationDrawer extends RecyclerView.Adapter<AdapterNavigationDrawer.ViewHolder> {

    private static final int HEADER = 0;
    private static final int ITEM = 1;
    private String name, email;
    private int profile;
    private List<RecycleViewItemInterface> recycleViewItemInterfacesList;
    private ItemRecycleClickListener itemRecycleClickListener;

    public AdapterNavigationDrawer(List<RecycleViewItemInterface> recycleViewItemInterfacesList, String name, String email, int profile) {
        this.recycleViewItemInterfacesList = recycleViewItemInterfacesList;
        this.name = name;
        this.email = email;
        this.profile = profile;

    }

    /**
     * Below first we override the method onCreateViewHolder which is called when the ViewHolder is Created
     */

    @Override
    public AdapterNavigationDrawer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        ViewHolder viewHolderItem, viewHolderHeader;

        if (viewType == ITEM) {


            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_navigation_drawer, parent, false);
            viewHolderItem = new ViewHolder(root, viewType);
            return viewHolderItem;


        } else if (viewType == HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_navigation_drawer, parent, false);
            viewHolderHeader = new ViewHolder(v, viewType);
            return viewHolderHeader;


        }
        return null;

    }

    /**
     * Next we override a method which is called when the item in a row is needed to be displayed, here the int position
     * Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
     * which view type is being created 1 for item row
     */

    @Override
    public void onBindViewHolder(AdapterNavigationDrawer.ViewHolder holder, int position) {

        RecycleViewItemInterface item = recycleViewItemInterfacesList.get(position);

        if (holder.typeHolder == 1) {
            ItemNavigationDrawer customItem = (ItemNavigationDrawer) item;

            if (item != null) {
                if (!item.isSection()) {

                    holder.titleItem.setText(customItem.getContentTitleFromResources());
                    holder.titleItem.setTextColor(customItem.getTextColor());
                    holder.titleItem.setBackgroundColor(customItem.getBackgroundContentSection());
                    holder.iconItem.setImageResource(customItem.getIcon());
                }
            }


        } else {

            holder.imageProfile.setImageResource(profile);
            holder.nameProfile.setText(name);
            holder.emailProfile.setText(email);

        }
    }

    /**
     * This method returns the number of items present in the list
     * the number of items in the list will be +1 the titles including the header view.
     */
    @Override
    public int getItemCount() {
        return  recycleViewItemInterfacesList.size();
    }


    /**
     * Witht the following method we check what type of view is being passed
     */
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return HEADER;

        return ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        int typeHolder;
        TextView titleItem, nameProfile, emailProfile;
        ImageView iconItem, imageProfile;


        public ViewHolder(final View itemView, int ViewType) {

            super(itemView);

          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  if (itemRecycleClickListener != null) {
                      // if(getPosition() != 0){
                           itemRecycleClickListener.itemRecycleClicked(getPosition());
                      // }



                  }

              }
          });

            if (ViewType == ITEM) {

                titleItem = (TextView) itemView.findViewById(R.id.rowText);
                iconItem = (ImageView) itemView.findViewById(R.id.rowIcon);
                typeHolder = 1;



            } else {

                nameProfile = (TextView) itemView.findViewById(R.id.name);
                emailProfile = (TextView) itemView.findViewById(R.id.email);
                imageProfile = (ImageView) itemView.findViewById(R.id.circleView);
                typeHolder = 0;
            }

        }


    }



    public interface ItemRecycleClickListener{
        /**
         * This method will invoke when an item from the list be clicked
         * */
        public void itemRecycleClicked(int position);
    }

    public void setItemRecycleClickListener(ItemRecycleClickListener itemRecycleClickListener) {
        this.itemRecycleClickListener = itemRecycleClickListener;
    }
}
