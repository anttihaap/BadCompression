# Viikko1

Huffmanin koodauksen ydin, eli puun muodostus ja merkkien uusien koodauksien luonti, on valmis. Yksittäisten bittien kirjoittaja ja lukia on valmiina.

Edellisellä viikolla pohdin seuraavaa ongelmaa. Jos esimerkiksi merkkin a uusi koodaus olisi 00 ja merkin x uusi koodaus olisi 001, niin miten tulkittaisiin tiedoston lopusta pätkä "00100000"? Jos viimeinen tavu ei täyty kokonaan, tiedostonkirjoittaja kirjoittaa loppuun nolla bittejä. Kyseisen jonon voisi tulkita a, ab tai abb. Päädyin lisäämään EOF-merkin Huffmanin koodaukseen. Pitää selvittää löytyykö tähän vähemmän tilaa vievää ratkaisua. 

Pakattuun tiedostoon täytyy myös kirjoittaa merkkien frekvenssit tai Huffmanin koodauksen puu. Tämän hetkinen suunitelma on kirjoittaa merkit peräkkäin ja sen jälkeen niiden esiintymätiheys, esim: ABC 1 2 3. Tätä täytyy vielä tutkia.

Seuraavalla viikolla lähden toteuttamaan ohjelman osaa, joka lukee tiedoston merkkien esiintymätiheyden, luo uuden koodauksen nyt valmiin luokan HuffmanCoding:n avulla ja kirjoittaa tiedoston syötteet uudestaan pakkaustiedostoon. Jos ehdin, toteutan myös purun. Aijon myös kirjoittaa nykyisille luokille lisää testejäl.

## Ajankäyttö:
n. 20h
