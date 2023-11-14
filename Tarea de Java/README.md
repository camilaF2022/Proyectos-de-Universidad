Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

---
Para la creacion de este proyecto se construyeron varias clases para las armas y los personajes.

Para el primero se construyo una clase abstracta "AbstractWeapon", en la que aparecen las caracteristicas de todas las armas y las subclases "axe","bow",
"knife","staff","sword" que corresponden al tipo de arma.

Estas armas pueden ser equipadas por los personajes mediante el metodo equip.

Para los personajes se creo la clase abstracta "AbstractCharacter", en la cual contiene toda las caracteristicas que possen los personajes, de esta clase, derivan
2 subclases, las cuales son AbstractPlayer character, la cual posee toda la informacion para que el jugador pueda controlar al personaje y la otra subclase es enemy.

Otra clase Abstracta es AbstractMage que contiene la informacion sobre los magos, esta subclase tambien deriva de AbstractPlayerCharacter.

Finalmente para testear que el proyecto funciona correctamente, se creo la clase main que sirve para comprobar que todo se ejecute de manera correcta.

