# Viikko1

Aloitin alkuviikosta aiheenvalinnan pohtimisen. Päädyin tutustumaan tiedoston pakkaukseen, koska en tästä aiheesta tunne juuri mitään. Aihe vaikutti mielenkiintoiselta ja päädyin valitsemaan Huffmanin koodauksen sen selkeyden vuoksi.

Tutustuessani Huffmanin koodaukseen, joka vertailee tavujen esiintymistiheyttä, tutustuin myös UTF-8-merkkeihin. Ajatuksena olisi lukea UTF-8-merkkien esiintymistiheyttä tavujen esiintymistiheyden sijaan, koska mitä luultavimmin UTF-8 merkit tiivistyisivät paremmin kielillä, joilla merkit ovat keskimäärin 2 tavua tai enemmän, verrattuna perinteiseen Huffmannin koodaukseen, joka tarkastellaan tavujen esiintymistiheyttä. Tätä etua ei ole kielillä, jotka voidaan esittää ASCII-merkein, sillä UTF-8 tunnistaa ASCII-merkit. Molempien tapojen toteutus ja vertailu, voisi olla hyvä laajennus työlle, mikäli aikaa riittää.

[Määrittelydokumentti](https://github.com/anttihaap/BadCompression/blob/master/doc/M%C3%A4%C3%A4rittelydokumentti.md)

Tutustuin Javan tiedoston kirjoitukseen ja lukuun. Merkkien lukeminen Javalla onnistuu helposti vakiokirjastoilla, mutta yksittäisten bittien kirjotittaminen ei. Tein nopeasti luokan tätä varten, joka kerää bitit yksitellen ja kirjoittaa tavu kerrallaan. Mikäli tiedoston viimeinen tavu ei täyty kokonaan, täytetään tavu nollilla. Tästä muodostuu ongelma: mikäli jokin merkki on koodattu vain 0-biteillä, purku voi luulla tiedoston lopussa olevan jokin kirjain. Huffmannin koodauksen puuhu tulee ehkä lisätä EOF-bittijono? Tätä pitää tutkia lisää.

Seuraavaksi aloitan Huffmanin koodauksen algoritmin ytimen toteuttamisen.

## Ajankäyttö:
n. 5h
