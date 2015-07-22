package ke.co.mediashare.mediashare;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
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

import java.io.File;
import java.util.ArrayList;

public class RecommendedBooksActivity extends Fragment {
	ArrayList<Integer> bookThumbnails;
	ArrayList<String> bookTitles;
	ArrayList<String> bookAuthors;
	ArrayList<String> bookYearOfProduction;
	RecyclerView recyclerView;
	RecyclerView.Adapter reAdapter;
	RecyclerView.LayoutManager reLayoutManager;
	CharSequence[] bookOptions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_recommended_books, container, false);
		bookOptions = new CharSequence[]{"Open with MS Reader", "Open with another application", "Buy", "Rent", "Add to library", "Read later"};
		setUpBooks();
		recyclerView = (RecyclerView) view.findViewById(R.id.recommended_books_recyclerView);
		recyclerView.setHasFixedSize(true);
		reAdapter = new MediaShareRecommendedBooksAdapter(bookThumbnails, bookTitles, bookAuthors, bookYearOfProduction);
		recyclerView.setAdapter(reAdapter);
		reLayoutManager = new LinearLayoutManager(view.getContext());
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
							if (bookOptions[which] == "Open with MS Reader") {

								Intent intent = new Intent(getActivity(), BookReaderActivity.class);
								startActivity(intent);
							} else if (bookOptions[which] == "Open with another application") {
								readPDF();
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
		bookThumbnails.add(R.drawable.novel_the_god_father);
		bookThumbnails.add(R.drawable.novel_lord_john);
		bookThumbnails.add(R.drawable.novel_the_burning_hand);
		bookThumbnails.add(R.drawable.novel_high_hand);

		bookTitles = new ArrayList<>();
		bookTitles.add("Building Websites With Joomla");
		bookTitles.add("The Complete C++ Reference");
		bookTitles.add("Hardening Linux");
		bookTitles.add("Zend Framework In Action");
		bookTitles.add("The God Father");
		bookTitles.add("Lord John");
		bookTitles.add("The Burning Hand");
		bookTitles.add("High Hand");

		bookAuthors = new ArrayList<>();
		bookAuthors.add("Hagen Graf");
		bookAuthors.add("Herbert Schildt");
		bookAuthors.add("James Tumbull");
		bookAuthors.add("Rob Allen");
		bookAuthors.add("Mario Puzo");
		bookAuthors.add("Diana Gabaldon");
		bookAuthors.add("Jodi Meadows");
		bookAuthors.add("Curtis J. James");

		bookYearOfProduction = new ArrayList<>();
		bookYearOfProduction.add("(2006)");
		bookYearOfProduction.add("(2009)");
		bookYearOfProduction.add("(2005)");
		bookYearOfProduction.add("(2007)");
		bookYearOfProduction.add("(2008)");
		bookYearOfProduction.add("(2008)");
		bookYearOfProduction.add("(2008)");
		bookYearOfProduction.add("(2008)");
	}

	public void readPDF() {
		try {
			File pdfFile = new File("/storage/sdcard0/Applied_Cryptography.pdf");
			Uri path = Uri.fromFile(pdfFile);
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setDataAndType(path, "application/pdf");
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
