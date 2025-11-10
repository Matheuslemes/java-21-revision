package fundamentos.cosole;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * ConsoleResumo.java
 *
 * Guia prático de console em Java:
 * - Saída: System.out/err, println/printf/format, PrintWriter (flush), ANSI cores.
 * - Entrada: Scanner (tokens), BufferedReader (linhas cruamente), Console (se disponível).
 * - Interativo: leitura robusta (loop + validação), senha sem eco, barra de progresso.
 * - Dicas: Locale em printf, charset, redirecionamento, quando usar logging.
 */

public class ConsoleResumo {

    public static void main(String[] args) {
        saidaBasica();
        printfEFormat();
        coresAnsiETabelas();
        barraDeProgressoDemo();

        // Métodos interativos — descomente para testar no terminal
        // leituraComScanner();
        // leituraComBufferedReader();
        // leituraComConsolePreferencial();
        // leituraSenhaSemEco();
    }

    // 1 - saida basica
    static void saidaBasica() {

        System.out.println("SAIDA BÁSICA");
        System.out.println("println adiciona quebra de linha automática.");
        System.out.print("print NÃO adiciona quebra ");
        System.out.print("— então as mensagens colam.\n");

        // System.err para mensagens de erro/alerta (vai para o stderr, útil em pipes)
        System.err.println("[WARN] Isso é um aviso no stderr.");

        // Crie um PrintWriter se quiser controle explícito de flushing:
        try (PrintWriter out = new PrintWriter(System.out, true)) { // auto-flush = true
            out.println("Usando PrintWriter com auto-flush.");
            out.flush(); // redundante aqui, mas ilustra o uso
        }

    }

    // 2 - printf/format e locale
    static void printfEFormat() {

        System.out.println("\nPRINTF/FORMAT E LOCALE");
        double total = 1234.5;

        // Locale influencia separadores decimais (pt-BR usa vírgula)
        System.out.printf(Locale.US,   "US   -> Total: $%,.2f%n", total);   // 1,234.50
        System.out.printf(new Locale("pt","BR"), "BR   -> Total: R$ %,.2f%n", total); // 1.234,50

        int qtd = 7;
        String nome = "Matheus";
        System.out.printf("Olá, %s! Você tem %d itens.%n", nome, qtd);

        // String.format (gera String ao invés de imprimir)
        String msg = String.format(Locale.ROOT, "Agora: %s", LocalDateTime.now());
        System.out.println(msg);

        // Especificadores úteis:
        // %d (inteiro), %f (decimal), %s (texto), %n (nova linha), %tF %tT (data/hora), %,(usar separador)

    }

    // 3 - ansi cpres e tabela
    static void coresAnsiETabelas() {

        System.out.println("\nANSI CORES E TABELA");
        // Em muitos terminais (Linux/Mac/Windows 10+ com VT), ANSI funciona.
        // Em alguns ambientes IDE, pode não renderizar.
        final String RESET = "\u001B[0m";
        final String BOLD  = "\u001B[1m";
        final String RED   = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String CYAN  = "\u001B[36m";

        System.out.println(BOLD + "Título em negrito" + RESET);
        System.out.println(RED + "Erro" + RESET + " / " + GREEN + "OK" + RESET + " / " + CYAN + "Info" + RESET);

        // Tabela simples com alinhamento usando printf
        System.out.println("\nTabela:");
        System.out.printf("%-12s %10s %10s%n", "Produto", "Qtd", "Preço");
        System.out.printf("%-12s %10d %10.2f%n", "Teclado", 2, 120.0);
        System.out.printf("%-12s %10d %10.2f%n", "Mouse",   1,  80.5);

    }

    // 4 - Scanner - leitura por tokens
    static void leituraComScanner() {

        System.out.println("\nLEITURA COM SCANNER INTERATIVO");
        // Scanner fatiará a entrada por whitespace por padrão.
        // Use try-with-resources para fechar se você criar um Scanner próprio;
        // não feche System.in se ele for reutilizado por outras leituras no mesmo processo.
        Scanner sc = new Scanner(System.in, Charset.defaultCharset());
        try {

            System.out.print("Seu nome: ");
            String nome = sc.nextLine().trim();

            int idade = lerIntSeguro(sc, "Sua idade (inteiro): ");

            double renda = lerDoubleSeguro(sc, "Renda (decimal): ");

            System.out.printf("Olá, %s! Idade=%d, Renda=%.2f%n", nome, idade, renda);

        } catch (NoSuchElementException e) {

            System.err.println("Entrada encerrada inesperadamente.");

        }
        // sc.close(); // evite fechar se pretende ler mais de System.in depois

    }

    // Helpers de leitura robusta (loop até válido)
    static int lerIntSeguro(Scanner sc, String prompt) {

        while (true) {

            System.out.print(prompt);
            String linha = sc.nextLine().trim();

            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }

        }

    }

    static double lerDoubleSeguro(Scanner sc, String prompt) {

        while (true) {
            System.out.print(prompt);
            String linha = sc.nextLine().trim().replace(",", "."); // aceita vírgula
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    // 5 - leitura com bufferedreader interativo
    static void leituraComBufferedReader() {

        System.out.println("\nLEITURA COM BUFFEREDREADER INTERATIVO");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()))) {
            System.out.print("Digite três números separados por espaço: ");
            String linha = br.readLine();
            if (linha == null) {
                System.err.println("Sem entrada.");
                return;
            }
            String[] partes = linha.trim().split("\\s+");
            if (partes.length < 3) {
                System.out.println("Foram informados menos de 3 números.");
                return;
            }
            try {
                int a = Integer.parseInt(partes[0]);
                int b = Integer.parseInt(partes[1]);
                int c = Integer.parseInt(partes[2]);
                System.out.printf("Soma = %d%n", (a + b + c));
            } catch (NumberFormatException e) {
                System.out.println("Pelo menos um valor não é inteiro válido.");
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O: " + e.getMessage());
        }

    }

    // 6 - java.io.Console (se disponível)
    static void leituraComConsolePreferencial() {

        System.out.println("\nCONSOLE PREFERENCIAL (SE DISPONIVEL)");
        Console cons = System.console(); // Pode ser null em IDEs/ambientes não interativos
        if (cons == null) {
            System.out.println("Console não disponível. Use Scanner/BufferedReader.");
            return;
        }

        String nome = cons.readLine("Nome: ");
        int idade = parseIntSeguro(cons, "Idade (int): ");
        cons.printf("Olá, %s (idade=%d)%n", nome, idade);

    }

    static int parseIntSeguro(Console cons, String prompt) {
        while (true) {
            String s = cons.readLine(prompt);
            try { return Integer.parseInt(s.trim()); }
            catch (NumberFormatException e) { cons.printf("Inválido. Tente de novo.%n"); }
        }
    }

    // Senha sem eco (Console)
    static void leituraSenhaSemEco() {

        System.out.println("\nSENHA SEM ECO (CONSOLE)");
        Console cons = System.console();
        if (cons == null) {
            System.out.println("Console não disponível (provável IDE). Teste no terminal real.");
            return;
        }

        char[] senha = cons.readPassword("Senha: "); // não ecoa
        try {
            cons.printf("Senha lida (%d chars). Não imprima senha em produção!%n", senha.length);
        } finally {
            java.util.Arrays.fill(senha, '\0'); // limpa da memória
        }

    }

    // 8 - Barra de progresso (stdout)
    static void barraDeProgressoDemo() {
        System.out.println("\n== Barra de progresso (simulada) ==");
        int total = 20;
        for (int i = 0; i <= total; i++) {
            String bar = "=".repeat(i) + " ".repeat(total - i);
            System.out.printf("\r[%s] %3d%%", bar, (i * 100 / total));
            try { Thread.sleep(30); } catch (InterruptedException ignored) {}
        }
        System.out.println(); // quebra final
    }

    /*
     * 9 - Dicas finais / Portabilidade
     * - Locale: use Locale apropriado em printf/format para moedas/datas/decimais.
     * - Charset: System.in/out usam charset do ambiente; para controle, envolva com Readers/Writers com Charset.
     * - Redirecionamento: em shell, você pode fazer `java App < input.txt > out.txt 2> err.txt`.
     * - Logging x Console: para apps reais, prefira logging (SLF4J/Logback) a prints soltos.
     * - Console pode ser null em IDEs. Testes de senha/eco exijam terminal real.
     * - Em Windows antigo, ANSI pode não funcionar sem habilitar VT. Em Windows 10+ geralmente funciona.
     */
}

