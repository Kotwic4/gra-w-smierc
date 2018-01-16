package board;

import bot.Player;

import javax.sound.midi.Track;
import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

class TileImplementation extends Tile{
    private Organism inhabitant;
    private int cost;
    private Coordinates coords;
    private List<TileImplementation> neighbours;
    private boolean stronghold;
    private int maximumNeighbouringFriendsCount;
    private static int DEFAULT_COST = 1;
    protected static int DEFAULT_NEIGHBOURING_FRIENDS_COUNT = 4;

    public TileImplementation(Coordinates coords) {
      this.coords = coords;
        cost = DEFAULT_COST;
        maximumNeighbouringFriendsCount = DEFAULT_NEIGHBOURING_FRIENDS_COUNT;
      neighbours = new LinkedList<>();
    }

    public boolean isInhabited() {
        return this.inhabitant != null;
    }

    public void broadcastAppeal(int appeal){
        for(TileImplementation neighbour: neighbours){
            if (neighbour.isInhabited() && neighbour.getPlayer().get() == getPlayer().get()) {
                if (neighbour.getInhabitant().getAppeal() == appeal) continue;
                neighbour.getInhabitant().setAppeal(appeal);
                neighbour.broadcastAppeal(appeal);
          }
        }
    }

    public Organism getInhabitant() {
        return inhabitant;
    }

    @Override
    public void inhabit(Player player) {
        if (canInhabit(player)) {
//            this.inhabitant = new Organism(player);
            uncheckedSetIntabitant(new Organism(player));
        } else {
            throw new InvalidOrganismPositionException(player);
        }
    }

    @Override
    public boolean canInhabit(Player player) {
        int foundFriendlyNeighbours = 0;
        if (this.isInhabited()) return false;
        for (TileImplementation neighbour : neighbours) {
            if (neighbour.isInhabited() && neighbour.getInhabitant().getPlayer() == player) {
                foundFriendlyNeighbours++;
            }
        }
        return foundFriendlyNeighbours > 0 && foundFriendlyNeighbours < maximumNeighbouringFriendsCount;

    }

    public void uncheckedSetIntabitant(Organism inhabitant) throws TileAlreadyInhabitedException {
      // Force setting inhabitant without checking neighbours - required for stronghold's organism initialization
      if(this.inhabitant == null) {
          this.inhabitant = inhabitant;
          Player player = inhabitant.getPlayer();
          player.addOrganism();
          if(isStronghold()){
              player.addStronhold();
          }
          for (TileImplementation neighbour : neighbours) {
              if(neighbour.getPlayer().isPresent() && neighbour.getPlayer().get() != player){
                  neighbour.unHabit();
              }
          }
      } else {
          throw new TileAlreadyInhabitedException(this.coords);
      }
    }

    @Override
    public Optional<Player> getPlayer() {
        if (this.isInhabited())
            return Optional.of(getInhabitant().getPlayer());
        else
            return Optional.empty();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) throws CostAlreadyAssignedException {
        if (this.cost != DEFAULT_COST) {
            throw new CostAlreadyAssignedException(this);
        } else {
            this.cost = cost;
        }
    }

    public Coordinates getCoords() {
        return coords;
    }

    public void addNeighbour(TileImplementation tile){
      neighbours.add(tile);
    }

    public void setStronghold(){
      stronghold = true;
    }

    public boolean isStronghold(){
      return stronghold;
    }

    public void checkAppealAndReact(int appeal){
      int knownAppeal = getInhabitant().getAppeal();
      if (knownAppeal != appeal){
          unHabit();
      }
    }

    private void unHabit(){
        getInhabitant().getPlayer().removeOrganism();
        if(isStronghold()){
            getInhabitant().getPlayer().removeStronhold();
        }
        inhabitant = null;
    }

    int getMaximumNeighbouringFriendsCount() {
        return maximumNeighbouringFriendsCount;
    }

    void setMaximumNeighbouringFriendsCount(int maximumNeighbouringFriendsCount) {
        this.maximumNeighbouringFriendsCount = maximumNeighbouringFriendsCount;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TileImplementation tile = (TileImplementation) o;
        if (isStronghold() != tile.isStronghold()) return false;
        if (cost != tile.cost) return false;
        if (isInhabited() != isInhabited()) return false;
        if (isInhabited() && (!inhabitant.equals(tile.inhabitant))) return false;
        return true;
    }
}
