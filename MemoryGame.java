/* MemoryGame.java
 * Brendan Galvin, Mahith Chitrapu, Brody Massad, Aidan Morgan
 * Mr.Blondin
 * Java II - Graphics
 * 12/1/20
 */
 
  import javax.swing.*;
  import java.awt.*;
  import java.awt.event.*;
  import java.util.ArrayList;
 
  public class MemoryGame extends JFrame implements ActionListener{
 
  JPanel backgroundPnl = new JPanel();
  JPanel titlePnl = new JPanel();
  JPanel messagePnl = new JPanel();
  JPanel gamePnl = new JPanel();
  JPanel scorePnl = new JPanel();
 
  JLabel titleLbl = new JLabel("Brendan's Memory Game");
  JLabel messageLbl = new JLabel("Let's Play!");
  JLabel matchLbl = new JLabel("Matches: ");
  JLabel attemptLbl = new JLabel("Attempts: ");
  JLabel accuracyLbl = new JLabel("Accuracy: ");
 
  JButton startButton = new JButton("Start");
  JButton [] button = new JButton[16];
 
  ImageIcon original = new ImageIcon("images/imageOriginal.gif");
 
  ArrayList<String> images = new ArrayList<String>();
  ArrayList<ImageIcon> gameImages = new ArrayList<ImageIcon>();
  
  int actions = 1;
  String previousActionOne = " ";
  String previousActionTwo = " ";
  int indexOne = 0;
  int indexTwo = 0;
  int count = 3;
  boolean first;
  double matchCounter = 0;
  double attemptCounter = 0;
  double accuracy = 0;
 
  public MemoryGame(){
 
  	backgroundPnl.setBackground(Color.WHITE);
  	backgroundPnl.setLayout(new BorderLayout());
  	add(backgroundPnl);
 	
  	createImages();
 
  	// title panel
  	titlePnl.setBackground(Color.GREEN);
  	titlePnl.add(titleLbl);
  	backgroundPnl.add(titlePnl, BorderLayout.NORTH);
 
  	// message label
  	messagePnl.add(messageLbl);
  	backgroundPnl.add(messagePnl, BorderLayout.SOUTH);
  	messagePnl.setBackground(Color.GREEN);
 
 
  	// score panel
  	scorePnl.setLayout(new GridLayout(5,1,2,2));
  	backgroundPnl.add(scorePnl,BorderLayout.EAST);
 	scorePnl.setBackground(Color.GREEN);
  	scorePnl.add(startButton);
  	scorePnl.add(attemptLbl);
  	scorePnl.add(matchLbl);
  	scorePnl.add(accuracyLbl); // displays the percent of correct to attempts
  	startButton.addActionListener(this);
   
  	// game panel
  	makeButtons();
  	backgroundPnl.add(gamePnl);
  	gamePnl.setBackground(Color.LIGHT_GRAY);
  	gamePnl.setLayout(new GridLayout(4, 4, 2, 2));
 
 
  	// container settings
  	setTitle("MemoryGame - Galvin");
  	setSize(520, 410);
  	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	setVisible(true);
  	disable();
 
  	}
 
  	public void makeButtons(){
  		for (int i = 0; i<16; i++){
  			button[i] = new JButton(original);
  			button[i].addActionListener(this);
  			gamePnl.add(button[i]);
  		}
  	}
  	public void disable(){
  		for(int i = 0; i<button.length; i++){
  			button[i].setEnabled(false);
  		}
 	}
 	public void createImages(){
  		for (int i = 0; i<8; i++){
  			for (int j = 0; j<2; j++){
  				images.add("images/image" + i + ".gif");
  			}
 		}
  		System.out.println (images);
 
  		while(images.size() > 0){
  			int index = (int)(Math.random() * images.size()); // generates a random number from zero to one and multiplies it by the size of itself
  			gameImages.add(new ImageIcon(images.remove(index))); // "image2.gif"
  		}
 		System.out.println (images);
  		System.out.println (gameImages);
  	}
  public void actionPerformed(ActionEvent e){
  	Object source = e.getSource();
  	if(source == startButton){
  		System.out.println("About to start");
  		startGame();
  	}
  	for (int i = 0; i<button.length; i++){
  		if(source == button[i]){
  			// gameImages.get(i).getDescription() - this will return a String
  			System.out.println (gameImages.get(i).getDescription());
  			actions++;
  			System.out.println(actions);
 			//checkFlip(actions);
 
  		if(actions % 2 != 0){
  			previousActionTwo = gameImages.get(i).getDescription();
  			indexTwo = i;
  		}
  		else{
 			checkFlip(actions);
  			previousActionOne = gameImages.get(i).getDescription();
  			indexOne = i;
  		}
  		button[i].setIcon(gameImages.get(i)); // this is an imageIcon
  		}
 	}
  }
  int countEnable = 0;
    // Method for Tracing How Many Times it has flipped (Brody)
    public void checkFlip(int times){
      System.out.println("I1: " + indexOne);
      System.out.println("I2: " + indexTwo);
      attemptCounter++;
      attemptLbl.setText("Attempts: " + attemptCounter);
      if(times % 2 == 0){
      	if(previousActionOne.equals(previousActionTwo) && button[indexOne]!=button[indexTwo]){
      		matchCounter++;
      		matchLbl.setText("Matches: " + matchCounter);
      		accuracy = matchCounter/attemptCounter * 100;
      		accuracyLbl.setText("Accuracy: " + Math.round(accuracy) + "%");
      		// Remove JButton at certain indexes which are a match and display the greyed out image and remove the buttons functionality
      		button[indexOne].setEnabled(false);
            button[indexTwo].setEnabled(false);
            countEnable++;
           
      	}
      	else{
      	unFlip();
      	accuracy = matchCounter / attemptCounter * 100;
      	accuracyLbl.setText("Accuracy: " + Math.round(accuracy) + "%");
      	}
      }
     
    }
    //Method for unFlipping
  	public void unFlip(){
          button[indexOne].setIcon(original);
          button[indexTwo].setIcon(original);  
    }
    boolean started = false;
    public void startGame(){
    	System.out.println("Starting");
    	if(!started){
    		System.out.println("Passed Start check");
    		for(int i = 0; i<button.length; i++){
    			button[i].setEnabled(true);
    		}
    		started = true;
    		startButton.setText("Stop");
  		}
   		else{
    		disable();
    		stop();
    		startButton.setText("Start");
    		clearImages();
    		createImages();
    		started = false;
   		}
	}
	public void stop(){
		for(int i = 0; i<button.length; i++){
			button[i].setIcon(original);
		}
	}
	public void clearImages(){
		images.clear();
		gameImages.clear();
	}
  	public static void main (String [] args){
  		MemoryGame game = new MemoryGame();
  	} // end main method
  } // end class MemoryGame

