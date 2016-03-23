package com.example.firstyalantistask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

//[Comment] Wrong toolbar and status bar color
//[Comment[ Wrong screen left and right paddings, see google material design guidelines
public class MainActivity extends AppCompatActivity implements Toastable {
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.status)
    TextView status;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.rlCreated)
    RelativeLayout rlCreated;
    @Bind(R.id.rlRegistered)
    RelativeLayout rlRegistered;
    @Bind(R.id.rlSolve)
    RelativeLayout rlSolve;
    @Bind(R.id.rlResponsible)
    RelativeLayout rlResponsible;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView; //[Comment] Wrong names, use google code style
    private MyToaster toaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toaster = new MyToaster(this); //[Comment] It you will have few screens, you will have memory leak here. You should unsubscribe MyToaster
        ButterKnife.bind(this);
        initRecyclerView();
        setOnClickListeners();

    }

    //Returns links List of images from resources
    private List<String> addLinks() {
        List<String> links = new ArrayList<>();
        String[] resourceLinks = getResources().getStringArray(R.array.urls);
        for (String link : resourceLinks) {
            links.add(link);
        }
        return links;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //Set OnClickListener for all Views
    void setOnClickListeners() {
        toaster.addClickListenerToViews(name, status, description);
        toaster.addClickListenerToViews(rlCreated, rlRegistered, rlResponsible, rlSolve);
    }

    //Interface method for making a toast
    @Override
    public void showToast(String controlName) {
        Toast.makeText(this, controlName, Toast.LENGTH_SHORT).show();
    }

    //set key "back" close application
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //initialize recyclerView for images
    void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapter(addLinks(), toaster));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
    }
}