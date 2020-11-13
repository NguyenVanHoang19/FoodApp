package com.nguyenvanhoang.foodapp.view.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.apdapter.ViewPagerCategoryAdapter;
import com.nguyenvanhoang.foodapp.entities.LoaiMonAn;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout =(TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        initActionBar();
        initIntent();
    }
    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    private void initIntent(){
        Intent intent =getIntent();
        List<LoaiMonAn> loaiMonAns = (List<LoaiMonAn>)intent.getSerializableExtra("loaiMon");
        int position = intent.getIntExtra("viTri",0);
        ViewPagerCategoryAdapter viewPagerCategoryAdapter = new ViewPagerCategoryAdapter(
                getSupportFragmentManager(),loaiMonAns);
        viewPager.setAdapter(viewPagerCategoryAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position,true);
        viewPagerCategoryAdapter.notifyDataSetChanged();
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