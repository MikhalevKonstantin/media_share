package ke.co.mediashare.mediashare;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class DiscoverBooksActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private MediaShareViewPageAdapter pagerAdapter;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private CharSequence[] tabTitles = {"New Releases", "Best Sellers", "Recommendations"};
    private int number_of_tabs = 3;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_books);
        // Instantiating Fragments
        NewReleasesActivity newReleasesActivity = new NewReleasesActivity();
        BestSellersActivity bestSellersActivity = new BestSellersActivity();
        RecommendedBooksActivity recommendedBooksActivity = new RecommendedBooksActivity();

        // Assigning instantiated Fragments to the Fragments array
        fragments = new Fragment[]{newReleasesActivity, bestSellersActivity, recommendedBooksActivity};

        toolbar = (Toolbar) findViewById(R.id.toolbar_discover_books);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        pagerAdapter = new MediaShareViewPageAdapter(getSupportFragmentManager(), tabTitles, number_of_tabs, fragments);
        viewPager = (ViewPager) findViewById(R.id.viewPager_discover_books);
        viewPager.setAdapter(pagerAdapter);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs_discover_books);
        slidingTabLayout.setDistributeEvenly(true);

        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.TabScrollColor);
            }
        });
        slidingTabLayout.setViewPager(viewPager);


    }


}
