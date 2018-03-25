import java.util.ArrayList;
import javax.swing.*;

public abstract class SubjectPanel extends JPanel{
    //Observerたちを保持
    ArrayList<ObserverPanel> observerpanels = new ArrayList<>();

    //Observerを追加
    public void addObserverPanel (ObserverPanel observerpanel){
        observerpanels.add(observerpanel);
    }
    //Observerを削除
    public void deleteObserverPanel(ObserverPanel observerpanel){
        observerpanels.remove(observerpanel);
    }
    //Observerへ通知
    public void notifyObserverPanels(){
        for(ObserverPanel o : observerpanels){
            o.update(this);
        }
    }
}