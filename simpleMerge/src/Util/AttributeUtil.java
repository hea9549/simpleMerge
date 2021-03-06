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
        StyleConstants.setBackground(addedAttribute, Color.WHITE);
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

    public static SimpleAttributeSet getClickAttribute(){
        SimpleAttributeSet select = new SimpleAttributeSet();
        StyleConstants.setBackground(select, Color.magenta);
        return select;
    }
    public static SimpleAttributeSet setUnClickAttribute(SimpleAttributeSet attributeSet){
        StyleConstants.setBackground(attributeSet,StyleConstants.getBackground(attributeSet).darker());
        return attributeSet;
    }
}
