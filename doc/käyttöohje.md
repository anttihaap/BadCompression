# Käyttöohjeet

Ohjelmaa ajetaan komentorivillä. (tl;tr: lue esimerkit kohta)

Huomioitavaa: Tiedoston polku tulee olla koknaisuudessaan. Esim. /home/user/pakattavatiedosto.txt riippumatta siitä missä kansiossa olet komentorivillä. Ohjelma myös tarkistaa, pakkauksen ja purun yhteydessä, ettei tiedostoa, jota ohjelma luo, ole jo olemassa.

## Tiedoston pakkaaminen:

Ohjelma pakkaa tiedoston samaan kansioon missä pakattava tiedosto on. Esimerkiksi jos tiedosto testi.txt pakataan tavuja tunnistavalla Huffmanin koodauksella uusi tiedosto testi.txt.badComp luodaan. UTF-8 tunnistavalla Huffmanin koodauksella pakattu tiedosto tallentuu nimellä testi.txt.utf8.badComp

## Tiedoston purkaminen

Ohjelma purkaa tiedoston samaan kansioon ja tunnistaa onko tiedosto pakattu UTF-8 tunnistavalla vai tavuja tunnistavalla koodauksella tiedoston nimestä. Esimerkiksi tiedosto test.txt.badComp purkautuu alkuperäiseen test.txt tiedoston nimeensä. UTF8 tunnistavalla Huffmanin koodauksella tiedosto test.txt.utf8.badComp purkautuu test.txt tiedostoksi.

## Esimerkkejä:

* Pakataan tiedosto test.txt tavuja tunnistavalla Huffmanin koodauksella:
 * java -jar BadComp.jar comp /home/user/test.txt
* Pakataan tiedosto txt.txt UTF-8 merkkejä tunnistavalla Huffmanin koodauksella:
 * java -jar BadComp.jar comp utf8 /home/user/test.txt
* Purku tapahtuu aina komennolla uncomp:
 * java -jar BadComp.jar uncomp /home/user/test.txt.badComp.
 * java -jar BadComp.jar uncomp /home/user/test.txt.utf8.badComp
