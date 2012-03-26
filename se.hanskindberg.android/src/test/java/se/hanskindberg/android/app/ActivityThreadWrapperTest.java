package se.hanskindberg.android.app;

import org.junit.Test;
import se.hanskindberg.android.app.mocks.TestActivityThread;
import se.hanskindberg.android.app.mocks.TestActivityThreadWithInvalidClassName;
import se.hanskindberg.lang.ArgumentNullException;

import static org.junit.Assert.*;

public class ActivityThreadWrapperTest
{
	/* Methods - begin */

	@Test
	public void activityThreadWrapperImplementsIActivityThread() throws Exception
	{
		ActivityThreadWrapper activityThreadWrapper = new ActivityThreadWrapper("se.hanskindberg.android.app.mocks.TestActivityThread", new TestActivityThread());
		assertTrue(activityThreadWrapper instanceof IActivityThread);
	}

	@Test
	public void constructorShouldThrowACorrectErrorMessageIfTheParameterClassNameIsNotAndroidAppActivityThread()
	{
		try
		{
			new ActivityThreadWrapper(new Object());
			fail();
		}
		catch (Exception exception)
		{
			assertEquals("The parameter \"activityThread\" must be of type \"android.app.ActivityThread\".", exception.getMessage());
		}
	}

	@Test(expected = NoSuchFieldException.class)
	public void constructorShouldThrowANoSuchFieldExceptionIfTheActivityThreadParameterDoNotContainAFieldNamedNInstrumentation() throws Exception
	{
		new ActivityThreadWrapper("se.hanskindberg.android.app.mocks.TestActivityThreadWithInvalidClassName", new TestActivityThreadWithInvalidClassName());
	}

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheActivityThreadParameterIsNull() throws Exception
	{
		try
		{
			new ActivityThreadWrapper(null);
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("activityThread", exception.getParameterName());
			throw exception;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowAnIllegalArgumentExceptionIfTheClassNameParameterIsNotAndroidAppActivityThread() throws Exception
	{
		new ActivityThreadWrapper(new Object());
	}

	@Test
	public void constructorTest() throws Exception
	{
		assertNotNull(new ActivityThreadWrapper("se.hanskindberg.android.app.mocks.TestActivityThread", new TestActivityThread()));
	}

	@Test
	public void getInstanceShouldReturnTheCurrentActivityThreadFromTheWrappedActivityThread() throws Exception
	{
		TestActivityThread activityThread = new TestActivityThread();
		TestActivityThread.setCurrentActivityThread(activityThread);
		assertEquals(activityThread, ActivityThreadWrapper.getInstance("se.hanskindberg.android.app.mocks.TestActivityThread").getActivityThread());
	}

	@Test(expected = NoSuchMethodException.class)
	public void getInstanceShouldThrowANoSuchMethodExceptionIfTheActivtityThreadClassDoNotContainAStaticMethodNamedCurrentActivityThread() throws Exception
	{
		ActivityThreadWrapper.getInstance("se.hanskindberg.android.app.mocks.TestActivityThreadWithoutCurrentActivityThreadMethod");
	}

	/* Methods - end */
}