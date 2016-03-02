# Viikko 6

En tällä viikolla ehtinyt edistämään työtäni paljoakaan kiireiden vuoksi. En saanut kirjoitettua dokumentaatio mielestäni tarpeeksi ennen DL. Mainin toteuttaminen jäi kesken. Ohjelmaa olisi tarkoitus käyttää parametrien avulla päätteessä. Pitänee ottaa loppukiri loppupalautukseen.

Tällä viikolla: Refraktoroin testejä. Nyt mm. utf8-tiedostojen testaus on helpompaa, koska testit lukevat tiedostot suoraan testitiedostojen kansiosta. Testailin tallentaa koko Huffmanin koodauksen luokan tiedostoon frekvenssien kirjoittamisen sijaan. Kokeilin myös huvikseni tallentaa frekvenssitalukon long[] tiedostoon. Molemmat olivat tehotomampia verrattuna nykyiseen ratkaisuun. Ei mikään yllätys ainakin long[] listan kohdalla. Tutustuin myös "canonial Huffman code":iin tällä viikolla. Toivottavasti ehdin sen vielä toteuttamaan tulevana viikkona, sillä se vaikuttaisi olevan selkeästi vähemmän tilaa vievä ratkaisu verrattuna nykyiseen. Korjasin hieman ohjelman rakennetta Exceptioneiden suhteen. Sain hyviä ehdotuksia vertaisarvioinnissa.

Lopulliseen palautukseen toteutus/testausdokumentit kuntoon ja ohjelman rakenteen siistiminen. Käyttöohjeiden kirjoitus. Rakenteen viilaamista: Esimerkiksi HuffmanCompressionByte/Utf8 sisältävät liikaa toistoa. Myös Mainin rakenne paremmaksi jne...

## Ajankäyttö:
n. 5h
