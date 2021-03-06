/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.util.Scanner;

import java.awt.*;     
import java.awt.event.*;
import javax.swing.*;    

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import java.util.Random;

/**
 *
 * @author S331385567
 */
public class HangmanGame extends JApplet{

    private static String[] LevelWord;
    private static String[] Level1Word;
    private static String[] Level2Word;
    private static String[] Level3Word;
    private static String [] correctGuess;
    private static String [] letterGuess;
    private static int level = 1;
    
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 100;
    private static JFXPanel fxContainer;
    
    private static Container cp;
    private static JApplet hangmanapplet;
    private static JButton submitbtn;
    private static JTextField textField;
    private static JTextArea output;

    /**
     * @param args the command line arguments
     */
    public HangmanGame(String x) {
        this.Level1Word = Level1List();
        this.Level2Word = Level2List();
        this.Level3Word = Level3List();
        
    }
    
    public HangmanGame() {
        DrawMan canvas = new DrawMan();
        canvas.setPreferredSize(new Dimension(500, 400));
        JPanel btnPanel = new JPanel(new FlowLayout());
        
        textField = new JTextField(15);
        textField.setFont(textField.getFont().deriveFont(20f));
        textField.setText("Enter guess here"); // seems to stay as a constant value
        textField.setEditable(true);
        
        output = new JTextArea(5, 15);
        
        //TextFieldListener tfListener = new TextFieldListener();

        btnPanel.add(textField);
        btnPanel.add(output);
      
        submitbtn = new JButton("Submit");
        btnPanel.add(submitbtn);
        
        
        submitbtn.addActionListener(new ActionListener() {
            @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            String input = output.getText(); // text remains as initial value
            System.out.println("*"+input + "*");
         }
        });
        // The listener for the textfield.
   /*private class TextFieldListener implements ActionListener
   {  public void actionPerformed(ActionEvent evt)
      {  String inputString = textField.getText();
         output.append(inputString + "\n");
         textField.setText("");
      }
   }*/
        
        // Add both panels to this JFrame's content-pane
        cp = getContentPane(); //getContentPane(); // new Container();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);
        cp.add(btnPanel, BorderLayout.SOUTH);
        String userGuessInput = textField.getText();
        System.out.println(userGuessInput);
        
    }
    
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {

            //HangmanGame man = new HangmanGame();

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                JFrame frame = new JFrame("JavaFX 2 in Swing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JApplet applet = new HangmanGame("initializing");
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                applet.start();
            }
        });
    }

    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                createScene();
            }
        });
    }
    

    private void createScene() { // needs to be formatted if adding more buttons
        JPanel addPanel = new JPanel();
        //Button btn = new Button();
        
        //btn.setText("Instructions");
        //btn.setLocation(12, 371);
              //addPanel.setLayout(null);
        //btn.setBounds(40,100,100,60);
    
        //addPanel.add(btn);

        Button btn2 = new Button();
        btn2.setText("Play Game");
        
        StackPane root = new StackPane();
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                root.getChildren().remove(btn2);
                playGame();
            }
         
        });
        

        //btn..setBounds(40, 100, 100, 60);
        //btn2.setBounds(40, 100, 100, 60);

        //root.getChildren().add(btn); //add(topPanel, BorderLayout.NORTH);
        root.getChildren().add(btn2);
        fxContainer.setScene(new Scene(root));
    }
    
    // code only works for words with no repetition of letters
    public String[] Level1List() {
        Random rand = new Random();
        int n = rand.nextInt(3); // 4 options (0 to 3)
        String[] arrayWord = new String []{"s", "o", "f", "t", "w", "a", "r", "e"}; // if n==0
        switch (n){
            case 1: 
                arrayWord = new String[] {"c", "o", "m", "p", "u", "t", "e", "r"};
                break;
            case 2: 
                arrayWord = new String[]{"c", "o", "d", "i", "n", "g"};
                break;
            case 3: 
                arrayWord = new String[]{"m", "e", "t", "h", "o", "d"};
                break;
        }
        return arrayWord;
    }

    public String[] Level2List(){ 
        Random rand = new Random();
        int n = rand.nextInt(3); // 4 options (0 to 3)
        String[] arrayWord = new String []{"c", "h", "e", "m", "i", "s", "t", "r", "y"}; // if n==0
        switch (n){
            case 1: 
                arrayWord = new String[] {"e", "n", "g", "l", "i", "s", "h"};
                break;
            case 2: 
                arrayWord = new String[]{"s", "c", "h", "o", "l", "a", "r"};
                break;
            case 3: 
                arrayWord = new String[]{"m", "a", "t", "h"};
                break;
        }
        return arrayWord;
    }

    public String[] Level3List() {
        Random rand = new Random();
        int n = rand.nextInt(3); // 4 options (0 to 3)
        String[] arrayWord = new String []{"a", "l", "g", "o", "r", "i", "t", "h", "m"}; // if n==0
        switch (n){
            case 1: 
                arrayWord = new String[] {"g", "i", "t", "h", "u", "b"};
                break;
            case 2: 
                arrayWord = new String[]{"o", "b", "j", "e", "c", "t"};
                break;
            case 3: 
                arrayWord = new String[]{"e", "n", "u", "m"};
                break;
        }
        return arrayWord;
    }
    
    /*public String enterGuess (){ 
        Scanner scanner = new Scanner(System.in);
        
        String userGuess = textField.getText();
        while(userGuess!=null) {
            System.out.println(userGuess);
            if (userGuess.isEmpty()) {
                System.out.println("Read Enter Key.");
            }
            if (scanner.hasNextLine()) { // doesn't get called
                //userGuess = scanner.nextLine();
                userGuess = textField.getText();
            } else {
                //userGuess = null;
                userGuess = textField.getText();
            }
            //int keycode = e.getKeyCode();
            //if (HangmanGame.isWPressed()) {
              
            //}
        }
        return userGuess;
    }*/
    
    public void keyPressed(KeyEvent e){
        String key = e.getKeyText(e.getKeyCode());
        if(key.equals("Space")){
            System.out.println("works");
            String userGuess = textField.getText();
            System.out.println(textField);
        }
        
    }
    
    public void playGame(){
        
        
        String userinput;
        char guess;
        int numCorrect = 0;
        int numGuessed = 0;
        
        switch (level){
            case 1: LevelWord = Level1Word;
                    break;
            case 2: LevelWord = Level2Word;
                    break;
            case 3: LevelWord = Level3Word;
                    break;
        }
        
        letterGuess = new String[26]; // for each of the letters
        correctGuess = new String [LevelWord.length] ;
        for (int k=0; k<correctGuess.length; k++){
            correctGuess[k] = "*"; // set all initial values to *
        }
        
        Scanner input = new Scanner(System.in);
        
        JFrame hangmanframe = new JFrame ("Hangman Level " + level);
        hangmanframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hangmanapplet = new HangmanGame();
        hangmanapplet.init();
        hangmanframe.setContentPane(hangmanapplet.getContentPane());
        DrawMan h = new DrawMan(LevelWord, correctGuess, level);
        hangmanframe.add(h);
        hangmanframe.setSize(500, 500);
        hangmanframe.setVisible(true);
        
        HangmanGame man = new HangmanGame();
        //System.out.println(enterGuess ()); 
       
        
        
        /*submitbtn.addActionListener(new ActionListener(){ // NOT WORKING
            //@Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Do Something Clicked");
            }
        });*/
        
        /*submitbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("Do Something Clicked");
            }
        });*/
        
        
        //submitbtn.addActionListener(this);
        
        
        
        while((h.livesLeft>0) && (numCorrect<LevelWord.length)){
            
            h.repaint();
            
            // this line needs to be changed so guess equals value entered in text field
            userinput = input.nextLine();
            guess = userinput.charAt(0);
            
            if ((Character.isLetter(guess) == false)|| (userinput.length()!= 1) ) { // if invalid input
                h.check = 3;
                continue;
            }
            
            h.check = checkifcorrect(guess);
            if (h.check==1){
                correctGuess[numCorrect] = Character.toString(guess);
                letterGuess[numGuessed] = Character.toString(guess);
                numCorrect+= 1;
                numGuessed+= 1;
                h.wordGuess = correctGuess;
            }else if (h.check==0){ 
                letterGuess[numGuessed] = Character.toString(guess); // won't do if check == 2
                numGuessed+= 1;
                h.livesLost += 1 ;
                h.livesLeft = 6 - h.livesLost ;
            }
            
            //h.requestFocus(); // works without it
            
        }
        
        if (numCorrect==LevelWord.length){
            System.out.println("YOU WIN");
            h.win = true;
            h.repaint(); 
            if (level<3){
                level ++;
                hangmanframe.setVisible(false);
                playGame();
            }
        }
        
    }
    
    
    
    public static int checkifcorrect(Character userguess){
        String userguessString = Character.toString(userguess);
        int isitcorrect = 0;
        
        for (int y=0; y<LevelWord.length; y++){
            if (userguessString.equalsIgnoreCase(LevelWord[y])){
                isitcorrect = 1;
              }
        }
        
        for (int z=0; z<letterGuess.length; z++){
            if (userguessString.equalsIgnoreCase(letterGuess[z]) ){ // if not already guessed and correct
                 isitcorrect = 2;
                 break;
                 // shouldn't draw body part if here
             }
        }
        
        return isitcorrect;
        
    }

}
