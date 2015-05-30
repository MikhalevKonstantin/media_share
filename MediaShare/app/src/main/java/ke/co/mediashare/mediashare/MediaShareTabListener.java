package ke.co.mediashare.mediashare;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.util.Log;

/**
 * Created by guidovanrossum on 5/28/15.
 */
public class MediaShareTabListener<T extends Fragment> implements ActionBar.TabListener{

    private Fragment fragment;
    private static final String TAG = "media_share_tabs";

    public MediaShareTabListener(Fragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
        Log.i(TAG, "Tab " + tab.getText() + " ReSelected");
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        fragmentTransaction.replace(R.id.container, fragment, null);
        Log.i(TAG, "Tab " + tab.getText() + " selected");

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
        Log.i(TAG, "Tab " + tab.getText() + " UnSelected");
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
        }
    }
}
