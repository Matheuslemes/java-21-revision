package estruturasdecontrole;

import java.util.*;

/**
 * ForResumo.java

 * Conteudo
 * 1) for classico com indice
 * 2) for com multiplas variaveis e passos customizados
 * 3) for each sobre arrays e colecoes
 * 4) Iteracao de Map por chave valor com entrySet
 * 5) break  continue e rotulos
 * 6) for infinito e saida por break
 * 7) Lacos aninhados e busca com curto circuito
 * 8) Dicas e armadilhas comuns
 */
public class ForResumo {

    public static void main(String[] args) {

        forClassico();
        forMultiplasVariaveis();
        forEachArrayELista();
        forEmMap();
        breakContinueRotulos();
        forInfinitoControlado();
        lacosAninhadosEBusca();
        dicasEArmadilhas();

    }

    // 1) for classico com indice
    static void forClassico() {

        System.out.println("For classico com indice");

        for (int i = 0; i < 5; i++) {
            System.out.println("posicao " + i);
        }

        System.out.println("Contagem regressiva");
        for (int i = 3; i > 0; i--) {
            System.out.println("valor " + i);
        }

    }

    // 2) for com multiplas variaveis e passos customizados
    static void forMultiplasVariaveis() {

        System.out.println("\nFor com multiplas variaveis e passos customizados");

        for (int i = 0, j = 10; i < j; i += 2, j -= 3) {
            System.out.println("par i j  " + i + "  " + j);
        }

        System.out.println("Atualizacao complexa");
        for (int i = 1; i < 20; i += i) { // dobra a cada ciclo
            System.out.println("valor correndo em potencias  " + i);
        }

    }

    // 3) for each sobre arrays e colecoes
    static void forEachArrayELista() {

        System.out.println("\nFor each sobre array");

        int[] numeros = {2, 4, 6};
        for (int n : numeros) {
            System.out.println("item do array  " + n);
        }

        System.out.println("For each sobre lista");
        List<String> nomes = List.of("Ana", "Bia", "Caio");
        for (String nome : nomes) {
            System.out.println("nome na lista  " + nome);
        }

        System.out.println("Iterando com indice e valor em lista");
        List<Double> valores = List.of(1.5, 2.5, 3.5);
        for (int i = 0; i < valores.size(); i++) {
            System.out.println("indice " + i + "  valor " + valores.get(i));
        }

    }

    // 4) Iteracao de Map por chave valor
    static void forEmMap() {

        System.out.println("\nFor em Map por chave valor");

        Map<String, Integer> pontuacao = new LinkedHashMap<>();
        pontuacao.put("Alice", 10);
        pontuacao.put("Bruno", 7);
        pontuacao.put("Carla", 12);

        for (Map.Entry<String, Integer> e : pontuacao.entrySet()) {
            System.out.println("chave " + e.getKey() + "  valor " + e.getValue());
        }

        System.out.println("Somente chaves");
        for (String k : pontuacao.keySet()) {
            System.out.println("chave vista  " + k);
        }

    }

    // 5) break  continue e rotulos
    static void breakContinueRotulos() {

        System.out.println("\nBreak e continue");

        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                System.out.println("pulando posicao dois");
                continue; // salta o corpo
            }
            if (i == 4) {
                System.out.println("parando no quatro");
                break;    // sai do laço
            }
            System.out.println("ciclo em i  " + i);
        }

        System.out.println("Rotulo para sair de laco externo");
        externo:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.println("par i j  " + i + "  " + j);
                if (i == 2 && j == 2) {
                    System.out.println("saindo do laco externo");
                    break externo; // sai dos dois lacos
                }
            }
        }

    }

    // 6) for infinito e saida por break
    static void forInfinitoControlado() {

        System.out.println("\nFor infinito controlado por break");

        int tentativas = 0;
        for (;;) {
            tentativas++;
            System.out.println("tentativa numero " + tentativas);
            if (tentativas >= 3) {
                System.out.println("condicao de saida atendida");
                break;
            }
        }

    }

    // 7) Lacos aninhados e busca com curto circuito
    static void lacosAninhadosEBusca() {

        System.out.println("\nLacos aninhados e busca");

        int[][] matriz = {
                {1, 3, 5},
                {7, 9, 11},
                {13, 15, 17}
        };
        int alvo = 9;
        boolean achou = false;

        busca:
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == alvo) {
                    System.out.println("encontrado na linha " + i + "  coluna " + j);
                    achou = true;
                    break busca;
                }
            }
        }
        if (!achou) {
            System.out.println("valor nao encontrado");
        }

    }

    // 8) Dicas e armadilhas
    static void dicasEArmadilhas() {

        System.out.println("\nDicas e armadilhas");

        System.out.println("use for each quando nao precisar do indice");
        System.out.println("prefira entrySet para mapear chave e valor em Map");
        System.out.println("cuidado com limites para evitar off by one");
        System.out.println("evite modificar a colecao durante a iteracao");
        System.out.println("para filtros e transformacoes considere Streams quando ficar mais legivel");

    }

}
