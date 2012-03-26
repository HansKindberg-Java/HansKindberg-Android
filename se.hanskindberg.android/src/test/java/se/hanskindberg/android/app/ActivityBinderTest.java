package se.hanskindberg.android.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import se.hanskindberg.android.app.mocks.TestActivity;
import se.hanskindberg.android.app.mocks.TestActivityWithAttachMethodWithWrongParameterCount;
import se.hanskindberg.android.app.mocks.TestActivityWithAttachMethodWithWrongParameters;
import se.hanskindberg.android.app.mocks.TestActivityWithoutAttachMethod;
import se.hanskindberg.android.testrunners.TestRunner;
import se.hanskindberg.lang.ArgumentNullException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(TestRunner.class)
public class ActivityBinderTest
{
	/* Methods - begin */

	@Test
	public void activityBinderImplementsIActivityThread() throws Exception
	{
		ActivityBinder activityBinder = new ActivityBinder(TestActivity.class);
		assertTrue(activityBinder instanceof IActivityBinder);
	}

	@Test(expected = RuntimeException.class)
	public void attachShouldThrowARuntimeExceptionIfTheActivityClassDoNotHaveAnAttachMethodWithCorrectParameters() throws Exception
	{
		try
		{
			TestActivityWithAttachMethodWithWrongParameters activity = new TestActivityWithAttachMethodWithWrongParameters();
			ActivityBinder activityBinder = new ActivityBinder(TestActivityWithAttachMethodWithWrongParameters.class);
			activityBinder.attach(activity, mock(Context.class), new Object(), mock(android.app.Instrumentation.class), mock(IBinder.class), mock(Application.class), mock(Intent.class), mock(ActivityInfo.class), mock(CharSequence.class), mock(Activity.class), "", new Object(), mock(Configuration.class));
		}
		catch (RuntimeException exception)
		{
			assertTrue(exception.getCause() instanceof IllegalArgumentException);
			throw exception;
		}
	}

	@Test
	public void attachTest() throws Exception
	{
		TestActivity activity = new TestActivity();
		ActivityBinder activityBinder = new ActivityBinder(TestActivity.class);
		assertFalse(activity.getAttachIsInvoked());
		activityBinder.attach(activity, mock(Context.class), new Object(), mock(android.app.Instrumentation.class), mock(IBinder.class), mock(Application.class), mock(Intent.class), mock(ActivityInfo.class), mock(CharSequence.class), mock(Activity.class), "", new Object(), mock(Configuration.class));
		assertTrue(activity.getAttachIsInvoked());
	}

	@Test(expected = NoSuchMethodException.class)
	public void constructorShouldThrowANoSuchMethodExceptionIfTheActivityClassDoNotHaveAnAttachMethod() throws Exception
	{
		new ActivityBinder(TestActivityWithoutAttachMethod.class);
	}

	@Test(expected = NoSuchMethodException.class)
	public void constructorShouldThrowANoSuchMethodExceptionIfTheActivityClassDoNotHaveAnAttachMethodWithCorrectParameterCount() throws Exception
	{
		new ActivityBinder(TestActivityWithAttachMethodWithWrongParameterCount.class);
	}

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheActivityClassParameterIsNull() throws Exception
	{
		try
		{
			new ActivityBinder(null);
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("activityClass", exception.getParameterName());
			throw exception;
		}
	}

	@Test
	public void constructorTest() throws Exception
	{
		new ActivityBinder(TestActivity.class);
	}

	/* Methods - end */
}