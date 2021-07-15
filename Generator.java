import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
	private int[] startState = new int[] {1,2,3,8,0,4,7,6,5};
	private int[] currentState = startState.clone();
	private enum expansionDirectionType{Left,Right,Up,Down};
	private expansionDirectionType lastMove;
	private List<expansionDirectionType> validMoves = new ArrayList<expansionDirectionType>();
	Random rand = new Random();
	
	public Generator() {
	}
	
	public void scramble(int numMoves) {
		
		for(int i = 0; i < numMoves; i++) {
			findValidMoves();
			randomlySelectMove();
		}
	}
	
	public int[] getPuzzle() {
		return currentState;
	}
	
	private void findValidMoves() {
		int blankSpace = blankIndex(currentState);
		int[] zeroPos = indexToPosition(blankSpace);
		validMoves.clear();
		
		if(zeroPos[0]>0 && (lastMove != expansionDirectionType.Right)) {
			validMoves.add(expansionDirectionType.Left);
		}
		
		if(zeroPos[0]<2 && (lastMove != expansionDirectionType.Left)) {
			validMoves.add(expansionDirectionType.Right);
		}
		
		if(zeroPos[1]>0 && (lastMove != expansionDirectionType.Down)) {
			validMoves.add(expansionDirectionType.Up);
		}
		
		if(zeroPos[1]<2 && (lastMove != expansionDirectionType.Up)) {
			validMoves.add(expansionDirectionType.Down);
		}
	}
	
	public int blankIndex(int[] problem) {
		for (int i = 0; i < 9; i++) {
			if (problem[i] == 0) {
				return i;
			}
		}
		return -1;
	}
	
	private int[] indexToPosition(int index) {
		int x;
		int y;
		x = index%3;
		y = (index-x)/3;
		return(new int[] {x,y});
	}
	
	private void randomlySelectMove() {
		lastMove = validMoves.get(rand.nextInt(validMoves.size()));
		swapTiles(lastMove);
	}
	
	private void swapTiles(expansionDirectionType move) {
		int blankSpace = blankIndex(currentState);
		switch(move) {
			case Left:
				currentState[blankSpace] = currentState[blankSpace-1];
				currentState[blankSpace-1] = 0;
				break;
			case Right:
				currentState[blankSpace] = currentState[blankSpace+1];
				currentState[blankSpace+1] = 0;
				break;
			case Up:
				currentState[blankSpace] = currentState[blankSpace-3];
				currentState[blankSpace-3] = 0;
				break;
			case Down:
				currentState[blankSpace] = currentState[blankSpace+3];
				currentState[blankSpace+3] = 0;
				break;
		}
	}

}
