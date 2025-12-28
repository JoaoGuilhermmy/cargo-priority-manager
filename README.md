# 📦 Sistema de Distribuição de Carga

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Data Structures](https://img.shields.io/badge/Data_Structures-Max%20Heap-orange?style=for-the-badge)
![Algorithm](https://img.shields.io/badge/Algorithm-Priority%20Queue-green?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**Sistema inteligente de priorização de cargas usando Max Heap**

[🇺🇸 English Version](README.en.md) | [📖 Sobre](#-sobre-o-projeto) | [🚀 Instalação](#-instalação) | [💡 Funcionalidades](#-funcionalidades)

---

</div>

## 📋 Sobre o Projeto

O **Sistema de Distribuição de Carga** é uma aplicação desenvolvida em Java que simula um sistema de logística onde cargas são priorizadas para transporte. O sistema utiliza:

- **Max Heap**: Para manter cargas ordenadas por prioridade
- **Fórmula de Priorização**: `Prioridade = Tipo × Urgência + Peso`
- **Importação CSV**: Carregamento em lote de cargas
- **Interface Interativa**: Menu completo para gestão

### 🎯 Objetivos

- Demonstrar **implementação de Max Heap em Java**
- Aplicar **fórmula de priorização complexa**
- Criar **sistema de importação de dados**
- Implementar **gerenciamento dinâmico de capacidade**
- Desenvolver **interface CLI profissional**

### ✨ Características Principais

- 🏔️ **Max Heap** - Prioridade máxima sempre no topo
- 📊 **Priorização Inteligente** - Combina tipo, urgência e peso
- 📁 **Importação CSV** - Carregamento em lote
- 🔄 **Capacidade Dinâmica** - Heap cresce automaticamente
- 🎨 **Interface Formatada** - Tabelas e separadores visuais
- ⚡ **Performance** - Operações O(log n)

---

## 🏗️ Arquitetura

### Estrutura do Projeto

```
sistema-carga/
├── src/
│   ├── Carga.java          # Classe de domínio
│   ├── HeapMaxima.java     # Implementação do heap
│   └── SistemaCarga.java   # Interface e orquestração
├── cargas.csv              # Dados de exemplo
└── README.md
```

### 📊 Classes e Responsabilidades

#### Classe `Carga`

```java
public class Carga {
    private int id;
    private int tipo;           // 1-9
    private int urgencia;       // 1 (baixa), 2 (média), 3 (alta)
    private int peso;           // em kg
    private String descricao;
    private int prioridade;     // Calculada automaticamente
    
    public Carga(int id, int tipo, int urgencia, int peso, String descricao);
    public int getPrioridade();
    public boolean temMaiorPrioridadeQue(Carga outra);
}
```

**Responsabilidades**:
- Armazenar dados da carga
- Calcular prioridade automaticamente
- Comparar prioridades

#### Fórmula de Prioridade

```java
private void calcularPrioridade() {
    this.prioridade = (tipo * urgencia) + peso;
}
```

**Exemplo**:
```
Tipo: 9, Urgência: 3, Peso: 50 kg
Prioridade = (9 × 3) + 50 = 27 + 50 = 77
```

#### Classe `HeapMaxima`

```java
public class HeapMaxima {
    private Carga[] heap;
    private int quantidade;
    private int capacidade;
    
    public HeapMaxima(int capacidadeInicial);
    public void inserir(Carga novaCarga);
    public Carga removerMaximo();
    public Carga consultarTopo();
    public void exibirTodas();
    private void subir(int i);
    private void descer(int i);
    private void garantirCapacidade();
}
```

**Responsabilidades**:
- Manter propriedade de heap
- Inserir com heapify up
- Remover máximo com heapify down
- Expandir capacidade dinamicamente

#### Classe `SistemaCarga`

```java
public class SistemaCarga {
    private HeapMaxima heap;
    private Scanner scanner;
    
    public void carregarDeArquivo(String nomeArquivo);
    public void inserirManualmente();
    public void exibirMaiorPrioridade();
    public void removerMaiorPrioridade();
    public void exibirTodasCargas();
    public void executarMenu();
}
```

**Responsabilidades**:
- Gerenciar interação com usuário
- Importar dados de CSV
- Orquestrar operações do heap

---

## 💡 Funcionalidades

### 🔄 Fluxo de Dados

```
┌────────────────────────┐
│   cargas.csv           │
│  1,9,3,50,Medicamentos │
│  2,5,2,70,Eletronicos  │
└──────────┬─────────────┘
           │
           ▼
┌──────────────────────────────┐
│   Parse e Validação          │
│   - Verificar urgência 1-3   │
│   - Verificar peso >= 0      │
│   - Calcular prioridade      │
└──────────┬───────────────────┘
           │
           ▼
┌──────────────────────────────┐
│   Inserção no Heap           │
│   1. Adicionar no final      │
│   2. Subir (heapify up)      │
│   3. Restaurar propriedade   │
└──────────┬───────────────────┘
           │
           ▼
┌──────────────────────────────┐
│   Max Heap (Array)           │
│                               │
│       [Prioridade 77]         │
│       /              \        │
│  [Prioridade 51]  [Prioridade 42] │
└──────────┬───────────────────┘
           │
           ▼
┌──────────────────────────────┐
│   Operações                  │
│   - Consultar topo: O(1)     │
│   - Remover máximo: O(log n) │
│   - Exibir todas: O(n log n) │
└──────────────────────────────┘
```

### 🔍 Algoritmos do Heap

#### Inserção (Heapify Up)

```java
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
```

**Processo**:
1. Inserir no final do array
2. Comparar com pai
3. Se filho > pai, trocar
4. Repetir até raiz ou heap restaurado

**Exemplo Visual**:
```
Inserir carga com prioridade 80:

    [50]                    [80]
    /  \          →        /  \
 [30]  [20]            [50]  [20]
  /                     /
[80]                 [30]

Passos:
1. Inserir [80] como filho de [30]
2. [80] > [30] → trocar
3. [80] > [50] → trocar
4. Heap restaurado
```

#### Remoção do Máximo (Heapify Down)

```java
public Carga removerMaximo() {
    if (quantidade == 0) return null;
    
    Carga maximo = heap[1];
    heap[1] = heap[quantidade];
    heap[quantidade] = null;
    quantidade--;
    
    if (quantidade > 0) {
        descer(1);
    }
    
    return maximo;
}

private void descer(int i) {
    while (2 * i <= quantidade) {
        int filhoEsq = 2 * i;
        int filhoDir = 2 * i + 1;
        int maior = filhoEsq;
        
        if (filhoDir <= quantidade && 
            heap[filhoDir].temMaiorPrioridadeQue(heap[filhoEsq])) {
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
```

**Processo**:
1. Salvar raiz (máximo)
2. Mover último elemento para raiz
3. Comparar com filhos
4. Trocar com maior filho
5. Repetir até folha ou heap restaurado

#### Expansão Dinâmica

```java
private void garantirCapacidade() {
    int novaCapacidade = capacidade * 2;
    Carga[] novoHeap = new Carga[novaCapacidade + 1];
    
    for (int i = 1; i <= quantidade; i++) {
        novoHeap[i] = heap[i];
    }
    
    heap = novoHeap;
    capacidade = novaCapacidade;
}
```

**Estratégia**: Dobrar capacidade quando cheio (amortizado O(1) por inserção).

---

## 🚀 Instalação

### Pré-requisitos

- **Java JDK 8+**
- **Sistema Operacional**: Windows, Linux ou macOS

### Compilação e Execução

```bash
# Clone o repositório
git clone https://github.com/JoaoGuilhermmy/sistema-carga-java.git
cd sistema-carga-java

# Compile
javac src/*.java

# Execute
java -cp src SistemaCarga
```

---

## 💻 Como Usar

### Formato do CSV (cargas.csv)

```csv
1,9,3,50,Medicamentos
2,5,2,70,Eletronicos
3,3,1,90,Roupas
4,9,3,30,Vacinas
5,5,2,80,Eletrodomesticos
```

**Formato**: `id,tipo,urgencia,peso,descricao`

**Regras**:
- `urgencia`: 1 (baixa), 2 (média), 3 (alta)
- `peso`: >= 0
- `tipo`: 1-9

### Exemplo de Uso Completo

```
==================================================
    SISTEMA DE DISTRIBUICAO DE CARGA
==================================================
1 - Carregar cargas de arquivo CSV
2 - Inserir nova carga
3 - Exibir carga de maior prioridade
4 - Remover carga de maior prioridade
5 - Exibir todas as cargas ordenadas por prioridade
6 - Sair
==================================================
Escolha uma opcao: 1

Nome do arquivo CSV: cargas.csv
5 cargas carregadas com sucesso do arquivo cargas.csv

Escolha uma opcao: 5

===========================================================================
ID   | Tipo | Urgencia | Peso | Prioridade | Descricao
---------------------------------------------------------------------------
4    | 9    | 3        | 30   | 57         | Vacinas
1    | 9    | 3        | 50   | 77         | Medicamentos
5    | 5    | 2        | 80   | 90         | Eletrodomesticos
2    | 5    | 2        | 70   | 80         | Eletronicos
3    | 3    | 1        | 90   | 93         | Roupas
===========================================================================

Escolha uma opcao: 3

=== Carga de Maior Prioridade ===
ID   | Tipo | Urgencia | Peso | Prioridade | Descricao
---------------------------------------------------------------------------
3    | 3    | 1        | 90   | 93         | Roupas

Escolha uma opcao: 4

=== Carga Removida ===
ID   | Tipo | Urgencia | Peso | Prioridade | Descricao
---------------------------------------------------------------------------
3    | 3    | 1        | 90   | 93         | Roupas

Carga removida com sucesso!

Escolha uma opcao: 2

=== Inserir Nova Carga ===
ID: 6
Tipo (1-9): 7
Urgencia (1=baixa, 2=media, 3=alta): 3
Peso (kg): 100
Descricao: Equipamentos Medicos

Carga inserida com sucesso! Prioridade calculada: 121

Escolha uma opcao: 6

Encerrando sistema...
```

---

## 🔧 Detalhes Técnicos

### Índices do Heap

```
Array: [null, 93, 90, 80, 77, 57]
Índices: 0     1   2   3   4   5

Árvore:
           [93]
          /    \
       [90]    [80]
       /  \
    [77] [57]

Relações:
- Pai de i: i/2
- Filho esquerdo de i: 2*i
- Filho direito de i: 2*i + 1
```

**Nota**: Índice 0 é mantido nulo para simplificar cálculos.

### Exibição Ordenada sem Alterar Heap Original

```java
public void exibirTodas() {
    // Criar cópia do heap
    Carga[] copiaHeap = new Carga[quantidade + 1];
    for (int i = 1; i <= quantidade; i++) {
        copiaHeap[i] = heap[i];
    }
    
    int qtdCopia = quantidade;
    
    // Extrair máximos sucessivamente
    while (qtdCopia > 0) {
        Carga max = copiaHeap[1];
        System.out.println(max);
        
        // Mover último para raiz
        copiaHeap[1] = copiaHeap[qtdCopia];
        qtdCopia--;
        
        // Heapify down na cópia
        // ... (código de descer)
    }
}
```

**Vantagem**: Não modifica heap original, permite múltiplas visualizações.

### Tratamento de Erros na Importação

```java
public void carregarDeArquivo(String nomeArquivo) {
    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
        String linha = br.readLine(); // Pular cabeçalho
        int carregadas = 0;
        
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            
            if (dados.length == 5) {
                try {
                    int urgencia = Integer.parseInt(dados[2].trim());
                    int peso = Integer.parseInt(dados[3].trim());
                    
                    // Validações
                    if (urgencia < 1 || urgencia > 3) {
                        System.out.println("Linha invalida (urgencia): " + linha);
                        continue;
                    }
                    
                    if (peso < 0) {
                        System.out.println("Linha invalida (peso): " + linha);
                        continue;
                    }
                    
                    // Criar e inserir carga
                    Carga carga = new Carga(...);
                    heap.inserir(carga);
                    carregadas++;
                    
                } catch (NumberFormatException e) {
                    System.out.println("Linha invalida (formato): " + linha);
                }
            }
        }
        
        System.out.println(carregadas + " cargas carregadas com sucesso");
        
    } catch (IOException e) {
        System.out.println("Erro ao ler arquivo: " + e.getMessage());
    }
}
```

---

## 📊 Complexidade Computacional

| Operação | Complexidade | Justificativa |
|----------|--------------|---------------|
| Inserir carga | O(log n) | Heapify up |
| Remover máximo | O(log n) | Heapify down |
| Consultar topo | O(1) | Acesso direto |
| Exibir todas (ordenadas) | O(n log n) | n remoções |
| Buscar por ID | O(n) | Busca linear |
| Expansão de capacidade | O(n) amortizado | Dobra de tamanho |

### Análise de Espaço

```
Capacidade inicial: 10
Após 10 inserções: capacidade 10 (cheia)
11ª inserção: expande para 20 (copia 10 elementos)
21ª inserção: expande para 40 (copia 20 elementos)

Total de cópias para n inserções: < 2n
Complexidade amortizada: O(1) por inserção
```

---

## 🎓 Conceitos Aplicados

### Max Heap

**Propriedade**: Pai sempre >= filhos

**Aplicações**:
- ✅ Fila de prioridade
- ✅ Algoritmo de Dijkstra
- ✅ Heap Sort
- ✅ Escalonamento de tarefas

### Comparação com Min Heap

| Aspecto | Max Heap | Min Heap |
|---------|----------|----------|
| Raiz | Máximo | Mínimo |
| Uso | Maior prioridade | Menor valor |
| Exemplo | Urgências | Distâncias |

### Comparação com Outras Estruturas

| Estrutura | Inserir | Remover Max | Consultar Max |
|-----------|---------|-------------|---------------|
| Array não ordenado | O(1) | O(n) | O(n) |
| Array ordenado | O(n) | O(1) | O(1) |
| BST | O(log n)* | O(log n)* | O(log n)* |
| **Max Heap** | **O(log n)** | **O(log n)** | **O(1)** |

*Não garantido sem balanceamento

---

## 🐛 Solução de Problemas

### Problema: CSV não carrega

**Sintomas**: "Erro ao ler arquivo"

**Soluções**:
1. Verificar caminho do arquivo
2. Verificar permissões
3. Verificar formato (vírgulas, sem espaços extras)

### Problema: Prioridades incorretas

**Causa**: Fórmula de prioridade mal compreendida

**Solução**:
```java
// Verificar cálculo
Prioridade = (tipo × urgencia) + peso

Exemplo:
Tipo 9, Urgência 3, Peso 50
= (9 × 3) + 50
= 27 + 50
= 77 ✅
```

### Problema: OutOfMemoryError

**Causa**: Heap muito grande

**Solução**:
```bash
# Aumentar heap da JVM
java -Xmx2g -cp src SistemaCarga
```

---

## 📈 Possíveis Melhorias

### Curto Prazo
- [ ] Persistência em banco de dados
- [ ] Busca por ID eficiente
- [ ] Logs de operações
- [ ] Testes unitários (JUnit)

### Médio Prazo
- [ ] Interface gráfica (JavaFX)
- [ ] Relatórios em PDF
- [ ] API REST
- [ ] Autenticação de usuários

### Longo Prazo
- [ ] Sistema distribuído
- [ ] Dashboard em tempo real
- [ ] Machine Learning para predição
- [ ] Integração com ERPs

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**João Guilhermmy**

- 🔗 GitHub: [https://github.com/JoaoGuilhermmy](https://github.com/JoaoGuilhermmy)
- 💼 LinkedIn: [www.linkedin.com/in/joão-guilhermmy-93661b29b](https://www.linkedin.com/in/joão-guilhermmy-93661b29b)
- 📧 Email: joaoguilhermmy2@gmail.com

---

## 🙏 Agradecimentos

- Comunidade Java pelos recursos educacionais
- Desenvolvedores que contribuíram com feedback
- Professores e mentores

---

<div align="center">

### ⭐ Se este projeto foi útil, considere dar uma estrela!

**Desenvolvido com ❤️ e muito ☕**

### 💡 Projeto educacional demonstrando Max Heap em Java

</div>
