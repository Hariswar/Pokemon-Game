import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Trainer {
  // Delays time for 1 second before next message
  private static void delay() {
    try {
      TimeUnit.SECONDS.sleep(1);
    }
    catch (InterruptedException e) {
      System.out.println(e);
    }
  }
  
  private final String name;
  private final boolean ally;
  private ArrayList<Pokemon> team = new ArrayList<>();
  private ArrayList<Pokemon> validSwitches = new ArrayList<>();
  private Pokemon activePokemon;
  
  // Constructor
  public Trainer(String name, Pokemon pokemon1, Pokemon pokemon2, Pokemon pokemon3, Pokemon pokemon4, Pokemon pokemon5, Pokemon pokemon6, boolean ally) {
    this.name = name;
    this.ally = ally;
    
    this.team.add(new Pokemon(pokemon1, ally));
    this.team.add(new Pokemon(pokemon2, ally));
    this.team.add(new Pokemon(pokemon3, ally));
    this.team.add(new Pokemon(pokemon4, ally));
    this.team.add(new Pokemon(pokemon5, ally));
    this.team.add(new Pokemon(pokemon6, ally));
    this.validSwitches.add(this.team.get(1));
    this.validSwitches.add(this.team.get(2));
    this.validSwitches.add(this.team.get(3));
    this.validSwitches.add(this.team.get(4));
    this.validSwitches.add(this.team.get(5));
    this.activePokemon = this.team.get(0);
  }

  public String getName() {
    return this.name;
  }

  public ArrayList<Pokemon> getTeam() {
    return this.team;
  }

  public Pokemon getActivePokemon() {
    return this.activePokemon;
  }

  public void setActivePokemon(Pokemon pokemon) {
    this.activePokemon = pokemon;
  }

  public ArrayList<Pokemon> getValidSwitches() {
    return this.validSwitches;
  }

  public Pokemon getValidSwitch(int n) {
    return this.validSwitches.get(n);
  }

  public boolean getAlly() {
    return this.ally;
  }

  public boolean defeated() {
    for (Pokemon pokemon : this.team) {
      if (pokemon.getStatusEffect("Fainted") == false) {
        return false;
      }
    }
    return true;
  }

  // Updates valid switches of trainer
  public void update() {
    this.validSwitches.removeAll(validSwitches);
    if (this.getActivePokemon().getStatusEffect("Recharging") == false) {
      for (Pokemon pokemon : this.getTeam()) {
        if (pokemon.getStatusEffect("Fainted") == false) {
          this.getValidSwitches().add(pokemon);
        }
      }
      this.getValidSwitches().remove(this.getActivePokemon());
    }
  }

  // Resets all members of a trainer's team and the order of the pokémon
  public void reset() {
    this.validSwitches.removeAll(validSwitches);
    for (Pokemon pokemon : this.team) {
      pokemon.reset();
    }
    this.setActivePokemon(this.team.get(0));
    this.validSwitches.remove(this.getActivePokemon());
  }

  // Switches to a given pokémon
  public void switchPokemon(Pokemon pokemon) {
    // Different dialogue based on if pokémon is the player's or the enemy's
    if (this.getAlly() == true) {
      System.out.println("Come back, " + this.getActivePokemon().getName() + "!");
      delay();
      this.validSwitches.add(this.getActivePokemon());
      this.getActivePokemon().switchReset();
      this.setActivePokemon(pokemon);
      this.validSwitches.remove(pokemon);    
      System.out.println("Go! " + this.getActivePokemon().getName() + "!");
      delay();
    }
    else {
      System.out.println(this.getName() + " withdrew " + this.getActivePokemon().getName().substring(13) + "!");
      delay();
      this.validSwitches.add(this.getActivePokemon());
      this.getActivePokemon().switchReset();
      this.setActivePokemon(pokemon);
      this.validSwitches.remove(pokemon);    
      System.out.println(this.getName() + " sent out " + this.getActivePokemon().getName().substring(13) + "!");
      delay();
    }
  }

  // Prints info of a pokémon
  public void printInfo() {
    // Different info based on if pokémon is the player's or the enemy's
    if (this.getAlly() == false) {
      if (this.getActivePokemon().getType(1) == "Empty") {
        System.out.println(this.getActivePokemon().getName().substring(13) + " ("+ this.getActivePokemon().getType(0) + ")");
      } 
      else {
        System.out.println(this.getActivePokemon().getName().substring(13) + " ("+ this.getActivePokemon().getType(0) + ", " + this.getActivePokemon().getType(1) + ")");
      }
      System.out.println("HP: " + this.getActivePokemon().getCurrentHp() + "/" + this.getActivePokemon().getStat("HP"));
      String statusInfo = "Status: ";
      for (String status: this.getActivePokemon().getStatusEffects().keySet()) {
        if (this.getActivePokemon().getStatusEffect(status) == true) {
          statusInfo += status + ", ";
        }
      }
      if (statusInfo.length() == 8) {
        statusInfo += "None";
      }
      else {
        statusInfo = statusInfo.substring(0, statusInfo.length() - 2);
      }
      System.out.println(statusInfo);
    }
    else {
      if (this.getActivePokemon().getType(1) == "Empty") {
        System.out.println(this.getActivePokemon().getName() + " ("+ this.getActivePokemon().getType(0) + ")");
      } 
      else {
        System.out.println(this.getActivePokemon().getName() + " ("+ this.getActivePokemon().getType(0) + ", " + this.getActivePokemon().getType(1) + ")");
      }
      System.out.println("HP: " + this.getActivePokemon().getCurrentHp() + "/" + this.getActivePokemon().getStat("HP"));
      String statusInfo = "Status: ";
      for (String status: this.getActivePokemon().getStatusEffects().keySet()) {
        if (this.getActivePokemon().getStatusEffect(status) == true) {
          statusInfo += status + ", ";
        }
      }
      if (statusInfo.length() == 8) {
        statusInfo += "None";
      }
      else {
        statusInfo = statusInfo.substring(0, statusInfo.length() - 2);
      }
      System.out.println(statusInfo);
      System.out.println("Attack: ");
      int n = 0;
      for (Move move: this.getActivePokemon().getValidMoves()) {
        System.out.println("[" + (n+1) + "] " + move.getName() + " (" + move.getType() + ") (" + move.getCategory() + ") (PP: " + move.getCurrentPp() + "/" + move.getPp() + ")");
        n += 1;
      }
      n = this.getActivePokemon().getValidMoves().size();
      System.out.println("Switch: ");
      for (Pokemon pokemon: this.validSwitches) {
        String typeInfo = "";
        if (pokemon.getType(1) == "Empty") {
          typeInfo = (" ("+ pokemon.getType(0) + ")");
        } 
        else {
          typeInfo = (" ("+ pokemon.getType(0) + ", " + pokemon.getType(1) + ")");
        }
        statusInfo = "Status: ";
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
      if (this.validSwitches.size() == 0) {
        System.out.println("Can't switch!");
      }
    }
  }
}