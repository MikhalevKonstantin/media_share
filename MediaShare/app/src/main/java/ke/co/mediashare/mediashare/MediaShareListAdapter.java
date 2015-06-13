package ke.co.mediashare.mediashare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by guidovanrossum on 6/6/15.
 */
public class MediaShareListAdapter extends RecyclerView.Adapter<MediaShareListAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0; // Declaring variable to store the view that is being worked on
    private static final int TYPE_ITEM = 1; // Variable to store the header

    private String navigation_titles[]; // String array to store the titles of various items of the recycler view
    private int navigation_icons[]; // Int array to store the icons that will be used in the recycler view

    private String name;
    private int profile;
    private String email;

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolders are used to store the inflated views in order to recycle them
    public static class ViewHolder extends RecyclerView.ViewHolder {
        int holder_id;
        TextView viewHolder_textView;
        ImageView viewHolder_imageView;
        TextView viewHolder_Name;
        TextView viewHolder_Email;
        ImageView viewHolder_Profile;


        // Creating ViewHolder Constructor with View and viewType as parameters
        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            if (ViewType == TYPE_ITEM) {
                viewHolder_textView = (TextView) itemView.findViewById(R.id.rowText);
                viewHolder_imageView = (ImageView) itemView.findViewById(R.id.rowImage);
                holder_id = 1;
            } else {
                viewHolder_Name = (TextView) itemView.findViewById(R.id.profile_name);
                viewHolder_Email = (TextView) itemView.findViewById(R.id.profile_email);
                viewHolder_Profile = (ImageView) itemView.findViewById(R.id.profile_photo);
                holder_id = 0;
            }


        }


    }

    // Class Constructor with parameters that will be passed during instantiation
    MediaShareListAdapter(String Navigation_Titles[], int Navigation_Icons[], String Name, String Email, int Profile_Photo) {
        navigation_titles = Navigation_Titles;
        navigation_icons = Navigation_Icons;
        name = Name;
        profile = Profile_Photo;
        email = Email;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        ViewHolder bodyViewHolder;
        ViewHolder headerViewHolder;
        if (ViewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_drawer_rows, parent, false); //Inflating the layout
            bodyViewHolder = new ViewHolder(view, ViewType); //Creating ViewHolder and passing the object of type view
            return bodyViewHolder;
        } else if (ViewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_header, parent, false);
            headerViewHolder = new ViewHolder(view, ViewType);
            return headerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MediaShareListAdapter.ViewHolder holder, int position) {
        if (holder.holder_id == 1) {
            holder.viewHolder_textView.setText(navigation_titles[position - 1]);
            holder.viewHolder_imageView.setImageResource(navigation_icons[position - 1]);
        } else {
            holder.viewHolder_Profile.setImageResource(profile);
            holder.viewHolder_Name.setText(name);
            holder.viewHolder_Email.setText(email);
        }

    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return navigation_titles.length + 1;
    }

    // With the following method we check what type of view is being passed
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

