package se.hanskindberg.android.app;

import android.app.Activity;

public class DefaultActivityFactory implements IActivityFactory
{
	/* Methods - begin */

	public Activity create(Class<? extends Activity> activityClass) throws InstantiationException
	{
		try
		{
			return activityClass.newInstance();
		}
		catch (Exception exception)
		{
			InstantiationException instantiationException = new InstantiationException(String.format("Could note create an activity of type \"%s\".", activityClass.getName()));
			instantiationException.initCause(exception);
			throw instantiationException;
		}
	}

	/* Methods - end */
}