package board;

import bot.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class TileImplementation implements Tile {
    private Organism inhabitant;
    private int cost;
    private Coordinates coords;
    private List<TileImplementation> neighbours;
    private boolean stronghold;
    private int maximumNeighbouringFriendsCount;
    private static int DEFAULT_COST = 1;
    protected static int DEFAULT_NEIGHBOURING_FRIENDS_COUNT = 4;
    private final Set<TileObserver> tileObservers;

    public TileImplementation(Coordinates coords) {
      this.coords = coords;
        cost = DEFAULT_COST;
        maximumNeighbouringFriendsCount = DEFAULT_NEIGHBOURING_FRIENDS_COUNT;
        neighbours = new LinkedList<>();
        tileObservers = Collections
                .newSetFromMap(new ConcurrentHashMap<TileObserver,
                        Boolean>(0));
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
            uncheckedSetIntabitant(new Organism(player));
            notifyObservers();
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
      if(!isInhabited()) {
          this.inhabitant = inhabitant;
          Player player = inhabitant.getPlayer();
          player.addOrganism();
          if(isStronghold()){
              player.addStronghold();
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
          notifyObservers();
      }
    }

    private void unHabit(){
        getInhabitant().getPlayer().removeOrganism();
        if(isStronghold()){
            getInhabitant().getPlayer().removeStronghold();
        }
        inhabitant = null;
    }

    int getMaximumNeighbouringFriendsCount() {
        return maximumNeighbouringFriendsCount;
    }

    void setMaximumNeighbouringFriendsCount(int maximumNeighbouringFriendsCount) {
        this.maximumNeighbouringFriendsCount = maximumNeighbouringFriendsCount;
    }

    public void setNeighbours(List<TileImplementation> neighbours) {
        this.neighbours = neighbours;
    }

    public void registerObserver(TileObserver tileObserver) {
        tileObservers.add(tileObserver);
    }

    public void removeObserver(TileObserver tileObserver) {
        tileObservers.remove(tileObserver);
    }

    private void notifyObservers() {
        for (TileObserver tileObserver : tileObservers) {
            tileObserver.update(this);
        }
    }
}
