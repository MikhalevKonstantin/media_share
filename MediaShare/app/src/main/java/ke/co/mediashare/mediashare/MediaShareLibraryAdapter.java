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
public class MediaShareLibraryAdapter extends RecyclerView.Adapter<MediaShareLibraryAdapter.ViewHolder> {

	private ArrayList<Integer> bookThumbnails;
	private ArrayList<String> bookTitles;
	private ArrayList<String> bookAuthors;
	private ArrayList<String> bookYearsOfPublish;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView my_library_book_thumbnail;
		TextView my_library_book_title;
		TextView my_library_book_author;
		TextView my_library_book_year_of_production;

		public ViewHolder(View view) {
			super(view);
			my_library_book_thumbnail = (ImageView) view.findViewById(R.id.my_library_book_cover_image);
			my_library_book_title = (TextView) view.findViewById(R.id.my_library_book_title);
			my_library_book_author = (TextView) view.findViewById(R.id.my_library_book_author);
			my_library_book_year_of_production = (TextView) view.findViewById(R.id.my_library_book_year_of_publish);
		}

	}

	MediaShareLibraryAdapter(ArrayList<Integer> BookThumbnails, ArrayList<String> BookTitles, ArrayList<String> BookAuthors, ArrayList<String> BookYearOfProduction) {
		bookThumbnails = BookThumbnails;
		bookTitles = BookTitles;
		bookAuthors = BookAuthors;
		bookYearsOfPublish = BookYearOfProduction;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows_my_library, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MediaShareLibraryAdapter.ViewHolder holder, int position) {
		holder.my_library_book_thumbnail.setImageResource(bookThumbnails.get(position));
		holder.my_library_book_title.setText(bookTitles.get(position));
		holder.my_library_book_author.setText(bookAuthors.get(position));
		holder.my_library_book_year_of_production.setText(bookYearsOfPublish.get(position));
	}

	@Override
	public int getItemCount() {

		return bookAuthors.size();
	}
}
