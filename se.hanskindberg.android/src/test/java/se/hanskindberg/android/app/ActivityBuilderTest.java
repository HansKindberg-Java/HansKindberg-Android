package se.hanskindberg.android.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import se.hanskindberg.android.app.mocks.TestActivity;
import se.hanskindberg.android.app.mocks.TestActivityThread;
import se.hanskindberg.android.testrunners.TestRunner;
import se.hanskindberg.lang.ArgumentNullException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(TestRunner.class)
public class ActivityBuilderTest
{
	/* Methods - begin */

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheActivityBinderParameterIsNull()
	{
		try
		{
			new ActivityBuilder(mock(IActivityThread.class), mock(IActivityFactory.class), null);
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("activityBinder", exception.getParameterName());
			throw exception;
		}
	}

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheActivityThreadParameterIsNull()
	{
		try
		{
			new ActivityBuilder(null, mock(IActivityFactory.class), mock(IActivityBinder.class));
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("activityThread", exception.getParameterName());
			throw exception;
		}
	}

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheDefaultFactoryParameterIsNull()
	{
		try
		{
			new ActivityBuilder(mock(IActivityThread.class), null, mock(IActivityBinder.class));
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("defaultFactory", exception.getParameterName());
			throw exception;
		}
	}

	@Test
	public void getFactoryShouldReturnTheDefaultFactoryIfNotTheFactoryIsExplicitlySet() throws Exception
	{
		IActivityThread activityThread = mock(IActivityThread.class);
		when(activityThread.getInstrumentation()).thenReturn(mock(android.app.Instrumentation.class));
		IActivityFactory activityFactory = mock(IActivityFactory.class);
		assertEquals(activityFactory, new ActivityBuilder(activityThread, activityFactory, mock(IActivityBinder.class)).getFactory());
	}

	@Test
	public void getFactoryShouldReturnTheFactorySet() throws Exception
	{
		IActivityThread activityThread = mock(IActivityThread.class);
		when(activityThread.getInstrumentation()).thenReturn(mock(android.app.Instrumentation.class));
		ActivityBuilder activityBuilder = new ActivityBuilder(activityThread, mock(IActivityFactory.class), mock(IActivityBinder.class));
		IActivityFactory activityFactory = mock(IActivityFactory.class);
		activityBuilder.setFactory(activityFactory);
		assertEquals(activityFactory, activityBuilder.getFactory());
	}

	@Test
	public void getInstanceShouldReturnAnActivityBuilderWithADefaultFactoryIfNotTheInstanceIsExplicitlySet() throws Exception
	{
		TestActivityThread activityThread = new TestActivityThread();
		activityThread.setInstrumentation(mock(android.app.Instrumentation.class));
		TestActivityThread.setCurrentActivityThread(activityThread);
		assertTrue(ActivityBuilder.getInstance("se.hanskindberg.android.app.mocks.TestActivityThread", TestActivity.class).getFactory() instanceof DefaultActivityFactory);
		TestActivityThread.setCurrentActivityThread(null);
	}

	@Test
	public void getInstanceShouldReturnTheInstanceSet() throws Exception
	{
		ActivityBuilder activityBuilder = new ActivityBuilder(mock(IActivityThread.class), mock(IActivityFactory.class), mock(IActivityBinder.class));
		ActivityBuilder.setInstance(activityBuilder);
		assertEquals(activityBuilder, ActivityBuilder.getInstance());
	}

	@Test(expected = ArgumentNullException.class)
	public void setFactoryShouldThrowAnArgumentNullExceptionIfTheFactoryParameterIsNull() throws Exception
	{
		new ActivityBuilder(mock(IActivityThread.class), mock(IActivityFactory.class), mock(IActivityBinder.class)).setFactory(null);
	}

	@Test
	public void setFactoryTest()
	{
		IActivityFactory activityFactory = mock(IActivityFactory.class);
		assertTrue(!activityFactory.equals(null));
	}

	@Test(expected = ArgumentNullException.class)
	public void setInstanceShouldThrowAnArgumentNullExceptionIfTheActivityBuilderParameterIsNull()
	{
		ActivityBuilder.setInstance(null);
	}

	@Test
	public void setInstanceTest()
	{
		ActivityBuilder activityBuilder = new ActivityBuilder(mock(IActivityThread.class), mock(IActivityFactory.class), mock(IActivityBinder.class));
		assertTrue(!activityBuilder.equals(null));
	}

	/* Methods - end */
}
