import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Math;

//--------------------
//インベーダー単体
//--------------------
class Invader extends Subject{

	int x,y;
	boolean life = false;
	static ImageIcon icon = new ImageIcon("images/invader01.png");
	static ImageIcon icon2 = new ImageIcon("images/invader02.png");
	static ImageIcon icon3 = new ImageIcon("images/invader03.png");

	static ImageIcon icon4 = new ImageIcon("images/invader-bang.png");
	int flag;
	int id;
	int inv_set;

	InvaderMissile im;

	PlayClip pc;

	//コンストラクタ
	Invader(int x, int y, InvaderMissile im, int f, int img, int id){

	  this.im = im;
		this.x = x;
		this.y = y;
		this.flag = f;
		this.id = id;
		this.inv_set = img;

		setBounds(x, y, Screen.INV_W, Screen.INV_H);

		if(img == 1){
			setIcon(icon);
		}else if(img == 2){
			setIcon(icon2);
		}else if(img == 3){
			setIcon(icon3);
		}
		setOpaque(false);
	}

	//壊れ画像に変更
	void setIconBreak(){
		pc = new PlayClip("BGM/bang.wav");
		pc.play();
		setIcon(icon4);

	}

	//lifeセット
	void setLife(Boolean l){
            life = l;
	}

	//発射メソッド
    void hasha(int hx, int hy){
	    if(life == true){
            im.moveDown(hx, hy);
	    }
    }

  //確認メソッド
  void check(int i){

  }

	void des(){
	    setBounds(-500, -500, Screen.INV_W, Screen.INV_H);
        notifyObservers();
  }

	//復活メソッド
	void restart(){
		pc = new PlayClip("BGM/respon.wav");
		pc.play();
		if(id < 200){
			setIcon(icon);
			setBounds((id-100)*Screen.INV_W,0,Screen.INV_W, Screen.INV_H);
		}else if(id < 300){
			setIcon(icon2);
			setBounds((id-200) * Screen.INV_W, 50, Screen.INV_W, Screen.INV_H);
		}else if(id < 400) {
			setIcon(icon2);
			setBounds((id-300) * Screen.INV_W, 100, Screen.INV_W, Screen.INV_H);
		}else if(id < 500){
			setIcon(icon3);
			setBounds((id-400) * Screen.INV_W, 150, Screen.INV_W, Screen.INV_H);
		}else if(id < 600){
			setIcon(icon3);
			setBounds((id-500) * Screen.INV_W, 200, Screen.INV_W, Screen.INV_H);
			flag = 0;
		}
	}

}

//-----------------
//インベーダー集団
//-----------------
class InvaderPanel extends JPanel implements Runnable, Observer{

	ArrayList<Invader> inv = new ArrayList<Invader>();
	GamePanel gp;

	static int sx = 0;
	static int sy = Screen.IP_Y;
	int dir=Screen.DIR_R;
	int count = 0;

	int startCount = 1;
	int x_add = startCount * 10;	//加速
	int y_add = 10;



	//コンストラクタ
	InvaderPanel(InvaderMissile[] im,GamePanel gp){
		this.gp = gp;
		setLayout(null);
		setBounds(sx,sy,Screen.IP_W,Screen.IP_H);
		setOpaque(false);

		//３段、５列
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 11; j++){
			    if(i == 0) {
              inv.add(i, new Invader(j * Screen.INV_W, i * Screen.INV_H, im[j], 1,1,(i+1)*100+j));
              add(inv.get(i));
          }else if(i == 1 || i == 2){
              inv.add(i, new Invader(j * Screen.INV_W, i * Screen.INV_H, im[j], 1, 2,(i+1)*100+j));
              add(inv.get(i));
          }else if(i == 3) {
							inv.add(i, new Invader(j * Screen.INV_W, i * Screen.INV_H, im[j], 1, 3,(i+1)*100+j));
							add(inv.get(i));
					}else if(i == 4){
							inv.add(i, new Invader(j * Screen.INV_W, i * Screen.INV_H, im[j], 0, 3,(i+1)*100+j));
							add(inv.get(i));
          }
			}
		}
		//setOpaque(false);
	}

	//スレッドの実行
	public void run(){

		for(;;){
		    //先頭かの判定
        for (Invader i : inv){
						if(i.y == 200 && i.flag == 0){
								i.setLife(true);
						}else if(i.y == 150 && i.flag == 0){
								i.setLife(true);
						}else if(i.y == 100 && i.flag == 0){
                i.setLife(true);
            }else if(i.y == 50 && i.flag == 0){
                i.setLife(true);
            }else if(i.flag == 0){
                i.setLife(true);
            }
        }
        //ミサイル発射
        for(Invader i : inv){
            if(i.life == true){
                int ran = (int)(Math.random()*100);
            		if(ran < 10){
                    i.hasha(sx + i.x + 15 , sy + 50 + i.y);
                }
            }
        }

			if(dir == Screen.DIR_R){
				if(sx + Screen.IP_W >= Screen.RIGHT + 20){	//右壁
					sy += this.y_add;
					dir = Screen.DIR_L;
					this.x_add += 5;
				}else{
					if(sx + Screen.IP_W + this.x_add >= Screen.RIGHT){
						sx = Screen.RIGHT - Screen.IP_W + 20;
					}else{
						sx += this.x_add;
					}
				}
			}else{
				if(sx <= Screen.LEFT){	//ひだり壁
					sy += this.y_add;
					dir = Screen.DIR_R;
					this.x_add += 5;
				}else{
					if(sx - this.x_add <= 0){
						sx = 0;
					}else{
						sx -= this.x_add;
					}
				}
			}
			setLocation(sx, sy);

			try{Thread.sleep(500);}catch(Exception e){}

		}
		//System.out.println(inv.x);

		//発射呼び出し

	}
	//ミサイルからの通知
	public void update(Subject s) {
	 	int mx = ((HoudaiMissile)s).getX();
		int my = ((HoudaiMissile)s).getY();

		//当たったか判定
		for(Invader i : inv){
			if(Screen.hitCheck5((i.y + this.sy + Screen.INV_H)) == true && i.life == true){
				System.out.println("GAME OVER");
				gp.killLife(3);
				resetInv(0,100);
			}
//System.out.printf("--インベーダpanelー-- (%d %d )(%d %d)\n ", mx,my, (i.x + this.sx ), (i.y + this.sy));
			if( Screen.hitCheck(mx,my,(i.x + this.sx - 25),(i.y + this.sy),Screen.INV_W, Screen.INV_H)  == true && i.life == true )//当たったか？
			{
				System.out.println("爆発");
				//life = false;

				//画像変更
				i.setIconBreak();

				if(i.inv_set == 3){
					gp.addScore(Screen.SCORE_INVADER1);
				}else if(i.inv_set == 2){
					gp.addScore(Screen.SCORE_INVADER2);
				}else if(i.inv_set == 1){
					gp.addScore(Screen.SCORE_INVADER3);
				}

				i.flag = 1;
				int bx = i.x;
				int by = i.y;
				for(Invader j : inv){
					if(j.x == bx && j.y + 50 == by){
						j.flag = 0;
            System.out.println("B");
					}
				}

				//lifeフラグセット
				i.setLife(false);

        ((HoudaiMissile)s).x = -100;
        ((HoudaiMissile)s).y = -100;

        try{
            Thread.sleep(100);
            i.des();
        }catch(Exception e){

        }
				count++;
				try {
					if (count == 55) {
						Thread.sleep(2000);
						gp.extinction();
						this.startCount ++;
						this.x_add = this.startCount * 10;
						for (Invader j : inv) {
							this.sx = 0;
							this.sy = 100;
							this.dir = Screen.DIR_R;
							setBounds(sx, sy, Screen.IP_W, Screen.IP_H);
							j.restart();
							count = 0;
							//System.out.println(Screen.WAL_F);
						}
					}
				}catch(Exception e){

				}
			}
		}

  }

		void resetInv(int xxx, int yyy){
			for (Invader j : inv) {
				this.sx = xxx;
				this.sy = yyy;
				this.dir = Screen.DIR_R;
				setBounds(sx, sy, Screen.IP_W, Screen.IP_H);
				j.restart();
				count = 0;
			}
		}

		//ゲームオーバー時リセット
		void resetGoInv(int xxx, int yyy, int nnn){
			for (Invader j : inv) {
				if(j.y == 200){
					j.flag = 0;
				}else{
					j.flag = 1;
					j.setLife(false);
				}
				this.sx = xxx;
				this.sy = yyy;
				this.x_add = nnn;
				this.dir = Screen.DIR_R;
				setBounds(sx, sy, Screen.IP_W, Screen.IP_H);
				j.restart();
				count = 0;
			}
		}


}
