package me.gopal.interview.ui.main;

import android.content.res.Resources;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.test.annotation.UiThreadTest;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import me.gopal.interview.R;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private static final int[] MENU_CONTENT_ITEM_IDS = {
            R.id.navigation_home, R.id.navigation_dashboard
    };
    private Map<Integer, String> menuStringContent;

    private BottomNavigationView bottomNavigation;

    @Before
    public void setUp() throws Exception {
        final MainActivity activity = activityTestRule.getActivity();
        activity.setTheme(R.style.Theme_MaterialComponents_Light);
        bottomNavigation = activity.findViewById(R.id.nav_view);

        final Resources res = activity.getResources();
        menuStringContent = new HashMap<>(MENU_CONTENT_ITEM_IDS.length);
        menuStringContent.put(R.id.navigation_home, res.getString(R.string.title_home));
        menuStringContent.put(R.id.navigation_dashboard, res.getString(R.string.title_dashboard));
    }
    @UiThreadTest
    @Test
    @SmallTest
    public void testBasics() {
        // Check the contents of the Menu object
        final Menu menu = bottomNavigation.getMenu();
        assertNotNull("Menu should not be null", menu);
        assertEquals("Should have matching number of items", MENU_CONTENT_ITEM_IDS.length, menu.size());
        for (int i = 0; i < MENU_CONTENT_ITEM_IDS.length; i++) {
            final MenuItem currItem = menu.getItem(i);
            assertEquals("ID for Item #" + i, MENU_CONTENT_ITEM_IDS[i], currItem.getItemId());
        }
    }

    @UiThreadTest
    @Test
    @SmallTest
    public void testAddItemsWithoutMenu() {
        BottomNavigationView navigation = new BottomNavigationView(activityTestRule.getActivity());
        activityTestRule.getActivity().setContentView(navigation);
        navigation.getMenu().add("Item1");
        navigation.getMenu().add("Item2");
        assertEquals(2, navigation.getMenu().size());
        navigation.getMenu().removeItem(0);
        navigation.getMenu().removeItem(0);
        assertEquals(0, navigation.getMenu().size());
    }

    @UiThreadTest
    @Test
    @SmallTest
    public void testContentDescription() {
        ViewGroup menuView = (ViewGroup) bottomNavigation.getChildAt(0);
        final int count = menuView.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = menuView.getChildAt(i);
            assertEquals(menuStringContent.get(child.getId()), child.getContentDescription().toString());
        }
        menuView.getChildAt(0).getContentDescription();
    }
}