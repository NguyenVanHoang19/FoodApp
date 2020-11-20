package com.nguyenvanhoang.foodapp.view.searchviewmonan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.apdapter.RecyclerViewSearchMonAn;
import com.nguyenvanhoang.foodapp.dao.MonAnDAO;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.interface_dao.MonAn_Interface;
import com.nguyenvanhoang.foodapp.view.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchViewMonAnActivity extends AppCompatActivity {
    private Toolbar toolbar ;
    private FirebaseDatabase firebaseDatabase ;
    private List<MonAn> monAnList = new ArrayList<MonAn>();
    private RecyclerView recyclerView ;
    private ProgressBar progressBar ;
    private RecyclerViewSearchMonAn adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_mon_an);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearchMonAn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_Search);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        monAnList = (List<MonAn>) intent.getSerializableExtra("monAnList");
        adapter = new RecyclerViewSearchMonAn(monAnList,SearchViewMonAnActivity.this);
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(true);
        adapter.setOnItemClickListener(new RecyclerViewSearchMonAn.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(SearchViewMonAnActivity.this, DetailActivity.class);
                System.out.println(monAnList.size());
                intent.putExtra("idNhaHang",monAnList.get(position).getIdNhaHang());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}