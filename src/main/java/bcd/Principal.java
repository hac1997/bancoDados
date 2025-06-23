package bcd;

import exemplo01.ExemploMuitoSimples;
import exemplo02.PadroesDeProjeto;
import exemplo03.UsandoPreparedStmt; // Added for Exemplo 03

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.SQLException;

public class Principal {

    private final String[] EXEMPLOS = {
            "\n..:: Pequenos exemplos com Java, SQLite e MySQL ::..\n",
            "1 - Exemplo 01",
            "2 - Exemplo 02 uso de padrões de projeto",
            "3 - Exemplo 03 uso de PreparedStatement", // Added for Exemplo 03
            "6 - Sair do programa"
    };

    private final String[] MENU_EX1 = {
            "\n...:: Exemplo com SQLite::...\n",
            "1 - Cadastrar pessoa",
            "2 - Alterar dados de uma pessoa",
            "3 - Excluir uma pessoa",
            "4 - Listar dados de uma pessoa",
            "5 - Listar todas pessoas",
            "6 - Voltar ao menu anterior"
    };

    private final String[] MENU_EX3 = { // Added for Exemplo 03
            "\n...:: Exemplo com PreparedStatement::...\n",
            "1 - Listar todas pessoas",
            "2 - Listar dados de uma pessoa",
            "3 - Atualizar email de uma pessoa",
            "4 - Voltar ao menu anterior"
    };

    private Scanner teclado;

    public Principal() {
        this.teclado = new Scanner(System.in);
    }

    public static void main(String[] args) throws Exception {
        Principal p = new Principal();
        int opcao = -1;
        do {
            opcao = p.menu(p.EXEMPLOS);
            switch (opcao) {
                case 1:
                    p.exemplo01();
                    break;
                case 2:
                    p.exemplo02();
                    break;
                case 3: // Added for Exemplo 03
                    p.exemplo03();
                    break;
                case 6:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 6);
    }

    private int menu(String[] menuComOpcoes) {
        int opcao = -1;
        if (menuComOpcoes != null) {
            for (String linha : menuComOpcoes) {
                System.out.println(linha);
            }
            try {
                System.out.print("Entre com uma opção: ");
                opcao = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Erro. Informe um número inteiro.");
                opcao = -1;
                teclado.nextLine(); // limpar entrada
            }
        }
        return opcao;
    }

    private void exemplo01() throws Exception {
        int opcao;
        ExemploMuitoSimples app = new ExemploMuitoSimples();

        // Criar o banco de dados e tabela
        app.criaBancoDeDados();

        try {
            do {
                opcao = this.menu(this.MENU_EX1);
                switch (opcao) {
                    case 1:
                        try {
                            teclado.nextLine(); // Limpa buffer
                            System.out.print("Entre com o nome: ");
                            String nome = teclado.nextLine();
                            System.out.print("Entre com o email: ");
                            String email = teclado.nextLine();
                            System.out.print("Entre com o peso: ");
                            double peso = teclado.nextDouble();
                            System.out.print("Entre com a altura: ");
                            int altura = teclado.nextInt();

                            int resultado = app.cadastrarPessoa(nome, peso, altura, email);
                            if (resultado > 0) {
                                System.out.println("\nPessoa cadastrada com sucesso.\n");
                            } else {
                                System.out.println("\nHouve algum problema e não foi possivel cadastrar");
                            }
                        } catch (Exception e) {
                            System.err.println("\nErro com os dados fornecidos. Tente novamente.\n");
                            teclado.nextLine(); // Limpa buffer para evitar erro contínuo
                        }
                        break;
                    case 2:
                        System.out.println(app.listarRegistros());
                        System.out.print("Informe o ID da pessoa que irá alterar os dados: ");
                        int idPessoa = teclado.nextInt();
                        teclado.nextLine(); // Limpa buffer
                        System.out.print("Entre com o nome: ");
                        String nomeAlterar = teclado.nextLine();
                        System.out.print("Entre com o email: ");
                        String emailAlterar = teclado.nextLine();
                        System.out.print("Entre com o peso: ");
                        double pesoAlterar = teclado.nextDouble();
                        System.out.print("Entre com a altura: ");
                        int alturaAlterar = teclado.nextInt();

                        int resultadoAlterar = app.alterarDadosPessoa(idPessoa, nomeAlterar, pesoAlterar, alturaAlterar,
                                emailAlterar);
                        if (resultadoAlterar > 0) {
                            System.out.println("\nDados alterados com sucesso.\n");
                        } else {
                            System.out.println("\nHouve algum problema e não foi possível alterar");
                        }
                        break;
                    case 3:
                        System.out.println(app.listarRegistros());
                        System.out.print("Informe o ID da pessoa que deseja excluir: ");
                        int idExcluir = teclado.nextInt();
                        int resultadoExcluir = app.excluirPessoa(idExcluir);
                        if (resultadoExcluir > 0) {
                            System.out.println("\nPessoa excluída com sucesso\n");
                        } else {
                            System.out.println("\nHouve algum problema e não foi possível excluir");
                        }
                        break;
                    case 4:
                        System.out.print("Entre com o email da pessoa que deseja procurar: ");
                        teclado.nextLine(); // Limpa buffer
                        String emailBuscar = teclado.nextLine();
                        System.out.println(app.listarDadosPessoa(emailBuscar));
                        break;
                    case 5:
                        System.out.println(app.listarRegistros());
                        break;
                    case 6:
                        System.out.println("Voltando ao menu anterior.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } while (opcao != 6);
        } catch (InputMismatchException e) {
            System.err.println("ERRO: Dados fornecidos estão em um formato diferente do esperado.");
        }
    }

    private void exemplo02() throws SQLException {
        PadroesDeProjeto app = new PadroesDeProjeto();
        System.out.println(app.listarPessoas());
    }

    // New method for Exemplo 03
    private void exemplo03() throws SQLException {
        int opcao;
        UsandoPreparedStmt app = new UsandoPreparedStmt();

        try {
            do {
                opcao = this.menu(this.MENU_EX3);
                switch (opcao) {
                    case 1:
                        System.out.println(app.listarPessoas());
                        break;
                    case 2:
                        System.out.print("Informe o ID da pessoa: ");
                        int idPessoa = teclado.nextInt();
                        System.out.println(app.listarDadosDeUmaPessoa(idPessoa));
                        break;
                    case 3:
                        System.out.println(app.listarPessoas());
                        System.out.print("Informe o ID da pessoa que irá alterar o email: ");
                        idPessoa = teclado.nextInt();
                        teclado.nextLine(); // Clear buffer
                        System.out.print("Entre com o email: ");
                        String email = this.teclado.next();
                        if (app.atualizaEmail(idPessoa, email) > 0) {
                            System.out.println("Email atualizado com sucesso");
                        } else {
                            System.out.println("Não foi possível atualizar o email.");
                        }
                        break;
                    case 4:
                        System.out.println("Voltando ao menu anterior.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } while (opcao != 4);
        } catch (InputMismatchException e) {
            System.err.println("ERRO: Dados fornecidos estão em um formato diferente do esperado.");
            teclado.nextLine(); // Clear buffer
        }
    }
}