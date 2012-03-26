package se.hanskindberg.android.sampleapp;

import com.google.inject.AbstractModule;

public class InjectionModule extends AbstractModule
{
	/* Methods - begin */

	@Override
	protected void configure()
	{
		bind(IConstructorInjectionInformation.class).toInstance(ConstructorInjectionInformation.getInstance());
	}

	/* Methods - end */
}
