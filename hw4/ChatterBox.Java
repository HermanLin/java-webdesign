import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChatterBox {

    static ChatterBox cb;
    JTextArea textArea;
    JTextField textField;
    JButton sendButton;

    ChatterBox() {
        // default settings for the window
        JFrame jf = new JFrame("ChatterBox");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //jf.setSize(500, 600);
        jf.setResizable(false);

        // create the components for the ChatterBox
        textArea = new JTextArea(30, 30);
        textArea.setEditable(false);
        JScrollPane sp = new JScrollPane(textArea);

        textField = new JTextField(30);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                textArea.append(text + "\n");
                textField.setText("");
            }
        });

        // create dynamic grid of cells which allows for
        // components to occupy one or more cells
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        JPanel jp = new JPanel(gridbag);

        // add components to the panel
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        jp.add(sp, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.weighty = 0.0;
        jp.add(textField, c);
    
        // add panel to the window frame
        jf.add(jp);
        jf.pack();
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        cb = new ChatterBox();
    }
}
