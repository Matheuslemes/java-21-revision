package estruturasdecontrole;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * WhileResumo.java

 * Conteudo
 * 1) while basico  condicao checada antes
 * 2) do while  executa ao menos uma vez
 * 3) Atualizacao correta da condicao  contadores e decrementos
 * 4) Laco sentinela  parar ao encontrar um marcador
 * 5) Validacao de entrada com do while
 * 6) Iteracao de colecoes com while e Iterator
 * 7) break  continue  e rotulos
 * 8) Laco infinito controlado e saida por evento
 * 9) Espera com backoff simples
 * 10) Conversao de for para while
 * 11) Armadilhas e boas praticas
 */
public class WhileResumo {

    public static void main(String[] args) {

        whileBasico();
        doWhileBasico();
        atualizacaoDaCondicao();
        lacoSentinela();
        validacaoComDoWhile();
        whileComIterator();
        breakContinueRotulos();
        whileInfinitoControlado();
        esperaComBackoff();
        conversaoForParaWhile();
        armadilhasEBoasPraticas();

    }

    // 1) while basico
    static void whileBasico() {

        System.out.println("While basico  repeticao enquanto condicao for verdadeira");

        int i = 0;
        while (i < 3) {
            System.out.println("contagem  " + i);
            i++; // atualiza para progredir
        }

        System.out.println("Contagem regressiva com while");
        int n = 3;
        while (n > 0) {
            System.out.println("valor  " + n);
            n--;
        }

    }

    // 2) do while  executa pelo menos uma vez
    static void doWhileBasico() {

        System.out.println("\nDo while  executa ao menos uma vez");

        int tentativas = 0;
        boolean sucesso = false;
        do {
            tentativas++;
            System.out.println("tentativa numero  " + tentativas);
            sucesso = tentativas >= 2; // simula sucesso na segunda rodada
        } while (!sucesso);
        System.out.println("processo concluido apos tentativas");

    }

    // 3) Atualizacao correta da condicao
    static void atualizacaoDaCondicao() {

        System.out.println("\nAtualizacao da condicao durante o laco");

        int soma = 0;
        int x = 1;
        while (x <= 5) {
            soma += x;
            x++;
        }
        System.out.println("soma calculada com while  " + soma);

    }

    // 4) Laco sentinela  parar ao encontrar marcador
    static void lacoSentinela() {

        System.out.println("\nLaco sentinela com marcador de parada");

        Queue<String> fila = new ArrayDeque<>();
        Collections.addAll(fila, "item1", "item2", "STOP", "item3");
        String item;
        while ((item = fila.poll()) != null) {
            if ("STOP".equals(item)) {
                System.out.println("marcador encontrado  encerrando leitura");
                break;
            }
            System.out.println("processando  " + item);
        }

    }

    // 5) Validacao de entrada com do while
    static void validacaoComDoWhile() {

        System.out.println("\nValidacao de entrada com do while");

        // Simulando leitura  em apps reais troque por Scanner ou UI
        String entradaSimulada = "  \n 123 ";
        String entrada;
        do {
            entrada = entradaSimulada.strip();
            if (entrada.isBlank()) {
                System.out.println("entrada vazia  solicite novamente");
                entradaSimulada = "42"; // simula nova tentativa
            }
        } while (entrada.isBlank());
        System.out.println("entrada valida  " + entrada);

    }

    // 6) Iteracao de colecoes com while e Iterator
    static void whileComIterator() {

        System.out.println("\nIteracao com Iterator em while");

        List<String> nomes = new ArrayList<>(List.of("Ana", "Bia", "Caio"));
        Iterator<String> it = nomes.iterator();
        while (it.hasNext()) {
            String nome = it.next();
            System.out.println("nome visto  " + nome);
            if (nome.startsWith("B")) {
                it.remove(); // remocao segura durante iteracao
            }
        }
        System.out.println("lista apos remocoes  " + nomes);

    }

    // 7) break  continue  e rotulos
    static void breakContinueRotulos() {

        System.out.println("\nBreak e continue em while");

        int i = 0;
        while (i < 5) {
            i++;
            if (i == 2) {
                System.out.println("pulando posicao dois");
                continue; // volta ao teste
            }
            if (i == 4) {
                System.out.println("parando no quatro");
                break;    // sai do laco
            }
            System.out.println("ciclo em i  " + i);
        }

        System.out.println("Rotulo para sair de dois lacos");
        externo:
        while (true) {
            int a = 0;
            while (a < 3) {
                System.out.println("passo  " + a);
                if (a == 1) {
                    System.out.println("quebrando laco externo");
                    break externo;
                }
                a++;
            }
        }

    }

    // 8) Laco infinito controlado por evento
    static void whileInfinitoControlado() {

        System.out.println("\nWhile infinito controlado com break");

        int contador = 0;
        while (true) {
            contador++;
            System.out.println("ciclo  " + contador);
            if (contador >= 3) {
                System.out.println("condicao de saida atendida");
                break;
            }
        }

    }

    // 9) Espera com backoff simples
    static void esperaComBackoff() {

        System.out.println("\nEspera com backoff simples");

        int tentativa = 0;
        int atraso = 100; // milissegundos
        boolean ok = false;

        while (tentativa < 5 && !ok) {
            tentativa++;
            System.out.println("checando recurso  tentativa  " + tentativa);
            ok = ThreadLocalRandom.current().nextInt(10) > 6; // simula sucesso aleatorio
            if (!ok) {
                try {
                    Thread.sleep(atraso);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("interrompido  abortando");
                    return;
                }
                atraso = Math.min(atraso * 2, 1600); // cresce ate um teto
            }
        }
        System.out.println(ok ? "recurso disponivel" : "recurso indisponivel apos tentativas");

    }

    // 10) Conversao de for para while
    static void conversaoForParaWhile() {

        System.out.println("\nConversao de for para while");

        // for (int i = 0; i < 3; i++) { corpo }
        int i = 0;            // inicializacao
        while (i < 3) {       // condicao
            System.out.println("iteracao  " + i);
            i++;                // atualizacao
        }

    }

    // 11) Armadilhas e boas praticas
    static void armadilhasEBoasPraticas() {

        System.out.println("\nArmadilhas e boas praticas");

        System.out.println("garanta atualizacao da variavel de controle para evitar laco infinito acidental");
        System.out.println("prefira do while quando precisar executar ao menos uma vez antes da checagem");
        System.out.println("use break e continue com parcimonia e comentarios claros");
        System.out.println("ao modificar colecoes durante a iteracao  prefira Iterator com remocao segura");
        System.out.println("evite usar comparacao de ponto flutuante como condicao de parada  prefira contadores ou tolerancias");
        System.out.println("isole I O de leitura em metodos  facilita testes e evita dependencias no laco");

    }

}
