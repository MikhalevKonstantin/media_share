package ke.co.mediashare.mediashare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BestSellersActivity extends Fragment {
	ArrayList<Integer> bookThumbnails;
	ArrayList<String> bookTitles;
	ArrayList<String> bookAuthors;
	ArrayList<String> bookYearOfProduction;
	RecyclerView recyclerView;
	RecyclerView.Adapter reAdapter;
	LinearLayoutManager reLayoutManager;
	CharSequence[] bookOptions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_best_sellers, container, false);
		bookOptions = new CharSequence[]{"Open", "Buy", "Rent","Add to library", "Read later"};
		setUpBooks();
		recyclerView = (RecyclerView) view.findViewById(R.id.best_seller_books_recyclerView);
		recyclerView.setHasFixedSize(true);
		reAdapter = new MediaShareBestSellersAdapter(bookThumbnails, bookTitles, bookAuthors, bookYearOfProduction);
		recyclerView.setAdapter(reAdapter);
		reLayoutManager = new LinearLayoutManager(view.getContext());
		reLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(reLayoutManager);

		// Adding Item Click Listener
		final GestureDetector gestureDetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onSingleTapUp(MotionEvent motionEvent) {

				return true;
			}

		});

		recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
			@Override
			public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
				View child = rv.findChildViewUnder(e.getX(), e.getY());

				if (child != null && gestureDetector.onTouchEvent(e)) {
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
					alertBuilder.setItems(bookOptions, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

							// If the option selected is Open, then open the selected PDF file
							if (bookOptions[which] == "Open") {

								Intent intent = new Intent(getActivity(), BookReaderActivity.class);
								startActivity(intent);
							}

						}
					});

					AlertDialog alertDialog = alertBuilder.create();
					alertDialog.show();
				}
				return false;
			}

			@Override
			public void onTouchEvent(RecyclerView rv, MotionEvent e) {

			}

			@Override
			public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

			}
		});
		return view;

	}

	public void setUpBooks() {
		bookThumbnails = new ArrayList<>();
		bookThumbnails.add(R.drawable.book_building_websites_with_joomla);
		bookThumbnails.add(R.drawable.book_complete_reference);
		bookThumbnails.add(R.drawable.book_hardening_linux);
		bookThumbnails.add(R.drawable.book_zend_framework_in_action);

		bookTitles = new ArrayList<>();
		bookTitles.add("Building Websites With Joomla");
		bookTitles.add("The Complete C++ Reference");
		bookTitles.add("Hardening Linux");
		bookTitles.add("Zend Framework In Action");

		bookAuthors = new ArrayList<>();
		bookAuthors.add("Hagen Graf");
		bookAuthors.add("Herbert Schildt");
		bookAuthors.add("James Tumbull");
		bookAuthors.add("Rob Allen");

		bookYearOfProduction = new ArrayList<>();
		bookYearOfProduction.add("(2006)");
		bookYearOfProduction.add("(2009)");
		bookYearOfProduction.add("(2005)");
		bookYearOfProduction.add("(2007)");
	}
}
