package se.hanskindberg.android.app.mocks;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;

public class TestActivityWithAttachMethodWithWrongParameters extends Activity
{
	/* Methods - begin */

	// The first parameter is changed from "android.content.Context" to "android.app.Activity".
	final void attach(Activity context, Object activityThread, Instrumentation instrumentation, IBinder token, Application application, Intent intent, ActivityInfo activityInfo, CharSequence title, Activity parentActivity, String id, Object lastNonConfigurationInstances, Configuration config)
	{

	}

	/* Methods - end */
}