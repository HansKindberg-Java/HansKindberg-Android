package se.hanskindberg.android.app.mocks;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;

public class TestActivityWithAttachMethodWithWrongParameterCount extends Activity
{
	/* Methods - begin */

	// The last parameter of type "android.content.res.Configuration" is removed.
	final void attach(Context context, Object activityThread, Instrumentation instrumentation, IBinder token, Application application, Intent intent, ActivityInfo activityInfo, CharSequence title, Activity parentActivity, String id, Object lastNonConfigurationInstances)
	{

	}

	/* Methods - end */
}