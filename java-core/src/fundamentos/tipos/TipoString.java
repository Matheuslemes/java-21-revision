package fundamentos.tipos;

import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringJoiner;

/**
 * StringResumo.java
 *
 * Guia prático e executável sobre String em Java 21.
 * Pontos-chave:
 * - String é IMUTÁVEL e interned (string pool para literais).
 * - Use equals/compareTo para comparar conteúdo; "==" compara REFERÊNCIA.
 * - Em loops/concatenações pesadas, use StringBuilder (mutável).
 * - APIs modernas: strip, isBlank, lines, repeat, transform, formatted.
 * - Unicode: length() conta code units UTF-16; use codePoints() para emoji/suplementares.
 */

public class TipoString {

    public static void main(String[] args) {

        imutabilidadeEPool();
        comparacao();
        concatenacaoEPerformance();
        operacoesBasicas();
        buscaEExtracao();
        substituicaoERegex();
        formatacao();
        unicodeECodePoints();
        normalizacaoDeAcentos();
        bytesECharset();
        utilidadesModernas();

    }

    // 1 - Imutabilidade + String Pool
    static void imutabilidadeEPool() {

        System.out.println("IMUTABILIDADE E STRING POOL");
        String a = "Java"; // literal -> entra no pool
        String b = "Java"; // aponta para o mesmo objeto do pool
        String c = new String("Java"); // força NOVO objeto (fora do pool)

        System.out.println("a == b? " + (a == b)); // true (mesmo objeto no pool)
        System.out.println("a == c? " + (a == c)); // false (objetos diferentes)
        System.out.println("a.equals(c)? " + a.equals(c)); // true (mesmo conteúdo)

        // Imutabilidade: "alterar" cria NOVA string
        String s = "Hi";
        s += " there"; // nova instância
        System.out.println(s); // "Hi there"

    }

    // 2 - Comparação: == vs equals, compareTo/equalsIgnoreCase
    static void comparacao() {

        System.out.println("\nCOMPARAÇÃO");
        String x = "abc";
        String y = "ab" + "c"; // compilador concatena em tempo de compilação (pool)
        System.out.println("x == y? " + (x == y));      // true (pool)
        System.out.println("x.equals(y)? " + x.equals(y)); // true

        String a = "Java";
        String b = "java";
        System.out.println("equalsIgnoreCase: " + a.equalsIgnoreCase(b)); // true
        System.out.println("compareTo (lexicográfico): " + "car".compareTo("cat")); // <0 (r < t)

    }

    // 3 - Concatenação e performance: prefira StringBuilder em loops
    static void concatenacaoEPerformance() {

        System.out.println("\nCONCATENAÇÃO E PERFORMANCE");
        // Bom para poucas concatenações:
        String msg = "Olá, " + "mundo " + 21;
        System.out.println(msg);

        // Em loop: prefira StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) sb.append(i).append(' ');
        System.out.println("SB: " + sb.toString());

        // StringBuffer é sincronizado (mais pesado); StringBuilder é não-sincronizado (geralmente preferível).

    }

    // 4 - Operações básicas: length, isEmpty, isBlank, strip/trim, toUpper/Lower (Locale)
    static void operacoesBasicas() {

        System.out.println("\nOPERAÇÕES BÁSICAS");
        String s = "  Olá  ";
        System.out.println("length=" + s.length());         // conta espaços
        System.out.println("isEmpty? " + s.isEmpty());      // false
        System.out.println("isBlank? " + s.isBlank());      // false (tem letras)

        System.out.println("trim   -> '" + s.trim() + "'");  // remove \u0020 (espaço ASCII)
        System.out.println("strip  -> '" + s.strip() + "'"); // remove todos os whitespaces (Unicode-aware)

        // Locale afeta maiúsculas/minúsculas (especialmente em turco)
        System.out.println("UPPER (pt-BR): " + "ação".toUpperCase(new Locale("pt", "BR")));
        System.out.println("lower (en-US): " + "İSTANBUL".toLowerCase(Locale.US));

    }

    // 5 - Busca/extração: startsWith/endsWith/contains/indexOf/lastIndexOf/substring
    static void buscaEExtracao() {

        System.out.println("\nBUSCA E EXTRAÇÃO");
        String url = "https://example.com/produtos?id=7";
        System.out.println("startsWith('https'): " + url.startsWith("https"));
        System.out.println("endsWith('7'): " + url.endsWith("7"));
        System.out.println("contains('produtos'): " + url.contains("produtos"));

        int idxQ = url.indexOf('?');
        String path = (idxQ >= 0) ? url.substring(0, idxQ) : url;
        System.out.println("path = " + path);

        // substring(inclusive, exclusive)
        String produto = "/api/v1/items".substring(8, 13); // pega "items" começando de cálculo -> cuidado!
        System.out.println("substring demo: " + produto);

    }

    // 6 - Substituição e regex: replace/replaceAll/matches/split/join
    static void substituicaoERegex() {

        System.out.println("\n=SUBSTITUIÇÃO E REGEX");
        String t = "R$ 1.234,56";

        // replace substitui literal; replaceAll usa regex
        System.out.println(t.replace("R$ ", ""));         // "1.234,56"
        System.out.println(t.replaceAll("[^0-9,]", ""));  // "1234,56"

        // matches verifica se a STRING INTEIRA casa com a regex
        String email = "user@mail.com";
        System.out.println("email válido? " + email.matches("^[\\w.%-]+@[\\w.-]+\\.[A-Za-z]{2,}$"));

        // split -> array; String.join ou StringJoiner para juntar
        String csv = "ana,joao,lia";
        String[] nomes = csv.split(",");
        System.out.println(Arrays.toString(nomes));
        String rejoined = String.join(" | ", nomes);
        System.out.println(rejoined);

        StringJoiner sj = new StringJoiner(";", "[", "]");
        for (String n : nomes) sj.add(n.toUpperCase());
        System.out.println(sj.toString());

    }

    // 7 - Formatação: String.format / formatted / printf
    static void formatacao() {

        System.out.println("\nFORMATAÇÃO");
        String r1 = String.format(Locale.US, "Total: $%.2f (itens=%d)", 12.345, 3);
        System.out.println(r1);

        // formatted (Java 15+): atalho encadeado
        String r2 = "Olá, %s! Você tem %d novas mensagens.".formatted("Matheus", 4);
        System.out.println(r2);

        // printf direto no stdout
        System.out.printf("Pi ~= %.4f%n", Math.PI);
    }

    // 8 - Unicode: length (code units) vs codePointCount (code points); emoji
    static void unicodeECodePoints() {

        System.out.println("\nUNICODE (CODE POINTS)");
        String rocket = "🚀"; // U+1F680 (suplementar, precisa de par surrogate)
        System.out.println("length = " + rocket.length()); // 2 (duas unidades UTF-16)
        int cps = rocket.codePointCount(0, rocket.length());
        System.out.println("codePointCount = " + cps);     // 1 (um caractere lógico)

        // Iterar por code points corretamente
        rocket.codePoints().forEach(cp -> System.out.println("cp=U+" + Integer.toHexString(cp).toUpperCase()));

        // charAt pega unidade UTF-16 (pode "quebrar" emoji); prefira codePoints para texto arbitrário.

    }

    // 9 - Normalização de acentos (remover diacríticos)
    static void normalizacaoDeAcentos() {

        System.out.println("\nNORMALIZAÇÃO E ACENTOS");
        String acentuado = "Ação Café São";

        // NFD decompõe (a +  ́), depois removemos marcas de acento (\p{M})
        String semAcento = Normalizer.normalize(acentuado, Normalizer.Form.NFD)
                .replaceAll("\\p{M}+", "");
        System.out.println("Original: " + acentuado);
        System.out.println("Sem acento: " + semAcento);

    }

    // 10 - Strings ↔ bytes (UTF-8)
    static void bytesECharset() {

        System.out.println("\nUTF-8: BYTES <-> STRING");
        String original = "Olá, 你好, 🚀";
        byte[] bytes = original.getBytes(StandardCharsets.UTF_8); // codifica
        String copiada = new String(bytes, StandardCharsets.UTF_8); // decodifica
        System.out.println("original=[" + original + "]");
        System.out.println("bytes=" + Arrays.toString(bytes));
        System.out.println("copiada=[" + copiada + "]");

    }

    // 11 - Utilidades modernas: strip/indent/transform/lines/repeat
    static void utilidadesModernas() {

        System.out.println("\nUTILIDADES MODERNAS");
        String multi = "  linha1\n  linha2\n  linha3  ";

        // strip() remove whitespaces Unicode nas pontas
        System.out.println("striped='" + multi.strip() + "'");

        // lines() -> stream de linhas
        long linhas = multi.lines().count();
        System.out.println("linhas=" + linhas);

        // repeat(n)
        System.out.println("ha".repeat(3)); // "hahaha"

        // indent (Java 12+): desloca com espaços (positivo) ou remove (negativo)
        System.out.println("indent:\n" + "x\ny".indent(2));

        // transform: aplica função e retorna resultado encadeável
        String upperCsv = "a,b,c".transform(s -> s.toUpperCase(Locale.ROOT).replace(",", " | "));
        System.out.println(upperCsv);

    }

}
