package fundamentos.objetovsprimitivo;

/**
 * ConversaoPrimitivosResumo.java
 *
 * Tópicos cobertos:
 * - Widening implícito: byte→short→int→long→float→double e char→int/long/float/double
 * - Narrowing com cast: double→int, long→int, int→byte e riscos de perda/overflow
 * - Promoção numérica em expressões: byte/short/char promovem para int
 * - Compound assignment (+=, -=, ...) realiza cast implícito para o tipo do lado esquerdo
 * - char ↔ int com foco em Unicode
 * - double/float para int/long: truncar x arredondar (round, floor, ceil)
 * - Overflow ao reduzir e Math.toIntExact
 * - Literais e bases: decimal, binário, octal, hexa
 * - Conversões sem sinal: Byte.toUnsignedInt, Short.toUnsignedInt, Integer.toUnsignedLong
 * - String para número: parse/valueOf (não é cast)
 * - Boas práticas
 */
import java.math.BigDecimal;

public class ConversaoPrimitivosResumo {

    public static void main(String[] args) {
        wideningImplicito();
        narrowingComCast();
        promocaoNumericaEmExpressoes();
        compoundAssignmentCastsImplicitos();
        charUnicodeEAscii();
        doubleFloatParaIntArredondamentos();
        overflowAoReduzir();
        literaisEBases();
        unsignedHelpers();
        parseVsCast();
        boasPraticas();
    }

    // 1) Widening implícito
    static void wideningImplicito() {

        System.out.println("Widening implicito");

        byte b = 100;
        short s = b;
        int i = s;
        long l = i;
        float f = l; // pode perder precisão fracionária, mas a conversão é permitida sem cast
        double d = f;
        char c = 'A';
        int ci = c;

        System.out.printf("b: %d  s: %d  i: %d  l: %d  f: %.1f  d: %.1f  c: %c  code: %d%n",
                b, s, i, l, f, d, c, ci);

    }

    // 2) Narrowing com cast
    static void narrowingComCast() {

        System.out.println("\nNarrowing com cast");

        int i = 130;
        byte nb = (byte) i; // wrap por estouro
        long l = 3_000_000_000L;
        int ni = (int) l; // fora do intervalo de int
        double pi = 3.99999;
        int truncado = (int) pi; // truncamento

        System.out.printf("int para byte: %d  long para int: %d  double para int: %d%n", nb, ni, truncado);

    }

    // 3) Promoção numérica nas expressões
    static void promocaoNumericaEmExpressoes() {

        System.out.println("\nPromocao numerica em expressoes");

        byte x = 60, y = 70;
        // x + y promovido para int
        byte z = (byte) (x + y); // cast para voltar a byte
        char c1 = 'A', c2 = 2;
        int somaChar = c1 + c2; // resultado inteiro

        System.out.printf("resultado byte: %d  char soma como inteiro: %d%n", z, somaChar);

    }

    // 4) Compound assignment com cast implícito para o tipo do LHS
    static void compoundAssignmentCastsImplicitos() {

        System.out.println("\nCompound assignment com cast implicito");

        byte b = 120;
        b += 20; // equivalente a b = (byte) (b + 20)

        System.out.println("byte apos operacao composta: " + b);

        char c = 'A';
        c += 2; // c = (char) (c + 2)

        System.out.println("char apos incremento composto: " + c);

    }

    // 5) char ↔ int (Unicode/ASCII)
    static void charUnicodeEAscii() {

        System.out.println("\nchar e int com Unicode");

        char ch = 'ç';
        int code = ch; // code unit UTF-16
        char fromCode = (char) 65;  // 65 corresponde a 'A'

        System.out.printf("char para inteiro: %d  inteiro para char: %c%n", code, fromCode);

    }

    // 6) double/float → int/long  truncar x arredondar
    static void doubleFloatParaIntArredondamentos() {

        System.out.println("\ndouble e float para inteiros  truncamento e arredondamento");

        double v1 = 2.7, v2 = -2.7;
        int t1 = (int) v1; // truncar para zero
        int t2 = (int) v2;
        long r1 = Math.round(v1); // arredondar
        long r2 = Math.round(v2);

        System.out.printf("truncar positivo: %d  truncar negativo: %d  round pos: %d  round neg: %d%n", t1, t2, r1, r2);

        System.out.printf("floor de 2.7: %.0f  ceil de 2.1: %.0f%n", Math.floor(2.7), Math.ceil(2.1));

    }

    // 7) Overflow ao reduzir e proteção com Math.toIntExact
    static void overflowAoReduzir() {

        System.out.println("\nOverflow ao reduzir");

        long grande = Long.MAX_VALUE;
        int cortado = (int) grande;  // perda de bits altos

        System.out.println("conversao direta para int com perda: " + cortado);

        try {
            int seguro = Math.toIntExact(grande);
            System.out.println("toIntExact sem overflow: " + seguro);
        } catch (ArithmeticException e) {
            System.out.println("toIntExact detectou overflow");
        }

    }

    // 8) Literais e bases
    static void literaisEBases() {

        System.out.println("\nLiterais e bases");

        int dec = 42;
        int bin = 0b101010;
        int oct = 052;
        int hex = 0x2A;

        System.out.printf("decimal: %d  binario: %d  octal: %d  hexa: %d%n", dec, bin, oct, hex);

    }

    // 9) Conversões sem sinal
    static void unsignedHelpers() {

        System.out.println("\nConversoes sem sinal");

        byte b = (byte) 250; // interpretado como -6 no modelo com sinal
        int bUnsigned = Byte.toUnsignedInt(b);   // 250
        short s = (short) 65000; // interpretado como valor negativo no modelo com sinal
        int sUnsigned = Short.toUnsignedInt(s);  // 65000
        long uInt = Integer.toUnsignedLong(0xFFFF_FFFE); // 4294967294

        System.out.printf("byte como sem sinal: %d  short como sem sinal: %d  inteiro sem sinal em long: %d%n",
                bUnsigned, sUnsigned, uInt);

    }

    // 10) String para número: parse/valueOf
    static void parseVsCast() {

        System.out.println("\nString para numero com parse e valueOf");

        String txt = "123";
        int n = Integer.parseInt(txt);
        Integer w = Integer.valueOf(txt);
        double d = Double.parseDouble("3.14");

        System.out.printf("texto para int: %d  texto para wrapper: %s  texto para double: %.2f%n", n, w, d);
        // lembrando: cast não converte String para número

    }

    // 11) Boas práticas
    static void boasPraticas() {

        System.out.println("\nBoas praticas");
        System.out.println("prefira widening quando possivel e documente narrowing");
        System.out.println("ao reduzir avalie perda e risco de estouro  para long para int use toIntExact quando fizer sentido");
        System.out.println("para arredondar use round floor ou ceil  cast apenas trunca");
        System.out.println("byte short e char promovem para int em expressoes  lembre do cast ao voltar");
        System.out.println("boolean nao se converte para numericos e vice versa");
        BigDecimal total = new BigDecimal("12.30").add(new BigDecimal("0.70"));
        System.out.println("para dinheiro prefira BigDecimal  exemplo de soma  " + total);

    }

}