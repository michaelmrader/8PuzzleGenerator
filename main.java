package eightPuzzleGenerator;

import java.util.Arrays;
import java.io.*;


public class Main {
	
	/* Example code, this creates 15 puzzles with a difficulty increase of 5 after every 5 iterations */
	/* Will create a file called puzzles.txt which has 5 puzzles of difficulty 5, 5 of difficulty 10 and 5 of difficulty 15 */
	public static void main(String[] args) throws IOException{
		FileWriter puzzles = new FileWriter("puzzles.txt");
		int puzzleDifficulty = 0;
		for(int i = 0; i < 15; i++) {
			if (i%5 == 0) {
				puzzleDifficulty += 5;
				System.out.println("Puzzle Difficulty is: " + puzzleDifficulty);
			}
			// Create new generator each time. It would be more efficient to have a reset function so I can reuse the same object each time. Oh well. 
			Generator gen = new Generator();
			gen.scramble(puzzleDifficulty);
			puzzles.write(Arrays.toString(gen.getPuzzle()));
			puzzles.write("\n");
		}
		puzzles.flush();
		puzzles.close();

		
	}

}
