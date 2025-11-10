package fundamentos.objetovsprimitivo;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * ObjetoVsPrimitivoResumo.java
 *
 * Tópicos cobertos:
 * - Tipos PRIMITIVOS (byte, short, int, long, float, double, char, boolean)
 *   vs OBJETOS (Wrappers: Byte, Short, Integer, Long, Float, Double, Character, Boolean; String; BigDecimal; registros/POJOs).
 * - Memória & semântica: valores X referências (sempre pass-by-value em Java).
 * - Autoboxing/unboxing e armadilhas (NPE, desempenho).
 * - Igualdade: '==' (identidade) vs equals (conteúdo); cache de Integer [-128..127].
 * - null X defaults; coleções/Generics exigem objetos; IntStream x Stream<Integer>.
 * - Mutabilidade e efeitos colaterais; cópia defensiva.
 * - Boas práticas: Objects.equals, Optional, BigDecimal para dinheiro.
 */

public class ObjetoVsPrimitivoResumo {

    public static void main(String[] args) {
        igualdadeEIntegerCache();
        autoboxingUnboxingPitfalls();
        nullVsDefaults();
        colecoesEStreams();
        arraysPrimitivoVsWrapper();
        passagemDeParametros();
        mutabilidadeVsImutabilidade();
        dinheiroDoubleVsBigDecimal();
        boasPraticas();
    }

    //1 - Igualdade: '==' (identidade) vs equals (conteúdo) + Integer cache
    static void igualdadeEIntegerCache() {

        System.out.println("== Igualdade e cache de Integer ==");
        Integer a = 127, b = 127; // cache [-128..127] -> MESMO objeto
        Integer c = 128, d = 128; // fora do cache -> objetos diferentes
        System.out.println("127: a==b? " + (a == b)); // true (cache)
        System.out.println("128: c==d? " + (c == d)); // false
        System.out.println("128: c.equals(d)? " + c.equals(d)); // true (valor)

        int p = 128, q = 128; // primitivos comparam VALOR sempre
        System.out.println("primitivos 128: p==q? " + (p == q)); // true

    }

    // 2 - Autoboxing/Unboxing e armadilhas (NPE, custo)
    static void autoboxingUnboxingPitfalls() {
        System.out.println("\n== Autoboxing/Unboxing (armadilhas) ==");
        Integer x = null;
        try {
            int y = x; // unboxing -> NullPointerException!
            System.out.println(y);
        } catch (NullPointerException e) {
            System.out.println("Unboxing de null -> NPE (evite!)");
        }

        // Comparações: prefira equals ou comparar primitivos
        Integer i1 = 1000, i2 = 1000;
        System.out.println("i1 == i2 ? " + (i1 == i2));        // false (identidade)
        System.out.println("i1.equals(i2)? " + i1.equals(i2)); // true (valor)

        // Autoboxing em loops pode custar caro (obj novos + GC)
        long start = System.nanoTime();
        long sum = 0;
        for (int k = 0; k < 1_0000; k++) { // 10 mil
            // OK: soma só com primitivo
            sum += k;
        }
        long tPrim = System.nanoTime() - start;

        start = System.nanoTime();
        Long sumBox = 0L; // boxing desnecessário no hot path
        for (int k = 0; k < 1_0000; k++) {
            sumBox += k; // boxing/unboxing repetidos
        }
        long tBox = System.nanoTime() - start;

        System.out.printf("loop primitive=%dµs, boxed=%dµs (exemplo ilustrativo)%n",
                tPrim / 1000, tBox / 1000);

    }

    // 3 - null (objetos) vs default values (campos primitivos)
    static int defaultInt;         // campos têm default: 0
    static boolean defaultBool;    // false
    static Integer defaultInteger; // null
    static void nullVsDefaults() {
        System.out.println("\n== null vs defaults ==");
        System.out.println("campo int default=" + defaultInt);
        System.out.println("campo boolean default=" + defaultBool);
        System.out.println("campo Integer default=" + defaultInteger); // null

        // Variáveis LOCAIS não têm default -> devem ser inicializadas antes de usar
        // int localInt; System.out.println(localInt); // ERRO de compilação

    }

    // 4 - Coleções e Streams: exigem OBJETOS (wrappers) — use IntStream para desempenho
    static void colecoesEStreams() {
        System.out.println("\n== Coleções e Streams ==");
        // List<int> NÃO existe; use wrappers:
        List<Integer> lista = new ArrayList<>(List.of(1, 2, 3));
        System.out.println("List<Integer> = " + lista);

        // Streams: prefira IntStream/LongStream/DoubleStream para numéricos (sem boxing)
        int soma = IntStream.of(1, 2, 3).sum();
        System.out.println("IntStream sum = " + soma);

        // Se você usar Stream<Integer>, há boxing/unboxing:
        int somaBox = Stream.of(1, 2, 3).mapToInt(Integer::intValue).sum();
        System.out.println("Stream<Integer>.mapToInt().sum() = " + somaBox);
    }

    // 5 - Arrays de primitivo vs arrays de wrapper
    static void arraysPrimitivoVsWrapper() {

        System.out.println("\n== Arrays: primitivo vs wrapper ==");
        int[] prim = {1, 2, 3};
        Integer[] wrap = {1, null, 3}; // wrappers aceitam null
        System.out.println("prim.length=" + prim.length + ", wrap[1]=" + wrap[1]);

    }

    /*
     * 6 - Passagem de parâmetros: SEMPRE por valor (valor da referência)
     *    - Primitivo: cópia do valor.
     *    - Objeto: cópia da REFERÊNCIA (pode alterar estado interno).*/
    static class Caixa { int n; Caixa(int n){ this.n = n; } }

    static void alteraPrimitivo(int v) { v = 999; } // NÃO afeta o chamador
    static void alteraCampoObjeto(Caixa c) { c.n = 999; } // AFETA estado do objeto
    static void reatribuiReferencia(Caixa c) { c = new Caixa(123); } // NÃO afeta a ref do chamador

    static void passagemDeParametros() {

        System.out.println("\n== Pass-by-value (sempre) ==");
        int p = 10;
        alteraPrimitivo(p);
        System.out.println("p após alteraPrimitivo: " + p); // 10

        Caixa cx = new Caixa(10);
        alteraCampoObjeto(cx);
        System.out.println("cx.n após alteraCampoObjeto: " + cx.n); // 999

        reatribuiReferencia(cx);
        System.out.println("cx.n após reatribuiReferencia: " + cx.n); // 999 (inalterado)

    }

    // 7 - Mutabilidade (objetos) vs imutabilidade; aliasing e cópia defensiva
    static class Pedido {
        private final Date criadoEm; // Date é mutável -> cuidado (exemplo didático)
        Pedido(Date criadoEm) { this.criadoEm = new Date(criadoEm.getTime()); } // cópia defensiva
        public Date getCriadoEm() { return new Date(criadoEm.getTime()); } // cópia defensiva
    }

    static void mutabilidadeVsImutabilidade() {
        System.out.println("\n== Mutabilidade & cópia defensiva ==");
        Date agora = new Date();
        Pedido p = new Pedido(agora);
        Date exposto = p.getCriadoEm();
        exposto.setTime(0L); // tentar mutar fora
        System.out.println("Pedido protegido? " + (p.getCriadoEm().getTime() != 0L)); // true
        // Dica: prefira tipos IMUTÁVEIS (ex.: java.time.*) em vez de Date/Calendar.

    }

     // 8 - Dinheiro: evite double; use BigDecimal (OBJETO imutável)
    static void dinheiroDoubleVsBigDecimal() {

        System.out.println("\n== Dinheiro: double vs BigDecimal ==");
        double d = 0.1 + 0.2; // representação binária -> 0.30000000000000004
        System.out.println("double 0.1 + 0.2 = " + d);

        BigDecimal bd = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        System.out.println("BigDecimal 0.1 + 0.2 = " + bd); // 0.3

    }

    // 9 - Boas práticas e utilitários
    static void boasPraticas() {

        System.out.println("\n== Boas práticas ==");
        Integer A = null, B = 10;
        System.out.println("Objects.equals(null,10) -> " + Objects.equals(A, B)); // evita NPE
        System.out.println("Objects.compare(2, 3, Integer::compare) -> " +
                Objects.compare(2, 3, Integer::compare));

        // Optional para retornos que podem estar ausentes (melhor que null explícito)
        Optional<Integer> maybe = Optional.of(42);
        System.out.println("Optional.map: " + maybe.map(v -> v * 2).orElse(-1));

    }
}