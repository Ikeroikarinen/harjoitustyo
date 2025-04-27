# Harjoitustyö: Lutemon Battle App

## 1. Yleiskuvaus
Tämä Android-sovellus toteuttaa Lutemon-pelimallin, jossa käyttäjä voi:
- Luoda ja nimittää erilaisia Lutemoneja (viisi tyyppiä: White, Green, Pink, Orange, Black)
- Treenata Lutemoneja ja seurata niiden kokemuspisteitä
- Valita kaksi Lutemonia taistelua varten (vuoropohjainen taisteluloki TextView’ssa)
- Tarkastella tilastoja (yhteensä taistelut ja voitot) MPAndroidChart-kirjastolla

Sovellus käyttää modernia Android-arkkitehtuuria:
- **Room** tietokantakerroksena  
- **LiveData** ja **ViewModel** datan hallintaan  
- **RecyclerView** ja **Spinner** listauksiin ja valikkoihin  
- **Fragmentit** eri näkymien toteutukseen  
- **MPAndroidChart** tilastojen piirtämiseen  

---

## 2. Luokkakaavio
Luokkakaavion UML-diagrammi on saatavilla erillisenä kuvatiedostona `UML_Diagram.png`.  
Se kattaa seuraavat pääluokat:
- `Lutemon` (entity + logiikka)
- `LutemonDao` (@Dao)
- `AppDatabase` (RoomDatabase)
- `LutemonRepository`
- `LutemonViewModel` (AndroidViewModel)
- Fragmentit: `HomeFragment`, `TrainingFragment`, `BattleFragment`, `StatsFragment`
- `LutemonAdapter` (ListAdapter + DiffUtil)

---

## 3. Työnjako
- **Ikeroikarinen Oikarinen**  
  - Sovelluksen kokonaisarkkitehtuuri  
  - Tietokantakerros (Room-entiteetit, DAO, database)  
  - ViewModel–Repository–DAO -ketju  
  - Fragmenttien ja adapterien toteutus  
  - Taistelu- ja tilastonäkymät  
  - Dokumentaation ja README:n laatiminen  

*Työ on suoritettu yksin.*

---

## 4. Implementoidut ominaisuudet ja pisteet

| Ominaisuus                                | Pisteet |
|-------------------------------------------|--------:|
| **Pakolliset (läpipääsy 15 p)**           |         |
| – Olioparadigma, Room, LiveData, ViewModel, fragmentit, RecyclerView, Spinner, perustekijät  | Pakollinen |
|                                           |         |
| **Lisäominaisuudet**                      |         |
| RecyclerView listauksessa (adapteri)      |      3p |
| Lutemon-kuvien näyttö itemissa            |      1p |
| Taistelun tekstuaalinen loki (TextView)   |      4p |
| Tilastojen tallennus ja näyttö (wins/battles) |   2p |
| Hävisijän palautus (kuolema ei poista)    |      1p |
| Satunnaisuus hyökkäyksissä (+0…3)         |      1p |
| Fragmenttien käyttö                       |      3p |
| MPAndroidChart-visualisointi              |      5p |
| **Yhteensä**                              | **20+** |

---

## 5. Asennus- ja käyttöohjeet

1. **Repositoryn kloonaus**  
   ```bash
   git clone https://github.com/Ikeroikarinen/harjoitustyo.git
