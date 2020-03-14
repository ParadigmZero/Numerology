import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



// center
//
//
// pop-up box that displays the instructions...
//
// Hebrew numerology
// firstly requires, english to hebrew conversion
// Hebrew uses planets, colours and so forth
// hebrew is another app
// secondly hebrew input - a seperate keyboard.
// add in-depth breakdowns in a console
// input box - delete, cut all, paste over, copy all
// add copy to the output boxes...

public class Numerology {


    private ArrayList<Integer> numberArray;
    private Integer number;

    private Map<Character, Integer> map = new HashMap<Character, Integer>();


    private JTextField pOutput = new JTextField();
    private JTextField cOutput = new JTextField();

    private Help help = new Help();




    public Numerology() throws IOException {

        /*
        Create HashMap lookup tables
         */
        // Pythagorean - represented by uppercase
        map.put('A', 1);
        map.put('B', 2);
        map.put('C', 3);
        map.put('D', 4);
        map.put('E', 5);
        map.put('F', 6);
        map.put('G', 7);
        map.put('H', 8);
        map.put('I', 9);
        map.put('J', 1);
        map.put('K', 2);
        map.put('L', 3);
        map.put('M', 4);
        map.put('N', 5);
        map.put('O', 6);
        map.put('P', 7);
        map.put('Q', 8);
        map.put('R', 9);
        map.put('S', 1);
        map.put('T', 2);
        map.put('U', 3);
        map.put('V', 4);
        map.put('W', 5);
        map.put('X', 6);
        map.put('Y', 7);
        map.put('Z', 8);

        // Chaldean represented by lowercase
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('f', 8);
        map.put('g', 3);
        map.put('h', 5);
        map.put('i', 1);
        map.put('j', 1);
        map.put('k', 2);
        map.put('l', 3);
        map.put('m', 4);
        map.put('n', 5);
        map.put('o', 7);
        map.put('p', 8);
        map.put('q', 1);
        map.put('r', 2);
        map.put('s', 3);
        map.put('t', 4);
        map.put('u', 6);
        map.put('v', 6);
        map.put('w', 6);
        map.put('x', 5);
        map.put('y', 1);
        map.put('z', 7);

        // numbers represent themselves in both systems.
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);

        /*
            Create the GUI
         */
        JFrame frame = new JFrame("Numerology calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        GridLayout gridLayout = new GridLayout(0, 1);

        frame.setLayout(gridLayout);


        JLabel inputLabel = new JLabel("Enter letters / numbers:", SwingConstants.CENTER);


       // JTextArea input = new JTextArea(4, 15);


        JTextField input = new JTextField();

        input.setHorizontalAlignment(JTextField.CENTER);


        JLabel pOutputLabel = new JLabel("Pythagorean",SwingConstants.CENTER);
        JLabel cOutputLabel = new JLabel("Chaldean",JLabel.CENTER);


        JMenu menu = new JMenu("Help");

        JMenuItem menuItem = new JMenuItem("Instructions");
        JMenuItem menuItem2 = new JMenuItem("About");

        menu.add(menuItem);
        menu.add(menuItem2);



        JMenuBar menuBar = new JMenuBar();
        menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        menuBar.add(menu);

        frame.setJMenuBar(menuBar);


        pOutput.setHorizontalAlignment(JTextField.CENTER);
        pOutput.setBackground(Color.WHITE);
        cOutput.setHorizontalAlignment(JTextField.CENTER);
        cOutput.setBackground(Color.WHITE);
        pOutput.setEditable(false);
        cOutput.setEditable(false);

        JButton button = new JButton("Calculate");

        frame.add(inputLabel);
        frame.add(input);
        frame.add(button);

        frame.add(pOutputLabel);
        frame.add(pOutput);

        frame.add(cOutputLabel);
        frame.add(cOutput);

        frame.setBackground(Color.BLACK);


        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i=0; i < input.getText().length(); i++)
                {
                    // if there is a single A-Z, a-z or 0-9 we can perform numerology
                    if(map.containsKey(input.getText().charAt(i)))
                    {
                        calculate(input.getText());
                        break;
                    }
                }
            }
        });

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               help.instructions();
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help.about();
            }
        });



    }




    public static void main(String[] args) throws IOException {
        Numerology numerology = new Numerology();
    }


    /*
        Button click : input -> output
     */
    private void calculate(String string) {
        System.out.println(string);
        pOutput.setText(buildNumber(string.toUpperCase(), true));

        cOutput.setText(buildNumber(string.toLowerCase(), false));
    }


    // for both Chaldean and
    private String buildNumber(String s, boolean isP) {

        System.out.println(s);

        numberArray = new ArrayList<Integer>();


        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                numberArray.add(map.get(s.charAt(i)));
            }
        }

        if(!isP)
        {
            if(checkForSpecialChaldeanNumber())
            {
                String numberString = "";

                for(int i=0; i < numberArray.size(); i++)
                {
                    numberString += numberArray.get(i);
                }

                return numberString;
            }
        }



        do {

            number = 0;


            for (int i = 0; i < numberArray.size(); i++) {
                number += numberArray.get(i);

            }


        }
        while (checkNumber(isP));

        return Integer.toString(number);
    }

    // check whether the numberArray needs to be reduced
    private boolean checkNumber(boolean p) {

        if (number > 9) {
            if (!p) {
                if (checkForSpecialChaldeanNumber()) {
                    return false;
                }
            }

            // refresh the numberArray
            numberArray = new ArrayList<Integer>();
            String numberString = number.toString();

            System.out.println(numberString);

            for (int i = 0; i < numberString.length(); i++) {
                numberArray.add(Character.getNumericValue(numberString.charAt(i)));
            }

            return true;
        }


        return false;
    }

    // Checks for repeating digit numbers that are not reduced in the Chaldean system such as 777, 1111 .etc
    private boolean checkForSpecialChaldeanNumber() {

        int a = numberArray.get(0);

        for (int i = 1; i < numberArray.size(); i++) {
            if (a != (int) numberArray.get(i)) {
                return false;
            }
        }

        return true;
    }


    private void about()
    {
        System.out.println("GitHub.com/ParadigmZero");
    }
}
