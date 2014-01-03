package com.inzpiral.consumer.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.inzpiral.consumer.R;
import com.inzpiral.consumer.activities.HomeActivity;
import com.inzpiral.consumer.adapters.SlidebarExpandableAdapter;
import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.models.PresentationNode;
import com.inzpiral.consumer.utils.EvaluationHelper;
import com.inzpiral.consumer.views.SlideMenuView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class SlideMenuController implements OnClickListener {

	private SlideMenuView mSpinnersView;
	private SlideMenuControllerListener mListener;
	private EvaluationHelper mHelper;
	
	private ArrayList<String> mParentItems = new ArrayList<String>();
	private ArrayList<Object> mChildItems = new ArrayList<Object>();
	private ExpandableListView mExpListView;

	public SlideMenuController(SlideMenuView slideMenuView, SlideMenuControllerListener listener) {
		this.mSpinnersView = slideMenuView;
		this.mListener = listener;
		this.mHelper = EvaluationHelper.getInstance();

		mSpinnersView.getRingGraphic().setPercent(75);


		mExpListView = slideMenuView.getExpandableListView();
		mExpListView.setDividerHeight(2);
		mExpListView.setGroupIndicator(null);
		mExpListView.setClickable(true);

		configureList(mHelper.getCategories());
		mListener.setAdapter(mExpListView, mParentItems, mChildItems);
		mExpListView.setOnChildClickListener(mOnChildClickListener);
		
		recalculateProgress();
	}
	
	public void configureList(List<Node> categories) {
		for (Node category : categories) {
			mParentItems.add(category.getName());
			ArrayList<String> child = new ArrayList<String>();
			for (BaseNode node : category.getChildren()) {
				child.add(((Node) node).getName());
			}
			mChildItems.add(child);
		}
	}
	

	private void recalculateProgress() {
		for (Node node : mHelper.getBrands()) {
			if(node instanceof PresentationNode) {
				PresentationNode presentationNode = (PresentationNode)node;
				System.out.println(node.getName() + ": " + (int)((float)presentationNode.countAnswers() / presentationNode.totalAnswers() * 100) + "%");
			}
		}
	}

	private OnChildClickListener mOnChildClickListener = new OnChildClickListener() {
		@Override
		public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
			return mListener.childClicked(childPosition, childPosition);
		}
	};
	

	@Override
	public void onClick(View v) {
		Thread t = new Thread(null, new Runnable() {
			@Override
			public void run() {
				//				Node v = (Node) EvaluationHelper.getInstance().getRoot();
				Evaluation v = EvaluationHelper.getInstance().getEvaluations();
				Gson gson = new Gson();

				String s = gson.toJson(v);
				System.out.println(s);
				System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/json.txt");
				File myFile = new File(Environment.getExternalStorageDirectory() + "/json.txt");
				try {
					myFile.createNewFile();
					FileOutputStream fOut = new FileOutputStream(myFile);
					OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
					myOutWriter.append(s);
					myOutWriter.close();
					fOut.close();
					System.out.println("guardo todo en un txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "saving", 1024 * 1024);
		t.start();

		try {
			t.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//        File myFile = new File("/sdcard/myjsonstuff.txt");
		//        FileInputStream fIn = new FileInputStream(myFile);
		//        BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
		//        String aDataRow = "";
		//        String aBuffer = ""; //Holds the text
		//        while ((aDataRow = myReader.readLine()) != null) 
		//        {
		//            aBuffer += aDataRow ;
		//        }
		//        myReader.close();
		//		
	}

	// Interfaces
	public interface SlideMenuControllerListener {
		public void setAdapter(ExpandableListView expListView, ArrayList<String> parentItems, ArrayList<Object> childItems);
		public boolean childClicked(int groupPosition, int childPosition);
	}

}
