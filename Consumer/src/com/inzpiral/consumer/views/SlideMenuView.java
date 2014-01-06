package com.inzpiral.consumer.views;

import pl.polidea.treeview.TreeViewList;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.inzpiral.consumer.R;
import com.inzpiral.consumer.customs.RingGraphic;

public class SlideMenuView extends LinearLayout {

	public SlideMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	// Listeners
	public void setListeners(OnClickListener onClickListener){
		getFinishButton().setOnClickListener(onClickListener);
	}
	
	// Getters
	public RingGraphic getRingGraphic() {
		return (RingGraphic) findViewById(R.id.ring_graph);
	}

	public Button getFinishButton() {
		return (Button) findViewById(R.id.btn_end);
	}

	public ExpandableListView getExpandableListView() {
		return (ExpandableListView) findViewById(R.id.exp_list);
	}
	
//	public ExpandableListView getTreeListView() {
//		return (ExpandableListView) findViewById(R.id.mainTreeView);
//	}
	
	public TreeViewList getTreeListView() {
		return (TreeViewList) findViewById(R.id.mainTreeView);
	}
	
}
