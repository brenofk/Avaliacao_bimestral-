package src;

import src.dao.CategoriaDAO;
import src.dao.ContatoDAO;
import src.dao.CompromissoDAO;

import src.model.Categoria;
import src.model.Contato;
import src.model.Compromisso;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ContatoDAO contatoDAO = new ContatoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        CompromissoDAO compromissoDAO = new CompromissoDAO();

        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Gerenciar Contatos");
            System.out.println("2 - Gerenciar Categorias");
            System.out.println("3 - Gerenciar Compromissos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuContatos(contatoDAO);
                case 2 -> menuCategorias(categoriaDAO);
                case 3 -> menuCompromissos(compromissoDAO);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // ---------------- MENU CONTATOS ----------------
    private static void menuContatos(ContatoDAO dao) {
        int opc;

        do {
            System.out.println("\n--- MENU CONTATOS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> cadastrarContato(dao);
                case 2 -> listarContatos(dao);
                case 3 -> atualizarContato(dao);
                case 4 -> excluirContato(dao);
            }

        } while (opc != 0);
    }

    private static void cadastrarContato(ContatoDAO dao) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String fone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        dao.inserir(new Contato(nome, fone, email));
        System.out.println("Contato cadastrado!");
    }

    private static void listarContatos(ContatoDAO dao) {
        List<Contato> lista = dao.listarTodos();
        System.out.println("\n--- LISTA DE CONTATOS ---");
        for (Contato c : lista) System.out.println(c);
    }

    private static void atualizarContato(ContatoDAO dao) {
        listarContatos(dao);

        System.out.print("Digite o ID para atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Contato c = dao.buscarPorId(id);
        if (c == null) {
            System.out.println("Contato não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        c.setNome(sc.nextLine());

        System.out.print("Novo telefone: ");
        c.setTelefone(sc.nextLine());

        System.out.print("Novo email: ");
        c.setEmail(sc.nextLine());

        dao.atualizar(c);
        System.out.println("Atualizado com sucesso!");
    }

    private static void excluirContato(ContatoDAO dao) {
        listarContatos(dao);
        System.out.print("ID para excluir: ");
        int id = sc.nextInt();
        sc.nextLine();

        dao.deletar(id);
        System.out.println("Contato excluído!");
    }

    // ---------------- MENU CATEGORIAS ----------------
    private static void menuCategorias(CategoriaDAO dao) {
        int opc;

        do {
            System.out.println("\n--- MENU CATEGORIAS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> cadastrarCategoria(dao);
                case 2 -> listarCategorias(dao);
                case 3 -> atualizarCategoria(dao);
                case 4 -> excluirCategoria(dao);
            }

        } while (opc != 0);
    }

    private static void cadastrarCategoria(CategoriaDAO dao) {
        System.out.print("Nome da categoria: ");
        String nome = sc.nextLine();

        dao.inserir(new Categoria(nome));
        System.out.println("Categoria cadastrada!");
    }

    private static void listarCategorias(CategoriaDAO dao) {
        List<Categoria> lista = dao.listarTodos();
        System.out.println("\n--- LISTA DE CATEGORIAS ---");
        for (Categoria c : lista) System.out.println(c);
    }

    private static void atualizarCategoria(CategoriaDAO dao) {
        listarCategorias(dao);

        System.out.print("ID da categoria: ");
        int id = sc.nextInt();
        sc.nextLine();

        Categoria c = dao.buscarPorId(id);
        if (c == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.print("Novo nome: ");
        c.setNome(sc.nextLine());

        dao.atualizar(c);
        System.out.println("Atualizada com sucesso!");
    }

    private static void excluirCategoria(CategoriaDAO dao) {
        listarCategorias(dao);
        System.out.print("ID para excluir: ");
        int id = sc.nextInt();
        sc.nextLine();

        dao.deletar(id);
        System.out.println("Categoria excluída!");
    }

    // ---------------- MENU COMPROMISSOS ----------------
    private static void menuCompromissos(CompromissoDAO dao) {
        int opc;

        do {
            System.out.println("\n--- MENU COMPROMISSOS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> cadastrarCompromisso(dao);
                case 2 -> listarCompromissos(dao);
                case 3 -> atualizarCompromisso(dao);
                case 4 -> excluirCompromisso(dao);
            }

        } while (opc != 0);
    }

    private static void cadastrarCompromisso(CompromissoDAO dao) {
        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Data (AAAA-MM-DD): ");
        Date data = Date.valueOf(sc.nextLine());

        System.out.print("Hora (HH:MM): ");
        Time hora = Time.valueOf(sc.nextLine() + ":00");

        System.out.print("ID do Contato: ");
        int contato = sc.nextInt();
        sc.nextLine();

        System.out.print("ID da Categoria: ");
        int categoria = sc.nextInt();
        sc.nextLine();

        dao.inserir(new Compromisso(titulo, data, hora, contato, categoria));
        System.out.println("Compromisso cadastrado!");
    }

    private static void listarCompromissos(CompromissoDAO dao) {
        List<Compromisso> lista = dao.listarTodos();
        System.out.println("\n--- COMPROMISSOS ---");
        for (Compromisso c : lista) System.out.println(c);
    }

    private static void atualizarCompromisso(CompromissoDAO dao) {
        listarCompromissos(dao);

        System.out.print("ID do compromisso: ");
        int id = sc.nextInt();
        sc.nextLine();

        Compromisso c = dao.buscarPorId(id);
        if (c == null) {
            System.out.println("Compromisso não encontrado.");
            return;
        }

        System.out.print("Novo título: ");
        c.setTitulo(sc.nextLine());

        System.out.print("Nova data (AAAA-MM-DD): ");
        c.setData(Date.valueOf(sc.nextLine()));

        System.out.print("Nova hora (HH:MM): ");
        c.setHora(Time.valueOf(sc.nextLine() + ":00"));

        System.out.print("ID do contato: ");
        c.setContatoId(sc.nextInt());
        sc.nextLine();

        System.out.print("ID da categoria: ");
        c.setCategoriaId(sc.nextInt());
        sc.nextLine();

        dao.atualizar(c);
        System.out.println("Atualizado!");
    }

    private static void excluirCompromisso(CompromissoDAO dao) {
        listarCompromissos(dao);

        System.out.print("ID para excluir: ");
        int id = sc.nextInt();
        sc.nextLine();

        dao.deletar(id);
        System.out.println("Compromisso excluído!");
    }
}
