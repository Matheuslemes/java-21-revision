package fundamentos.operadores;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * OperadoresRelacionaisResumo.java
 *
 * Conteudo
 * 1) Primitivos  maior  menor  maior ou igual  menor ou igual  igualdade e diferenca
 * 2) Ponto flutuante  comparacao  NaN e infinitos
 * 3) char e ordem Unicode
 * 4) Objetos  identidade com dupla igual e igualdade com equals
 * 5) String  equals e compareTo para ordem lexicografica
 * 6) BigDecimal  compareTo para valores monetarios
 * 7) Arrays e null safety  Arrays.equals e Objects.equals
 * 8) compareTo e convenios de ordenacao
 * 9) Armadilhas e boas praticas
 */
public class OperadoresRelacionaisResumo {

    public static void main(String[] args) {
        primitivosBasico();
        doubleENaN();
        charUnicode();
        objetosIdentidadeVsIgualdade();
        stringsComparacao();
        bigDecimalComparacao();
        arraysENullSafety();
        compareToConvenios();
        armadilhasEBoasPraticas();
    }

    // 1) Primitivos  >  <  >=  <=  ==  !=
    static void primitivosBasico() {

        System.out.println("Primitivos  basico");

        int a = 7, b = 3;
        System.out.println("sete maior que tres  " + (a > b));
        System.out.println("sete menor que tres  " + (a < b));
        System.out.println("sete maior ou igual a sete  " + (a >= 7));
        System.out.println("tres menor ou igual a tres  " + (b <= 3));
        System.out.println("sete igual a tres  " + (a == b));
        System.out.println("sete diferente de tres  " + (a != b));

    }

    // 2) Ponto flutuante  NaN quebra todas as comparacoes e igualdade
    static void doubleENaN() {
        System.out.println("\nPonto flutuante  NaN e infinitos");
        double x = 0.1 + 0.2; // representacao binaria pode gerar arredondamento
        double y = 0.3;
        System.out.println("soma de zero ponto um com zero ponto dois eh igual a zero ponto tres  " + (x == y));

        double nan = Double.NaN;
        System.out.println("NaN maior que zero  " + (nan > 0));
        System.out.println("NaN menor que zero  " + (nan < 0));
        System.out.println("NaN igual a si mesmo  " + (nan == nan));
        System.out.println("teste com utilitario isNaN  " + Double.isNaN(nan));

        double inf = Double.POSITIVE_INFINITY;
        System.out.println("infinito maior que qualquer finito  " + (inf > 1E308));
    }

    // 3) char  comparacao por valor Unicode
    static void charUnicode() {

        System.out.println("\nchar e ordem Unicode");

        char A = 'A';
        char a = 'a';
        System.out.println("letra maiuscula vem antes de minuscula  " + (A < a));
        System.out.println("codigo de A menor que codigo de a  " + ((int) A < (int) a));

    }

    // 4) Objetos  identidade com dupla igual  igualdade com equals
    static void objetosIdentidadeVsIgualdade() {

        System.out.println("\nObjetos  identidade e igualdade");

        Integer i1 = Integer.valueOf(128);
        Integer i2 = Integer.valueOf(128);
        System.out.println("duas caixas com cento e vinte e oito  identidade igual  " + (i1 == i2));
        System.out.println("duas caixas com cento e vinte e oito  igualdade de valor  " + i1.equals(i2));

        // Atenção para cache [-128..127] de Integer  aqui identidade pode coincidir
        Integer c1 = 127, c2 = 127;
        System.out.println("cache de inteiros pequenos pode dar identidade verdadeira  " + (c1 == c2));

    }

    // 5) String  equals para conteudo  compareTo para ordem
    static void stringsComparacao() {

        System.out.println("\nString  comparacao");

        String s1 = "carro";
        String s2 = "casa";
        String s3 = "carro";
        System.out.println("conteudo de carro e casa igual  " + s1.equals(s2));
        System.out.println("conteudo de carro e carro igual  " + s1.equals(s3));
        System.out.println("ordem lexicografica  carro antes de casa  " + (s1.compareTo(s2) < 0));
        System.out.println("ordem lexicografica  casa depois de carro  " + (s2.compareTo(s1) > 0));
        System.out.println("ordem lexicografica  carro comparado com carro retorna neutro  " + (s1.compareTo(s3) == 0));

    }

    // 6) BigDecimal  compareTo ignora escala quando valores sao equivalentes
    static void bigDecimalComparacao() {

        System.out.println("\nBigDecimal  comparacao por valor");

        BigDecimal v1 = new BigDecimal("0.30");
        BigDecimal v2 = new BigDecimal("0.3");
        System.out.println("equals considera escala  " + v1.equals(v2));
        System.out.println("compareTo considera apenas valor numerico  " + (v1.compareTo(v2) == 0));

    }

    // 7) Arrays e null safety
    static void arraysENullSafety() {

        System.out.println("\nArrays e null safety");

        int[] p = {1, 2, 3};
        int[] q = {1, 2, 3};
        System.out.println("duas referencias diferentes  dupla igual retorna falso  " + (p == q));
        System.out.println("comparacao de conteudo com Arrays equals  " + Arrays.equals(p, q));

        String s = null;
        System.out.println("comparacao null safe com Objects equals  " + Objects.equals(s, "x"));

    }

    // 8) compareTo  convenios de retorno  negativo  zero  positivo
    static void compareToConvenios() {

        System.out.println("\ncompareTo  convenios de ordenacao");

        Integer a = 5, b = 9, c = 5;
        System.out.println("cinco contra nove retorna numero negativo  " + (a.compareTo(b) < 0));
        System.out.println("nove contra cinco retorna numero positivo  " + (b.compareTo(a) > 0));
        System.out.println("cinco contra cinco retorna neutro  " + (a.compareTo(c) == 0));

    }

    // 9) Armadilhas e boas praticas
    static void armadilhasEBoasPraticas() {

        System.out.println("\nArmadilhas e boas praticas");

        // Ponto flutuante  evite comparar diretamente valores calculados
        double r = Math.sqrt(2) * Math.sqrt(2);
        System.out.println("raiz de dois ao quadrado compara com dois usando tolerancia  " + quaseIgual(r, 2.0, 1e-12));

        // Strings  nao use dupla igual para conteudo
        String a = new String("ok");
        String b = new String("ok");
        System.out.println("dupla igual em strings diferentes  " + (a == b));
        System.out.println("equals para string com mesmo conteudo  " + a.equals(b));

        // Ordenacao e consistencia  compareTo deve ser consistente com equals sempre que possivel

    }

    static boolean quaseIgual(double v, double alvo, double eps) {

        return Math.abs(v - alvo) < eps;

    }
}

