/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author S331385567
 */

public class DrawMan extends JPanel {
    
    public static int livesLeft;
    public static int livesLost; // would indicate number of body parts drawn
    
    public static String[] wordAns;
    public static String[] wordGuess;
    
    public static int check;
    
    public Canvas canvas; // The custom drawing canvas
    
    public static boolean win;
    
    public static int levelGraphics = 1;
    
    
    // need to somehow make this run when its called
    // and have the word and number of guesses as parameters
    public DrawMan (){
        // start
    }
    public DrawMan (String[] wordArray, String[] correctArray, int lev){
        this.livesLeft = 6;
        this.livesLost = 0;
        this.win = false;
        this.wordAns = wordArray;
        this.wordGuess = correctArray;
        this.check = -1;
        this.levelGraphics = lev;
    
    }
    public void paintComponent (Graphics g){ 
        
        super.paintComponent(g);
        
        this.setBackground(Color.WHITE);
        
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.drawString("Guess a letter:", 150, 25); // number of spaces as word
        
        int x = 50;
        int y = 100;
        man(g, x, y);
        
    }
    
    public static void man(Graphics g2, int x, int y){
        // where hangman 'hangs' from
        g2.drawLine(10, 400, 10, 10);
        g2.drawLine(x+50, 10, 10, 10);
        g2.drawLine(x+50, 10, x+50, y+50);
        
        // writes out word to be guessed ("_" if unknown, letter if guessed correctly)
        boolean drawn = false;
        for (int m=0; m<wordAns.length; m++){
            drawn = false;
            for (int n=0; n<wordGuess.length; n++){
                 if (wordGuess[n].equalsIgnoreCase(wordAns[m]) ){
                     g2.drawString(wordAns[m], (150 + 20*m), 200); 
                     drawn = true;
                }
             }
             if (!drawn){
                 g2.drawString("_", (150 + 20*m), 200);
             } 
        }
        
        // man
        // are seperate if statements so each item is run
        if (livesLost>=1){
            g2.fillOval (x, y, 100, 100);  
        }
        if (livesLost>=2){
            g2.drawLine(x+50, y+50, x+50, y+200);
        }
        if (livesLost>=3){
            g2.drawLine(x, y+150, x+50, y+150);
        }
        if (livesLost>=4){
            g2.drawLine(x+100, y+150, x+50, y+150);
        }
        if (livesLost>=5){
            g2.drawLine(x+50, y+200, x, y+300);
        }
        if (livesLost>=6){
            g2.drawLine(x+50, y+200, x+100, y+300);
            g2.drawString("GAME OVER", 150, 100);
            g2.drawString("The word was: " + getWord(), 150, 150); // put word here
        }
        
        if (win){
            g2.drawString("YOU WIN", 150, 100);
        }else if (check==1){
            g2.drawString("correct", 150, 100);
        }else if (check==2){
            g2.drawString("you already guessed this", 150, 100);
        }else if (check==3){
            g2.drawString("invalid guess", 150, 100);
        }else if (check==0){
            g2.drawString("incorrect", 150, 100);
        }
        if (win && (levelGraphics>=3) ){
            g2.setColor(Color.BLUE);
            g2.fillRect(0, 0, 400, 400);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            g2.drawString("YOU WON ALL 3 LEVELS", 0, 200);
        }
        
    }
    
    public static String getWord(){ // just converts array of wordAns to string value
        String answer = "";
        for (int j=0; j<(wordAns.length); j++){
            answer = answer+wordAns[j];
            
        }
        return answer;
    }
}
