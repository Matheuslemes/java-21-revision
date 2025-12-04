package fundamentos.operadores;

/**
 * OperadorTernarioResumo.java
 *
 * Conteudo
 * 1) Basico do operador condicional ternario
 * 2) Comparacao com if else
 * 3) Precedencia e necessidade de parenteses
 * 4) Encadeamento com legibilidade
 * 5) Com var e inferencia de tipo
 * 6) Promocao e compatibilidade de tipos no ramo verdadeiro e falso
 * 7) Nulos de forma segura
 * 8) Exemplos uteis  faixa etaria  minimo e maximo simples  formatacao
 * 9) Armadilhas e boas praticas
 */
public class OperadorTernarioResumo {

    public static void main(String[] args) {
        basico();
        ifElseEquivalente();
        precedenciaEParenteses();
        encadeamentoLegivel();
        inferenciaComVar();
        promocaoDeTipos();
        nulosComSeguranca();
        exemplosUteis();
        armadilhasEBoasPraticas();
    }

    // 1) Basico
    static void basico() {

        System.out.println("Basico do ternario");

        int idade = 20;
        String status = (idade >= 18) ? "maior" : "menor";
        System.out.println("classificacao por idade  " + status);

        boolean aprovado = true;
        String msg = aprovado ? "parabens aprovado" : "tente novamente";
        System.out.println("resultado do exame  " + msg);

    }

    // 2) Equivalencia com if else
    static void ifElseEquivalente() {

        System.out.println("\nEquivalencia com if else");

        int pontos = 73;
        // Ternario
        String nivel = pontos >= 70 ? "intermediario" : "iniciante";
        System.out.println("nivel por pontos  " + nivel);

        // If else
        String nivel2;
        if (pontos >= 70) {
            nivel2 = "intermediario";
        } else {
            nivel2 = "iniciante";
        }
        System.out.println("nivel por pontos via if else  " + nivel2);

    }

    // 3) Precedencia  use parenteses quando combinado com outros operadores
    static void precedenciaEParenteses() {

        System.out.println("\nPrecedencia e parenteses");

        int a = 5, b = 12;
        // Sem parenteses pode confundir quando ha soma ou concatenacao
        String texto1 = a > b ? "a maior" : "b maior";
        String texto2 = "comparacao  " + (a > b ? "a maior" : "b maior");
        System.out.println(texto1);
        System.out.println(texto2);

    }

    // 4) Encadeamento  prefira clareza com parenteses e extracao de variaveis
    static void encadeamentoLegivel() {

        System.out.println("\nEncadeamento com legibilidade");

        int nota = 84;
        String conceito = nota >= 90 ? "A"
                : nota >= 80 ? "B"
                : nota >= 70 ? "C"
                : nota >= 60 ? "D"
                : "E";
        System.out.println("conceito pela nota  " + conceito);

        // Alternativa mais legivel usando variavel intermediaria
        boolean topo = nota >= 90;
        boolean alto = nota >= 80 && nota < 90;
        String conceitoClaro = topo ? "A" : (alto ? "B" : "outros");
        System.out.println("conceito simplificado  " + conceitoClaro);

    }

    // 5) Inferencia com var
    static void inferenciaComVar() {

        System.out.println("\nInferencia com var");

        int valor = 10;
        var mensagem = valor % 2 == 0 ? "par" : "impar"; // var recebe tipo String
        System.out.println("mensagem de paridade  " + mensagem);

    }

    // 6) Promocao e compatibilidade de tipos
    static void promocaoDeTipos() {

        System.out.println("\nPromocao e compatibilidade de tipos");

        // Ambos os ramos precisam ser atribuiveis a um tipo comum
        boolean flag = true;
        Number n = flag ? Integer.valueOf(10) : Double.valueOf(3.5); // comum e Number
        System.out.println("numero comum escolhido  " + n);

        // Em tipos primitivos ha promocao
        double r = flag ? 1 : 2.5; // 1 promove para double
        System.out.println("resultado com promocao numerica  " + r);

    }

    // 7) Nulos com seguranca
    static void nulosComSeguranca() {

        System.out.println("\nNulos com seguranca");

        String nome = null;
        String safe = (nome != null && !nome.isBlank()) ? nome : "desconhecido";
        System.out.println("nome tratado  " + safe);

        Integer qtd = null;
        int qtdSafe = qtd != null ? qtd : 0;
        System.out.println("quantidade tratada  " + qtdSafe);

    }

    // 8) Exemplos uteis
    static void exemplosUteis() {

        System.out.println("\nExemplos uteis");

        int idade = 67;
        String faixa = idade < 13 ? "crianca"
                : idade < 18 ? "adolescente"
                : idade < 60 ? "adulto"
                : "idoso";
        System.out.println("faixa etaria  " + faixa);

        int x = 7, y = 3;
        int min = x < y ? x : y;
        int max = x > y ? x : y;
        System.out.println("minimo calculado  " + min);
        System.out.println("maximo calculado  " + max);

        double preco = 100.0;
        boolean vip = true;
        double finalizado = vip ? preco * 0.9 : preco; // desconto simples
        System.out.println("preco final  " + finalizado);

        int codigo = 2;
        String label = codigo == 1 ? "aberto"
                : codigo == 2 ? "em andamento"
                : codigo == 3 ? "concluido"
                : "indefinido";
        System.out.println("status por codigo  " + label);

    }

    // 9) Armadilhas e boas praticas
    static void armadilhasEBoasPraticas() {

        System.out.println("\nArmadilhas e boas praticas");

        // a) Nao realize efeitos colaterais nos ramos
        int saldo = 100;
        boolean debitar = false;
        int novo = debitar ? saldo - 10 : saldo;
        System.out.println("saldo recalculado  " + novo);

        // b) Evite aninhamento profundo quando if else encurta melhor
        int h = 14;
        String turno = h < 6 ? "madrugada"
                : h < 12 ? "manha"
                : h < 18 ? "tarde"
                : "noite";
        System.out.println("turno do dia  " + turno);

        // c) Use parenteses quando misturar com soma ou concatenacao para evitar ambiguidade
        int a = 2, b = 5;
        String info = "maior selecionado  " + (a > b ? a : b);
        System.out.println(info);

        // d) Tipos genericos diferentes nos ramos podem gerar escolha inesperada
        Object valor = true ? "texto" : 10; // tipo comum vira Object
        System.out.println("valor com tipo generico  " + valor.getClass().getSimpleName());

    }

}

