import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


//-----------------
//砲台ミサイル
//-----------------
class HoudaiMissile extends Subject implements Runnable,Observer{

	 //ｘ座標
	 int x = -100;
	 int y = 900;

	 private boolean flag;

	 //コンストラクタ
	 HoudaiMissile(){

	 		//座標、サイズ
	 		setBounds(x,y,45,70);

	 		//画像貼りつけ
	 		setIcon(new ImageIcon("images/missile2.png"));

		}

	//移動メソッド
	void moveUp(int hx, int hy) {
	    if(x == -100 && flag == true) {
            x = hx;
            y = hy;
        }
	}

    public void run(){
	    for(;;){
	        if(y >= 0 && x > 0 && flag == true) {
                y -= 10;
            }else if(y <= 0 || flag == false) {
                x = -100;
                y = -100;
                flag = true;
            }
            setLocation(x, y);
            try{
                Thread.sleep(10);
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
    public void update(SubjectPanel sp){

    }
}
