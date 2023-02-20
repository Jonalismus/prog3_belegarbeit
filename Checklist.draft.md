# Beleg Beispiel (94)
Checkboxen befüllen und _kursiv_ gesetzten Text durch entsprechende Angaben ersetzten.
Bei keiner Angabe wird nur Entwurf, Testqualität, Testabdeckung GL, Fehlerfreiheit und Basisfunktionalität bewertet.
Die Zahl in der Klammer sind die jeweiligen Punkte für die Bewertung.
Die empfohlenen Realisierungen zum Bestehen der Prüfung sind **fett** gesetzt.
Ergänzende Anmerkungen bitte immer _kursiv_ setzen. Andere Änderungen sind nicht zulässig.

## Entwurf (11)
- [ ] **Schichtenaufteilung** (2)
- [ ] **Architekturdiagramm vollständig** (1)
- [ ] **Architekturdiagramm korrekt bzgl. source code** (1)
- [ ] Zuständigkeit (2)
- [ ] Paketierung (2)
- [ ] Benennung (2)
- [ ] keine Duplikate (1)

## Tests (28)
- [ ] **Testqualität** (7)
- [ ] **Testabdeckung GL** (7) _Abdeckung in Prozent angeben_
- [ ] **Testabdeckung Rest** (6)
  - [ ] Einfügen von Herstellern über das CLI _getestete Klassen angeben_
  - [ ] Anzeigen von Herstellern über das CLI _getestete Klassen angeben_
  - [ ] ein Beobachter bzw. dessen alternative Implementierung _getestete Klassen angeben_
  - [ ] deterministische Funktionalität der Simulationen _getestete Klassen angeben_
  - [ ] Speichern via JOS oder JBP _getestete Klassen angeben_
  - [ ] Laden via JOS oder JBP _getestete Klassen angeben_
- [ ] **Mockito richtig verwendet** (4)
- [ ] Spy- / Verhaltens-Tests (3)
- [ ] keine unbeabsichtigt fehlschlagenden Test (1)

## Fehlerfreiheit (10)
- [ ] **Kapselung** (5)
- [ ] **keine Ablauffehler** (5)

## Basisfunktionalität (12)
- [ ] **CRUD** (2)
- [ ] **CLI** (2)
  * Syntax gemäß Anforderungen
- [ ] **Simulation** (2)
  * ohne race conditions
- [ ] **GUI** (2)
- [ ] **I/O** (2)
  * in CLI oder GUI integriert
- [ ] **Net** (2)

## Funktionalität (23)
- [ ] vollständige GL (2)
- [ ] threadsichere GL (1)
- [ ] vollständiges CLI (1)
- [ ] alternatives CLI (1)
  * _angeben welche Funktionalität im alternativen CLI deaktiviert_
- [ ] skalierbare GUI (1)
- [ ] vollständige GUI (1)
- [ ] ausdifferenziertes event-System mit mindestens 3 events (2)
- [ ] observer ^ property change propagation (2)
- [ ] angemessene Aufzählungstypen (2)
- [ ] Simulation 2 (1)
- [ ] Simulation 3 (1)
- [ ] data binding verwendet (1)
- [ ] Änderung der Fachnummer mittels drag&drop (1)
- [ ] Einfügen von Kuchen via GUI sperrt nicht (1)
- [ ] JBP und JOS (2)
- [ ] TCP und UDP (1)
- [ ] Server unterstützt konkurierende Clients für TCP oder UDP (2)

## zusätzliche Anforderungen (10)

## Architekturdiagramm
![Architekturdiagramm](architecture.png)