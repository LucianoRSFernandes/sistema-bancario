package com.luciano.sistemabancario.app;

import com.luciano.sistemabancario.model.Cliente;
import com.luciano.sistemabancario.model.Conta;
import com.luciano.sistemabancario.model.ContaCorrente;
import com.luciano.sistemabancario.model.ContaPoupanca;
import com.luciano.sistemabancario.service.Banco;
import com.luciano.sistemabancario.investimento.CDB;
import com.luciano.sistemabancario.investimento.LCI;
import com.luciano.sistemabancario.investimento.Investimento;
import com.luciano.sistemabancario.util.MoedaUtil;

import com.luciano.sistemabancario.model.TipoConta;
import com.luciano.sistemabancario.investimento.TipoInvestimento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SistemaBancarioApp {

    private final Banco meuBanco;
    private final Scanner scanner;

    public SistemaBancarioApp() {
        this.meuBanco = new Banco("Banco");
        this.scanner = new Scanner(System.in);
        inicializarDadosExemplo();
    }

    private void inicializarDadosExemplo() {
        Cliente cliente1 = new Cliente("Luciano", "123.456.789-00", "luciano@email.com");
        Cliente cliente2 = new Cliente("Maria", "987.654.321-00", "maria@email.com");
        meuBanco.adicionarCliente(cliente1);
        meuBanco.adicionarCliente(cliente2);

        ContaCorrente ccLuciano = new ContaCorrente(cliente1);
        ContaPoupanca cpLuciano = new ContaPoupanca(cliente1);
        ContaCorrente ccMaria = new ContaCorrente(cliente2);

        meuBanco.adicionarConta(ccLuciano);
        meuBanco.adicionarConta(cpLuciano);
        meuBanco.adicionarConta(ccMaria);

        ccLuciano.depositar(1500);
        cpLuciano.depositar(300);
        ccMaria.depositar(500);

        ccLuciano.transferir(200, ccMaria);
        ccLuciano.transferirPix(100, cpLuciano);
    }

    public void iniciar() {
        System.out.println("=== BEM-VINDO AO SISTEMA BANCÁRIO ===");
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    criarCliente();
                    break;
                case 2:
                    criarConta();
                    break;
                case 3:
                    depositar();
                    break;
                case 4:
                    sacar();
                    break;
                case 5:
                    transferir();
                    break;
                case 6:
                    transferirPix();
                    break;
                case 7:
                    exibirExtrato();
                    break;
                case 8:
                    adicionarInvestimento();
                    break;
                case 9:
                    consultarInvestimentos();
                    break;
                case 0:
                    System.out.println("Obrigado por usar o Sistema Bancário. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            if (opcao != 0) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Criar Cliente");
        System.out.println("2. Criar Conta");
        System.out.println("3. Depositar");
        System.out.println("4. Sacar");
        System.out.println("5. Transferir (Conta a Conta)");
        System.out.println("6. Transferir (PIX)");
        System.out.println("7. Exibir Extrato");
        System.out.println("8. Adicionar Investimento");
        System.out.println("9. Consultar Investimentos do Cliente");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcaoMenu() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next();
            System.out.print("Escolha uma opção: ");
        }
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    private double lerValor(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Por favor, digite um valor numérico.");
            scanner.next();
            System.out.print(prompt);
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private void criarCliente() {
        System.out.println("\n--- CRIAR CLIENTE ---");
        String nome = lerString("Nome do Cliente: ");
        String cpf = lerString("CPF do Cliente: ");
        String email = lerString("Email do Cliente: ");

        Cliente novoCliente = new Cliente(nome, cpf, email);
        meuBanco.adicionarCliente(novoCliente);
        System.out.println("Cliente '" + nome + "' criado com sucesso!");
    }

    private void criarConta() {
        System.out.println("\n--- CRIAR CONTA ---");
        String cpfCliente = lerString("CPF do Cliente para a nova conta: ");
        Optional<Cliente> cliente = meuBanco.buscarClientePorCpf(cpfCliente);

        if (cliente.isEmpty()) {
            System.out.println("Cliente não encontrado. Crie o cliente primeiro.");
            return;
        }

        System.out.print("Tipo de Conta (1 para Corrente, 2 para Poupança): ");
        int tipoContaCodigo = lerOpcaoMenu();

        TipoConta tipoConta = TipoConta.fromCodigo(tipoContaCodigo);

        Conta novaConta;
        if (tipoConta == TipoConta.CONTA_CORRENTE) {
            novaConta = new ContaCorrente(cliente.orElse(null));
        } else if (tipoConta == TipoConta.CONTA_POUPANCA) {
            novaConta = new ContaPoupanca(cliente.orElse(null));
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        meuBanco.adicionarConta(novaConta);
        System.out.println("Conta " + tipoConta.getDescricao() + " (Número: " + novaConta.getNumero() + ") criada para " + cliente.get().getNome() + ".");
    }

    private void depositar() {
        System.out.println("\n--- DEPOSITAR ---");
        int numeroConta = (int) lerValor("Número da Conta: ");
        Optional<Conta> conta = meuBanco.buscarContaPorNumero(numeroConta);

        if (conta.isEmpty()) {
            System.out.println("Conta não encontrada.");
            return;
        }

        double valor = lerValor("Valor a depositar: ");
        conta.get().depositar(valor);
    }

    private void sacar() {
        System.out.println("\n--- SACAR ---");
        int numeroConta = (int) lerValor("Número da Conta: ");
        Optional<Conta> conta = meuBanco.buscarContaPorNumero(numeroConta);

        if (conta.isEmpty()) {
            System.out.println("Conta não encontrada.");
            return;
        }

        double valor = lerValor("Valor a sacar: ");
        conta.get().sacar(valor);
    }

    private void transferir() {
        System.out.println("\n--- TRANSFERÊNCIA (CONTA A CONTA) ---");
        int contaOrigemNum = (int) lerValor("Número da Conta de Origem: ");
        Optional<Conta> contaOrigem = meuBanco.buscarContaPorNumero(contaOrigemNum);

        if (contaOrigem.isEmpty()) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }

        int contaDestinoNum = (int) lerValor("Número da Conta de Destino: ");
        Optional<Conta> contaDestino = meuBanco.buscarContaPorNumero(contaDestinoNum);

        if (contaDestino.isEmpty()) {
            System.out.println("Conta de destino não encontrada.");
            return;
        }

        double valor = lerValor("Valor a transferir: ");
        contaOrigem.get().transferir(valor, contaDestino.orElse(null));
    }

    private void transferirPix() {
        System.out.println("\n--- TRANSFERÊNCIA VIA PIX ---");
        int contaOrigemNum = (int) lerValor("Número da Conta de Origem: ");
        Optional<Conta> contaOrigem = meuBanco.buscarContaPorNumero(contaOrigemNum);

        if (contaOrigem.isEmpty()) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }

        int contaDestinoNum = (int) lerValor("Número da Conta de Destino (como chave PIX): ");
        Optional<Conta> contaDestino = meuBanco.buscarContaPorNumero(contaDestinoNum);

        if (contaDestino.isEmpty()) {
            System.out.println("Conta de destino não encontrada (chave PIX inválida).");
            return;
        }

        double valor = lerValor("Valor a transferir via PIX: ");
        contaOrigem.get().transferirPix(valor, contaDestino.orElse(null));
    }

    private void exibirExtrato() {
        System.out.println("\n--- EXIBIR EXTRATO ---");
        int numeroConta = (int) lerValor("Número da Conta: ");
        Optional<Conta> conta = meuBanco.buscarContaPorNumero(numeroConta);

        if (conta.isEmpty()) {
            System.out.println("Conta não encontrada.");
            return;
        }
        conta.get().imprimirExtrato();
    }

    private void adicionarInvestimento() {
        System.out.println("\n--- ADICIONAR INVESTIMENTO ---");
        String cpfCliente = lerString("CPF do Cliente: ");
        Optional<Cliente> cliente = meuBanco.buscarClientePorCpf(cpfCliente);

        if (cliente.isEmpty()) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Tipo de Investimento (1 para CDB, 2 para LCI): ");
        int tipoInvCodigo = lerOpcaoMenu();

        TipoInvestimento tipoInvestimento = TipoInvestimento.fromCodigo(tipoInvCodigo);

        double valor = lerValor("Valor a ser aplicado: ");
        double taxa = lerValor("Taxa de rendimento diária (ex: 0.005 para 0.5% ao dia): ");
        LocalDate dataAplicacao = LocalDate.now();

        Investimento novoInvestimento;
        if (tipoInvestimento == TipoInvestimento.CDB) {
            novoInvestimento = new CDB(valor, dataAplicacao, taxa);
        } else if (tipoInvestimento == TipoInvestimento.LCI) {
            novoInvestimento = new LCI(valor, dataAplicacao, taxa);
        } else {
            System.out.println("Tipo de investimento inválido.");
            return;
        }

        cliente.get().adicionarInvestimento(novoInvestimento);
        System.out.println("Investimento de " + MoedaUtil.formatar(valor) + " em " + novoInvestimento.getTipo() + " adicionado para " + cliente.get().getNome() + ".");

        String sacarValorStr = lerString("Deseja sacar o valor do investimento de uma conta corrente? (s/n): ").toLowerCase();
        if (sacarValorStr.equals("s")) {
            int contaOrigemNum = (int) lerValor("Número da Conta Corrente de Origem para saque do valor do investimento: ");
            Optional<Conta> contaOrigem = meuBanco.buscarContaPorNumero(contaOrigemNum);

            if (contaOrigem.isEmpty() || !(contaOrigem.get() instanceof ContaCorrente contaCorrente)) {
                System.out.println("A conta informada não é uma Conta Corrente válida para o saque do investimento.");
            } else {
                if (contaCorrente.sacar(valor)) {
                    System.out.println("Valor do investimento sacado da conta corrente " + contaCorrente.getNumero() + " com sucesso.");
                } else {
                    System.out.println("Falha ao sacar o valor do investimento da conta corrente.");
                }
            }
        }
    }

    private void consultarInvestimentos() {
        System.out.println("\n--- CONSULTAR INVESTIMENTOS DO CLIENTE ---");
        String cpfCliente = lerString("CPF do Cliente: ");
        Optional<Cliente> cliente = meuBanco.buscarClientePorCpf(cpfCliente);

        if (cliente.isEmpty()) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        List<Investimento> investimentos = cliente.get().getInvestimentos();
        if (investimentos.isEmpty()) {
            System.out.println("Nenhum investimento registrado para " + cliente.get().getNome() + ".");
            return;
        }

        System.out.println("\n--- Investimentos de " + cliente.get().getNome() + " ---");
        for (int i = 0; i < investimentos.size(); i++) {
            Investimento inv = investimentos.get(i);
            System.out.println("\nInvestimento #" + (i + 1));
            inv.imprimirDetalhes();

            long diasDesdeAplicacao = ChronoUnit.DAYS.between(inv.getDataAplicacao(), LocalDate.now());
            double rendimentoAtual = inv.calcularRendimento(diasDesdeAplicacao);
            System.out.println("  Rendimento acumulado (" + diasDesdeAplicacao + " dias): " + MoedaUtil.formatar(rendimentoAtual));
            System.out.println("  Valor total atual: " + MoedaUtil.formatar(inv.getValorAplicado() + rendimentoAtual));
        }
    }
}