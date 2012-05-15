package Database;

import Database.Primitives.Similarity;
import Database.Primitives.Song;
import Database.Primitives.User;
import java.util.Scanner;

public class NeighborhoodParser extends Parser {

        private Song currentSong;

        public NeighborhoodParser(Songs s, Users u, String f)  {
           super(s, u, f);
        }

    @Override
        protected void format(String dataLine) {
            if(dataLine.contains("-")){
                    formatSong(dataLine);
            } else {
                    formatSimilarity(dataLine);
            }
        }

        private void formatSong(String dataLine) {
            if (dataLine != null) {
                Song newSong = new Song(Integer.getInteger(dataLine));
                currentSong = newSong;
                songs.addSong(newSong);                
            }
            else {
                System.err.println("Unexpected format for song line: " + dataLine);
            }
        }

        private void formatSimilarity(String dataLine) {
            String[] splitData = dataLine.split("\t");
            if ((splitData != null) && (splitData.length == 3) ) {
                int neighborID = Integer.getInteger(splitData[1]);
                Song neighbor = songs.containsSong(neighborID) ? songs.getSong(neighborID) : new Song(neighborID);
                double neighborSim = Double.parseDouble(splitData[2]);
                Similarity sim = new Similarity(neighbor, neighborSim);
                currentSong.addToNeighborhood(sim);
            }
            else 
                System.err.println("Unexpected format for similarity line: " + dataLine);
        }


}
