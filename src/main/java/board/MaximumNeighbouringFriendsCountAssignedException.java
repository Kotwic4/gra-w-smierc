package board;

public class MaximumNeighbouringFriendsCountAssignedException extends RuntimeException {
    public MaximumNeighbouringFriendsCountAssignedException() {
        super("Tiles have already their maximum neighbouring friends count assigned");
    }
}
