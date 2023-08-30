public class Move {
  
  //Creates the variables
  private final String name;
  private final String type;
  private final String category;
  private final int pp;
  private final int basePower;
  private final int flatDamage;
  private final double accuracy;
  private final String buffStat;
  private final double buffChance;
  private final int buffAmount;
  private final String debuffStat;
  private final double debuffChance;
  private final int debuffAmount;
  private final String effect;
  private final double effectChance;
  private int currentPp;

  // Constructor
  public Move(String name, String type, String category, int pp, int basePower, int flatDamage, double accuracy, String buffStat, double buffChance, int buffAmount, String debuffStat, double debuffChance, int debuffAmount, String effect, double effectChance){
    this.name = name;
    this.type = type;
    this.category = category;
    this.pp = pp;
    this.basePower = basePower;
    this.flatDamage = flatDamage;
    this.accuracy = accuracy;
    this.buffStat = buffStat;
    this.buffChance = buffChance;
    this.buffAmount = buffAmount;
    this.debuffStat = debuffStat;
    this.debuffChance = debuffChance;
    this.debuffAmount = debuffAmount;
    this.effect = effect;
    this.effectChance = effectChance;
    this.currentPp = pp;
  }

  // Copy constructor
  public Move(Move move){
    this.name = move.name;
    this.type = move.type;
    this.category = move.category;
    this.pp = move.pp;
    this.basePower = move.basePower;
    this.flatDamage = move.flatDamage;
    this.accuracy = move.accuracy;
    this.buffStat = move.buffStat;
    this.buffChance = move.buffChance;
    this.buffAmount = move.buffAmount;
    this.debuffStat = move.debuffStat;
    this.debuffChance = move.debuffChance;
    this.debuffAmount = move.debuffAmount;
    this.effect = move.effect;
    this.effectChance = move.effectChance;
    this.currentPp = move.pp;
  }

  // getters and setters
  public String getName(){
    return this.name;
  }
  
  public String getType(){
    return this.type;
  }

  public String getCategory(){
    return this.category;
  }

  public int getPp(){
    return this.pp;
  }

  public int getBasePower(){
    return this.basePower;
  }

  public int getFlatDamage(){
    return this.flatDamage;
  }

  public double getAccuracy(){
    return this.accuracy;
  }

  public String getBuffStat(){
    return this.buffStat;
  }

  public double getBuffChance(){
    return this.buffChance;
  }

  public int getBuffAmount(){
    return this.buffAmount;
  }
  
  public String getDebuffStat(){
    return this.debuffStat;
  }

  public double getDebuffChance(){
    return this.debuffChance;
  }

  public int getDebuffAmount(){
    return this.debuffAmount;
  }
  
  public String getEffect(){
    return this.effect;
  }

  public double getEffectChance(){
    return this.effectChance;
  }

  public int getCurrentPp(){
    return this.currentPp;
  }

  public void setCurrentPp(int currentPp){
    this.currentPp = currentPp;
  }

}
