package fundamentos.wrappers;

/*
- WrappersResumo.java

- Guia prático dos WRAPPERS em java:
    - Primitivos: byte, short, int, long, float, double, char, boolean
    - Wrappers: Byte, Short, Integer, Long, Float, Double, Character, Boolean

- Por que existem?
    - Coleções/Generics só aceitam OBJETOS (ex.: List<Integer>).
    - APIs que exigem referência (podem carregar null).
    - Métodos utilitários estáticos (parse/compare/valueOf/etc.)

- Atenções:
    - Autoboxing/unboxing pode causar NPE (unboxing de null) e custo no hot path.
    - '==' compara IDENTIDADE (mesmo objeto) — prefira equals() para conteúdo.
    - Cache de wrappers: Integer/Byte/Short/Long/Character (ASCII), Boolean.
 */

public class WrappersResumo {
}
