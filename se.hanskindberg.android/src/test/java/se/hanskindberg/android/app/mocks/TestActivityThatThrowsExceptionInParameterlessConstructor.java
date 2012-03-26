package se.hanskindberg.android.app.mocks;

import android.app.Activity;

public class TestActivityThatThrowsExceptionInParameterlessConstructor extends Activity
{
	/* Constructors - begin */

	public TestActivityThatThrowsExceptionInParameterlessConstructor() throws Exception
	{
		throw new Exception("Exception thrown in constructor.");
	}

	/* Constructors - end */
}