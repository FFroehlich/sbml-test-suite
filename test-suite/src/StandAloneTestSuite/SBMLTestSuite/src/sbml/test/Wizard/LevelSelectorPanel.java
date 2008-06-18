
package sbml.test.Wizard;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;


public class LevelSelectorPanel extends WizardPanel implements ItemListener {

    private JRadioButton L1V2radioButton,  L2V1radioButton,  L2V2radioButton,  L2V3radioButton;
    //ButtonGroup buttonGroup = new ButtonGroup();
    String level;
    private CreateTestWizard createTestWizard;
    private HashMap<String, Object> selections = new HashMap<String, Object>();

    public LevelSelectorPanel(CreateTestWizard createTestWizard) {
        this.createTestWizard = createTestWizard;
        initComponents();

    }

    private void initComponents() {
        selections = createTestWizard.getSelections();
        System.out.println("hash map in levels is on initialization: " + selections.values());
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel(getQualifiedName());
        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        topPanel.add(nameLabel, BorderLayout.PAGE_START);
        topPanel.add(new JSeparator(), BorderLayout.PAGE_END);
        add(topPanel, BorderLayout.PAGE_START);

        JPanel contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        L1V2radioButton = new JRadioButton("Version 2");
        L1V2radioButton.setActionCommand("L1V2radioButton");
        L2V1radioButton = new JRadioButton("Version 1");
        L2V1radioButton.setActionCommand("L2V1radioButton");
        L2V2radioButton = new JRadioButton("Version 2");
        L2V2radioButton.setActionCommand("L2V2radioButton");
        L2V3radioButton = new JRadioButton("Version 3");
        L2V3radioButton.setActionCommand("L2V3radioButton");
        L2V3radioButton.setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(L1V2radioButton);
        buttonGroup.add(L2V1radioButton);
        buttonGroup.add(L2V2radioButton);
        buttonGroup.add(L2V3radioButton);
        
        L1V2radioButton.addItemListener(this);
        L2V1radioButton.addItemListener(this);
        L2V2radioButton.addItemListener(this);
        L2V3radioButton.addItemListener(this);
        
        JPanel contentPanel1 = new JPanel(new GridLayout(0,2,80,10)); 
        contentPanel1.add(new JLabel("Level 1"));
        contentPanel1.add(new JLabel("Level 2"));
        contentPanel1.add(L1V2radioButton);        
        contentPanel1.add(L2V1radioButton);
        contentPanel1.add(new JLabel());
        contentPanel1.add(L2V2radioButton);
        contentPanel1.add(new JLabel());
        contentPanel1.add(L2V3radioButton);
        JPanel contentPanel2 = new JPanel(new BorderLayout()); 
        contentPanel.add(contentPanel2, BorderLayout.NORTH);
        contentPanel2.add(contentPanel1, BorderLayout.WEST);
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        messagePanel.add(new JLabel("Please select the desired SBML Level:"), BorderLayout.NORTH);
        contentPanel2.add(messagePanel, BorderLayout.NORTH);       
    }
    

    public void itemStateChanged(ItemEvent e){
     int state = e.getStateChange();
     if(state == ItemEvent.SELECTED) {
      if (e.getSource() == L1V2radioButton){
          // change the selections hash
          updateSelections("L1V2radiobutton",1);
          updateSelections("L2V1radiobutton",0);
          updateSelections("L2V2radiobutton",0);
          updateSelections("L2V3radiobutton",0);
          //createTestWizard.setSelections(selections);
          //propertyChangeSupport.firePropertyChange();
      }
      else if (e.getSource() == L2V1radioButton) {
          updateSelections("L1V2radiobutton",0);
          updateSelections("L2V1radiobutton",1);
          updateSelections("L2V2radiobutton",0);
          updateSelections("L2V3radiobutton",0);
          //createTestWizard.setSelections(selections);
      }
      else if (e.getSource() == L2V2radioButton) {
          updateSelections("L1V2radiobutton",0);
          updateSelections("L2V1radiobutton",0);
          updateSelections("L2V2radiobutton",1);
          updateSelections("L2V3radiobutton",0);
         // createTestWizard.setSelections(selections);
      }
      else if(e.getSource() == L2V3radioButton){
          updateSelections("L1V2radiobutton",0);
          updateSelections("L2V1radiobutton",0);
          updateSelections("L2V2radiobutton",0);
          updateSelections("L2V3radiobutton",1);
          //createTestWizard.setSelections(selections);
      }
   }
     createTestWizard.setSelections(selections);

    } 
   
    public void updateSelections(String field, Integer value){
        // get the selection hashmap, and replace the field with the new value and write it back
        selections.put(field, value);
    }
  

    public String getQualifiedName() {
        return "Select Levels";
    }

    public String getIdentifier() {
        return "LEVELSELECTOR";
    }

    public String getPrevious() {
        return null;
    }

    public String getNext() {
        
        return "TESTTYPE";
    }

    public boolean isLast() {
        return false;
    }

    public boolean isFirst() {
        return true;
    }

    public boolean mayFinish() {
        return false;
    }

    public void validateSelections(HashMap<String, Object> selections) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
}