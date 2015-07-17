package ke.co.mediashare.mediashare;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by guidovanrossum on 16/07/15.
 */
public class NavigationDrawerTextView extends TextView {

	public NavigationDrawerTextView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/RobotoMedium.ttf"));
	}
}
