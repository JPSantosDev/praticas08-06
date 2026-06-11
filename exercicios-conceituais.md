# Exercícios de Interpretação de Código

## Exercício 1

```kotlin
val nomes = listOf("Ana", "Bruno", "Ana", "Carla")
val unicos = nomes.toSet()
println(unicos.size)
```

**Saída: `3`**

`toSet()` converte a lista em um conjunto, que não permite elementos duplicados. O valor "Ana" aparece duas vezes na lista, mas no Set ele é armazenado uma única vez. Portanto, o conjunto final contém "Ana", "Bruno" e "Carla", resultando em tamanho 3.

---

## Exercício 2

```kotlin
data class Curso(val id: Int, val nome: String)
val cursos = listOf(
    Curso(1, "Kotlin"),
    Curso(1, "Compose"),
    Curso(2, "Room")
)
val mapa = cursos.associateBy { it.id }
println(mapa[1])
```

**Saída: `Curso(id=1, nome=Compose")`**

`associateBy` transforma a lista em um Map usando o campo informado como chave. Quando dois elementos têm a mesma chave, o último sobrescreve o anterior. Como `Curso(1, "Kotlin")` e `Curso(1, "Compose")` têm o mesmo id, o "Compose" substitui o "Kotlin" no mapa. Ao acessar `mapa[1]`, o resultado é o segundo elemento.

---

## Exercício 3

```kotlin
val notas = listOf(8.0, 6.5, 9.0, 5.0)
val resultado = notas
    .filter { it >= 7.0 }
    .map { it + 1.0 }
println(resultado)
```

**Saída: `[9.0, 10.0]`**

O `filter` mantém apenas as notas maiores ou iguais a 7.0, resultando em `[8.0, 9.0]`. O `map` soma 1.0 a cada valor restante, produzindo `[9.0, 10.0]`. As notas 6.5 e 5.0 são descartadas ainda no `filter` e não chegam ao `map`.

---

## Exercício 4

```kotlin
fun nomeCurso(cursos: List<Curso>, id: Int): String {
    return cursos.find { it.id == id }!!.nome
}
```

**Problema: uso do operador `!!` sem verificação prévia.**

`find` retorna `null` quando nenhum elemento satisfaz a condição. O operador `!!` força o acesso ao valor sem checar se ele é nulo. Se nenhum curso com o `id` informado existir na lista, o app lança uma `NullPointerException` e crasha em tempo de execução.

A correção correta é tratar o nulo com segurança:

```kotlin
fun nomeCurso(cursos: List<Curso>, id: Int): String {
    return cursos.find { it.id == id }?.nome ?: "Curso não encontrado"
}
```

O operador `?.` acessa `.nome` apenas se o resultado não for nulo, e o `?:` fornece um valor padrão caso seja.

---

## Exercício 5

```kotlin
val ranking = resumos.sortedWith(
    compareByDescending<CursoResumo> { it.mediaFinal }
        .thenBy { it.nome }
)
```

**Em caso de mesma média, aparece primeiro o curso com nome em ordem alfabética menor.**

A ordenação funciona em duas etapas. Primeiro, `compareByDescending` ordena pela `mediaFinal` do maior para o menor, então quem tem média mais alta aparece antes. Quando dois cursos têm exatamente a mesma média, o critério de desempate entra em ação: `thenBy { it.nome }` ordena pelo nome em ordem alfabética crescente, ou seja, "Android" apareceria antes de "Kotlin", que apareceria antes de "Room".
