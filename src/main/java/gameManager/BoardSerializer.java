package gameManager;

import board.Board;
import board.Coordinates;
import board.Tile;
import util.BoardHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Created by michal on 06.01.18.
 */
public class BoardSerializer {
    public static Board load(BufferedReader br) throws MalformedFileException {
        String currentLine;
        Board.BoardBuilder boardBuilder = null;
        try {
            currentLine = br.readLine();
            if(currentLine == null){
                throw new MalformedFileException();
            }
            if(!currentLine.matches("width=[0-9]*")){
                throw new MalformedFileException();
            }
            int width = 0, height = 0;
            try{
                width = Integer.parseInt(currentLine.substring(6));
            } catch (NumberFormatException ex) {
                throw new MalformedFileException();
            }
            currentLine = br.readLine();
            if(currentLine == null) {
                throw new MalformedFileException();
            }
            if(!currentLine.matches("height=[0-9]*")){
                throw new MalformedFileException();
            }
            try{
                height = Integer.parseInt(currentLine.substring(7));
            } catch (NumberFormatException ex) {
                throw new MalformedFileException();
            }

            boardBuilder = new Board.BoardBuilder(width,height, new BoardHelper<>());
            for(int line=0; line<height; line++){
                currentLine = br.readLine();
                if(currentLine == null){
                    throw new MalformedFileException();
                }
                StringTokenizer str = new StringTokenizer(currentLine);
                for(int col = 0; col<width; col++){
                    if(!str.hasMoreElements()){
                        throw new MalformedFileException();
                    }
                    String token = (String) str.nextElement();

                    //[cost]
                    if(!token.matches("\\[([0-9]*),([0-9]*)]")){
                        throw new MalformedFileException();
                    }

                    StringTokenizer properties = new StringTokenizer(token.substring(1, token.length()-1), ",");

                    String cost, stronghold;
                    try{
                        cost = (String) properties.nextElement();
                        stronghold = (String) properties.nextElement();
                        boardBuilder.setTileCost(Integer.parseInt(cost), new Coordinates(line,col));
                        if(Integer.parseInt(stronghold) != 0) {
                            boardBuilder.markAsStronghold(new Coordinates(line,col));
                        }
                    } catch (NoSuchElementException ex){
                        throw new MalformedFileException();
                    } catch (NumberFormatException ex) {
                        throw new MalformedFileException();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return boardBuilder.build();
    }
    public static void save(Board board, Writer writer) throws IOException {
        writer.write(getSerializedString(board));
    }
    public static String getSerializedString(Board board) {
        if(board == null){
            return null;
        }

        int width, height;
        width = board.getWidth();
        height = board.getHeight();

        StringBuilder sb = new StringBuilder();
        sb.append("width=");
        sb.append(width);
        sb.append("\n");
        sb.append("height=");
        sb.append(height);
        sb.append("\n");

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                sb.append(tileToString(board.getTile(new Coordinates(i,j))));
                if(j < width-1)sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static String tileToString(Tile tile){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(tile.getCost());
        sb.append(",");
        if(tile.isStronghold()){
            sb.append(1);
        } else {
            sb.append(0);
        }
        sb.append("]");
        return sb.toString();
    }

    public static class MalformedFileException extends Exception{

    }
}
