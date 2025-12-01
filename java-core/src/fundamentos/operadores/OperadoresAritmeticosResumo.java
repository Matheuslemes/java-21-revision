package fundamentos.operadores;

/**
 * OperadoresAritmeticosResumo.java
 * Conteúdo abordado
 * 1) Sinais unários e incremento
 * 2) Operadores aritméticos binários e precedência
 * 3) Divisão inteira x divisão real e resto
 * 4) Promoção numérica e casts
 * 5) Atribuições compostas
 * 6) Overflow e utilitários exatos
 * 7) Ponto flutuante  infinito  NaN  arredondamentos
 * 8) Funções úteis de Math
 * 9) Ordem de avaliação e efeitos colaterais
 */
public class OperadoresAritmeticosResumo {

    public static void main(String[] args) {

        unariosEIncremento();
        binariosEPrecedencia();
        divisaoEResto();
        promocaoENarrowing();
        atribuicoesCompostas();
        overflowEAddExact();
        pontoFlutuanteENaN();
        funcoesDeMath();
        ordemDeAvaliacao();

    }

    // 1) Sinais unários  positivo  negativo  incremento e decremento
    static void unariosEIncremento() {

        System.out.println("Sinais unarios  incremento e decremento");

        int a = 5;
        int b = -a; // sinal negativo
        int c = +a; // sinal positivo
        int x = 10;
        int pre = ++x; // incrementa antes de usar
        int pos = x++; // usa  depois incrementa
        int y = 3;
        int preDec = --y;
        int posDec = y--;

        System.out.println("negativo de cinco  " + b);
        System.out.println("positivo de cinco  " + c);
        System.out.println("pre incremento de dez  " + pre);
        System.out.println("pos incremento apos uso  " + pos);
        System.out.println("pre decremento partindo de tres  " + preDec);
        System.out.println("pos decremento apos uso  " + posDec);

    }

    // 2) Operadores binários  soma  subtracao  multiplicacao  divisao  resto
    //    Precedencia  unarios  multiplicacao divisao resto  soma subtracao
    //    Associatividade  a maioria e da esquerda para direita  unarios da direita para esquerda
    static void binariosEPrecedencia() {

        System.out.println("\nOperadores binarios e precedencia");

        int r1 = 2 + 3 * 4; // multiplicacao vem antes
        int r2 = (2 + 3) * 4; // parenteses mudam a ordem
        int r3 = 20 - 5 - 3; // associatividade a esquerda
        int r4 = 20 - (5 - 3);

        System.out.println("dois mais tres vezes quatro  " + r1);
        System.out.println("parentezado dois mais tres vezes quatro  " + r2);
        System.out.println("vinte menos cinco menos tres  " + r3);
        System.out.println("vinte menos parentezado cinco menos tres  " + r4);

    }

    // 3) Divisão inteira x real  resto  sinal do resto segue o dividendo
    static void divisaoEResto() {

        System.out.println("\nDivisao inteira e real  resto");

        int d1 = 7 / 2; // divisao inteira
        double d2 = 7 / 2.0; // divisao real
        int m1 = 7 % 2; // resto positivo
        int m2 = -7 % 2; // resto com sinal do dividendo
        int m3 = 7 % -2;

        System.out.println("sete dividido por dois inteiro  " + d1);
        System.out.println("sete dividido por dois real  " + d2);
        System.out.println("resto de sete por dois  " + m1);
        System.out.println("resto de menos sete por dois  " + m2);
        System.out.println("resto de sete por menos dois  " + m3);

    }

    // 4) Promocao numerica  byte short char promovem para int em expressoes
    //    Cast para reduzir  pode perder informacao
    static void promocaoENarrowing() {

        System.out.println("\nPromocao numerica e cast de reducao");

        byte b = 60, c = 70;
        // b + c promove para int
        byte somaByte = (byte) (b + c); // cast para voltar a byte
        char ch = 'A';
        int code = ch + 2; // char promovido a int
        double real = 3.9;
        int truncado = (int) real; // truncamento

        System.out.println("soma de bytes com cast  " + somaByte);
        System.out.println("codigo de caracter somado com dois  " + code);
        System.out.println("truncamento de tres ponto nove  " + truncado);

    }

    // 5) Atribuicoes compostas  somas  subtracoes  produtos  quocientes  resto
    //    Elas aplicam cast implicito ao tipo do lado esquerdo
    static void atribuicoesCompostas() {

        System.out.println("\nAtribuicoes compostas com cast implicito");

        byte k = 120;
        k += 20; // equivalente a k  cast byte  k mais vinte
        int n = 5;
        n *= 3; // quinze
        n -= 2; // treze
        n /= 4; // tres
        n %= 2; // um

        System.out.println("byte apos adicao composta  " + k);
        System.out.println("inteiro apos multiplos compostos  " + n);

    }

    // 6) Overflow em inteiros  operacao aritmetica faz wrap em modulo dois elevado a n
    //    Versoes exatas de Math lancam excecao quando ha estouro
    static void overflowEAddExact() {

        System.out.println("\nOverflow e operacoes exatas");

        int max = Integer.MAX_VALUE;
        int wrap = max + 1; // overflow com wrap
        System.out.println("valor maximo de int e incremento com wrap  " + wrap);

        try {
            int boom = Math.addExact(Integer.MAX_VALUE, 1);
            System.out.println("soma exata sem falha  " + boom);
        } catch (ArithmeticException e) {
            System.out.println("soma exata detectou estouro");
        }

        try {
            int boom2 = Math.multiplyExact(1_000_000, 3_000);
            System.out.println("multiplicacao exata sem falha  " + boom2);
        } catch (ArithmeticException e) {
            System.out.println("multiplicacao exata detectou estouro");
        }

    }

    // 7) Ponto flutuante  infinitos  NaN  arredondar  piso  teto
    static void pontoFlutuanteENaN() {

        System.out.println("\nPonto flutuante  infinitos e NaN");

        double posInf = 1.0 / 0.0;
        double negInf = -1.0 / 0.0;
        double nan = 0.0 / 0.0;

        System.out.println("infinito positivo  " + posInf);
        System.out.println("infinito negativo  " + negInf);
        System.out.println("nao numero  " + nan);
        System.out.println("verificacao de NaN  " + Double.isNaN(nan));
        System.out.println("verificacao de finitude  " + Double.isFinite(1.23));

        double v = 2.75;
        long arred = Math.round(v);
        double piso = Math.floor(v);
        double teto = Math.ceil(2.01);

        System.out.println("arredondamento padrao  " + arred);
        System.out.println("piso de dois ponto setenta e cinco  " + piso);
        System.out.println("teto de dois ponto zero um  " + teto);

    }

    // 8) Funcoes utilitarias de Math  abs  pow  sqrt  min  max  clamp simples
    static void funcoesDeMath() {

        System.out.println("\nFuncoes uteis da biblioteca matematica");

        int abs = Math.abs(-10);
        double raiz = Math.sqrt(144.0);
        double pot = Math.pow(2.0, 10.0);
        int menor = Math.min(7, 3);
        int maior = Math.max(7, 3);

        int valor = 120;
        int min = 0, max = 100;
        int clamp = Math.max(min, Math.min(max, valor));

        System.out.println("valor absoluto de menos dez  " + abs);
        System.out.println("raiz de cento e quarenta e quatro  " + raiz);
        System.out.println("potencia dois elevado a dez  " + pot);
        System.out.println("menor entre sete e tres  " + menor);
        System.out.println("maior entre sete e tres  " + maior);
        System.out.println("limitacao de faixa para zero a cem  " + clamp);

    }

    // 9) Ordem de avaliacao  Java avalia argumentos da esquerda para a direita
    //    Evite depender de efeitos colaterais em expressoes complexas
    static void ordemDeAvaliacao() {


        System.out.println("\nOrdem de avaliacao de argumentos");

        int i = 1;
        int r = soma(trace(i++), trace(i++)); // primeiro argumento avaliado antes do segundo
        System.out.println("resultado de chamada com efeitos  " + r);
        System.out.println("valor final de i apos chamadas  " + i);

    }

    static int trace(int v) {
        System.out.println("avaliando argumento com valor  " + v);
        return v;
    }

    static int soma(int a, int b) {
        return a + b;
    }


    // Dicas finais
    // Use parenteses para deixar a intencao clara quando a precedencia nao for obvia
    // Prefira tipos de ponto flutuante apenas quando tolerar erro de representacao
    // Para dinheiro use BigDecimal fora do escopo de primitivos
    // Para detectar estouro em inteiros use metodos exatos da classe Math
}

