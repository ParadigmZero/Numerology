import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;




public class Help {

    private File helpFile = new File("instructions");
    private Scanner scanner = new Scanner(helpFile);

    private String instructionsString = "";
    private String aboutString = "";

    private JDialog dialog;
    private JTextArea textArea;
    private JOptionPane optionPane;

    private JDialog dialog2;
    private JTextArea textArea2;
    private JOptionPane optionPane2;

     public Help() throws IOException {


        while(scanner.hasNextLine())
        {
            instructionsString += scanner.nextLine();
            instructionsString += "\n";
        }

        textArea = new JTextArea(instructionsString);
        textArea.setFont(new Font("Monospaced",Font.PLAIN,12));
        textArea.setEditable(false);
        optionPane = new JOptionPane(textArea);

        dialog = optionPane.createDialog("Instructions");

        aboutString=
         "This application experiments with \"numerological\" methods.\n\n"+
             "It is designed to be of academic interest only, as a Java programming example.\n\n"
+
         "This was created by Paradigm Zero software:\n\n"+

         "GitHub.com/ParadigmZero";

        textArea2 = new JTextArea(aboutString);

        textArea2.setFont(new Font("Monospaced",Font.PLAIN,12));
        textArea2.setEditable(false);
        optionPane2 = new JOptionPane(textArea2);

        dialog2 = optionPane2.createDialog("About");


    }

    public void instructions()
    {

        dialog.setVisible(true);
    }

    public void about()
    {
        dialog2.setVisible(true);
    }


}
