# Sistema Bancário Simplificado com Java e POO

Este projeto é uma aplicação em linguagem Java, desenvolvida com o objetivo de consolidar e demonstrar conceitos fundamentais da Programação Orientada a Objetos (POO), como herança, encapsulamento, polimorfismo, abstração e reuso de código. A aplicação simula um sistema bancário básico interativo via console, permitindo a criação e gestão de contas, depósitos, saques, transferências (incluindo PIX), criação de investimentos e acompanhamento de histórico de transações.

## 🚀 Funcionalidades

* **Gestão de Clientes:** Criação e busca de clientes.
* **Gestão de Contas:**
    * Criação de Contas Correntes e Contas Poupança.
    * Depósitos e Saques.
    * Transferências entre contas (funcionalidade "PIX" diferenciada).
    * Aplicação de rendimento mensal para Conta Poupança.
    * Histórico detalhado de transações para cada conta.
* **Gestão de Investimentos:**
    * Criação de diferentes tipos de investimentos (CDB, LCI) associados a clientes.
    * Cálculo de rendimento para os investimentos.
* **Menu Interativo:** Interface de linha de comando (CLI) para interação do usuário com o sistema.

## 💡 Conceitos de POO Aplicados

Este projeto foi cuidadosamente desenhado para ilustrar os pilares da Programação Orientada a Objetos:

* **Herança:** As classes `ContaCorrente` e `ContaPoupanca` herdam da classe abstrata `Conta`, reutilizando comportamentos comuns e especializando os seus próprios. Da mesma forma, `CDB` e `LCI` herdam de `Investimento`.
* **Encapsulamento:** Atributos das classes são privados (`private` ou `protected`) e acessados/modificados apenas através de métodos públicos (`getters` e `setters`), protegendo a integridade dos dados. O uso do Project Lombok ajuda a reduzir o boilerplate desses métodos.
* **Polimorfismo:** Métodos como `imprimirExtrato()` (em `Conta`) e `calcularRendimento()` (em `Investimento`) são implementados de maneiras diferentes nas subclasses, permitindo que objetos de diferentes tipos sejam tratados de forma uniforme.
* **Abstração:** As classes `Conta` e `Investimento` são abstratas, definindo um contrato (métodos abstratos) que suas subclasses devem implementar, focando no "o quê" fazer, não no "como" fazer.
* **Reuso de Código:** Métodos genéricos são definidos nas classes base (`Conta`, `Investimento`) e reutilizados pelas subclasses. A classe `MoedaUtil` é um exemplo claro de reuso de funcionalidade.
* **Composição:** A classe `Cliente` "possui" uma lista de `Investimento`s e as classes `Conta` "possuem" um `Cliente`.
* **Enums:** Utilizados para representar tipos fixos de contas (`TipoConta`) e investimentos (`TipoInvestimento`), melhorando a legibilidade e a segurança do tipo.
* **Repositório em Memória:** A classe `Banco` atua como um repositório centralizado, gerenciando coleções de `Cliente`s e `Conta`s em memória, simulando a persistência de dados.

## 📁 Estrutura do Projeto

O projeto segue uma estrutura de pacotes organizada para separar as responsabilidades:


sistema-bancario/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── luciano/
│                   └── sistemabancario/
│                       ├── app/
│                       │   ├── Main.java             # Ponto de entrada da aplicação
│                       │   └── SistemaBancarioApp.java # Lógica do menu interativo e operações
│                       ├── investimento/
│                       │   ├── CDB.java              # Investimento de Certificado de Depósito Bancário
│                       │   ├── Investimento.java     # Classe abstrata para investimentos
│                       │   ├── LCI.java              # Investimento de Letra de Crédito Imobiliário
│                       │   └── TipoInvestimento.java # Enum para tipos de investimento
│                       ├── model/
│                       │   ├── Cliente.java          # Representa um cliente do banco
│                       │   ├── Conta.java            # Classe abstrata para contas bancárias
│                       │   ├── ContaCorrente.java    # Implementação de conta corrente
│                       │   ├── ContaPoupanca.java    # Implementação de conta poupança
│                       │   └── TipoConta.java        # Enum para tipos de conta
│                       └── service/
│                           └── Banco.java            # Repositório em memória para clientes e contas
│                       └── util/
│                           └── MoedaUtil.java        # Utilitário para formatação de moeda
└── build.gradle                  # Configurações de build do Gradle


## 🛠️ Como Executar

### Pré-requisitos

* Java Development Kit (JDK) 17 ou superior.
* Gradle (geralmente incluído com o IntelliJ IDEA).
* IntelliJ IDEA (IDE recomendada para o desenvolvimento deste projeto).

### Configuração e Execução

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/sistema-bancario.git](https://github.com/seu-usuario/sistema-bancario.git)
    cd sistema-bancario
    ```
2.  **Abra no IntelliJ IDEA:**
    * Abra o IntelliJ IDEA.
    * Selecione `File` > `Open...` e navegue até a pasta `sistema-bancario` que você clonou.
    * O IntelliJ deve detectar o projeto Gradle automaticamente. Se solicitado, importe as mudanças do Gradle.
3.  **Sincronize as Dependências do Gradle:**
    * Certifique-se de que o IntelliJ sincronize as dependências do `build.gradle` (ícone do elefante Gradle ou notificação "Load Gradle Changes").
    * **Importante para Lombok:** Certifique-se de que o plugin Lombok está instalado no IntelliJ (`File` > `Settings` > `Plugins`) e que a "Annotation Processing" está habilitada para o seu módulo Gradle (`File` > `Settings` > `Build, Execution, Deployment` > `Compiler` > `Annotation Processors` > marque `Enable annotation processing`).
4.  **Execute a Aplicação:**
    * Navegue até o arquivo `src/main/java/com/luciano/sistemabancario/app/Main.java`.
    * Clique com o botão direito no arquivo `Main.java` e selecione `Run 'Main.main()'`.
    * Alternativamente, você pode usar o comando Gradle no terminal (na raiz do projeto):
        ```bash
        ./gradlew run
        ```

A aplicação iniciará no console, apresentando um menu de opções para interação.

## 🤝 Contribuição

Contribuições são bem-vindas! Se você tiver sugestões, melhorias ou encontrar algum problema, sinta-se à vontade para abrir uma *issue* ou enviar um *pull request*.

## 📄 Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes. (Se você não tiver um arquivo LICENSE, pode removê-lo ou criá-lo).
