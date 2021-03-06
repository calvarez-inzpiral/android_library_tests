package com.inzpiral.consumer.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.polidea.treeview.InMemoryTreeStateManager;
import pl.polidea.treeview.TreeBuilder;
import pl.polidea.treeview.TreeStateManager;
import pl.polidea.treeview.TreeViewList;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.google.gson.Gson;
import com.inzpiral.consumer.models.BaseNode;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.models.IHeader;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.models.PresentationNode;
import com.inzpiral.consumer.models.TreeStructure;
import com.inzpiral.consumer.utils.EvaluationHelper;
import com.inzpiral.consumer.views.SlideMenuView;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 *
 */
public class SlideMenuController implements OnClickListener {

	private SlideMenuView mSlideMenuView;
	private SlideMenuControllerListener mListener;
	private EvaluationHelper mHelper;

	private ArrayList<String> mParentItems = new ArrayList<String>();
	private ArrayList<Object> mChildItems = new ArrayList<Object>();
	private ExpandableListView mExpListView;

	// ExpandableTree
	private enum TreeType implements Serializable {
		SIMPLE
	}
	private final Set<Long> selected = new HashSet<Long>();
	private TreeViewList treeView;

	private static ArrayList<TreeStructure> mNodeDepth;
	private TreeStateManager<TreeStructure> manager = null;
	//	private static final int[] DEMO_NODES = new int[] { 0, 0, 1, 1, 1, 2, 2, 1,
	//		1, 2, 1, 0, 0, 0, 1, 2, 3, 2, 0, 0, 1, 2, 0, 1, 2, 0, 1 };

	private TreeType treeType;
	private boolean collapsible;

	public SlideMenuController(SlideMenuView slideMenuView, SlideMenuControllerListener listener) {
		this.mSlideMenuView = slideMenuView;
		this.mListener = listener;
		this.mHelper = EvaluationHelper.getInstance();

		mSlideMenuView.getRingGraphic().setPercent(75);
		displayHeaders();

		configureExpandableTree();

		//		Para 2 niveles NO BORRAR
		//		mExpListView = slideMenuView.getExpandableListView();
		//		mExpListView.setDividerHeight(2);
		//		mExpListView.setGroupIndicator(null);
		//		mExpListView.setClickable(true);
		//		configureList(mHelper.getCategories());
		//		mListener.setAdapter(mExpListView, mParentItems, mChildItems);
		//		mExpListView.setOnChildClickListener(mOnChildClickListener);

		//		NO BORRAR
		//		recalculateProgress();
	}

	private void configureExpandableTree() {
		TreeType newTreeType = null;
		boolean newCollapsible;

		//		if (savedInstanceState == null) {
		manager = new InMemoryTreeStateManager<TreeStructure>();
		final TreeBuilder<TreeStructure> treeBuilder = new TreeBuilder<TreeStructure>(manager);
		for (int i = 0; i < mNodeDepth.size(); i++) {
			treeBuilder.sequentiallyAddNextNode(mNodeDepth.get(i), mNodeDepth.get(i).getDepth());
		}

		newTreeType = TreeType.SIMPLE;
		newCollapsible = true;
		//		} else {
		//			manager = (TreeStateManager<Long>) savedInstanceState
		//					.getSerializable("treeManager");
		//			if (manager == null) {
		//				manager = new InMemoryTreeStateManager<Long>();
		//			}
		//			newTreeType = (TreeType) savedInstanceState
		//					.getSerializable("treeType");
		//			if (newTreeType == null) {
		//				newTreeType = TreeType.SIMPLE;
		//			}
		//			newCollapsible = savedInstanceState.getBoolean("collapsible");
		//		}

		//		treeView = (TreeViewList) getActivity().findViewById(R.id.mainTreeView);
		treeView = mSlideMenuView.getTreeListView();
//		treeView.setHandleTrackballPress(true);

		mListener.setAdapter(treeView, selected, manager);

		setCollapsible(newCollapsible);
		manager.collapseChildren(null);
	}

	protected final void setCollapsible(final boolean newCollapsible) {
		this.collapsible = newCollapsible;
		treeView.setCollapsible(this.collapsible);
	}

	private void displayHeaders() {
		mNodeDepth = new ArrayList<TreeStructure>();
		if(mHelper.getRoot() instanceof IHeader) {
			IHeader root = (IHeader) mHelper.getRoot();
			System.out.println("Root: " + root.headerName());
			root.displayHeader(mNodeDepth, 0);
		}

		System.out.println(Arrays.toString(mNodeDepth.toArray()));
	}

	// llena el listview hay que hacerlo iterar los nuevos niveles
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
			//			return mListener.childClicked(childPosition, childPosition);
			return false;
		}
	};


	@Override
	public void onClick(View v) {
		Thread t = new Thread(null, new Runnable() {
			@Override
			public void run() {
				//Node v = (Node) EvaluationHelper.getInstance().getRoot();
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
		public void setAdapter(TreeViewList treeView, Set<Long> selected,
				TreeStateManager<TreeStructure> manager);
	}

}
