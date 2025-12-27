# 🚚 Sistema de Prioridade de Carga com Heap Máxima

<div align="center">

![Java](https://img.shields.io/badge/Java-11+-orange?style=for-the-badge&logo=java)
![Estruturas de Dados](https://img.shields.io/badge/Estruturas-Heap%20Máxima-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Concluído-green?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-lightgrey?style=for-the-badge)

**Sistema de gerenciamento de cargas baseado em prioridade, utilizando Heap Máxima em Java**

[📖 Documentação](#documentação) • [🚀 Instalação](#instalação) • [⚙️ Funcionalidades](#funcionalidades) • [🏗️ Arquitetura](#arquitetura)

</div>

---

## 📋 Sobre o Projeto

Este projeto implementa um **Sistema de Prioridade de Carga** desenvolvido em **Java**, utilizando a estrutura de dados **Heap Máxima** para garantir que cargas com maior prioridade sejam processadas primeiro.

O sistema simula um cenário logístico, no qual cargas são cadastradas com atributos como **descrição, peso e prioridade**, e organizadas automaticamente de acordo com sua importância operacional.

O foco do projeto é aplicar conceitos fundamentais de:
- **Estruturas de Dados**
- **Programação Orientada a Objetos**
- **Organização e manipulação eficiente de dados**

---

## 🎯 Objetivos

- Implementar uma **Heap Máxima do zero**
- Simular um sistema real de **priorização logística**
- Aplicar conceitos de **POO em Java**
- Garantir remoção eficiente da carga de maior prioridade
- Desenvolver um código organizado, legível e reutilizável

---

## ✨ Funcionalidades

### 🚛 Gestão de Cargas
- ✅ Cadastro de cargas com descrição, peso e prioridade
- ✅ Validação de dados de entrada
- ✅ Listagem das cargas registradas

### ⚡ Sistema de Prioridade
- 🔼 Organização automática por prioridade (Heap Máxima)
- 🥇 Acesso rápido à carga de maior prioridade
- ❌ Remoção eficiente da carga prioritária

### 🖥️ Interface via Terminal
- Menu interativo
- Operações claras e objetivas
- Feedback imediato ao usuário

---

## 🏗️ Arquitetura

### Estrutura de Classes
┌───────────────────────────────┐
│ Carga │
├───────────────────────────────┤
│ - descricao │
│ - peso │
│ - prioridade │
├───────────────────────────────┤
│ + getters e setters │
│ + toString() │
└───────────────┬───────────────┘
│
│ utilizada por
│
┌───────────────▼───────────────┐
│ HeapMaxima │
├───────────────────────────────┤
│ - lista de cargas │
├───────────────────────────────┤
│ + inserirCarga() │
│ + removerMaiorPrioridade() │
│ + heapifyUp() │
│ + heapifyDown() │
└───────────────┬───────────────┘
│
│ controlada por
│
┌───────────────▼───────────────┐
│ SistemaCarga │
├───────────────────────────────┤
│ + menu() │
│ + main() │
│ + interação com usuário │
└───────────────────────────────┘

---

## ⚙️ Descrição das Classes

### 📦 Carga
Representa uma carga logística.

**Atributos:**
- `descricao` — Identificação da carga
- `peso` — Peso da carga
- `prioridade` — Nível de prioridade (quanto maior, mais urgente)

---

### 📊 HeapMaxima
Implementa a **estrutura de dados Heap Máxima**, garantindo que a carga com maior prioridade esteja sempre no topo.

**Responsabilidades:**
- Inserção ordenada de cargas
- Remoção da carga mais prioritária
- Reorganização automática da heap

---

### 🖥️ SistemaCarga
Classe principal do sistema.

**Funções:**
- Exibe o menu interativo
- Lê dados do usuário
- Coordena as operações entre as classes

---

## 🚀 Instalação

### Pré-requisitos
- ☕ Java JDK 11 ou superior
- 🛠️ IDE ou terminal com `javac`

### Compilação
```bash
javac Carga.java HeapMaxima.java SistemaCarga.java

Execução
java SistemaCarga

💻 Como Usar
Menu Principal
1 - Inserir carga
2 - Remover carga de maior prioridade
3 - Listar cargas
0 - Sair

Exemplo
Descrição: Equipamentos médicos
Peso: 300
Prioridade: 10

✅ Carga inserida com sucesso!
🧠 Conceitos Aplicados

Heap Máxima

Estruturas de Dados

Programação Orientada a Objetos

Encapsulamento

Algoritmos de ordenação implícita

📈 Melhorias Futuras

 Persistência em arquivo

 Interface gráfica

 Priorização automática por peso + urgência

 Testes unitários

📄 Licença

Licença MIT.

👨‍💻 Autor

João Guilhermmy

GitHub: https://github.com/JoaoGuilhermmy

Email: joaoguilhermmy2@gmail.com


---

# 🇺🇸 README — Load Priority System in Java

```markdown
# 🚚 Load Priority System Using Max Heap

<div align="center">

![Java](https://img.shields.io/badge/Java-11+-orange?style=for-the-badge&logo=java)
![Data Structures](https://img.shields.io/badge/Data%20Structures-Max%20Heap-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-green?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-lightgrey?style=for-the-badge)

**Load management system based on priority using a Max Heap data structure in Java**

</div>

---

## 📋 About the Project

This project implements a **Load Priority Management System** developed in **Java**, using a **Max Heap** data structure to ensure that the most critical loads are always processed first.

Each load is registered with attributes such as **description, weight, and priority**, and automatically organized according to its priority level.

---

## 🎯 Goals

- Implement a **Max Heap from scratch**
- Simulate a real-world **logistics prioritization system**
- Apply **Object-Oriented Programming** concepts
- Ensure efficient insertion and removal operations
- Develop clean and well-structured code

---

## ✨ Features

### 🚛 Load Management
- ✅ Register loads with description, weight, and priority
- ✅ Input validation
- ✅ List registered loads

### ⚡ Priority System
- 🔼 Automatic ordering using Max Heap
- 🥇 Fast access to highest priority load
- ❌ Efficient removal of top-priority load

### 🖥️ Terminal Interface
- Interactive menu
- Clear operations
- Immediate user feedback

---

## 🏗️ Architecture

### Class Structure

Load
└── MaxHeap
└── LoadSystem


---

## ⚙️ Class Description

### 📦 Load
Represents a logistics load with priority attributes.

### 📊 MaxHeap
Implements the Max Heap structure responsible for ordering loads.

### 🖥️ LoadSystem
Main class that controls system execution and user interaction.

---

## 🚀 Installation

### Requirements
- Java JDK 11+
- Terminal or IDE

### Compile
```bash
javac Load.java MaxHeap.java LoadSystem.java

Run
