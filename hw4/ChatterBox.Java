import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;

public class ChatterBox {

    static ChatterBox cb;
    JTextArea textChat, textInput;
    JButton sendButton;
    String serverAddr, username;
    Client connection;

    static final String IP_ADDRESS = 
        "[0-9]{1,4}\\.[0-9]{1,4}\\.[0-9]{1,4}\\.[0-9]{1,4}";

    ChatterBox() {
        // default settings for the window
        JFrame jf = new JFrame("ChatterBox");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setVisible(true);

        serverPrompt(jf);
    }

    /*
        Prompt user for which server to connect to and a username
    */
    private void serverPrompt (JFrame jf) {
        // create components 
        JTextField addrInput = new JTextField(20);
        JTextField nameInput = new JTextField(20);

        JButton submit = new JButton("Login");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                serverAddr = addrInput.getText().strip();
                username = nameInput.getText().strip();

                if (Pattern.matches(IP_ADDRESS, serverAddr)) {
                    startChatterBox(jf);
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Please use a valid server address",
                        "ChatterBox Error",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // create dynamic grid of cells which allows for
        // components to occupy one or more cells
        JPanel prompt = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        // add components to the panel
        //c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        prompt.add(new Label("Server Address"), c);

        //c.weightx = 0.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        prompt.add(addrInput, c);

        c.gridwidth = GridBagConstraints.RELATIVE;
        prompt.add(new Label("Username"), c);
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        prompt.add(nameInput, c);

        c.gridwidth = GridBagConstraints.HORIZONTAL;
        prompt.add(submit, c);

        // add panel to the window frame
        jf.add(prompt);
        jf.pack();
    }

    /*
        Initialize the Client object and start
        the ChatterBox program
    */
    private void startChatterBox(JFrame jf) {
        connection = new Client(serverAddr);
        connection.write(username + "\r\n");
        
        chatWindow(jf);
    }

    /*
        Create the ChatterBox interface for user IO
    */
    private void chatWindow(JFrame jf) {
        // create the components for the ChatterBox
        textChat = new JTextArea(30, 30);
        textChat.setEditable(false);
        textChat.setLineWrap(true);
        JScrollPane chatSP = new JScrollPane(textChat);

        textInput = new JTextArea(3, 30);
        textInput.setLineWrap(true);
        JScrollPane inputSP = new JScrollPane(textInput);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textInput.getText();
                //textChat.append(username + ": " + text + "\n");
                connection.write(text + "\r\n");
                textInput.setText("");
            }
        });

        // create dynamic grid of cells which allows for
        // components to occupy one or more cells
        JPanel jp = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        // add components to the panel
        c.gridwidth = GridBagConstraints.REMAINDER;
        jp.add(chatSP, c);

        c.gridwidth = GridBagConstraints.RELATIVE;
        jp.add(inputSP, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        jp.add(sendButton, c);

        // replace the old panel in the frame
        jf.setTitle("ChatterBox: " + serverAddr);
        // not sure how to remove the old panel instead of replacing
        jf.setContentPane(jp); 
        jf.pack();
        jf.getContentPane().invalidate();
        jf.getContentPane().validate();
    }

    public static void main(String[] args) {
        cb = new ChatterBox();
    }
}
