package com.example.administrator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.administrator.activity.FileLoadActivity;
import com.example.administrator.apputils.R;
import com.example.administrator.view.TimeView;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class Fragment1 extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    private TextView textView;
    private DrawerLayout drawer;
    private Button but_load;
    private TimeView mTimeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView(view);

        return view;
    }

    private void initView(View view){
        textView = (TextView) view.findViewById(R.id.id_time);
        but_load = (Button) view.findViewById(R.id.btu_load);

        mTimeView = new TimeView();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimeView.showControlTime(getContext(),textView,false,100,"YMD", TimePickerView.Type.YEAR_MONTH_DAY);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
//        but_load.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), FileLoadActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
