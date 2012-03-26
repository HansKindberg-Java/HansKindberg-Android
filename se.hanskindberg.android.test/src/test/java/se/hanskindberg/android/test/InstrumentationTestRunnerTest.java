package se.hanskindberg.android.test;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import se.hanskindberg.android.app.IActivityBinder;
import se.hanskindberg.android.app.IActivityFactory;
import se.hanskindberg.android.test.testrunners.TestRunner;
import se.hanskindberg.lang.ArgumentNullException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(TestRunner.class)
public class InstrumentationTestRunnerTest
{
	/* Methods - begin */

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheActivityBinderParameterIsNull()
	{
		try
		{
			new InstrumentationTestRunner(mock(IActivityFactory.class), null);
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("activityBinder", exception.getParameterName());
			throw exception;
		}
	}

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheActivityFactoryParameterIsNull()
	{
		try
		{
			new InstrumentationTestRunner(null, mock(IActivityBinder.class));
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("activityFactory", exception.getParameterName());
			throw exception;
		}
	}

	private static InstrumentationTestRunner createInstrumentationTestRunner()
	{
		return new InstrumentationTestRunner(mock(IActivityFactory.class), mock(IActivityBinder.class));
	}

	@Test
	public void getActivityBinderShouldReturnTheActivityBinderSetInTheConstructor()
	{
		IActivityBinder activityBinder = mock(IActivityBinder.class);
		InstrumentationTestRunner instrumentationTestRunner = new InstrumentationTestRunner(mock(IActivityFactory.class), activityBinder);
		assertEquals(activityBinder, instrumentationTestRunner.getActivityBinder());
	}

	@Test
	public void getActivityFactoryShouldReturnTheActivityFactorySetInTheConstructor()
	{
		IActivityFactory activityFactory = mock(IActivityFactory.class);
		InstrumentationTestRunner instrumentationTestRunner = new InstrumentationTestRunner(activityFactory, mock(IActivityBinder.class));
		assertEquals(activityFactory, instrumentationTestRunner.getActivityFactory());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void newActivityFirstOverloadShouldCallActivityFactoryCreate() throws Exception
	{
		InstrumentationTestRunner instrumentationTestRunner = createInstrumentationTestRunner();
		verify(instrumentationTestRunner.getActivityFactory(), never()).create(any(Activity.class.getClass()));
		instrumentationTestRunner.newActivity(mock(ClassLoader.class), "Test", mock(Intent.class));
		verify(instrumentationTestRunner.getActivityFactory()).create(any(Activity.class.getClass()));
	}

	@Test
	public void newActivitySecondOverloadShouldCallActivityBinderAttach() throws Exception
	{
		InstrumentationTestRunner instrumentationTestRunner = createInstrumentationTestRunner();
		verify(instrumentationTestRunner.getActivityBinder(), never()).attach(any(Activity.class), any(Context.class), anyObject(), any(android.app.Instrumentation.class), any(IBinder.class), any(Application.class), any(Intent.class), any(ActivityInfo.class), any(CharSequence.class), any(Activity.class), anyString(), anyObject(), any(Configuration.class));
		instrumentationTestRunner.newActivity(mock(Activity.class).getClass(), mock(Context.class), mock(IBinder.class), mock(Application.class), mock(Intent.class), mock(ActivityInfo.class), mock(CharSequence.class), mock(Activity.class), "Test", new Object());
		verify(instrumentationTestRunner.getActivityBinder()).attach(any(Activity.class), any(Context.class), anyObject(), any(android.app.Instrumentation.class), any(IBinder.class), any(Application.class), any(Intent.class), any(ActivityInfo.class), any(CharSequence.class), any(Activity.class), anyString(), anyObject(), any(Configuration.class));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void newActivitySecondOverloadShouldCallActivityFactoryCreate() throws Exception
	{
		InstrumentationTestRunner instrumentationTestRunner = createInstrumentationTestRunner();
		verify(instrumentationTestRunner.getActivityFactory(), never()).create(any(Activity.class.getClass()));
		instrumentationTestRunner.newActivity(mock(Activity.class).getClass(), mock(Context.class), mock(IBinder.class), mock(Application.class), mock(Intent.class), mock(ActivityInfo.class), mock(CharSequence.class), mock(Activity.class), "Test", new Object());
		verify(instrumentationTestRunner.getActivityFactory()).create(any(Activity.class.getClass()));
	}

	/* Methods - end */
}