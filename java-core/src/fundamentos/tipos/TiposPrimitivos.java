package fundamentos.tipos;

/**
 * PrimitivosResumo.java
 *
 * Resumo dos tipos primitivos em Java:
 * 1) Integrais com sinal: byte(8b), short(16b), int(32b), long(64b)
 * 2) Ponto flutuante: float(32b, IEEE 754), double(64b, IEEE 754)
 * 3) Caractere: char(16b, UTF-16 code unit)
 * 4) Booleano: boolean(1 bit lógico; implementação depende da JVM)
 *
 * Observações importantes:
 * - Tamanhos são fixos por especificação (não variam por plataforma).
 * - Valores default só existem para *campos* de instância/estáticos:
 *      byte/short/int/long -> 0
 *      float/double       -> 0.0
 *      char               -> '\u0000' (NUL)
 *      boolean            -> false
 *      (para variáveis locais não há default; é obrigatório inicializar)
 * - Literais:
 *      Inteiros: decimal, binário (0b...), octal (0...), hexa (0x...)
 *      Separadores: underscores em literais numéricos (ex.: 1_000_000)
 *      Sufixos: long -> L/l, float -> F/f, double -> D/d (D é opcional)
 * - Casting:
 *      Widening (ampliação) é seguro: byte->short->int->long->float->double
 *      Narrowing (redução) exige cast e pode causar perda/overflow.
 * - Overflow inteiro em Java *não* lança exceção (wrap-around: módulo 2^n).
 * - Ponto flutuante segue IEEE 754: +Infinity, -Infinity e NaN existem.
 * - char guarda um code unit UTF-16 (cuidado com emojis/suplementares).
 * - Para dinheiro/precisão exata: BigDecimal (não-primitivo).
 */

public class TiposPrimitivos {

    public static void main(String[] args) {

        // Chamada de metodos no main
        integrais();
        pontoFlutuante();
        caractere();
        booleano();
        promocaoNumericaECasting();
        overflowEEspeciais();
        divisaoInteiraVsRealEMod();
        extrasLiteraisNumericos();

    }

    // 1 - Integrais: byte, short, int, long
    static void integrais() {

        System.out.println("INTEGRAIS");

        byte b = 127; // min -128, max 127 (8 bints, 2^7-1)
        short s = 32_767; // min -32768, max 32767
        int i = 2_147_483_647; //
        long l = 9_223_372_036_854_775_807L; // min - 2^63, max 2^63-1 (sufixo L)

        System.out.printf("byte b=%d, short s=%d, int i=%d, long l=%d%n", b, s, i, l);

    }

    // 2 - Ponto flutuante: float, double
    static void pontoFlutuante() {

        System.out.println("\n PONTO FLUTUANTE");

        float f = 3.1415927f; // 7 dígitos ~ de precisão; sufixo F é obrigatório
        double d = 3.141592653589793; // 15-16 dígitos ~ de precisão

        double notacaoCientifica = 1.23e3; // 1.23 * 10^3 -> 1230.0
        double notacaoNegativa   = 4.56e-2; // 4.56 * 10^-2 -> 0.0456

        System.out.printf("float f=%.7f, double d=%.15f%n", f, d);
        System.out.printf("científica: 1.23e3=%.1f, 4.56e-2=%.4f%n", notacaoCientifica, notacaoNegativa);

    }

    // 3 - Caractere: char (UTF-16 code unit)
    static void caractere() {

        System.out.println("\n CARACTERE (CHAR)");

        char letraA = 'A'; // literal simples
        char novaLinha = '\n'; // escape
        char unicode = '\u00E7'; // 'ç' via escape Unicode
        char digitoAscii = 65; // 'A' pelo código (0..65535)

        System.out.print("A com unicode e escapes: ");
        System.out.print(letraA);
        System.out.print(' '); // espaço
        System.out.print(unicode);
        System.out.print(' ');
        System.out.print(digitoAscii);
        System.out.print(novaLinha);

        // Observação: emojis geralmente precisam de pares surrogates (2 char)
        String emoji = "Amo Java \uD83D\uDE80"; // 🚀 = U+1F680 (surrogate pair)
        System.out.println(emoji);

    }

    // 4 - Booleano: boolean (true/false)
    static void booleano() {

        System.out.println("\nBOOLEANO");

        boolean ligado = true;
        boolean permitido = false;

        System.out.println("ligado=" + ligado + ", permitido=" + permitido);

        // Operadores: ! (NOT), && (AND), || (OR)
        boolean ex = !ligado || (ligado && !permitido);
        System.out.println("!ligado || (ligado && !permitido) = " + ex);

        // Curto-circuito: segunda parte pode nem ser avaliada
        boolean curto = false && muitoCaroDeCalcular();
        System.out.println("false && muitoCaroDeCalcular() -> " + curto);

    }

    static boolean muitoCaroDeCalcular() {

        // nunca será chamado se o primeiro operando de && for false
        System.out.println("(chamou muitoCaroDeCalcular)");

        return true;

    }

    // Promoção numérica e casting (widening vs narrowing)
    static void promocaoNumericaECasting() {

        System.out.println("\nPROMOÇÃO NUMÉRICA E CASTING");

        byte b = 10;
        short s = 300;
        int i = b + s; // promoção automática para int
        long l = i; // widening
        float f = l; // widening
        double d = f; // widening

        System.out.printf("i=%d, l=%d, f=%.1f, d=%.1f%n", i, l, f, d);

        // Narrowing: requer cast explícito e pode perder informação
        int grande = 130;
        byte pequeno = (byte) grande; // overflow: 130 -> -126

        System.out.printf("int grande=%d -> (byte) = %d%n", grande, pequeno);

        // Atenção: operações com byte/short promovem a int antes de somar
        byte x = 60, y = 70;
        // byte z = x + y; // ERRO de compilação
        byte z = (byte) (x + y); // cast necessário

        System.out.println("byte z = (byte)(60+70) = " + z);

    }

    // Overflow inteiro e especiais de ponto flutuante
    static void overflowEEspeciais() {

        System.out.println("\nOVERFLOW E ESPECIAIS");

        int max = Integer.MAX_VALUE; // 2_147_483_647
        int wrap = max + 1; // overflow -> -2_147_483_648

        System.out.printf("MAX_INT=%d, MAX_INT+1=%d (overflow)%n", max, wrap);

        double posInf = 1.0 / 0.0; // Infinity
        double negInf = -1.0 / 0.0; // -Infinity
        double nan    = 0.0 / 0.0; // NaN

        System.out.println("Infinity=" + posInf + ", -Infinity=" + negInf + ", NaN=" + nan);
        System.out.println("Double.isNaN(nan)? " + Double.isNaN(nan));
        System.out.println("Double.isFinite(1.23)? " + Double.isFinite(1.23));

    }

    // Divisão inteira vs real e operador módulo
    static void divisaoInteiraVsRealEMod() {

        System.out.println("\nDIVISÃO INTEIRA VS REAL E MÓDULO");

        System.out.println("7 / 2   = " + (7 / 2)); // 3 (int)
        System.out.println("7 / 2.0 = " + (7 / 2.0)); // 3.5 (double)
        System.out.println("7 % 2   = " + (7 % 2)); // 1 (resto)
        System.out.println("-7 % 2  = " + (-7 % 2)); // -1 (sinal segue dividendo)

    }

    // Literais numéricos úteis (underscores, bases, sufixos)
    static void extrasLiteraisNumericos() {

        System.out.println("\nLITERAIS NUMÉRICOS");

        int umMilhao = 1_000_000;
        long millis = 3_600_000L; // 1h em millis
        int binario = 0b1111_0000;
        int hexa = 0xFF_EC_DE_5E;

        float imposto = 12.5F;
        double taxa   = 0.035D; // o D é opcional (apenas ilustra)

        System.out.printf("1_000_000=%d, millis=%d, bin=0b11110000=%d, hex=0xFFECDE5E=%d, imposto=%.2f, taxa=%.3f%n",
                umMilhao, millis, binario, hexa, imposto, taxa);

    }

}
