package se.hanskindberg.android.app.mocks;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;

public class TestActivity extends Activity
{
	/* Fields - begin */

	private Boolean m_attachedIsInvoked;

	/* Fields - end */

	/* Constructors - begin */

	public TestActivity()
	{
		this.m_attachedIsInvoked = false;
	}

	/* Constructors - end */

	/* Methods - begin */

	final void attach(Context context, Object activityThread, Instrumentation instrumentation, IBinder token, Application application, Intent intent, ActivityInfo activityInfo, CharSequence title, Activity parentActivity, String id, Object lastNonConfigurationInstances, Configuration config)
	{
		this.m_attachedIsInvoked = true;
	}

	public Boolean getAttachIsInvoked()
	{
		return this.m_attachedIsInvoked;
	}

	/* Methods - end */
}
