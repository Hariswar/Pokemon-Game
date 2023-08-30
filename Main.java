import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

class Main {
  // Delays time for 1 second before next message
  private static void delay() { 
    try {
      TimeUnit.SECONDS.sleep(1);
    }
    catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  // Stores the boost multipliers
  private static final HashMap<Integer, Double> boostMultipliers = new HashMap<>();
  public static double getBoostMultiplier(int boost) {
    return boostMultipliers.get(boost);
  }

  // Move object creation
  private static Move empty = new Move("Empty", "Normal", "Physical", 0, 0, 0, 0, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move toxic = new Move("Toxic", "Poison", "Status", 16, 0, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Badly Poisoned", 1);
  
  private static Move psychic = new Move("Psychic", "Psychic", "Special", 16, 90, 0, 1, "ATK", 0, 0, "SPC", 0.33, 1, "Burned", 0);
  
  private static Move hornDrill = new Move("Horn Drill", "Normal", "Physical", 8, 0, 65535, 0.3, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move hydroPump = new Move("Hydro Pump", "Water", "Special", 8, 120, 0, 0.8, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move hypnosis = new Move("Hypnosis", "Psychic", "Status", 32, 0, 0, 0.6, "ATK", 0, 0, "ATK", 0, 0, "Asleep", 1); 
  
  private static Move recover = new Move("Recover", "Normal", "Status", 32, 0, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move softBoiled = new Move("Soft-Boiled", "Normal", "Status", 32, 0, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move slash = new Move("Slash", "Normal", "Physical", 16, 70, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move drillPeck = new Move("Drill Peck", "Flying", "Physical", 32, 80, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move confuseRay = new Move("Confuse Ray", "Ghost", "Status", 16, 0, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Confused", 1);
  
  private static Move nightShade = new Move("Night Shade", "Ghost", "Special", 24, 0, 100, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move seismicToss = new Move("Seismic Toss", "Fighting", "Physical", 32, 0, 100, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move thunderbolt = new Move("Thunderbolt", "Electric", "Special", 24, 95, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Paralyzed", 0.1);
  
  private static Move surf = new Move("Surf", "Water", "Special", 24, 95, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move clamp = new Move("Clamp", "Water", "Special", 16, 35, 0, 0.75, "ATK", 0, 0, "ATK", 0, 0, "Clamped", 1);
  
  private static Move wrap = new Move("Wrap", "Normal", "Physical", 32, 24, 0, 0.85, "ATK", 0, 0, "ATK", 0, 0, "Wrapped", 1);
  
  private static Move thunderWave = new Move("Thunder Wave", "Electric", "Status", 32, 0, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Paralyzed", 1);
  
  private static Move reflect = new Move("Reflect", "Psychic", "Status", 32, 0, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move rest = new Move("Rest", "Psychic", "Status", 16, 0, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move blizzard = new Move("Blizzard", "Ice", "Special", 8, 120, 0, 0.9, "ATK", 0, 0, "ATK", 0, 0, "Frozen", 0.1);
  
  private static Move explosion = new Move("Explosion", "Normal", "Physical", 8, 170, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move sleepPowder = new Move("Sleep Powder", "Grass", "Status", 24, 0, 0, 0.75, "ATK", 0, 0, "ATK", 0, 0, "Asleep", 1);
  
  private static Move doubleEdge = new Move("Double-Edge", "Normal", "Physical", 24, 100, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move doubleKick = new Move("Double Kick", "Fighting", "Physical", 48, 60, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move lovelyKiss = new Move("Lovely Kiss", "Normal", "Status", 24, 0, 0, 0.75, "ATK", 0, 0, "ATK", 0, 0, "Asleep", 1);
  
  private static Move bodySlam = new Move("Body Slam", "Normal", "Physical", 24, 85, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Paralyzed", 0.3);
  
  private static Move lick = new Move("Lick", "Ghost", "Special", 48, 20, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Paralyzed", 0.3);
  
  private static Move earthquake = new Move("Earthquake", "Ground", "Physical", 16, 100, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move rockSlide = new Move("Rock Slide", "Rock", "Physical", 16, 75, 0, 0.9, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move amnesia = new Move("Amnesia", "Psychic", "Status", 32, 0, 0, 1, "SPC", 1, 2, "ATK", 0, 0, "Burned", 0);
  
  private static Move selfDestruct = new Move("Self-Destruct", "Normal", "Physical", 8, 130, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move agility = new Move("Agility", "Psychic", "Status", 32, 0, 0, 1, "SPE", 1, 2, "ATK", 0, 0, "Burned", 0);
  
  private static Move razorLeaf = new Move("Razor Leaf", "Grass", "Special", 40, 55, 0, 0.95, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move swordsDance = new Move("Swords Dance", "Normal", "Status", 48, 0, 0, 1, "ATK", 1, 2, "ATK", 0, 0, "Burned", 0);
  
  private static Move hyperBeam = new Move("Hyper Beam", "Normal", "Physical", 8, 150, 0, 0.9, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  
  private static Move fireBlast = new Move("Fire Blast", "Fire", "Special", 8, 120, 0, 0.85, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0.3);

  private static Move lowKick = new Move("Low Kick", "Fighting", "Physical", 40, 50, 0, 0.9, "ATK", 0, 0, "ATK", 0, 0, "Flinched", 0.3);

  // Pokemon object creation
  private static Pokemon tauros = new Pokemon("Tauros", "Normal", "Empty", bodySlam, hyperBeam, blizzard, earthquake, 75, 100, 95, 70, 110);
  
  private static Pokemon alakazam = new Pokemon("Alakazam", "Psychic", "Empty", psychic, seismicToss, thunderWave, recover, 55, 50, 45, 135, 120);
  
  private static Pokemon chansey = new Pokemon("Chansey", "Normal", "Empty", reflect, seismicToss, softBoiled, thunderWave, 250, 5, 5, 105, 50);
  
  private static Pokemon adi = new Pokemon("Adi", "Bug", "Empty", lick, lovelyKiss, surf, softBoiled, 100, 100, 100, 100, 50);
  
  private static Pokemon kirby = new Pokemon("Kirby", "Normal", "Empty", toxic, empty, empty, empty, 150, 100, 100, 100, 100);
  
  private static Pokemon pikachu = new Pokemon("Pikachu", "Electric", "Empty", thunderbolt, thunderWave, surf, seismicToss, 35, 55, 30, 50, 90);
  
  private static Pokemon cloyster = new Pokemon("Cloyster", "Water", "Ice", clamp, blizzard, rest, explosion, 50, 95, 180, 85, 70);
  
  private static Pokemon exeggutor = new Pokemon("Exeggutor", "Grass", "Psychic", sleepPowder, psychic, doubleEdge, explosion, 95, 95, 85, 125, 55);
  
  private static Pokemon gengar = new Pokemon("Gengar", "Ghost", "Poison", hypnosis, nightShade, thunderbolt, explosion, 60, 65, 60, 130, 110);
  
  private static Pokemon jolteon = new Pokemon("Jolteon", "Electric", "Empty", thunderWave, thunderbolt, doubleKick, rest, 65, 65, 60, 110, 130);
  
  private static Pokemon rhydon = new Pokemon("Rhydon", "Rock", "Ground", earthquake, rockSlide, bodySlam, hornDrill, 105, 130, 120, 45, 40);
  
  private static Pokemon slowbro = new Pokemon("Slowbro", "Water", "Psychic", amnesia, reflect, psychic, rest, 95, 75, 110, 80, 30);
  
  private static Pokemon snorlax = new Pokemon("Snorlax", "Normal", "Empty", bodySlam, reflect, rest, selfDestruct, 160, 110, 65, 65, 30);
  
  private static Pokemon starmie = new Pokemon("Starmie", "Water", "Psychic", recover, thunderWave, surf, thunderbolt, 60, 75, 85, 100, 115);
  
  private static Pokemon dragonite = new Pokemon("Dragonite", "Dragon", "Flying", wrap, hyperBeam, blizzard, thunderWave, 91, 134, 95, 100, 80);
  
  private static Pokemon zapdos = new Pokemon("Zapdos", "Electric", "Flying", thunderbolt, drillPeck, agility, thunderWave, 90, 90, 85, 125, 100);
  
  private static Pokemon lapras = new Pokemon("Lapras", "Water", "Ice", confuseRay, thunderbolt, bodySlam, blizzard, 130, 85, 80, 95, 60);
  
  private static Pokemon owenSong = new Pokemon("Owen Song", "Normal", "Empty", empty, empty, empty, empty, 5, 500, 5, 5, 500); 
  
  private static Pokemon charizard = new Pokemon("Charizard", "Fire", "Flying", swordsDance, hyperBeam, earthquake, fireBlast, 78, 84, 78, 85, 100);
  
  private static Pokemon blastoise = new Pokemon("Blastoise", "Water", "Empty", hydroPump, earthquake, rest, blizzard, 79, 83, 100, 85, 78);
  
  private static Pokemon venusaur = new Pokemon("Venusaur", "Grass", "Poison", sleepPowder, razorLeaf, swordsDance, hyperBeam, 80, 82, 83, 100, 80);
  
  private static Pokemon jynx = new Pokemon("Jynx", "Psychic", "Ice", lovelyKiss, blizzard, psychic, rest, 65, 50, 35, 95, 95);
  
  private static Pokemon mew = new Pokemon("Mew", "Psychic", "Empty", swordsDance, bodySlam, explosion, hyperBeam, 100, 100, 100, 100, 100);
  
  //private static Pokemon mewtwo = new Pokemon("Mewtwo", "Psychic", "Empty", psychic, amnesia, thunderbolt, recover, 106, 110, 90, 154, 130);
  
  private static Pokemon gyarados = new Pokemon("Gyarados", "Water", "Flying", hydroPump, thunderbolt, bodySlam, hyperBeam, 95, 130, 79, 100, 81);

  private static Pokemon dugtrio = new Pokemon("Dugtrio", "Ground", "Empty", earthquake, rockSlide, slash, toxic, 35, 80, 50, 70, 100);

  private static Pokemon nidoking = new Pokemon("Nidoking", "Poison", "Ground", thunderbolt, blizzard, earthquake, bodySlam, 81, 92, 77, 75, 85);

  private static Pokemon nidoqueen = new Pokemon("Nidoqueen", "Poison", "Ground", thunderbolt, blizzard, earthquake, bodySlam, 90, 82, 87, 75, 76);

  private static Pokemon aerodactyl = new Pokemon("Aerodactyl", "Rock", "Flying", doubleEdge, hyperBeam, fireBlast, toxic, 80, 105, 65, 60, 130);

  private static Pokemon machamp = new Pokemon("Machamp", "Fighting", "Empty", bodySlam, hyperBeam, lowKick, earthquake, 90, 130, 80, 65, 55);

  private static Pokemon electabuzz = new Pokemon("Electabuzz", "Electric", "Empty", thunderWave, thunderbolt, seismicToss, bodySlam, 65, 83, 57, 85, 105);

  private static Pokemon kangaskhan = new Pokemon("Kangaskhan", "Normal", "Empty", bodySlam, hyperBeam, earthquake, surf, 105, 95, 80, 40, 90);

  private static Pokemon golem = new Pokemon("Golem", "Rock", "Ground", bodySlam, rockSlide, explosion, earthquake, 80, 110, 130, 55, 45);

  private static Pokemon scyther = new Pokemon("Scyther", "Bug", "Flying", slash, swordsDance, hyperBeam, agility, 70, 110, 80, 55, 105);

  public static void main(String[] args) { 
    //Adds the dfferent boost multipliers and their values to the hash map
    boostMultipliers.put(-6,0.25);
    boostMultipliers.put(-5,0.28);
    boostMultipliers.put(-4,0.33);
    boostMultipliers.put(-3,0.40);
    boostMultipliers.put(-2,0.50);
    boostMultipliers.put(-1,0.66);
    boostMultipliers.put(0,1.0);
    boostMultipliers.put(1,1.5);
    boostMultipliers.put(2,2.0);
    boostMultipliers.put(3,2.5);
    boostMultipliers.put(4,3.0);
    boostMultipliers.put(5,3.5);
    boostMultipliers.put(6,4.0);

    //Adds each of the pokemon to the array list
    ArrayList<Pokemon> allPokemon = new ArrayList<>();
    allPokemon.add(adi);
    allPokemon.add(aerodactyl);
    allPokemon.add(alakazam);
    allPokemon.add(blastoise);
    allPokemon.add(chansey);
    allPokemon.add(charizard);
    allPokemon.add(cloyster);
    allPokemon.add(dragonite);
    allPokemon.add(dugtrio);
    allPokemon.add(electabuzz);
    allPokemon.add(exeggutor);
    allPokemon.add(gengar);
    allPokemon.add(golem);
    allPokemon.add(gyarados);
    allPokemon.add(jolteon);
    allPokemon.add(jynx);
    allPokemon.add(kangaskhan);
    allPokemon.add(kirby);
    allPokemon.add(lapras);
    allPokemon.add(machamp);
    allPokemon.add(mew);
    // allPokemon.add(mewtwo);
    allPokemon.add(nidoking);
    allPokemon.add(nidoqueen);
    allPokemon.add(owenSong);
    allPokemon.add(pikachu);
    allPokemon.add(rhydon);
    allPokemon.add(scyther);
    allPokemon.add(slowbro);
    allPokemon.add(snorlax);
    allPokemon.add(starmie);
    allPokemon.add(tauros);
    allPokemon.add(venusaur);
    allPokemon.add(zapdos);

    ArrayList<Pokemon> allPokemonTemp = new ArrayList<>(allPokemon);

    //Creates the scanner, the intScan and name string
    Scanner sc = new Scanner(System.in);
    String name;
    int intScan;

    //Starts the story and prompts the user for their name
    System.out.println("Hello there!");
    delay();
    System.out.println("Welcome to the world of Pokémon!");
    delay();
    System.out.println("My name is Mr. Hariswar!");
    delay();
    System.out.println("People call me the Pokémon professor!");
    delay();
    System.out.println("This world is inhabited by creatures called Pokémon!");
    delay();
    System.out.println("For some people, Pokémon are pets.");
    delay();
    System.out.println("Others use them for fights.");
    delay();
    System.out.println("Myself... I study Pokémon as a profession.");
    delay();
    System.out.println();
    System.out.print("First, what is your name?: ");
    name = sc.nextLine();
    System.out.print("\033[H\033[2J");
    System.out.println("Right! So your name is " + name + "!");
    delay();
    System.out.println("Here, " + name + "! There are 3 Pokémon here!");
    delay();
    System.out.println("Haha! They are inside the Poké Balls.");
    delay();
    System.out.println("When I was young, I was a serious Pokémon trainer!");
    delay();
    System.out.println("In my old age, I have only 3 left, but you can have one! Choose!");
    delay();
    System.out.println("(You choose your favorite Pokémon.)");
    delay();
    System.out.println("Your very own Pokémon legend is about to unfold!");
    delay();
    System.out.println("A world of dreams and adventures with Pokémon awaits! Let's go!");
    delay();
    System.out.println();
    System.out.println("Press [RETURN] to set out on your journey!");
    sc.nextLine();
    System.out.print("\033[H\033[2J");
    System.out.println("You set out on a long journey through the Kanto region!");
    delay();
    System.out.println("Over the course of many years, you catch and raise " + allPokemon.size() + " different Pokémon!");
    delay();
    System.out.println("You earn all eight gym badges in Kanto and compete in the Pokémon League!");
    delay();
    System.out.println("You proceed to win all of the preliminary matches!");
    delay();
    System.out.println("The quarterfinals are about to begin!");
    delay();
    System.out.println();
    System.out.println("Press [RETURN] to choose your team of 6 pokémon for the final matches of the Pokémon League!");
    sc.nextLine();
    System.out.print("\033[H\033[2J");

    Pokemon pokemon1;
    Pokemon pokemon2;
    Pokemon pokemon3;
    Pokemon pokemon4;
    Pokemon pokemon5;
    Pokemon pokemon6;

    int n;

    //Prompts the user to choose their team
    System.out.println("Your Pokémon: ");
    n = 0;
    for (Pokemon pokemon : allPokemonTemp) {
      System.out.println("[" + (n+1) + "] " + pokemon.getName());
      n++;
    }
    while(true) {
      try {
        System.out.println();
        System.out.print("Choose your first pokemon: ");
        intScan = Integer.parseInt(sc.nextLine());
        pokemon1 = allPokemonTemp.get(intScan - 1);
        break;
      }
      catch(Exception e) {
        System.out.println("Invalid input.");
      }
    }
    allPokemonTemp.remove(pokemon1);
    System.out.print("\033[H\033[2J");
    System.out.println("Your Pokémon: ");
    n = 0;
    for (Pokemon pokemon : allPokemonTemp) {
      System.out.println("[" + (n+1) + "] " + pokemon.getName());
      n++;
    }
    while(true) {
      try {
        System.out.println();
        System.out.print("Choose your second pokemon: ");
        intScan = Integer.parseInt(sc.nextLine());
        pokemon2 = allPokemonTemp.get(intScan - 1);
        break;
      }
      catch(Exception e) {
        System.out.println("Invalid input.");
      }
    }
    allPokemonTemp.remove(pokemon2);
    System.out.print("\033[H\033[2J");
    System.out.println("Your Pokémon: ");
    n = 0;
    for (Pokemon pokemon : allPokemonTemp) {
      System.out.println("[" + (n+1) + "] " + pokemon.getName());
      n++;
    }
    while(true) {
      try {
        System.out.println();
        System.out.println();
        System.out.print("Choose your third pokemon: ");
        intScan = Integer.parseInt(sc.nextLine());
        pokemon3 = allPokemonTemp.get(intScan - 1);
        break;
      }
      catch(Exception e) {
        System.out.println("Invalid input.");
      }
    }
    allPokemonTemp.remove(pokemon3);
    System.out.print("\033[H\033[2J");
    System.out.println("Your Pokémon: ");
    n = 0;
    for (Pokemon pokemon : allPokemonTemp) {
      System.out.println("[" + (n+1) + "] " + pokemon.getName());
      n++;
    }
    while(true) {
      try {
        System.out.println();
        System.out.print("Choose your fourth pokemon: ");
        intScan = Integer.parseInt(sc.nextLine());
        pokemon4 = allPokemonTemp.get(intScan - 1);
        break;
      }
      catch(Exception e) {
        System.out.println("Invalid input.");
      }
    }
    allPokemonTemp.remove(pokemon4);
    System.out.print("\033[H\033[2J");
    System.out.println("Your Pokémon: ");
    n = 0;
    for (Pokemon pokemon : allPokemonTemp) {
      System.out.println("[" + (n+1) + "] " + pokemon.getName());
      n++;
    }
    while(true) {
      try {
        System.out.println();
        System.out.print("Choose your fifth pokemon: ");
        intScan = Integer.parseInt(sc.nextLine());
        pokemon5 = allPokemonTemp.get(intScan - 1);
        break;
      }
      catch(Exception e) {
        System.out.println("Invalid input.");
      }
    }
    allPokemonTemp.remove(pokemon5);
    System.out.print("\033[H\033[2J");
    System.out.println("Your Pokémon: ");
    n = 0;
    for (Pokemon pokemon : allPokemonTemp) {
      System.out.println("[" + (n+1) + "] " + pokemon.getName());
      n++;
    }
    while(true) {
      try {
        System.out.println();
        System.out.print("Choose your sixth pokemon: ");
        intScan = Integer.parseInt(sc.nextLine());
        pokemon6 = allPokemonTemp.get(intScan - 1);
        break;
      }
      catch(Exception e) {
        System.out.println("Invalid input.");
      }
    }
    allPokemonTemp.remove(pokemon6);
    System.out.print("\033[H\033[2J");

    Trainer player = new Trainer(name, pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, true);

    //Generates random pokemon for opponents
    Collections.shuffle(allPokemon);
    pokemon1 = allPokemon.get(0);
    pokemon2 = allPokemon.get(1);
    pokemon3 = allPokemon.get(2);
    pokemon4 = allPokemon.get(3);
    pokemon5 = allPokemon.get(4);
    pokemon6 = allPokemon.get(5);
    Trainer enemy1 = new Trainer("Coach Witt", pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, false);
    Collections.shuffle(allPokemon);
    pokemon1 = allPokemon.get(0);
    pokemon2 = allPokemon.get(1);
    pokemon3 = allPokemon.get(2);
    pokemon4 = allPokemon.get(3);
    pokemon5 = allPokemon.get(4);
    pokemon6 = allPokemon.get(5);
    Trainer enemy2 = new Trainer("Mr. White", pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, false);
    Collections.shuffle(allPokemon);
    pokemon1 = allPokemon.get(0);
    pokemon2 = allPokemon.get(1);
    pokemon3 = allPokemon.get(2);
    pokemon4 = allPokemon.get(3);
    pokemon5 = allPokemon.get(4);
    pokemon6 = allPokemon.get(5);
    Trainer enemy3 = new Trainer("Mr. Jaggedinski", pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, false);
    // Stores attacks of the player, enemy, the swtiching pokemon and the action (attack or switch)
    Move playerAttack = null;
    Move enemyAttack = null;
    Pokemon switchInto = null;
    String action = null;

    // Prints pre-match information
    player.reset();
    player.update();
    enemy1.update();
    System.out.println("Press [RETURN] to challenge " + enemy1.getName() + " and begin the quarterfinal match!");
    sc.nextLine();
    System.out.print("\033[H\033[2J");
    System.out.println("Why weren't you at practice? Physics club? Likely story.");
    delay();
    System.out.println(enemy1.getName() + " wants to fight!");
    delay();
    System.out.println();
    System.out.println("Go! " + player.getActivePokemon().getName() + "!");
    delay();
    System.out.println(enemy1.getName() + " sent out " + enemy1.getActivePokemon().getName().substring(13) + "!");
    delay();
    player.getActivePokemon().update();
    enemy1.getActivePokemon().update();
    System.out.println();
    enemy1.printInfo();
    System.out.println();
    player.printInfo();

    // The first battle
    int wins = 0;
    while (true) {
      // Checks if user wants to attack or switch
      while(true) {
        try {
          System.out.println();
          System.out.print("Choose an action: ");
          intScan = Integer.parseInt(sc.nextLine());
          if (intScan <= player.getActivePokemon().getValidMoves().size()) {
            playerAttack = player.getActivePokemon().getValidMove(intScan - 1);
            action = "Attack";
          }
          else {
            switchInto = player.getValidSwitch(intScan - player.getActivePokemon().getValidMoves().size() - 1);
            action = "Switch";
          }
          break;
        }
        catch(Exception e) {
          System.out.println("Invalid input.");
        }
      }
      System.out.print("\033[H\033[2J");

      // Generates opponent's move
      enemyAttack = enemy1.getActivePokemon().getValidMove((int)(Math.random() * enemy1.getActivePokemon().getValidMoves().size()));
      if (action.equals("Attack")) {
        // Checks which pokémon moves first and both pokémon perform attacks
        if ((int)(player.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(player.getActivePokemon().getBoost("SPE"))) > (int)(enemy1.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(enemy1.getActivePokemon().getBoost("SPE")))) { 
          player.getActivePokemon().attack(enemy1.getActivePokemon(), playerAttack);
          player.getActivePokemon().update();
          if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy1.getActivePokemon().getStatusEffect("Fainted") == false) {
            System.out.println();
            enemy1.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
            enemy1.getActivePokemon().update();
          }
        }
        else if ((int)(player.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(player.getActivePokemon().getBoost("SPE"))) < (int)(enemy1.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(enemy1.getActivePokemon().getBoost("SPE")))){
          enemy1.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
          enemy1.getActivePokemon().update();
          if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy1.getActivePokemon().getStatusEffect("Fainted") == false) {
            System.out.println();
            player.getActivePokemon().attack(enemy1.getActivePokemon(), playerAttack);
            player.getActivePokemon().update();
          }
        }
        else {
          if (Math.random() < 0.5) {
            player.getActivePokemon().attack(enemy1.getActivePokemon(), playerAttack);
            player.getActivePokemon().update();
            if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy1.getActivePokemon().getStatusEffect("Fainted") == false) {
              System.out.println();
              enemy1.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
              enemy1.getActivePokemon().update();
            }
          }
          else {
            enemy1.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
            enemy1.getActivePokemon().update();
            if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy1.getActivePokemon().getStatusEffect("Fainted") == false) {
              System.out.println();
              player.getActivePokemon().attack(enemy1.getActivePokemon(), playerAttack);
              player.getActivePokemon().update();
            }
          }
        }
      }

      // Switches active pokémon if the user chose to switch
      if (action.equals("Switch")) {
        player.switchPokemon(switchInto);
        player.getActivePokemon().update();
        System.out.println();
        enemy1.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
        enemy1.getActivePokemon().update();
        if (player.getActivePokemon().getStatusEffect("Flinched") == true) {
          player.getActivePokemon().setStatus("Flinched", false);
        }
      }

      // Checks for win and loss
      if (enemy1.defeated() == true) {
        wins += 1;
        System.out.println();
        System.out.println(enemy1.getName() + " has no Pokémon remaining!");
        delay();
        System.out.println(player.getName() + " won the battle!");
        delay();
        System.out.println();
        System.out.println("Press [RETURN] to continue! ");
        sc.nextLine();
        System.out.print("\033[H\033[2J");
        break;
      }
      if (player.defeated() == true) {
        System.out.println();
        System.out.println(player.getName() + " has no Pokémon remaining!");
        delay();
        System.out.println(enemy1.getName() + " won the battle!");
        delay();
        System.out.println();
        System.out.println("Press [RETURN] to continue! ");
        sc.nextLine();
        System.out.print("\033[H\033[2J");
        break;
      }

      // Prompts the user to switch their pokémon if it faints
      if (player.getActivePokemon().getStatusEffect("Fainted") == true) {
        player.update();
        n = 0;
        System.out.println();
        System.out.println("Switch: ");
        for (Pokemon pokemon: player.getValidSwitches()) {
          String typeInfo = "";
          if (pokemon.getType(1) == "Empty") {
            typeInfo = (" (Type: "+ pokemon.getType(0) + ")");
          } 
          else {
            typeInfo = (" (Type: "+ pokemon.getType(0) + ", " + pokemon.getType(1) + ")");
          }
          String statusInfo = "Status: ";
          for (String status: pokemon.getStatusEffects().keySet()) {
            if (pokemon.getStatusEffect(status) == true) {
              statusInfo += status + ", ";
            }
          }
          if (statusInfo.length() == 8) {
            statusInfo += "None";
          }
          else {
            statusInfo = statusInfo.substring(0, statusInfo.length() - 2);
          }
          System.out.println("[" + (n+1) + "] " + pokemon.getName() + typeInfo + " (HP: " + pokemon.getCurrentHp() + "/" + pokemon.getStat("HP") + ") (" + statusInfo + ")");
          n += 1;
        }

        // Prompts for user input
        while(true) {
          try {
            System.out.println();
            System.out.print("Choose an action: ");
            intScan = Integer.parseInt(sc.nextLine());
            switchInto = player.getValidSwitch(intScan - 1);
            action = "Switch";
            break;
          }
          catch(Exception e) {
            System.out.println("Invalid input.");
          }
        }
        System.out.print("\033[H\033[2J");
        player.switchPokemon(switchInto);
        player.getActivePokemon().update();
      }

      // Switches enemy pokémon if they faint
      if (enemy1.getActivePokemon().getStatusEffect("Fainted") == true) {
        enemy1.update();
        switchInto = enemy1.getValidSwitch((int)(Math.random() * enemy1.getValidSwitches().size()));
        System.out.println();
        enemy1.switchPokemon(switchInto);
        enemy1.getActivePokemon().update();
      }

      //Updates validSwitches of both trainers
      player.update();
      enemy1.update();

      // Prints out info of pokémon before continuing to the next turn
      System.out.println();
      enemy1.printInfo();
      System.out.println();
      player.printInfo();
    }

    // The second battle
    if (wins == 1) {
      player.reset();
      player.update();
      enemy2.update();
      System.out.println("Press [RETURN] to challenge " + enemy2.getName() + " and begin the semifinal match!");
      sc.nextLine();
      System.out.print("\033[H\033[2J");
      System.out.println("If I win, I get to take another point off of your test! It makes me feel so good!");
      delay();
      System.out.println(enemy2.getName() + " wants to fight!");
      delay();
      System.out.println();
      System.out.println("Go! " + player.getActivePokemon().getName() + "!");
      delay();
      System.out.println(enemy2.getName() + " sent out " + enemy2.getActivePokemon().getName().substring(13) + "!");
      delay();
      player.getActivePokemon().update();
      enemy2.getActivePokemon().update();
      System.out.println();
      enemy2.printInfo();
      System.out.println();
      player.printInfo();
      while (true) {
        while(true) {
          try {
            System.out.println();
            System.out.print("Choose an action: ");
            intScan = Integer.parseInt(sc.nextLine());
            if (intScan <= player.getActivePokemon().getValidMoves().size()) {
              playerAttack = player.getActivePokemon().getValidMove(intScan - 1);
              action = "Attack";
            }
            else {
              switchInto = player.getValidSwitch(intScan - player.getActivePokemon().getValidMoves().size() - 1);
              action = "Switch";
            }
            break;
          }
          catch(Exception e) {
            System.out.println("Invalid input.");
          }
        }
        System.out.print("\033[H\033[2J");
        
        enemyAttack = enemy2.getActivePokemon().getValidMove((int)(Math.random() * enemy2.getActivePokemon().getValidMoves().size()));
        if (action.equals("Attack")) {
          if ((int)(player.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(player.getActivePokemon().getBoost("SPE"))) > (int)(enemy2.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(enemy2.getActivePokemon().getBoost("SPE")))) {
            player.getActivePokemon().attack(enemy2.getActivePokemon(), playerAttack);
            player.getActivePokemon().update();
            if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy2.getActivePokemon().getStatusEffect("Fainted") == false) {
              System.out.println();
              enemy2.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
              enemy2.getActivePokemon().update();
            }
          }
          else if ((int)(player.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(player.getActivePokemon().getBoost("SPE"))) < (int)(enemy2.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(enemy2.getActivePokemon().getBoost("SPE")))){
            enemy2.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
            enemy2.getActivePokemon().update();
            if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy2.getActivePokemon().getStatusEffect("Fainted") == false) {
              System.out.println();
              player.getActivePokemon().attack(enemy2.getActivePokemon(), playerAttack);
              player.getActivePokemon().update();
            }
          }
          else {
            if (Math.random() < 0.5) {
              player.getActivePokemon().attack(enemy2.getActivePokemon(), playerAttack);
              player.getActivePokemon().update();
              if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy2.getActivePokemon().getStatusEffect("Fainted") == false) {
                System.out.println();
                enemy2.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
                enemy2.getActivePokemon().update();
              }
            }
            else {
              enemy2.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
              enemy2.getActivePokemon().update();
              if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy2.getActivePokemon().getStatusEffect("Fainted") == false) {
                System.out.println();
                player.getActivePokemon().attack(enemy2.getActivePokemon(), playerAttack);
                player.getActivePokemon().update();
              }
            }
          }
        }
        
        if (action.equals("Switch")) {
          player.switchPokemon(switchInto);
          player.getActivePokemon().update();
          System.out.println();
          enemy2.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
          enemy2.getActivePokemon().update();
          if (player.getActivePokemon().getStatusEffect("Flinched") == true) {
            player.getActivePokemon().setStatus("Flinched", false);
          }
        }
  
        if (enemy2.defeated() == true) {
          wins += 1;
          System.out.println();
          System.out.println(enemy2.getName() + " has no Pokémon remaining!");
          delay();
          System.out.println(player.getName() + " won the battle!");
          delay();
          System.out.println();
          System.out.println("Press [RETURN] to continue! ");
          sc.nextLine();
          System.out.print("\033[H\033[2J");
          break;
        }
        if (player.defeated() == true) {
          System.out.println();
          System.out.println(player.getName() + " has no Pokémon remaining!");
          delay();
          System.out.println(enemy1.getName() + " won the battle!");
          delay();
          System.out.println();
          System.out.println("Press [RETURN] to continue! ");
          sc.nextLine();
          System.out.print("\033[H\033[2J");
          break;
        }
        
        if (player.getActivePokemon().getStatusEffect("Fainted") == true) {
          player.update();
          n = 0;
          System.out.println();
          System.out.println("Switch: ");
          for (Pokemon pokemon: player.getValidSwitches()) {
            String typeInfo = "";
            if (pokemon.getType(1) == "Empty") {
              typeInfo = (" (Type: "+ pokemon.getType(0) + ")");
            } 
            else {
              typeInfo = (" (Type: "+ pokemon.getType(0) + ", " + pokemon.getType(1) + ")");
            }
            String statusInfo = "Status: ";
            for (String status: pokemon.getStatusEffects().keySet()) {
              if (pokemon.getStatusEffect(status) == true) {
                statusInfo += status + ", ";
              }
            }
            if (statusInfo.length() == 8) {
              statusInfo += "None";
            }
            else {
              statusInfo = statusInfo.substring(0, statusInfo.length() - 2);
            }
            System.out.println("[" + (n+1) + "] " + pokemon.getName() + typeInfo + " (HP: " + pokemon.getCurrentHp() + "/" + pokemon.getStat("HP") + ") (" + statusInfo + ")");
            n += 1;
          }
          while(true) {
            try {
              System.out.println();
              System.out.print("Choose an action: ");
              intScan = Integer.parseInt(sc.nextLine());
              switchInto = player.getValidSwitch(intScan - 1);
              action = "Switch";
              break;
            }
            catch(Exception e) {
              System.out.println("Invalid input.");
            }
          }
          System.out.print("\033[H\033[2J");
          player.switchPokemon(switchInto);
          player.getActivePokemon().update();
        }
        
        if (enemy2.getActivePokemon().getStatusEffect("Fainted") == true) {
          enemy2.update();
          switchInto = enemy2.getValidSwitch((int)(Math.random() * enemy2.getValidSwitches().size()));
          System.out.println();
          enemy2.switchPokemon(switchInto);
          enemy2.getActivePokemon().update();
        }
        
        player.update();
        enemy2.update();
  
        System.out.println();
        enemy2.printInfo();
        System.out.println();
        player.printInfo();
      }
    }

    // The third battle
    if (wins == 2) {
      player.reset();
      player.update();
      enemy1.update();
      System.out.println("Press [RETURN] to challenge " + enemy3.getName() + " and begin the final match!");
      sc.nextLine();
      System.out.print("\033[H\033[2J");
      System.out.println("Hey, you said my name wrong!");
      delay();
      System.out.println(enemy3.getName() + " wants to fight!");
      delay();
      System.out.println();
      System.out.println("Go! " + player.getActivePokemon().getName() + "!");
      delay();
      System.out.println(enemy3.getName() + " sent out " + enemy3.getActivePokemon().getName().substring(13) + "!");
      delay();
      player.getActivePokemon().update();
      enemy3.getActivePokemon().update();
      System.out.println();
      enemy3.printInfo();
      System.out.println();
      player.printInfo();
      while (true) {
        while(true) {
          try {
            System.out.println();
            System.out.print("Choose an action: ");
            intScan = Integer.parseInt(sc.nextLine());
            if (intScan <= player.getActivePokemon().getValidMoves().size()) {
              playerAttack = player.getActivePokemon().getValidMove(intScan - 1);
              action = "Attack";
            }
            else {
              switchInto = player.getValidSwitch(intScan - player.getActivePokemon().getValidMoves().size() - 1);
              action = "Switch";
            }
            break;
          }
          catch(Exception e) {
            System.out.println("Invalid input.");
          }
        }
        System.out.print("\033[H\033[2J");
        
        enemyAttack = enemy3.getActivePokemon().getValidMove((int)(Math.random() * enemy3.getActivePokemon().getValidMoves().size()));
        if (action.equals("Attack")) {
          if ((int)(player.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(player.getActivePokemon().getBoost("SPE"))) > (int)(enemy3.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(enemy3.getActivePokemon().getBoost("SPE")))) {
            player.getActivePokemon().attack(enemy3.getActivePokemon(), playerAttack);
            player.getActivePokemon().update();
            if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy3.getActivePokemon().getStatusEffect("Fainted") == false) {
              System.out.println();
              enemy3.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
              enemy3.getActivePokemon().update();
            }
          }
          else if ((int)(player.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(player.getActivePokemon().getBoost("SPE"))) < (int)(enemy3.getActivePokemon().getCurrentSpeed()*getBoostMultiplier(enemy3.getActivePokemon().getBoost("SPE")))){
            enemy3.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
            enemy3.getActivePokemon().update();
            if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy3.getActivePokemon().getStatusEffect("Fainted") == false) {
              System.out.println();
              player.getActivePokemon().attack(enemy3.getActivePokemon(), playerAttack);
              player.getActivePokemon().update();
            }
          }
          else {
            if (Math.random() < 0.5) {
              player.getActivePokemon().attack(enemy3.getActivePokemon(), playerAttack);
              player.getActivePokemon().update();
              if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy3.getActivePokemon().getStatusEffect("Fainted") == false) {
                System.out.println();
                enemy3.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
                enemy3.getActivePokemon().update();
              }
            }
            else {
              enemy3.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
              enemy3.getActivePokemon().update();
              if (player.getActivePokemon().getStatusEffect("Fainted") == false && enemy3.getActivePokemon().getStatusEffect("Fainted") == false) {
                System.out.println();
                player.getActivePokemon().attack(enemy3.getActivePokemon(), playerAttack);
                player.getActivePokemon().update();
              }
            }
          }
        }
        
        if (action.equals("Switch")) {
          player.switchPokemon(switchInto);
          player.getActivePokemon().update();
          System.out.println();
          enemy3.getActivePokemon().attack(player.getActivePokemon(), enemyAttack);
          enemy3.getActivePokemon().update();
          if (player.getActivePokemon().getStatusEffect("Flinched") == true) {
            player.getActivePokemon().setStatus("Flinched", false);
          }
        }
  
        if (enemy3.defeated() == true) {
          wins += 1;
          System.out.println();
          System.out.println(enemy3.getName() + " has no Pokémon remaining!");
          delay();
          System.out.println(player.getName() + " won the battle!");
          delay();
          System.out.println();
          System.out.println("Press [RETURN] to continue! ");
          sc.nextLine();
          System.out.print("\033[H\033[2J");
          break;
        }
        if (player.defeated() == true) {
          System.out.println();
          System.out.println(player.getName() + " has no Pokémon remaining!");
          delay();
          System.out.println(enemy3.getName() + " won the battle!");
          delay();
          System.out.println();
          System.out.println("Press [RETURN] to continue! ");
          sc.nextLine();
          System.out.print("\033[H\033[2J");
          break;
        }
        
        if (player.getActivePokemon().getStatusEffect("Fainted") == true) {
          player.update();
          n = 0;
          System.out.println();
          System.out.println("Switch: ");
          for (Pokemon pokemon: player.getValidSwitches()) {
            String typeInfo = "";
            if (pokemon.getType(1) == "Empty") {
              typeInfo = (" (Type: "+ pokemon.getType(0) + ")");
            } 
            else {
              typeInfo = (" (Type: "+ pokemon.getType(0) + ", " + pokemon.getType(1) + ")");
            }
            String statusInfo = "Status: ";
            for (String status: pokemon.getStatusEffects().keySet()) {
              if (pokemon.getStatusEffect(status) == true) {
                statusInfo += status + ", ";
              }
            }
            if (statusInfo.length() == 8) {
              statusInfo += "None";
            }
            else {
              statusInfo = statusInfo.substring(0, statusInfo.length() - 2);
            }
            System.out.println("[" + (n+1) + "] " + pokemon.getName() + typeInfo + " (HP: " + pokemon.getCurrentHp() + "/" + pokemon.getStat("HP") + ") (" + statusInfo + ")");
            n += 1;
          }
          while(true) {
            try {
              System.out.println();
              System.out.print("Choose an action: ");
              intScan = Integer.parseInt(sc.nextLine());
              switchInto = player.getValidSwitch(intScan - 1);
              action = "Switch";
              break;
            }
            catch(Exception e) {
              System.out.println("Invalid input.");
            }
          }
          System.out.print("\033[H\033[2J");
          player.switchPokemon(switchInto);
          player.getActivePokemon().update();
        }
        
        if (enemy3.getActivePokemon().getStatusEffect("Fainted") == true) {
          enemy3.update();
          switchInto = enemy3.getValidSwitch((int)(Math.random() * enemy3.getValidSwitches().size()));
          System.out.println();
          enemy3.switchPokemon(switchInto);
          enemy3.getActivePokemon().update();
        }
        
        player.update();
        enemy3.update();
  
        System.out.println();
        enemy3.printInfo();
        System.out.println();
        player.printInfo();
      }
    }

    // End of game messages
    if (wins == 0 || wins == 1 || wins == 2) {
      System.out.println("You were eliminated from the Pokémon League!");
      delay();
      System.out.println("Better luck next time!");
    }
    if (wins == 3) {
      System.out.println("You won the Pokémon League!");
      delay();
      System.out.println();
      System.out.println("Hall of Fame: ");
      delay();
      System.out.println("Trainer: " + player.getName());
      delay();
      n = 0;
      for (Pokemon pokemon: player.getTeam()) {
        System.out.println("Pokémon " + (n+1) + ": " + pokemon.getName());
        delay();
        n++;
      }
      System.out.println();
      System.out.println("Congratulations!");
    }
    
    sc.close();
  }
}
