import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class GUI2 implements ActionListener {
    JTextField txf1, txf2, txf3;
    GUI2() {
        JFrame jf = new JFrame("Spam Ham Detection - Naman Gupta");
        JPanel jp1 = new JPanel(), jp2 = new JPanel(), jp3 = new JPanel();
        JLabel jb1 = new JLabel("Message: "), jb2 = new JLabel("Prediction"), jb3 = new JLabel("Accuracy: ");
        txf1 = new JTextField("*message*", 40); 
        txf2 = new JTextField(5);
        txf3 = new JTextField(10);
        JButton jb = new JButton("Check");
        jf.setLayout(new BoxLayout(jf.getContentPane(), BoxLayout.Y_AXIS));
        jb.addActionListener(this);
        jp1.add(jb1);
        jp1.add(txf1);
        jf.add(jp1);
        jp2.add(jb3);
        jp2.add(txf3);
        jp2.add(jb2);
        jp2.add(txf2);
        jf.add(jp2);
        jf.add(Box.createVerticalGlue());
        jp3.add(jb);
        jf.add(jp3);
        jf.add(Box.createVerticalGlue());
        jf.setSize(600,200);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    public void actionPerformed(ActionEvent ae) {
        try {
            String newMessage = txf1.getText();
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "/Users/namangupta/Desktop/Projects/SpamHam Message Detection/detection2.py", newMessage);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            line = reader.readLine();
            double val = Double.parseDouble(line);
            val *= 100;
            line = "" + val;
            txf3.setText(line.substring(0, 5));
            line = reader.readLine();
            line = Character.toUpperCase(line.charAt(0)) + line.substring(1);
            txf2.setText(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    new GUI2();
                }
            });
        }
        catch(Exception e) {
            System.out.print(e);
        }
    }
}