package com.st.app.service;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotManipulator {
    private Robot bot;
    public RobotManipulator(Robot... bot){
        if(isObjectValid(Robot.class, bot )){
            this.bot = bot[0];
        }else{
            try{
                this.bot = new Robot();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void click(){
        this.bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        this.bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    public boolean isObjectValid(Class someClass, Object... optional ){
        try{
            if(optional.length < 0){
                return false;
            }
            if(someClass.isInstance(optional[0])){
                return true;
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
       
    }
    public void moveAndClick(Integer x, Integer y){
        this.bot.mouseMove(x, y);
        this.click();
    }
    public void write(String message, Integer... duration) throws Exception{
        Integer time = 0;
        if(isObjectValid(Integer.class, duration)){
            time = duration[0];
        }
        // Set duration if needed
        Integer timeToSleep =  time/message.length(); 
        for (int i = 0; i < message.length(); i++) {
            // Check if the current character is a capital letter
            if (Character.isUpperCase(message.charAt(i))) {
                // Press shift key
                this.bot.keyPress(KeyEvent.VK_SHIFT);
                // Press the current character
                this.bot.keyPress(Character.toUpperCase(message.charAt(i)));
                // Release shift key
                this.bot.keyRelease(KeyEvent.VK_SHIFT);
            }
            // else display the character as it is
            else this.bot.keyPress(Character.toUpperCase(message.charAt(i)));
            // wait for 200ms
            Thread.sleep(timeToSleep);
        }
    }
    public void getParametizedValue(Class someClass, Object... optional){

    }
    public void drag(Integer oriX, Integer oriY, Integer destX, Integer destY, Integer... duration){
        this.bot.mouseMove(oriX, oriY);

    }
}

