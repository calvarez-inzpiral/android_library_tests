package com.inzpiral.consumer.fragments;

import com.inzpiral.consumer.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SpinnerFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 
		View fragmentView = inflater.inflate(R.layout.spinners, container, false);
		return fragmentView;
		
	}
}
