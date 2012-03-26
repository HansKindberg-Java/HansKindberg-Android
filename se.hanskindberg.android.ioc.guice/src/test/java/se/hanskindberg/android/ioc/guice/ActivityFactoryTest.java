package se.hanskindberg.android.ioc.guice;

import org.junit.Test;
import se.hanskindberg.lang.ArgumentNullException;

import static org.junit.Assert.assertEquals;

public class ActivityFactoryTest
{
	/* Methods - begin */

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheInjectorParameterIsNull() throws Exception
	{
		try
		{
			new ActivityFactory(null);
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("injector", exception.getParameterName());
			throw exception;
		}
	}

	/* Methods - end */
}
