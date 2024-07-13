import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
    private Aluno[] alunos;
    private final JPanel panel;

    public Main() {
        setTitle("Sistema de Medir Notas");
        setSize(1000, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        alunos = new Aluno[]{
            new Aluno("Alane Costa", new double[]{7.5, 8.0, 9.0, 7.0}),
            new Aluno("Luiz Fernando", new double[]{6.5, 7.0, 8.5, 9.0}),
            new Aluno("Maria Clara", new double[]{9.0, 9.5, 10.0, 8.5}),
            new Aluno("João Pedro", new double[]{5.5, 6.0, 7.0, 6.5}),
            new Aluno("Ana Beatriz", new double[]{8.0, 8.5, 9.0, 9.5}),
            new Aluno("Gabriel Souza", new double[]{7.0, 7.5, 8.0, 8.5}),
            new Aluno("Mariana Lima", new double[]{6.0, 6.5, 7.0, 7.5}),
            new Aluno("Lucas Oliveira", new double[]{9.0, 8.0, 7.0, 8.5}),
            new Aluno("Rafaela Santos", new double[]{7.5, 8.0, 8.5, 9.0}),
            new Aluno("Pedro Henrique", new double[]{6.0, 7.0, 8.0, 7.5}),
            new Aluno("Juliana Carvalho", new double[]{8.5, 9.0, 9.5, 10.0}),
            new Aluno("Ricardo Alves", new double[]{7.0, 7.5, 8.0, 8.5}),
            new Aluno("Sofia Costa", new double[]{6.5, 7.0, 7.5, 8.0}),
            new Aluno("Felipe Rocha", new double[]{9.5, 9.0, 8.5, 8.0}),
            new Aluno("Camila Martins", new double[]{8.0, 8.5, 9.0, 9.5})
        };

        panel = new JPanel();
        panel.setLayout(new GridLayout(16, 5));
        panel.setBackground(new Color(30, 30, 30));

        updatePanel();

        JButton calcularButton = new JButton("Calcular Média");
        calcularButton.setFont(new Font("Arial", Font.BOLD, 16));
        calcularButton.setBackground(new Color(70, 130, 180));
        calcularButton.setForeground(Color.WHITE);
        calcularButton.addActionListener((ActionEvent e) -> calcularMedia());

        JButton modificarNotasButton = new JButton("Modificar Notas");
        modificarNotasButton.setFont(new Font("Arial", Font.BOLD, 16));
        modificarNotasButton.setBackground(new Color(70, 130, 180));
        modificarNotasButton.setForeground(Color.WHITE);
        modificarNotasButton.addActionListener((ActionEvent e) -> {
            ModificarNotas modificarNotas = new ModificarNotas(alunos, this);
            modificarNotas.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.add(calcularButton);
        buttonPanel.add(modificarNotasButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updatePanel() {
        panel.removeAll();
        JLabel[] nameLabels = new JLabel[alunos.length];
        JLabel[][] notaLabels = new JLabel[alunos.length][4];
        JLabel[] mediaLabels = new JLabel[alunos.length];

        Font font = new Font("Arial", Font.PLAIN, 14);

        for (int i = 0; i < alunos.length; i++) {
            nameLabels[i] = new JLabel(alunos[i].getNome());
            nameLabels[i].setFont(font);
            nameLabels[i].setForeground(new Color(0, 255, 127));
            panel.add(nameLabels[i]);

            double[] notas = alunos[i].getNotas();
            for (int j = 0; j < notas.length; j++) {
                notaLabels[i][j] = new JLabel(String.valueOf(notas[j]));
                notaLabels[i][j].setFont(font);
                notaLabels[i][j].setForeground(new Color(135, 206, 250));
                panel.add(notaLabels[i][j]);
            }

            mediaLabels[i] = new JLabel(String.valueOf(alunos[i].calcularMedia()));
            mediaLabels[i].setFont(font);
            mediaLabels[i].setForeground(new Color(255, 69, 0));
            panel.add(mediaLabels[i]);
        }
        panel.revalidate();
        panel.repaint();
    }

    private void calcularMedia() {
        for (Aluno aluno : alunos) {
            aluno.calcularMedia();
        }
        JOptionPane.showMessageDialog(this, "Médias calculadas e atualizadas!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    public void refresh() {
        updatePanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
