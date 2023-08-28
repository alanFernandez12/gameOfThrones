package com.trabajoIntegrador.gameOfThrones

class PersonajesProvider {
    companion object {
        val personajeList = listOf<Personaje>(
            Personaje(
                "Daenerys",
                "Targaryen",
                "Casa Targaryen",
                "Madre de dragones",
                "https://thronesapi.com/assets/images/daenerys.jpg"
            ),
            Personaje(
                "Samwell",
                "Tarly",
                "Casa Tarly",
                "Maestro",
                "https://thronesapi.com/assets/images/sam.jpg"
            ),
            Personaje(
                "Jon",
                "Snow",
                "Casa Stark",
                "Rey del norte",
                "https://thronesapi.com/assets/images/jon-snow.jpg"
            ),
            Personaje(
                "Arya",
                "Stark",
                "Casa Stark",
                "No tiene",
                "https://thronesapi.com/assets/images/arya-stark.jpg"
            ),
            Personaje(
                "Sansa",
                "Stark",
                "Casa Stark",
                "Dama de Winterfell",
                "https://thronesapi.com/assets/images/sansa-stark.jpeg"
            )

        )
    }
}