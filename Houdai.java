import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


//-----------------
//砲台
//-----------------
class Houdai extends JLabel implements Observer {

    //ｘ座標
    static int x = 550;
    static int y = Screen.BOTTOM - 210;

    HoudaiMissile hm;
    GamePanel gp;
    Houdai hd;
    PlayClip pc;
    ImageIcon icon1 = new ImageIcon("images/jet.png");
    ImageIcon icon2 = new ImageIcon("images/jet-bang.png");

    //コンストラクタ
    Houdai(HoudaiMissile hm,GamePanel gp) {

        this.hm = hm;
        this.gp = gp;

        //座標、サイズ
        setBounds(x, y, 100, 50);

        //画像貼りつけ
        ImageIcon icon1 = new ImageIcon("images/jet.png");
        setIcon(icon1);

    }

    /*void setLife(Boolean l){
        life = l;
    }*/

    //移動メソッド
    void moveRight() {
        if (x < Screen.RIGHT - 110) {
            x += 15;
            setLocation(x, y);
        } else if (x >= Screen.RIGHT - 110) {
            x = Screen.RIGHT - 110;
            setLocation(x, y);
        }

    }

    void moveLeft() {
        if (x > 0) {
            x -= 15;
            setLocation(x, y);
        } else if (x <= 0) {
            x = 0;
            setLocation(x, y);
        }
    }

    //発射メソッド
    void hasha() {
      pc = new PlayClip("BGM/missile.wav");
      pc.play();
      hm.moveUp(this.x + 25, this.y);
    }

    public void update(Subject s) {
        int imx = ((InvaderMissile) s).getX();
        int imy = ((InvaderMissile) s).getY();
        //当たったか判定
        //System.out.println("x:" + hd.x + "y:" + hd.y );
        if (Screen.hitCheck4(imx, imy, hd.x, hd.y, Screen.HOUDAI_W, Screen.HOUDAI_H) == true) {
            ((InvaderMissile) s).x = -300;
            ((InvaderMissile) s).y = 900;
            //System.out.println("HIT");
            //lifeフラグセット
            //i.setLife(false);

            pc = new PlayClip("BGM/houdai.wav");
            pc.play();

            setIcon(icon2);
            setBounds((this.x + 20),this.y,50,50);

            gp.killLife(1);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            setIcon(icon1);
            setBounds(this.x,this.y,100,50);
            //i.des();

        }
    }
}
