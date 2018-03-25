import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//-----------------
//ゲーム画面
//-----------------
public class GamePanel extends JPanel implements KeyListener {

    GamePanel gp;
    Houdai hd;
    HoudaiMissile hm;
    Ufo ufo;
    InvaderPanel ip;
    InvaderMissile im[];
    WallPanel wp;
    BgImage bi;
    int ts;
    int lf = 3;
    JLabel score;
    JLabel life;

    ImageIcon title = new ImageIcon("images/gameover.png");
    ImageIcon back = new ImageIcon("images/ucyu-gameover.png");
    ImageIcon start = new ImageIcon("images/start-button.png");
    ImageIcon exit = new ImageIcon("images/exit-button.png");

    JLabel label1 = new JLabel(title);
    JLabel label2 = new JLabel(back);
    JButton button1 = new JButton(start);
    JButton button2 = new JButton(exit);


    public GamePanel() {

        add(label1);
        add(button1);
        add(button2);

        //背景生成
        bi = new BgImage();

        //レイアウトは自由
        setLayout(null);

        //サイズ設定
        setBounds(0, 0, Screen.WIDTH, Screen.HEIGHT);

        //ミサイル生成
        hm = new HoudaiMissile();

        //砲台生成
        hd = new Houdai(hm,this);

        wp = new WallPanel();

        //UFO生成
        ufo = new Ufo(this);

        im = new InvaderMissile[11];

        for (int i = 0; i < 11; i++) {
            im[i] = new InvaderMissile();
            im[i].addObserver(wp);
            im[i].addObserver(hd);
        }

        //インベーダー生成
        ip = new InvaderPanel(im,this);

        //スコア表示
        score = new JLabel("SCORE: " + ts);
        score.setBounds(1030,0,170,35);
        score.setFont(new Font("Arial",Font.BOLD,20));
        score.setForeground(Color.orange);
        add(score);

        //ゲームオーバー画面背景
        //add(label2);

        //ライフ表示
        life = new JLabel("LIFE: " + lf);
        life.setBounds(10,0,170,35);
        life.setFont(new Font("Arial",Font.BOLD,20));
        life.setForeground(Color.green);
        add(life);

        //ミサイルの観測者としてUFOを登録する
        //hm.addObserver();

        hm.addObserver(ip);
        hm.addObserver(ufo);
        hm.addObserver(wp);

        // im.addObserver();

        //ゲーム画面に砲台を貼る
        add(hd);

        add(hm);

        add(ufo);

        add(ip);
        for (InvaderMissile x : im) add(x);

        add(wp);

        //画面設定
        add(bi);

        //キーイベント追加（observerパターンと同じ）
        addKeyListener(this);

        //可視
        setVisible(true);
        setFocusable(true);

    }

    //防護壁再生成メソッド
    public void extinction(){
      wp.start();
    }

    //スコア加算メソッド
    void addScore(int n){
      if(lf >= 1){
        ts += n;
        score.setText("SCORE: " + String.valueOf(ts));
      }
    }

    //スコアリセットメソッド
    void resetScore(){
      ts = 0;
      score.setText("SCORE: " + String.valueOf(ts));
    }

    //lifeリセットメソッド
    void resetLife(){
      lf = 3;
      life.setText("LIFE: " + String.valueOf(lf));
    }

    //life減算メソッド
    void killLife(int n){
      if(lf > 0){
        lf -= n;
        life.setText("LIFE: " + String.valueOf(lf));
      }
      if(lf <= 0){
        try{Thread.sleep(1000);}catch(Exception e){}

          label1.setBounds(300,50,600,300);
          //label2.setBounds(0,0,1200,750);
          button1.setBounds(470,440,100,100);
          button1.setContentAreaFilled(false);
          button1.setBorderPainted(false);
          button2.setBounds(630,440,100,100);
          button2.setContentAreaFilled(false);
          button2.setBorderPainted(false);

          label1.setVisible(true);
          //label2.setVisible(true);
          button1.setVisible(true);
          button2.setVisible(true);


          button1.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                  resetLife();
                  resetScore();
                  extinction();
                  ip.resetGoInv(0,100,10);

                  label1.setVisible(false);
                  //label2.setVisible(false);
                  button1.setVisible(false);
                  button2.setVisible(false);

              }
          });
          button2.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                  //dispose();
                  System.exit(0);
              }
          });
      }
    }


    //----------------------------------------------
    //キーイベント取得(KeyListener継承からの実装）
    //----------------------------------------------
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_LEFT) {
            //System.out.println("left");
            hd.moveLeft();
        } else if (e.getKeyCode() == e.VK_RIGHT) {
            //System.out.println("right");
            hd.moveRight();
        } else if (e.getKeyCode() == e.VK_SPACE) {
            //System.out.println("space");
            hd.hasha();
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_LEFT) {
        }
        //System.out.println("stop");

    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
            //System.out.println("space");
            hd.hasha();
        }

    }

}
