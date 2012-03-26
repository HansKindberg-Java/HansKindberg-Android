package se.hanskindberg.android.app;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;
import se.hanskindberg.lang.ArgumentNullException;

import java.lang.reflect.Method;

public class ActivityBinder implements IActivityBinder
{
	/* Fields - begin */

	public static final Class<? extends Activity> ACTIVITY_CLASS = Activity.class;
	private final Method m_attachMethod;
	private final Boolean m_attachMethodIsAccessible;
	private static ActivityBinder s_instance;

	/* Fields - end */

	/* Constructors - begin */

	protected ActivityBinder() throws NoSuchMethodException
	{
		this(ACTIVITY_CLASS);
	}

	// To make it easier to test
	protected ActivityBinder(Class<? extends Activity> activityClass) throws NoSuchMethodException
	{
		super();

		if (activityClass == null)
			throw new ArgumentNullException("activityClass");

		Method attachMethod = null;

		for (Method method : activityClass.getDeclaredMethods())
		{
			if (method.getName().equals("attach") && method.getParameterTypes().length == 12)
			{
				attachMethod = method;
				break;
			}
		}

		if (attachMethod == null)
			throw new NoSuchMethodException(String.format("The class \"%s\" does not have an \"attach\" method with 12 parameters.", activityClass.getName()));

		this.m_attachMethod = attachMethod;
		this.m_attachMethodIsAccessible = this.m_attachMethod.isAccessible();
	}

	/* Constructors - end */

	/* Methods - begin */

	public void attach(Activity activity, Context context, Object activityThread, Instrumentation instrumentation, IBinder token, Application application, Intent intent, ActivityInfo activityInfo, CharSequence title, Activity parentActivity, String id, Object lastNonConfigurationInstances, Configuration configuration)
	{
		if (activity == null)
			throw new ArgumentNullException("activity");

		Object parameterObjects[] = {context, activityThread, instrumentation, token, application, intent, activityInfo, title, parentActivity, id, lastNonConfigurationInstances, configuration};

		try
		{
			this.m_attachMethod.setAccessible(true);
			this.m_attachMethod.invoke(activity, parameterObjects);
		}
		catch (Exception exception)
		{
			throw new RuntimeException("The attach method could not be invoked.", exception);
		}
		finally
		{
			this.m_attachMethod.setAccessible(this.m_attachMethodIsAccessible);
		}
	}

	public static ActivityBinder getInstance() throws NoSuchMethodException
	{
		return getInstance(ACTIVITY_CLASS);
	}

	// To make it easier to test
	protected static ActivityBinder getInstance(Class<? extends Activity> activityClass) throws NoSuchMethodException
	{
		if (s_instance == null)
			s_instance = new ActivityBinder(activityClass);

		return s_instance;
	}

	public static void setInstance(ActivityBinder activityBinder)
	{
		if (activityBinder == null)
			throw new ArgumentNullException("activityBinder");

		s_instance = activityBinder;
	}

	/* Methods - end */
}