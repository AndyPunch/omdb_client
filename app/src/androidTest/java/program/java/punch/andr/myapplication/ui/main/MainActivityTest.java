package program.java.punch.andr.myapplication.ui.main;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import program.java.punch.andr.myapplication.R;
import program.java.punch.andr.myapplication.TestComponentRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends InstrumentationTestCase {

    /*@Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

 */

    final String STRING_NO_RESULTS = "ejttrjgdbfnkyksfawhrrej";


    @Rule
    private TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());

    @Rule
    private IntentsTestRule<MainActivity> main =
            new IntentsTestRule<>(MainActivity.class, false, false);


    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);


    @Test
    public void testSearchRequestWithEmptyField() throws Exception {
        onView(withId(R.id.search_button)).perform(click());
        onView(withText(R.string.title_is_empty)).inRoot(withDecorView(not(is(main.getActivity()
                .getWindow()
                .getDecorView())))).check(matches(isDisplayed()));
    }


    @Test
    public void testSearchRequestWithNoResults() throws Exception {
        onView(withId(R.id.search_edittext))
                .perform(typeText(STRING_NO_RESULTS), closeSoftKeyboard());
        onView(withId(R.id.search_button)).perform(click());
        onView(withId(R.id.list_empty)).check(matches(withText(R.string.no_results)));
    }

}
