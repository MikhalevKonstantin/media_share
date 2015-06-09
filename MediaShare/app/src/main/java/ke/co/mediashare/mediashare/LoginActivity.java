package ke.co.mediashare.mediashare;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;


public class LoginActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private MediaShareViewPageAdapter pagerAdapter;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private CharSequence[] tabTitles = {"Login", "Create Account"};
    private int number_of_tabs = 2;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Instantiating Fragments
        SignInActivity signInActivity = new SignInActivity();
        SignUpActivity signUpActivity = new SignUpActivity();

        // Assigning Fragments to the Fragments array
        fragments = new Fragment[]{signInActivity, signUpActivity};

        toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);

        pagerAdapter = new MediaShareViewPageAdapter(getSupportFragmentManager(), tabTitles, number_of_tabs, fragments);
        viewPager = (ViewPager) findViewById(R.id.viewPager_login);
        viewPager.setAdapter(pagerAdapter);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs_login);
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
