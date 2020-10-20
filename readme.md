# Anagram

## Lösungsweg
Anagramme sind bekanntlich Wörter mit derselben Länge und denselben Buchstaben.
Es ist also ein einfacher Lösungsweg, alle Buchstaben alphabetisch zu sortieren, 
und dann die gleichen "Strings" zusammenzuführen.

Mit der Collectors#groupingBy lässt sich relativ einfach realisieren. Anschließend 
mussten die Ergebnisse nur zusammengeführt und für die Anzeige aufbereitet werden.

## Bibliotheken
Ich habe bewusst auf keine Bibliothek zurückgegriffen.
### apache commons-io 2.8.0

