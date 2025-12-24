public class Carga {
    private int id;
    private int tipo;
    private int urgencia;
    private int peso;
    private String descricao;
    private int prioridade;

    public Carga(int id, int tipo, int urgencia, int peso, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.urgencia = urgencia;
        this.peso = peso;
        this.descricao = descricao;
        this.prioridade = calcularPrioridade();
    }

    private int calcularPrioridade() {
        return (urgencia * 10) + (peso * 2) + (tipo * 5);
    }

    public int getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public int getPeso() {
        return peso;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public boolean temMaiorPrioridadeQue(Carga outra) {
        if (this.prioridade != outra.prioridade) {
            return this.prioridade > outra.prioridade;
        }
        if (this.urgencia != outra.urgencia) {
            return this.urgencia > outra.urgencia;
        }
        if (this.peso != outra.peso) {
            return this.peso > outra.peso;
        }
        return this.id < outra.id;
    }

    @Override
    public String toString() {
        return String.format("%-4d | %-4d | %-8d | %-4d | %-10d | %s",
                id, tipo, urgencia, peso, prioridade, descricao);
    }
}