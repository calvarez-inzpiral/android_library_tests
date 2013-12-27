package com.inzpiral.consumer.models;

import android.app.Activity;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.inzpiral.consumer.R;

public class Question extends FrogmiActivity {
	//temporalmente no se parsea los datos, solo question
	@SerializedName("question")
	private String mQuestion;
	@SerializedName("regex")
	transient private String mRegExp;
	@SerializedName("errorMessage")
	transient private String mErrorMsg;
	@SerializedName("keyboard")
	transient private int mKeyboard;
	transient private EditText mInputField;
	transient private String NULL_VALUE = "null";


	// Getters and Setters
	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}

	public String getRegExp() {
		return mRegExp;
	}

	public void setRegExp(String mRegExp) {
		this.mRegExp = mRegExp;
	}

	public String getRegMsg() {
		return mErrorMsg;
	}

	public void setRegMsg(String mErrorMsg) {
		this.mErrorMsg = mErrorMsg;
	}

	public int getKeyboard() {
		return mKeyboard;
	}

	public void setKeyboard(int mKeyboard) {
		this.mKeyboard = mKeyboard;
	}

	@Override
	public void display(Activity activity, View parentView, int parentId) {
		mActivity = activity;
		mParentView = parentView;
		mParentId = parentId;

		System.out.println("MOSTRANDOME! Soy un 'Question' de question: " + getQuestion());
		QuestionController controller = new QuestionController(new QuestionView());
	}


	private class QuestionController implements TextWatcher {
		public QuestionController(QuestionView questionView) {
			((LinearLayout) mParentView.findViewById(mParentId)).addView(questionView);
			questionView.getQuestionTextView().setText(getQuestion());
			questionView.setListener(this);
			mInputField = questionView.getFieldEditText();
			formValidation(questionView.getFieldEditText(), 2); //getKeyboard(); cambia el numero hardcode por ese metodo
			if(hasResult()) {
				questionView.getFieldEditText().setText(getResult());
			}
			else {
				setResult("");				
			}		
		}

		@Override
		public void afterTextChanged( final Editable s) {
			setResult(s.toString());
			Handler mHandler = new Handler();
			Runnable mFilterTask = new Runnable() {
				@Override
				public void run() {

					//falta llamar al metodo isInputValueInvalid
					if( isInputValueInvalid(s.toString()) && !s.toString().equals("")){

						//cambia el color del texto segun version de Andorid
						if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
							mInputField.setError( "hola");
						}else{
							mInputField.setError( Html.fromHtml("<font color='black'>"+"hola"+"</font>"));
						}
					}
				}       
			};
			mHandler.removeCallbacks(mFilterTask); 
			mHandler.postDelayed(mFilterTask, 1000);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
	}

	private class QuestionView extends LinearLayout {
		public QuestionView() {
			super(mActivity);
			LayoutInflater mInflater = LayoutInflater.from(mActivity);
			addView(mInflater.inflate(R.layout.module_question, null));
		}

		public TextView getQuestionTextView() {
			return (TextView)findViewById(R.id.question);
		}

		public EditText getFieldEditText() {
			return (EditText)findViewById(R.id.field);
		}

		public void setListener(TextWatcher watcher) {
			getFieldEditText().addTextChangedListener(watcher);
		}

	}

	public boolean isInputValueInvalid(String inputValue) {		
		// TODO traer los regex y message desde json
		if(getRegExp() != null && getRegExp()!=NULL_VALUE && !inputValue.matches(getRegExp()) && 
				!getRegMsg().equals(NULL_VALUE)){
			return true;
		}
		return false;
	}

	@Override
	public void formPercent() {
		// TODO dar ponderaci—n de pregunta sobre el total y setear grafico y lista

	}


	public void formValidation(EditText questionInput, int keyboard) {

		switch (keyboard) {
		case 1:
			questionInput.setRawInputType(InputType.TYPE_CLASS_NUMBER );

			break;
		case 2:
			questionInput.setRawInputType(InputType.TYPE_CLASS_NUMBER 
					| InputType.TYPE_NUMBER_FLAG_DECIMAL |
					InputType.TYPE_NUMBER_FLAG_SIGNED);
			break;

		default:
			questionInput.setRawInputType(1);
			break;
		}
	}

	@Override
	public void formClear() {
		// TODO Auto-generated method stub


	}

}
