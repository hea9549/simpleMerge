package Observer;

import Data.DataId;

/**
 * Created by ParkHaeSung on 2017-06-02.
 */
public class UpdateEvent {
    private DataId id;
    private Object object;
    public UpdateEvent(DataId id, Object object){
        this.id= id;
        this.object = object;
    }

    public DataId getId() {
        return id;
    }

    public Object getObject() {
        return object;
    }
}
