package com.smriti.uiautomatortest;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TestCases extends UiAutomatorTestCase {

	public void testDemo() throws UiObjectNotFoundException,
			InterruptedException {

		// Simulate a short press on the HOME button.
		getUiDevice().pressHome();

		// simulate click on All Apps screen.
		// All Apps button’s content-description property has the value “Apps”
		UiObject allAppsButton = new UiObject(
				new UiSelector().description("Apps"));

		// Simulate a click to bring up the All Apps screen.
		allAppsButton.clickAndWaitForNewWindow();

		// Simulate click on 'Apps' section
		UiObject appsTab = new UiObject(new UiSelector().text("Apps"));

		// Simulate a click to enter the Apps tab.
		appsTab.click();

		// Simulate a user swiping until they come to the Clock app icon
		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));

		// Set the swiping mode to horizontal - default is vertical
		appViews.setAsHorizontalList();

		// Create a UiSelector to find the Clock app and simulate
		// a user click to launch the app.
		UiObject clock_app = appViews.getChildByText(new UiSelector()
				.className(android.widget.TextView.class.getName()), "Clock");
		clock_app.clickAndWaitForNewWindow();

		// Validate that the package name is the expected one
		UiObject clock_validation = new UiObject(
				new UiSelector().packageName("com.google.android.deskclock"));
		assertTrue("Unable to detect Clock App", clock_validation.exists());
		System.out.println("Clock App launched");

		// adding 5sec delay after app is launched
		Thread.sleep(5000);

		// get current time
		UiObject time = new UiObject(
				new UiSelector().resourceId("com.android.deskclock:id/time"));
		String current_time = time.getContentDescription();
		System.out.println("Current Time : " + current_time);

		// get current date
		UiObject date = new UiObject(
				new UiSelector().resourceId("com.android.deskclock:id/date"));
		String todays_date = date.getText();
		System.out.println("Today's Date : " + todays_date);
	}
}
