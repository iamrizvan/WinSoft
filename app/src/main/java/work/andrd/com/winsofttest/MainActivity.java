package work.andrd.com.winsofttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.andrd.com.winsofttest.constant.Constant;
import work.andrd.com.winsofttest.session.LoginSharedPreferences;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    LoginSharedPreferences loginSharedPreferences;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.name_text)
    TextView nameText;
    @BindView(R.id.email_text)
    TextView emailText;
    @BindView(R.id.phone_text)
    TextView phoneText;
    @BindView(R.id.addrees_text)
    TextView addreesText;
    @BindView(R.id.gender_text)
    TextView genderText;
    @BindView(R.id.dob_text)
    TextView dobText;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    String firstName ;
    String lastName ;
    String email;
    String phone ;
    String Address;
    String Gender;
    String DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        loginSharedPreferences = new LoginSharedPreferences(MainActivity.this);
        if (!loginSharedPreferences.isLoggedIn()) {
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        firstName = getIntent().getStringExtra(Constant.FIRST_NAME);
        lastName = getIntent().getStringExtra(Constant.LAST_NAME);
        email = getIntent().getStringExtra(Constant.EMAIL);
        phone = getIntent().getStringExtra(Constant.PHONE);
        Address = getIntent().getStringExtra(Constant.ADDRESS);
        Gender = getIntent().getStringExtra(Constant.GENDER);
        DOB = getIntent().getStringExtra(Constant.DATE_OF_BIRTH);


        if (firstName != null && lastName != null)
        {
            nameText.setText("Welcome "+ firstName+" "+lastName);
        }
        if (email != null)
        {
            emailText.setText(email);
        }
        if (phone != null)
        {
            phoneText.setText(phone);
        }
        if (Address != null)
        {
            addreesText.setText(Address);
        }
        if (Gender != null)
        {
            genderText.setText(Gender);
        }
        if (DOB != null)
        {
            dobText.setText(DOB);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            new LoginSharedPreferences(MainActivity.this).logOutUser();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
