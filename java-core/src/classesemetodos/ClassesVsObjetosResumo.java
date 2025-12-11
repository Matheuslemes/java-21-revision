package classesemetodos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassesVsObjetosResumo.java
 *
 * Conteudo
 * 1) Definicao  classe como molde  objeto como instancia
 * 2) Estado e comportamento  campos e metodos
 * 3) Construtores  sobrecarga e invariantes
 * 4) static x instancia  membros de classe e de objeto
 * 5) Identidade de referencia x igualdade de conteudo
 * 6) Semantica de referencia  aliasing e copias
 * 7) Encapsulamento  getters  setters e validacoes
 * 8) Composicao e heranca  quando escolher cada uma
 * 9) Imutabilidade pragmatica e objetos de valor
 * 10) Convenios toString  equals  hashCode  contrato basico
 * 11) Boas praticas de projeto com classes e objetos
 */
public class ClassesVsObjetosResumo {

    public static void main(String[] args) {

        definicaoClasseObjeto();
        estadoEComportamento();
        construtoresESobreCarga();
        membrosStaticVsInstancia();
        identidadeVsIgualdade();
        semanticaDeReferencia();
        encapsulamentoNaPratica();
        composicaoEHeranca();
        imutabilidadeEValor();
        conveniosDoObject();
        boasPraticas();

    }

    // 1) Definicao
    static void definicaoClasseObjeto() {

        System.out.println("Classe  molde que descreve dados e acoes");

        System.out.println("Objeto  instancia concreta criada a partir do molde");
        Pessoa p = new Pessoa("Ana", 30);
        System.out.println("instancia gerada com nome e idade  " + p.getNome() + "  " + p.getIdade());

    }

    // 2) Estado e comportamento
    static void estadoEComportamento() {

        System.out.println("\nEstado e comportamento");

        Pessoa p = new Pessoa("Bruno", 25);
        System.out.println("estado inicial  " + p);
        p.aniversario();
        System.out.println("estado apos acao de aniversario  " + p);

    }

    // 3) Construtores e invariantes
    static void construtoresESobreCarga() {

        System.out.println("\nConstrutores  sobrecarga e invariantes");

        Produto a = new Produto("Livro", 79_90);
        Produto b = new Produto("Caderno"); // preço padrao interno
        System.out.println("produto com preco informado  " + a);
        System.out.println("produto com preco padrao     " + b);

        try {
            new Produto("Item Invalido", -10);
        } catch (IllegalArgumentException e) {
            System.out.println("validacao de construtor rejeitou valor negativo");
        }

    }

    // 4) static x instancia
    static void membrosStaticVsInstancia() {

        System.out.println("\nMembros de classe e de instancia");

        System.out.println("contador global antes de criar objetos  " + Contador.total());
        new Contador();
        new Contador();
        System.out.println("contador global apos criar objetos      " + Contador.total());

        Cronometro c = new Cronometro();
        c.start();
        c.stop();
        System.out.println("duracao medida por objeto cronometro    " + c.duracaoMillis() + " ms");

    }

    // 5) Identidade x igualdade
    static void identidadeVsIgualdade() {

        System.out.println("\nIdentidade de referencia e igualdade de conteudo");

        Pessoa x = new Pessoa("Carla", 22);
        Pessoa y = new Pessoa("Carla", 22);
        System.out.println("hash de identidade de x  " + System.identityHashCode(x));
        System.out.println("hash de identidade de y  " + System.identityHashCode(y));
        System.out.println("igualdade por conteudo via equals       " + x.equals(y));
        System.out.println("hashCode consistente com equals         " + (x.hashCode() == y.hashCode()));

    }

    // 6) Referencia  aliasing e copias
    static void semanticaDeReferencia() {

        System.out.println("\nSemantica de referencia  aliasing e copias");

        Pessoa original = new Pessoa("Davi", 28);
        Pessoa alias = original;         // aponta para o mesmo alvo
        alias.setIdade(29);
        System.out.println("alteracao via alias refletida no original  " + original.getIdade());

        Pessoa copia = new Pessoa(original); // copia defensiva
        copia.setIdade(40);
        System.out.println("copia alterada sem afetar original         " + original.getIdade());

    }

    // 7) Encapsulamento
    static void encapsulamentoNaPratica() {

        System.out.println("\nEncapsulamento  protecao do estado interno");

        Pessoa p = new Pessoa("Eva", 27);
        p.setIdade(28);
        System.out.println("idade atualizada via metodo publico        " + p.getIdade());
        try {
            p.setIdade(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("tentativa de por valor invalido foi bloqueada");
        }

    }

    // 8) Composicao e heranca
    static void composicaoEHeranca() {

        System.out.println("\nComposicao e heranca");

        // Composicao  Pedido possui itens e um cliente
        Pedido pedido = new Pedido(new Cliente("Fabi", 33, "Gold"));
        pedido.adicionarItem(new Item("Mouse", 99_90, 1));
        pedido.adicionarItem(new Item("Teclado", 199_90, 1));
        System.out.println("pedido com total em centavos              " + pedido.totalCentavos());

        // Heranca  Animal e subtipos
        Animal a1 = new Cachorro("Rex");
        Animal a2 = new Gato("Mimi");
        System.out.println("som polimorfico cachorro                  " + a1.som());
        System.out.println("som polimorfico gato                      " + a2.som());

    }

    // 9) Imutabilidade e objetos de valor
    static void imutabilidadeEValor() {

        System.out.println("\nImutabilidade pragmatica e objetos de valor");

        Dinheiro v1 = Dinheiro.deCentavos(12_30);
        Dinheiro v2 = v1.acrescentar(0_70);
        System.out.println("valor original                             " + v1);
        System.out.println("valor apos acrescimo                       " + v2);

    }

    // 10) Convenios toString  equals  hashCode
    static void conveniosDoObject() {

        System.out.println("\nConvenios fundamentais");

        Pessoa a = new Pessoa("Gabi", 20);
        System.out.println("toString amigavel                           " + a);
        System.out.println("equals usa nome e idade                     " + a.equals(new Pessoa("Gabi", 20)));
        System.out.println("hashCode alinhado com igualdade             " + a.hashCode());

    }

    // 11) Boas praticas
    static void boasPraticas() {

        System.out.println("\nBoas praticas");

        System.out.println("mantenha campos privados  exponha operacoes claras");
        System.out.println("valide argumentos nos construtores e setters");
        System.out.println("prefira composicao quando o relacionamento for tem um");
        System.out.println("use heranca quando houver eh um com contrato estavel");
        System.out.println("implemente equals e hashCode quando a identidade for por valor");
        System.out.println("considere objetos imutaveis para dados de leitura e concorrencia");

    }

    /* MODELOS DE APOIO */

    static class Pessoa {
        private String nome;
        private int idade;

        public Pessoa(String nome, int idade) {
            if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome invalido");
            if (idade < 0) throw new IllegalArgumentException("idade negativa");
            this.nome = nome;
            this.idade = idade;
        }

        // copia
        public Pessoa(Pessoa other) {
            this(other.nome, other.idade);
        }

        public String getNome() { return nome; }
        public int getIdade() { return idade; }

        public void setIdade(int nova) {
            if (nova < 0) throw new IllegalArgumentException("idade negativa");
            this.idade = nova;
        }

        public void aniversario() { this.idade++; }

        @Override
        public String toString() { return "Pessoa  " + nome + "  " + idade; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pessoa p)) return false;
            return idade == p.idade && nome.equals(p.nome);
        }

        @Override
        public int hashCode() { return Objects.hash(nome, idade); }
    }

    static class Cliente extends Pessoa {
        private final String categoria;
        public Cliente(String nome, int idade, String categoria) {
            super(nome, idade);
            this.categoria = categoria;
        }
        public String categoria() { return categoria; }
    }

    static class Produto {
        private final String nome;
        private final double precoCentavos;
        public Produto(String nome) { this(nome, 0_00); }
        public Produto(String nome, double precoCentavos) {
            if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome invalido");
            if (precoCentavos < 0) throw new IllegalArgumentException("preco negativo");
            this.nome = nome;
            this.precoCentavos = precoCentavos;
        }
        @Override
        public String toString() {
            return "Produto  " + nome + "  " + precoCentavos;
        }
    }

    static class Contador {
        private static int total;
        public Contador() { total++; }
        public static int total() { return total; }
    }

    static class Cronometro {
        private long ini;
        private long fim;
        public void start() { ini = System.nanoTime(); }
        public void stop() { fim = System.nanoTime(); }
        public long duracaoMillis() { return (fim - ini) / 1_000_000; }
    }

    // Composicao
    static class Pedido {
        private final Cliente cliente;
        private final List<Item> itens = new ArrayList<>();
        public Pedido(Cliente cliente) { this.cliente = Objects.requireNonNull(cliente); }
        public void adicionarItem(Item i) {
            if (i == null || i.qtd <= 0) throw new IllegalArgumentException("item invalido");
            itens.add(i);
        }
        public long totalCentavos() {
            long soma = 0;
            for (Item i : itens) soma += i.subtotalCentavos();
            return soma;
        }
    }
    static class Item {
        private final String nome;
        private final long precoCentavos;
        private final int qtd;
        public Item(String nome, double precoCentavos, int qtd) {
            if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome invalido");
            if (precoCentavos < 0 || qtd <= 0) throw new IllegalArgumentException("valores invalidos");
            this.nome = nome;
            this.precoCentavos = Math.round(precoCentavos);
            this.qtd = qtd;
        }
        long subtotalCentavos() { return Math.round(precoCentavos) * qtd; }
    }

    // Heranca
    static abstract class Animal {
        private final String nome;
        protected Animal(String nome) { this.nome = nome; }
        public String nome() { return nome; }
        public abstract String som();
    }
    static class Cachorro extends Animal {
        public Cachorro(String nome) { super(nome); }
        @Override
        public String som() { return "au au"; }
    }
    static class Gato extends Animal {
        public Gato(String nome) { super(nome); }
        @Override
        public String som() { return "miau"; }
    }

    // Imutavel de valor
    static final class Dinheiro {
        private final long centavos;
        private Dinheiro(long centavos) {
            if (centavos < 0) throw new IllegalArgumentException("valor negativo");
            this.centavos = centavos;
        }
        public static Dinheiro deCentavos(long c) { return new Dinheiro(c); }
        public Dinheiro acrescentar(long c) { return new Dinheiro(this.centavos + c); }
        @Override
        public String toString() {
            long r = centavos / 100;
            long s = Math.abs(centavos % 100);
            return "R$ " + r + "," + String.format("%02d", s);
        }
    }
}
