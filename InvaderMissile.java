import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


//-----------------
//砲台ミサイル
//-----------------
class InvaderMissile extends Subject implements Runnable,Observer{


    //ｘ座標
    int x = -300;
    int y = 900;

    private boolean flag = true;


    //コンストラクタ
    InvaderMissile(){

        //座標、サイズ
        setBounds(x,y,50,50);

        //画像貼りつけ
        setIcon(new ImageIcon("images/invader-missile.png"));


    }

    //移動メソッド
    void moveDown(int ix, int iy) {
        if(x == -300 && flag == true){
            x = ix;
            y = iy;
            //System.out.println("x:"+x);
            //System.out.println("y:"+y);
        }
    }

    public void run(){
        for(;;){
            if(y <= Screen.BOTTOM && x >= 0 && flag == true) {
                y += 10;
            }else if(y >= Screen.BOTTOM || flag == false) {
                x = -300;
                y = -100;
                flag = true;
            }
            setLocation(x, y);
            try{
                Thread.sleep(100);
            }catch (Exception e){

            }
            notifyObservers();
        }
    }
    public void update(Subject s){
      /*  if(y <= 100){
            int ux = ((Ufo)s).getX();
            int uy = ((Ufo)s).getY();
            if(y <= uy+50 && y+100 >= uy){
                if(x + 100 >= ux && x <= ux + 100) {
                    System.out.printf("【HM】(%d,%d)¥n", ux, uy);
                    flag = false;
                }
            }
        }else{
            int ix = ((Invader)s).getX();
        }*/


    }



}
