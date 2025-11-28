package fundamentos.objetovsprimitivo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

/**
 * ConversaoNumeroStringResumo.java
 *
 * Conteúdo
 * 1) Número para String com APIs básicas e formatadas
 * 2) String para número com parse e valueOf
 * 3) Bases numéricas e decode para 0x e 0
 * 4) Locale com NumberFormat e DecimalFormat
 * 5) Dinheiro com BigDecimal e impressão segura
 * 6) Porcentagens e notação científica
 * 7) Helpers tryParse com Optional
 * 8) Armadilhas comuns e boas práticas
 */
public class ConversaoNumeroStringResumo {

    public static void main(String[] args) {

        numeroParaStringBasico();
        stringParaNumeroBasico();
        basesNumericasEDecode();
        localeComNumberFormat();
        dinheiroComBigDecimal();
        porcentagensENotacaoCientifica();
        helpersTryParse();
        armadilhasEBoasPraticas();

    }

    // 1) Número -> String  básico e formatado
    static void numeroParaStringBasico() {

        System.out.println("Numero para String  formas basicas");
        int inteiro = 42;
        double pi = 3.1415926535;

        // conversões diretas
        String a = Integer.toString(inteiro);
        String b = String.valueOf(inteiro);
        String c = Double.toString(pi);

        System.out.println("toString de inteiro  " + a);
        System.out.println("String valueOf de inteiro  " + b);
        System.out.println("toString de double  " + c);

        // formatação com casas e separadores via String.format
        String f1 = String.format(Locale.US, "pi com 3 casas  %.3f", pi);
        String f2 = "pi com 2 casas  %.2f".formatted(pi);
        String f3 = String.format("inteiro com zeros a esquerda  %04d", inteiro);

        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);

    }

    // 2) String -> Número  parse e valueOf
    static void stringParaNumeroBasico() {

        System.out.println("\nString para numero  parse e valueOf");
        String sx = "123";
        String sd = "3.75";

        int x = Integer.parseInt(sx);
        Integer wx = Integer.valueOf(sx);
        double d = Double.parseDouble(sd);
        Double wd = Double.valueOf(sd);

        System.out.println("parseInt  " + x);
        System.out.println("valueOf Integer  " + wx);
        System.out.println("parseDouble  " + d);
        System.out.println("valueOf Double  " + wd);

        // tratamento de erro
        try {
            Integer.parseInt("12a3");
        } catch (NumberFormatException e) {
            System.out.println("falha ao fazer parse de inteiro  entrada invalida");
        }

    }

    // 3) Bases numéricas e decode para 0x e 0
    static void basesNumericasEDecode() {

        System.out.println("\nbases numericas e decode");
        String hex = "FF";
        String bin = "1010";
        String oct = "52";

        int vHex = Integer.parseInt(hex, 16);
        int vBin = Integer.parseInt(bin, 2);
        int vOct = Integer.parseInt(oct, 8);

        System.out.println("hex FF para inteiro  " + vHex);
        System.out.println("bin 1010 para inteiro  " + vBin);
        System.out.println("oct 52 para inteiro  " + vOct);

        // decode entende prefixos 0x  0  e #
        int dec1 = Integer.decode("0x2A");
        int dec2 = Integer.decode("052");
        int dec3 = Integer.decode("#2A");
        System.out.println("decode 0x2A  " + dec1);
        System.out.println("decode 052   " + dec2);
        System.out.println("decode #2A   " + dec3);

        // também o inverso  inteiro para string com base
        String outHex = Integer.toString(255, 16);
        String outBin = Integer.toString(10, 2);
        System.out.println("255 para string base 16  " + outHex);
        System.out.println("10 para string base 2   " + outBin);

    }

    // 4) Locale com NumberFormat e DecimalFormat
    static void localeComNumberFormat() {

        System.out.println("\nlocale e formatacao numerica");

        double valor = 1234.56;

        NumberFormat us = NumberFormat.getNumberInstance(Locale.US);
        NumberFormat br = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

        String usTxt = us.format(valor);
        String brTxt = br.format(valor);

        System.out.println("format US  " + usTxt);  // 1,234.56
        System.out.println("format BR  " + brTxt);  // 1.234,56

        // parse respeitando locale
        try {
            Number n1 = br.parse("1.234,56");
            Number n2 = us.parse("1,234.56");
            System.out.println("parse BR  " + n1.doubleValue());
            System.out.println("parse US  " + n2.doubleValue());
        } catch (ParseException e) {
            System.out.println("falha ao fazer parse com locale");
        }

        // DecimalFormat com padrao customizado
        DecimalFormatSymbols brSymbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        brSymbols.setDecimalSeparator(',');
        brSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", brSymbols);
        String custom = df.format(98765.4321);
        System.out.println("decimal format customizado  " + custom);

    }

    // 5) Dinheiro com BigDecimal  construção a partir de String e impressão segura
    static void dinheiroComBigDecimal() {

        System.out.println("\ndinheiro com BigDecimal");

        // construa a partir de texto para evitar erro binario de ponto flutuante
        BigDecimal a = new BigDecimal("12.30");
        BigDecimal b = new BigDecimal("0.70");
        BigDecimal total = a.add(b).setScale(2, RoundingMode.HALF_UP);

        // impressão amigavel
        System.out.println("soma precisa  " + total.toPlainString());

        // moeda com NumberFormat
        NumberFormat brMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.println("moeda BR  " + brMoeda.format(total));

        NumberFormat usMoeda = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("moeda US  " + usMoeda.format(total));

    }

    // 6) Porcentagens e notacao cientifica
    static void porcentagensENotacaoCientifica() {

        System.out.println("\nporcentagem e notacao cientifica");

        double taxa = 0.1575;

        NumberFormat pctBR = NumberFormat.getPercentInstance(new Locale("pt", "BR"));
        pctBR.setMinimumFractionDigits(2);
        String t1 = pctBR.format(taxa); // 15,75%

        NumberFormat pctUS = NumberFormat.getPercentInstance(Locale.US);
        pctUS.setMinimumFractionDigits(1);
        String t2 = pctUS.format(taxa); // 15.8%

        System.out.println("porcentagem BR  " + t1);
        System.out.println("porcentagem US  " + t2);

        // notacao cientifica com String.format
        String sci = String.format(Locale.US, "cientifico  %.3e", 12345.0);
        System.out.println(sci);

    }

    // 7) Helpers  tryParse  evitando excecoes para fluxo esperado
    static void helpersTryParse() {

        System.out.println("\nhelpers tryParse");
        System.out.println("tryParseInt 123  " + tryParseInt("123").orElse(-1));
        System.out.println("tryParseInt 12a  " + tryParseInt("12a").orElse(-1));
        System.out.println("tryParseDouble 3.14  " + tryParseDouble("3.14").orElse(-1.0));
        System.out.println("tryParseLocale BR 1.234,56  " + tryParseLocale("1.234,56", new Locale("pt", "BR")).orElse(-1.0));

    }

    // 8) Armadilhas e boas práticas
    static void armadilhasEBoasPraticas() {

        System.out.println("\narmadilhas e boas praticas");
        System.out.println("evite usar parseDouble para dinheiro  prefira BigDecimal a partir de texto");
        System.out.println("atencao com locale  1.234,56 em pt BR e 1,234.56 em US");
        System.out.println("espacos e simbolos extras exigem limpeza de entrada");
        System.out.println("parse de porcentagem depende do formatador  use NumberFormat de percent");
        System.out.println("Double toString lida com NaN e infinitos  verifique antes de formatar se necessario");
        // exemplo de NaN e infinitos
        double nan = Double.NaN;
        double inf = Double.POSITIVE_INFINITY;
        System.out.println("toString de NaN  " + Double.toString(nan));
        System.out.println("toString de infinito  " + Double.toString(inf));

    }

    // helpers
    static OptionalInt tryParseInt(String s) {

        try {
            return OptionalInt.of(Integer.parseInt(s.trim()));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }

    }

    static OptionalDouble tryParseDouble(String s) {

        try {
            return OptionalDouble.of(Double.parseDouble(s.trim().replace(',', '.')));
        } catch (NumberFormatException e) {
            return OptionalDouble.empty();
        }

    }

    static Optional<Double> tryParseLocale(String s, Locale locale) {

        try {
            NumberFormat nf = NumberFormat.getNumberInstance(locale);
            return Optional.of(nf.parse(s.trim()).doubleValue());
        } catch (ParseException e) {
            return Optional.empty();
        }

    }

}

