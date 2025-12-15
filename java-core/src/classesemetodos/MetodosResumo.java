package classesemetodos;

import java.util.*;
import java.util.function.Function;

/**
 * MetodosResumo.java
 *
 * Conteudo
 * 1) Declaracao de metodo  assinatura  nome  parametros  retorno
 * 2) Parametros por valor  primitivos e referencias
 * 3) Sobrecarga  polimorfismo estatico
 * 4) Varargs  cuidado com ambiguidade
 * 5) static x instancia  utilitarios e operacoes de objeto
 * 6) Encapsulamento  validacao e efeitos colaterais
 * 7) Retorno  void  tipos  Optional em vez de null
 * 8) Excecoes  throws  tratamento local
 * 9) Generics em metodos  reuso com tipos
 * 10) Recursao  caso base e progresso
 * 11) Method reference e uso com lambdas
 * 12) Fluencia  encadeamento de chamadas
 * 13) Boas praticas
 */
public class MetodosResumo {

    public static void main(String[] args) {
        declaracaoBasica();
        parametrosPorValor();
        sobrecarga();
        varargsExemplos();
        estaticoVsInstancia();
        encapsulamentoEfeitos();
        retornoEOptional();
        excecoesUso();
        metodosGenericos();
        recursaoFatorial();
        methodReferenceELambdas();
        fluenciaEncadeamento();
        boasPraticas();
    }

    // 1) Declaracao de metodo  assinatura define nome  tipos de parametros e retorno
    static void declaracaoBasica() {

        System.out.println("Declaracao de metodo  assinatura e responsabilidade");

        int soma = somar(7, 5);
        System.out.println("soma de inteiros  " + soma);

        Pessoa p = new Pessoa("Ana", 30);
        String saud = p.saudacaoFormal();
        System.out.println("saudacao formal  " + saud);

    }

    static int somar(int a, int b) { return a + b; }

    // 2) Parametros por valor  referencias sao copiadas  objeto pode ter estado alterado
    static void parametrosPorValor() {

        System.out.println("\nParametros por valor em primitivos e referencias");

        int n = 10;
        incrementarPrimitivo(n);
        System.out.println("primitivo apos chamada  " + n);

        Pessoa q = new Pessoa("Bruno", 20);
        alterarIdade(q);
        System.out.println("objeto apos mutacao interna  " + q.getIdade());
        trocarReferencia(q);
        System.out.println("objeto apos tentativa de troca de referencia  " + q.getNome());

    }

    static void incrementarPrimitivo(int x) { x++; }

    static void alterarIdade(Pessoa p) { p.setIdade(p.getIdade() + 1); }

    static void trocarReferencia(Pessoa p) { p = new Pessoa("Outro", 99); }

    // 3) Sobrecarga  metodos com mesmo nome e assinaturas diferentes
    static void sobrecarga() {

        System.out.println("\nSobrecarga de metodos");

        System.out.println("area de quadrado  " + area(4));
        System.out.println("area de retangulo  " + area(3, 5));
        System.out.println("area de circulo    " + area(2.5));

    }

    static int area(int lado) { return lado * lado; }

    static int area(int base, int alt) { return base * alt; }

    static double area(double raio) { return Math.PI * raio * raio; }

    // 4) Varargs  ultimo parametro variavel  cuidado com chamada ambigua
    static void varargsExemplos() {

        System.out.println("\nVarargs  ultimo parametro aceita muitos valores");

        System.out.println("soma com varargs  " + somaTudo(1, 2, 3, 4));
        System.out.println("soma com array    " + somaTudo(new int[]{5, 6, 7}));

    }

    static int somaTudo(int... nums) {

        int s = 0;
        for (int n : nums) s += n;
        return s;

    }

    // 5) static x instancia
    static void estaticoVsInstancia() {

        System.out.println("\nMetodos estaticos e de instancia");

        System.out.println("raiz inteira via utilitario  " + MathUtil.raizInteira(99));

        Cronometro cr = new Cronometro().start();
        busy();
        cr.stop();
        System.out.println("duracao medida  " + cr.millis() + " ms");

    }

    static void busy() { for (int i = 0; i < 200_000; i++); }

    // 6) Encapsulamento  validar argumentos  evitar estados ilegais
    static void encapsulamentoEfeitos() {

        System.out.println("\nEncapsulamento e efeitos colaterais controlados");

        Conta conta = new Conta("123", 10_00);
        conta.deposito(5_00);
        System.out.println("saldo apos deposito  " + conta.saldoCentavos());
        try {
            conta.saque(100_00);
        } catch (IllegalStateException e) {
            System.out.println("bloqueio de saque por saldo insuficiente");
        }

    }

    // 7) Retorno  Optional em vez de null
    static void retornoEOptional() {

        System.out.println("\nRetorno com Optional para evitar nulos");

        Optional<Pessoa> achada = buscarPorNome(List.of(
                new Pessoa("Caio", 22),
                new Pessoa("Duda", 27)), "Duda");
        System.out.println("pessoa encontrada  " + achada.map(Pessoa::getNome).orElse("nao encontrada"));

    }

    static Optional<Pessoa> buscarPorNome(List<Pessoa> pessoas, String nome) {

        for (Pessoa p : pessoas) if (p.getNome().equalsIgnoreCase(nome)) return Optional.of(p);
        return Optional.empty();

    }

    // 8) Excecoes  quando propagar  quando capturar
    static void excecoesUso() {

        System.out.println("\nExcecoes  declaracao e tratamento");

        try {
            int r = dividirSeguro(10, 0);
            System.out.println("divisao produzida  " + r);
        } catch (ArithmeticException e) {
            System.out.println("falha de divisao tratada");
        }

    }

    static int dividirSeguro(int a, int b) {

        if (b == 0) throw new ArithmeticException("divisor zero");
        return a / b;

    }

    // 9) Metodos genericos  tipo parametrico T
    static void metodosGenericos() {

        System.out.println("\nMetodos genericos para reuso");

        String maiorStr = maior(List.of("a", "ccc", "bb"), String::length);
        Integer maiorInt = maior(List.of(1, 100, 7), x -> x);
        System.out.println("maior por comprimento  " + maiorStr);
        System.out.println("maior numero           " + maiorInt);

    }

    static <T> T maior(List<T> itens, Function<T, Integer> chave) {

        if (itens == null || itens.isEmpty()) throw new IllegalArgumentException("lista vazia");
        T best = itens.get(0);
        int max = chave.apply(best);
        for (int i = 1; i < itens.size(); i++) {
            int v = chave.apply(itens.get(i));
            if (v > max) { max = v; best = itens.get(i); }
        }
        return best;

    }

    // 10) Recursao  sempre definir caso base e reduzir o problema
    static void recursaoFatorial() {

        System.out.println("\nRecursao com caso base");

        System.out.println("fatorial de cinco  " + fatorial(5));

    }

    static long fatorial(int n) {

        if (n < 0) throw new IllegalArgumentException("negativo");
        if (n <= 1) return 1;
        return n * fatorial(n - 1);

    }

    // 11) Method reference e lambdas consumindo metodos
    static void methodReferenceELambdas() {

        System.out.println("\nMethod reference e lambdas");

        List<String> nomes = new ArrayList<>(List.of("zeta", "beta", "alfa"));
        nomes.sort(String::compareTo);
        nomes.replaceAll(s -> s.toUpperCase(Locale.ROOT));
        nomes.forEach(System.out::println);

    }

    // 12) Fluencia  retorno do proprio objeto para encadear
    static void fluenciaEncadeamento() {

        System.out.println("\nFluencia com encadeamento");

        Query q = new Query()
                .selecionar("id", "nome")
                .onde("ativo")
                .ordenar("nome");
        System.out.println("consulta montada  " + q.sql());

    }

    // 13) Boas praticas
    static void boasPraticas() {

        System.out.println("\nBoas praticas");

        System.out.println("nomeie metodos de forma clara  verbo no infinitivo quando apropriado");
        System.out.println("valide parametros na borda  evite estados ilegais");
        System.out.println("mantenha metodos curtos  unica responsabilidade por vez");
        System.out.println("prefira imutabilidade em metodos puros para facilitar testes");
        System.out.println("documente contratos  pre e pos condicoes e excecoes lancadas");

    }

    /*Tipos de apoio para os exemplos*/

    static final class Pessoa {

        private String nome;
        private int idade;
        Pessoa(String nome, int idade) {
            if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome invalido");
            if (idade < 0) throw new IllegalArgumentException("idade negativa");
            this.nome = nome;
            this.idade = idade;
        }
        String getNome() { return nome; }
        int getIdade() { return idade; }
        void setIdade(int v) {
            if (v < 0) throw new IllegalArgumentException("idade negativa");
            idade = v;
        }
        String saudacaoFormal() { return "Ola  " + nome; }

    }

    static final class MathUtil {

        static int raizInteira(int n) {
            if (n < 0) throw new IllegalArgumentException("negativo");
            int r = (int) Math.sqrt(n);
            while ((r + 1L) * (r + 1L) <= n) r++;
            while ((long) r * r > n) r--;
            return r;
        }

    }

    static final class Cronometro {

        private long ini;
        private long fim;
        Cronometro start() { ini = System.nanoTime(); return this; }
        Cronometro stop() { fim = System.nanoTime(); return this; }
        long millis() { return (fim - ini) / 1_000_000; }

    }

    static final class Conta {

        private final String numero;
        private long saldoCentavos;
        Conta(String numero, long saldoCentavos) {
            if (numero == null || numero.isBlank()) throw new IllegalArgumentException("numero invalido");
            if (saldoCentavos < 0) throw new IllegalArgumentException("saldo negativo");
            this.numero = numero;
            this.saldoCentavos = saldoCentavos;
        }
        void deposito(long v) {
            if (v <= 0) throw new IllegalArgumentException("valor invalido");
            saldoCentavos += v;
        }
        void saque(long v) {
            if (v <= 0) throw new IllegalArgumentException("valor invalido");
            if (v > saldoCentavos) throw new IllegalStateException("saldo insuficiente");
            saldoCentavos -= v;
        }
        long saldoCentavos() { return saldoCentavos; }

    }

    static final class Query {

        private final List<String> campos = new ArrayList<>();
        private final List<String> condicoes = new ArrayList<>();
        private String ordem;
        Query selecionar(String... c) { campos.addAll(List.of(c)); return this; }
        Query onde(String cond) { condicoes.add(cond); return this; }
        Query ordenar(String c) { ordem = c; return this; }
        String sql() {
            String base = "select " + (campos.isEmpty() ? "*" : String.join(", ", campos)) + " from tabela";
            if (!condicoes.isEmpty()) base += " where " + String.join(" and ", condicoes);
            if (ordem != null) base += " order by " + ordem;
            return base;
        }

    }

}

