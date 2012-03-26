package se.hanskindberg.android.app;

public interface IActivityThread
{
	/* Methods - begin */

	android.app.Instrumentation getInstrumentation() throws IllegalAccessException;
	void setInstrumentation(android.app.Instrumentation instrumentation) throws IllegalAccessException;

	/* Methods - end */
}