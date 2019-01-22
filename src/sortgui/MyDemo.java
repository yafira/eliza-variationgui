package sortgui;

import sortgui.ElizaGUI;

public class MyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(
				new Runnable(){
					@Override
					public void run() {
						ElizaGUI gui = new ElizaGUI();
						
					}
			
		});
	}

}