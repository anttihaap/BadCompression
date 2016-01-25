# Määrittelydokumentti

Aiheena UTF-8:llä koodattujen tekstitiedostojen pakkaus Huffmanin koodauksella. Tavoitteena on päästä UTF-8 koodauksella tiiviimpään pakkaukseen kielillä, jotka sisältävät paljon yli yksitavuisia UTF-8 merkkejä. Mikäli aikaa riittää vertailen työssäni tavuja ja UTF-8 merkkejä tunnistavia Huffman-koodauksia.

Työsssä käytetään Java-ohjelmointikieltä. Ohjelma saa syötteenä tiedoston pakkausta tai purkua varten. Purettu tai pakattu tiedosto kirjoitetaan käyttäjän antamaan kohteeseen.

## Huffmannin koodaus

Huffmanin koodaus tarkastelee merkkien esiintymistiheyttä. Perusajatuksena on, että kielessä jotkin merkit esiintyvät useammin ja näin useimmiten esiintyvät merkit voidaan esittää käyttäen lyhyempiä bittijonoja, kuin vähiten esiintyvissä. Tämä onnistuu etuliitekoodauksella, eli minkään koodauksessa käytetty bittijono ei ole minkään toisen bittijonon etuliite.

#### Algoritmin toiminta:

Pakkaaminen:

* Luetaan tiedosto merkki kerrallaan. Kasvatetaan esiintymistaulukkoa yhdellä merkin kohdalta.

* Luodaan jokaiselle merkille puu alkio, joka lisätään minimikekoon. Painona merkin esiintymien lukumäärä.

* Luodaan Huffmanin koodaukseen tarvittava puu. Valitsemalla aina 2 pienintä solmua minimikeosta ja ydistämällä ne uudeksi solmuksi, joka lisätään takaisin kekoon. Uuden solmun paino on yhdistettyjen solmujen painojen summa. [Tässä animaatio puun luomisesta.](https://en.wikipedia.org/wiki/File:Huffman_huff_demo.gif) (lähde: wikipedia)

* Käydään koko puu läpi. Alimman solmun kohdalla merkitään merkille uusi koodaus. Merkin koodaus muodostuu puussa liikkumisesta: jokainen vasemmalle siirtyminen lisää 0-bitin ja jokainen oikealle 1-bitin bittijonoon.

* Tallennetaan pakkaustiedostoon esiintymätiheys-taulukko. Esim. ABC,3,2,1#-muodossa, eli A-merkkiä esiintyy 3 jne. .

* Luodaan pakattu tiedosto kirjoittamalla jokaisen merkin koodaustaulukosta binäärijono tiedostoon.

Purku:

* Luetaan tiedoston alusta tiheystaulukko.

* Luodaan tiheystaulukkoa käyttäen Huffmanin koodauksen puu kuten pakkauksessa.

* Luetaan tiedosto bitti kerrallaan ja edetään puussa, kuten pakkauksessa, ja kun päädytään puussa solmuun, jossa on merkki alkiona, niin kirjoitetaan puussa läpikäynnin yhteydessä luettu bittijono tiedostoon.

## Aikavaativuus ja tilavaativuus

Algoritmin aikavaativuus on O(m + nlogn), jossa m on tiedoston bittien lukumäärä ja n tiedoston merkkien uniikkien merkkien lukumäärä.

Algoritmi toimii itsetoteutettujen algoritmien ja tietorakenteiden osalta O(nlogn), jossa n on tiedoston uniikkien merkkien määrä. Jokaisen merkin lisäys ja poisto minimikekoon vie O(nlogn). Huffman-koodaukseen tarvittavan puun luonti tapahtuu O(n) ja koodaustaulukon luonti tapahtuu lukemalla koko puu läpi tapahtuu O(n). Koodauksen siirtäminen puusta taulukkoon tapahtuu rekursiolla käymällä koko puu läpi.

Tilavaativuus on O(n), jossa n on tiedoston uniikkien merkkien lukumäärä. Algoritmi kirjaa jokaisen uniikin merkin kohdalla vakiomääräisen määrän tietoa (uuden kodauksen).

## Tietorakenteet työssä

Huffmannin koodauksen puun luonnissa käytetään **minimikekoa.** Minimikeon aikavaativuudet lisäämiselle ja poistamiselle O(logn). Huffmannin koodauksen puu käyttää luonnollisesti **binääripuuta**.

## Lähteet:
* Huffmannin koodaus
  * http://www.cprogramming.com/tutorial/computersciencetheory/huffman.html
  * http://www.utdallas.edu/~daescu/huffman.ppt
  * https://en.wikipedia.org/wiki/Huffman_coding
  * wikipedian animaatio: https://en.wikipedia.org/wiki/File:Huffman_huff_demo.gif

* UTF-8
  * http://www.fileformat.info/info/unicode/utf8.htm
  *
  http://www.utf8-chartable.de/
t
