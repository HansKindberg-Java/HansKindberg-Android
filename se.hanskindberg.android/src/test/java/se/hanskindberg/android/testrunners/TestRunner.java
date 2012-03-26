package se.hanskindberg.android.testrunners;

import com.xtremelabs.robolectric.RobolectricConfig;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.runners.model.InitializationError;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class TestRunner extends RobolectricTestRunner
{
	/* Constructors - begin */

	public TestRunner(Class<?> testClass) throws IllegalAccessException, InitializationError, NoSuchFieldException, UnsupportedEncodingException
	{
		super(testClass, new RobolectricConfig(new File(getResourcesPath(), "TestAndroidManifest.xml"), new File(getResourcesPath(), "res")));
	}

	/* Constructors - end */

	/* Methods - begin */

	private static File getFile(File file, String... pathParts)
	{
		for (String pathPart : pathParts)
		{
			file = new File(file, pathPart);
		}

		return file;
	}

	private static String getResourcesPath() throws UnsupportedEncodingException
	{
		// This is dependant of the build output directory, if you change the output directory you probably have to change this code.
		return getFile(new File(URLDecoder.decode(TestRunner.class.getResource("/").getFile(), "UTF-8")).getParentFile().getParentFile(), "src", "test", "resources").getPath();
	}

	/* Methods - end */
}