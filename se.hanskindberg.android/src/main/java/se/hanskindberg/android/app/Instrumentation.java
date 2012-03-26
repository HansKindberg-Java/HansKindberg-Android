package se.hanskindberg.android.app;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import se.hanskindberg.lang.ArgumentNullException;

public class Instrumentation extends android.app.Instrumentation
{
	/* Fields - begin */

	private final IActivityBinder m_activityBinder;
	private final IActivityFactory m_activityFactory;
	private final android.app.Instrumentation m_defaultInstrumentation;

	/* Fields - end */

	/* Constructors - begin */

	public Instrumentation(android.app.Instrumentation defaultInstrumentation, IActivityFactory activityFactory, IActivityBinder activityBinder)
	{
		super();

		if (defaultInstrumentation == null)
			throw new ArgumentNullException("defaultInstrumentation");

		if (activityFactory == null)
			throw new ArgumentNullException("activityFactory");

		if (activityBinder == null)
			throw new ArgumentNullException("activityBinder");

		this.m_activityBinder = activityBinder;
		this.m_activityFactory = activityFactory;
		this.m_defaultInstrumentation = defaultInstrumentation;
	}

	/* Constructors - end */

	/* Methods - begin */

	@Override
	public void addMonitor(ActivityMonitor monitor)
	{
		this.m_defaultInstrumentation.addMonitor(monitor);
	}

	@Override
	public ActivityMonitor addMonitor(IntentFilter filter, ActivityResult result, boolean block)
	{
		return this.m_defaultInstrumentation.addMonitor(filter, result, block);
	}

	@Override
	public ActivityMonitor addMonitor(String cls, ActivityResult result, boolean block)
	{
		return this.m_defaultInstrumentation.addMonitor(cls, result, block);
	}

	@Override
	public void callActivityOnCreate(Activity activity, Bundle icicle)
	{
		this.m_defaultInstrumentation.callActivityOnCreate(activity, icicle);
	}

	@Override
	public void callActivityOnDestroy(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnDestroy(activity);
	}

	@Override
	public void callActivityOnNewIntent(Activity activity, Intent intent)
	{
		this.m_defaultInstrumentation.callActivityOnNewIntent(activity, intent);
	}

	@Override
	public void callActivityOnPause(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnPause(activity);
	}

	@Override
	public void callActivityOnPostCreate(Activity activity, Bundle icicle)
	{
		this.m_defaultInstrumentation.callActivityOnPostCreate(activity, icicle);
	}

	@Override
	public void callActivityOnRestart(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnRestart(activity);
	}

	@Override
	public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState)
	{
		this.m_defaultInstrumentation.callActivityOnRestoreInstanceState(activity, savedInstanceState);
	}

	@Override
	public void callActivityOnResume(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnResume(activity);
	}

	@Override
	public void callActivityOnSaveInstanceState(Activity activity, Bundle outState)
	{
		this.m_defaultInstrumentation.callActivityOnSaveInstanceState(activity, outState);
	}

	@Override
	public void callActivityOnStart(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnStart(activity);
	}

	@Override
	public void callActivityOnStop(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnStop(activity);
	}

	@Override
	public void callActivityOnUserLeaving(Activity activity)
	{
		this.m_defaultInstrumentation.callActivityOnUserLeaving(activity);
	}

	@Override
	public void callApplicationOnCreate(Application app)
	{
		this.m_defaultInstrumentation.callApplicationOnCreate(app);
	}

	@Override
	public boolean checkMonitorHit(ActivityMonitor monitor, int minHits)
	{
		return this.m_defaultInstrumentation.checkMonitorHit(monitor, minHits);
	}

	@Override
	public void endPerformanceSnapshot()
	{
		this.m_defaultInstrumentation.endPerformanceSnapshot();
	}

	@Override
	public void finish(int resultCode, Bundle results)
	{
		this.m_defaultInstrumentation.finish(resultCode, results);
	}

	public IActivityBinder getActivityBinder()
	{
		return this.m_activityBinder;
	}

	public IActivityFactory getActivityFactory()
	{
		return this.m_activityFactory;
	}

	@Override
	public Bundle getAllocCounts()
	{
		return this.m_defaultInstrumentation.getAllocCounts();
	}

	@Override
	public Bundle getBinderCounts()
	{
		return this.m_defaultInstrumentation.getBinderCounts();
	}

	@Override
	public ComponentName getComponentName()
	{
		return this.m_defaultInstrumentation.getComponentName();
	}

	@Override
	public Context getContext()
	{
		return this.m_defaultInstrumentation.getContext();
	}

	public android.app.Instrumentation getDefaultInstrumentation()
	{
		return this.m_defaultInstrumentation;
	}

	@Override
	public Context getTargetContext()
	{
		return this.m_defaultInstrumentation.getTargetContext();
	}

	@Override
	public boolean invokeContextMenuAction(Activity targetActivity, int id, int flag)
	{
		return this.m_defaultInstrumentation.invokeContextMenuAction(targetActivity, id, flag);
	}

	@Override
	public boolean invokeMenuActionSync(Activity targetActivity, int id, int flag)
	{
		return this.m_defaultInstrumentation.invokeMenuActionSync(targetActivity, id, flag);
	}

	@Override
	public boolean isProfiling()
	{
		return this.m_defaultInstrumentation.isProfiling();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Activity newActivity(ClassLoader classLoader, String className, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException
	{
		return this.m_activityFactory.create((Class<? extends Activity>) classLoader.loadClass(className));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Activity newActivity(Class<?> activityClass, Context context, IBinder token, android.app.Application application, Intent intent, ActivityInfo activityInfo, CharSequence title, Activity parentActivity, String id, Object lastNonConfigurationInstance) throws IllegalAccessException, InstantiationException
	{
		Activity activity = this.m_activityFactory.create((Class<? extends Activity>) activityClass);

		this.m_activityBinder.attach(activity, context, null, this, token, application, intent, activityInfo, title, parentActivity, id, lastNonConfigurationInstance, new Configuration());

		return activity;
	}

	@Override
	public Application newApplication(ClassLoader classLoader, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		return this.m_defaultInstrumentation.newApplication(classLoader, className, context);
	}

	@Override
	public void onCreate(Bundle arguments)
	{
		this.m_defaultInstrumentation.onCreate(arguments);
	}

	@Override
	public void onDestroy()
	{
		this.m_defaultInstrumentation.onDestroy();
	}

	@Override
	public boolean onException(Object obj, Throwable e)
	{
		return this.m_defaultInstrumentation.onException(obj, e);
	}

	@Override
	public void onStart()
	{
		this.m_defaultInstrumentation.onStart();
	}

	@Override
	public void removeMonitor(ActivityMonitor monitor)
	{
		this.m_defaultInstrumentation.removeMonitor(monitor);
	}

	@Override
	public void runOnMainSync(Runnable runner)
	{
		this.m_defaultInstrumentation.runOnMainSync(runner);
	}

	@Override
	public void sendCharacterSync(int keyCode)
	{
		this.m_defaultInstrumentation.sendCharacterSync(keyCode);
	}

	@Override
	public void sendKeyDownUpSync(int key)
	{
		this.m_defaultInstrumentation.sendKeyDownUpSync(key);
	}

	@Override
	public void sendKeySync(KeyEvent event)
	{
		this.m_defaultInstrumentation.sendKeySync(event);
	}

	@Override
	public void sendPointerSync(MotionEvent event)
	{
		this.m_defaultInstrumentation.sendPointerSync(event);
	}

	@Override
	public void sendStatus(int resultCode, Bundle results)
	{
		this.m_defaultInstrumentation.sendStatus(resultCode, results);
	}

	@Override
	public void sendStringSync(String text)
	{
		this.m_defaultInstrumentation.sendStringSync(text);
	}

	@Override
	public void sendTrackballEventSync(MotionEvent event)
	{
		this.m_defaultInstrumentation.sendTrackballEventSync(event);
	}

	@Override
	public void setAutomaticPerformanceSnapshots()
	{
		this.m_defaultInstrumentation.setAutomaticPerformanceSnapshots();
	}

	@Override
	public void setInTouchMode(boolean inTouch)
	{
		this.m_defaultInstrumentation.setInTouchMode(inTouch);
	}

	@Override
	public void start()
	{
		this.m_defaultInstrumentation.start();
	}

	@Override
	public Activity startActivitySync(Intent intent)
	{
		return this.m_defaultInstrumentation.startActivitySync(intent);
	}

	@Override
	public void startAllocCounting()
	{
		this.m_defaultInstrumentation.startAllocCounting();
	}

	@Override
	public void startPerformanceSnapshot()
	{
		this.m_defaultInstrumentation.startPerformanceSnapshot();
	}

	@Override
	public void startProfiling()
	{
		this.m_defaultInstrumentation.startProfiling();
	}

	@Override
	public void stopAllocCounting()
	{
		this.m_defaultInstrumentation.stopAllocCounting();
	}

	@Override
	public void stopProfiling()
	{
		this.m_defaultInstrumentation.stopProfiling();
	}

	@Override
	public void waitForIdle(Runnable recipient)
	{
		this.m_defaultInstrumentation.waitForIdle(recipient);
	}

	@Override
	public void waitForIdleSync()
	{
		this.m_defaultInstrumentation.waitForIdleSync();
	}

	@Override
	public Activity waitForMonitor(ActivityMonitor monitor)
	{
		return this.m_defaultInstrumentation.waitForMonitor(monitor);
	}

	@Override
	public Activity waitForMonitorWithTimeout(ActivityMonitor monitor, long timeOut)
	{
		return this.m_defaultInstrumentation.waitForMonitorWithTimeout(monitor, timeOut);
	}

	/* Methods - end */
}