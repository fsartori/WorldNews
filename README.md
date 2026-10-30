# World-News

Questa applicazione sarà un client per poter visualizzare dati recuperati da [News API](https://newsapi.org)

## Esercitazione 3 (22 ottobre)

In questa esercitazione abbiamo creato il progetto World News da zero. Abbiamo importato un foglio di stile da [questo sito](https://material-foundation.github.io/material-theme-builder/) e in seguito è stata creata la pagna iniziale `PickACountryActivity`

È stata modificata l'Activity di partenza da `MainActivity` a `PickACountryActivity` modificando il file `manifests/AndroidManifest.xml`(spostando il blocco <intent>)

`PickACountryActivity` utilizza un ConstraintLayout per mostrare una `TextView` e un `LinearLayout`, all'interno del quale sono presenti due `CountryCard`.

La `CountryCard` è una card definita nel file `res/layout/country_card.xml` a partire dalle linee guida che potete trovare in [in questo link](https://github.com/material-components/material-components-android/blob/master/docs/components/Card.md)

## Esercitazione 4 (29 ottobre)

In questa esercitazione abbiamo concluso la pagina `PickACountryActivity` e creato le pagine `PickCategoriesActivity` e `LoginActivity`, impostando la gestione di alcuni aventi

per le view a aprtire dal codice java. Il lancio dell'applicaizone è stato fissato sull'activity di login.

Le tre pagine serviranno per fare il login, e successivamente scegliere una nazione di riferimento e una serie di categorie.
