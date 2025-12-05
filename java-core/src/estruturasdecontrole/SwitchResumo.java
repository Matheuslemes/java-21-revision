package estruturasdecontrole;

import java.time.DayOfWeek;
import java.util.Locale;

/**
 * SwitchResumo.java

 * Conteudo
 * 1) switch classico com break e fall through
 * 2) switch com multiplos rotulos
 * 3) switch com String e enum
 * 4) switch expression com seta e yield
 * 5) Escopo de variaveis dentro de casos
 * 6) Default e tratamento de valores inesperados
 * 7) Null no switch  alternativa segura
 * 8) Pattern matching simples por tipo no switch
 * 9) Armadilhas e boas praticas
 */
public class SwitchResumo {

    public static void main(String[] args) {

        switchClassico();
        switchMultiplosRotulos();
        switchStringEEnum();
        switchExpressionSeta();
        escopoDeVariaveis();
        defaultEValoresInesperados();
        tratamentoDeNull();
        patternMatchingPorTipo();
        armadilhasEBoasPraticas();

    }

    // 1) switch classico com break e queda de caso
    static void switchClassico() {

        System.out.println("Switch classico com break e queda");

        int dia = 3;
        switch (dia) {
            case 1:
                System.out.println("segunda");
                break;
            case 2:
                System.out.println("terca");
                break;
            case 3:
            case 4:
                System.out.println("meio da semana");
                // sem break aqui cairia no proximo caso
                break;
            default:
                System.out.println("outro dia");
        }

    }

    // 2) multiplos rotulos por caso com queda controlada
    static void switchMultiplosRotulos() {

        System.out.println("\nMultiplos rotulos em um caso");

        int codigo = 500;
        switch (codigo) {
            case 200:
                System.out.println("ok");
                break;
            case 400:
            case 401:
            case 403:
                System.out.println("erro do cliente");
                break;
            case 500:
            case 502:
            case 503:
                System.out.println("erro do servidor");
                break;
            default:
                System.out.println("situacao desconhecida");
        }

    }

    // 3) switch com String e enum
    static void switchStringEEnum() {

        System.out.println("\nSwitch com String");

        String comando = "start";
        switch (comando.toLowerCase(Locale.ROOT)) {
            case "start":
                System.out.println("iniciar processo");
                break;
            case "stop":
                System.out.println("parar processo");
                break;
            default:
                System.out.println("comando invalido");
        }

        System.out.println("Switch com enum");
        DayOfWeek dw = DayOfWeek.SATURDAY;
        switch (dw) {
            case SATURDAY:
            case SUNDAY:
                System.out.println("fim de semana");
                break;
            default:
                System.out.println("dia util");
        }

    }

    // 4) switch expression com seta e yield
    static void switchExpressionSeta() {

        System.out.println("\nSwitch expression com seta e yield");

        int mes = 2;
        int dias = switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> {
                boolean bissexto = true; // exemplo ilustrativo
                yield bissexto ? 29 : 28;
            }
            default -> {
                System.out.println("mes invalido");
                yield -1;
            }
        };
        System.out.println("dias calculados  " + dias);

    }

    // 5) Escopo de variaveis  cada caso pode ter seu proprio bloco
    static void escopoDeVariaveis() {

        System.out.println("\nEscopo de variaveis em casos");

        int n = 4;
        switch (n) {
            case 1 -> {
                int a = 10;
                System.out.println("bloco um com variavel local  " + a);
            }
            case 2 -> {
                int a = 20;
                System.out.println("bloco dois com variavel local  " + a);
            }
            default -> System.out.println("bloco padrao sem variavel local");
        }

    }

    // 6) Default e tratamento de valores inesperados
    static void defaultEValoresInesperados() {

        System.out.println("\nDefault e valores inesperados");

        String status = "pendente";
        String mensagem = switch (status) {
            case "novo" -> "registrado";
            case "em andamento" -> "em processamento";
            case "concluido" -> "finalizado";
            default -> "estado desconhecido";
        };
        System.out.println("mensagem gerada  " + mensagem);

    }

    // 7) Null no switch  abordagem defensiva
    static void tratamentoDeNull() {

        System.out.println("\nTratamento de null antes do switch");

        String modo = null;
        String info = (modo == null)
                ? "modo nao informado"
                : switch (modo) {
            case "claro" -> "tema claro ativo";
            case "escuro" -> "tema escuro ativo";
            default -> "tema padrao";
        };
        System.out.println("saida do fluxo  " + info);

    }

    // 8) Pattern matching por tipo  exemplo simples
    static void patternMatchingPorTipo() {

        System.out.println("\nPattern matching por tipo em switch");

        Object obj = 42.5;
        String tipo = switch (obj) {
            case null -> "nulo";
            case String s -> "texto com tamanho " + s.length();
            case Integer i -> "inteiro com valor " + i;
            case Double d -> {
                String faixa = d > 0 ? "positivo" : "nao positivo";
                yield "real " + faixa;
            }
            default -> "outro tipo";
        };
        System.out.println("descricao do objeto  " + tipo);

    }

    // 9) Armadilhas e boas praticas
    static void armadilhasEBoasPraticas() {
        System.out.println("\nArmadilhas e boas praticas");
        System.out.println("use break no switch classico para evitar queda acidental");
        System.out.println("prefira switch expression com seta para codigo mais claro");
        System.out.println("agruppe valores com virgula para reduzir repeticao");
        System.out.println("valide null antes do switch quando houver risco");
        System.out.println("quando houver regra por tipo considere pattern matching");
        System.out.println("mantenha cada caso curto e objetivo");
    }

}
