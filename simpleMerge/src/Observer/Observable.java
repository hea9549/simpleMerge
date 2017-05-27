package Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-24.
 */
public class Observable{
    List<Observer> observerList = new ArrayList<>();
    public void addObserver(Observer observer){
        observerList.add(observer);
    }

    public boolean removeObserver(Observer observer){
        for(int i =0;i<observerList.size();i++){
            if(observerList.get(i) == observer){
                observerList.remove(i);
                return true;
            }
        }
        return false;
    }

    public void notifyChange(Map<Integer, String> data){
        for(int i = 0 ; i < observerList.size();i++){
            observerList.get(i).update(data);
        }
    }
}
