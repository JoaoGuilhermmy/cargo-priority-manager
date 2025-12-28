# 📦 Cargo Distribution System

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Data Structures](https://img.shields.io/badge/Data_Structures-Max%20Heap-orange?style=for-the-badge)
![Algorithm](https://img.shields.io/badge/Algorithm-Priority%20Queue-green?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**Intelligent cargo prioritization system using Max Heap**

[🇧🇷 Versão em Português](README.md) | [📖 About](#-about-the-project) | [🚀 Installation](#-installation) | [💡 Features](#-features)

---

</div>

## 📋 About the Project

The **Cargo Distribution System** is an application developed in Java that simulates a logistics system where cargo is prioritized for transport. The system uses:

- **Max Heap**: To keep cargo ordered by priority
- **Prioritization Formula**: `Priority = Type × Urgency + Weight`
- **CSV Import**: Batch cargo loading
- **Interactive Interface**: Complete management menu

### 🎯 Objectives

- Demonstrate **Max Heap implementation in Java**
- Apply **complex prioritization formula**
- Create **data import system**
- Implement **dynamic capacity management**
- Develop **professional CLI interface**

### ✨ Key Features

- 🏔️ **Max Heap** - Maximum priority always at top
- 📊 **Intelligent Prioritization** - Combines type, urgency and weight
- 📁 **CSV Import** - Batch loading
- 🔄 **Dynamic Capacity** - Heap grows automatically
- 🎨 **Formatted Interface** - Tables and visual separators
- ⚡ **Performance** - O(log n) operations

---

## 🏗️ Architecture

### Project Structure

```
cargo-system/
├── src/
│   ├── Carga.java          # Domain class
│   ├── HeapMaxima.java     # Heap implementation
│   └── SistemaCarga.java   # Interface and orchestration
├── cargas.csv              # Sample data
└── README.md
```

### 📊 Classes and Responsibilities

#### `Carga` Class

```java
public class Carga {
    private int id;
    private int tipo;           // 1-9
    private int urgencia;       // 1 (low), 2 (medium), 3 (high)
    private int peso;           // in kg
    private String descricao;
    private int prioridade;     // Calculated automatically
    
    public Carga(int id, int tipo, int urgencia, int peso, String descricao);
    public int getPrioridade();
    public boolean temMaiorPrioridadeQue(Carga outra);
}
```

**Responsibilities**:
- Store cargo data
- Calculate priority automatically
- Compare priorities

#### Priority Formula

```java
private void calcularPrioridade() {
    this.prioridade = (tipo * urgencia) + peso;
}
```

**Example**:
```
Type: 9, Urgency: 3, Weight: 50 kg
Priority = (9 × 3) + 50 = 27 + 50 = 77
```

#### `HeapMaxima` Class

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

**Responsibilities**:
- Maintain heap property
- Insert with heapify up
- Remove maximum with heapify down
- Expand capacity dynamically

#### `SistemaCarga` Class

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

**Responsibilities**:
- Manage user interaction
- Import data from CSV
- Orchestrate heap operations

---

## 💡 Features

### 🔄 Data Flow

```
┌────────────────────────┐
│   cargas.csv           │
│  1,9,3,50,Medicines    │
│  2,5,2,70,Electronics  │
└──────────┬─────────────┘
           │
           ▼
┌──────────────────────────────┐
│   Parse and Validation       │
│   - Check urgency 1-3        │
│   - Check weight >= 0        │
│   - Calculate priority       │
└──────────┬───────────────────┘
           │
           ▼
┌──────────────────────────────┐
│   Heap Insertion             │
│   1. Add at end              │
│   2. Rise (heapify up)       │
│   3. Restore property        │
└──────────┬───────────────────┘
           │
           ▼
┌──────────────────────────────┐
│   Max Heap (Array)           │
│                               │
│       [Priority 77]           │
│       /              \        │
│  [Priority 51]  [Priority 42] │
└──────────┬───────────────────┘
           │
           ▼
┌──────────────────────────────┐
│   Operations                 │
│   - Query top: O(1)          │
│   - Remove max: O(log n)     │
│   - Display all: O(n log n)  │
└──────────────────────────────┘
```

### 🔍 Heap Algorithms

#### Insertion (Heapify Up)

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

**Process**:
1. Insert at end of array
2. Compare with parent
3. If child > parent, swap
4. Repeat until root or heap restored

**Visual Example**:
```
Insert cargo with priority 80:

    [50]                    [80]
    /  \          →        /  \
 [30]  [20]            [50]  [20]
  /                     /
[80]                 [30]

Steps:
1. Insert [80] as child of [30]
2. [80] > [30] → swap
3. [80] > [50] → swap
4. Heap restored
```

#### Remove Maximum (Heapify Down)

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

**Process**:
1. Save root (maximum)
2. Move last element to root
3. Compare with children
4. Swap with larger child
5. Repeat until leaf or heap restored

#### Dynamic Expansion

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

**Strategy**: Double capacity when full (amortized O(1) per insertion).

---

## 🚀 Installation

### Prerequisites

- **Java JDK 8+**
- **Operating System**: Windows, Linux or macOS

### Compilation and Execution

```bash
# Clone the repository
git clone https://github.com/JoaoGuilhermmy/cargo-system-java.git
cd cargo-system-java

# Compile
javac src/*.java

# Run
java -cp src SistemaCarga
```

---

## 💻 How to Use

### CSV Format (cargas.csv)

```csv
1,9,3,50,Medicines
2,5,2,70,Electronics
3,3,1,90,Clothes
4,9,3,30,Vaccines
5,5,2,80,Appliances
```

**Format**: `id,type,urgency,weight,description`

**Rules**:
- `urgency`: 1 (low), 2 (medium), 3 (high)
- `weight`: >= 0
- `type`: 1-9

### Complete Usage Example

```
==================================================
    CARGO DISTRIBUTION SYSTEM
==================================================
1 - Load cargo from CSV file
2 - Insert new cargo
3 - Display highest priority cargo
4 - Remove highest priority cargo
5 - Display all cargo sorted by priority
6 - Exit
==================================================
Choose an option: 1

CSV file name: cargas.csv
5 cargo loaded successfully from file cargas.csv

Choose an option: 5

===========================================================================
ID   | Type | Urgency | Weight | Priority | Description
---------------------------------------------------------------------------
4    | 9    | 3       | 30     | 57       | Vaccines
1    | 9    | 3       | 50     | 77       | Medicines
5    | 5    | 2       | 80     | 90       | Appliances
2    | 5    | 2       | 70     | 80       | Electronics
3    | 3    | 1       | 90     | 93       | Clothes
===========================================================================

Choose an option: 3

=== Highest Priority Cargo ===
ID   | Type | Urgency | Weight | Priority | Description
---------------------------------------------------------------------------
3    | 3    | 1       | 90     | 93       | Clothes

Choose an option: 4

=== Removed Cargo ===
ID   | Type | Urgency | Weight | Priority | Description
---------------------------------------------------------------------------
3    | 3    | 1       | 90     | 93       | Clothes

Cargo removed successfully!

Choose an option: 2

=== Insert New Cargo ===
ID: 6
Type (1-9): 7
Urgency (1=low, 2=medium, 3=high): 3
Weight (kg): 100
Description: Medical Equipment

Cargo inserted successfully! Calculated priority: 121

Choose an option: 6

Exiting system...
```

---

## 🔧 Technical Details

### Heap Indices

```
Array: [null, 93, 90, 80, 77, 57]
Indices: 0     1   2   3   4   5

Tree:
           [93]
          /    \
       [90]    [80]
       /  \
    [77] [57]

Relationships:
- Parent of i: i/2
- Left child of i: 2*i
- Right child of i: 2*i + 1
```

**Note**: Index 0 is kept null to simplify calculations.

### Ordered Display Without Altering Original Heap

```java
public void exibirTodas() {
    // Create heap copy
    Carga[] copiaHeap = new Carga[quantidade + 1];
    for (int i = 1; i <= quantidade; i++) {
        copiaHeap[i] = heap[i];
    }
    
    int qtdCopia = quantidade;
    
    // Extract maximums successively
    while (qtdCopia > 0) {
        Carga max = copiaHeap[1];
        System.out.println(max);
        
        // Move last to root
        copiaHeap[1] = copiaHeap[qtdCopia];
        qtdCopia--;
        
        // Heapify down on copy
        // ... (descer code)
    }
}
```

**Advantage**: Doesn't modify original heap, allows multiple visualizations.

### Error Handling on Import

```java
public void carregarDeArquivo(String nomeArquivo) {
    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
        String linha = br.readLine(); // Skip header
        int carregadas = 0;
        
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            
            if (dados.length == 5) {
                try {
                    int urgencia = Integer.parseInt(dados[2].trim());
                    int peso = Integer.parseInt(dados[3].trim());
                    
                    // Validations
                    if (urgencia < 1 || urgencia > 3) {
                        System.out.println("Invalid line (urgency): " + linha);
                        continue;
                    }
                    
                    if (peso < 0) {
                        System.out.println("Invalid line (weight): " + linha);
                        continue;
                    }
                    
                    // Create and insert cargo
                    Carga carga = new Carga(...);
                    heap.inserir(carga);
                    carregadas++;
                    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid line (format): " + linha);
                }
            }
        }
        
        System.out.println(carregadas + " cargo loaded successfully");
        
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}
```

---

## 📊 Computational Complexity

| Operation | Complexity | Justification |
|-----------|------------|---------------|
| Insert cargo | O(log n) | Heapify up |
| Remove maximum | O(log n) | Heapify down |
| Query top | O(1) | Direct access |
| Display all (sorted) | O(n log n) | n removals |
| Search by ID | O(n) | Linear search |
| Capacity expansion | O(n) amortized | Size doubling |

### Space Analysis

```
Initial capacity: 10
After 10 insertions: capacity 10 (full)
11th insertion: expand to 20 (copy 10 elements)
21st insertion: expand to 40 (copy 20 elements)

Total copies for n insertions: < 2n
Amortized complexity: O(1) per insertion
```

---

## 🎓 Applied Concepts

### Max Heap

**Property**: Parent always >= children

**Applications**:
- ✅ Priority queue
- ✅ Dijkstra's algorithm
- ✅ Heap Sort
- ✅ Task scheduling

### Comparison with Min Heap

| Aspect | Max Heap | Min Heap |
|--------|----------|----------|
| Root | Maximum | Minimum |
| Use | Highest priority | Lowest value |
| Example | Urgencies | Distances |

### Comparison with Other Structures

| Structure | Insert | Remove Max | Query Max |
|-----------|--------|------------|-----------|
| Unsorted array | O(1) | O(n) | O(n) |
| Sorted array | O(n) | O(1) | O(1) |
| BST | O(log n)* | O(log n)* | O(log n)* |
| **Max Heap** | **O(log n)** | **O(log n)** | **O(1)** |

*Not guaranteed without balancing

---

## 🐛 Troubleshooting

### Problem: CSV doesn't load

**Symptoms**: "Error reading file"

**Solutions**:
1. Check file path
2. Check permissions
3. Check format (commas, no extra spaces)

### Problem: Incorrect priorities

**Cause**: Priority formula misunderstood

**Solution**:
```java
// Check calculation
Priority = (type × urgency) + weight

Example:
Type 9, Urgency 3, Weight 50
= (9 × 3) + 50
= 27 + 50
= 77 ✅
```

### Problem: OutOfMemoryError

**Cause**: Heap too large

**Solution**:
```bash
# Increase JVM heap
java -Xmx2g -cp src SistemaCarga
```

---

## 📈 Possible Improvements

### Short Term
- [ ] Database persistence
- [ ] Efficient ID search
- [ ] Operation logs
- [ ] Unit tests (JUnit)

### Medium Term
- [ ] Graphical interface (JavaFX)
- [ ] PDF reports
- [ ] REST API
- [ ] User authentication

### Long Term
- [ ] Distributed system
- [ ] Real-time dashboard
- [ ] Machine Learning for prediction
- [ ] ERP integration

---

## 📄 License

This project is under the MIT license. See the [LICENSE](LICENSE) file for more details.

---

## 👨‍💻 Author

**João Guilhermmy**

- 🔗 GitHub: [https://github.com/JoaoGuilhermmy](https://github.com/JoaoGuilhermmy)
- 💼 LinkedIn: [www.linkedin.com/in/joão-guilhermmy-93661b29b](https://www.linkedin.com/in/joão-guilhermmy-93661b29b)
- 📧 Email: joaoguilhermmy2@gmail.com

---

## 🙏 Acknowledgments

- Java community for educational resources
- Developers who contributed with feedback
- Professors and mentors

---

<div align="center">

### ⭐ If this project was useful, consider giving it a star!

**Developed with ❤️ and lots of ☕**

### 💡 Educational project demonstrating Max Heap in Java

</div>
