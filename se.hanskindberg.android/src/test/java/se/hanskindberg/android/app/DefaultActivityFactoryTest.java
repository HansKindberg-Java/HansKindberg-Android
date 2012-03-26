package se.hanskindberg.android.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import se.hanskindberg.android.app.mocks.TestActivityThatThrowsExceptionInParameterlessConstructor;
import se.hanskindberg.android.testrunners.TestRunner;

import static org.junit.Assert.*;

@RunWith(TestRunner.class)
public class DefaultActivityFactoryTest
{
	/* Methods - begin */

	@Test
	public void createShouldThrowAnExceptionWithTheCorrectMessageIfTheActivityConstructorThrowsAnException()
	{
		DefaultActivityFactory defaultActivityFactory = new DefaultActivityFactory();
		try
		{
			defaultActivityFactory.create(TestActivityThatThrowsExceptionInParameterlessConstructor.class);
			fail();
		}
		catch (Exception exception)
		{
			assertEquals(String.format("Could note create an activity of type \"%s\".", TestActivityThatThrowsExceptionInParameterlessConstructor.class.getName()), exception.getMessage());
		}
	}

	@Test(expected = InstantiationException.class)
	public void createShouldThrowAnInstantiationExceptionIfTheActivityConstructorThrowsAnException() throws InstantiationException
	{
		DefaultActivityFactory defaultActivityFactory = new DefaultActivityFactory();
		defaultActivityFactory.create(TestActivityThatThrowsExceptionInParameterlessConstructor.class);
	}

	@Test
	public void defaultActivityFactoryImplementsIActivityFactory()
	{
		DefaultActivityFactory defaultActivityFactory = new DefaultActivityFactory();
		assertTrue(defaultActivityFactory instanceof IActivityFactory);
	}

	/* Methods - end */
}