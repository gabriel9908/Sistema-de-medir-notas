import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarNotas extends JFrame {
    private final Aluno[] alunos;
    private final JComboBox<String> alunoComboBox;
    private final JTextField[] notaFields;
    private final Main mainFrame;

    public ModificarNotas(Aluno[] alunos, Main mainFrame) {
        this.alunos = alunos;
        this.mainFrame = mainFrame;
        setTitle("Modificar Notas");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.setBackground(new Color(30, 30, 30));

        JLabel alunoLabel = new JLabel("Selecione o Aluno:");
        alunoLabel.setForeground(Color.WHITE);
        alunoComboBox = new JComboBox<>();
        for (Aluno aluno : alunos) {
            alunoComboBox.addItem(aluno.getNome());
        }

        panel.add(alunoLabel);
        panel.add(alunoComboBox);

        notaFields = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            JLabel notaLabel = new JLabel("Nota " + (i + 1) + ":");
            notaLabel.setForeground(Color.WHITE);
            notaFields[i] = new JTextField();
            panel.add(notaLabel);
            panel.add(notaFields[i]);
        }

        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("Arial", Font.BOLD, 16));
        salvarButton.setBackground(new Color(70, 130, 180));
        salvarButton.setForeground(Color.WHITE);
        salvarButton.addActionListener((ActionEvent e) -> salvarNotas());

        add(panel, BorderLayout.CENTER);
        add(salvarButton, BorderLayout.SOUTH);

        alunoComboBox.addActionListener((ActionEvent e) -> carregarNotas());
        carregarNotas();
    }

    private void carregarNotas() {
        int selectedIndex = alunoComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            double[] notas = alunos[selectedIndex].getNotas();
            for (int i = 0; i < 4; i++) {
                notaFields[i].setText(String.valueOf(notas[i]));
            }
        }
    }

    private void salvarNotas() {
        int selectedIndex = alunoComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                double[] novasNotas = new double[4];
                for (int i = 0; i < 4; i++) {
                    novasNotas[i] = Double.parseDouble(notaFields[i].getText());
                }
                alunos[selectedIndex].setNotas(novasNotas);
                JOptionPane.showMessageDialog(this, "Notas atualizadas com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                mainFrame.refresh();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para as notas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
