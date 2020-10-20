# Anagram

## Ausführen
Um die Anwendung zu starten, [anagram-collector.jar](https://github.com/usornalingam/anagrams/raw/master/target/anagram-collector.jar) herunterladen und über die CLI starten  

```bash
java -jar anagram-collector.jar "<Absoluten Pfad zur sample.txt>"
```

Unter Windows kann der absolute Pfad einer Datei ganz einfach über Rechtsklick  
auf die Datei und Reiter Sicherheit eingesehen und kopiert werden.

## Lösungsweg
Anagramme sind bekanntlich Wörter mit derselben Länge und denselben Buchstaben.  
Es ist also ein einfacher Lösungsweg, alle Buchstaben alphabetisch zu sortieren,  
und dann die gleichen "Strings" zusammenzuführen.  

Mit der Collectors#groupingBy lässt sich relativ einfach realisieren. Anschließend   
mussten die Ergebnisse nur zusammengeführt und für die Anzeige aufbereitet werden.  

## Bibliotheken
Ich bin ein Fan von Bibliotheken, aber wo man sie weglassen kann, sollte man sie  
auch weglassen.   

Diese Aufgabe kann ohne Drittanbieter-Bibliotheken implementiert werden. Mit  
Java 8 hat man sämtliche Werkzeuge dazu in der JDK.  

Die Vor- und Nachteile beim Einsatz von Bibliotheken müssen gut überdacht werden.  
Konkret in diesem Beispiel hab ich keinerlei Vorteile für den Einsatz von   
externen Bibliotheken gesehen - sondern eher die Nachteile, die Abhängigkeit  
zu Drittanbieter-Bibliotheken zu schaffen und diese in die Anwendung mit einzubeziehen  
und somit ihre Dateigröße zu erhöhen, als auch während der Laufzeit mehr Resourcen laden zu müssen.

## Architektur und Skalierbarkeit
Wenn wir von Skalierbarkeit reden, denke ich an erster Stelle an verteilte Systeme und verteilte Aufgaben.  
Falls man tatsächlich mal mit 1 Mrd. Wörter zu tun haben sollte, würde ich auf Microservices setzen,  
und die rechenintensive Aufgaben klar definieren und auslagern.  
  
Mein Lösungsweg umfasst z.B. das Umwandeln der einzelnen Strings in ein char-Array und deren alphabetische  
Sortierung. Dieser Schritt könnte von verschiedenen Microservices übernommen werden,  
um die Last einer 1 Mrd. Wörter großen Liste zu verteilen.  
  
Die große Liste sollte in kleinere Workloads heruntergebrochen und den entsprechenden Services bereitgestellt werden (z.B.  
über MQs). Sobald die Services durch sind, schicken sie eine entsprechende Mitteilung mit den Ergebnissen in eine MQ des Empfängers.  
Sobald die gesamten "Workloads" verarbeitet wurden, arbeitet der Hauptprozess weiter.  

