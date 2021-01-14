/*****************************************************************************
  * Author- Patrick Amons
  * Written- 2017/05/31
  * Last Updated- 2017/06/01
  * 
  *****************************************************************************/
import java.awt.event.KeyEvent;

public class FinalProject {
    public static void main(String[] args){   
        
//instruction screen
        StdDraw.text(.5,.6,"Instructions:");
        StdDraw.text(.5,.55,"Get a score of 10 to win the game");
        StdDraw.text(.5,.5,"Your starting score should be 5");
        StdDraw.text(.5,.45,"Try to catch the ball in order to gain a point");
        StdDraw.text(.5,.4,"Avoid the red ball or you will lose a point");
        
        while(true){
            
            if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
                StdDraw.clear();
                break;
            }  
        }
        
//variables    
        //array
        double[] hunterXarray = new double [100];
        double[] hunterYarray = new double [100];
        double[] hunterVX = new double [100];
        double[] hunterVY = new double [100];
        //booleans
        boolean run= true;
        boolean win=false;
        
        //int
        
        int score=5;
        int timer=0;
        int repeated=0;
        
        //double
        double radius=0.015;
        
        //player
        double playerX=StdDraw.mouseX();
        double playerY=StdDraw.mouseY();
        
        //hunter
        double hunterX= Math.random();
        double hunterY= Math.random();
        
        for(int i=0;i<3;i++){
            hunterXarray[i]= Math.random();
            hunterYarray[i]= Math.random();
            hunterVX[i] = (Math.random() * (.01 - .005)) + .005;
            hunterVY[i] = (Math.random() * (.01 - .005)) + .005;
        }
        
        //prey
        double preyX= Math.random();
        double preyY= Math.random();
        double preyVX=.03;
        double preyVY=.02;
        
        
        //game starting now
        while(run){
            
            StdDraw.clear();
            
            if(repeated==66){
                repeated=0;
                timer++;
            }
            
            //player  
            playerX=StdDraw.mouseX();
            playerY=StdDraw.mouseY();
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(playerX,playerY,radius);
            
            //hunter
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(hunterXarray[0],hunterYarray[0],radius);   
            
            
            //prey
            StdDraw.setPenColor((int)(Math.random()*255),
                                (int)(Math.random()*255),
                                (int)(Math.random()*255));
            StdDraw.filledCircle(preyX,preyY,radius);
            
            //adds clones of hunter
            
            if(score==6 || score==7 ||score==8){
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledCircle(hunterXarray[0],hunterXarray[0],radius);
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledCircle(hunterXarray[1],hunterXarray[1],radius);
            }
            
            if(score>8){
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledCircle(hunterXarray[0],hunterXarray[0],radius);
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledCircle(hunterXarray[1],hunterXarray[1],radius);
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledCircle(hunterXarray[2],hunterXarray[2],radius);
            }
            
            //score
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(.5,.5,"Score: " + score);
            StdDraw.text(.5,.45,"Timer: " + timer);
            
            
            //teleports the prey if player touches it
            if(Math.abs(playerX-preyX)<2*radius && 
               Math.abs(playerY-preyY)<2*radius){
                
                preyX= Math.random();
                preyY= Math.random();  
                score++;
            }
            
            //takes point away from score if player hits the hunter
            //work on this
            if(score==6||score==7||score==8){
                if((Math.abs(hunterXarray[0]-playerX)<2*radius && 
                    Math.abs(hunterYarray[0]-playerY)<2*radius)&&(score==6||score==7||score==8)){
                    hunterXarray[0]= Math.random();
                    hunterYarray[0]= Math.random();   
                    score--;
                }
                if((Math.abs(hunterXarray[1]-playerX)<2*radius && 
                    Math.abs(hunterYarray[1]-playerY)<2*radius)&&(score==6||score==7||score==8)){
                    hunterXarray[1]= Math.random();
                    hunterYarray[1]= Math.random();   
                    score--;
                }
                if((Math.abs(hunterXarray[0]-playerX)<2*radius && 
                    Math.abs(hunterYarray[0]-playerY)<2*radius)&&(score==6||score==7||score==8)){
                    hunterXarray[0]= Math.random();
                    hunterYarray[0]= Math.random();   
                    score--;
                }
            }
           
            //makes sure the object bounces when it hits the wall
            if(preyX + preyVX>1-radius || preyX+preyVX<0+radius){
                preyVX=-preyVX;
            }
            if(preyY + preyVY>1-radius || preyY+preyVY<0+radius){
                preyVY=-preyVY;
            }
            
            for(int i=0;i<3;i++){
                if(hunterXarray[i] + hunterVX[i]>1-radius || hunterXarray[i]+hunterVX[i]<0+radius){
                    hunterVX[i]=-hunterVX[i];
                }
                if(hunterYarray[i] + hunterVY[i]>1-radius || hunterYarray[i]+hunterVY[i]<0+radius){
                    hunterVY[i]=-hunterVY[i];
                }
            }
            
            //makes objects move
            preyX=preyX+preyVX;
            preyY=preyY+preyVY;
            
            for(int i=0;i<3;i++){
                hunterXarray[i]=hunterXarray[i]+hunterVX[i];
                hunterYarray[i]=hunterYarray[i]+hunterVY[i];
            }
            
            
            //ends the game if score reaches 0 or 10
            if(score==10){
                win=true;
                run=false;
            }
            if(score==0){
                win=false;
                run=false;
            }
            //counts how many times the program has run
            repeated++;
            
            StdDraw.show(10);
        }
        if(win=true){
            StdDraw.clear();
            StdDraw.text(.5,.55,"You Win");
            StdDraw.text(.5,.5,"Your time was: " + timer);
            StdDraw.show();
        }
        if(win=false){
            StdDraw.clear();
            StdDraw.text(.5,.55,"You Lose");
            StdDraw.show();
        }
        System.out.println(hunterXarray[0] + " " + hunterYarray[0]);
        System.out.println(hunterXarray[1] + " " + hunterYarray[1]);
        System.out.println(hunterXarray[2] + " " + hunterYarray[2]);
    }
}