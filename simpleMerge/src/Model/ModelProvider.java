package Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public class ModelProvider {
    private static ModelProvider instance;
    private Map<String,Object> modelMap = new HashMap<String,Object>();
    private ModelProvider(){
    }
    public static ModelProvider getInstance(){
        if(instance == null) instance = new ModelProvider();
        return instance;
    }

    /**
     * @return Object : when there is model
     *          false : when there is already same key
     * */
    public Object getModel(String key){
        Iterator<String> modelIterator = modelMap.keySet().iterator();
        while (modelIterator.hasNext()){
            if(key.equals(modelIterator.next()))return modelMap.get(key);
        }
        return null;
    }

    /**
     * @return true : when register success
     *          false : when there is already same key
     * */
    public boolean registerModel(String key,Object model){
        Iterator<String> modelIterator = modelMap.keySet().iterator();
        while (modelIterator.hasNext()){
            if(key.equals(modelIterator.next()))return false;
        }
        modelMap.put(key,model);
        return true;
    }
}
