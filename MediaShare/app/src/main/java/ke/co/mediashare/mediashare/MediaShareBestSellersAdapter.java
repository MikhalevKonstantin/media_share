package ke.co.mediashare.mediashare;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by guidovanrossum on 14/07/15.
 */
public class MediaShareBestSellersAdapter extends RecyclerView.Adapter<MediaShareBestSellersAdapter.ViewHolder> {
	private ArrayList<Integer> bookThumbnails;
	private ArrayList<String> bookTitles;
	private ArrayList<String> bookAuthors;
	private ArrayList<String> bookYearsOfPublish;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView best_seller_book_thumbnail;
		TextView best_seller_book_title;
		TextView best_seller_book_author;
		TextView best_seller_book_year_of_production;
		CardView cardView;

		public ViewHolder(View view) {
			super(view);
			best_seller_book_thumbnail = (ImageView) view.findViewById(R.id.best_seller_book_cover_image);
			best_seller_book_title = (TextView) view.findViewById(R.id.best_seller_book_title);
			best_seller_book_author = (TextView) view.findViewById(R.id.best_seller_book_author);
			best_seller_book_year_of_production = (TextView) view.findViewById(R.id.best_seller_book_year_of_publish);
			cardView = (CardView) view.findViewById(R.id.best_seller_books_cardView);
		}

	}

	MediaShareBestSellersAdapter(ArrayList<Integer> BookThumbnails, ArrayList<String> BookTitles, ArrayList<String> BookAuthors, ArrayList<String> BookYearOfProduction) {
		bookThumbnails = BookThumbnails;
		bookTitles = BookTitles;
		bookAuthors = BookAuthors;
		bookYearsOfPublish = BookYearOfProduction;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows_best_seller_books, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MediaShareBestSellersAdapter.ViewHolder holder, int position) {
		holder.best_seller_book_thumbnail.setImageResource(bookThumbnails.get(position));
		holder.best_seller_book_title.setText(bookTitles.get(position));
		holder.best_seller_book_author.setText(bookAuthors.get(position));
		holder.best_seller_book_year_of_production.setText(bookYearsOfPublish.get(position));
	}

	@Override
	public int getItemCount() {

		return bookAuthors.size();
	}
}
