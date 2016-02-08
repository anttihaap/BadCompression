Tällä viikolla tuli kiire saada koodi ja testit kauniiksi palautusta varten. Javadocin kirjotitaminen jäi kesken. Työ on kuitenkin edistynyt mielestäni hyvin. Tavuja tunnistava Huffmanin koodaus on valmis. Ohjelman rakenteesta en ole täysin varma ja testit laahaavat vähän perässä. Tiedostojen pakkaus näyttää toimivan testien mukaan, toivottavasti myös oikeasti. Ainakin se tuntui toimivan ennen rakennemuutoksia ja testien kirjoittelua. Pakkasin ja purin testisyötteellä, jonka jälkeen vertailin alkuperästä tiedostoa ja ohjelman luomaa tiedostoa MD5-hash:llä. UTF-8 tiedostojen pakkaus ja purun toteuttaminen nykyisen päälle tuntuisi sujuvan nopeasti nyt tulevalla viikolla.

Loin rajapinnan "EncodedFile", jonka avulla Huffmanin koodaus osaa lukea UTF-8 koodattuja tiedostoja ja kaikkia tiedostoja tavu kerrallaan.

Tulevana viikolla mietin ohjelman rakennetta. Kirjoitan testejä ja javadokkia lisää. Tämän jälkeen toteutan UTF-8 koodattujen tiedostoja tunnistavan pakkaus/purku -luokan. En ole aivan varma miten ohjelma tunnistaa pakkauksen jälkeen UTF-8 koodatut tiedostot muista. Tätä pitää pohtia. Keon toteuttaminen olisi syytä aloittaa pian.

Ajankäyttö: n. 20h
