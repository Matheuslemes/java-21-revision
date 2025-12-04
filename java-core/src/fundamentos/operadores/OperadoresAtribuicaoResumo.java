package fundamentos.operadores;

/**
 * OperadoresAtribuicaoResumo.java
 *
 * Conteudo abordado
 * 1) Atribuicao simples
 * 2) Atribuicoes compostas aritmeticas
 * 3) Atribuicoes compostas bit a bit
 * 4) Promocao numerica e cast implicito nas compostas
 * 5) Encadeamento e ordem de avaliacao
 * 6) Lvalue  o que pode receber valor
 * 7) Interacao com final
 * 8) Referencias de objeto e arrays
 * 9) Overflow  ponto flutuante e boas praticas
 */
public class OperadoresAtribuicaoResumo {

    public static void main(String[] args) {

        atribuicaoSimples();
        compostasAritmeticas();
        compostasBitABit();
        promocaoNumericaECastImplicito();
        encadeamentoEAvaliacao();
        lvalueRegras();
        usoComFinal();
        referenciasEArrays();
        overflowPontoFlutuanteBoasPraticas();

    }

    // 1) Atribuicao simples
    static void atribuicaoSimples() {

        System.out.println("Atribuicao simples");

        int a = 10;
        double b = 2.5;
        a = 20; // substitui o valor anterior
        b = b + 0.5; // reatribuicao usando o proprio valor

        System.out.println("inteiro apos reatribuicao  " + a);
        System.out.println("decimal apos ajuste        " + b);

        // Tipagem forte  o tipo do lado direito precisa ser compatível
        long L = 0;
        L = a; // ampliacao permitida

        System.out.println("valor em longo apos copia  " + L);

    }

    // 2) Atribuicoes compostas aritmeticas  +=  -=  *=  /=  %=
    static void compostasAritmeticas() {

        System.out.println("\nAtribuicoes compostas aritmeticas");

        int n = 10;
        n += 5;    // soma direta no destino
        n -= 3;    // subtrai
        n *= 2;    // multiplica
        n /= 4;    // divide com truncamento inteiro
        n %= 3;    // resto

        System.out.println("valor apos sequencia aritmetica composta  " + n);

        double d = 5.0;
        d *= 1.5;
        d /= 2;
        System.out.println("decimal apos multiplicar e dividir         " + d);

    }

    // 3) Atribuicoes compostas bit a bit  &=  |=  ^=  <<=  >>=  >>>=
    static void compostasBitABit() {

        System.out.println("\nAtribuicoes compostas bit a bit");

        int mask = 0b1010_1100;
        int x = 0b1111_0000;

        x &= mask;     // mantem apenas bits em comum
        System.out.println("aplicacao de E bit a bit  " + toBin(x));

        x |= 0b0000_0011; // liga bits das unidades
        System.out.println("aplicacao de OU bit a bit " + toBin(x));

        x ^= 0b0000_0001; // alterna o bit menos significativo
        System.out.println("aplicacao de OU exclusivo " + toBin(x));

        int y = 0b0001_0000;
        y <<= 2;       // desloca para esquerda
        System.out.println("deslocamento a esquerda   " + toBin(y));

        y >>= 1;       // desloca preservando sinal
        System.out.println("deslocamento a direita    " + toBin(y));

        int neg = -8;
        neg >>>= 1;    // desloca com preenchimento por zero
        System.out.println("deslocamento logico dir   " + toBin(neg));

    }

    // 4) Promocao numerica e cast implicito nas compostas
    static void promocaoNumericaECastImplicito() {

        System.out.println("\nPromocao numerica e cast implicito");

        byte b = 120;
        // Na soma comum  b + 10 promove para inteiro  seria preciso cast
        // Na composta  o cast para o tipo do destino ocorre automaticamente
        b += 10; // equivalente a b tipo byte de b mais dez
        System.out.println("byte apos soma composta   " + b);

        char c = 'A';
        c += 3; // converte o resultado para caracter
        System.out.println("char apos incremento comp " + c);

    }

    // 5) Encadeamento e ordem de avaliacao
    static void encadeamentoEAvaliacao() {

        System.out.println("\nEncadeamento e ordem de avaliacao");

        int a, b, c;
        a = b = c = 7;   // avalia da direita para a esquerda
        System.out.println("valores apos encadeamento " + a + "  " + b + "  " + c);

        int i = 0;
        int r = (i += 2) + (i *= 3); // primeiro adiciona  depois multiplica
        System.out.println("resultado com efeitos      " + r + "  contador final  " + i);

    }

    // 6) Lvalue  somente algo atribuivel pode ficar no lado esquerdo
    static void lvalueRegras() {

        System.out.println("\nLado atribuivel  regras");

        int[] arr = {1, 2, 3};
        arr[1] = 20; // elemento de array pode receber valor
        System.out.println("array apos reatribuicao    " + arr[0] + " " + arr[1] + " " + arr[2]);

        var P = new Ponto(2, 3);
        P.x = 10; // campo pode receber valor
        System.out.println("ponto apos ajuste          " + P.x + "," + P.y);

        // Chamadas e literais nao podem ficar no lado atribuivel
        // 10 = 5;            // proibido
        // (a + b) = 7;       // proibido

    }

    // 7) Interacao com final
    static void usoComFinal() {

        System.out.println("\nInteracao com final");

        final int constante;
        constante = 42;     // inicializacao unica
        System.out.println("constante definida uma vez " + constante);

        final Ponto p = new Ponto(1, 1);
        // a referencia nao pode mudar  mas o estado interno pode
        p.x = 9;
        System.out.println("objeto final com estado    " + p.x + "," + p.y);
        // p = new Ponto(0,0); // proibido  a referencia ficaria diferente

    }

    // 8) Referencias de objeto e arrays
    static void referenciasEArrays() {

        System.out.println("\nReferencias de objeto e arrays");

        Ponto a = new Ponto(0, 0);
        Ponto b = a; // copia a referencia  ambos apontam para o mesmo objeto
        b.y = 5; // altera o mesmo alvo
        System.out.println("dois apontando mesmo alvo  " + a.x + "," + a.y);

        int[] v1 = {1, 2, 3};
        int[] v2 = v1;
        v2[0] = 99;

        System.out.println("arrays compartilhando alvo " + v1[0] + " " + v2[0]);

    }

    // 9) Overflow  ponto flutuante e boas praticas
    static void overflowPontoFlutuanteBoasPraticas() {

        System.out.println("\nOverflow  ponto flutuante e boas praticas");

        int m = Integer.MAX_VALUE;
        m += 1; // wrap modular
        System.out.println("inteiro apos transbordo   " + m);

        // Versoes exatas da biblioteca matematica ajudam a detectar transbordo
        try {
            int boom = Math.addExact(Integer.MAX_VALUE, 1);
            System.out.println("soma exata sem falha      " + boom);
        } catch (ArithmeticException e) {
            System.out.println("soma exata sinalizou erro");
        }

        double d = 1.0;
        d /= 0.0; // infinito positivo
        System.out.println("divisao por zero em real  " + d);

        System.out.println("boas praticas");
        System.out.println("use compostas quando ganharem clareza");
        System.out.println("evite efeitos colaterais confusos na mesma expressao");
        System.out.println("para detectar transbordo convem usar metodos exatos");
        System.out.println("em hot paths prefira tipos primitivos apropriados");

    }

    // Auxiliares

    static String toBin(int v) {

        String s = Integer.toBinaryString(v);

        // padroniza para 8 ou 32 conforme tamanho
        if (s.length() <= 8) return String.format("%8s", s).replace(' ', '0');
        return String.format("%32s", s).replace(' ', '0');

    }

    static class Ponto {

        int x, y;
        Ponto(int x, int y) { this.x = x; this.y = y; }

    }

}

