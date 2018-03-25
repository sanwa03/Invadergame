import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame{
    static MainFrame mf;
    GamePanel gp = new GamePanel();
    JPanel mp;
    ImageIcon icon1 = new ImageIcon("images/invader01.png");
    ImageIcon icon2 = new ImageIcon("images/invader02.png");
    ImageIcon icon3 = new ImageIcon("images/invader03.png");
    ImageIcon icon4 = new ImageIcon("images/ufo.png");
    ImageIcon back = new ImageIcon("images/ucyu-start.jpg");
    ImageIcon title = new ImageIcon("images/title-start.png");
    ImageIcon start = new ImageIcon("images/start-button.png");
    ImageIcon exit = new ImageIcon("images/exit-button.png");
    JButton button1 = new JButton(start);
    JButton button2 = new JButton(exit);
    JLabel label1 = new JLabel(title);
    JLabel label2 = new JLabel(icon1);
    JLabel label3 = new JLabel(icon2);
    JLabel label4 = new JLabel(icon3);
    JLabel label5 = new JLabel(icon4);
    JLabel label6 = new JLabel(back);
    protected GraphicsDevice device = null;


    public MainFrame(){
        this.setLayout(null);
        this.add(gp);
        gp.setVisible(false);

        mp = new JPanel();
        mp.setBackground(Color.black);
        mp.setLayout(null);
        mp.setBounds(0,0,1200,750);
        this.add(mp);

        this.setTitle("インベーダーゲーム");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200,100,1200,750);
        mp.add(label1);
        mp.add(label2);
        mp.add(label3);
        mp.add(label4);
        mp.add(label5);
        mp.add(button1);
        mp.add(button2);

        mp.add(label6);

        button1.setBounds(470,470,100,100);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        button1.setFont(new Font("Arial",Font.ITALIC,40));
        button1.setForeground(Color.orange);
        button2.setBounds(630,470,100,100);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        button2.setFont(new Font("Arial",Font.ITALIC,40));
        button2.setForeground(Color.orange);


        label1.setBounds(350,50,500,210);
        label2.setBounds(400,250,300,100);
        label3.setBounds(500,250,300,100);
        label4.setBounds(400,320,300,100);
        label5.setBounds(500,320,300,100);
        label6.setBounds(0,0,1200,750);


        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                gp.setVisible(true);
                mp.setVisible(false);

                //砲台
                Thread t1 = new Thread(gp.hm);
                t1.start();

                //UFO
                Thread t2 = new Thread(gp.ufo);
                t2.start();

                //インベーダー
                Thread t3 = new Thread(gp.ip);
                t3.start();

                Thread[] t4 = new Thread[11];
                for (int i = 0 ; i < 11; i++){
                    t4[i] = new Thread(gp.im[i]);
                    t4[i].start();
                }

            }
        });
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        mf = new MainFrame();
        mf.setVisible(true);

        //BGM生成
        PlayClip pc = new PlayClip("BGM/ucyu-BGM.wav");
        pc.loop();
    }
}
