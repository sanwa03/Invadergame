import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


class Wall extends JLabel {

    Houdai hd;
    int id;

    int x, y;
    boolean life = true;
    static ImageIcon icon = new ImageIcon("images/Wall.png");

    //コンストラクタ
    Wall(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;

        setBounds(x, y, Screen.WAL_W, Screen.WAL_H);
        setIcon(icon);
        setOpaque(false);
    }

    void setLife(Boolean l) {
        life = l;
    }

    void des() {
        setBounds(-500, -500, Screen.WAL_W, Screen.WAL_H);
    }

    void restart() {
        if (id < 2000) {
            int num = id - 1000;
            if (num < 200) {
                setBounds((num - 100) * Screen.WAL_W, 0, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 300) {
                setBounds((num - 200) * Screen.WAL_W, 20, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 400) {
                setBounds((num - 300) * Screen.WAL_W, 40, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 500) {
                setBounds((num - 400) * Screen.WAL_W, 60, Screen.WAL_W, Screen.WAL_H);
            }
        } else if (id < 3000) {
            int num = id - 2000;
            if (num < 200) {
                setBounds((num - 100) * Screen.WAL_W + 300, 0, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 300) {
                setBounds((num - 200) * Screen.WAL_W + 300, 20, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 400) {
                setBounds((num - 300) * Screen.WAL_W + 300, 40, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 500) {
                setBounds((num - 400) * Screen.WAL_W + 300, 60, Screen.WAL_W, Screen.WAL_H);
            }
        } else if (id < 4000) {
            int num = id - 3000;
            if (num < 200) {
                setBounds((num - 100) * Screen.WAL_W + 600, 0, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 300) {
                setBounds((num - 200) * Screen.WAL_W + 600, 20, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 400) {
                setBounds((num - 300) * Screen.WAL_W + 600, 40, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 500) {
                setBounds((num - 400) * Screen.WAL_W + 600, 60, Screen.WAL_W, Screen.WAL_H);
            }
        } else if (id < 5000) {
            int num = id - 4000;
            if (num < 200) {
                setBounds((num - 100) * Screen.WAL_W + 900, 0, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 300) {
                setBounds((num - 200) * Screen.WAL_W + 900, 20, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 400) {
                setBounds((num - 300) * Screen.WAL_W + 900, 40, Screen.WAL_W, Screen.WAL_H);
            } else if (num < 500) {
                setBounds((num - 400) * Screen.WAL_W + 900, 60, Screen.WAL_W, Screen.WAL_H);
            }
        }
        setLife(true);
    }
}

public class WallPanel extends JPanel implements Observer {

    ArrayList<Wall> wal = new ArrayList<Wall>();

    private int x = 50;
    static int y = Screen.WP_Y;
    int dir = Screen.DIR_R;
    private int cont = 0;
    boolean flag;

    //コンストラクタ
    WallPanel() {
        setLayout(null);
        setBounds(x, y, Screen.WP_W, Screen.WP_H);
        setOpaque(false);

        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 8; j++) {
                    wal.add(i, new Wall(cont + j * Screen.WAL_W, i * Screen.WAL_H, (k+1)*1000+(i+1)*100+j));
                    add(wal.get(i));
                }
            }
            cont += 300;
        }
    }

    //ミサイルからの通知
    public void update(Subject s) {
        if (s instanceof HoudaiMissile) {
            int mx = ((HoudaiMissile) s).getX();
            int my = ((HoudaiMissile) s).getY();
            for (Wall i : wal) {
                //System.out.println("x:" + mx + "y:" + my + "  " + (i.x + this.x) + (i.y + this.y));
                //System.out.println(mx);
                if (Screen.hitCheck2(mx, my, (i.x + this.x), (i.y + this.y), Screen.WAL_W, Screen.WAL_H) == true && i.life == true) {
                    ((HoudaiMissile) s).x = -100;
                    ((HoudaiMissile) s).y = -100;
                    //System.out.println("あたりあたりあたりあたりあたり");

                    //lifeフラグセット
                    i.setLife(false);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    i.des();
                }
            }
        } else if (s instanceof InvaderMissile) {
            int imx = ((InvaderMissile) s).getX();
            int imy = ((InvaderMissile) s).getY();
            //当たったか判定
            for (Wall i : wal) {
                //System.out.println("x:" + imx + "y:" + imy);
                if (Screen.hitCheck3(imx, (imy + 50), (i.x + this.x), (i.y + this.y), Screen.WAL_W, Screen.WAL_H) == true && i.life == true) {
                    ((InvaderMissile) s).x = -300;
                    ((InvaderMissile) s).y = 900;

                    //lifeフラグセット
                    i.setLife(false);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    i.des();

                }
            }
        }
    }
    void start(){
        for (Wall i: wal){
            i.restart();
        }
    }
}
