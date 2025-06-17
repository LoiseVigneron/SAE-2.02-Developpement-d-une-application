---
title: SAE S2.02 -- Rapport graphes
subtitle: C4
author: Mangin MAXENCE, Loïse VIGNERON, Manon VILODA-RIVIERE 
date: 2025
---

# Sommaire

* [Version 1](#version-1)
     * [Choix pour la modélisation](#choix-pour-la-modélisation)
       * [Forte affinité](#forte-affinité)
       * [Faible affinité](#faible-affinité)
       * [Arbitrage entre les critères d'affinité](#arbitrage-entre-les-critères-daffinité)
     * [Exemple concret](#exemple-complet)
     * [Score d'affinité](#score-daffinité)
     * [Retour sur l'exemple](#retour-sur-lexemple)
* [Version 2](#version-2)
     * [Exemple avec appariement total](#exemple-avec-appariement-total)
     * [Exemple sans appariement total](#exemple-sans-appariement-total)
     * [Score d'affinité](#score-daffinité-1)
     * [Retour sur l'exemple](#retour-sur-lexemple-1)
* [Version 3](#version-3)
     * [Équilibrage entre affinité / incompatibilité](#équilibrage-entre-affinité--incompatibilité)
     * [Score d'affinité](#score-daffinité-2)
     * [Retour sur l'exemple](#retour-sur-lexemple-2)

# Version 1

## Choix pour la modélisation

| PERSON | BIRTH_DATE | GENDER | PAIR_GENDER | HOBBIES |
| :------: |:------: | :------: | :-----------: | :-------: |
| **H1** | 22/06/2004 | Male | x | Video games, Dinosaurs, Sport, Cinema | 
| **V1** | 05/09/2005 | Male | x | Video games, Sport, Cinema | 
| **H2** | 05/09/2005 | Male | x | Video games, Sport, Cinema | 
| **V2** | 26/10/1999 | Other | x | Crochet, Guitar, Dinosaurs | 

Le tableau ci-dessus représente les 4 personnes qui nous seront utiles pour représenter une forte et une faible affinité. Celles-ci sont d'ailleurs décrites ci-après.  
Néanmoins, avant de les expliquer, il nous semble important de rappeler les conditions pour accepter une affinité :
   * **BIRTH_DATE :** Si une différence d'âge est inférieure à 1,5 ans, nous considérons qu'il y a une affinité entre les deux personnes. 
   * **PAIR_GENDER :** Nous considérons qu'il y a une affinité si chaque préférence de genre est satisfaite. Une préférence non exprimée (par un *"x"* dans le tableau) sera toujours considérée comme satisfaite. 
   * **HOBBIES :** Plus les personnes ont des loisirs en commun, plus l'affinité est importante.  
Globalement, 1 loisir commun = 1 affinité

### Forte affinité

La paire *(H1, V1)* présente ici une forte affinité. En effet, nous pouvons retrouver plusieurs points communs (5 affinités) entre l'*host* et le *visitor*.  
  
La première affinités concerne les *BIRTH_DATE*, la différence entre celles-ci est inférieure à 1,5 an. Il y a donc un bonus d'affinité qui peut être pris en compte. 
  
La seconde affinité concerne la colonne *PAIR_GENDER*. Ici, nous pouvons observer que les deux personnes n'ont aucune préférence quant au genre à privilégier pour la constitution d'un duo. 
  
Enfin, les dernières affinités se retrouvent dans les *HOBBIES*. Effectivement, *H1* partage avec *V1* tous ses loisirs, à l'exception de *Dinosaurs* que *V1* a spécifié en plus lors du questionnaire. Il y a donc 3 loisirs en commun entre *(H1,V1)*. 

### Faible affinité

Contrairement à *(H1,V1)*, la paire *(H2,V2)* ne présente pas une forte affinité, mais plutôt une faible affinité. En effet, avec cette paire, nous ne retrouvons qu'un seul point d'affinité.  
  
La différence des *BIRTH_DATE* est supérieure à 1,5 ans et donc ne permet pas de valider une affinité entre les deux personnes. 

Egalement, *H2* et *V2* ne partagent aucun loisir en commun, ce qui ne permet pas non plus de trouver une affinité pour *(H2,V2)*. 
  
Enfin, le seul point d'affinité que nous pouvons confirmer pour la paire *(H2,V2)* concerne le *PAIR_GENDER*. Effectivement, les deux personnes n'ont pas de préférence au niveau de l'affinité de genre pour la constitution d'une paire. 

### Arbitrage entre les critères d'affinité

Rajoutons maintenant trois autres nouvelles paires afin d'illustrer plus en détail l'affectation des différentes affinités : 

| PERSON | BIRTH_DATE | GENDER | PAIR_GENDER | HOBBIES |
| :------: |:------: | :------: | :-----------: | :-------: |
| **H3** | 31/10/2006 | Female | Female | Reading, Sport| 
| **V3** | 17/11/2006 | Female | x | Video games, Drawing, Reading | 
| **H4** | 17/11/2006 | Female | x | Video games, Drawing, Reading | 
| **V4** | 22/06/2004 | Male | x | Video games, Sport, Cinema, Dinosaurs |  
| **H5** | 26/10/1999 | Other | x | Crochet, Guitar, Dinosaurs | 
| **V5** | 22/06/2004 | Male | x | Video games, Sport, Cinema, Dinosaurs |

Ces trois paires présentent des affinités équivalentes.  
La paire *(H3, V3)* possède trois points d'affinité, qui sont : le *PAIR_GENDER* (validé pour les deux individus), le *BIRTH_DATE* (inférieur à 1,5 ans) et enfin les *HOBBIES* (avec *Reading* en commun).  
La paire *(H4, V4)* a deux points d'affinité qui sont : le *PAIR_GENDER* (validé par les deux personnes) et le *HOBBIES* (basé sur les jeux vidéos). Il n'y a pas de point d'affinité avec le *BIRTH_DATE* car la différence entre les dates est supérieure à 1,5 ans.
Pour finir, la paire *(H5, V5)* présente aussi deux points d'affinité : ils concernent également le *PAIR_GENDER* et les *HOBBIES*, sauf qu'ici le loisir en commun est *Dinosaurs*.

## Exemple complet

Prenons l'exemple entre quatre hôtes et quatre visiteurs. 

_Hôtes :_   

| HOST | BIRTH_DATE | GENDER | PAIR_GENDER | HOBBIES |
| :--: | :--------: | :----: | :---------: | :-----: |
| **A** | 26/10/1999 | O | x | Crochet, Guitar, Dinosaurs | 
| **B** | 28/10/2006 | M | x | Video Games, Ducks | 
| **C** | 19/11/1999 | M | x | Video Games, Cinema |
| **D** | 29/04/2006 | F | F | Reading, Crochet, Sport |  

_Visiteurs :_  

| VISITOR | BIRTH_DATE | GENDER | PAIR_GENDER | HOBBIES |
| :-----: | :--------: | :----: | :---------: | :-----: |
| **W** | 31/10/2006 | F | F | Reading, Sports | 
| **X** | 17/11/2006 | F | x | Drawing, Video games, Reading | 
| **Y** | 22/06/2004 | M | x | Video games, Dinosaurs, Sports, Cinema |
| **Z** | 05/09/2005 | M | x | Video games, Sports, Cinema |


Après une simple observation de ces 8 élèves, nous pouvons en déduire des appariements qui nous semblent de premier abord les meilleurs appariements possibles :  

| **HOST** | **VISITOR** | 
| :------: | :---------: |
| A | Y | 
| B | X | 
| C | Z |
| D | W | 

## Score d'affinité

```
double score_affinité_1(hôte, visiteur) 

  Score = A

  Si hote_age - Visiteur_age >= 18 mois {
    alors score += B
  }
  
  Si hote_gender != Visiteur_pair_gender {
    Si visiteur_gender != hote_pair_gender {
      alors score += C
    }
  }
    
  nb_hobby_commun = N
  Score -= N
  retourner score
  ```

## Retour sur l'exemple

Reprenons le pseudo code ci-dessus pour proposer un graphe biparti entre les hôtes et les visiteurs décrits plus haut.  
Supposons que chaque affinité a une valeur de 1. 

```
 ;A;B;C;D  
W;2;0;2;-2  
X;1;-1;0;-1  
Y;0;0;-1;1  
Z;1;-1;-1;0   
```

Si l'on calcule l'appariement de poids minimal de ce graphe biparti, nous obtenons les appariements suivants : 

| **HOST** | **VISITOR** | **Cout minimal** | 
| :------: | :---------: | :--------------: |
| A | Y | -1 |
| B | X | -2 |
| C | Z | 0 |
| D | W | -1 |

Nous retrouvons l'appariement que nous avions identifié comme le meilleur.  
Néanmoins, le calcul de ce poids minimal nous conforte et affirme que les meilleurs appariements possibles sont ceux issus de l'algorithme. 

En effet, ici le nombre de valeurs était relativement faible et un appariement manuel était donc possible. Dans l'optique où ce nombre de valeurs serait plus important, il deviendrait humainement compliqué d'extraire de ces données le meilleur appariement. Afin d'éviter toute erreur et toute perte de temps, il semble important voire essentiel de se baser sur un algorithme de calcul de poids minimal dans le but d'éviter toutes erreurs ou toutes omissions, et de gagner en rapidité. 

# Version 2

*Hôtes* :

| **NAME** | **BIRTH_DATE** | **GENDER** | **PAIR_GENDER** | **HOBBIES** | **HAS_ANIMAL** | **HOST_FOOD** | 
| :------: | :------------: | :--------: | :-------------: | :---------: | :------------: | :-----------: |
| **A1** | 26/10/1999 | O | x | Crochet, Guitar, Dinosaurs | false | vegetarian | 
| **B1** | 28/10/2006 | M | x | Video games, Ducks | false | normal | 
| **C1** | 19/11/1999 | M | x | Video games, Cinema | true | no nuts | 
| **D1** | 29/04/2006 | F | F | Reading, Crochet, Sport | true | vegetarian | 

*Visiteurs* :  

| **NAME** | **BIRTH_DATE** | **GENDER** | **PAIR_GENDER** | **HOBBIES** | **GUEST_ANIMAL_ALLERGY** | **GUEST_FOOD_CONSTRAINT** | 
| :------: | :------------: | :--------: | :-------------: | :---------: | :----------------------: | :-----------------------: |
| **W1** | 31/10/2006 | F | F | Reading, Sport | true | normal |
| **X1** | 17/11/2006 | F | x | Drawing, Video games, Reading | false | no nuts | 
| **Y1** | 22/06/2004 | M | x | Video games, Dinosaurs, Sport, Cinema | false | vegetarian | 
| **Z1** | 05/09/2005 | M | x | Videos games, Sport, Cinema | true | normal | 

Les tableaux ci-dessus possèdent de nouvelles valeurs, qui seront des contraintes rédhibitoires : 
   * **HAS_ANIMAL** : Si *true*, l'*hôte* possède un animal de compagnie. Si *false*, alors il n'en possède pas.
   * **HOST_FOOD** : Cette variable représente le régime alimentaire de l'*hôte*. 
   * **GUEST_ANIMAL_ALLERGY** : Si *true*, le visiteur est allergique aux animaux. Si *false*, il n'est pas allergique. 
   * **GUEST_FOOD_CONSTRAINT** : représentation du régime alimentaire du *visiteur*. 

## Exemple avec appariement total

Les paires suivantes sont des paires où il existe des incompatibilités entre *hôtes* et *visiteurs* :  

| **HOST** | **VISITOR** | 
| :------: | :---------: |
| A1 | X1 |
| C1 | W1 |
| D1 | Z1 |
| A1 | W1 | 

Les incompatibilités de ces paires concernent les contraintes classiques, abordées dans la *Version 1*, mais aussi les nouvelles contraintes rédhibitoires énoncées plus haut.  
Néanmoins, si on modifie 4 paires incompatibles, il est possible que les contraintes rédhibitoires **GUEST_ANIMAL_ALLERGY**, **GUEST_FOOD_CONSTRAINT**, **HAS_ANIMAL** et **HOST_FOOD** sont possibles d'être respectées. 
En effet, après avoir étudié ces différents *hôtes* et *visiteurs*, nous pouvons en déduire le meilleur appariement suivant : 

| **HOST** | **VISITOR** | 
| :------: | :---------: |
| A1 | W1 |
| B1 | Z1 |
| C1 | X1 |
| D1 | Y1 | 

Cet appariement nous semble être le meilleur, car il nous évite d'avoir des paires avec des contraintes rédhibitoires. Elles ne présentent pas une affinité autant optimale que la *Version 1*, mais l'affinité de chaque paire semble pour nous la plus optimale car elle ne possède pas ou alors très peu de contraintes.  

## Exemple sans appariement total

Prenons désormais l'exemple d'autres *hôtes* et *visiteurs* qui présentent plus de contraintes que nos paires précédentes : 

*Hôtes* :

| **NAME** | **BIRTH_DATE** | **GENDER** | **PAIR_GENDER** | **HOBBIES** | **HAS_ANIMAL** | **HOST_FOOD** | 
| :------: | :------------: | :--------: | :-------------: | :---------: | :------------: | :-----------: |
| **A2** | 25/08/2006 | M | O | Cooking, Fashion | true | normal | 
| **B2** | 25/04/2005 | O | M | Fashion, Jardin | true | vegetarian | 
| **C2** | 26/07/2006 | F | F | Opera, Sport | true | no nuts | 
| **D2** | 13/12/2006 | M | F | Geography, mathematics | true | vegetarian | 

*Visiteurs* :  

| **NAME** | **BIRTH_DATE** | **GENDER** | **PAIR_GENDER** | **HOBBIES** | **GUEST_ANIMAL_ALLERGY** | **GUEST_FOOD_CONSTRAINT** | 
| :------: | :------------: | :--------: | :-------------: | :---------: | :----------------------: | :-----------------------: |
| **W2** | 23/08/2006 | M | M | Video games, Sport | false | normal |
| **X2** | 19/11/2000 | F | x | Chemistry, Music | true | vegetarian | 
| **Y2** | 05/09/2005 | M | F | Video games, Sport, Cinema | true | normal | 
| **Z2** | 19/03/2006 | O | O | Technology, Art | true | no nuts | 

Si l'on considère ces *hôtes* et *visiteurs*, nous pouvons constater qu'il est difficile de constituer des paires compatibles pour chacun, au vu du nombre important d'incompatibilités des uns et des autres.  
  

Dans ce cas présent, il est possible de former uniquement 2 paires avec une compatibilité que nous considérons comme "convenable", c'est deux paires sont : *(A2/W2)*, qui ne présente pas de contraintes rédhibitoires mais une contrainte avec **PAIR_GENDER** et *(D2,Y2)* qui ne présente pas non plus de contraintes rédhibitoires et une seule contrainte concernant également le **PAIR_GENDER**. 

Pour déterminer le meilleur appariement possible pour cette deuxième proposition, nous avons choisi de prioriser les scores minimum, afin de garantir une répartition optimale tout en évitant au plus les contraintes rédhibitoires. Grâce à cette priorisation, nous avons donc obtenu les paires suivantes :  

| **HOST** | **VISITOR** | 
| :------: | :---------: |
| A2 | W2 |
| B2 | X2 |
| C2 | Z2 |
| D2 | Y2 | 

Nous trouvons que cet appariement est le meilleur, puisqu'au vu de toutes les incompatibilités nous avons réussi à former 2 paires sans contraintes rédhibitoires et 2 paires avec qu'une seule contraintes rédhibitoires (et d'autres contraintes "classiques"). 

## Score d'affinité

```
double score_affinité_2(hôte, visiteur) 

  Score = A

  Si écart entre hote_age et Visiteur_age >= 18 mois {
    alors Score += B
  }
  
  Si hote_gender != Visiteur_pair_gender {
    Si visiteur_gender != hote_pair_gender {
      alors Score += C
    }
  }
    
  nb_hobby_commun = N
  Score -= N

  Si has_animal && guest_animal_allergy {
    alors Score += E 
  }

  Si host_food != guest_food_constraint {
    alors Score +=E 
  } mais si guest_food_constraint == normal {
    alors retourner Score
  }

  retourner Score
```

## Retour sur l'exemple

Si nous prenons un poids de 1 pour les contraintes de base et un poids de 10 pour les contraintes rédhibitoires, nous pouvons alors proposer 2 matrices d'adjence issues des exemples énoncés plus haut.  
  
   * Matrice d'adjacence pour le premier exemple : 

```
 ;A1;B1;C1;D1  
W1;2;0;12;10  
X1;11;9;0;9  
Y1;0;10;9;1  
Z1;1;-1;9;10
```
En utilisant le calcul de poids minimal sur cette matrice, nous obtenons le résultat suivant :  

| **HOST** | **VISITOR** | **Cout minimal** | 
| :------: | :---------: | :--------------: |
| A1 | Z1 | 1 |
| B1 | W1 | 1 |
| C1 | X1 | 0 |
| D1 | Y1 | 0 | 

Le résultat ne donne pas exactement le même appariement que proposé plus haut.  
Cependant nous pouvons en déduire que le meilleur appariement est celui proposé par l'affectation de cout minimal, puisque les paires ont au maximum une seule contrainte et au minimum 0 contrainte.  
A contrario, notre appariement proposait des poids de 2 au maximum : *(A1,W1)*, et de -1 au minimum : *(B1,Z1)*. L'intervalle entre l'affectation des différentes paires étaient donc plus grande que celle du résultat proposé par le calcul de l'appariement de poids minimal.  
  
   * Matrice d'adjacence du deuxième exemple : 

```
 ;A2;B2;C2;D2  
W2;1;1;0;1  
X2;22;12;21;11  
Y2;11;11;10;1  
Z2;21;21;11;11
```

En utilisant également le calcul de poids minimal, nous obtenons pour ce deuxième exemple l'appariement suivant : 

| **HOST** | **VISITOR** | **Cout minimal** | 
| :------: | :---------: | :--------------: |
| A2 | W2 | 1 |
| B2 | X2 | 11 |
| C2 | Z2 | 1 |
| D2 | Y2 | 12 | 

Notre proposition d'appariement est identique à celui préconisé par le résultat du calcul.  
Nous pouvons donc en conclure que cette configuration est la plus optimale pour cet exemple, malgré les fortes contraintes présentes, notamment pour les paires *(B2,X2)* et *(D2,Y2)* qui possèdent des contraintes rédhibitoires. 

## Robustesse de la modélisation (question difficile)

Notre fonction `score_affinité_2` peut garantir que les contraintes rédhibitoires soient respectées, mais cette garantie dépend des valeurs de poids attribuées aux contraintes rédhibitoires.  
En effet, lorsque celles-ci ont un poids beaucoup plus important que les contraintes classiques, la fonction assure un bon appariement des élèves tout en respectant les contraintes rédhibitoires. 
Néanmoins, nous avons pu observer avec le deuxième exemple que le meilleur appariement fait des paires avec des contraintes rédhibitoires. 
Il semble donc très plausible qu'une population possède des *hotes* et des *visiteurs* qui sont en majorité incompatibles. 
Nous pouvons donc avancer en conséquence que l'efficacité de `score_affinité_2` dépend surtout des données de populations.  

Pour conclure, notre fonction `score_affinité_2` dépendra des données de la population choisie. Pour cela il semble essentiel d'attribuer des poids importants pour les contraintes rédhibitoires afin d'éviter au mieux des appariements indésirables. 

# Version 3

*Hôtes* :

| **NAME** | **BIRTH_DATE** | **GENDER** | **PAIR_GENDER** | **HOBBIES** | **HAS_ANIMAL** | **HOST_FOOD** | 
| :------: | :------------: | :--------: | :-------------: | :---------: | :------------: | :-----------: |
| **H1** | 23/08/2003 | M | M | Video games, Sport, Reading | false | normal | 
| **H2** | 19/11/2005 | F | x | Chemistry, Music | true | vegetarian | 
| **H3** | 05/09/2005 | M | F | Video games, Sport, Cinema | true | normal | 
| **H4** | 19/03/2006 | O | X | Technology, Art | true | no nuts | 

*Visiteurs* :

| **NAME** | **BIRTH_DATE** | **GENDER** | **PAIR_GENDER** | **HOBBIES** | **HAS_ANIMAL** | **HOST_FOOD** | 
| :------: | :------------: | :--------: | :-------------: | :---------: | :------------: | :-----------: |
| **V1** | 25/08/2006 | M | x | Video games, Fashion, Reading, Sport | true | normal | 
| **V2** | 25/04/2005 | O | F | Fashion, Gardening, Chemistry | true | vegetarian | 
| **V3** | 26/07/2006 | F | F | Opera, Sport, Video games | true | no nuts | 
| **V4** | 13/12/2006 | M | X | Geography, Mathematics, Art | false | no nuts | 

## Équilibrage entre affinité / incompatibilité

A l'aide de ces nouvelles données, nous pouvons proposer des paires que nous considérons comme quasi-équivalentes concernant l'affectation : 

| **HOST** | **VISITOR** | 
| :------: | :---------: |
| H1 | V1 | 
| H2 | V2 |
| H3 | V3 |
| H4 | V4 |

Pour réaliser cet appariement, nous avons choisi d'utiliser notre fonction `score_affinité_2`, avec comme attribution de poids :
 - 1 pour les contraintes classiques,
 - 50 pour les contraintes rédhibitoires, 
Ce qui nous donne une intervalle de score de [-4;102].  

Grâce au poids très important des contraintes rédhibitoires (qui avait pour but de garantir une intervalle assez grande), nous avons décidé de mettre en place une réduction de ce poids uniquement si le nombre de **HOBBIES** est égal ou supérieur à 3. Effectivement, cette réduction permet de minimiser l'importance des contraintes rédhibitoires, sans totalement les effacées du calcul de poids minimal.  
Par exemple, si deux personnes ont 3 **HOBBIES** en commun, nous divisons le poids des contraintes rédhibitoires par 10, ce qui nous donne donc un poids de 5 pour celles-ci. 
Ce poids pourrait ainsi permettre de privilégier les affinités plutot que de se focaliser uniquement les contraintes. 

## Score d'affinité

```
double score_affinité_3(hote, visitor) 
int Score;
Si écart entre hote_age et visitor_age > 18mois {
	alors Score +=  1;}
Si hote_gender != visitor_pairgender{
	si visitor_gender != hote_pairgender{
		alors Score += 1;
	}
}
int nb_hobby_commun = N;
Score -= N;

int ctr = 50;
Si nb_hobby_commun >= 3 {
	ctr = ctr / 10;
}

Si hote_animal && visitor animal {
	alors Score += ctr;
}

Si hote_food != visitor_food {
	alors Score += ctr;
mais si visitor_food == ‘normal’ {
	alors retourner Score;}
}

retourner Score
```

## Retour sur l'exemple

Si nous reprenons la fonction `score_affinité_3` décrite ci-dessus, ainsi que l'appariement proposé, nous pouvons donner le résultat de poids minimal pour chaque paires : 

| **HOST** | **VISITOR** | **Cout minimal** | 
| :------: | :---------: | :--------------: |
| H1 | V1 | -2 |
| H2 | V2 | 4 |
| H3 | V3 | 9 |
| H4 | V4 | -1 | 

Les scores obtenus ci-dessus peuvent être considérés comme proches, au vu de notre intervalle importante : [-4,102]. 
Pourtant parmi ces quatre paires, il y a deux paires sans contrainte rédhibitoire : *(H1,V1)* et *(H4,V4)*, et deux paires avec contraintes rédhibitoires : *(H2,V2)* et *(H3,V3)*. 
Nous avons donc réussi à prioriser l'affinité aux contraintes, lors d'un contexte bien précis, qui est : avoir 3 **HOBBIES** ou plus en commun.  
Cette fonction garantie donc (à première vue) la priorisation des affinités entre les personnes.  

Il semble intéressant de tester cette dernière fonction `score_affinité_3` avec un panel plus large de personnes, et avec différentes populations afin de garantir pleinement la fiabilité de ce code. 