package se.hanskindberg.android.app;

import android.app.Activity;

public interface IActivityFactory
{
	/* Methods - begin */

	Activity create(Class<? extends Activity> activityClass) throws InstantiationException;

	/* Methods - end */
}