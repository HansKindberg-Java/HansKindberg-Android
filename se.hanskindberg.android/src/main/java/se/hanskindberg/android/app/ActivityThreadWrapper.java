package se.hanskindberg.android.app;

import se.hanskindberg.lang.ArgumentNullException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ActivityThreadWrapper implements IActivityThread
{
	/* Fields - begin */

	public static final String ACTIVITY_THREAD_CLASS_NAME = "android.app.ActivityThread";
	private final Object m_activityThread;
	private final Field m_instrumentationField;
	private final boolean m_instrumentationFieldAccessible;

	/* Fields - end */

	/* Constructors - begin */

	protected ActivityThreadWrapper(Object activityThread) throws NoSuchFieldException
	{
		this(ACTIVITY_THREAD_CLASS_NAME, activityThread);
	}

	// To make it easier to test
	protected ActivityThreadWrapper(String activityThreadClassName, Object activityThread) throws NoSuchFieldException
	{
		super();

		if (activityThread == null)
			throw new ArgumentNullException("activityThread");

		if (!activityThread.getClass().getName().equals(activityThreadClassName))
			throw new IllegalArgumentException(String.format("The parameter \"%s\" must be of type \"%s\".", "activityThread", activityThreadClassName));

		this.m_activityThread = activityThread;
		this.m_instrumentationField = this.m_activityThread.getClass().getDeclaredField("mInstrumentation");
		this.m_instrumentationFieldAccessible = this.m_instrumentationField.isAccessible();
	}

	/* Constructors - end */

	/* Methods - begin */

	protected Object getActivityThread()
	{
		return this.m_activityThread;
	}

	public static ActivityThreadWrapper getInstance() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException
	{
		return getInstance(ACTIVITY_THREAD_CLASS_NAME);
	}

	// To make it easier to test
	protected static ActivityThreadWrapper getInstance(String activityThreadClassName) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException
	{
		return new ActivityThreadWrapper(activityThreadClassName, Class.forName(activityThreadClassName).getDeclaredMethod("currentActivityThread").invoke(null));
	}

	public android.app.Instrumentation getInstrumentation() throws IllegalAccessException
	{
		this.m_instrumentationField.setAccessible(true);
		android.app.Instrumentation instrumentation = (android.app.Instrumentation) this.m_instrumentationField.get(this.m_activityThread);
		this.m_instrumentationField.setAccessible(this.m_instrumentationFieldAccessible);
		return instrumentation;
	}

	public void setInstrumentation(android.app.Instrumentation instrumentation) throws IllegalAccessException
	{
		this.m_instrumentationField.setAccessible(true);
		this.m_instrumentationField.set(this.m_activityThread, instrumentation);
		this.m_instrumentationField.setAccessible(this.m_instrumentationFieldAccessible);
	}

	/* Methods - end */
}