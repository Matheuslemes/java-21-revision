package fundamentos.operadores;

/**
 * OperadoresLogicosResumo.java
 *
 * Conteúdo
 * 1) Operadores logicos basicos  negacao  conjuncao  disjuncao
 * 2) Curto circuito  ordem de avaliacao
 * 3) Diferenca entre logicos e bit a bit em boolean
 * 4) Precedencia e parenteses
 * 5) Leis de De Morgan
 * 6) Padroes uteis  guard clauses  validacoes
 * 7) Predicados e Streams  anyMatch  allMatch  noneMatch
 * 8) Pitfalls comuns
 */
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class OperadoresLogicosResumo {

    public static void main(String[] args) {

        basico();
        curtoCircuitoEOrdem();
        logicoVsBitABit();
        precedenciaEParenteses();
        leisDeDeMorgan();
        padroesGuardClauses();
        predicadosEStreams();
        pitfalls();

    }

    // 1) Basico  !  &&  ||
    static void basico() {

        System.out.println("Basico de logicos");
        boolean t = true;
        boolean f = false;

        System.out.println("negacao de verdadeiro  " + (!t));
        System.out.println("negacao de falso       " + (!f));

        System.out.println("verdadeiro E falso     " + (t && f));
        System.out.println("verdadeiro E verdadeiro " + (t && t));

        System.out.println("falso OU falso         " + (f || f));
        System.out.println("falso OU verdadeiro    " + (f || t));

        // comparacoes podem ser combinadas
        int idade = 20;
        boolean maiorDeIdade = idade >= 18;
        boolean menorDe60 = idade < 60;
        System.out.println("maior de idade E menor de sessenta  " + (maiorDeIdade && menorDe60));

    }

    // 2) Curto circuito e ordem de avaliacao
    static void curtoCircuitoEOrdem() {

        System.out.println("\nCurto circuito e ordem de avaliacao");

        // No E curto circuito  se o primeiro for falso o segundo nao e avaliado
        System.out.println("E curto circuito  primeiro falso evita segundo  " + (falsoETrace()));

        // No OU curto circuito  se o primeiro for verdadeiro o segundo nao e avaliado
        System.out.println("OU curto circuito  primeiro verdadeiro evita segundo  " + (verdadeiroOuTrace()));

        // Ordem de avaliacao e da esquerda para a direita
        int i = 0;
        boolean r = trace("A", ++i) && trace("B", ++i) || trace("C", ++i);
        System.out.println("ordem A depois B depois possivel C  contador  " + i + "  resultado  " + r);

    }

    static boolean falsoETrace() {

        return false && trace("nao deveria aparecer");

    }

    static boolean verdadeiroOuTrace() {

        return true || trace("nao deveria aparecer");

    }

    static boolean trace(String rotulo) {

        System.out.println("avaliando  " + rotulo);
        return true;

    }

    static boolean trace(String rotulo, int contador) {

        System.out.println("avaliando  " + rotulo + "  passo  " + contador);
        return contador % 2 == 1; // apenas para variar retornos

    }

    // 3) Diferenca entre logicos e bit a bit em boolean
    static void logicoVsBitABit() {

        System.out.println("\nLogico curto circuito vs bit a bit em boolean");

        // & e | avaliados sempre  sem curto circuito
        boolean a = false;
        boolean b = sideEffect("lado B");

        System.out.println("E curto circuito  " + (a && b)); // b nao avaliado
        System.out.println("E bit a bit       " + (a &  b)); // b avaliado

        boolean c = true;
        boolean d = sideEffect("lado D");
        System.out.println("OU curto circuito " + (c || d)); // d nao avaliado
        System.out.println("OU bit a bit      " + (c |  d)); // d avaliado

        // ^ em boolean faz OU exclusivo
        System.out.println("OU exclusivo com boolean  " + (true ^ false));

    }

    static boolean sideEffect(String quem) {

        System.out.println("executando efeito colateral de  " + quem);
        return true;

    }

    // 4) Precedencia e parenteses
    static void precedenciaEParenteses() {

        System.out.println("\nPrecedencia e parenteses");

        // Ordem  ! tem maior prioridade  depois  &&  depois  ||
        boolean r1 = !true || true && false;       // equivale a  (!true) || (true && false)
        boolean r2 = (!true || true) && false;     // parenteses mudam o resultado
        System.out.println("sem parenteses  " + r1);
        System.out.println("com parenteses  " + r2);

        // Dica  quando a intencao nao estiver clara  use parenteses

    }

    // 5) Leis de De Morgan
    static void leisDeDeMorgan() {

        System.out.println("\nLeis de De Morgan");
        boolean A = true, B = false;

        boolean nAnd = !(A && B);
        boolean demorgan1 = !A || !B;

        boolean nOr = !(A || B);
        boolean demorgan2 = !A && !B;

        System.out.println("negacao de A E B  igual a  nao A OU nao B  " + (nAnd == demorgan1));
        System.out.println("negacao de A OU B igual a  nao A E nao B   " + (nOr == demorgan2));

        // Uso pratico  negar uma condicao composta trocando conectivos e negando atomos
        int idade = 15;
        boolean valido = idade >= 18 && idade <= 60;
        boolean invalido = !(idade >= 18 && idade <= 60);      // usando negacao direta
        boolean invalidoDM = idade < 18 || idade > 60;         // usando De Morgan
        System.out.println("invalido direto e invalido por De Morgan batem  " + (invalido == invalidoDM));

    }

    // 6) Padroes uteis com guard clauses e validacoes
    static void padroesGuardClauses() {

        System.out.println("\nPadroes com guard clauses e validacoes");
        // Guard clause  sai cedo quando a condicao invalida aparecer
        String token = "abc123";

        if (token == null || token.isBlank()) {
            System.out.println("token invalido");
            return;
        }

        if (!(token.length() >= 3 && token.length() <= 8)) {
            System.out.println("tamanho fora do intervalo");
            return;
        }

        System.out.println("token aceito  prosseguir");

        // Validacao composta legivel
        String email = "user@mail.com";
        boolean formatoOk = email.contains("@") && email.indexOf('@') < email.length() - 1;
        boolean naoNulo = Objects.nonNull(email);

        if (naoNulo && formatoOk) {
            System.out.println("email parece valido");
        }

    }

    // 7) Predicados e Streams  combinacao com and  or  negate
    static void predicadosEStreams() {

        System.out.println("\nPredicados e Streams");

        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);

        Predicate<Integer> par = n -> n % 2 == 0;
        Predicate<Integer> maiorQueTres = n -> n > 3;

        boolean temPar = nums.stream().anyMatch(par);
        boolean todosGrandes = nums.stream().allMatch(maiorQueTres);
        boolean nenhumNegativo = nums.stream().noneMatch(n -> n < 0);

        System.out.println("lista contem algum par  " + temPar);
        System.out.println("lista todos maiores que tres  " + todosGrandes);
        System.out.println("lista sem negativos  " + nenhumNegativo);

        // Composicao de predicados
        Predicate<Integer> parEGrande = par.and(maiorQueTres);
        Predicate<Integer> imparOuGrande = par.negate().or(maiorQueTres);

        System.out.println("algum par e maior que tres  " + nums.stream().anyMatch(parEGrande));
        System.out.println("algum impar ou maior que tres  " + nums.stream().anyMatch(imparOuGrande));

    }

    // 8) Armadilhas comuns
    static void pitfalls() {

        System.out.println("\nArmadilhas comuns");

        // Confundir & e | com && e ||  os primeiros sempre avaliam ambos os lados
        boolean erroCurto = false & sideEffect("lado avaliado por erro");
        System.out.println("uso de E bit a bit forca avaliacao do segundo  " + erroCurto);

        // Negacao mal posicionada
        String s = "abc";
        boolean errado = !s.isEmpty() && s.length() < 2;  // condicao impossivel
        System.out.println("exemplo de condicao incoerente  " + errado);

        // Precedencia  ! tem alta prioridade  use parenteses para clareza
        boolean confuso = !true || true && false;     // dificil de ler rapidamente
        boolean claro = !(true) || (true && false);   // clareza com parenteses
        System.out.println("expressao confusa   " + confuso);
        System.out.println("expressao clara     " + claro);

        // Ternario nao e operador logico  mas depende de uma condicao
        int idade = 17;
        String r = (idade >= 18 && idade < 60) ? "acesso liberado" : "acesso negado";
        System.out.println("resultado de condicao com ternario  " + r);

    }

}

