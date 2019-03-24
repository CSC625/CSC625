package net.csc625.checkin.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import net.csc625.checkin.models.pojos.MainMenuItem;
import net.csc625.checkin.models.pojos.MenuList;

import java.util.ArrayList;
import java.util.List;

import net.csc625.checkin.R;



public class HomeActivity extends BaseActivity {

    List<MainMenuItem> mainMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        context = this;

        /*OrbitUserPreferences orbitPref = new OrbitUserPreferences(this);
        final User user = orbitPref.getUserPreferenceObj("loggedUser");
        Log.i("UserFromSharedPref", user.toString());
        String userRole = user.getRole().getName();*/

        // ROLE BASED LIST ASSIGNMENT FOR MENU GRID GENERATION
        if(true)
            this.mainMenuItems = MenuList.adminMenuList;
        super.onCreate(savedInstanceState);

        //need to inflate this activity inside the relativeLayout inherited from BaseActivity.  This will add this view to the mainContent layout
        getLayoutInflater().inflate(R.layout.activity_home, relativeLayout);

         GridView gridview = (GridView) findViewById(R.id.gridview);
         class ImageAdapter extends BaseAdapter
         {
             private Context mContext;
             private final List<MainMenuItem> menuItems;

             public ImageAdapter(Context c, List<MainMenuItem> menuItems ) {
                 mContext = c;
                 this.menuItems = menuItems;
             }

             @Override
             public int getCount() {
                 // TODO Auto-generated method stub
                 return menuItems.size();
             }

             @Override
             public Object getItem(int position) {
                 // TODO Auto-generated method stub
                 return null;
             }

             @Override
             public long getItemId(int position) {
                 // TODO Auto-generated method stub
                 return 0;
             }

             @Override
             public View getView(int position, View convertView, ViewGroup parent) {
                 // TODO Auto-generated method stub
                 View grid;
                 LayoutInflater inflater = (LayoutInflater) mContext
                         .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



                 grid = new View(mContext);

                 if (convertView == null)
                 {
                     MainMenuItem temp = menuItems.get(position);
                     grid = new View(mContext);
                     grid = inflater.inflate(R.layout.grid_item, null);
                     TextView textView = (TextView) grid.findViewById(R.id.gridText);
                     ImageView imageView = (ImageView) grid.findViewById(R.id.gridImage);
                     textView.setText(getString(temp.getLabel()));
                     imageView.setImageResource(temp.getImage());
                 } else
                     grid = convertView;

                 return grid;
             }
            }


        gridview.setAdapter(new ImageAdapter(this, mainMenuItems));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id)
            {
                MainMenuItem temp = mainMenuItems.get(position);

                if(temp.getLabel() == (R.string.menu_logout))
                {
                    finish();
                    Intent newIntent = new Intent(HomeActivity.this, LogoutActivity.class);
                    startActivity(newIntent);
                }
                if(temp.getLabel() == (R.string.menu_add_student))
                {
                    Intent newIntent = new Intent(HomeActivity.this, CreateStudentActivity.class);
                    startActivity(newIntent);
                }
                if(temp.getLabel() == (R.string.menu_choose_student))
                {
                    Intent newIntent = new Intent(HomeActivity.this, ChooseStudentActivity.class);
                    startActivity(newIntent);
                }

            }
        });

        // Displays a alert window and lets you know if your DB connection is successful.
        // If menu_student data is returned, then the connection was successful.
        getDBConnectionAlert();

    }

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, HomeActivity.class);
        return i;
    }

    public void getDBConnectionAlert() {
        // Sets the URL for the API url
        /*PropertiesService propertiesService = new PropertiesService(this);
        String apiUrl = propertiesService.getProperty(this, Constants.ORBIT_API_URL);
        OrbitRestClient orbitRestClient = new OrbitRestClient(this);
        orbitRestClient.setBaseUrl(apiUrl);
        orbitRestClient.get("all-students", null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray students) {
                Log.i("HomeActivity", "Successfully connected to DB.");
                // TODO GP-121 testing roles
                //rs.hasTeacherRole();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                if (errorResponse != null) {
                    OrbitRestClient orbitRestClient = new OrbitRestClient(HomeActivity.this);
                    Log.e("HomeActivity", "Error connection to DB: " + errorResponse.toString());
                    AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                    alertDialog.setTitle("DB Connection");
                    alertDialog.setMessage("Cannot connect to DB: " + orbitRestClient.getBaseUrl());
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }

        });*/
    }

    private Context context;

    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch(item.getItemId())
        {
            case R.id.menu_info:
                PopupService p = new PopupService(context);
                p.showPopup(PopupMessages.HOME_MESSAGE);
        }*/


        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }
}
