package estruturasdecontrole;

/**
 * IfResumo.java

 * Conteudo
 * 1) if simples
 * 2) if com else
 * 3) if encadeado  else if
 * 4) Blocos e escopo de variaveis
 * 5) Guard clauses  sair cedo com condicoes invalidas
 * 6) Combinacao de condicoes  E  OU  negacao
 * 7) Strings  comparar conteudo com equals  e checar null
 * 8) instanceof com pattern matching
 * 9) Pitfalls e boas praticas
 */
public class IfResumo {

    public static void main(String[] args) {
        ifSimples();
        ifComElse();
        ifEncadeado();
        blocosEEscopo();
        guardClauses();
        combinacaoDeCondicoes();
        stringsENull();
        instanceofPatternMatching();
        pitfallsEBoasPraticas();
    }

    // 1) if simples
    static void ifSimples() {

        System.out.println("If simples");

        int idade = 20;
        if (idade >= 18) {
            System.out.println("maior de idade");
        }
        if (idade < 18) {
            System.out.println("menor de idade");
        }

    }

    // 2) if com else
    static void ifComElse() {

        System.out.println("\nIf com else");

        double saldo = 120.0;
        double compra = 99.9;
        if (saldo >= compra) {
            System.out.println("compra autorizada");
        } else {
            System.out.println("saldo insuficiente");
        }

    }

    // 3) encadeado  else if
    static void ifEncadeado() {

        System.out.println("\nIf encadeado");

        int nota = 84;
        if (nota >= 90) {
            System.out.println("conceito A");
        } else if (nota >= 80) {
            System.out.println("conceito B");
        } else if (nota >= 70) {
            System.out.println("conceito C");
        } else if (nota >= 60) {
            System.out.println("conceito D");
        } else {
            System.out.println("conceito E");
        }

    }

    // 4) Blocos e escopo
    static void blocosEEscopo() {

        System.out.println("\nBlocos e escopo");

        int x = 10;
        if (x > 5) {
            int dentro = x * 2; // visivel apenas aqui
            System.out.println("variavel interna criada no bloco");
            System.out.println("valor processado dentro do bloco  " + dentro);
        }
        // System.out.println(dentro); // nao compila  fora do escopo

    }

    // 5) Guard clauses  sair cedo quando algo invalido ocorrer
    static void guardClauses() {

        System.out.println("\nGuard clauses");

        String token = "abc123";
        if (token == null || token.isBlank()) {
            System.out.println("token invalido");
            return;
        }
        if (token.length() < 3) {
            System.out.println("token curto");
            return;
        }
        System.out.println("token aprovado  continua o fluxo");

    }

    // 6) Combinacao de condicoes
    static void combinacaoDeCondicoes() {

        System.out.println("\nCombinacao de condicoes");

        int hora = 14;
        boolean horarioComercial = hora >= 9 && hora < 18;
        boolean fimDeSemana = false;

        if (horarioComercial && !fimDeSemana) {
            System.out.println("expediente ativo");
        }
        if (hora < 6 || hora >= 22) {
            System.out.println("horario de baixa atividade");
        }

    }

    // 7) Strings e null  usar equals e checar null antes
    static void stringsENull() {

        System.out.println("\nStrings e null");

        String s = "Java";
        String t = "java";
        if (s.equals(t)) {
            System.out.println("texto com mesma grafia e mesma caixa");
        } else if (s.equalsIgnoreCase(t)) {
            System.out.println("texto com mesma grafia ignorando caixa");
        } else {
            System.out.println("texto diferente");
        }

        String nome = null;
        if (nome == null || nome.isBlank()) {
            System.out.println("nome ausente ou em branco");
        } else {
            System.out.println("nome informado");
        }

    }

    // 8) instanceof com pattern matching  Java 16+
    static void instanceofPatternMatching() {

        System.out.println("\ninstanceof com pattern matching");

        Object obj = "123";
        if (obj instanceof String str && str.length() > 0) {
            System.out.println("obj contem texto com tamanho maior que zero");
        }

    }

    // 9) Pitfalls e boas praticas
    static void pitfallsEBoasPraticas() {

        System.out.println("\nPitfalls e boas praticas");

        // a) Sempre use chaves em blocos compostos para evitar ambiguidades
        int n = 3;
        if (n > 0) {
            System.out.println("positivo");
            System.out.println("mensagem adicional");
        }

        // b) Evite efeitos colaterais nos testes  preferir funcoes puras
        int estoque = 5;
        int pedido = 2;
        if (temEstoqueSuficiente(estoque, pedido)) {
            System.out.println("pedido atendido");
        } else {
            System.out.println("estoque insuficiente");
        }

        // c) Evite cascatas longas quando um mapa ou switch expressar melhor
        int codigo = 2;
        String status;
        if (codigo == 1) {
            status = "aberto";
        } else if (codigo == 2) {
            status = "em andamento";
        } else if (codigo == 3) {
            status = "concluido";
        } else {
            status = "indefinido";
        }
        System.out.println("status calculado  " + status);

        // d) Prefira extrair condicoes complexas para metodos com nomes claros
        int idade = 66;
        boolean vip = true;
        if (temDesconto(idade, vip)) {
            System.out.println("desconto aplicado");
        } else {
            System.out.println("sem desconto");
        }

    }

    static boolean temEstoqueSuficiente(int estoque, int pedido) {

        return estoque >= pedido;

    }

    static boolean temDesconto(int idade, boolean vip) {
        return idade >= 60 || vip;

    }

}

