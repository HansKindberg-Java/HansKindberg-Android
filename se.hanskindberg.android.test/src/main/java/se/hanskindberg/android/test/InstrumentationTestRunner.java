package se.hanskindberg.android.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;
import se.hanskindberg.android.app.IActivityBinder;
import se.hanskindberg.android.app.IActivityFactory;
import se.hanskindberg.lang.ArgumentNullException;

public class InstrumentationTestRunner extends android.test.InstrumentationTestRunner
{
	/* Fields - begin */

	private final IActivityBinder m_activityBinder;
	private final IActivityFactory m_activityFactory;

	/* Fields - end */

	/* Constructors - begin */

	public InstrumentationTestRunner(IActivityFactory activityFactory, IActivityBinder activityBinder)
	{
		super();

		if (activityFactory == null)
			throw new ArgumentNullException("activityFactory");

		if (activityBinder == null)
			throw new ArgumentNullException("activityBinder");

		this.m_activityBinder = activityBinder;
		this.m_activityFactory = activityFactory;
	}

	/* Constructors - end */

	/* Methods - begin */

	public IActivityBinder getActivityBinder()
	{
		return this.m_activityBinder;
	}

	public IActivityFactory getActivityFactory()
	{
		return this.m_activityFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Activity newActivity(ClassLoader classLoader, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		return this.m_activityFactory.create((Class<? extends Activity>) classLoader.loadClass(className));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Activity newActivity(Class<?> activityClass, Context context, IBinder token, android.app.Application application, Intent intent, ActivityInfo activityInfo, CharSequence title, Activity parentActivity, String id, Object lastNonConfigurationInstance) throws IllegalAccessException, InstantiationException
	{
		Activity activity = this.m_activityFactory.create((Class<? extends Activity>) activityClass);

		this.m_activityBinder.attach(activity, context, null, this, token, application, intent, activityInfo, title, parentActivity, id, lastNonConfigurationInstance, new Configuration());

		return activity;
	}

	/* Methods - end */
}