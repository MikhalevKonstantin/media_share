package ke.co.mediashare.mediashare;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by guidovanrossum on 6/6/15.
 */
public class MediaShareListAdapter extends RecyclerView.Adapter<MediaShareListAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0; // Declaring variable to store the view that is being worked on
    private static final int TYPE_ITEM = 1; // Variable to store the header

    private String navigation_titles[]; // String array to store the titles of various items of the recycler view
    private int navigation_icons[]; // Int array to store the icons that will be used in the recycler view

    private String profile_name; //String variable that will store the profile name on the recycler header
    private int profile_photo; //int variable that will store the profile photo on the recycler header
    private String profile_email; //String variable to store the profile email address on the recycler header


    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolders are used to store the inflated views in order to recycle them
    public static class ViewHolder extends RecyclerView.ViewHolder {
        int holder_id;

        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;


        // Creating ViewHolder Constructor with View and viewType as parameters
        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                // Defining the TextView that will hold navigation list items
                textView = (TextView) itemView.findViewById(R.id.rowText);
                // Defining the ImageView that will hold the navigation list item's indicators
                imageView = (ImageView) itemView.findViewById(R.id.rowImage);
            } else {

                // Defining the TextView that will hold the profile name on the header
                Name = (TextView) itemView.findViewById(R.id.profile_name);
                // Defining the TextView that will hold the profile email on the header
                email = (TextView) itemView.findViewById(R.id.profile_email);
                // Defining the ImageView that will hold the profile photo on the header
                profile = (ImageView) itemView.findViewById(R.id.profile_photo);
                holder_id = 0;
            }
        }


    }

    // Class Constructor with parameters that will be passed during instantiation
    MediaShareListAdapter(String Navigation_Titles[], int Navigation_Icons[], String Name, String Email, int Profile) {
        navigation_titles = Navigation_Titles;
        navigation_icons = Navigation_Icons;
        profile_name = Name;
        profile_email = Email;
        profile_photo = Profile;

    }

    @Override
    public MediaShareListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_drawer_rows, parent, false); //Inflating the layout

            ViewHolder viewHolder = new ViewHolder(view, viewType); //Creating ViewHolder and passing the object of type view

            return viewHolder; // Returning the created object

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_header, parent, false); //Inflating the layout

            ViewHolder viewHolder2 = new ViewHolder(view, viewType); //Creating ViewHolder and passing the object of type view

            return viewHolder2; //returning the object created


        }
        return null;

    }

    @Override
    public void onBindViewHolder(MediaShareListAdapter.ViewHolder holder, int position) {
        if (holder.holder_id == 1) {
            holder.textView.setText(navigation_titles[position - 1]);
            holder.imageView.setImageResource(navigation_icons[position - 1]);
        } else {

            holder.profile.setImageResource(profile_photo);
            holder.Name.setText(profile_name);
            holder.email.setText(profile_email);
        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return navigation_titles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}

