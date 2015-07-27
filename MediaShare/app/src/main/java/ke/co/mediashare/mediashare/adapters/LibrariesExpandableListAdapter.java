package ke.co.mediashare.mediashare.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import ke.co.mediashare.mediashare.R;

/**
 * Created by guidovanrossum on 27/07/15.
 */
public class LibrariesExpandableListAdapter extends BaseExpandableListAdapter {

	private Activity context;
	private Map<String, List<String>> libraryCollection;
	private List<String> libraryItems;

	public LibrariesExpandableListAdapter(Activity context, List<String> libraryBooks, Map<String, List<String>> libraryTitles) {
		this.context = context;
		this.libraryCollection = libraryTitles;
		this.libraryItems = libraryBooks;

	}

	public Object getChild(int groupPosition, int childPosition) {

		return libraryCollection.get(libraryItems.get(groupPosition)).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {

		return childPosition;
	}

	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		final String libraryItem = (String) getChild(groupPosition, childPosition);
		LayoutInflater layoutInflater = context.getLayoutInflater();
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.rows_my_library_list_child_items, null);
		}

		TextView item = (TextView) convertView.findViewById(R.id.label_library_items);

		item.setText(libraryItem);
		return convertView;
	}

	public int getChildrenCount(int groupPosition) {

		return libraryCollection.get(libraryItems.get(groupPosition)).size();
	}

	public Object getGroup(int groupPosition) {

		return libraryItems.get(groupPosition);
	}

	public int getGroupCount() {

		return libraryItems.size();
	}

	public long getGroupId(int groupPosition) {

		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		String libraryName = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.rows_my_library_list_headers, null);
		}
		TextView item = (TextView) convertView.findViewById(R.id.label_library_header);
		item.setText(libraryName);
		return convertView;
	}

	public boolean hasStableIds() {

		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {

		return true;
	}

}
