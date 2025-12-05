package estruturasdecontrole;

import java.util.*;

/**
 * BreakContinueResumo.java

 * Conteudo
 * 1) For com break para sair cedo
 * 2) For com continue para pular iteracao
 * 3) While com break e continue
 * 4) Do while com saida antecipada
 * 5) Lacos aninhados com rotulo para sair de varios niveis
 * 6) Busca em matriz com saida antecipada
 * 7) Varredura de lista com filtro via continue
 * 8) Varredura de mapa com saida por condicao
 * 9) Switch classico com break
 * 10) Leitura ate sentinela
 * 11) Armadilhas e boas praticas
 */


public class BreakContinueResumo {

    public static void main(String[] args) {

        breakEmForSimples();
        continueEmForSimples();
        whileComBreakEContinue();
        doWhileComBreak();
        lacosAninhadosComRotulo();
        buscaEmMatrizComSaidaAntecipada();
        varreduraDeListaComContinue();
        varreduraDeMapaComSaidaPorCondicao();
        usoComSwitchClassico();
        leituraAteSentinela();
        armadilhasEBoasPraticas();

    }

    // 1) for simples com break
    static void breakEmForSimples() {

        System.out.println("For com break para sair cedo");

        for (int i = 0; i < 10; i++) {
            if (i > 3) {
                System.out.println("limite atingido  saindo");
                break;
            }
            System.out.println("ciclo em i  " + i);
        }

    }

    // 2) for simples com continue
    static void continueEmForSimples() {

        System.out.println("\nFor com continue para pular iteracao");

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                System.out.println("pulando par  " + i);
                continue; // ignora o restante do corpo e vai ao proximo i
            }
            System.out.println("processando impar  " + i);
        }

    }

    // 3) while com ambos
    static void whileComBreakEContinue() {

        System.out.println("\nWhile com break e continue");

        int i = 0;
        while (i < 7) {
            i++;
            if (i == 3) {
                System.out.println("posicao tres pulada");
                continue;
            }
            if (i == 6) {
                System.out.println("posicao seis interrompe a leitura");
                break;
            }
            System.out.println("lendo posicao  " + i);
        }

    }

    // 4) do while com break
    static void doWhileComBreak() {

        System.out.println("\nDo while com break quando condicao externa ocorrer");

        int tentativas = 0;
        boolean sucesso = false;
        do {
            tentativas++;
            System.out.println("tentativa numero  " + tentativas);
            sucesso = tentativas >= 2;
            if (sucesso) {
                System.out.println("concluido com sucesso  encerrando");
                break;
            }
        } while (true);

    }

    // 5) laços aninhados com rotulo para sair de varios niveis
    static void lacosAninhadosComRotulo() {

        System.out.println("\nLacos aninhados com rotulo para saida dupla");

        externo:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.println("verificando par  " + i + "  " + j);
                if (i == 2 && j == 2) {
                    System.out.println("condicao encontrada  saindo do externo");
                    break externo;
                }
            }
        }

    }

    // 6) busca em matriz com break ao achar o alvo
    static void buscaEmMatrizComSaidaAntecipada() {

        System.out.println("\nBusca em matriz com saida antecipada");

        int[][] m = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int alvo = 5;
        boolean achou = false;
        busca:
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == alvo) {
                    System.out.println("alvo encontrado na linha  " + i + "  coluna  " + j);
                    achou = true;
                    break busca;
                }
            }
        }
        if (!achou) System.out.println("alvo nao encontrado");

    }

    // 7) varredura de lista usando continue para filtrar
    static void varreduraDeListaComContinue() {

        System.out.println("\nLista com continue para filtrar itens");

        List<String> nomes = List.of("Ana", "Bruno", "Bea", "Caio", "Beto");
        for (String n : nomes) {
            if (!n.startsWith("B")) continue;
            System.out.println("nome selecionado  " + n);
        }

    }

    // 8) varredura de mapa com saida por condicao
    static void varreduraDeMapaComSaidaPorCondicao() {

        System.out.println("\nMapa com saida ao atingir pontuacao alvo");

        Map<String, Integer> pontos = new LinkedHashMap<>();
        pontos.put("Alice", 5);
        pontos.put("Bruno", 12);
        pontos.put("Carla", 8);
        int alvo = 10;
        for (Map.Entry<String, Integer> e : pontos.entrySet()) {
            if (e.getValue() >= alvo) {
                System.out.println("alvo atingido por  " + e.getKey());
                break;
            }
            System.out.println("avaliando  " + e.getKey());
        }
    }

    // 9) switch classico usa break para evitar queda
    static void usoComSwitchClassico() {

        System.out.println("\nSwitch classico com break");

        int codigo = 2;
        switch (codigo) {
            case 1:
                System.out.println("opcao um");
                break;
            case 2:
                System.out.println("opcao dois");
                break;
            default:
                System.out.println("opcao desconhecida");
        }

    }

    // 10) leitura ate sentinela com break
    static void leituraAteSentinela() {

        System.out.println("\nLeitura ate sentinela usando fila simulada");

        Queue<String> fila = new ArrayDeque<>();
        Collections.addAll(fila, "a", "b", "STOP", "c");
        while (true) {
            String v = fila.poll();
            if (v == null) {
                System.out.println("fila vazia  encerrando");
                break;
            }
            if ("STOP".equals(v)) {
                System.out.println("sentinela recebida  encerrando");
                break;
            }
            System.out.println("processando item  " + v);
        }

    }

    // 11) armadilhas e boas praticas
    static void armadilhasEBoasPraticas() {
        System.out.println("\nArmadilhas e boas praticas");
        System.out.println("use break para sair cedo quando a intencao for clara");
        System.out.println("use continue para pular processamento de casos especificos");
        System.out.println("evite aninhamentos profundos  prefira rotulos com parcimonia");
        System.out.println("documente o motivo do break em fluxos complexos");
        System.out.println("em Streams nao existe break  use anyMatch  noneMatch  limit ou encontre outra abordagem");
        System.out.println("cuidado para nao pular atualizacoes importantes ao usar continue");
    }

}
