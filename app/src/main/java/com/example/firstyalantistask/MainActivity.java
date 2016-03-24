package com.example.firstyalantistask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

//[Comment[ Wrong screen left and right paddings, see google material design guidelines
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initializeRecyclerView();
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

    //Set OnClickListener for all Views
    void setOnClickListeners() {
        addClickListenerToViews(name, status, description);
        addClickListenerToViews(rlCreated, rlRegistered, rlResponsible, rlSolve);
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
    void initializeRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapter(addLinks(), this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    //add onClickListener For Views in array of ViewGroups
    void addClickListenerToViews(ViewGroup... view) {
        for (ViewGroup v : view) {
            for (int i = 0; i < v.getChildCount(); i++) {
                View child = v.getChildAt(i);
                child.setOnClickListener(this);
            }
        }
    }

    //add onClickListener For Views array
    void addClickListenerToViews(View... view) {
        for (View v : view) {
            v.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, v.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }
}