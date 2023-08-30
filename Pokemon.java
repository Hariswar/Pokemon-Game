import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Pokemon {
  // Delays time for one second
  private static void delay() {
    try {
      TimeUnit.SECONDS.sleep(1);
    }
    catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  // Creates the variables
  private final String name;
  private final boolean ally;
  private final ArrayList<String> type = new ArrayList<>();
  private final ArrayList<Move> moves = new ArrayList<>();
  private final Move recharge = new Move("Recharge", "Normal", "Physical", 0, 0, 0, 0, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  private final Move struggle = new Move("Struggle", "Normal", "Physical", 0, 50, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Burned", 0);
  private final Move continueWrap = new Move("Continue Wrap", "Normal", "Physical", 0, 0, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Wrapped", 1);
  private final Move continueClamp = new Move("Continue Clamp", "Water", "Physical", 0, 0, 0, 1, "ATK", 0, 0, "ATK", 0, 0, "Clamped", 1);
  private final HashMap<String, Integer> baseStats = new HashMap<>();
  private final HashMap<String, Integer> stats = new HashMap<>();
  private HashMap<String, Integer> boosts = new HashMap<>();
  private HashMap<String, Boolean> statusEffect = new HashMap<>();
  
  private int currentHp;
  private int currentSpeed;
  private int currentAttack;
  private int sleepCounter;
  private int toxicCounter;
  private int wrapCounter;
  private int confusionCounter;
  private int wrapDamage;
  private ArrayList<Move> validMoves = new ArrayList<>();

  //Creates the hash map for the boost multipliers and the type chart
  private static final HashMap<Integer, Double> boostMultipliers = new HashMap<>();
  private static final HashMap<String, HashMap<String, ArrayList<String>>> typeChart = new HashMap<>();

  // Constructor
  public Pokemon(String name, String type1, String type2, Move move1, Move move2, Move move3, Move move4, int hp, int atk, int def, int spc, int spe){
    this.name = name;
    this.ally = true;
    
    this.type.add(type1);
    this.type.add(type2);

    this.moves.add(move1);
    this.moves.add(move2);
    this.moves.add(move3);
    this.moves.add(move4);
    
    this.baseStats.put("HP", hp);
    this.baseStats.put("ATK", atk);
    this.baseStats.put("SPC", spc);
    this.baseStats.put("DEF", def);
    this.baseStats.put("SPE", spe);

    this.stats.put("HP", 2*hp+203);
    this.stats.put("ATK", 2*atk+98);
    this.stats.put("DEF", 2*def+98);
    this.stats.put("SPC", 2*spc+98);
    this.stats.put("SPE", 2*spe+98);
    
    this.statusEffect.put("Burned", false);
    this.statusEffect.put("Frozen", false);
    this.statusEffect.put("Paralyzed", false);
    this.statusEffect.put("Asleep", false);
    this.statusEffect.put("Badly Poisoned", false);
    this.statusEffect.put("Poisoned", false);
    this.statusEffect.put("Confused", false);
    this.statusEffect.put("Flinched", false);
    this.statusEffect.put("Recharging", false);
    this.statusEffect.put("Wrapping", false);
    this.statusEffect.put("Wrapped", false);
    this.statusEffect.put("Clamping", false);
    this.statusEffect.put("Clamped", false);
    this.statusEffect.put("Reflect", false);
    this.statusEffect.put("Fainted" , false);
    
    
    this.boosts.put("ATK", 0);
    this.boosts.put("SPC", 0);
    this.boosts.put("DEF", 0);
    this.boosts.put("SPE", 0);
    this.boosts.put("EVA", 0);
    this.boosts.put("ACC", 0);

    this.currentHp = 2*hp+203;
    this.currentSpeed = 2*spe+98;
    this.currentAttack = 2*atk+98;
    this.sleepCounter = 0;
    this.toxicCounter = 0;
    this.wrapCounter = 0;
    this.confusionCounter = 0;
    
    this.validMoves.add(move1);
    this.validMoves.add(move2);
    this.validMoves.add(move3);
    this.validMoves.add(move4);
    
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

    HashMap<String, ArrayList<String>> bugMap = new HashMap<>();
    ArrayList<String> bugWeakness = new ArrayList<>();
    ArrayList<String> bugResistance = new ArrayList<>();
    ArrayList<String> bugImmunity = new ArrayList<>();
    bugMap.put("Weakness", bugWeakness);
    bugMap.put("Resistance", bugResistance);
    bugMap.put("Immunity", bugImmunity);
    HashMap<String, ArrayList<String>> dragonMap = new HashMap<>();
    ArrayList<String> dragonWeakness = new ArrayList<>();
    ArrayList<String> dragonResistance = new ArrayList<>();
    ArrayList<String> dragonImmunity = new ArrayList<>();
    dragonMap.put("Weakness", dragonWeakness);
    dragonMap.put("Resistance", dragonResistance);
    dragonMap.put("Immunity", dragonImmunity);
    HashMap<String, ArrayList<String>> electricMap = new HashMap<>();
    ArrayList<String> electricWeakness = new ArrayList<>();
    ArrayList<String> electricResistance = new ArrayList<>();
    ArrayList<String> electricImmunity = new ArrayList<>();
    electricMap.put("Weakness", electricWeakness);
    electricMap.put("Resistance", electricResistance);
    electricMap.put("Immunity", electricImmunity);
    HashMap<String, ArrayList<String>> fightingMap = new HashMap<>();
    ArrayList<String> fightingWeakness = new ArrayList<>();
    ArrayList<String> fightingResistance = new ArrayList<>();
    ArrayList<String> fightingImmunity = new ArrayList<>();
    fightingMap.put("Weakness", fightingWeakness);
    fightingMap.put("Resistance", fightingResistance);
    fightingMap.put("Immunity", fightingImmunity);
    HashMap<String, ArrayList<String>> fireMap = new HashMap<>();
    ArrayList<String> fireWeakness = new ArrayList<>();
    ArrayList<String> fireResistance = new ArrayList<>();
    ArrayList<String> fireImmunity = new ArrayList<>();
    fireMap.put("Weakness", fireWeakness);
    fireMap.put("Resistance", fireResistance);
    fireMap.put("Immunity", fireImmunity);
    HashMap<String, ArrayList<String>> flyingMap = new HashMap<>();
    ArrayList<String> flyingWeakness = new ArrayList<>();
    ArrayList<String> flyingResistance = new ArrayList<>();
    ArrayList<String> flyingImmunity = new ArrayList<>();
    flyingMap.put("Weakness", flyingWeakness);
    flyingMap.put("Resistance", flyingResistance);
    flyingMap.put("Immunity", flyingImmunity);
    HashMap<String, ArrayList<String>> ghostMap = new HashMap<>();
    ArrayList<String> ghostWeakness = new ArrayList<>();
    ArrayList<String> ghostResistance = new ArrayList<>();
    ArrayList<String> ghostImmunity = new ArrayList<>();
    ghostMap.put("Weakness", ghostWeakness);
    ghostMap.put("Resistance", ghostResistance);
    ghostMap.put("Immunity", ghostImmunity);
    HashMap<String, ArrayList<String>> grassMap = new HashMap<>();
    ArrayList<String> grassWeakness = new ArrayList<>();
    ArrayList<String> grassResistance = new ArrayList<>();
    ArrayList<String> grassImmunity = new ArrayList<>();
    grassMap.put("Weakness", grassWeakness);
    grassMap.put("Resistance", grassResistance);
    grassMap.put("Immunity", grassImmunity);
    HashMap<String, ArrayList<String>> groundMap = new HashMap<>();
    ArrayList<String> groundWeakness = new ArrayList<>();
    ArrayList<String> groundResistance = new ArrayList<>();
    ArrayList<String> groundImmunity = new ArrayList<>();
    groundMap.put("Weakness", groundWeakness);
    groundMap.put("Resistance", groundResistance);
    groundMap.put("Immunity", groundImmunity);
    HashMap<String, ArrayList<String>> iceMap = new HashMap<>();
    ArrayList<String> iceWeakness = new ArrayList<>();
    ArrayList<String> iceResistance = new ArrayList<>();
    ArrayList<String> iceImmunity = new ArrayList<>();
    iceMap.put("Weakness", iceWeakness);
    iceMap.put("Resistance", iceResistance);
    iceMap.put("Immunity", iceImmunity);
    HashMap<String, ArrayList<String>> normalMap = new HashMap<>();
    ArrayList<String> normalWeakness = new ArrayList<>();
    ArrayList<String> normalResistance = new ArrayList<>();
    ArrayList<String> normalImmunity = new ArrayList<>();
    normalMap.put("Weakness", normalWeakness);
    normalMap.put("Resistance", normalResistance);
    normalMap.put("Immunity", normalImmunity);
    HashMap<String, ArrayList<String>> poisonMap = new HashMap<>();
    ArrayList<String> poisonWeakness = new ArrayList<>();
    ArrayList<String> poisonResistance = new ArrayList<>();
    ArrayList<String> poisonImmunity = new ArrayList<>();
    poisonMap.put("Weakness", poisonWeakness);
    poisonMap.put("Resistance", poisonResistance);
    poisonMap.put("Immunity", poisonImmunity);
    HashMap<String, ArrayList<String>> psychicMap = new HashMap<>();
    ArrayList<String> psychicWeakness = new ArrayList<>();
    ArrayList<String> psychicResistance = new ArrayList<>();
    ArrayList<String> psychicImmunity = new ArrayList<>();
    psychicMap.put("Weakness", psychicWeakness);
    psychicMap.put("Resistance", psychicResistance);
    psychicMap.put("Immunity", psychicImmunity);
    HashMap<String, ArrayList<String>> rockMap = new HashMap<>();
    ArrayList<String> rockWeakness = new ArrayList<>();
    ArrayList<String> rockResistance = new ArrayList<>();
    ArrayList<String> rockImmunity = new ArrayList<>();
    rockMap.put("Weakness", rockWeakness);
    rockMap.put("Resistance", rockResistance);
    rockMap.put("Immunity", rockImmunity);
    HashMap<String, ArrayList<String>> waterMap = new HashMap<>();
    ArrayList<String> waterWeakness = new ArrayList<>();
    ArrayList<String> waterResistance = new ArrayList<>();
    ArrayList<String> waterImmunity = new ArrayList<>(); 
    waterMap.put("Weakness", waterWeakness);
    waterMap.put("Resistance", waterResistance);
    waterMap.put("Immunity", waterImmunity);
    HashMap<String, ArrayList<String>> emptyMap = new HashMap<>();
    ArrayList<String> emptyWeakness = new ArrayList<>();
    ArrayList<String> emptyResistance = new ArrayList<>();
    ArrayList<String> emptyImmunity = new ArrayList<>();  
    emptyMap.put("Weakness", emptyWeakness);
    emptyMap.put("Resistance", emptyResistance);
    emptyMap.put("Immunity", emptyImmunity);
    typeChart.put("Bug", bugMap);
    typeChart.put("Dragon", dragonMap);
    typeChart.put("Electric", electricMap);
    typeChart.put("Fighting", fightingMap);
    typeChart.put("Fire", fireMap);
    typeChart.put("Flying", flyingMap);
    typeChart.put("Ghost", ghostMap);
    typeChart.put("Grass", grassMap);
    typeChart.put("Ground", groundMap);
    typeChart.put("Ice", iceMap);
    typeChart.put("Normal", normalMap);
    typeChart.put("Poison", poisonMap);
    typeChart.put("Psychic", psychicMap);
    typeChart.put("Rock", rockMap);
    typeChart.put("Water", waterMap);
    typeChart.put("Empty", emptyMap);
    typeChart.get("Bug").get("Weakness").add("Fire");
    typeChart.get("Bug").get("Weakness").add("Flying");
    typeChart.get("Bug").get("Weakness").add("Poison");
    typeChart.get("Bug").get("Weakness").add("Rock");
    typeChart.get("Bug").get("Resistance").add("Fighting");
    typeChart.get("Bug").get("Resistance").add("Ground");
    typeChart.get("Bug").get("Resistance").add("Grass"); 
    typeChart.get("Dragon").get("Weakness").add("Dragon");
    typeChart.get("Dragon").get("Weakness").add("Ice");
    typeChart.get("Dragon").get("Resistance").add("Electric");
    typeChart.get("Dragon").get("Resistance").add("Fire");
    typeChart.get("Dragon").get("Resistance").add("Grass");
    typeChart.get("Dragon").get("Resistance").add("Water"); 
    typeChart.get("Electric").get("Weakness").add("Ground");
    typeChart.get("Electric").get("Resistance").add("Electric");
    typeChart.get("Electric").get("Resistance").add("Flying");
    typeChart.get("Fighting").get("Weakness").add("Flying");
    typeChart.get("Fighting").get("Weakness").add("Psychic");
    typeChart.get("Fighting").get("Resistance").add("Bug");
    typeChart.get("Fighting").get("Resistance").add("Rock");
    typeChart.get("Fire").get("Weakness").add("Ground");
    typeChart.get("Fire").get("Weakness").add("Rock");
    typeChart.get("Fire").get("Weakness").add("Water");
    typeChart.get("Fire").get("Resistance").add("Bug");
    typeChart.get("Fire").get("Resistance").add("Fire");
    typeChart.get("Fire").get("Resistance").add("Grass");
    typeChart.get("Flying").get("Weakness").add("Electric");
    typeChart.get("Flying").get("Weakness").add("Ice");
    typeChart.get("Flying").get("Weakness").add("Rock");
    typeChart.get("Flying").get("Resistance").add("Bug");
    typeChart.get("Flying").get("Resistance").add("Fighting");
    typeChart.get("Flying").get("Resistance").add("Grass");
    typeChart.get("Flying").get("Immunity").add("Ground");
    typeChart.get("Ghost").get("Weakness").add("Ghost");
    typeChart.get("Ghost").get("Resistance").add("Poison");
    typeChart.get("Ghost").get("Resistance").add("Bug");
    typeChart.get("Ghost").get("Immunity").add("Normal");
    typeChart.get("Ghost").get("Immunity").add("Fighting");
    typeChart.get("Grass").get("Weakness").add("Fire");
    typeChart.get("Grass").get("Weakness").add("Ice");
    typeChart.get("Grass").get("Weakness").add("Poison");
    typeChart.get("Grass").get("Weakness").add("Flying");
    typeChart.get("Grass").get("Weakness").add("Bug");
    typeChart.get("Grass").get("Resistance").add("Water");
    typeChart.get("Grass").get("Resistance").add("Electric");
    typeChart.get("Grass").get("Resistance").add("Grass");
    typeChart.get("Grass").get("Resistance").add("Ground");
    typeChart.get("Ground").get("Weakness").add("Water");
    typeChart.get("Ground").get("Weakness").add("Grass");
    typeChart.get("Ground").get("Weakness").add("Ice");
    typeChart.get("Ground").get("Resistance").add("Poison");
    typeChart.get("Ground").get("Resistance").add("Rock");
    typeChart.get("Ground").get("Immunity").add("Electric");
    typeChart.get("Ice").get("Weakness").add("Fire");
    typeChart.get("Ice").get("Weakness").add("Fighting");
    typeChart.get("Ice").get("Weakness").add("Rock");
    typeChart.get("Ice").get("Resistance").add("Ice");
    typeChart.get("Normal").get("Weakness").add("Fighting");
    typeChart.get("Normal").get("Immunity").add("Ghost");
    typeChart.get("Poison").get("Weakness").add("Ground");
    typeChart.get("Poison").get("Weakness").add("Psychic");
    typeChart.get("Poison").get("Weakness").add("Bug");
    typeChart.get("Poison").get("Resistance").add("Grass");
    typeChart.get("Poison").get("Resistance").add("Fighting");
    typeChart.get("Poison").get("Resistance").add("Poison");
    typeChart.get("Psychic").get("Weakness").add("Bug");
    typeChart.get("Psychic").get("Resistance").add("Psychic");
    typeChart.get("Psychic").get("Resistance").add("Fighting");
    typeChart.get("Psychic").get("Immunity").add("Ghost");    
    typeChart.get("Rock").get("Weakness").add("Fighting");
    typeChart.get("Rock").get("Weakness").add("Ground");
    typeChart.get("Rock").get("Weakness").add("Grass");
    typeChart.get("Rock").get("Weakness").add("Water");
    typeChart.get("Rock").get("Resistance").add("Normal");
    typeChart.get("Rock").get("Resistance").add("Poison");
    typeChart.get("Rock").get("Resistance").add("Flying");
    typeChart.get("Rock").get("Resistance").add("Fire");
    typeChart.get("Water").get("Weakness").add("Electric");
    typeChart.get("Water").get("Weakness").add("Grass");
    typeChart.get("Water").get("Resistance").add("Water");
    typeChart.get("Water").get("Resistance").add("Fire");
    typeChart.get("Water").get("Resistance").add("Ice");
  }

  // Copy constructor
  public Pokemon(Pokemon pokemon, boolean ally){
    if (ally == true) {
      this.name = pokemon.name;
    }
    else {
      this.name = "The opposing " + pokemon.name;
    }
    this.ally = ally;
    
    this.type.add(pokemon.type.get(0));
    this.type.add(pokemon.type.get(1));

    this.moves.add(new Move(pokemon.moves.get(0)));
    this.moves.add(new Move(pokemon.moves.get(1)));
    this.moves.add(new Move(pokemon.moves.get(2)));
    this.moves.add(new Move(pokemon.moves.get(3)));
    
    this.baseStats.put("HP", pokemon.baseStats.get("HP"));
    this.baseStats.put("ATK", pokemon.baseStats.get("ATK"));
    this.baseStats.put("SPC", pokemon.baseStats.get("SPC"));
    this.baseStats.put("DEF", pokemon.baseStats.get("DEF"));
    this.baseStats.put("SPE", pokemon.baseStats.get("SPE"));

    this.stats.put("HP", pokemon.stats.get("HP"));
    this.stats.put("ATK", pokemon.stats.get("ATK"));
    this.stats.put("DEF", pokemon.stats.get("DEF"));
    this.stats.put("SPC", pokemon.stats.get("SPC"));
    this.stats.put("SPE", pokemon.stats.get("SPE"));
    
    this.statusEffect.put("Burned", false);
    this.statusEffect.put("Frozen", false);
    this.statusEffect.put("Paralyzed", false);
    this.statusEffect.put("Asleep", false);
    this.statusEffect.put("Badly Poisoned", false);
    this.statusEffect.put("Poisoned", false);
    this.statusEffect.put("Confused", false);
    this.statusEffect.put("Flinched", false);
    this.statusEffect.put("Recharging", false);
    this.statusEffect.put("Wrapping", false);
    this.statusEffect.put("Wrapped", false);
    this.statusEffect.put("Clamping", false);
    this.statusEffect.put("Clamped", false);
    this.statusEffect.put("Fainted" , false);
    this.statusEffect.put("Reflect", false);
    
    this.boosts.put("ATK", 0);
    this.boosts.put("SPC", 0);
    this.boosts.put("DEF", 0);
    this.boosts.put("SPE", 0);
    this.boosts.put("EVA", 0);
    this.boosts.put("ACC", 0);

    this.currentHp = pokemon.stats.get("HP");
    this.currentSpeed = pokemon.stats.get("SPE");
    this.currentAttack = pokemon.stats.get("ATK");
    this.sleepCounter = 0;
    this.toxicCounter = 0;
    this.wrapCounter = 0;
    this.confusionCounter = 0;
    
    this.validMoves.add(this.moves.get(0));
    this.validMoves.add(this.moves.get(1));
    this.validMoves.add(this.moves.get(2));
    this.validMoves.add(this.moves.get(3));
    
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

    HashMap<String, ArrayList<String>> bugMap = new HashMap<>();
    ArrayList<String> bugWeakness = new ArrayList<>();
    ArrayList<String> bugResistance = new ArrayList<>();
    ArrayList<String> bugImmunity = new ArrayList<>();
    bugMap.put("Weakness", bugWeakness);
    bugMap.put("Resistance", bugResistance);
    bugMap.put("Immunity", bugImmunity);
    HashMap<String, ArrayList<String>> dragonMap = new HashMap<>();
    ArrayList<String> dragonWeakness = new ArrayList<>();
    ArrayList<String> dragonResistance = new ArrayList<>();
    ArrayList<String> dragonImmunity = new ArrayList<>();
    dragonMap.put("Weakness", dragonWeakness);
    dragonMap.put("Resistance", dragonResistance);
    dragonMap.put("Immunity", dragonImmunity);
    HashMap<String, ArrayList<String>> electricMap = new HashMap<>();
    ArrayList<String> electricWeakness = new ArrayList<>();
    ArrayList<String> electricResistance = new ArrayList<>();
    ArrayList<String> electricImmunity = new ArrayList<>();
    electricMap.put("Weakness", electricWeakness);
    electricMap.put("Resistance", electricResistance);
    electricMap.put("Immunity", electricImmunity);
    HashMap<String, ArrayList<String>> fightingMap = new HashMap<>();
    ArrayList<String> fightingWeakness = new ArrayList<>();
    ArrayList<String> fightingResistance = new ArrayList<>();
    ArrayList<String> fightingImmunity = new ArrayList<>();
    fightingMap.put("Weakness", fightingWeakness);
    fightingMap.put("Resistance", fightingResistance);
    fightingMap.put("Immunity", fightingImmunity);
    HashMap<String, ArrayList<String>> fireMap = new HashMap<>();
    ArrayList<String> fireWeakness = new ArrayList<>();
    ArrayList<String> fireResistance = new ArrayList<>();
    ArrayList<String> fireImmunity = new ArrayList<>();
    fireMap.put("Weakness", fireWeakness);
    fireMap.put("Resistance", fireResistance);
    fireMap.put("Immunity", fireImmunity);
    HashMap<String, ArrayList<String>> flyingMap = new HashMap<>();
    ArrayList<String> flyingWeakness = new ArrayList<>();
    ArrayList<String> flyingResistance = new ArrayList<>();
    ArrayList<String> flyingImmunity = new ArrayList<>();
    flyingMap.put("Weakness", flyingWeakness);
    flyingMap.put("Resistance", flyingResistance);
    flyingMap.put("Immunity", flyingImmunity);
    HashMap<String, ArrayList<String>> ghostMap = new HashMap<>();
    ArrayList<String> ghostWeakness = new ArrayList<>();
    ArrayList<String> ghostResistance = new ArrayList<>();
    ArrayList<String> ghostImmunity = new ArrayList<>();
    ghostMap.put("Weakness", ghostWeakness);
    ghostMap.put("Resistance", ghostResistance);
    ghostMap.put("Immunity", ghostImmunity);
    HashMap<String, ArrayList<String>> grassMap = new HashMap<>();
    ArrayList<String> grassWeakness = new ArrayList<>();
    ArrayList<String> grassResistance = new ArrayList<>();
    ArrayList<String> grassImmunity = new ArrayList<>();
    grassMap.put("Weakness", grassWeakness);
    grassMap.put("Resistance", grassResistance);
    grassMap.put("Immunity", grassImmunity);
    HashMap<String, ArrayList<String>> groundMap = new HashMap<>();
    ArrayList<String> groundWeakness = new ArrayList<>();
    ArrayList<String> groundResistance = new ArrayList<>();
    ArrayList<String> groundImmunity = new ArrayList<>();
    groundMap.put("Weakness", groundWeakness);
    groundMap.put("Resistance", groundResistance);
    groundMap.put("Immunity", groundImmunity);
    HashMap<String, ArrayList<String>> iceMap = new HashMap<>();
    ArrayList<String> iceWeakness = new ArrayList<>();
    ArrayList<String> iceResistance = new ArrayList<>();
    ArrayList<String> iceImmunity = new ArrayList<>();
    iceMap.put("Weakness", iceWeakness);
    iceMap.put("Resistance", iceResistance);
    iceMap.put("Immunity", iceImmunity);
    HashMap<String, ArrayList<String>> normalMap = new HashMap<>();
    ArrayList<String> normalWeakness = new ArrayList<>();
    ArrayList<String> normalResistance = new ArrayList<>();
    ArrayList<String> normalImmunity = new ArrayList<>();
    normalMap.put("Weakness", normalWeakness);
    normalMap.put("Resistance", normalResistance);
    normalMap.put("Immunity", normalImmunity);
    HashMap<String, ArrayList<String>> poisonMap = new HashMap<>();
    ArrayList<String> poisonWeakness = new ArrayList<>();
    ArrayList<String> poisonResistance = new ArrayList<>();
    ArrayList<String> poisonImmunity = new ArrayList<>();
    poisonMap.put("Weakness", poisonWeakness);
    poisonMap.put("Resistance", poisonResistance);
    poisonMap.put("Immunity", poisonImmunity);
    HashMap<String, ArrayList<String>> psychicMap = new HashMap<>();
    ArrayList<String> psychicWeakness = new ArrayList<>();
    ArrayList<String> psychicResistance = new ArrayList<>();
    ArrayList<String> psychicImmunity = new ArrayList<>();
    psychicMap.put("Weakness", psychicWeakness);
    psychicMap.put("Resistance", psychicResistance);
    psychicMap.put("Immunity", psychicImmunity);
    HashMap<String, ArrayList<String>> rockMap = new HashMap<>();
    ArrayList<String> rockWeakness = new ArrayList<>();
    ArrayList<String> rockResistance = new ArrayList<>();
    ArrayList<String> rockImmunity = new ArrayList<>();
    rockMap.put("Weakness", rockWeakness);
    rockMap.put("Resistance", rockResistance);
    rockMap.put("Immunity", rockImmunity);
    HashMap<String, ArrayList<String>> waterMap = new HashMap<>();
    ArrayList<String> waterWeakness = new ArrayList<>();
    ArrayList<String> waterResistance = new ArrayList<>();
    ArrayList<String> waterImmunity = new ArrayList<>(); 
    waterMap.put("Weakness", waterWeakness);
    waterMap.put("Resistance", waterResistance);
    waterMap.put("Immunity", waterImmunity);
    HashMap<String, ArrayList<String>> emptyMap = new HashMap<>();
    ArrayList<String> emptyWeakness = new ArrayList<>();
    ArrayList<String> emptyResistance = new ArrayList<>();
    ArrayList<String> emptyImmunity = new ArrayList<>();  
    emptyMap.put("Weakness", emptyWeakness);
    emptyMap.put("Resistance", emptyResistance);
    emptyMap.put("Immunity", emptyImmunity);
    typeChart.put("Bug", bugMap);
    typeChart.put("Dragon", dragonMap);
    typeChart.put("Electric", electricMap);
    typeChart.put("Fighting", fightingMap);
    typeChart.put("Fire", fireMap);
    typeChart.put("Flying", flyingMap);
    typeChart.put("Ghost", ghostMap);
    typeChart.put("Grass", grassMap);
    typeChart.put("Ground", groundMap);
    typeChart.put("Ice", iceMap);
    typeChart.put("Normal", normalMap);
    typeChart.put("Poison", poisonMap);
    typeChart.put("Psychic", psychicMap);
    typeChart.put("Rock", rockMap);
    typeChart.put("Water", waterMap);
    typeChart.put("Empty", emptyMap);
    typeChart.get("Bug").get("Weakness").add("Fire");
    typeChart.get("Bug").get("Weakness").add("Flying");
    typeChart.get("Bug").get("Weakness").add("Poison");
    typeChart.get("Bug").get("Weakness").add("Rock");
    typeChart.get("Bug").get("Resistance").add("Fighting");
    typeChart.get("Bug").get("Resistance").add("Ground");
    typeChart.get("Bug").get("Resistance").add("Grass"); 
    typeChart.get("Dragon").get("Weakness").add("Dragon");
    typeChart.get("Dragon").get("Weakness").add("Ice");
    typeChart.get("Dragon").get("Resistance").add("Electric");
    typeChart.get("Dragon").get("Resistance").add("Fire");
    typeChart.get("Dragon").get("Resistance").add("Grass");
    typeChart.get("Dragon").get("Resistance").add("Water"); 
    typeChart.get("Electric").get("Weakness").add("Ground");
    typeChart.get("Electric").get("Resistance").add("Electric");
    typeChart.get("Electric").get("Resistance").add("Flying");
    typeChart.get("Fighting").get("Weakness").add("Flying");
    typeChart.get("Fighting").get("Weakness").add("Psychic");
    typeChart.get("Fighting").get("Resistance").add("Bug");
    typeChart.get("Fighting").get("Resistance").add("Rock");
    typeChart.get("Fire").get("Weakness").add("Ground");
    typeChart.get("Fire").get("Weakness").add("Rock");
    typeChart.get("Fire").get("Weakness").add("Water");
    typeChart.get("Fire").get("Resistance").add("Bug");
    typeChart.get("Fire").get("Resistance").add("Fire");
    typeChart.get("Fire").get("Resistance").add("Grass");
    typeChart.get("Flying").get("Weakness").add("Electric");
    typeChart.get("Flying").get("Weakness").add("Ice");
    typeChart.get("Flying").get("Weakness").add("Rock");
    typeChart.get("Flying").get("Resistance").add("Bug");
    typeChart.get("Flying").get("Resistance").add("Fighting");
    typeChart.get("Flying").get("Resistance").add("Grass");
    typeChart.get("Flying").get("Immunity").add("Ground");
    typeChart.get("Ghost").get("Weakness").add("Ghost");
    typeChart.get("Ghost").get("Resistance").add("Poison");
    typeChart.get("Ghost").get("Resistance").add("Bug");
    typeChart.get("Ghost").get("Immunity").add("Normal");
    typeChart.get("Ghost").get("Immunity").add("Fighting");
    typeChart.get("Grass").get("Weakness").add("Fire");
    typeChart.get("Grass").get("Weakness").add("Ice");
    typeChart.get("Grass").get("Weakness").add("Poison");
    typeChart.get("Grass").get("Weakness").add("Flying");
    typeChart.get("Grass").get("Weakness").add("Bug");
    typeChart.get("Grass").get("Resistance").add("Water");
    typeChart.get("Grass").get("Resistance").add("Electric");
    typeChart.get("Grass").get("Resistance").add("Grass");
    typeChart.get("Grass").get("Resistance").add("Ground");
    typeChart.get("Ground").get("Weakness").add("Water");
    typeChart.get("Ground").get("Weakness").add("Grass");
    typeChart.get("Ground").get("Weakness").add("Ice");
    typeChart.get("Ground").get("Resistance").add("Poison");
    typeChart.get("Ground").get("Resistance").add("Rock");
    typeChart.get("Ground").get("Immunity").add("Electric");
    typeChart.get("Ice").get("Weakness").add("Fire");
    typeChart.get("Ice").get("Weakness").add("Fighting");
    typeChart.get("Ice").get("Weakness").add("Rock");
    typeChart.get("Ice").get("Resistance").add("Ice");
    typeChart.get("Normal").get("Weakness").add("Fighting");
    typeChart.get("Normal").get("Immunity").add("Ghost");
    typeChart.get("Poison").get("Weakness").add("Ground");
    typeChart.get("Poison").get("Weakness").add("Psychic");
    typeChart.get("Poison").get("Weakness").add("Bug");
    typeChart.get("Poison").get("Resistance").add("Grass");
    typeChart.get("Poison").get("Resistance").add("Fighting");
    typeChart.get("Poison").get("Resistance").add("Poison");
    typeChart.get("Psychic").get("Weakness").add("Bug");
    typeChart.get("Psychic").get("Resistance").add("Psychic");
    typeChart.get("Psychic").get("Resistance").add("Fighting");
    typeChart.get("Psychic").get("Immunity").add("Ghost");    
    typeChart.get("Rock").get("Weakness").add("Fighting");
    typeChart.get("Rock").get("Weakness").add("Ground");
    typeChart.get("Rock").get("Weakness").add("Grass");
    typeChart.get("Rock").get("Weakness").add("Water");
    typeChart.get("Rock").get("Resistance").add("Normal");
    typeChart.get("Rock").get("Resistance").add("Poison");
    typeChart.get("Rock").get("Resistance").add("Flying");
    typeChart.get("Rock").get("Resistance").add("Fire");
    typeChart.get("Water").get("Weakness").add("Electric");
    typeChart.get("Water").get("Weakness").add("Grass");
    typeChart.get("Water").get("Resistance").add("Water");
    typeChart.get("Water").get("Resistance").add("Fire");
    typeChart.get("Water").get("Resistance").add("Ice");
  }

  // Getters and setters
  public String getType(int n){
    return this.type.get(n);
  }
  
  public Move getMove(int n){
    return this.moves.get(n);
  }
  
  public Move getValidMove(int n) {
    return this.validMoves.get(n);
  }
  
  public int getBaseStat(String stat){
    return this.baseStats.get(stat);
  }
  
  public int getStat(String stat){
    return this.stats.get(stat);
  }

  public boolean getStatusEffect(String s){
    return this.statusEffect.get(s);
  }

  public void setStatus(String s, boolean b){
    this.statusEffect.put(s,b);
  }

  public int getBoost(String s){
    return boosts.get(s);
  }

  public void setBoosts(String s, int b){
    this.boosts.put(s,b);
  }

  public String getName(){
    return this.name;
  }

  public boolean getAlly() {
    return this.ally;
  }
  
  public int getCurrentHp() {
    return this.currentHp;
  }

  public int getSleepCounter() {
    return this.sleepCounter;
  }

  public void setSleepCounter(int sleepCounter) {
    this.sleepCounter = sleepCounter;
  }

  public int getToxicCounter() {
    return this.toxicCounter;
  }

  public void setToxicCounter(int toxicCounter) {
    this.toxicCounter = toxicCounter;
  }
  
  public int getWrapCounter() {
    return this.wrapCounter;
  }
  
  public void setWrapCounter(int wrapCounter) {
    this.wrapCounter = wrapCounter;
  }

  public int getConfusionCounter() {
    return this.confusionCounter;
  }

  public void setConfusionCounter(int confusionCounter) {
    this.confusionCounter = confusionCounter;
  }
  
  public void setCurrentHp(int currentHp){
    this.currentHp = currentHp;
  }
  
  public int getCurrentSpeed(){
    return this.currentSpeed;
  }
  
  public void setCurrentSpeed(int currentSpeed){
    this.currentSpeed = currentSpeed;
  }

  public int getCurrentAttack() {
    return this.currentAttack;
  }
  
  public void setCurrentAttack(int currentAttack) {
    this.currentAttack = currentAttack;
  }

  public Move getStruggle() {
    return this.struggle;
  }

  public Move getRecharge() {
    return this.recharge;
  }

  public Move getWrap() {
    return this.continueWrap;
  }

  public Move getClamp() {
    return this.continueClamp;
  }

  public ArrayList<Move> getValidMoves() {
    return this.validMoves;
  }

  public HashMap<String, Boolean> getStatusEffects() {
    return this.statusEffect;
  }

  public void setWrapDamage(int wrapDamage) {
    this.wrapDamage = wrapDamage;
  }

  public int getWrapDamage() {
    return this.wrapDamage;
  }

  // Attacks an opponent with a given move
  public void attack(Pokemon enemy, Move move){
    int critical;
    double highCritRatio;
    double stab;
    double explosion;
    int effectiveAtk;
    int effectiveDef;
    int damage;
    int healing;
    int reflect;
    double effectiveness1;
    double effectiveness2;

    // Checks for status conditions before using the move
    if (this.getConfusionCounter() != 0) {
      this.setConfusionCounter(this.getConfusionCounter() - 1);
    }
    if (this.getWrapCounter() != 0) {
      this.setWrapCounter(this.getWrapCounter() - 1);
    }
    if (this.getStatusEffect("Recharging") == true) {
      this.validMoves.removeAll(validMoves);
      this.validMoves.add(this.getMove(0));
      this.validMoves.add(this.getMove(1));
      this.validMoves.add(this.getMove(2));
      this.validMoves.add(this.getMove(3));
      this.setStatus("Recharging", false);
    }
    if (this.getStatusEffect("Fainted") == true || enemy.getStatusEffect("Fainted") == true) {}
    else if (this.getStatusEffect("Frozen") == true) {
      System.out.println(this.getName() + " is frozen solid!"); 
      delay();
      if (move.getName().equals("Continue Wrap") || move.getName().equals("Continue Clamp")) {
        this.setWrapCounter(0);
      }
    }
    else if (this.getStatusEffect("Asleep") == true && this.getSleepCounter() != 0) {
      System.out.println(this.getName() + " is fast asleep!");
      delay();
      this.setSleepCounter(this.getSleepCounter() - 1);
      if (move.getName().equals("Continue Wrap") || move.getName().equals("Continue Clamp")) {
        this.setWrapCounter(0);
      }
    }
    else if (move.getName().equals("Recharge")) {
      System.out.println(this.getName() + " must recharge!");
      delay();
    }
    else if (this.getStatusEffect("Asleep") == true && this.getSleepCounter() == 0) {
      System.out.println(this.getName() + " woke up!");
      delay();
      this.setStatus("Asleep", false);
    }
    else if (this.getStatusEffect("Wrapped") == true || this.getStatusEffect("Clamped") == true) {
      System.out.println(this.getName() + " can\'t move!");
      delay();
      if (move.getName().equals("Continue Wrap") || move.getName().equals("Continue Clamp")) {
        this.setWrapCounter(0);
      }
    }
    else if (this.getStatusEffect("Flinched") == true) {
      System.out.println(this.getName() + " flinched!");
      delay();
      if (move.getName().equals("Continue Wrap") || move.getName().equals("Continue Clamp")) {
        this.setWrapCounter(0);
      }
    }
    else if (this.getStatusEffect("Paralyzed") == true && Math.random() < 0.25) {
      System.out.println(this.getName() + " is paralyzed! It can\'t move!");
      delay();
      if (move.getName().equals("Continue Wrap") || move.getName().equals("Continue Clamp")) {
        this.setWrapCounter(0);
      }
    }    
    else if (this.getStatusEffect("Confused") == true && this.getConfusionCounter() != 0 && Math.random() < 0.5){
      if (move.getName().equals("Continue Wrap") || move.getName().equals("Continue Clamp")) {
        this.setWrapCounter(0);
      }
      System.out.println(this.getName() + " is confused!");
      delay();
      System.out.println(this.getName() + " hurt itself in confusion!");
      delay();
      if (enemy.getStatusEffect("Reflect") == true) {
        reflect = 2;
      }
      else {
        reflect = 1;
      }
      effectiveAtk = this.getCurrentAttack();
      effectiveDef = this.getStat("DEF") * reflect;
      damage = Math.min(Math.min((int)((int)(1680 * effectiveAtk / effectiveDef) / 50), 997) + 2, this.getCurrentHp());
      System.out.println(this.getName() + " lost " + damage + " HP!");
      delay();
      this.setCurrentHp(this.getCurrentHp() - damage);
      if (this.getCurrentHp() == 0) {
        for (String effect : statusEffect.keySet()) {
            this.setStatus(effect, false);
        }
        this.setStatus("Fainted", true);
        System.out.println(this.getName() + " fainted!");
        delay();
      }
    }
    else {  
      if (this.getStatusEffect("Confused") == true && this.getConfusionCounter() == 0) {
        System.out.println(this.getName() + " snapped out of its confusion!");
        delay();
        this.setStatus("Confused", false);
      }
      if (this.getStatusEffect("Confused") == true && this.getConfusionCounter() != 0){
        System.out.println(this.getName() + " is confused!");
        delay();
      }
      if (move.getName().equals("Continue Clamp") || move.getName().equals("Continue Wrap")) {
        System.out.println(this.getName() + " continues its attack!");
        delay();
      }
      else {
        System.out.println(this.getName() + " used " + move.getName() + "!");
        delay();
      }
      // Checks if move is a damaging move
      if (move.getCategory() != "Status"){
        // Calculates and applies damage
        if (move.getName().equals("Continue Clamp") || move.getName().equals("Continue Wrap")) {
          damage = Math.min(this.getWrapDamage(), enemy.getCurrentHp());
          if (typeChart.get(enemy.getType(0)).get("Immunity").contains(move.getType()) || typeChart.get(enemy.getType(0)).get("Immunity").contains(move.getType())){
            effectiveness1 = 0;
          }
          else {
            effectiveness1 = 1;
          }
          if (typeChart.get(enemy.getType(1)).get("Immunity").contains(move.getType())){
            effectiveness2 = 0;
          }
          else {
            effectiveness2 = 1;
          }
          damage *= effectiveness1;
          damage *= effectiveness2;
          critical = 1;
        }
        else if (move.getFlatDamage() > 0) {
          // Flat damage calculation
          damage = Math.min(move.getFlatDamage(), enemy.getCurrentHp());
          if (typeChart.get(enemy.getType(0)).get("Immunity").contains(move.getType())){
            effectiveness1 = 0;
          }
          else {
            effectiveness1 = 1;
          }
          if (typeChart.get(enemy.getType(1)).get("Immunity").contains(move.getType())){
            effectiveness2 = 0;
          }
          else {
            effectiveness2 = 1;
          }
          damage *= effectiveness1;
          damage *= effectiveness2;
          critical = 1;
        }
        else {           
          // Normal damage calculation
          if (move.getName().equals("Crabhammer") || move.getName().equals("Slash") || move.getName().equals("Razor Leaf") || move.getName().equals("Karate Chop")){
            highCritRatio = 4;
          }
          else {
            highCritRatio = 0.5;
          }
          
          if ((int)(Math.random() * 256) < Math.min((int)(this.getBaseStat("SPE") * highCritRatio), 255)){
            critical = 2;
          }
          else {
            critical = 1;
          }
    
          if (this.getType(0).equals(move.getType()) || this.getType(1).equals(move.getType())){
            stab = 1.5;
          }
          else {
            stab = 1;
          }

          if (enemy.getStatusEffect("Reflect") == true) {
            reflect = 2;
          }
          else {
            reflect = 1;
          } 
          
          if (move.getName().equals("Self-Destruct") || move.getName().equals("Explosion")) {
            explosion = 0.5;
          }
          else {
            explosion = 1;
          }

          // Uses physical stats if move is physical
          if (move.getCategory().equals("Physical")) {
            if (critical == 1){
              effectiveAtk = (int)(this.getCurrentAttack() * boostMultipliers.get(this.boosts.get("ATK")));
              effectiveDef = (int) ((int) (enemy.getStat("DEF") * boostMultipliers.get(enemy.boosts.get("DEF"))) * reflect * explosion);
            }
            else {
              if (this.getStatusEffect("Burned") == true) {
                effectiveAtk = this.getCurrentAttack() * 2;
              }
              else {
                effectiveAtk = this.getCurrentAttack();
              }
              effectiveDef = enemy.getStat("DEF");
            }
          } 
          // Uses special stats if move is special
          else {
            if (critical == 1){
              effectiveAtk = (int) (this.getStat("SPC") * boostMultipliers.get(this.getBoost("SPC")));
              effectiveDef = (int) (enemy.getStat("SPC") * boostMultipliers.get(enemy.getBoost("SPC")));
            }
            else {
              effectiveAtk = this.getStat("SPC");
              effectiveDef = enemy.getStat("SPC");
            }
          }

          // Prints effectiveness of move
          if (typeChart.get(enemy.getType(0)).get("Weakness").contains(move.getType())){
            effectiveness1 = 2;
          }
          else if (typeChart.get(enemy.getType(0)).get("Resistance").contains(move.getType())){
            effectiveness1 = 0.5;
          }
          else if (typeChart.get(enemy.getType(0)).get("Immunity").contains(move.getType())){
            effectiveness1 = 0;
          }
          else {
            effectiveness1 = 1;
          }
          
          if (typeChart.get(enemy.getType(1)).get("Weakness").contains(move.getType())){
            effectiveness2 = 2;
          }
          else if (typeChart.get(enemy.getType(1)).get("Resistance").contains(move.getType())){
            effectiveness2 = 0.5;
          }
          else if (typeChart.get(enemy.getType(1)).get("Immunity").contains(move.getType())){
            effectiveness2 = 0;
          }
          else {
            effectiveness2 = 1;
          }

          // Damage formula
          damage = (int)((int)((int)((Math.min((int)((int)((40 * critical + 2) * move.getBasePower() * effectiveAtk / effectiveDef) / 50), 997) + 2) * stab) * effectiveness1) * effectiveness2);
          if (damage != 1) { 
            damage = Math.min((int)(damage * (217 + (int)(Math.random() * 39)) / 255), enemy.getCurrentHp());
          }
        }
        // Fails OHKO moves if slower
        if (move.getName().equals("Horn Drill") && (int)(this.getCurrentSpeed()*boostMultipliers.get(this.getBoost("SPE"))) < (int)(enemy.getCurrentSpeed()*boostMultipliers.get(enemy.getBoost("SPE")))){
          System.out.println("But it failed!");
          delay();
        }
        // Damage immunity text
        else if (damage == 0) {
          if (enemy.getAlly() == true) {
            System.out.println("It doesn't affect " + enemy.getName() + "!");
            delay();
          }
          else {
            System.out.println("It doesn't affect " + enemy.getName().substring(0, 1).toLowerCase() + enemy.getName().substring(1) + "!");
            delay();
          }
          if (move.getName().equals("Continue Wrap") || move.getName().equals("Continue Clamp")) {
            this.setWrapCounter(0);
          }
        }
        // Checks if move misses
        else if (Math.random() > move.getAccuracy() * boostMultipliers.get(this.getBoost("ACC")) * boostMultipliers.get(-enemy.getBoost("EVA"))){
          System.out.println(this.getName() + "\'s attack missed!");
          delay();
          if (move.getName().equals("Continue Wrap") || move.getName().equals("Continue Clamp")) {
            this.setWrapCounter(0);
          }
        }
        else {
          // Applies the calculated damage
          enemy.setCurrentHp(enemy.getCurrentHp() - damage);
          if (critical == 2) {
            System.out.println("A critical hit!");
            delay();
          }
          if (effectiveness1 * effectiveness2 < 1) {
            System.out.println("It\'s not very effective...");
            delay();
          }
          if (effectiveness1 * effectiveness2 > 1) {
            System.out.println("It\'s super effective!");
            delay();
          }

          if (move.getName().equals("Double Kick")) {
            System.out.println("It hit two times!");
            delay();
          }
          
          System.out.println(enemy.getName() + " lost " + damage + " HP!");
          delay();
          
          if (enemy.getCurrentHp() > 0){
            // Checks for debuffs and applies them
            if (Math.random() < move.getDebuffChance()) {
              int changedBoost = Math.max(enemy.getBoost(move.getDebuffStat()) - move.getDebuffAmount(), -6);
              int change = enemy.getBoost(move.getDebuffStat()) - changedBoost;
              if (change == 1) {
                System.out.println(enemy.getName() + "\'s " + move.getDebuffStat() + " fell!");
                delay();
              }
              if (change == 2) {
                System.out.println(enemy.getName() + "\'s " + move.getDebuffStat() + " greatly fell!");
                delay();
              }
              enemy.setBoosts(move.getDebuffStat(), changedBoost);
            }
            if (move.getEffect().equals("Burned") && enemy.getStatusEffect("Frozen") == true) {
              enemy.setStatus("Frozen", false);
              System.out.println(enemy.getName() + " thawed out!");
              delay();
            }
            // Checks for status effects and applies them
            if (Math.random() < move.getEffectChance()) {
              ArrayList<String> nonVolatile = new ArrayList<>();
              nonVolatile.add("Burned");
              nonVolatile.add("Frozen");
              nonVolatile.add("Paralyzed");
              nonVolatile.add("Asleep");
              nonVolatile.add("Badly Poisoned");
              nonVolatile.add("Poisoned");
              if (nonVolatile.contains(move.getEffect())) {
                boolean hasNonVolatile = false;
                for (String effect: nonVolatile) { 
                  if (enemy.getStatusEffect(effect) == true){
                    hasNonVolatile = true;
                  }
                }
                if (hasNonVolatile == false && !(move.getEffect().equals("Paralyzed") && (move.getType().equals(enemy.getType(0)) || move.getType().equals(enemy.getType(1)))) && !(move.getEffect().equals("Burned") && (enemy.getType(0).equals("Fire") || enemy.getType(1).equals("Fire"))) && !(move.getEffect().equals("Poisoned") && (enemy.getType(0).equals("Poison") || enemy.getType(1).equals("Poison"))) && !(move.getEffect().equals("Badly Poisoned") && (enemy.getType(0).equals("Poison") || enemy.getType(1).equals("Poison"))) && !(move.getEffect().equals("Frozen") && (enemy.getType(0).equals("Ice") || enemy.getType(1).equals("Ice")))){
                  enemy.setStatus(move.getEffect(), true);
                  if (move.getEffect().equals("Asleep")) {
                    System.out.println(enemy.getName() + " fell asleep!");
                    delay();
                    enemy.setSleepCounter((int)(Math.random() * 6) + 1);
                  }
                  if (move.getEffect().equals("Paralyzed")) {
                    System.out.println(enemy.getName() + " was paralyzed!");
                    delay();
                    enemy.setCurrentSpeed((int)(enemy.getCurrentSpeed() * 0.25));
                  }
                  if (move.getEffect().equals("Frozen")) {
                    System.out.println(enemy.getName() + " was frozen!");
                    delay();
                  }
                  if (move.getEffect().equals("Burned")) {
                    System.out.println(enemy.getName() + " was burned!");
                    delay();
                    enemy.setCurrentAttack((int)(enemy.getCurrentAttack() / 2));
                  }
                  if (move.getEffect().equals("Poisoned")) {
                    System.out.println(enemy.getName() + " was poisoned!");
                    delay();
                  }
                  if (move.getEffect().equals("Badly Poisoned")) {
                    System.out.println(enemy.getName() + " was badly poisoned!");
                    delay();
                    enemy.setToxicCounter(1);
                  }
                }
              }
              else {
                if (move.getEffect().equals("Confused")) {
                  enemy.setStatus(move.getEffect(), true);
                  System.out.println(enemy.getName() + " was confused!");
                  delay();
                  enemy.setConfusionCounter((int)(Math.random() * 3) + 2);
                }
                if (move.getEffect().equals("Flinched") && (int)(this.getCurrentSpeed()*boostMultipliers.get(this.getBoost("SPE"))) > (int)(enemy.getCurrentSpeed()*boostMultipliers.get(enemy.getBoost("SPE")))) {
                  enemy.setStatus("Flinched", true);
                }
                if (move.getEffect().equals("Wrapped")) {
                  enemy.setStatus("Wrapped", true);
                }
                if (move.getEffect().equals("Clamped")) {
                  enemy.setStatus("Clamped", true);
                }
              }
            }
          }
          else {
            // Faints enemy if their HP is 0
            for (String effect : statusEffect.keySet()) {
              enemy.setStatus(effect, false);
            }
            enemy.setStatus("Fainted", true);
            System.out.println(enemy.getName() + " fainted!");
            delay();
          }

          // Checks and applies effects or damage to self
          if (move.getName().equals("Struggle") || move.getName().equals("Double-Edge")){
            System.out.println(this.getName() + " was damaged by the recoil!");
            delay();
            double multiplier; 
            if (move.getName().equals("Struggle")) {
              multiplier = 0.5;
            }
            else {
              multiplier = 0.25;
            }
            damage = Math.min((int)(damage * multiplier), this.getCurrentHp());
            System.out.println(this.getName() + " lost " + damage + " HP!");
            delay();
            this.setCurrentHp(this.getCurrentHp() - damage);
            if (this.getCurrentHp() == 0) {
              for (String effect : this.statusEffect.keySet()) {
                  this.setStatus(effect, false);
              }
              this.setStatus("Fainted", true);
              System.out.println(this.getName() + " fainted!");
              delay();
            }
          }
          if (move.getName().equals("Hyper Beam") && enemy.getStatusEffect("Fainted") == false) {
            this.setStatus("Recharging", true);
          }
          if (move.getName().equals("Wrap") && this.getStatusEffect("Wrapping") == false) {
            this.setStatus("Wrapping", true);
            this.setWrapDamage(damage);
            if (Math.random() < 0.375) {
              this.setWrapCounter(1);
            }
            else if (Math.random() < 0.75) {
              this.setWrapCounter(2);
            }
            else if (Math.random() < 0.875) {
              this.setWrapCounter(3);
            }
            else {
              this.setWrapCounter(4);
            }
          }
          if (move.getName().equals("Clamp") && this.getStatusEffect("Clamping") == false) {
            this.setStatus("Clamping", true);
            this.setWrapDamage(damage);
            if (Math.random() < 0.375) {
              this.setWrapCounter(1);
            }
            else if (Math.random() < 0.75) {
              this.setWrapCounter(2);
            }
            else if (Math.random() < 0.875) {
              this.setWrapCounter(3);
            }
            else {
              this.setWrapCounter(4);
            }
          }
        }
        
        if (move.getName().equals("Explosion") || move.getName().equals("Self-Destruct")) {
          System.out.println(this.getName() + " lost " + this.getCurrentHp() + " HP!");
          delay();
          this.setCurrentHp(0);
          for (String effect : this.statusEffect.keySet()) {
            this.setStatus(effect, false);
          }
          this.setStatus("Fainted", true);
          System.out.println(this.getName() + " fainted!"); 
          delay();
        }
      }
      // Checks if move is a status move
      if (move.getCategory().equals("Status")) {
        if (Math.random() < move.getAccuracy() * boostMultipliers.get(this.getBoost("ACC")) * boostMultipliers.get(-enemy.getBoost("EVA"))) {

          // Applies debuff from move to enemy if it has one
          if (Math.random() < move.getDebuffChance() && move.getDebuffChance() > 0) {
            int changedBoost = Math.max(enemy.getBoost(move.getDebuffStat()) - move.getDebuffAmount(), -6);
            int change = enemy.getBoost(move.getDebuffStat()) - changedBoost;
            if (change == 1) {
              System.out.println(enemy.getName() + "\'s " + move.getDebuffStat() + " fell!");
              delay();
            }
            if (change == 2) {
              System.out.println(enemy.getName() + "\'s " + move.getDebuffStat() + " greatly fell!");
              delay();
            }
            if (change == 0) {
              System.out.println("Nothing happened!");
              delay();
            }
            enemy.setBoosts(move.getDebuffStat(), changedBoost);
          }

          // Applies buff from move to self if it has one
          if (Math.random() < move.getBuffChance()) {
            int changedBoost = Math.min(this.getBoost(move.getBuffStat()) + move.getBuffAmount(), 6);
            int change = changedBoost - this.getBoost(move.getBuffStat());
            if (change == 1) {
              System.out.println(this.getName() + "\'s " + move.getBuffStat() + " rose!");
              delay();
            }
            if (change == 2) {
              System.out.println(this.getName() + "\'s " + move.getBuffStat() + " greatly rose!");
              delay();
            }
            if (change == 0) {
              System.out.println("Nothing happened!");
              delay();
            }
            this.setBoosts(move.getBuffStat(), changedBoost);
          }

          // Applies status from move to enemy if it has one
          if (Math.random() < move.getEffectChance() && move.getEffectChance() > 0) {
            ArrayList<String> nonVolatile = new ArrayList<>();
            nonVolatile.add("Burned");
            nonVolatile.add("Frozen");
            nonVolatile.add("Paralyzed");
            nonVolatile.add("Asleep");
            nonVolatile.add("Badly Poisoned");
            nonVolatile.add("Poisoned");
            if (nonVolatile.contains(move.getEffect())) {
              boolean hasNonVolatile = false;
              for (String effect: nonVolatile) { 
                if (enemy.getStatusEffect(effect) == true){
                  hasNonVolatile = true;
                }
              }
              if (hasNonVolatile == false && !(enemy.getType(0).equals("Ground") && move.getName().equals("Thunder Wave") || (enemy.getType(1).equals("Ground") && move.getName().equals("Thunder Wave"))) && !(move.getEffect().equals("Burned") && (enemy.getType(0).equals("Fire") || enemy.getType(1).equals("Fire"))) && !(move.getEffect().equals("Poisoned") && (enemy.getType(0).equals("Poison") || enemy.getType(1).equals("Poison"))) && !(move.getEffect().equals("Badly Poisoned") && (enemy.getType(0).equals("Poison") || enemy.getType(1).equals("Poison"))) && !(move.getEffect().equals("Frozen") && (enemy.getType(0).equals("Ice") || enemy.getType(1).equals("Ice")))){
                enemy.setStatus(move.getEffect(), true);
                if (move.getEffect().equals("Asleep")) {
                  System.out.println(enemy.getName() + " fell asleep!");
                  delay();
                  enemy.setSleepCounter((int)(Math.random() * 6) + 1);
                }
                if (move.getEffect().equals("Paralyzed")) {
                  System.out.println(enemy.getName() + " was paralyzed!");
                  delay();
                  enemy.setCurrentSpeed((int)(enemy.getCurrentSpeed() * 0.25));
                }
                if (move.getEffect().equals("Frozen")) {
                  System.out.println(enemy.getName() + " was frozen!");
                  delay();
                }
                if (move.getEffect().equals("Burned")) {
                  System.out.println(enemy.getName() + " was burned!");
                  delay();
                  enemy.setCurrentAttack((int)(enemy.getCurrentAttack() / 2));
                }
                if (move.getEffect().equals("Poisoned")) {
                  System.out.println(enemy.getName() + " was poisoned!");
                  delay();
                }
                if (move.getEffect().equals("Badly Poisoned")) {
                  System.out.println(enemy.getName() + " was badly poisoned!");
                  delay();
                  enemy.setToxicCounter(1);
                }
              }
              else if (hasNonVolatile == false) {
                if (enemy.getAlly() == true) {
                  System.out.println("It doesn't affect " + enemy.getName() + "!");
                  delay();
                }
                else {
                  System.out.println("It doesn't affect " + enemy.getName().substring(0, 1).toLowerCase() + enemy.getName().substring(1) + "!");
                  delay();
                }
              }
              else {
                if (enemy.getStatusEffect(move.getEffect()) == true) {
                  System.out.println(enemy.getName() + " is already " + move.getEffect().toLowerCase() + "!");
                  delay();
                }
                else {
                  System.out.println("But it failed!");
                  delay();
                }
              }
            }
            else {
              if (move.getEffect().equals("Confused") && enemy.getStatusEffect("Confused") == false) {
                enemy.setStatus(move.getEffect(), true);
                System.out.println(enemy.getName() + " was confused!");
                delay();
                enemy.setConfusionCounter((int)(Math.random() * 3) + 2);
              }
              else if (move.getEffect().equals("Confused") && enemy.getStatusEffect("Confused") == true) {
                System.out.println("But it failed!");
                delay();
              }
            }
          }
        }
        else {
          System.out.println(this.getName() + "\'s attack missed!");
          delay();
        }

        // Checks for moves that have special effects
        if (move.getName().equals("Recover") || move.getName().equals("Soft-Boiled")) {
          if (this.getCurrentHp() == this.getStat("HP")) {
            System.out.println("But it failed!");
            delay();
          }
          else {
            healing = Math.min((int)(this.getStat("HP") / 2), this.getStat("HP") - this.getCurrentHp());
            System.out.println(this.getName() + " had its HP restored!");
            delay();
            System.out.println(this.getName() + " gained " + healing + " HP!");
            delay();
            this.setCurrentHp(this.getCurrentHp() + healing);
          }
        }
        if (move.getName().equals("Rest")) {
          if (this.getCurrentHp() == this.getStat("HP")) {
            System.out.println("But it failed!");
            delay();
          }
          else {
            healing = this.getStat("HP") - this.getCurrentHp();
            System.out.println(this.getName() + " slept and became healthy!");
            delay();
            System.out.println(this.getName() + " gained " + healing + " HP!");
            delay();
            this.setCurrentHp(this.getCurrentHp() + healing);
            this.setStatus("Burned", false);
            this.setStatus("Frozen", false);
            this.setStatus("Paralyzed", false);
            this.setStatus("Badly Poisoned", false);
            this.setStatus("Poisoned", false);
            this.setStatus("Asleep", true);
          }
        }
        if (move.getName().equals("Reflect")) {
          if (this.getStatusEffect("Reflect") == true) {
            System.out.println("But it failed!");
            delay();
          }
          else {
            System.out.println(this.getName() + " gained armor!");
            delay();
            this.setStatus("Reflect", true);
          }
        }
      }
      if (!(move.getName().equals("Struggle") || move.getName().equals("Continue Clamp") || move.getName().equals("Continue Wrap"))) {
        move.setCurrentPp(move.getCurrentPp() - 1);
      }
    } 
  }

  // Updates valid moves and damage due to status effects
  public void update() {
    int damage;
    
    this.validMoves.removeIf(move -> move.getCurrentPp() == 0);
    
    if (validMoves.size() == 0) {
      this.validMoves.add(this.getStruggle());
    }
    if (this.getStatusEffect("Recharging") == true) {
      this.validMoves.removeAll(validMoves);
      this.validMoves.add(this.getRecharge());
    }
    if (this.getStatusEffect("Clamping") == true) {
      this.validMoves.removeAll(validMoves);
      this.validMoves.add(this.getClamp());
    }
    if (this.getStatusEffect("Wrapping") == true) {
      this.validMoves.removeAll(validMoves);
      this.validMoves.add(this.getWrap());
    }
    if (this.getStatusEffect("Wrapping") == true && this.getWrapCounter() == 0) {
      this.validMoves.removeAll(validMoves);
      this.validMoves.add(this.getMove(0));
      this.validMoves.add(this.getMove(1));
      this.validMoves.add(this.getMove(2));
      this.validMoves.add(this.getMove(3));
      this.setStatus("Wrapping", false);
    }
    if (this.getStatusEffect("Clamping") == true && this.getWrapCounter() == 0) {
      this.validMoves.removeAll(validMoves);
      this.validMoves.add(this.getMove(0));
      this.validMoves.add(this.getMove(1));
      this.validMoves.add(this.getMove(2));
      this.validMoves.add(this.getMove(3));
      this.setStatus("Clamping", false);
    }
    
    if (this.getStatusEffect("Burned") == true) {
      System.out.println(this.getName() + " is hurt by its burn!");
      delay();
      damage = Math.min((int)(this.getStat("HP") / 16), this.getCurrentHp());
      System.out.println(this.getName() + " lost " + damage + " HP!");
      delay();
      this.setCurrentHp(this.getCurrentHp() - damage);
      if (this.getCurrentHp() == 0) {
        for (String effect : this.statusEffect.keySet()) {
            this.setStatus(effect, false);
        }
        this.setStatus("Fainted", true);
        System.out.println(this.getName() + " fainted!");
        delay();
      }
    }
  
    if (this.getStatusEffect("Poisoned") == true) {
      System.out.println(this.getName() + " is hurt by poison!");
      delay();
      damage = Math.min((int)(this.getStat("HP") / 16), this.getCurrentHp());
      System.out.println(this.getName() + " lost " + damage + " HP!");
      delay();
      this.setCurrentHp(this.getCurrentHp() - damage);
      if (this.getCurrentHp() == 0) {
        for (String effect : this.statusEffect.keySet()) {
            this.setStatus(effect, false);
        }
        this.setStatus("Fainted", true);
        System.out.println(this.getName() + " fainted!");
        delay();
      }
    }
  
    if (this.getStatusEffect("Badly Poisoned") == true) {
      System.out.println(this.getName() + " is hurt by poison!");
      delay();
      damage = Math.min((int)(this.getStat("HP") / 16) * this.getToxicCounter(), this.getCurrentHp());
      System.out.println(this.getName() + " lost " + damage + " HP!");
      delay();
      this.setCurrentHp(this.getCurrentHp() - damage);
      this.setToxicCounter(this.getToxicCounter() + 1);
      if (this.getCurrentHp() == 0) {
        for (String effect : this.statusEffect.keySet()) {
            this.setStatus(effect, false);
        }
        this.setStatus("Fainted", true);
        System.out.println(this.getName() + " fainted!");
        delay();
      }
    }
    
    if (this.getStatusEffect("Flinched") == true) {
      this.setStatus("Flinched", false);
    }
    if (this.getStatusEffect("Wrapped") == true) {
      this.setStatus("Wrapped", false);
    }
    if (this.getStatusEffect("Clamped") == true) {
      this.setStatus("Clamped", false);
    }
  }

  // Resets volatile status effects when switching
  public void switchReset() {
    this.setToxicCounter(0);
    this.setWrapCounter(0);
    this.setConfusionCounter(0);
    this.setStatus("Confused", false);
    this.setStatus("Flinched", false);
    this.setStatus("Recharging", false);
    this.setStatus("Reflect", false);
    this.setStatus("Wrapping", false);
    this.setStatus("Wrapped", false);
    this.setStatus("Clamping", false);
    this.setStatus("Clamped", false); 
    if (this.getStatusEffect("Badly Poisoned") == true) {
      this.setStatus("Badly Poisoned", false);
      this.setStatus("Poisoned", true);
    }
  }

  // Heals HP and PP of pokémon and resets all status conditions
  public void reset() {
    this.setCurrentHp(this.getStat("HP"));
    this.setCurrentSpeed(this.getStat("SPE"));
    this.setSleepCounter(0);
    this.setToxicCounter(0);
    this.setWrapCounter(0);
    this.setConfusionCounter(0);
    this.validMoves.removeAll(validMoves);
    this.validMoves.add(this.getMove(0));
    this.validMoves.add(this.getMove(1));
    this.validMoves.add(this.getMove(2));
    this.validMoves.add(this.getMove(3));
    for (String effect : this.statusEffect.keySet()) {
      this.setStatus(effect, false);
    }
    for (String boost : this.boosts.keySet()) {
      this.setBoosts(boost, 0);
    }
    for (Move move: this.moves) {
      move.setCurrentPp(move.getPp());
    }
  }
} 