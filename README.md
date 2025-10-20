# TicTacToe - Jeux de plateau

Application de jeux de plateau développée en Java avec JavaFX.

## Jeux disponibles

- **TicTacToe** (3x3)
- **Gomoku** (15x15)
- **Power4** (7x6)

## Fonctionnalités

- Interface graphique moderne avec JavaFX
- Mode console pour les développeurs
- Joueur vs Joueur ou IA (gestion avec un algorithme MinMax)
- Sauvegarde/Chargement de parties
- Détection automatique des victoires

## Prérequis

- **Java 11 ou supérieur** ([Télécharger Java](https://www.oracle.com/java/technologies/downloads/))
- **Git** ([Télécharger Git](https://git-scm.com/downloads))

**Note :** Vous n'avez **pas besoin** d'installer JavaFX manuellement - Gradle le télécharge automatiquement !

## Installation

1. Cloner le repository :
```bash
git clone https://github.com/Pierrick74/TicTacToe-java-campus.git
cd TicTacToe-java-campus
```

2. Lancer l'application :
```bash
./gradlew run
```

## Utilisation

### Lancer l'application
```bash
./gradlew run
```

### Compiler le projet
```bash
./gradlew build
```

### Nettoyer les builds
```bash
./gradlew clean
```

## Contrôles

### Interface graphique
- Cliquez sur les cases pour jouer
- Utilisez le menu pour sauvegarder/nouvelle partie/quitter

### Contrôle clavier (Console)
- Tapez les chiffres pour sélectionner les positions
- Appuyez sur **ENTER** pour valider (pour les grands plateaux)

## Architecture du projet

```
TicTacToe/
├── src/
│   └── main/
│       └── java/
│           └── org/Games/
│               ├── Controller/      # Contrôleurs MVC
│               ├── JavaFX/          # Vues JavaFX
│               ├── model/           # Modèles (logique métier)
│               ├── observer/        # Pattern Observer
│               └── Vue/             # Vue console
├── build.gradle                     # Configuration Gradle
└── README.md
```

## Technologies utilisées

- **Java 11+**
- **JavaFX 21** (interface graphique)
- **Gradle** (build tool)

## Pattern de conception

- **MVC** (Model-View-Controller)
- **Observer** (pour la communication entre composants)
- **Strategy** (pour les règles de placement et représentation)
- **Singleton** (pour la vue console)

## Auteur

Développé par Pierrick VIRET dans le cadre de la formation du CampusNumerique

## Licence

Ce projet est sous licence MIT.