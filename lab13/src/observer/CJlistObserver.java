package observer;
import solver.CStepData;

import javax.swing.*;

public class CJlistObserver implements IObserver{
    private DefaultListModel<Object> model;

    public CJlistObserver(DefaultListModel<Object> model){
        model.clear();
        model.addElement("Czas [s] Alfa [rad]   Omega[rad/s]");
        this.model=model;
    }

    @Override
    public void update(CStepData data) {
        model.addElement(String.format("%-6.4f.    %-10.7f     %-10.7f", data.T,data.Alpha,data.Omega));
    }
}
