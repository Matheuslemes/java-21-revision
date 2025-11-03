package fundamentos.notaçãoponto;

/**
 * NotacaoPontoResumo.java
 *
 * "Notação ponto" (dot notation) em Java:
 *  - Instância: objeto.membro / objeto.metodo(...)
 *  - Estático:  Classe.membroEstatico / Classe.metodoEstatico(...)
 *  - Qualificação de pacotes e tipos: pacote.subpacote.Classe
 *  - Encadeamento (fluent): obj.metodo().outro().final()
 *  - Acesso especial: this.campo, super.metodo(), array.length (sem parênteses)
 *
 * Observações:
 *  - Precedência:  a.b  tem alta precedência; leitura é da esquerda p/ direita.
 *  - Null-safety:  acessar algo via ponto em null -> NullPointerException.
 *  - Não confundir com "::", que é *method reference* (outra sintaxe).
 */


import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NotacaoPonto {


    // 1 - Tipos de apoio para exemplos
    static class Pessoa {

        // campos de instância
        private String nome;
        private int idade;

        // campo estático (compartilhado entre todas as instâncias)
        static String especie = "Homo sapiens";

        Pessoa(String nome, int idade) {
            // "this" referencia o objeto atual quando há ambiguidade de nomes:
            this.nome = nome;
            this.idade = idade;
        }

        // métodos de instância (acessam estado do objeto via this)
        String getNome() { return nome; }
        int getIdade()   { return idade; }

        // método de instância com retorno do próprio objeto (fluent)
        Pessoa fazerAniversario() {
            this.idade++;
            return this; // permite encadear .fazerAniversario().fazerAniversario()
        }

        // método estático (não acessa "this")
        static String especie() { return especie; }

        // toString para impressão
        @Override public String toString() { return nome + " (" + idade + ")"; }
    }

    // Subclasse para demonstrar super.metodo()
    static class Estudante extends Pessoa {
        private String curso;
        Estudante(String nome, int idade, String curso) {
            super(nome, idade);
            this.curso = curso;
        }
        @Override public String toString() {
            // usa super.toString() com ponto
            return super.toString() + " - " + curso;
        }
    }

    // Record: acessores gerados com notação ponto (nome(), idade())
    record Cliente(String id, String nome) {}

    // Enum: constantes e método de instância
    enum Status {
        NOVO, PAGO, ENVIADO;
        boolean concluido() { return this == ENVIADO; }
    }

    // Classe interna (inner) acessando Outer.this
    static class Outer {
        private final String titulo = "Outer";
        class Inner {
            String fullName() {
                // "Outer.this" referencia a instância externa
                return Outer.this.titulo + ".Inner";
            }
        }
    }

    // Chamada de metodos no main
    public static void main(String[] args) {

        instanciaEMetodos();
        estaticos();
        encadeamentoFluent();
        arraysELength();
        recordsEnums();
        innerClasses();
        streamsEOptional();
        qualificacaoPacote();
        nullSafetyDica();
        stringBuilderDemo();

    }

    // 2 - Instância: objeto.campo / objeto.metodo()
    static void instanciaEMetodos() {

        System.out.println("INSTÂNCIA");
        Pessoa p = new Pessoa("Ana", 20);

        // leitura por ponto
        System.out.println(p.getNome() + " tem " + p.getIdade());

        // alteração via método e retorno do próprio objeto (fluent)
        p.fazerAniversario();
        System.out.println("Após aniversário: " + p);

        // Subclasse usando super.metodo()
        Estudante e = new Estudante("João", 22, "Computação");
        System.out.println(e.toString());

    }

    // 3 -  Estáticos: Classe.membro / Classe.metodo()
    static void estaticos() {

        System.out.println("\nESTÁTICOS");
        System.out.println(Pessoa.especie);      // campo estático
        System.out.println(Pessoa.especie());    // método estático
        // Convenção: prefira acesso estático por Classe (não por instância).

    }

    // 4- Encadeamento (fluent API): obj.metodo().outro().final()
    static void encadeamentoFluent() {

        System.out.println("\nENCADEAMENTO - FLUENT");
        Pessoa p = new Pessoa("Lia", 17);
        p.fazerAniversario()
                .fazerAniversario()
                .fazerAniversario(); // 3 aniversários encadeados
        System.out.println(p); // Lia (20)

    }

    // 5 - Arrays: campo .length (não é método)
    static void arraysELength() {

        System.out.println("\nARRAIS E .LENGTH");
        int[] nums = {1, 2, 3, 4};
        System.out.println("Tamanho: " + nums.length); // sem parênteses

        // String tem .length() como MÉTODO, não campo:
        String s = "Java";
        System.out.println("String.length(): " + s.length()); // com parênteses

    }

    // 6 - Records e Enums usando notação ponto
    static void recordsEnums() {

        System.out.println("\nRECORD E ENUMS");
        Cliente c = new Cliente("c1", "Bia");

        // accessors gerados: c.id(), c.nome()
        System.out.println(c.id() + " - " + c.nome());

        Status st = Status.PAGO;
        System.out.println("Status=" + st + ", concluido? " + st.concluido());

    }

    // 7 - Classes internas (inner) e Outer.this ====
    static void innerClasses() {

        System.out.println("\nINNER CLASS E OUTER.THIS");
        Outer o = new Outer();
        Outer.Inner in = o.new Inner();
        System.out.println(in.fullName()); // Outer.Inner

    }

    // 8 - Streams e Optional: encadeamento expressivo por ponto
    static void streamsEOptional() {

        System.out.println("\nSTREAMS E OPTIONAL");
        List<String> nomes = List.of("ana", "joao", "bia", "lia");

        // Encadeamento: stream().filter().map().sorted().findFirst().orElse(...)
        String primeiraMaiuscula =
                nomes.stream()
                        .filter(n -> n.length() > 3)
                        .map(n -> Character.toUpperCase(n.charAt(0)) + n.substring(1))
                        .sorted()
                        .findFirst()
                        .orElse("N/A");
        System.out.println("Primeira (formatada): " + primeiraMaiuscula);

        // Optional: criação, map encadeado e orElse
        Optional<String> maybe = Optional.of("java");
        int tamanho = maybe.map(String::toUpperCase)
                .map(String::length)
                .orElse(0);
        System.out.println("Tamanho via Optional: " + tamanho);

    }

    // 9 - Qualificação de pacotes e tipos ====
    static void qualificacaoPacote() {

        System.out.println("\n=QUALIFICAÇÃO DE PACOTES E TIPOS");
        // Exemplo conceitual (normalmente você usaria import):
        java.util.Map<String, Integer> mapa = new java.util.HashMap<>();
        mapa.put("x", 1);
        System.out.println("mapa=" + mapa);
        // Notação ponto também separa segmentos de pacote: a.b.c.Classe

    }

    // 10 - Null-safety: cuidado com o ponto em referências null
    static void nullSafetyDica() {

        System.out.println("\nNULL SAFETY");
        Pessoa p = null;
        // // p.getNome(); // DESCOMENTAR lança NullPointerException

        // Boas práticas:
        Pessoa segura = Objects.requireNonNullElseGet(p, () -> new Pessoa("Default", 0));
        System.out.println(segura.getNome());

    }

    // 11 - StringBuilder: exemplo clássico de encadeamento por ponto
    static void stringBuilderDemo() {

        System.out.println("\nSTRINGBUILDER");
        String msg = new StringBuilder()
                .append("Olá, ")
                .append("notação ")
                .append("ponto!")
                .toString(); // fecha o builder e obtém String
        System.out.println(msg);

    }

}
