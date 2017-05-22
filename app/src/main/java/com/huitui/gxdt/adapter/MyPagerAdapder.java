package com.huitui.gxdt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapder extends FragmentPagerAdapter {
	private List<Fragment> list;
	public MyPagerAdapder(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list=list;

		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return super.getPageTitle(position);
	}
}
