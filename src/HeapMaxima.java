public class HeapMaxima {
    private Carga[] heap;
    private int quantidade;
    private int capacidade;

    public HeapMaxima(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.heap = new Carga[capacidadeInicial + 1];
        this.quantidade = 0;
    }

    public void inserir(Carga novaCarga) {
        if (quantidade == capacidade) {
            garantirCapacidade();
        }

        quantidade++;
        heap[quantidade] = novaCarga;
        subir(quantidade);
    }

    public Carga removerMaximo() {
        if (quantidade == 0) {
            return null;
        }

        Carga maximo = heap[1];
        heap[1] = heap[quantidade];
        heap[quantidade] = null;
        quantidade--;

        if (quantidade > 0) {
            descer(1);
        }

        return maximo;
    }

    public Carga consultarTopo() {
        return quantidade > 0 ? heap[1] : null;
    }

    public int tamanho() {
        return quantidade;
    }

    public boolean estaVazio() {
        return quantidade == 0;
    }

    private void subir(int i) {
        while (i > 1) {
            int pai = i / 2;
            if (heap[i].temMaiorPrioridadeQue(heap[pai])) {
                trocar(i, pai);
                i = pai;
            } else {
                break;
            }
        }
    }

    private void descer(int i) {
        while (2 * i <= quantidade) {
            int filhoEsq = 2 * i;
            int filhoDir = 2 * i + 1;
            int maior = filhoEsq;

            if (filhoDir <= quantidade && heap[filhoDir].temMaiorPrioridadeQue(heap[filhoEsq])) {
                maior = filhoDir;
            }

            if (heap[maior].temMaiorPrioridadeQue(heap[i])) {
                trocar(i, maior);
                i = maior;
            } else {
                break;
            }
        }
    }

    private void trocar(int i, int j) {
        Carga temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void garantirCapacidade() {
        int novaCapacidade = capacidade * 2;
        Carga[] novoHeap = new Carga[novaCapacidade + 1];

        for (int i = 1; i <= quantidade; i++) {
            novoHeap[i] = heap[i];
        }

        heap = novoHeap;
        capacidade = novaCapacidade;
    }

    public void exibirTodas() {
        if (quantidade == 0) {
            System.out.println("Nenhuma carga cadastrada.");
            return;
        }

        System.out.println("\n" + "=".repeat(75));
        System.out.println("ID   | Tipo | Urgencia | Peso | Prioridade | Descricao");
        System.out.println("-".repeat(75));

        Carga[] copiaHeap = new Carga[quantidade + 1];
        for (int i = 1; i <= quantidade; i++) {
            copiaHeap[i] = heap[i];
        }

        int qtdCopia = quantidade;

        while (qtdCopia > 0) {
            Carga max = copiaHeap[1];
            System.out.println(max);

            copiaHeap[1] = copiaHeap[qtdCopia];
            copiaHeap[qtdCopia] = null;
            qtdCopia--;

            int i = 1;
            while (2 * i <= qtdCopia) {
                int filhoEsq = 2 * i;
                int filhoDir = 2 * i + 1;
                int maior = filhoEsq;

                if (filhoDir <= qtdCopia && copiaHeap[filhoDir].temMaiorPrioridadeQue(copiaHeap[filhoEsq])) {
                    maior = filhoDir;
                }

                if (copiaHeap[maior].temMaiorPrioridadeQue(copiaHeap[i])) {
                    Carga temp = copiaHeap[i];
                    copiaHeap[i] = copiaHeap[maior];
                    copiaHeap[maior] = temp;
                    i = maior;
                } else {
                    break;
                }
            }
        }

        System.out.println("=".repeat(75) + "\n");
    }
}