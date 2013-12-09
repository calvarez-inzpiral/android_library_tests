package com.javacodegeeks.android.json.utils;

import java.lang.reflect.Type;

import com.javacodegeeks.android.json.model.ChainedChoice;
import com.javacodegeeks.android.json.model.Comment;
import com.javacodegeeks.android.json.model.Geotag;
import com.javacodegeeks.android.json.model.MultipleChoiceQuestion;
import com.javacodegeeks.android.json.model.Picture;
import com.javacodegeeks.android.json.model.PresentationNode;
import com.javacodegeeks.android.json.model.Question;
import com.javacodegeeks.android.json.model.Repetition;
import com.javacodegeeks.android.json.model.Timestamp;


public class Utils {
	
	public static Type getTypeByTag(String tag) {

		if(tag.equals("Frogmi::Presentation::Node")) {
	        return PresentationNode.class;
		}
		else if(tag.equals("Frogmi::Presentation::Repetition")) {
	        return Repetition.class;
		}
//		else if(tag.equals("DESICION")) {
//	        return Decision.class;
//		}

		else if(tag.equals("Frogmi::Activities::Automatic::Timestamp")) {
	        return Timestamp.class;
		}
		else if(tag.equals("Frogmi::Activities::Automatic::Geotag")) {
	        return Geotag.class;
		}
		else if(tag.equals("Frogmi::Activities::Manual::Comment")) {
	        return Comment.class;
		}
		else if(tag.equals("Frogmi::Activities::Manual::Question")) {
	        return Question.class;
		}
		else if(tag.equals("Frogmi::Activities::Manual::MultipleChoiceQuestion")) {
	        return MultipleChoiceQuestion.class;
		}
		else if(tag.equals("Frogmi::Activities::Manual::Picture")) {
	        return Picture.class;
		}
		else if(tag.equals("Frogmi::Activities::Manual::ChainedChoice")) {
	        return ChainedChoice.class;
		}
		
		return PresentationNode.class;
	}

}
