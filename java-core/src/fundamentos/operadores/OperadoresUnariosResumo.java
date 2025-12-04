package fundamentos.operadores;
/**
 * OperadoresUnariosResumo.java
 *
 * Conteudo
 * 1) Sinal numerico  mais e menos
 * 2) Negacao logica
 * 3) Incremento e decremento  prefixo e posfixo
 * 4) Complemento bit a bit
 * 5) Cast unario e promocao numerica
 * 6) Ordem de avaliacao e efeitos colaterais
 * 7) Armadilhas e boas praticas
 */
public class OperadoresUnariosResumo {

    public static void main(String[] args) {

        sinalNumerico();
        negacaoLogica();
        incrementoDecremento();
        incrementoEmExpressao();
        complementoBit();
        castUnarioEPromocao();
        ordemDeAvaliacao();
        armadilhasEBoasPraticas();

    }

    // 1) Sinal numerico  +  e  -
    static void sinalNumerico() {

        System.out.println("Sinal numerico  mais e menos");

        int a = 5;
        int positivo = +a; // nao altera o valor
        int negativo = -a; // inverte o sinal
        double v = -3.5;
        double volta = -v; // duplo menos reverte

        System.out.println("numero base cinco com mais unario  " + positivo);
        System.out.println("numero base cinco com menos unario " + negativo);
        System.out.println("numero real negativo com menos unario   " + v);
        System.out.println("duplo menos sobre real retorna positivo " + volta);
    }

    // 2) Negacao logica  !
    static void negacaoLogica() {

        System.out.println("\nNegacao logica");

        boolean t = true, f = false;
        System.out.println("negacao de verdadeiro  " + (!t));
        System.out.println("negacao de falso       " + (!f));

        int idade = 17;
        boolean maior = idade >= 18;
        System.out.println("nao maior de idade     " + (!maior));
        // Dupla negacao pode ser util para normalizar para booleano simples
        System.out.println("dupla negacao normaliza valor booleano  " + (!!maior));

    }

    // 3) Incremento e decremento  ++  --
    static void incrementoDecremento() {

        System.out.println("\nIncremento e decremento  formas isoladas");

        int x = 10;
        ++x; // prefixo  incrementa antes
        System.out.println("aplicacao de prefixo em dez   " + x);
        x++; // posfixo  incrementa depois do uso
        System.out.println("aplicacao de posfixo em seguida  " + x);

        int y = 3;
        --y;
        System.out.println("decremento prefixo partindo de tres " + y);
        y--;
        System.out.println("decremento posfixo na sequencia     " + y);

    }

    // Incremento em expressao  mostra diferenca entre prefixo e posfixo
    static void incrementoEmExpressao() {

        System.out.println("\nIncremento e decremento em expressoes");

        int i = 1;
        int a = ++i;  // i vira dois  a recebe dois
        int b = i++;  // b recebe dois  i vira tres
        System.out.println("valor capturado apos prefixo   " + a);
        System.out.println("valor capturado no posfixo     " + b);
        System.out.println("contador apos ambas aplicacoes  " + i);

        int j = 5;
        int r = j++ + ++j; // avalia j posfixo  depois j prefixo
        System.out.println("soma com posfixo e prefixo      " + r);
        System.out.println("contador final apos soma         " + j);

    }

    // 4) Complemento bit a bit  ~
    static void complementoBit() {

        System.out.println("\nComplemento bit a bit");

        int m = 0b0000_1100;     // doze
        int comp = ~m; // inverte todos os bits  complemento de dois
        System.out.println("valor original em binario        " + toBin(m));
        System.out.println("valor complementado em binario   " + toBin(comp));

        // Dica  complemento somado com um produz o negativo do numero em complemento de dois
        int neg = ~m + 1;
        System.out.println("negativo calculado por complemento mais um  " + neg);

    }

    // 5) Cast unario e promocao numerica
    static void castUnarioEPromocao() {

        System.out.println("\nCast unario e promocao numerica");

        double d = 7.9;
        int truncado = (int) d; // cast reduz e trunca
        System.out.println("conversao real para inteiro com truncamento  " + truncado);

        // Promocao  byte  short  char promovem para int em operacoes aritmeticas e nos sinais unarios
        byte b = 100;
        int viaMais = +b; // mais unario promove para int
        byte deVolta = (byte) (+b);  // cast explicito para voltar
        System.out.println("promocao com mais unario produz inteiro      " + viaMais);
        System.out.println("retorno ao intervalo de byte por cast        " + deVolta);

        char c = 'A';
        int code = +c; // promove para inteiro com codigo Unicode
        System.out.println("codigo Unicode de caracter por mais unario   " + code);

    }

    // 6) Ordem de avaliacao e efeitos colaterais
    static void ordemDeAvaliacao() {

        System.out.println("\nOrdem de avaliacao e efeitos");

        int k = 0;
        int r = trace("primeiro", ++k) + trace("segundo", k++) + trace("terceiro", ++k);
        System.out.println("resultado agregado da soma    " + r);
        System.out.println("contador final apos chamadas  " + k);

    }

    static int trace(String rotulo, int v) {

        System.out.println("avaliando passo  " + rotulo + "  com valor  " + v);

        return v;

    }

    // 7) Armadilhas e boas praticas
    static void armadilhasEBoasPraticas() {

        System.out.println("\nArmadilhas e boas praticas");

        // a) Evite usar incremento dentro de expressoes complexas
        int n = 1;
        int confuso = n++ + ++n + n--; // leitura dificil
        System.out.println("expressao com varios incrementos  " + confuso);
        System.out.println("contador apos expressao confusa    " + n);

        // b) Nao ha incremento para wrappers imutaveis como Integer em contexto de null
        // Integer z = null;
        // z++;  // compilacao ok mas em tempo de execucao ocorreria falha por desencaixotar nulo
        System.out.println("incremento em caixa nula pode causar erro de desencaixe");

        // c) Complemento bit a bit so faz sentido em inteiros
        // d) Duplo menos em ponto flutuante apenas troca sinais  nao corrige erros de representacao
        double p = 0.1 + 0.2;
        System.out.println("soma de ponto flutuante pode ter arredondamento binario  " + p);

        // e) Para melhorar legibilidade  prefira separar passos
        int v = 10;
        v++; // passo claro
        int total = v + 5;
        System.out.println("somatorio apos passos claros  " + total);

    }

    // Utilitario para binario
    static String toBin(int v) {

        String s = Integer.toBinaryString(v);

        if (s.length() < 32) s = "0".repeat(32 - s.length()) + s;

        return s.substring(0, 4) + " " + s.substring(4, 8) + " " + s.substring(8, 12) + " " +
                s.substring(12, 16) + " " + s.substring(16, 20) + " " + s.substring(20, 24) + " " +
                s.substring(24, 28) + " " + s.substring(28, 32);

    }

}

