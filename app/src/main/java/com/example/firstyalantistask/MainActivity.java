package com.example.firstyalantistask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IToaster{
    @Bind(R.id.name) TextView name;
    @Bind(R.id.status) TextView status;
    @Bind(R.id.description) TextView description;
    @Bind(R.id.rlCreated) RelativeLayout rlCreated;
    @Bind(R.id.rlRegistered) RelativeLayout rlRegistered;
    @Bind(R.id.rlSolve) RelativeLayout rlSolve;
    @Bind(R.id.rlResponsible) RelativeLayout rlResponsible;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initRecyclerView();
        setOCL();

    }
    //Getting links of images from resources
    List<String> addLinks(){
        List<String> links = new ArrayList<>();
        String[] resourceLinks = getResources().getStringArray(R.array.urls);
        for(String link:resourceLinks){
            links.add(link);
        }return links;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    //Set OnClickListener for all Views
    void setOCL(){
        name.setOnClickListener(this);
        status.setOnClickListener(this);
        description.setOnClickListener(this);
        rlCreated.setOnClickListener(this);
        rlRegistered.setOnClickListener(this);
        rlSolve.setOnClickListener(this);
        rlResponsible.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        toaster(v);
    }
    //Interface method for RecyclerViewAdapter
    @Override
    public void toaster(View v){
        StringBuilder toastText = new StringBuilder().append(getString(R.string.pressed_view)).append(" ")
                .append(v.getClass().getSimpleName());
        Toast.makeText(this,  toastText , Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //initialize recyclerView for images
    void initRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapter(addLinks(), this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
    }

}
