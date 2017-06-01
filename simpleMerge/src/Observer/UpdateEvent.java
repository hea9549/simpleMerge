package Observer;

/**
 * Created by ParkHaeSung on 2017-06-02.
 */
public class UpdateEvent {
    private int id;
    private Object object;
    public UpdateEvent(int id, Object object){
        this.id = id;
        this.object = object;
    }

    public int getId() {
        return id;
    }

    public Object getObject() {
        return object;
    }
}
