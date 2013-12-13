package com.inzpiral.consumer.utils;

import java.lang.reflect.Type;

import com.inzpiral.consumer.models.ChainedChoice;
import com.inzpiral.consumer.models.Comment;
import com.inzpiral.consumer.models.Decision;
import com.inzpiral.consumer.models.Geotag;
import com.inzpiral.consumer.models.MultipleChoiceQuestion;
import com.inzpiral.consumer.models.Picture;
import com.inzpiral.consumer.models.PresentationNode;
import com.inzpiral.consumer.models.Question;
import com.inzpiral.consumer.models.Repetition;
import com.inzpiral.consumer.models.Row;
import com.inzpiral.consumer.models.Section;
import com.inzpiral.consumer.models.Timestamp;
import com.inzpiral.consumer.models.Boolean;


public class ModelUtils {
	
	public static Type getTypeByTag(String tag) {
		
		// Nodos Basicos
		if(tag.equals("Frogmi::Presentation::Node")) {
	        return PresentationNode.class;
		}
		else if(tag.equals("Frogmi::Presentation::Repetition")) {
	        return Repetition.class;
		}
		else if(tag.equals("Frogmi::Presentation::Decision")) {
	        return Decision.class;
		}

		// Nodos Eespeciales
		else if(tag.equals("Frogmi::Presentation::Section")) {
	        return Section.class;
		}
		else if(tag.equals("Frogmi::Presentation::Row")) {
	        return Row.class;
		}
		
		// Hojas
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
		else if(tag.equals("Frogmi::Activities::Manual::Boolean")) {
	        return Boolean.class;
		}
		
		return PresentationNode.class;
	}

}
