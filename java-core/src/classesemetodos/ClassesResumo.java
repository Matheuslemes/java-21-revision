package classesemetodos;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassesResumo.java
 *
 * Conteudo
 * 1) O que eh uma classe  modelo com estado e comportamento
 * 2) Campos  metodos  construtores e sobrecarga
 * 3) Encapsulamento  modificadores de acesso  this
 * 4) Metodos de classe e de instancia  static x objeto
 * 5) Heranca  super  override  polimorfismo
 * 6) Classes aninhadas  static nested e inner
 * 7) Imutabilidade pragmatica  factories e builders simples
 * 8) Convenio toString  equals e hashCode  visao geral
 * 9) Boas praticas
 */
public class ClassesResumo {

    public static void main(String[] args) {
        definicaoBasica();
        construtoresESobrecarga();
        encapsulamentoDemonstracao();
        metodosStaticVsInstancia();
        herancaEPolimorfismo();
        classesAninhadas();
        imutabilidadeEFactory();
        conveniosObject();
        boasPraticas();
    }

    // 1) O que eh uma classe
    static void definicaoBasica() {

        System.out.println("Classe eh um molde que descreve dados e acoes");
        System.out.println("Objeto eh uma instancia concreta criada a partir do molde");

        // Exemplo minimalista
        Pessoa p = new Pessoa("Ana", 30);
        System.out.println("instancia criada com nome e idade  " + p.getNome() + "  " + p.getIdade());

    }

    // 2) Construtores e sobrecarga
    static void construtoresESobrecarga() {

        System.out.println("\nConstrutores e sobrecarga");

        Produto livro = new Produto("Livro", 79_90);
        Produto livroSemPreco = new Produto("Livro Basico");
        System.out.println("produto com preco informado  " + livro);
        System.out.println("produto com preco padrao     " + livroSemPreco);

    }

    // 3) Encapsulamento  modificadores  this
    static void encapsulamentoDemonstracao() {

        System.out.println("\nEncapsulamento e modificadores");

        Pessoa p = new Pessoa("Bruno", 25);
        p.setIdade(26);
        System.out.println("idade atualizada via metodo  " + p.getIdade());
        // private protege a invariancia de Pessoa  acesso indireto via metodos

    }

    // 4) static x instancia
    static void metodosStaticVsInstancia() {

        System.out.println("\nMetodos de classe e de instancia");

        System.out.println("contador global antes de criar  " + Contador.total());
        new Contador();
        new Contador();
        System.out.println("contador global depois de criar " + Contador.total());
        // metodo de instancia opera no estado do objeto
        Cronometro c = new Cronometro();
        c.start();
        c.stop();
        System.out.println("duracao medida  " + c.duracaoMillis() + " ms");

    }

    // 5) Heranca  super  override  polimorfismo
    static void herancaEPolimorfismo() {

        System.out.println("\nHeranca e polimorfismo");

        Pessoa cliente = new Cliente("Carla", 34, "Gold");
        Pessoa funcionario = new Funcionario("Davi", 28, "TI");

        // despacho dinamico em toString e saudacao
        System.out.println("descricao de cliente  " + cliente);
        System.out.println("descricao de funcionario  " + funcionario);

        List<Pessoa> pessoas = List.of(cliente, funcionario);
        for (Pessoa p : pessoas) {
            System.out.println("saudacao polimorfica  " + p.saudacao());
        }

    }

    // 6) Classes aninhadas
    static void classesAninhadas() {

        System.out.println("\nClasses aninhadas  static nested e inner");

        // static nested nao precisa de instancia externa
        Caixa.Peso padrao = new Caixa.Peso(2_50);
        System.out.println("peso padrao criado via classe aninhada  " + padrao.gramas());

        // inner precisa de instancia externa
        Caixa cx = new Caixa(30, 20, 10);
        Caixa.Volume vol = cx.new Volume();
        System.out.println("volume calculado por inner  " + vol.emCentimetrosCubicos());

    }

    // 7) Imutabilidade e factory simples
    static void imutabilidadeEFactory() {

        System.out.println("\nImutabilidade pragmatica e fabrica");

        Dinheiro v1 = Dinheiro.deReais(12_30);
        Dinheiro v2 = v1.acrescentar(0_70);
        System.out.println("valor original  " + v1);
        System.out.println("valor com acrescimo  " + v2);

        Usuario u = new Usuario.Builder()
                .nome("Eva")
                .email("eva@mail.com")
                .perfil("admin")
                .build();
        System.out.println("usuario criado via builder  " + u);

    }

    // 8) Convenios Object
    static void conveniosObject() {

        System.out.println("\nConvenios toString equals e hashCode  visao geral");

        Pessoa a = new Pessoa("Fabi", 22);
        Pessoa b = new Pessoa("Fabi", 22);
        System.out.println("toString amigavel  " + a);
        System.out.println("igualdade por conteudo implementada em Pessoa  " + a.equals(b));
        System.out.println("hashCode consistente com igualdade  " + (a.hashCode() == b.hashCode()));

    }

    // 9) Boas praticas
    static void boasPraticas() {

        System.out.println("\nBoas praticas");

        System.out.println("mantenha campos privados e exponha metodos claros");
        System.out.println("use construtores coesos  ou factories quando houver regras de criacao");
        System.out.println("evite heranca profunda  prefira composicao quando fizer sentido");
        System.out.println("sobrescreva toString para depuracao  equals e hashCode quando a identidade for por valor");
        System.out.println("documente invariantes e valide argumentos no construtor");
    }

    /*MODELOS DE APOIO*/

    // Modelo basico com encapsulamento e equals por valor
    static class Pessoa {
        private String nome;
        private int idade;

        public Pessoa(String nome, int idade) {
            if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome invalido");
            if (idade < 0) throw new IllegalArgumentException("idade negativa");
            this.nome = nome;
            this.idade = idade;
        }

        public String getNome() { return nome; }
        public int getIdade() { return idade; }
        public void setIdade(int nova) {
            if (nova < 0) throw new IllegalArgumentException("idade negativa");
            this.idade = nova;
        }

        public String saudacao() { return "Ola  " + nome; }

        @Override
        public String toString() { return "Pessoa  " + nome + "  " + idade; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pessoa p)) return false;
            return idade == p.idade && nome.equals(p.nome);
        }
        @Override
        public int hashCode() {
            int r = 17;
            r = 31 * r + nome.hashCode();
            r = 31 * r + idade;
            return r;
        }
    }

    static class Cliente extends Pessoa {
        private final String categoria;
        public Cliente(String nome, int idade, String categoria) {
            super(nome, idade);
            this.categoria = categoria;
        }

        @Override
        public String saudacao() { return "Bem vinda cliente " + getNome() + "  categoria " + categoria; }

        @Override
        public String toString() { return "Cliente  " + getNome() + "  " + categoria; }
    }

    static class Funcionario extends Pessoa {
        private final String setor;
        public Funcionario(String nome, int idade, String setor) {
            super(nome, idade);
            this.setor = setor;
        }
        @Override
        public String saudacao() { return "Ola colaborador " + getNome() + "  setor " + setor; }
        @Override
        public String toString() { return "Funcionario  " + getNome() + "  " + setor; }
    }

    // Produto com sobrecarga de construtor e toString
    static class Produto {
        private final String nome;
        private final double preco;
        public Produto(String nome) { this(nome, 0_00); }
        public Produto(String nome, double preco) {
            if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome invalido");
            if (preco < 0) throw new IllegalArgumentException("preco negativo");
            this.nome = nome;
            this.preco = preco;
        }
        @Override
        public String toString() { return "Produto  " + nome + "  " + preco; }

    }

    // Contador com estado de classe
    static class Contador {
        private static int total;
        public Contador() { total++; }
        public static int total() { return total; }
    }

    // Cronometro de instancia
    static class Cronometro {
        private long ini;
        private long fim;
        public void start() { ini = System.nanoTime(); }
        public void stop() { fim = System.nanoTime(); }
        public long duracaoMillis() { return (fim - ini) / 1_000_000; }
    }

    // Classe aninhada estatica e inner
    static class Caixa {
        private final int comp;
        private final int larg;
        private final int alt;

        Caixa(int comp, int larg, int alt) {
            this.comp = comp; this.larg = larg; this.alt = alt;
        }

        static class Peso {
            private final int gramas;
            Peso(int gramas) { this.gramas = gramas; }
            int gramas() { return gramas; }
            @Override public String toString() { return gramas + " g"; }
        }

        class Volume {
            int emCentimetrosCubicos() { return comp * larg * alt; }
        }
    }

    // Tipo imutavel com factory e operacao pura
    static final class Dinheiro {
        private final long centavos;
        private Dinheiro(long centavos) {
            if (centavos < 0) throw new IllegalArgumentException("valor negativo");
            this.centavos = centavos;
        }
        public static Dinheiro deReais(long centavos) { return new Dinheiro(centavos); }
        public Dinheiro acrescentar(long centavos) { return new Dinheiro(this.centavos + centavos); }
        @Override public String toString() {
            long reais = centavos / 100;
            long cents = Math.abs(centavos % 100);
            return "R$ " + reais + "," + String.format("%02d", cents);
        }
    }

    // Builder simples para objeto com muitos atributos opcionais
    static final class Usuario {
        private final String nome;
        private final String email;
        private final String perfil;
        private final List<String> tags;

        private Usuario(String nome, String email, String perfil, List<String> tags) {
            this.nome = nome; this.email = email; this.perfil = perfil; this.tags = tags;
        }

        public static class Builder {
            private String nome;
            private String email;
            private String perfil = "leitor";
            private List<String> tags = new ArrayList<>();

            public Builder nome(String v) { this.nome = v; return this; }
            public Builder email(String v) { this.email = v; return this; }
            public Builder perfil(String v) { this.perfil = v; return this; }
            public Builder adicionarTag(String v) { this.tags.add(v); return this; }

            public Usuario build() {
                if (nome == null || nome.isBlank()) throw new IllegalStateException("nome ausente");
                if (email == null || !email.contains("@")) throw new IllegalStateException("email invalido");
                return new Usuario(nome, email, perfil, List.copyOf(tags));
            }
        }

        @Override public String toString() {
            return "Usuario  " + nome + "  " + email + "  " + perfil + "  tags " + tags.size();
        }
    }
}
