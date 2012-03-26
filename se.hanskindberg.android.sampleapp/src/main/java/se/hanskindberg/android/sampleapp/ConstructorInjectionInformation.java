package se.hanskindberg.android.sampleapp;

public class ConstructorInjectionInformation implements IConstructorInjectionInformation
{
	/* Fields - begin */

	private static ConstructorInjectionInformation s_instance;

	/* Fields - end */

	/* Methods - begin */

	@Override
	public String getInformation()
	{
		return "This message is returned from a class that is injected in the constructor of an Activity.";
	}

	public static ConstructorInjectionInformation getInstance()
	{
		if (s_instance == null)
			s_instance = new ConstructorInjectionInformation();

		return s_instance;
	}

	/* Methods - end */
}
