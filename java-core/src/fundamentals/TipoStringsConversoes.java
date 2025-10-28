package fundamentals;

public class TipoStringsConversoes {

    // 1) Tipos e var: crie variáveis, some, subtraia,
    // multiplique, divida; imprima resultados
    static void exTipos() {

        int a = 7, b = 3;
        var soma = a + b;
        var div  = (double) a / b;
        final double PI = 3.14159;

        System.out.printf("soma=%d, div=%.2f, PI=%.5f%n", soma, div, PI);

    }

    // 2) String vs StringBuilder: mostre custo de concatenação em loop
    static void exStrings() {

        String s = "";
        for (int i = 0; i < 5; i++) s += i + " "; // cria novas Strings

        System.out.println("String: " + s);

        var sb = new StringBuilder();
        for (int i = 0; i < 5; i++) sb.append(i).append(' ');

        System.out.println("StringBuilder: " + sb);

    }

    // 3) Conversões e operadores: parse de texto para número e
    // uso de operador ternário
    static void exConversoes() {

        var txt = "42";
        int n = Integer.parseInt(txt);
        String paridade = (n % 2 == 0) ? "par" : "ímpar";

        System.out.printf("%d é %s%n", n, paridade);

    }

    public static void main(String[] args) {

        exTipos();
        exStrings();
        exConversoes();

    }
}
