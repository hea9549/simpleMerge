package Controller;

import Data.DataId;

import java.awt.event.ActionListener;

/**
 * Created by ParkHaeSung on 2017-06-02.
 */
public interface Controller <T> {
    T getEventListener(DataId id);
    T getEventListener(DataId id,Object extraData);
}
