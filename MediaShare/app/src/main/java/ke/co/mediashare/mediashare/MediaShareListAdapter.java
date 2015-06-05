package ke.co.mediashare.mediashare;

import android.app.Activity;
import android.content.Context;
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
public class MediaShareListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] listItems;
    private final Integer[] imageList;

    public MediaShareListAdapter(Activity context, Integer[] imageList, String[] listItems) {
        super(context, R.layout.navigation_drawer_rows, listItems);
        this.context = context;
        this.listItems = listItems;
        this.imageList = imageList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.navigation_drawer_rows, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.rowText);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.rowImage);
        txtTitle.setText(listItems[position]);

        imageView.setImageResource(imageList[position]);
        return rowView;
    }
}
