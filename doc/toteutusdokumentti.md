# Toteutusdokumentti

### HuffmanCoding - luokka

Tarkempi kuvaus Huffmanin koodauksesta määrittelydokumentissa.

Sisältää pakkauksen ja purun ytimen: Huffmanin koodauksen luominen käyttäen merkkien esiintymätiheyttä. Koodauksen luonnin jälkeen, voidaan yksittäisen merkin koodaus hakea taulukosta.

Kaikissa tapauksissa n on merkkien määrä. UTF-8:ssa merkkejä on 1114112 ja tavuissa 256 merkkiä.

Toteutuksessa käytetään **prioriteettijonoa**, joka taas käyttää **kekoa**. Prioriteettijono palauttaa aina pienimmän alkion. Pussa käytetään alkioina HuffmanTreeNode-olioita. Kekoon lisääminen/poistaminen aikavaativuus: **O(logn)**, koska prioriteettijono käyttää toteutuksessaan kekoa, sillä on sama aikavaativuus.

* createHuffmanTree(long[] charFreq) **O(nlogn)**
  * täytetään pioriteettijonoon esiintymätiheydet. **O(n log n)**
  * haetaan 2 pienintä alkiosta prioriteettijonosta, kunnes prioriteettijonossa on vain yksi jäljellä,yhdistetään ne jonoon uudeksi solmuksi. Hakuja on n kappaletta. PriorityQueue:hin lisäys/poisto O(log n). **O(n log n)**
  * luodaan String[] taulukko, johon lisätään jokaisen merkin kohdalle oma koodaus. Etsiminen tapahtuu käymällä koko puu läpi. **O(n)**
