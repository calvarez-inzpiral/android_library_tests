package com.inzpiral.consumer.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.activities.HomeActivity;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.utils.EvaluationHelper;

public class LocationSlideMenu extends Fragment {

	private Evaluation mEvaluation;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mEvaluation = ((HomeActivity)getActivity()).getEvaluation();
		ArrayList<String> locations = getLocationsAsString();
		

	}

	private ArrayList<String> getLocationsAsString() {
		ArrayList<String> result = new ArrayList<String>();
		for (Node node : new EvaluationHelper(mEvaluation).getLocations()) {
			result.add(node.getName());
		}
		
		return result;
	}
	


	
}

