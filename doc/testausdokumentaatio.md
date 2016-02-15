# Testausdokumentaatio

## Pakkauksen ja purun testaaminen:
Tiedostojen purkua ja pakkausta testataan satunnaisgeneroiduilla tiedostoilla ja internetistä varastetuilla testitiedostoilla. Alkuperäinen tiedosto pakataan ja sen jälkeen puretaan toiseen tiedostoon. Alkuperäisestä ja purettua tiedostoa vertaillaan MD5-hashillä. Jos hashit täsmäävät, tiedosto on pakattu ja purettu onnistuneesti (mitä suurimmalla todennäköisyydellä).

Tavuja tunnistavan Huffmanin koodauksen tastaus on helppoa generoimalla tiedosto satunnaistavuilla.

Toteutetaan ehkä ohjelma bash/java:lla joka hakee satunnaisen wikipedia artikkelin ja testaa pakkausta/purkua.

## Huffmanin koodaus UTF8 vs. tavut

Tarkoituksena toteuttaa vertailua, miten UTF8 merkkejä tunnistava Huffmanin koodaus pärjää tavuja tunnistavaa vastaan. Erityisesti kielillä, joissa on yli yksi tavuisia utf-8 merkkejä, tulosten pitäisi näkyä.

Esimerkki kokeilusta: Wikipedian Japaninkielinen artikkeli pakkautui UTF-8 tunnistavalla 268KB tiedostosta 120KB tiedostoksi. Tavuja tunnistava pakkasi sen 192KB:ksi.

### Mahdollisia testejä toteutettavaksi:

* eri tiedostotyyppien testailua, mikä pakkautuu parhaiten? Mitä uultavimmin luonnollinen kieli ja satunnaiset tiedostot eivät pakkaannu ollenkaan.
* miten utf-8 koodaus vaikuttaa eri kielten pakkautumiseen
* tiedostojen kokojen verailu ja ajankäyttö
