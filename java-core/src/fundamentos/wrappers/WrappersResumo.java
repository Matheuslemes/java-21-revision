package fundamentos.wrappers;

import java.util.*;
import java.util.stream.IntStream;

/**
 * WrappersResumo.java
 *
 * Guia prático dos WRAPPERS em Java:
 *  - Primitivos: byte, short, int, long, float, double, char, boolean
 *  - Wrappers:  Byte, Short, Integer, Long, Float, Double, Character, Boolean
 *
 * Por que existem?
 *  - Coleções/Generics só aceitam OBJETOS (ex.: List<Integer>).
 *  - APIs que exigem referência (podem carregar null).
 *  - Métodos utilitários estáticos (parse/compare/valueOf/etc.).
 *
 * Atenções:
 *  - Autoboxing/unboxing pode causar NPE (unboxing de null) e custo no hot path.
 *  - '==' compara IDENTIDADE (mesmo objeto) — prefira equals() para conteúdo.
 *  - Cache de wrappers: Integer/Byte/Short/Long/Character (ASCII), Boolean.
 */
public class WrappersResumo {

    public static void main(String[] args) {
        criacaoECache();
        boxingUnboxing();
        comparacaoIgualdadeOrdenacao();
        parsingEFormatacao();
        conversoesNumericas();
        utilitariosEConstantes();
        characterEBoolean();
        colecoesEStreams();
        pitfallsComDesempenhoENPE();
        boasPraticas();
    }

    /* 1) Criação e CACHE (valueOf x new) */
    static void criacaoECache() {

        System.out.println("== Criação & Cache ==");
        // Preferir valueOf (usa cache) ao invés de 'new'
        Integer a = Integer.valueOf(127);
        Integer b = Integer.valueOf(127);
        System.out.println("Integer.valueOf(127) cache? a==b -> " + (a == b)); // true (cache [-128..127])

        Integer c = Integer.valueOf(128);
        Integer d = Integer.valueOf(128);
        System.out.println("Integer.valueOf(128) a==b -> " + (c == d)); // false (fora do cache)

        // Outros caches:
        System.out.println("Boolean.valueOf(true) identico? " + (Boolean.valueOf(true) == Boolean.TRUE)); // true
        System.out.println("Byte.valueOf((byte)10) identico? " + (Byte.valueOf((byte)10) == Byte.valueOf((byte)10))); // true

        // Evite 'new Wrapper(...)'
        Integer e = new Integer(127); // sempre NOVO objeto (deprecated/evite)
        System.out.println("'new Integer(127)' quebra cache? " + (e == a)); // false

    }

    /* 2) Boxing/Unboxing (automático e explícito) */
    static void boxingUnboxing() {
        System.out.println("\n== Boxing/Unboxing ==");
        int p = 42;
        Integer w = p; // autoboxing (int -> Integer)
        int q = w; // unboxing   (Integer -> int)
        System.out.println("p=" + p + ", w=" + w + ", q=" + q);

        // NPE ao desencaixotar null
        try {
            Integer z = null;
            int fail = z;           // NPE
            System.out.println(fail);
        } catch (NullPointerException ex) {
            System.out.println("Unboxing de null -> NullPointerException");
        }
    }

    /* 3) Comparação: == x equals x compareTo/compare */
    static void comparacaoIgualdadeOrdenacao() {
        System.out.println("\n== Comparação (==, equals, compareTo/compare) ==");
        Integer i1 = 1000, i2 = 1000;
        System.out.println("i1 == i2 ? " + (i1 == i2));           // false (identidade)
        System.out.println("i1.equals(i2)? " + i1.equals(i2));    // true (conteúdo)
        System.out.println("Integer.compare(3, 7) = " + Integer.compare(3, 7));    // -1
        System.out.println("i1.compareTo(i2) = " + i1.compareTo(i2));              // 0

        // Ordenação natural com Comparator (wrappers implementam Comparable)
        List<Integer> numeros = new ArrayList<>(List.of(5, 2, 9, 2));
        numeros.sort(Comparator.naturalOrder());
        System.out.println("Ordenado asc: " + numeros);
        numeros.sort(Comparator.reverseOrder());
        System.out.println("Ordenado desc: " + numeros);
    }

    /* 4) Parsing e formatação: parseX, valueOf, toString, radix e decode */
    static void parsingEFormatacao() {
        System.out.println("\n== Parsing & Formatação ==");
        // Inteiros
        int n1 = Integer.parseInt("42");                   // "42" -> 42
        int hex = Integer.parseInt("FF", 16);              // radix 16 -> 255
        int bin = Integer.parseInt("1010", 2);             // radix 2  -> 10
        int dec = Integer.decode("0x2A");                  // detecta 0x/0/#
        System.out.printf("parse: n1=%d, hex=%d, bin=%d, dec=%d%n", n1, hex, bin, dec);

        // Flutuantes
        double d = Double.parseDouble("3.1415");
        float f  = Float.parseFloat("2.5");
        System.out.printf("Double.parse/Float.parse -> d=%.4f, f=%.1f%n", d, f);

        // valueOf -> retorna WRAPPER (e pode usar cache)
        Integer W = Integer.valueOf("123");
        System.out.println("Integer.valueOf(\"123\") -> " + W);

        // toString
        System.out.println("Integer.toString(255, 16) = " + Integer.toString(255, 16)); // "ff"
    }

    /* 5) Conversões numéricas entre wrappers (Number API) */
    static void conversoesNumericas() {
        System.out.println("\n== Conversões numéricas ==");
        Number num = Integer.valueOf(300);
        System.out.println("num.intValue()=" + num.intValue());
        System.out.println("num.longValue()=" + num.longValue());
        System.out.println("num.doubleValue()=" + num.doubleValue());

        Double dd = 12.7;
        System.out.println("dd.intValue() (trunca) = " + dd.intValue()); // 12
    }

    /* 6) Utilitários e constantes úteis de wrappers */
    static void utilitariosEConstantes() {
        System.out.println("\n== Utilitários & Constantes ==");
        // MIN/MAX
        System.out.printf("Integer: [%d .. %d]%n", Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("Long:    [%d .. %d]%n", Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.printf("Double:  (%.1f .. %.1f) ~%n", -Double.MAX_VALUE, Double.MAX_VALUE);
        System.out.println("Double.MIN_VALUE = menor POSITIVO > 0, não o menor negativo!");

        // Bits
        int x = 0b1011_0001;
        System.out.println("bitCount(" + x + ") = " + Integer.bitCount(x));
        System.out.println("rotateLeft(0x01, 3) = 0b" + Integer.toBinaryString(Integer.rotateLeft(0x01, 3)));

        // Checagens
        System.out.println("Character.isDigit('5') = " + Character.isDigit('5'));
        System.out.println("Character.isLetterOrDigit('Ç') = " + Character.isLetterOrDigit('Ç'));
    }

    /* 7) Character & Boolean (regras e helpers) */
    static void characterEBoolean() {
        System.out.println("\n== Character & Boolean ==");
        Character ch = 'A';
        System.out.println("ch=" + ch + ", lower=" + Character.toLowerCase(ch));

        // Boolean parsing é especial: só "true" (case-insensitive) vira true; qualquer outra string -> false
        System.out.println("Boolean.parseBoolean(\"true\")  -> " + Boolean.parseBoolean("true"));
        System.out.println("Boolean.parseBoolean(\"TrUe\") -> " + Boolean.parseBoolean("TrUe"));
        System.out.println("Boolean.parseBoolean(\"yes\")  -> " + Boolean.parseBoolean("yes")); // false
    }

    /* 8) Coleções & Streams (wrappers são obrigatórios em Generics) */
    static void colecoesEStreams() {
        System.out.println("\n== Coleções & Streams ==");
        List<Integer> lista = new ArrayList<>(List.of(1, 2, 3, 4));
        int soma = lista.stream().mapToInt(Integer::intValue).sum(); // evita boxing no somatório
        System.out.println("soma via mapToInt = " + soma);

        // Para desempenho, prefira IntStream quando possível:
        int soma2 = IntStream.of(1, 2, 3, 4).sum();
        System.out.println("soma via IntStream = " + soma2);
    }

    /* 9) Pitfalls comuns: desempenho e NPE (unboxing) */
    static void pitfallsComDesempenhoENPE() {
        System.out.println("\n== Pitfalls: desempenho & NPE ==");
        // Exemplo ilustrativo (tempo não científico, só para mostrar o padrão)
        long t0 = System.nanoTime();
        long acc = 0;
        for (int i = 0; i < 200_000; i++) acc += i; // só primitivo
        long t1 = System.nanoTime();

        Long accBox = 0L;
        for (int i = 0; i < 200_000; i++) accBox += i; // boxing/unboxing a cada iteração
        long t2 = System.nanoTime();

        System.out.printf("prim=%dµs, boxed=%dµs (ilustrativo)%n", (t1 - t0) / 1000, (t2 - t1) / 1000);

        // NPE com unboxing
        try {
            Integer maybeNull = null;
            int val = maybeNull; // NPE
            System.out.println(val);
        } catch (NullPointerException e) {
            System.out.println("CUIDADO: unboxing de null lança NPE.");
        }
    }

    /* 10) Boas práticas rápidas */
    static void boasPraticas() {
        System.out.println("\n== Boas práticas ==");
        // 1) Prefira primitivos em hot paths (menos GC/boxing)
        // 2) Use wrappers quando precisar de null/coleções/APIs
        // 3) equals/compareTo ao invés de '==' para comparar valores de wrappers
        Integer A = null, B = 10;
        System.out.println("Objects.equals(null,10) -> " + Objects.equals(A, B)); // null-safe

        // 4) Evite 'new Wrapper(...)'; prefira valueOf/parse
        Integer v = Integer.valueOf(42);
        System.out.println("valueOf(42) -> " + v);

        // 5) Optional para retornos possivelmente ausentes (evita null explícito)
        Optional<Integer> maybeIdade = Optional.of(21);
        System.out.println("Optional.map().orElse: " + maybeIdade.map(x -> x + 1).orElse(0));
    }

}