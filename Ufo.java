import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Ufo extends Subject implements Runnable, Observer{

    private int x = -200;
    private int y = Screen.UFO_Y;
    static int w = Screen.UFO_W;
    static int h = Screen.UFO_H;
    GamePanel gp;
    PlayClip pc;

    int dir = Screen.DIR_R;
    boolean life = false;

    Icon icon1 = new ImageIcon("images/ufo.png");

    Icon icon2 = new ImageIcon("images/invader-bang.png");


    Ufo(GamePanel gp){
        this.gp = gp;
        setBounds(x, -200, w, y);
        setIcon(icon1);
        setOpaque(false);
    }

    public void run(){
      int cnt = 0;
      //pc = new PlayClip("BGM/ufo.wav");
      //pc.play();
      try{Thread.sleep(5000);}catch(Exception e){}

        for(;;){
            if(x < Screen.RIGHT && dir == 0) {
                if(cnt == 0){
                  pc = new PlayClip("BGM/ufo.wav");
                  pc.play();
                  cnt++;
                }
                x += 25;
            }else if(x >= Screen.RIGHT && dir == 0){
                if(cnt == 1){
                  try{Thread.sleep(5000);}catch(Exception e){}
                  pc.setFramePosition();
                  cnt--;
                }
                dir = (int)(Math.random()*2);
                if(dir == 0){
                    x = -200;
                }else{
                    x = 1300;
                }
            }else if(x > -200 && dir == 1) {
                if(cnt == 0){
                  pc.play();
                  cnt++;
                }
                x -= 25;
            }else if(x <= -200 && dir == 1){
                if(cnt == 1){
                  try{Thread.sleep(5000);}catch(Exception e){}
                  pc.setFramePosition();
                  cnt--;
                }
                dir = (int)(Math.random()*2);
                if(dir == 0){
                    x = -200;
                }else{
                    x = 1300;
                }
            }else if(dir == 2){
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                x = -400;
                dir = Screen.DIR_R;
                setIcon(icon1);
            }
            setLocation(x, y);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            notifyObservers();
        }
    }
    public void update(Subject s){
        int mx = ((HoudaiMissile)s).getX();
        int my = ((HoudaiMissile)s).getY();

        if(y >= my-50 && y-50 <= my-50){
            if(x <= mx && x+100 >= mx) {
               // System.out.printf("【UFO】(%d,%d)¥n", mx, my);

                pc = new PlayClip("BGM/bang.wav");
                pc.play();

                setIcon(icon2);
                gp.addScore(Screen.SCORE_UFO);
                ((HoudaiMissile)s).x = -100;
                ((HoudaiMissile)s).y = -100;
                dir = 2;
            //    it.moveDown(this.x+50);
            }
        }
    }
}
