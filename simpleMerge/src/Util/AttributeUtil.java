package Util;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

/**
 * Created by ParkHaeSung on 2017-06-01.
 */
public class AttributeUtil {
    public static SimpleAttributeSet getAddedAttribute(){
        SimpleAttributeSet addedAttribute = new SimpleAttributeSet();
        StyleConstants.setBackground(addedAttribute, Color.YELLOW);
        return addedAttribute;
    }

    public static SimpleAttributeSet getDefaultAttribute(){
        SimpleAttributeSet addedAttribute = new SimpleAttributeSet();
        StyleConstants.setBackground(addedAttribute, Color.YELLOW);
        return addedAttribute;
    }

    public static SimpleAttributeSet getDiffAttribute(){
        SimpleAttributeSet diffAttribute = new SimpleAttributeSet();
        StyleConstants.setBackground(diffAttribute, Color.RED);
        return diffAttribute;
    }
    public static SimpleAttributeSet getEmptyAttribute(){
        SimpleAttributeSet emptyAttribute = new SimpleAttributeSet();
        StyleConstants.setBackground(emptyAttribute, Color.GRAY);
        return emptyAttribute;
    }

}
