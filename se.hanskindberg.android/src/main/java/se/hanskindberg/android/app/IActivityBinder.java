package se.hanskindberg.android.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;

public interface IActivityBinder
{
	/* Methods - begin */

	void attach(Activity activity, Context context, Object activityThread, android.app.Instrumentation instrumentation, IBinder token, Application application, Intent intent, ActivityInfo activityInfo, CharSequence title, Activity parentActivity, String id, Object lastNonConfigurationInstances, Configuration configuration);

	/* Methods - end */
}