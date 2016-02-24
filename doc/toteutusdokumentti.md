# Toteutusdokumentti

## HuffmanCoding

### HuffmanCoding - luokka

Tarkempi kuvaus Huffmanin koodauksesta määrittelydokumentissa.

Sisältää pakkauksen ja purun ytimen: Huffmanin koodauksen luominen käyttäen merkkien esiintymätiheyttä. Koodauksen luonnin jälkeen, voidaan yksittäisen merkin koodaus hakea taulukosta.

Kaikissa tapauksissa n on merkkien määrä. UTF-8:ssa merkkejä on 1114112 ja tavuissa 256 merkkiä.

Toteutuksessa käytetään **prioriteettijonoa**, joka taas käyttää **kekoa**. Prioriteettijono palauttaa aina pienimmän alkion. Pussa käytetään alkioina HuffmanTreeNode-olioita. Kekoon lisääminen/poistaminen aikavaativuus: **O(logn)**, koska prioriteettijono käyttää toteutuksessaan kekoa, sillä on sama aikavaativuus.

* createHuffmanTree(long[] charFreq) **O(nlogn)**
  * täytetään pioriteettijonoon esiintymätiheydet. **O(n log n)**
  * haetaan 2 pienintä alkiosta prioriteettijonosta, kunnes prioriteettijonossa on vain yksi jäljellä,yhdistetään ne jonoon uudeksi solmuksi. Hakuja on n kappaletta. PriorityQueue:hin lisäys/poisto O(log n). **O(n log n)**
  * luodaan String[] taulukko, johon lisätään jokaisen merkin kohdalle oma koodaus. Etsiminen tapahtuu käymällä koko puu läpi. **O(n)**

## Tietorakenteet

### Binäärikeko (MinBinaryHeap.java)

Binäärikeon aikavaativuudet:

|operaatio|aikavaativuus|
|---|---|
|peekMin|O(1)|
|removeMin|O(log n)|
|insert|O(log n)|
|expandArray|O(n)|

Binäärikeon puu on toteuttettu käyttäen taulukkoa. Tästä syystä operaation expandArray aikavaativuus on O(n). Keon luontivaiheessa luodaan 100 alkion pituinen taulukko. Pidetään kirjaa suurimmasta indeksistä jota käytämme. Kun taulukosta loppuu tila, luodaan uusi taulukko, joka on 2 kertaa suurempi kuin edellinen ja kopioidaan siihen vanhat alkiot.

Taulukon ensimmäinen indeksi on 0. Solmun lapset ja vanhempi löytyy seuraavasti.
* *Lapset:* Solmun, jonka indeksi on i, vasen lapsi löytyy indeksistä 2*i+1 ja oikea lapsi indeksistä 2*i+2. Jos lapsien idenksi on yhtäsuuri tai suurempi kuin taulukosta suurin käytetty indeksin koko, lasta ei ole olemassa.
* *Vanhempi:* Solmun, jonka indeksi on i. Vanhempi löytyy indeksistä floor(i-1)/2. Puun juurelta, eli solmulta jonka indeksi on 0, ei löyty vanhempaa.

Operaatiot:

* *Lisääminen:* Lisätään uusi alkio käytetyn taulukon loppuun. Verrataan uuden solmun vanhempaa, jos vanhempi on suurempi, vaihdetaan alkioiden paikkoja. Tehdään sama solmun vanhemmalle, kunnes vanhempi on pienempi. Ohjelmassa kyseinen vertailu on toteutettu rekursiivisesti.

* *Poistaminen:*  Tallennetaan taulukon alkio taulukon ensimmäisestä indeksistä muuttujaan. Vaihdetaan taulukon viimeinen alkio ensimmäiseksi ja vähennetään suurinta käytettyä indeksiä yhdellä. Vertaillaan alkion lapsia. Jos lapsista jompi kumpi on suurempi kuin nykyinen solmu vaihdetaan pienimmän ja nykyisen solmun alkiot keskenään. Tehdään sama vertailu pienimmän lapsen solmun kohdalla. Jatketaan rekursiivisesti kunnes solmulla ei ole lapsia tai lapset ovat suurempia kuin nykyinen solmu. Lopuksi palautetaan alussa tallennettu muuttuja, eli keon pienin alkio.

** [TODO: kuvia] **
