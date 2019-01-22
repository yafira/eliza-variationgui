
package sortgui;

import java.util.Scanner;
public class QuestionBank {

	static String [] questions= new String[13];
	private static int currQuestionIndex = -1;

	public QuestionBank() {
	}

	public static void populateQuestions() {

		questions[0]= "Which common saying or phrase describes you?";
		questions[1]= "What was your favorite subject at school?";
		questions[2]= "What’s the best thing that’s happened to you this week?";
		questions[3]= "Who was your role model when you were a child?";
		questions[4]= "What are you most grateful for?";
		questions[5]= "What is your greatest strength?";
		questions[6]= "What did you want to be when you grew up?";
		questions[7]= "If you had one superpower what would it be?";
		questions[8]= "If you could have one wish come true what would it be?";
		questions[9]= "Where is your dream destination?";
		questions[10]= "If you could live in another planet, which would it be?";
		questions[11]= "Which would you prefer — three wishes over five years or one wish right now?";
		questions[12]= "Do you believe in Aliens?";
		
	}
	
		public static String getNextQuestion() {
			
				if (currQuestionIndex < 12) {
					currQuestionIndex++;
					populateQuestions();
					//System.out.println("the current index is " + currQuestionIndex);
					
					return ( questions[currQuestionIndex] );
				}
				
				else {
					MyJPanel.endQuestioning();
					return ( "<html>Thank you, this is the end of this session. Now check your results.<br>If you wish to continue click Start Next Session.</html>" );
				}

		}
		
		public static void restartQuestions() {
			currQuestionIndex = -1;
		}

}
