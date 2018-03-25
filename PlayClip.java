import java.io.*;
import javax.sound.sampled.*;

//音声管理クラス
class PlayClip{

  int x;

  Clip clip = null;

  //コンストラクタ
  PlayClip(String filename){
    try{
      //ファイル名からClip情報の取得（詳細はAPIドキュメント等で）
      AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filename));
      clip = AudioSystem.getClip();
      clip.open(ais);
    }
    catch(Exception e){System.out.println(e);}
  }
  //再生メソッド
  public void play(){
    clip.start();

    System.out.println("Playing...");
  }
  //停止メソッド
  public void stop(){
    clip.stop();

    System.out.println("Stop...");
  }
  //巻き戻しメソッド
  public void setFramePosition(){
    clip.setFramePosition(1);

    System.out.println("巻き戻し...");
  }

  //Repeatメソッド
  public void loop(){
    clip.loop(1000);

    System.out.println("ループ...");
  }
}
