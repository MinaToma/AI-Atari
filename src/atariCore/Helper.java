package atariCore;

import javax.swing.*;
import java.awt.*;

public class Helper {

    static public JButton btnHelper(JButton btn , String txt , int x , int y , Dimension dim , Panel panel) {
        btn = new JButton(txt);
        btn.setLayout(null);
        btn.setBounds(x , y , dim.width , dim.height);

        panel.add(btn);

        return btn;
    }

}
