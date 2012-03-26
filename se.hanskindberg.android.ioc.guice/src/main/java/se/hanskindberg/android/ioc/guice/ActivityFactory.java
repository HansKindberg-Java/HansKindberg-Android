package se.hanskindberg.android.ioc.guice;

import android.app.Activity;
import com.google.inject.Injector;
import se.hanskindberg.android.app.IActivityFactory;
import se.hanskindberg.lang.ArgumentNullException;
import se.hanskindberg.lang.extensions.ClassExtension;

import java.lang.reflect.Constructor;

public class ActivityFactory implements IActivityFactory
{
	/* Fields - begin */

	private final Injector m_injector;

	/* Fields - end */

	/* Constructors - begin */

	public ActivityFactory(Injector injector)
	{
		if (injector == null)
			throw new ArgumentNullException("injector");

		this.m_injector = injector;
	}

	/* Constructors - end */

	/* Methods - begin */

	@Override
	public Activity create(Class<? extends Activity> activityClass) throws InstantiationException
	{
		Activity activity = null;

		Constructor constructor = ClassExtension.getConstructorWithMostParameters(activityClass);

		if (constructor == null)
			throw new InstantiationException(String.format("The class \"%s\" does not have a public consructor.", activityClass.getName()));

		Class<?>[] parameterTypes = constructor.getParameterTypes();
		Object[] parameterObjects = new Object[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++)
		{
			parameterObjects[i] = this.m_injector.getInstance(parameterTypes[i]);
		}

		try
		{
			activity = (Activity) constructor.newInstance(parameterObjects);
		}
		catch (Exception exception)
		{
			this.throwInstantiationException(String.format("The class \"%s\" could not be instantiated.", activityClass.getName()), exception);
		}

		return activity;
	}

	protected void throwInstantiationException(String message, Throwable cause) throws InstantiationException
	{
		InstantiationException instantiationException = new InstantiationException(message);
		instantiationException.initCause(cause);
		throw instantiationException;
	}

	/* Methods - end */
}
