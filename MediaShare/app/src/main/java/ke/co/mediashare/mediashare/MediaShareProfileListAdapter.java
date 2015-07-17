package ke.co.mediashare.mediashare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by guidovanrossum on 13/07/15.
 */
public class MediaShareProfileListAdapter extends RecyclerView.Adapter<MediaShareProfileListAdapter.ViewHolder> {
	private ArrayList<Integer> listItemIcons;
	private ArrayList<String> listItemText;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView profile_list_icon;
		TextView profile_list_text;

		public ViewHolder(View view) {
			super(view);
			profile_list_icon = (ImageView) view.findViewById(R.id.my_profile_list_icon);
			profile_list_text = (TextView) view.findViewById(R.id.my_profile_list_text);
		}
	}

	MediaShareProfileListAdapter(ArrayList<Integer> ListItemIcons, ArrayList<String> ListItemText) {
		listItemIcons = ListItemIcons;
		listItemText = ListItemText;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows_profile_items, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MediaShareProfileListAdapter.ViewHolder holder, int position) {
		holder.profile_list_icon.setImageResource(listItemIcons.get(position));
		holder.profile_list_text.setText(listItemText.get(position));
	}

	@Override
	public int getItemCount() {

		return listItemIcons.size();
	}
}
