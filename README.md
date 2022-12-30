# ALD-WorldGenerator

## Popis aplikace
Aplikace po spuštění začne postupně generovat validní dlaždice, než bude zaplněna
celá hrací plocha (rozměr plochy se může odvíjet od složitosti herního systému, ale
alespoň 10x10). Algoritmus, kterým bude vybrána další dlaždice na vhodné místo je
na vás.
Algoritmus se ukončí po zaplnění celé hrací plochy. Může nastat situace, kde
program nenajde žádnou vhodnou dlaždici, která by pasovala do herního světa, v
tomto případě by bylo vhodné buď

1. skrze backtracking umístit předposlední dlaždici, tak aby současná byla
umístitelná
2. nebo změnit konfiguraci dlaždic a zkusit algoritmus znovu
