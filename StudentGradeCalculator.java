import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame {

    private JTextField[] subjectFields;
    private JTextField totalMarksField;
    private JTextField averagePercentageField;
    private JTextField gradeField;

    public StudentGradeCalculator() {
        super("Student Grade Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        getContentPane().setBackground(new Color(173, 216, 230));

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 

        initializeUI();

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        calculateButton.setBackground(new Color(0, 102, 204)); 
        calculateButton.setForeground(Color.WHITE); 

        add(createSubjectPanel());
        add(createResultPanel());
        add(calculateButton);

        setVisible(true);
    }

    private JPanel createSubjectPanel() {
        JPanel subjectPanel = new JPanel();
        subjectPanel.setLayout(new GridLayout(3, 2));

        subjectPanel.setBackground(new Color(173, 216, 230)); 

        subjectPanel.add(new JLabel("Subject 1:"));
        subjectPanel.add(subjectFields[0]);
        subjectPanel.add(new JLabel("Subject 2:"));
        subjectPanel.add(subjectFields[1]);
        subjectPanel.add(new JLabel("Subject 3:"));
        subjectPanel.add(subjectFields[2]);

        return subjectPanel;
    }

    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(3, 2));

        resultPanel.setBackground(new Color(173, 216, 230)); 

        resultPanel.add(new JLabel("Total Marks:"));
        resultPanel.add(totalMarksField);
        resultPanel.add(new JLabel("Average Percentage:"));
        resultPanel.add(averagePercentageField);
        resultPanel.add(new JLabel("Grade:"));
        resultPanel.add(gradeField);

        return resultPanel;
    }

    private void initializeUI() {
        subjectFields = new JTextField[3]; 
        for (int i = 0; i < subjectFields.length; i++) {
            subjectFields[i] = new JTextField();
        }

        totalMarksField = new JTextField();
        totalMarksField.setEditable(false);
        totalMarksField.setBackground(new Color(240, 248, 255)); 

        averagePercentageField = new JTextField();
        averagePercentageField.setEditable(false);
        averagePercentageField.setBackground(new Color(240, 248, 255)); 

        gradeField = new JTextField();
        gradeField.setEditable(false);
        gradeField.setBackground(new Color(240, 248, 255)); 
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int totalMarks = 0;

                for (int i = 0; i < subjectFields.length; i++) {
                    int marks = Integer.parseInt(subjectFields[i].getText());
                    totalMarks += marks;
                }

                double averagePercentage = (double) totalMarks / subjectFields.length;

                totalMarksField.setText(Integer.toString(totalMarks));
                averagePercentageField.setText(String.format("%.2f%%", averagePercentage));

                
                if (averagePercentage >= 90) {
                    gradeField.setText("A");
                } else if (averagePercentage >= 80) {
                    gradeField.setText("B");
                } else if (averagePercentage >= 70) {
                    gradeField.setText("C");
                } else if (averagePercentage >= 60) {
                    gradeField.setText("D");
                } else {
                    gradeField.setText("F");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter numerical values for marks.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeCalculator::new);
    }
}
