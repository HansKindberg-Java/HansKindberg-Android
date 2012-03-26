package se.hanskindberg.android.app;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation.ActivityMonitor;
import android.app.Instrumentation.ActivityResult;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import se.hanskindberg.android.testrunners.TestRunner;
import se.hanskindberg.lang.ArgumentNullException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(TestRunner.class)
public class InstrumentationTest
{
	/* Methods - begin */

	@Test
	public void addMonitorFirstOverloadShouldCallDefaultInstrumentationAddMonitorFirstOverload()
	{
		Instrumentation instrumentation = createInstrumentation();
		verify(instrumentation.getDefaultInstrumentation(), never()).addMonitor(any(ActivityMonitor.class));
		instrumentation.addMonitor(mock(ActivityMonitor.class));
		verify(instrumentation.getDefaultInstrumentation()).addMonitor(any(ActivityMonitor.class));
	}

	@Test
	public void addMonitorSecondOverloadShouldCallDefaultInstrumentationAddMonitorSecondOverload()
	{
		Instrumentation instrumentation = createInstrumentation();
		verify(instrumentation.getDefaultInstrumentation(), never()).addMonitor(any(IntentFilter.class), any(ActivityResult.class), anyBoolean());
		instrumentation.addMonitor(mock(IntentFilter.class), new ActivityResult(0, null), true);
		verify(instrumentation.getDefaultInstrumentation()).addMonitor(any(IntentFilter.class), any(ActivityResult.class), anyBoolean());
	}

	@Test
	public void addMonitorThirdOverloadShouldCallDefaultInstrumentationAddMonitorThirdOverload()
	{
		Instrumentation instrumentation = createInstrumentation();
		verify(instrumentation.getDefaultInstrumentation(), never()).addMonitor(anyString(), any(ActivityResult.class), anyBoolean());
		instrumentation.addMonitor("", new ActivityResult(0, null), true);
		verify(instrumentation.getDefaultInstrumentation()).addMonitor(anyString(), any(ActivityResult.class), anyBoolean());
	}

	@Test
	public void callActivityOnCreateShouldCallDefaultInstrumentationCallActivityOnCreate()
	{
		Instrumentation instrumentation = createInstrumentation();
		verify(instrumentation.getDefaultInstrumentation(), never()).callActivityOnCreate(any(Activity.class), any(Bundle.class));
		instrumentation.callActivityOnCreate(mock(Activity.class), mock(Bundle.class));
		verify(instrumentation.getDefaultInstrumentation()).callActivityOnCreate(any(Activity.class), any(Bundle.class));
	}

	@Test
	public void callActivityOnDestroyShouldCallDefaultInstrumentationCallActivityOnDestroy()
	{
		Instrumentation instrumentation = createInstrumentation();
		verify(instrumentation.getDefaultInstrumentation(), never()).callActivityOnDestroy(any(Activity.class));
		instrumentation.callActivityOnDestroy(mock(Activity.class));
		verify(instrumentation.getDefaultInstrumentation()).callActivityOnDestroy(any(Activity.class));
	}

	@Test
	public void callActivityOnNewIntentShouldCallDefaultInstrumentationCallActivityOnNewIntent()
	{
		Instrumentation instrumentation = createInstrumentation();
		verify(instrumentation.getDefaultInstrumentation(), never()).callActivityOnNewIntent(any(Activity.class), any(Intent.class));
		instrumentation.callActivityOnNewIntent(mock(Activity.class), mock(Intent.class));
		verify(instrumentation.getDefaultInstrumentation()).callActivityOnNewIntent(any(Activity.class), any(Intent.class));
	}

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheActivityBinderParameterIsNull()
	{
		try
		{
			new Instrumentation(mock(android.app.Instrumentation.class), mock(IActivityFactory.class), null);
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
			new Instrumentation(mock(android.app.Instrumentation.class), null, mock(IActivityBinder.class));
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("activityFactory", exception.getParameterName());
			throw exception;
		}
	}

	@Test(expected = ArgumentNullException.class)
	public void constructorShouldThrowAnArgumentNullExceptionIfTheDefaultInstrumentationParameterIsNull()
	{
		try
		{
			new Instrumentation(null, mock(IActivityFactory.class), mock(IActivityBinder.class));
		}
		catch (ArgumentNullException exception)
		{
			assertEquals("defaultInstrumentation", exception.getParameterName());
			throw exception;
		}
	}

	private static Instrumentation createInstrumentation()
	{
		return createInstrumentation(mock(android.app.Instrumentation.class), mock(IActivityFactory.class), mock(IActivityBinder.class));
	}

	private static Instrumentation createInstrumentation(android.app.Instrumentation defaultInstrumentation, IActivityFactory activityFactory, IActivityBinder activityBinder)
	{
		return new Instrumentation(defaultInstrumentation, activityFactory, activityBinder);
	}

	@Test
	public void getActivityBinderShouldReturnTheActivityBinderSetInTheConstructor()
	{
		IActivityBinder activityBinder = mock(IActivityBinder.class);
		Instrumentation instrumentation = new Instrumentation(mock(android.app.Instrumentation.class), mock(IActivityFactory.class), activityBinder);
		assertEquals(activityBinder, instrumentation.getActivityBinder());
	}

	@Test
	public void getActivityFactoryShouldReturnTheActivityFactorySetInTheConstructor()
	{
		IActivityFactory activityFactory = mock(IActivityFactory.class);
		Instrumentation instrumentation = new Instrumentation(mock(android.app.Instrumentation.class), activityFactory, mock(IActivityBinder.class));
		assertEquals(activityFactory, instrumentation.getActivityFactory());
	}

	@Test
	public void getDefaultInstrumentationShouldReturnTheDefaultInstrumentationSetInTheConstructor()
	{
		android.app.Instrumentation defaultInstrumentation = mock(android.app.Instrumentation.class);
		Instrumentation instrumentation = new Instrumentation(defaultInstrumentation, mock(IActivityFactory.class), mock(IActivityBinder.class));
		assertEquals(defaultInstrumentation, instrumentation.getDefaultInstrumentation());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void newActivityFirstOverloadShouldCallActivityFactoryCreate() throws Exception
	{
		Instrumentation instrumentation = createInstrumentation();
		verify(instrumentation.getActivityFactory(), never()).create(any(Activity.class.getClass()));
		instrumentation.newActivity(mock(ClassLoader.class), "Test", mock(Intent.class));
		verify(instrumentation.getActivityFactory()).create(any(Activity.class.getClass()));
	}

	@Test
	public void newActivitySecondOverloadShouldCallActivityBinderAttach() throws Exception
	{
		Instrumentation instrumentation = createInstrumentation();
		verify(instrumentation.getActivityBinder(), never()).attach(any(Activity.class), any(Context.class), anyObject(), any(android.app.Instrumentation.class), any(IBinder.class), any(Application.class), any(Intent.class), any(ActivityInfo.class), any(CharSequence.class), any(Activity.class), anyString(), anyObject(), any(Configuration.class));
		instrumentation.newActivity(mock(Activity.class).getClass(), mock(Context.class), mock(IBinder.class), mock(Application.class), mock(Intent.class), mock(ActivityInfo.class), mock(CharSequence.class), mock(Activity.class), "Test", new Object());
		verify(instrumentation.getActivityBinder()).attach(any(Activity.class), any(Context.class), anyObject(), any(android.app.Instrumentation.class), any(IBinder.class), any(Application.class), any(Intent.class), any(ActivityInfo.class), any(CharSequence.class), any(Activity.class), anyString(), anyObject(), any(Configuration.class));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void newActivitySecondOverloadShouldCallActivityFactoryCreate() throws Exception
	{
		Instrumentation instrumentation = createInstrumentation();
		verify(instrumentation.getActivityFactory(), never()).create(any(Activity.class.getClass()));
		instrumentation.newActivity(mock(Activity.class).getClass(), mock(Context.class), mock(IBinder.class), mock(Application.class), mock(Intent.class), mock(ActivityInfo.class), mock(CharSequence.class), mock(Activity.class), "Test", new Object());
		verify(instrumentation.getActivityFactory()).create(any(Activity.class.getClass()));
	}

	/* Methods - end */

	/*
	public void callActivityOnPause(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnPause(activity);
	}

	public void callActivityOnPostCreate(Activity activity, Bundle icicle)
	{
		this.m_defaultInstrumentation.callActivityOnPostCreate(activity, icicle);
	}

	
	public void callActivityOnRestart(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnRestart(activity);
	}

	
	public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState)
	{
		this.m_defaultInstrumentation.callActivityOnRestoreInstanceState(activity, savedInstanceState);
	}

	
	public void callActivityOnResume(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnResume(activity);
	}

	
	public void callActivityOnSaveInstanceState(Activity activity, Bundle outState)
	{
		this.m_defaultInstrumentation.callActivityOnSaveInstanceState(activity, outState);
	}

	
	public void callActivityOnStart(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnStart(activity);
	}

	
	public void callActivityOnStop(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnStop(activity);
	}

	
	public void callActivityOnUserLeaving(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnUserLeaving(activity);
	}

	
	public void callApplicationOnCreate(android.app.Application app)
	{
		this.m_defaultInstrumentation.callApplicationOnCreate(app);
	}

	
	public boolean checkMonitorHit(Instrumentation.ActivityMonitor monitor, int minHits)
	{
		return this.m_defaultInstrumentation.checkMonitorHit(monitor, minHits);
	}

	
	public void endPerformanceSnapshot()
	{
		this.m_defaultInstrumentation.endPerformanceSnapshot();
	}

	
	public void finish(int resultCode, Bundle results)
	{
		this.m_defaultInstrumentation.finish(resultCode, results);
	}

	
	public Bundle getAllocCounts()
	{
		return this.m_defaultInstrumentation.getAllocCounts();
	}

	
	public Bundle getBinderCounts()
	{
		return this.m_defaultInstrumentation.getBinderCounts();
	}

	
	public ComponentName getComponentName()
	{
		return this.m_defaultInstrumentation.getComponentName();
	}

	
	public Context getContext()
	{
		return this.m_defaultInstrumentation.getContext();
	}

	
	public Context getTargetContext()
	{
		return this.m_defaultInstrumentation.getTargetContext();
	}

	
	public boolean invokeContextMenuAction(Activity targetActivity, int id, int flag)
	{
		return this.m_defaultInstrumentation.invokeContextMenuAction(targetActivity, id, flag);
	}

	
	public boolean invokeMenuActionSync(Activity targetActivity, int id, int flag)
	{
		return this.m_defaultInstrumentation.invokeMenuActionSync(targetActivity, id, flag);
	}

	
	public boolean isProfiling()
	{
		return this.m_defaultInstrumentation.isProfiling();
	}

	


	
	public Application newApplication(ClassLoader cl, StringUtility className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		return this.m_defaultInstrumentation.newApplication(cl, className, context);
	}

	
	public void onCreate(Bundle arguments)
	{
		this.m_defaultInstrumentation.onCreate(arguments);
	}

	
	public void onDestroy()
	{
		this.m_defaultInstrumentation.onDestroy();
	}

	
	public boolean onException(Object obj, Throwable e)
	{
		return this.m_defaultInstrumentation.onException(obj, e);
	}

	
	public void onStart()
	{
		this.m_defaultInstrumentation.onStart();
	}

	
	public void removeMonitor(Instrumentation.ActivityMonitor monitor)
	{
		this.m_defaultInstrumentation.removeMonitor(monitor);
	}

	
	public void runOnMainSync(Runnable runner)
	{
		this.m_defaultInstrumentation.runOnMainSync(runner);
	}

	
	public void sendCharacterSync(int keyCode)
	{
		this.m_defaultInstrumentation.sendCharacterSync(keyCode);
	}

	
	public void sendKeyDownUpSync(int key)
	{
		this.m_defaultInstrumentation.sendKeyDownUpSync(key);
	}

	
	public void sendKeySync(KeyEvent event)
	{
		this.m_defaultInstrumentation.sendKeySync(event);
	}

	
	public void sendPointerSync(MotionEvent event)
	{
		this.m_defaultInstrumentation.sendPointerSync(event);
	}

	
	public void sendStatus(int resultCode, Bundle results)
	{
		this.m_defaultInstrumentation.sendStatus(resultCode, results);
	}

	
	public void sendStringSync(StringUtility text)
	{
		this.m_defaultInstrumentation.sendStringSync(text);
	}

	
	public void sendTrackballEventSync(MotionEvent event)
	{
		this.m_defaultInstrumentation.sendTrackballEventSync(event);
	}

	
	public void setAutomaticPerformanceSnapshots()
	{
		this.m_defaultInstrumentation.setAutomaticPerformanceSnapshots();
	}

	
	public void setInTouchMode(boolean inTouch)
	{
		this.m_defaultInstrumentation.setInTouchMode(inTouch);
	}

	
	public void start()
	{
		this.m_defaultInstrumentation.start();
	}

	
	public Activity startActivitySync(Intent intent)
	{
		return this.m_defaultInstrumentation.startActivitySync(intent);
	}

	
	public void startAllocCounting()
	{
		this.m_defaultInstrumentation.startAllocCounting();
	}

	
	public void startPerformanceSnapshot()
	{
		this.m_defaultInstrumentation.startPerformanceSnapshot();
	}

	
	public void startProfiling()
	{
		this.m_defaultInstrumentation.startProfiling();
	}

	
	public void stopAllocCounting()
	{
		this.m_defaultInstrumentation.stopAllocCounting();
	}

	
	public void stopProfiling()
	{
		this.m_defaultInstrumentation.stopProfiling();
	}

	
	public void waitForIdle(Runnable recipient)
	{
		this.m_defaultInstrumentation.waitForIdle(recipient);
	}

	
	public void waitForIdleSync()
	{
		this.m_defaultInstrumentation.waitForIdleSync();
	}

	
	public Activity waitForMonitor(Instrumentation.ActivityMonitor monitor)
	{
		return this.m_defaultInstrumentation.waitForMonitor(monitor);
	}

	
	public Activity waitForMonitorWithTimeout(Instrumentation.ActivityMonitor monitor, long timeOut)
	{
		return this.m_defaultInstrumentation.waitForMonitorWithTimeout(monitor, timeOut);
	}
	*/
}