package ke.co.mediashare.mediashare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by guidovanrossum on 15/07/15.
 */
public class MediaShareBooksAdapter extends RecyclerView.Adapter<MediaShareBooksAdapter.ViewHolder> {

	private ArrayList<Integer> bookThumbnails;
	private ArrayList<String> bookTitles;
	private ArrayList<String> bookAuthors;
	private ArrayList<String> bookYearsOfPublish;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView read_later_book_thumbnail;
		TextView read_later_book_title;
		TextView read_later_book_author;
		TextView read_later_book_year_of_production;

		public ViewHolder(View view) {
			super(view);
			read_later_book_thumbnail = (ImageView) view.findViewById(R.id.read_later_book_cover_image);
			read_later_book_title = (TextView) view.findViewById(R.id.read_later_book_title);
			read_later_book_author = (TextView) view.findViewById(R.id.read_later_book_author);
			read_later_book_year_of_production = (TextView) view.findViewById(R.id.read_later_book_year_of_publish);
		}

	}

	MediaShareBooksAdapter(ArrayList<Integer> BookThumbnails, ArrayList<String> BookTitles, ArrayList<String> BookAuthors, ArrayList<String> BookYearOfProduction) {
		bookThumbnails = BookThumbnails;
		bookTitles = BookTitles;
		bookAuthors = BookAuthors;
		bookYearsOfPublish = BookYearOfProduction;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows_read_later_list, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MediaShareBooksAdapter.ViewHolder holder, int position) {
		holder.read_later_book_thumbnail.setImageResource(bookThumbnails.get(position));
		holder.read_later_book_title.setText(bookTitles.get(position));
		holder.read_later_book_author.setText(bookAuthors.get(position));
		holder.read_later_book_year_of_production.setText(bookYearsOfPublish.get(position));
	}

	@Override
	public int getItemCount() {

		return bookAuthors.size();
	}
}
