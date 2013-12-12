package com.inzpiral.consumer.fragments;

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
import com.inzpiral.consumer.customs.RingGraphic;
import com.inzpiral.consumer.models.Evaluation;



public class LocationSlideMenu extends Fragment {

	private Evaluation mEvaluation;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.slidebar, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		((RingGraphic)getActivity().findViewById(R.id.ring_graph)).setPercent(66);

		mEvaluation = ((HomeActivity)getActivity()).getEvaluation();
		
	
	//	ListView lv = (ListView) getActivity().findViewById(R.id.customList);
		rowAdapter adapter = new rowAdapter(getActivity());
		for (int i = 0; i < 3; i++) {
			adapter.add(new rowItem("titulo","1%", android.R.drawable.ic_menu_search));
		}
	//	setListAdapter(adapter);
		
	}
	


	private class rowItem {
		public String tag, tag2;
		public int iconRes;
		public rowItem(String tag,String tag2, int iconRes) {
			this.tag = tag; 
			this.tag2 = tag2; 
			this.iconRes = iconRes;
		}
	}

	public class rowAdapter extends ArrayAdapter<rowItem> {

		public rowAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.sidebar_row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);
			TextView percent = (TextView) convertView.findViewById(R.id.row_percent);
			percent.setText(getItem(position).tag2);
			

			return convertView;
		}

	}

	


	
}

