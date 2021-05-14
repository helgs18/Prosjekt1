Prosjekt IKT205 av Helge Stegemoen

Huskeliste

This is a todolist app, that you can use to keep tracks of which elements in the list that you have finished. When you have done one of the actions specified in a todolist, you can mark it completed.

Kommentarer som jeg la til på innleveringen i Canvas:
Fikk diverse problemer å hente json, så jeg måtte bare gå tilbake til eldre versjon. (Fikk ikke til å hente ut TodoItems, med å bruke kun en json-fil). Rakk ikke å implementere henting av json fil fra Firebase. Prøvde å sette opp en alternativ løsning ved å hente json fra Github, men den feilet pga. context fixen jeg la til. Du finner funksjonen i DepositoryManageren. APK filen ligger i roten på prosjekt1 mappen.



Knotsandcroses

Dette er en 3 på rad app. 

Fikk ikke gui ferdig. Klarte å få til en å opprette spill, joine, oppdatere og poll, men ikke å koble det til GUI. Fikk nettverksproblemer på slutten, så det gjorde det litt vanskeligere.

Create knappen, skal fungere med å opprette spill, og det er lagt til intent for gameId, slik at gamestate skal kunne hentes fra GameActivity. 

Join knappen skal også fungere og her bruker intenten gameId-en som den får fra teksten om skrives inn i dialog boksen.

Har en poll request knapp i ActivityMain, som blir kjørt fra main, og som kan testes med breakpoint. Før du teter, må du sette inn gameId på linje 45 i MainActivity. På linje 67-80 i MainActivity.kt kan det sette breakpoint for å sjekke poll funkjonen.
På linje 149-185 i GameService.kt ligger koden for requesten. Her sleit jeg med å få hentet ut gameState, pga. Gson() kunne ikke konvertere til responsen fra serveren til mitt gameobjekt. Så jeg valgte å dele opp json stringen, og hente ut players og state hver for seg, og fikk konvertert de til objekter. Deretter loopet jeg igjennom objektene for players og state, slik at jeg fikk lagt de til i String array for players og GameState for staten.

Skulle prøve å få Join og create knappene til å fungere med å skrive ut state, men får ikke testet de nå etter siste endringer pga. nettverksproblemer med koden og nå får jeg ikke tid til å skrive de ferdig. Dette kulle jeg prøve å få til etter å ha laget en poll funksjonalitet.
Feilmeldingen jeg fikk er: E/Volley: [3576] NetworkUtility.shouldRetryException: Unexpected response code 404 for https://generic-game-service.herokuapp.com/Game/1qiq5/poll. Her hadde jeg kun koblet gameId til knapp00, slik at gameId fungerer etter å ha opprettet spill. Dette gjorde jeg med bruk av intent.

Oppsummering

Det blei mye jeg måtte lære meg etter hvert, og følte at mye av tiden i begynnelsen gikk vekk til vas. Men etter hvert med litt testing, og prøve å dele det opp i enklere deler så fikk jeg løst noen av problemene. Jeg måtte også søke etter litt hjelp angående json og intent, for å få til det grunnleggende. Selve gui delen sparte jeg, men la til noe koden i GameActivity, slik at det ville være klart, dersom jeg kom så langt at jeg kunne begynne å koble spillet til interfacet.

Det jeg sleit mest med var å få requestene til å fungere, men etter litt blanding testing av koden i Swagger og Curl, klarte jeg å få til requester.
Jeg sleit også mye med å få vist verdier for spill i GameActivity, men fikk etter hvert løst det med intent.
Json koden var også et stort problem, men fikk etter hvert løst det på et vist. Det som jeg sleit mest med her, var poll koden.


Når det gjelder oppsummering for huskeliste, så får jeg ikke det med her, pga. det er en stund siden vi leverte dette prosjektet.
