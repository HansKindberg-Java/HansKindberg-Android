package se.hanskindberg.android.app;

import android.app.Activity;
import se.hanskindberg.lang.ArgumentNullException;

import java.lang.reflect.InvocationTargetException;

public class ActivityBuilder
{
	/* Fields - begin */

	private IActivityBinder m_activityBinder;
	private IActivityThread m_activityThread;
	private IActivityFactory m_defaultFactory;
	private IActivityFactory m_factory;
	private static ActivityBuilder s_instance;

	/* Fields - end */

	/* Constructors - begin */

	protected ActivityBuilder(IActivityThread activityThread, IActivityFactory defaultFactory, IActivityBinder activityBinder)
	{
		if (activityThread == null)
			throw new ArgumentNullException("activityThread");

		if (defaultFactory == null)
			throw new ArgumentNullException("defaultFactory");

		if (activityBinder == null)
			throw new ArgumentNullException("activityBinder");

		this.m_activityBinder = activityBinder;
		this.m_activityThread = activityThread;
		this.m_defaultFactory = defaultFactory;
	}

	/* Constructors - end */

	/* Methods - begin */

	public IActivityFactory getFactory() throws IllegalAccessException
	{
		if (this.m_factory == null)
			this.setFactory(this.m_defaultFactory);

		return this.m_factory;
	}

	public static ActivityBuilder getInstance() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException
	{
		return getInstance(ActivityThreadWrapper.ACTIVITY_THREAD_CLASS_NAME, ActivityBinder.ACTIVITY_CLASS);
	}

	// To make it easier to test
	protected static ActivityBuilder getInstance(String activityThreadClassName, Class<? extends Activity> activityClass) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException
	{
		if (s_instance == null)
			s_instance = new ActivityBuilder(ActivityThreadWrapper.getInstance(activityThreadClassName), new DefaultActivityFactory(), ActivityBinder.getInstance(activityClass));

		return s_instance;
	}

	public void setFactory(IActivityFactory factory) throws IllegalAccessException
	{
		if (factory == null)
			throw new ArgumentNullException("factory");

		if (!factory.equals(this.m_factory))
		{
			this.m_factory = factory;
			this.m_activityThread.setInstrumentation(new Instrumentation(this.m_activityThread.getInstrumentation(), factory, this.m_activityBinder));
		}
	}

	public static void setInstance(ActivityBuilder activityBuilder)
	{
		if (activityBuilder == null)
			throw new ArgumentNullException("activityBuilder");

		if (!activityBuilder.equals(s_instance))
			s_instance = activityBuilder;
	}

	/* Methods - end */
}