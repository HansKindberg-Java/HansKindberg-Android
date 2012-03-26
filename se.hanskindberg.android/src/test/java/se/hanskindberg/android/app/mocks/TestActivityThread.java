package se.hanskindberg.android.app.mocks;

import android.app.Instrumentation;

public class TestActivityThread
{
	/* Fields - begin */

	private Instrumentation mInstrumentation;
	private static TestActivityThread s_currentActivityThread;

	/* Fields - end */

	/* Methods - begin */

	public static TestActivityThread currentActivityThread()
	{
		return s_currentActivityThread;
	}

	// To make testing possible
	public static void setCurrentActivityThread(TestActivityThread activityThread)
	{
		s_currentActivityThread = activityThread;

		String s = "";
	}

	// To make testing possible
	public void setInstrumentation(Instrumentation instrumentation)
	{
		this.mInstrumentation = instrumentation;
	}

	/* Methods - end */
}
