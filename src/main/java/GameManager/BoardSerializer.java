package GameManager;

import board.Board;
import board.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.IntSummaryStatistics;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Created by michal on 06.01.18.
 */
public class BoardSerializer {
    public static Board load(BufferedReader br) throws MalformedFileException {
        String currentLine;
        Board board = null;
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

            board = new Board(width, height);
            Tile[][] tiles = board.getTiles();

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
                        tiles[line][col].setCost(Integer.parseInt(cost));
                        if(Integer.parseInt(stronghold) != 0) {
                            tiles[line][col].setStronghold();
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
        return board;
    }
    public static class MalformedFileException extends Exception{

    }
}
