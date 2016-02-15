# Viikko 4

UTF8 tunnistava Huffmanin koodaus on jokseenkin valmis. En kuitenkaan tällä viikolla ehtinyt aloittaa tietorakenteiden totutusta, koska UTF-8 koodauksen kirjoitus ja luku on aiheuttanut yllättävän paljon päänvaivaa. Myöskin ohjelmassa esiintyvän virheen metsästys on vienyt aikaa. Myös testejä kirjoittaessa on tullut ongelmia, esimerkiksi: UTF8Writer testiä kirjoittaessa törmäsin ongelmaan missä tekstieditorini kirjoittaa linefeedin tiedoston loppuun, jonka takia md5-hashit erosivat. Tämän näkeminen mm. meni yllättävän kauan, vaikka ongelma oli yksinekrtainen.

Ohjelmassa on jokin bugi/bugeja liittyen UTF-8 koodauksen käsittelyyn. Tämä näkyy kirjoittaessa testejä erittäin suuriarviosille UTF-8 merkeille. Testit menevät satunnaisesti läpi satunaisgeneroidulla UTF-8 tiedostolla. En ole vielä ehtinyt tutkimaan sen tarkemmin. Pakkaus ja purku toimii kuitenkin kaikilla UTF-8 koodatuilla syötteillä, joilla olen kokeillut. Toisaalta tavuja tunnistava Huffmanin koodaus selviää satunnaisgeneroidusta syötteestä mallikkaasti. Tätä kirjoittaessa tajusin, että ongelma saattaa löytyä FrequencyIO-luokasta. String <-> long muunnoksissa saattaisi olla jotain vikaa.

HuffmanCompressionByte ja HuffmanCompressionUTF8 -luokat sisältävät toistoa. Tätä tulisi refraktoroida tulevana viikolla. Omien tietorakenteiden toteutus olisi syytä aloittaa heti. Yritän myös etsiä UTF-8 koodaukseen liittyvää bugia ensi viikolla.

## Ajankäyttö:

n. 15h
