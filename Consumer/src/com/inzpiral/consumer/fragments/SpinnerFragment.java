package com.inzpiral.consumer.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.inzpiral.consumer.R;
import com.inzpiral.consumer.activities.HomeActivity;
import com.inzpiral.consumer.controllers.SpinnerController;
import com.inzpiral.consumer.controllers.SpinnerController.SpinnerControllerListener;
import com.inzpiral.consumer.models.Evaluation;
import com.inzpiral.consumer.models.Node;
import com.inzpiral.consumer.utils.EvaluationHelper;
import com.inzpiral.consumer.views.SpinnersView;

public class SpinnerFragment extends SherlockFragment implements SpinnerControllerListener {
	
	private String[] vals = { "Seleccione ubicacion" };
	private EvaluationHelper mHelper;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.spinners, container, false);
	}
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
		
		// Activity links the view and the controller
		SpinnerController spinnerController = new SpinnerController((SpinnersView) view.findViewById(R.id.spinners_view), this, getArguments());
		
		// Intercept the events of MainView
		if(getArguments() != null) {
			((SpinnersView) view.findViewById(R.id.spinners_view)).setListeners(spinnerController);
		}
		
	}

	@Override
	public void onLoadQuestionTypes(int position) {

		mHelper = EvaluationHelper.getInstance();
		if(mHelper.getCategories().size() == 0) {
			return;
		}
		
		mHelper.setCurrentCategory(mHelper.getCategories().get(position));
		
		ArrayList<String> questionTypes = mHelper.getNodesAsString(mHelper.getQuestionTypes());

		Fragment newFragment = new MainFragment();
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

		Bundle bundle = new Bundle();
		bundle.putStringArray("question_types", questionTypes.toArray(new String[0]));
		newFragment.setArguments(bundle);
		
//		((ViewPager)getActivity().findViewById(R.id.pager)).getAdapter().getItemPosition(0).ge;
		transaction.replace(R.id.main_view, newFragment);
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();
		
	}
}
