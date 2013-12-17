package com.inzpiral.consumer.models;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.inzpiral.consumer.R;

public class MultipleChoiceQuestion extends FrogmiActivity {

	@SerializedName("question")
	private String mQuestion;
	
	@SerializedName("multiple")
	private int mMultiple;
	
	@SerializedName("alternatives")
	private List<Alternative> mAlternatives;

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}

	public int getMultiple() {
		return mMultiple;
	}

	public void setMultiple(int mMultiple) {
		this.mMultiple = mMultiple;
	}

	public List<Alternative> getAlternatives() {
		return mAlternatives;
	}

	public void setAlternatives(List<Alternative> mAlternatives) {
		this.mAlternatives = mAlternatives;
	}
	
	@Override
	public void display(Activity activity, View ParentView, int parentId) {
		mActivity = activity;
		mParentView = ParentView;
		mParentId = parentId;
		
		System.out.println("MOSTRANDOME! Soy un 'MultipleChoice' de question: " + getQuestion());
		MultipleChoiceController controller = new MultipleChoiceController(new MultipleChoiceView());
	}

	
	private class MultipleChoiceController {
		public MultipleChoiceController(MultipleChoiceView questionView) {
			((LinearLayout) mParentView.findViewById(mParentId)).addView(questionView);
			
			questionView.getQuestionTextView().setText(getQuestion());
			for(int i = 0; i < getAlternatives().size(); i++) {
				CompoundButton button = getMultiple() == 0 ? new RadioButton(mActivity) : new CheckBox(mActivity);
			    button.setText(getAlternatives().get(i).getName());
			    questionView.getRadioGroup().addView(button);
			}
		}
	}
	
	private class MultipleChoiceView extends LinearLayout {
		public MultipleChoiceView() {
			super(mActivity);
			LayoutInflater mInflater = LayoutInflater.from(mActivity);
			addView(mInflater.inflate(R.layout.module_multiple_choice, null));
		}
		
		public TextView getQuestionTextView() {
			return (TextView)findViewById(R.id.multiple_choice_question_text_view);
		}
		
		public RadioGroup getRadioGroup() {
			return (RadioGroup)findViewById(R.id.radio_group);
		}
	}
	
}
