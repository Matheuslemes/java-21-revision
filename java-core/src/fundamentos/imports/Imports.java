package fundamentos.imports;

/*
 * ImportsResumo.java
 *
 * Guia prático de "imports" em Java:
 *
 * 1  Tipos de import:
 *    - Import simples (single-type):      import pacote.Classe;
 *    - Import on-demand (wildcard):       import pacote.*;
 *    - Import estático simples:           import static pacote.Classe.MEMBRO;
 *    - Import estático on-demand:         import static pacote.Classe.*;
 *
 * 2  Regras importantes:
 *    - Tudo em java.lang (String, Math, System, etc.) já é importado automaticamente.
 *    - Imports valem para o arquivo-fonte inteiro (compilação), não para tempo de execução.
 *    - Não existe "alias" (tipo "import X as Y") em Java.
 *    - O curinga '*' não atravessa subpacotes (java.util.* NÃO inclui java.util.stream.*).
 *    - Mesmo pacote não precisa de import.
 *    - Evite colisões de nome (ex.: java.util.List x java.awt.List); use nome qualificado se necessário.
 *    - Static import é para CONSTANTES/MÉTODOS estáticos; use com parcimônia (pode reduzir legibilidade).
 *    - Não é possível importar do "default package".
 *
 * 3  Estilo/boas práticas (resumo):
 *    - Prefira imports explícitos a muitos wildcards em código de biblioteca.
 *    - Em app pequeno, wildcard moderado em java.util pode ser aceitável.
 *    - Evite static import excessivo; mantenha onde realmente melhora leitura (ex.: assertThat, toSeconds).
 *    - Deixe a IDE organizar/otimizar imports (semântica não muda).
 */

import java.util.*; // on-demand: List, Map, Arrays, Collections, etc.
import java.util.stream.Collectors; // import simples de um tipo específico
import java.time.*;                      // on-demand (não pega subpacotes como java.time.format.*)
import java.time.format.DateTimeFormatter;

// Static imports: tornam membros estáticos acessíveis sem prefixo de classe.
import static java.lang.Math.*; // PI, sqrt, max, min, etc.
import static java.util.Collections.*; // sort, reverseOrder, emptyList, etc.

public class Imports {

    public static void main(String[] args) {
        exemploImportSimplesEWildcard();
        exemploImportEStaticImport();
        exemploConflitoDeNomes();
        exemploSubpacotesENomeQualificado();
        exemploTempoEFormatacao();
        boasPraticas();
    }

    // 1 - Import simples e on-demand (wildcard)
    static void exemploImportSimplesEWildcard() {
        System.out.println("IMPORT SIMPLES E ON-DEMEND");

        // Graças a "import java.util.*;", podemos usar List diretamente
        List<String> nomes = new ArrayList<>(List.of("Ana", "Lia", "João"));

        // "import java.util.stream.Collectors;" permite referenciar Collectors pelo nome simples
        String csv = nomes.stream().collect(Collectors.joining(";"));
        System.out.println(csv);

        // Arrays (java.util.Arrays) também veio pelo wildcard de java.util.*
        int[] nums = {3, 1, 2};
        Arrays.sort(nums);

    }

    // 2 - Static import (simples e on-demand)
    static void exemploImportEStaticImport() {
        System.out.println("\nSTATIC IMPORT");

        // Sem static import: Math.sqrt(16), Math.PI
        // Com "import static java.lang.Math.*;", podemos usar diretamente:
        double r = sqrt(16);   // Math.sqrt
        double circ = 2 * PI * 10; // Math.PI
        System.out.printf("sqrt(16)=%.0f, circ(raio=10)=%.2f%n", r, circ);

        // Com "import static java.util.Collections.*;"
        List<Integer> lista = new ArrayList<>(List.of(5, 2, 9));
        sort(lista, reverseOrder());  // Collections.sort + Collections.reverseOrder
        System.out.println(lista);
        // Use com moderação: sem o contexto da classe, o leitor pode estranhar de onde vem o método/constante.

    }

    // 3 - onflito de nomes (java.util.List x java.awt.List)
    static void exemploConflitoDeNomes() {
        System.out.println("\nCONFLITO DE NOMES E NOME QUALIFICADO");

        // Já importamos java.util.* (List, Map, etc.). Se precisarmos java.awt.List, NÃO importe ambos;
        // use o nome totalmente qualificado para o "intruso":
        java.awt.List listaAwt = new java.awt.List(); // nome qualificado evita colisão
        List<String> listaUtil = new ArrayList<>();   // java.util.List (importado)
        listaAwt.add("item-awt");
        listaUtil.add("item-util");

        System.out.println("awt: " + listaAwt.getItem(0) + " | util: " + listaUtil.get(0));

        // Observação: se você importasse explicitamente java.awt.List e java.util.List, o compilador
        // consideraria "List" ambíguo neste arquivo. Prefira 1 import + FQN para o outro.

    }

    // 4) Subpacotes NÃO vêm com wildcard do pai; quando faltar, use import específico ou FQN.
    static void exemploSubpacotesENomeQualificado() {

        System.out.println("\nSUBPACOTES E FQN (FULLY QUALIFIED NAME");
        // java.util.* NÃO inclui java.util.concurrent.* ou java.util.stream.* (embora tenhamos Collectors importado)
        // Podemos usar FQN direto, sem import:
        java.util.concurrent.atomic.AtomicInteger ai = new java.util.concurrent.atomic.AtomicInteger(0);
        System.out.println("AtomicInteger=" + ai.incrementAndGet());

        // Ou importar explicitamente no topo do arquivo se for usar bastante.

    }

    // 5) Tempo e formatação (demonstra subpacote format e import específico)
    static void exemploTempoEFormatacao() {

        System.out.println("\nTEMPO E FORMATAÇÃO");
        // java.time.* permite LocalDate, LocalDateTime, ZonedDateTime etc.
        LocalDate hoje = LocalDate.now();

        // DateTimeFormatter está em java.time.format (subpacote) — importamos explicitamente:
        String br = hoje.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        System.out.println("Hoje (BR): " + br);

        // Outro exemplo: subpacote java.time.temporal (sem import; FQN pontual)
        long diasAteNatal = java.time.temporal.ChronoUnit.DAYS.between(hoje, LocalDate.of(hoje.getYear(), 12, 25));
        System.out.println("Dias até o Natal: " + diasAteNatal);

    }

    // 6 - Boas práticas resumidas (impressão em runtime apenas como lembrete)
    static void boasPraticas() {

        System.out.println("\nBOAS PRATICAS DE IMPORT");
        System.out.println("- Prefira imports explícitos em bibliotecas; use wildcard com parcimônia.");
        System.out.println("- Evite static import excessivo; use onde melhora a leitura (ex.: testes, utilitários).");
        System.out.println("- Não há alias em Java (sem 'as'); use nome qualificado para resolver conflitos.");
        System.out.println("- java.lang já é importado (String, Math, System).");
        System.out.println("- Wildcard não inclui subpacotes.");
        System.out.println("- Deixe a IDE organizar imports (remover não usados e ordenar).");

    }

}