Les objets dans des tables de hachage doivent être immuables. Or, ce n'est pas le cas d'un personnage. On lui ajoute des armes, donne des trésors, lui inflige des dégats etc.. 
La méthode hashCode() doit retourner constamment le même entier et le méthode equals doit pouvoir retourner vrai pour deux même objets.
Ainsi, si on veut pouvoir stocker des personnages dans une table de hachage, il faut donc programmer les methodes hashCode et equals avec 
des paramètres de la classe personnage qui sont immuables comme par exemple avec le ROLE, le nom ou bien les PVMax. 
Il n'est donc pas prudent de stocker des personnages dans une table de hachage car c'est une classe muable.