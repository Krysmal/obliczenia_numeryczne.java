package edu.lab13;

import observer.*;
import solver.*;

import javax.swing.*;
import java.awt.*;

public class CMainForm extends JFrame {
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu menuPlik;
    private JMenu menuOptions;
    private JMenuItem itemExit;
    private JMenuItem itemSolve;
    private JMenuItem itemAbout;
    private JRadioButton rbFirst;
    private JRadioButton rbSecond;
    private JRadioButton rbFourth;
    private JTextField alphaTextField;
    private JTextField tkTextField;
    private JTextField omegaTextField;
    private JCheckBox cbPanel;
    private JCheckBox cbFile;
    private JCheckBox cbConsole;
    private JList<Object> list1;

    private DefaultListModel<Object> model;

    public CMainForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        list1.setModel(model);


        itemExit.addActionListener(actionEvent -> CMainForm.this.dispose());
        itemAbout.addActionListener(actionEvent->JOptionPane.showMessageDialog(
                CMainForm.this, "Programowanie obiektowe\nlabolatorium 13",
                "o programie", JOptionPane.INFORMATION_MESSAGE));
        itemSolve.addActionListener(actionEvent -> solveActionPerformed());
    }

    private void solveActionPerformed() {
        CSolverCreator sc = new CSolverCreator();
        ESolverType st= ESolverType.FIRST_ORDER;
        if(rbSecond.isSelected()) st = ESolverType.SECOND_ORDER;
        else if (rbFourth.isSelected()) st = ESolverType.FOURTH_ORDER;

        CStepData init = new CStepData(
                Double.parseDouble(tkTextField.getText().trim()),
                Double.parseDouble(alphaTextField.getText().trim()),
                Double.parseDouble(omegaTextField.getText().trim())
        );

        CSolver solverObj= sc.getSolver(st, init);

        if(cbPanel.isSelected())
            solverObj.addObserver(new CJlistObserver(model));
        if(cbConsole.isSelected())
            solverObj.addObserver(new CConsoleObserver());
        if(cbFile.isSelected())
            solverObj.addObserver(new CFileObserver());

        solverObj.solve();
        }
}


