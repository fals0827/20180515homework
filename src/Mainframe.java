import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainframe extends JFrame{
    private JLabel jlb = new JLabel("0.0");
    private String[] btText = {"7","8","9","/","AC",
            "4","5","6","*","sqrt",
            "1","2","3","-","PI",
            "0",".","=","+","EXIT"};
    private JButton [] jbtns = new JButton[20];
    private Double sum = 0.0, v1 = 0.0;
    private int op = -1 ;
    private String str = "";
    private Container cp ;
    private JPanel jpnN = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel jpnC = new JPanel(new GridLayout(4,5,2,2));
    private Boolean eqFirst = true;
    private ImageIcon imgIcons [] = new ImageIcon[10];
    private String imgName = "";
    private int length = 0;

    public Mainframe (){
        this.init();
    }

    private void init (){
        setBounds(100,100,550,650);
        setTitle("計算機");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp = this.getContentPane();
        cp.add(jpnN , BorderLayout.NORTH);
        cp.add(jpnC , BorderLayout.CENTER);
        jpnN.add(jlb);
        jlb.setFont(new Font(null,Font.BOLD,30));

        for (int i = 0 ; i < 10 ; i++){
            imgName = Integer.toString(i) + ".png";
            imgIcons[i] = new ImageIcon(imgName);
        }

        for (int i = 0 ; i < 20 ; i ++){
            jbtns[i] = new JButton(btText[i]);
            jbtns[i].setFont(new Font(null,Font.BOLD,30));
            jpnC.add(jbtns[i]);
        }

        jbtns[0].setIcon(imgIcons[7]);
        jbtns[1].setIcon(imgIcons[8]);
        jbtns[2].setIcon(imgIcons[9]);
        jbtns[5].setIcon(imgIcons[4]);
        jbtns[6].setIcon(imgIcons[5]);
        jbtns[7].setIcon(imgIcons[6]);
        jbtns[10].setIcon(imgIcons[1]);
        jbtns[11].setIcon(imgIcons[2]);
        jbtns[12].setIcon(imgIcons[3]);
        jbtns[15].setIcon(imgIcons[0]);

        for (int i = 0 ; i < 20 ; i ++) {
            switch (jbtns[i].getText()) {
                case "AC":
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            sum = 0.0;
                            v1 = 0.0;
                            str = "";
                            op = -1;
                            jlb.setText("0.0");
                            eqFirst = true;
                        }
                    });
                    break;
                case "sqrt":
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            v1 = Math.sqrt(Double.parseDouble(jlb.getText()));
                            jlb.setText(Double.toString(v1));
                            str = "";
                        }
                    });
                    break;
                case "PI":
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            v1 = Math.PI;
                            jlb.setText(Double.toString(v1));
                            str = "";
                        }
                    });
                    break;
                case "EXIT":
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });
                    break;
                case "/":
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            opEq();
                            jlb.setText(Double.toString(sum));
                            str = "";
                            op = 3;
                            eqFirst = false;
                        }
                    });
                    break;
                case "*":
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            opEq();
                            jlb.setText(Double.toString(sum));
                            str = "";
                            op = 2;
                            eqFirst = false;
                        }
                    });
                    break;
                case "-":
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            opEq();
                            jlb.setText(Double.toString(sum));
                            str = "";
                            op = 1;
                            eqFirst = false;
                        }
                    });
                    break;
                case "+":
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            opEq();
                            jlb.setText(Double.toString(sum));
                            str = "";
                            op = 0;
                            eqFirst = false;
                        }
                    });
                    break;
                case "=":
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            opEq();
                            jlb.setText(Double.toString(sum));
                            sum = 0.0;
                            v1 = 0.0;
                            str = "";
                            op = -1;
                            eqFirst = true;
                        }
                    });
                    break;
                case "." :
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton tmp = (JButton) e.getSource();
                            length = str.length();
                            for (int j = 0 ; j < length ; j ++){
                                if (!str.contains(".")){
                                    str = str + tmp.getText();
                                    v1 = Double.parseDouble(str);
                                    jlb.setText(str);
                                }
                            }
                        }
                    });
                    break;
                default:
                    jbtns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton tmpBtn = (JButton) e.getSource();
                            for (int j = 0 ; j < 10 ; j ++){
                                if (tmpBtn.getIcon().equals(imgIcons[j])){
                                    str = str + Integer.toString(j);
                                    v1 = Double.parseDouble(str);
                                    jlb.setText(str);
                                }
                            }
                        }
                    });
                    jbtns[i].setText("");
                    break;
            }
        }
    }

    private void opEq () {
        if (eqFirst){
            sum += v1;
        }else {
            switch (op){
                case 0 :
                    sum += v1;
                    break;
                case 1 :
                    sum -= v1;
                    break;
                case 2 :
                    sum *= v1;
                    break;
                case 3 :
                    sum /= v1;
                    break;
            }
        }
    }
}