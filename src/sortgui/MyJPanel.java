package sortgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class MyJPanel extends JPanel implements ActionListener{
	
	private static JPanel jpTop;
	private JPanel jpTopButtons;
	private static JPanel jpBottom;
	private static JPanel jpOutput;
	
	private static JTextField jtf;
	private static JButton startBtn;
	private static JButton nextBtn;
	private static JButton sessionBtn;

	private static JButton finishBtn;
	private JButton jbtn1;
	private JButton jbtn2;
	private static JLabel jheading;
	
	private static ArrayList<String> allAnswers= new ArrayList<String>();
	private static String[] textFromFieldSplit;
	private static TextFileHandler handler = new TextFileHandler();
	
	private static JTextArea textArea;
	private static JScrollPane jscrollPane = new JScrollPane(textArea);
	private static JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private static int session = 1;
	private static int questionNum = 0;

	
	public MyJPanel(){
		JPanel mainJP = new JPanel();
		mainJP.setBackground(Color.BLACK);
		mainJP.setForeground(Color.WHITE);
		
		setLayout(new GridLayout(1,1));
		
		// TOP PANEL
		jpTop = new JPanel();
		jpTop.setLayout(new GridLayout(3,1));
		jpTop.setBackground(Color.BLACK);
		
		
		
		jheading = new JLabel("<htmL>Hello, I am ELIZA. <br>Welcome to the Eliza Therapy Session. <br>Click Start Session to continue.</html>");
		jheading.setForeground(Color.GREEN);
		
		Font font = new Font("Courier", Font.PLAIN ,14);
		jheading.setFont(font);
		
		jpTop.add(jheading);
		
		// TEXT FIELD
		jtf = new JTextField(10);
		jpTop.add(jtf);
		jtf.setVisible(false);

		
		// BUTTONS
		jpTopButtons = new JPanel();
		jpTopButtons.setLayout(new GridLayout(1,2));
		jpTopButtons.setBackground(Color.BLACK);
		
		startBtn = new JButton("Start Session");
		startBtn.addActionListener(this); 
		jpTopButtons.add(startBtn);
		
		nextBtn = new JButton("Next Question");
		nextBtn.addActionListener(this); 
		jpTopButtons.add(nextBtn);
		nextBtn.setVisible(false);

		sessionBtn = new JButton("Start Next Session");
		sessionBtn.addActionListener(this); 
		jpTopButtons.add(sessionBtn);
		sessionBtn.setVisible(false);
		
		finishBtn = new JButton("Finish Session");
		finishBtn.addActionListener(this); 
		jpTopButtons.add(finishBtn);
		finishBtn.setVisible(false);
	
		jpTop.add(jpTopButtons);
		
		// BOTTOM PANEL
		jpBottom = new JPanel();
		jpBottom.setLayout(new GridLayout(1,1));
		jpBottom.setBackground(Color.BLACK);
		
		// BUTTONS
		jbtn1 = new JButton("View Longest Words");
		jbtn1.addActionListener(this); 
		jpBottom.add(jbtn1);
		
		jbtn2 = new JButton("View Longest Words Alphabetically");
		jbtn2.addActionListener(this); 
		jpBottom.add(jbtn2);
		
		jpBottom.setVisible(false);
		
		JScrollBar vertical = jscrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );

		
		//OUTPUT SECTION	
		jpOutput = new JPanel();
		jpOutput.setLayout(new GridLayout(1,1));

		
		//ADD PARTS TO PANEL
		add(mainJP);
		mainJP.add(jpTop);
		mainJP.add(jpBottom);
		
		textArea = new JTextArea(35,35);
		jscrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		mainJP.add(jpOutput);
		jpOutput.add(jscrollPane);


		jpOutput.setVisible(false);
		jscrollPane.setVisible(false);
		textArea.setVisible(false);


		handler.createNewFile("project2.txt");
		

		
	}
	
	
	
		// ACTIONS PERFORMED WHEN BUTTONS ARE CLICKED.
	
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton jbtnClicked = (JButton) e.getSource();
			String textFromField = jtf.getText();
		
		
		
		// WHEN SESSION IS STARTED.
		
		if(jbtnClicked.getText().equals("Start Session")){
			jheading.setText(QuestionBank.getNextQuestion());
			//startBtn.setText("Next Question");
			finishBtn.setVisible(true);
			nextBtn.setVisible(true);
			jpTopButtons.remove(startBtn);
			jtf.setText("");
			jtf.setVisible(true);
			//System.out.println("counter");
		}
		
		
		
		
		// NEXT QUESTION IS CLICKED
		
		else if(jbtnClicked.getText().equals("Next Question")){
			questionNum ++;
			finishBtn.setVisible(true);
			
			logOnFile(jheading.getText(), textFromField);
			
			splitAnswer(textFromField);
			
			jheading.setText(QuestionBank.getNextQuestion());
			jtf.setText("");
			
		}
		
		if(jbtnClicked.getText().equals("Start Next Session")){
			session ++;
			QuestionBank.restartQuestions();
			jheading.setText(QuestionBank.getNextQuestion());
			jtf.setText("");
			jtf.setVisible(true);
			jpBottom.setVisible(false);

			
			sessionBtn.setVisible(false);
			nextBtn.setVisible(true);
			finishBtn.setVisible(true);
			
			jpOutput.setVisible(false);
			jscrollPane.setVisible(false);
			textArea.setVisible(false);
		}
		
		// WHEN SESSION FINISHES
		
		else if(jbtnClicked.getText().equals("Finish Session")){
			
			
			questionNum ++;
			
			logOnFile(jheading.getText(), textFromField);
			
			splitAnswer(textFromField);
			
			sessionBtn.setVisible(true);
			nextBtn.setVisible(false);
			jheading.setText("<html>Thank you, this is the end of this session.<br>Now check your results or click Start Next Session to continue.</html>");
			//shortestLongest();
			QuestionBank.restartQuestions();
			endQuestioning();
			
		}
		
		
		
		
		else if(jbtnClicked.getText().equals("View Longest Words")){
			longestWord();
		}
		
		else if(jbtnClicked.getText().equals("View Longest Words Alphabetically")){
			alphabeticalOrder();
		}
		
		
		
	}
	
	
	
	// METHODS THAT PROCESS INFO 
	
	public static void endQuestioning() {
		finishBtn.setVisible(false);
		nextBtn.setVisible(false);
		sessionBtn.setVisible(true);
		
		jtf.setText("");
		jtf.setVisible(false);
		//startBtn.setVisible(true);
		jpBottom.setVisible(true);
		
		jpOutput.setVisible(true);
		jscrollPane.setVisible(true);
		textArea.setVisible(true);
		
		shortestLongest();
		//startBtn.setText("Start Next Session");
		QuestionBank.restartQuestions();
		questionNum=0;
	}
	
	public static void splitAnswer(String textFromField) {
		textFromFieldSplit = textFromField.split(" ");
		
		for (int x=0; x< textFromFieldSplit.length ; x++) {
				allAnswers.add( textFromFieldSplit[x] );
				//System.out.println("allAnswers contains: "+ allAnswers );
		}
	}
	
	
	
	// METHODS THAT WORK WITH ARRAY LISTS	
	
	public static void logOnFile(String question, String textFromField){
		String logLineQuestion =  question;
		String logLineAnswer = textFromField;

		//text that goes in the file
		handler.appendToFile("project2.txt", "Session: "+ session);
		handler.appendToFile("project2.txt", "Question: "+ questionNum);
		handler.appendToFile("project2.txt", "Question: " + logLineQuestion);
		handler.appendToFile("project2.txt",  "Answer:  " + logLineAnswer);
		handler.appendToFile("project2.txt", " ");
		
		// text that goes in the GUI
		textArea.append("Session "+ session + "\n" + "Q"+ questionNum +": " + logLineQuestion + "\n"+"Answer:" + logLineAnswer+ "\n" + " \n" );
		
	}

	public static void longestWord () {
		allAnswers.sort(Comparator.comparing(String::length).reversed());
		String logLineLongest = ""+ allAnswers;
		//text that goes in the file
		handler.appendToFile("project2.txt", "Session: "+ session);
		handler.appendToFile("project2.txt", "All answers from longest to shortest: ");
		handler.appendToFile("project2.txt", logLineLongest);
		handler.appendToFile("project2.txt", " ");
		
		// text that goes in the GUI
		textArea.append("Session "+ session + "\n" + "All answers from longest to shortest: " + "\n" + logLineLongest+ "\n" + " \n" );

	}

	public static void alphabeticalOrder() {
		String alphabeticalPrint ="";
		Collections.sort(allAnswers);

		//System.out.println("ArrayList is sorted");
		for(String temp: allAnswers){
			alphabeticalPrint = alphabeticalPrint +" "+ temp;  
		}
		
		
		String logLineAlphabetical = ""+ alphabeticalPrint;
		
		handler.appendToFile("project2.txt", "Session "+ session);
		handler.appendToFile("project2.txt", "All answers in alphabetical order: ");
		handler.appendToFile("project2.txt", logLineAlphabetical);
		handler.appendToFile("project2.txt", " ");

		textArea.append("Session: "+ session + "\n" + "All answers in alphabetical order: " + "\n" + logLineAlphabetical+ "\n" + " \n" );
		
	}
	
	public static void shortestLongest() {
		allAnswers.sort(Comparator.comparing(String::length).reversed());
		String longestWord = allAnswers.get(0);
		String shortestWord = allAnswers.get(allAnswers.size() - 2);


		System.out.println( "the longest word is " + longestWord );
		System.out.println( "the longest word is " + shortestWord );
		
		
		handler.appendToFile("project2.txt", "Session "+ session);
		handler.appendToFile("project2.txt", "The longest word in this session is: " + longestWord);
		handler.appendToFile("project2.txt", "The shortest word in this session is: " + shortestWord);
		handler.appendToFile("project2.txt", " ");
		
		textArea.append("* * * * * * * * * * * * * * * *"+ "\n" +"ANALYSIS FOR SESSION "+ session +  ": WOW! " 
		+ shortestWord + " and " + longestWord + " must mean a lot to you.\n" + "* * * * * * * * * * * * * * * *"+" \n"+" \n"+" \n" );
		
	}
}






