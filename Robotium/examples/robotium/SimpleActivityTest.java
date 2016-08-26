package de.vogella.android.test.target.test;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import de.vogella.android.test.target.SimpleActivity;
import de.vogella.android.test.target.SimpleListActivity;

public class SimpleActivityTest extends
		ActivityInstrumentationTestCase2<SimpleActivity> {

	private Solo solo;

	public SimpleActivityTest() {
		super(SimpleActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	public void testListItemClickShouldDisplayToast() throws Exception {
		// check that we have the right activity
		solo.assertCurrentActivity("wrong activity", SimpleActivity.class);

		// Click a button which will start a new Activity
		// Here we use the ID of the string to find the right button
		solo.clickOnButton(solo
				.getString(de.vogella.android.test.target.R.string.button1));
		// assert that the current activity is the SimpleListActivity.class
		solo.assertCurrentActivity("wrong activity", SimpleListActivity.class);
		solo.clickInList(1);
		// searchForText has a timeout of 5 seconds
		assertTrue(solo.waitForText("Android")); // Assertion
		solo.clickInList(2);
		assertTrue(solo.waitForText("iPhone")); // Assertion
		solo.clickInList(3);
		assertTrue(solo.waitForText("Blackberry")); // Assertion
		solo.goBack();
		solo.clickOnButton("Button2");
		solo.clickOnButton("Button3");
	}

	public void testListItemClickShouldDisplayToast() throws Exception {
		// open the menu
		solo.sendKey(Solo.MENU);
		solo.clickOnText("Preferences");
		solo.clickOnText("User");
		solo.clearEditText(0);
		Assert.assertTrue(solo.searchText(""));
		solo.enterText(0, "http//:www.vogella.com");
		Assert.assertTrue(solo.searchText("http//:www.vogella.com"));
		solo.goBack();

	}

}