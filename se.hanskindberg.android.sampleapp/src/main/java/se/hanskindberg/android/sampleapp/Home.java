package se.hanskindberg.android.sampleapp;

import android.os.Bundle;
import android.widget.TextView;
import se.hanskindberg.lang.ArgumentNullException;

public class Home extends android.app.Activity
{
	/* Fields - begin */

	private final IConstructorInjectionInformation m_constructorInjectionInformation;

	/* Fields - end */

	/* Constructors - begin */

	public Home(IConstructorInjectionInformation constructorInjectionInformation)
	{
		if (constructorInjectionInformation == null)
			throw new ArgumentNullException("constructorInjectionInformation");

		this.m_constructorInjectionInformation = constructorInjectionInformation;
	}

	/* Constructors - end */

	/* Methods - begin */

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.home_default_view);

		TextView informationTextView = (TextView) this.findViewById(R.id.informationTextView);

		informationTextView.setText(this.m_constructorInjectionInformation.getInformation());
	}

	/* Methods - end */
}
