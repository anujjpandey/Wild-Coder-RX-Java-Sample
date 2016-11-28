package com.locationsearch;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locationsearch.fragments.ListFragmentIPSearch;
import com.locationsearch.fragments.ListFragmentStateSearch;
import com.locationsearch.utils.BackManager;
import com.locationsearch.utils.FragmentFlow;
import com.locationsearch.utils.Methods;


public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    public ActionBar actionBar;
    DrawerLayout drawerLayout;
    boolean doubleBackToExitPressedOnce = false;

    private NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        //mTitle = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                drawerLayout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setArrowColor();

    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (actionBar != null)
            actionBar.setTitle(title);
    }

    private void setArrowColor() {
        try {
            final Drawable upArrow = ContextCompat.getDrawable(MainActivity.this, R.drawable.abc_ic_ab_back_material);
            upArrow.setColorFilter(ContextCompat.getColor(MainActivity.this, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
            actionBar.setHomeAsUpIndicator(upArrow);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        }
    }


    @Override
    public void onBackPressed() {
        if (!BackManager.onBackPressed(getSupportFragmentManager()))
            return;
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
                if (doubleBackToExitPressedOnce) {
                    if (mNavigationDrawerFragment != null)
                        mNavigationDrawerFragment.setCallback(null);
                    this.finish();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Methods.openShortToast(this, getResources().getString(R.string.press_back_again_to_exit));
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (drawerLayout != null)
            drawerLayout.closeDrawers();
        switch (position) {
            case 0:
                new FragmentFlow().openFragment(getSupportFragmentManager(), new ListFragmentStateSearch(), true, null, R.id.container);
                break;
            case 1:
                new FragmentFlow().openFragment(getSupportFragmentManager(), new ListFragmentIPSearch(), false, null, R.id.container);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends android.app.Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
        }
    }


}