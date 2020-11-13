package com.nguyenvanhoang.foodapp.apdapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nguyenvanhoang.foodapp.entities.LoaiMonAn;
import com.nguyenvanhoang.foodapp.view.category.CategoryFragment;

import java.util.List;

public class ViewPagerCategoryAdapter extends FragmentPagerAdapter {
    private List<LoaiMonAn> loaiMonAns;
    public ViewPagerCategoryAdapter(FragmentManager fragmentManager , List<LoaiMonAn> loaiMonAns){
        super(fragmentManager);
        this.loaiMonAns = loaiMonAns;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle bundle =new Bundle();
        bundle.putString("keyID",loaiMonAns.get(position).getKeyID());
        bundle.putString("tenLoaiMon",loaiMonAns.get(position).getTenLoaiMon());
        bundle.putString("hinhAnh",loaiMonAns.get(position).getHinhAnh());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return loaiMonAns.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return loaiMonAns.get(position).getTenLoaiMon();
    }
}
