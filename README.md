# ALD-WorldGenerator

## Popis aplikace
Aplikace po spuštění začne postupně generovat validní dlaždice, než bude zaplněna
celá hrací plocha (rozměr plochy se může měnit, dafalutně nastaven na 10x10). Algoritmus, kterým bude vybrána další dlaždice na vhodné místo je
na vás. Algoritmus se ukončí po zaplnění celé hrací plochy.

## Popis fungování
Celý program funguje tak, že projíždí matici a na každou pozici vybírá náhodný obrázek a následně kontroluje, jestli tam obrázek sedí. Pokud tam obrázek nesedí, tak opět náhodně vybere další obrázek a zkusí, jestli tam sedí, to se opakuje do té doby, dokud tam obrázek nepasuje a jde na další pozici v matici. Matice se plní po řádcích. Zvolili jsme 2 barvy - černou a šedou a od nich vytvořili sérii zatáček, rovných čar, startů, dvojitých zatáček a prolnutí s druhou barvou.

## Ukázka výstupů

![vysledek2](https://user-images.githubusercontent.com/100779510/210067369-3537d042-0b8d-4a8a-a005-b6b0135605ff.png)

![vysledek1](https://user-images.githubusercontent.com/100779510/210067379-58576c24-ffa0-4b1d-aa97-ec02d5d3a431.png)

![vysledek](https://user-images.githubusercontent.com/100779510/210067383-e0aae2eb-7b21-4ec1-a996-99683a17f6ba.png)
