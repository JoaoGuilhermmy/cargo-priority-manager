import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SistemaCarga {
    private HeapMaxima heap;
    private Scanner scanner;

    public SistemaCarga() {
        this.heap = new HeapMaxima(10);
        this.scanner = new Scanner(System.in);
    }

    public void carregarDeArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = br.readLine();
            int carregadas = 0;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length == 5) {
                    try {
                        int id = Integer.parseInt(dados[0].trim());
                        int tipo = Integer.parseInt(dados[1].trim());
                        int urgencia = Integer.parseInt(dados[2].trim());
                        int peso = Integer.parseInt(dados[3].trim());
                        String descricao = dados[4].trim();

                        if (urgencia < 1 || urgencia > 3) {
                            System.out.println("Linha invalida (urgencia fora do intervalo): " + linha);
                            continue;
                        }

                        if (peso < 0) {
                            System.out.println("Linha invalida (peso negativo): " + linha);
                            continue;
                        }

                        Carga carga = new Carga(id, tipo, urgencia, peso, descricao);
                        heap.inserir(carga);
                        carregadas++;

                    } catch (NumberFormatException e) {
                        System.out.println("Linha invalida (formato incorreto): " + linha);
                    }
                }
            }

            System.out.println(carregadas + " cargas carregadas com sucesso do arquivo " + nomeArquivo);

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    public void inserirManualmente() {
        System.out.println("\n=== Inserir Nova Carga ===");

        System.out.print("ID: ");
        int id = scanner.nextInt();

        System.out.print("Tipo (1-9): ");
        int tipo = scanner.nextInt();

        System.out.print("Urgencia (1=baixa, 2=media, 3=alta): ");
        int urgencia = scanner.nextInt();

        if (urgencia < 1 || urgencia > 3) {
            System.out.println("Urgencia invalida! Deve ser entre 1 e 3.");
            scanner.nextLine();
            return;
        }

        System.out.print("Peso (kg): ");
        int peso = scanner.nextInt();

        if (peso < 0) {
            System.out.println("Peso invalido! Deve ser maior ou igual a zero.");
            scanner.nextLine();
            return;
        }

        scanner.nextLine();
        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();

        Carga carga = new Carga(id, tipo, urgencia, peso, descricao);
        heap.inserir(carga);

        System.out.println("Carga inserida com sucesso! Prioridade calculada: " + carga.getPrioridade());
    }

    public void exibirMaiorPrioridade() {
        Carga topo = heap.consultarTopo();

        if (topo == null) {
            System.out.println("\nNenhuma carga cadastrada.");
            return;
        }

        System.out.println("\n=== Carga de Maior Prioridade ===");
        System.out.println("ID   | Tipo | Urgencia | Peso | Prioridade | Descricao");
        System.out.println("-".repeat(75));
        System.out.println(topo);
        System.out.println();
    }

    public void removerMaiorPrioridade() {
        Carga removida = heap.removerMaximo();

        if (removida == null) {
            System.out.println("\nNenhuma carga para remover.");
            return;
        }

        System.out.println("\n=== Carga Removida ===");
        System.out.println("ID   | Tipo | Urgencia | Peso | Prioridade | Descricao");
        System.out.println("-".repeat(75));
        System.out.println(removida);
        System.out.println("\nCarga removida com sucesso!");
    }

    public void exibirTodasCargas() {
        heap.exibirTodas();
    }

    public void executarMenu() {
        int opcao;

        do {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("    SISTEMA DE DISTRIBUICAO DE CARGA");
            System.out.println("=".repeat(50));
            System.out.println("1 - Carregar cargas de arquivo CSV");
            System.out.println("2 - Inserir nova carga");
            System.out.println("3 - Exibir carga de maior prioridade");
            System.out.println("4 - Remover carga de maior prioridade");
            System.out.println("5 - Exibir todas as cargas ordenadas por prioridade");
            System.out.println("6 - Sair");
            System.out.println("=".repeat(50));
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do arquivo CSV: ");
                    String arquivo = scanner.nextLine();
                    carregarDeArquivo(arquivo);
                    break;

                case 2:
                    inserirManualmente();
                    break;

                case 3:
                    exibirMaiorPrioridade();
                    break;

                case 4:
                    removerMaiorPrioridade();
                    break;

                case 5:
                    exibirTodasCargas();
                    break;

                case 6:
                    System.out.println("\nEncerrando sistema...");
                    break;

                default:
                    System.out.println("\nOpcao invalida!");
            }

        } while (opcao != 6);

        scanner.close();
    }

    public static void main(String[] args) {
        SistemaCarga sistema = new SistemaCarga();
        sistema.executarMenu();
    }
}