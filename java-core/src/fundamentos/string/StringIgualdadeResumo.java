package fundamentos.string;

import java.text.Collator;
import java.text.Normalizer;
import java.util.Locale;
import java.util.Objects;

/**
 * StringIgualdadeResumo.java
 *
 * Conteudo
 * 1) Identidade de referencia vs igualdade de conteudo
 * 2) equals e equalsIgnoreCase
 * 3) Comparacao com Collator sensivel a idioma
 * 4) regionMatches por trecho com ou sem diferenca de caixa
 * 5) Normalizacao e acentos  comparacao acento insensivel
 * 6) Null safety com Objects.equals
 * 7) contentEquals com StringBuilder  CharSequence
 * 8) compareTo  quando a intencao eh ordenacao
 * 9) String pool e intern  quando identidade pode coincidir
 * 10) Armadilhas comuns e boas praticas
 */
public class StringIgualdadeResumo {

    public static void main(String[] args) {

        referenciaVsConteudo();
        equalsBasico();
        equalsIgnoreCaseTurco();
        collatorLocale();
        regionMatchesExemplos();
        normalizacaoEAcentos();
        nullSafetyObjectsEquals();
        contentEqualsComBuilder();
        compareToParaOrdem();
        internEPool();
        armadilhasEBoasPraticas();

    }

    // 1) Referencia x Conteudo
    static void referenciaVsConteudo() {

        System.out.println("Referencia vs conteudo");

        String a = new String("java");
        String b = new String("java");
        System.out.println("duas instancias distintas com mesmo texto  identidade   " + (a == b));
        System.out.println("duas instancias distintas com mesmo texto  conteudo     " + a.equals(b));

        String c = "java";
        String d = "java";
        System.out.println("literais podem compartilhar alvo interno  identidade     " + (c == d));
        System.out.println("literais com mesmo texto  conteudo                        " + c.equals(d));

    }

    // 2) equals e equalsIgnoreCase
    static void equalsBasico() {

        System.out.println("\nIgualdade sensivel e insensivel a caixa");

        String s1 = "Casa";
        String s2 = "casa";
        System.out.println("sensivel a caixa                                           " + s1.equals(s2));
        System.out.println("ignora caixa                                               " + s1.equalsIgnoreCase(s2));

    }

    // Demonstra cuidado com idiomas  ex  turco
    static void equalsIgnoreCaseTurco() {

        System.out.println("\nIgnorar caixa pode divergir de regras locais");

        String I1 = "I";
        String I2 = "ı"; // i sem ponto do turco
        System.out.println("equalsIgnoreCase padrao para I e i turcos                 " + I1.equalsIgnoreCase(I2));
        // Para regras especificas de idioma  prefira Collator

    }

    // 3) Collator  compara conforme regras de um Locale  pode ignorar acentos e/ou caixa
    static void collatorLocale() {

        System.out.println("\nComparacao com Collator sensivel a idioma");

        Collator br = Collator.getInstance(new Locale("pt", "BR"));
        br.setStrength(Collator.PRIMARY); // ignora acentos e caixa
        String t1 = "acao";
        String t2 = "ação";
        System.out.println("acao e acao com acento tratadas como iguais               " + (br.compare(t1, t2) == 0));

    }

    // 4) regionMatches  comparar trecho com opcao de ignorar caixa
    static void regionMatchesExemplos() {

        System.out.println("\nComparacao por trecho com regionMatches");

        String texto = "Aprender Java agora";
        boolean trecho1 = texto.regionMatches(true, 9, "JAVA", 0, 4); // ignora caixa
        boolean trecho2 = texto.regionMatches(false, 0, "Apren", 0, 5);
        System.out.println("trecho Java ignorando caixa                                " + trecho1);
        System.out.println("prefixo Apren sensivel                                     " + trecho2);

    }

    // 5) Normalizacao e acentos  remover marcas para comparar de forma simples
    static void normalizacaoEAcentos() {

        System.out.println("\nNormalizacao e acentos");

        String a = "cafe";
        String b = "café";
        String normA = Normalizer.normalize(a, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
        String normB = Normalizer.normalize(b, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
        System.out.println("comparacao apos remover diacriticos                         " + normA.equals(normB));

    }

    // 6) Null safety  evite NullPointerException com Objects.equals
    static void nullSafetyObjectsEquals() {

        System.out.println("\nNull safety em igualdade");

        String s = null;
        System.out.println("comparacao segura com null                                 " + Objects.equals(s, "x"));
        System.out.println("comparacao segura entre nulos                               " + Objects.equals(null, null));

    }

    // 7) contentEquals  compara com CharSequence e StringBuilder sem criar String
    static void contentEqualsComBuilder() {

        System.out.println("\ncontentEquals com StringBuilder");

        String base = "dados";
        StringBuilder sb = new StringBuilder().append("da").append("dos");
        System.out.println("conteudo igual entre String e builder                        " + base.contentEquals(sb));

    }

    // 8) compareTo  quando a intencao eh ordenacao  nao igualdade
    static void compareToParaOrdem() {

        System.out.println("\ncompareTo para ordenacao");

        String a = "carro";
        String b = "casa";
        int cmp = a.compareTo(b);
        System.out.println("carro vem depois de casa em ordem lexicografica             " + (cmp > 0));
        System.out.println("igualdade por compareTo requer retorno neutro               " + ("x".compareTo("x") == 0));

    }

    // 9) String pool e intern
    static void internEPool() {

        System.out.println("\nString pool e intern");

        String a = new String("api");
        String b = a.intern();
        String c = "api";
        System.out.println("intern aproxima a referencia do literal                      " + (b == c));
        System.out.println("equals permanece verdadeiro entre todas                       " + a.equals(c));

    }

    // 10) Armadilhas e boas praticas
    static void armadilhasEBoasPraticas() {

        System.out.println("\nArmadilhas e boas praticas");

        // Espaços e caracteres invisiveis
        String x = "ok";
        String y = "ok ";
        System.out.println("espaco ao fim altera igualdade                               " + x.equals(y));
        System.out.println("comparacao apos strip                                        " + x.strip().equals(y.strip()));

        // Diferenca de normalizacao unicode
        String comp = "Cafe\u0301";   // E + acento combinado
        String pre = "Café";          // caractere precomposto
        System.out.println("formas diferentes podem parecer iguais ao olho               " +
                comp.equals(pre));
        String nn1 = Normalizer.normalize(comp, Normalizer.Form.NFC);
        String nn2 = Normalizer.normalize(pre, Normalizer.Form.NFC);
        System.out.println("apos normalizar para NFC                                     " + nn1.equals(nn2));

        // Nao use dupla igual para comparar conteudo
        String a = new String("teste");
        String b = new String("teste");
        System.out.println("dupla igual verifica identidade                              " + (a == b));
        System.out.println("equals verifica conteudo                                     " + a.equals(b));

        // Quando precisar ignorar acentos e caixa com regras linguisticas  prefira Collator
        Collator col = Collator.getInstance(new Locale("pt", "BR"));
        col.setStrength(Collator.PRIMARY);
        System.out.println("comparacao idioma ciente ignorando acentos e caixa           " + (col.compare("maça", "maca") == 0));

    }

}
