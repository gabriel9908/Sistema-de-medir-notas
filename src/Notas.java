public class Notas {
    private double[] notas;

    public Notas(double[] notas) {
        if (notas.length != 4) {
            throw new IllegalArgumentException("É necessário fornecer 4 notas para os bimestres.");
        }
        this.notas = notas;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        if (notas.length != 4) {
            throw new IllegalArgumentException("É necessário fornecer 4 notas para os bimestres.");
        }
        this.notas = notas;
    }

    public double calcularMedia() {
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.length;
    }
}
