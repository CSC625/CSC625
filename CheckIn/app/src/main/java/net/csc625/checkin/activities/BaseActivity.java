package net.csc625.checkin.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.csc625.checkin.R;
import net.csc625.checkin.models.pojos.MainMenuItem;
import net.csc625.checkin.models.pojos.MenuList;
import net.csc625.checkin.services.LogoutService;
import net.csc625.checkin.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseActivity - class that app other app activities will extend.  This will provide the nav menu by default.  When
 *      extending this class you need to open AndroidManifest.xml and make sure your activity is using the NoActionBar theme.
 */
public class BaseActivity extends AppCompatActivity {

    private static String TAG = BaseActivity.class.getSimpleName();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    protected RelativeLayout relativeLayout;
    private OrbitMenuNavigation orbitNav;
    private TextView userName;
    private boolean navBarUpdated = false;
    private Context test;

    int selectedItem = 0;

    //user setters to set menu title when menu drawer is open and closed
    private String drawerOpenTitle = "";
    private String drawerClosedTitle = "";
    private String activityTitle = "";

    private List<MainMenuItem> mainMenuItems;
    private DrawerListAdapter adapter;


    public String getDrawerOpenTitle() {
        return drawerOpenTitle;
    }

    public void setDrawerOpenTitle(String drawerOpenTitle) {
        this.drawerOpenTitle = drawerOpenTitle;
    }

    public String getDrawerClosedTitle() {
        return drawerClosedTitle;
    }

    public void setDrawerClosedTitle(String drawerClosedTitle) {
        this.drawerClosedTitle = drawerClosedTitle;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    /**
     * NavItem class
     */
    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    }

    /**
     * DrawerListAdapter class
     */
    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            } else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText(mNavItems.get(position).mTitle);
            subtitleView.setText(mNavItems.get(position).mSubtitle);
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }


    /**
     * OrbitMenuNavigation - internal class that controls the selection of items from the nav drawer.  This had to be
     *     an internal class because passing the app context to a separate class did not work.
     *
     */
    public class OrbitMenuNavigation {
        public static final int HOME = 0;
        public static final int ADD_STUDENT = 1;
        public static final int CHOOSE_STUDENT = 2;
        public static final int LOG_OFF = 3;

        private int result = 0;
        private Context context;

        public OrbitMenuNavigation(Context context)
        {
            this.context = context;
        }


        /**
         * gotoMenuItem - start app activities
         * @param position
         */
        public void gotoMenuItem(int position)
        {
            NavItem item = (NavItem)mNavItems.get(position);
            LogoutService logoutService = new LogoutService(this.context);

            // translation of menu item titles -> defined menu constants
            selectedItem = 0;
            if(item.mTitle.trim().equals("Home"))
                selectedItem = HOME;
            else if(item.mTitle.trim().equals("Add Student"))
                selectedItem = ADD_STUDENT;
            else if(item.mTitle.trim().equals("Choose Student"))
                selectedItem = CHOOSE_STUDENT;
            else if(item.mTitle.trim().equals("Log Out"))
                selectedItem = LOG_OFF;


            switch(selectedItem)
            {
                case HOME: startActivityForResult(HomeActivity.createIntent(context), result);
                    break;
                case ADD_STUDENT: startActivityForResult(CreateStudentActivity.createIntent(context), result);
                    break;
                case CHOOSE_STUDENT: startActivityForResult(ChooseStudentActivity.createIntent(context), result);
                    break;
                case LOG_OFF:
                    finish();
                    startActivityForResult(LogoutActivity.createIntent(context), result);
                    break;
            }
        }

        /**
         * showMessage - show Toast message if needed
         * @param message
         */
        private void showMessage(String message)
        {
            Toast.makeText(context, message,
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        test = this;
        Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /***
         * SendBird
         */
        //OrbitUserPreferences.init(getApplicationContext());

        /***
         * SendBird
         */

        relativeLayout = (RelativeLayout)findViewById(R.id.mainContent);

        // This method is going to be changed once we have all pages ready
        mNavItems.add(new NavItem(getString(R.string.menu_home), getString(R.string.menu_home), R.drawable.menu_school));
        orbitNav = new OrbitMenuNavigation(getApplicationContext());

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        this.adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.open,  /* "open drawer" description */
                R.string.open  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(activityTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                activityTitle = getSupportActionBar().getTitle().toString();
                if (!navBarUpdated) {
                    updateNavBar();
                    navBarUpdated = true;
                }
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    public void updateNavBar(){
        /*OrbitUserPreferences orbitPref = new OrbitUserPreferences(this);
        User user = orbitPref.getUserPreferenceObj("loggedUser");
        Log.i("UserFromSharedPref", user.toString());
        String userRole = user.getRole().getName();*/

        if (true) {
            this.mainMenuItems = MenuList.adminMenuList;
            for (MainMenuItem item : mainMenuItems) {
                String label = getString(item.getLabel());
                String title = getString(item.getTitle());
                mNavItems.add(new NavItem(label, title, item.getImage()));
                adapter.notifyDataSetChanged();
            }
        }
        orbitNav = new OrbitMenuNavigation(getApplicationContext());
        userName = (TextView)findViewById(R.id.userName);
        String firstName = "Kevin";
        String lastName = "Stanley";
        String role = "ADMIN";
        userName.setText(firstName + " " + lastName + " (" + role + ")");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    /*
* Called when a particular item from the navigation drawer
* is selected.
* */
    private void selectItemFromDrawer(int position) {
        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);

        orbitNav.gotoMenuItem(position);

    }

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, BaseActivity.class);
        return i;
    }
}
