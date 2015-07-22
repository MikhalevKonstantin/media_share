package ke.co.mediashare.mediashare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class ReadLaterListActivity extends ActionBarActivity {
	ArrayList<Integer> bookThumbnails;
	ArrayList<String> bookTitles;
	ArrayList<String> bookAuthors;
	ArrayList<String> bookYearOfProduction;
	RecyclerView recyclerView;
	RecyclerView.Adapter reAdapter;
	RecyclerView.LayoutManager reLayoutManager;
	CharSequence[] bookOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_later_list);

		bookOptions = new CharSequence[]{"Open", "Buy", "Rent", "Remove from list"};

		Toolbar toolbar;

		toolbar = (Toolbar) findViewById(R.id.toolbar_read_later);
		toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
		setSupportActionBar(toolbar);

		setUpBooks();
		recyclerView = (RecyclerView) findViewById(R.id.read_list_recyclerView);
		recyclerView.setHasFixedSize(true);
		reAdapter = new MediaShareBooksAdapter(bookThumbnails, bookTitles, bookAuthors, bookYearOfProduction);
		recyclerView.setAdapter(reAdapter);
		reLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(reLayoutManager);

		// Adding Item Click Listener
		final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
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
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ReadLaterListActivity.this);
					alertBuilder.setItems(bookOptions, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

							// If the option selected is Open, then open the selected PDF file
							if (bookOptions[which] == "Open") {

								Intent intent = new Intent(ReadLaterListActivity.this, BookReaderActivity.class);
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
	}

	public void setUpBooks() {
		bookThumbnails = new ArrayList<>();
		bookThumbnails.add(R.drawable.book_building_websites_with_joomla);
		bookThumbnails.add(R.drawable.book_complete_reference);
		bookThumbnails.add(R.drawable.book_hardening_linux);
		bookThumbnails.add(R.drawable.book_zend_framework_in_action);
		bookThumbnails.add(R.drawable.novel_black_hand_gang);
		bookThumbnails.add(R.drawable.novel_cool_hand_look);
		bookThumbnails.add(R.drawable.novel_fight_less_love_more);
		bookThumbnails.add(R.drawable.novel_the_god_father);
		bookThumbnails.add(R.drawable.novel_lord_john);

		bookTitles = new ArrayList<>();
		bookTitles.add("Building Websites With Joomla");
		bookTitles.add("The Complete C++ Reference");
		bookTitles.add("Hardening Linux");
		bookTitles.add("Zend Framework In Action");
		bookTitles.add("Black Hand Gang");
		bookTitles.add("Cool Hand Look");
		bookTitles.add("Fight Less Love More");
		bookTitles.add("The God Father");
		bookTitles.add("Lord John");

		bookAuthors = new ArrayList<>();
		bookAuthors.add("Hagen Graf");
		bookAuthors.add("Herbert Schildt");
		bookAuthors.add("James Tumbull");
		bookAuthors.add("Rob Allen");
		bookAuthors.add("Pat Kellemer");
		bookAuthors.add("Donn Pearce");
		bookAuthors.add("Laurie Pann");
		bookAuthors.add("Mario Puzo");
		bookAuthors.add("Diana Gabaldon");

		bookYearOfProduction = new ArrayList<>();
		bookYearOfProduction.add("(2006)");
		bookYearOfProduction.add("(2009)");
		bookYearOfProduction.add("(2005)");
		bookYearOfProduction.add("(2007)");
		bookYearOfProduction.add("(2007)");
		bookYearOfProduction.add("(2007)");
		bookYearOfProduction.add("(2007)");
		bookYearOfProduction.add("(2008)");
		bookYearOfProduction.add("(2008)");
	}
}


