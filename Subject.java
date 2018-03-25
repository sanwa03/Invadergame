import java.util.ArrayList;
import javax.swing.*;

public abstract class Subject extends JLabel{
    //Observerたちを保持
    ArrayList<Observer> observers = new ArrayList<>();

    //Observerを追加
    public void addObserver (Observer observer){
        observers.add(observer);
    }
    //Observerを削除
    public void deleteObserver(Observer observer){
        observers.remove(observer);
    }
    //Observerへ通知
    public void notifyObservers(){
        for(Observer o : observers){
            o.update(this);
        }
    }
}