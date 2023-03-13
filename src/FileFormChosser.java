import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileFormChosser extends JFrame {
    private JButton btn;
    private JTextArea textArea;
    private JPanel mainPanel;

    public FileFormChosser() {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                vyberSouboru();
            }
        });
    }
    public void vyberSouboru(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == fileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String radek = reader.readLine();
                while(radek != null){
                    textArea.append(radek + "\n");
                    radek = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            textArea.setText("Soubor nebyl vybrán");
        }
    }
    public static void main(String[] args) {
        FileFormChosser fileForm = new FileFormChosser();
        fileForm.setContentPane(fileForm.mainPanel);
        fileForm.pack();
        fileForm.setTitle("Výběr souboru");
        fileForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fileForm.setVisible(true);
    }
}
