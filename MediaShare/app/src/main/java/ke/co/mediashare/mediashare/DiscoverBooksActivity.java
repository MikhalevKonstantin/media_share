package ke.co.mediashare.mediashare;

import java.util.Locale;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DiscoverBooksActivity extends ActionBarActivity{
    private static final String TAG ="media_share";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_books);
        setTabsUp(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState){

        // Save the index of the selected tab so that it is reselected when the orientation changes
        outState.putInt("tabIndex", getSupportActionBar().getSelectedNavigationIndex());
    }

    private void setTabsUp(Bundle savedInstanceState){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.action_bar_color)));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab best_sellers_tab = actionBar.newTab();
        ActionBar.Tab new_releases_tab = actionBar.newTab();
        ActionBar.Tab recommendations_tab = actionBar.newTab();

        BestSellersActivity bestSellersActivity = new BestSellersActivity();
        best_sellers_tab.setText("BEST SELLERS").setContentDescription("best_sellers_tab").setTabListener(new MediaShareTabListener<BestSellersActivity>(bestSellersActivity));

        NewReleasesActivity newReleasesActivity = new NewReleasesActivity();
        new_releases_tab.setText("NEW RELEASES").setContentDescription("new_releases_tab").setTabListener(new MediaShareTabListener<NewReleasesActivity>(newReleasesActivity));

        RecommendedBooksActivity recommendedBooksActivity = new RecommendedBooksActivity();
        recommendations_tab.setText("RECOMMENDATIONS").setContentDescription("recommended_books_tab").setTabListener(new MediaShareTabListener<RecommendedBooksActivity>(recommendedBooksActivity));

        actionBar.addTab(best_sellers_tab);
        actionBar.addTab(new_releases_tab);
        actionBar.addTab(recommendations_tab);

        if (savedInstanceState != null) {
            Log.i(TAG, "setting selected tab from saved bundle");
//            get the saved selected tab's index and set that tab as selected
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tabIndex", 0));
        }
    }


}
