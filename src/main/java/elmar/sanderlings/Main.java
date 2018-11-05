package elmar.sanderlings;

public class Main {
	public static final int SIZE = 8;

	public static void main(String[] args) {
		int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };//clockwise
		int[] currentPos = { 0, -1 };
		
//		int[][] directions = { { 1 ,0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };//counter-clockwise 
//		int[] currentPos = { -1, 0 };
		
		int[][] martrix = new int[SIZE][SIZE];
		int number = 1;
		int currentDir = 0;
		int attemptOnCurrentPos = 0;
		while (attemptOnCurrentPos < directions.length) {
			int[] nextPos = { currentPos[0] + directions[currentDir][0], currentPos[1] + directions[currentDir][1] };

			boolean validPos = isValidPos(martrix, nextPos);

			if (validPos) {
				currentPos = nextPos;
				martrix[nextPos[0]][nextPos[1]] = number++;
				attemptOnCurrentPos = 0;
			} else {
				currentDir = (currentDir + 1) % directions.length;
				attemptOnCurrentPos++;
			}
		}

		// output
		for (int i = 0; i < martrix.length; i++) {
			for (int j = 0; j < martrix[i].length; j++) {
				System.out.print(martrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static boolean isValidPos(int[][] martrix, int[] pos) {
		boolean validPos = pos[0] >= 0 && pos[0] < SIZE;
		validPos = validPos && pos[1] >= 0 && pos[1] < SIZE;
		validPos = validPos && martrix[pos[0]][pos[1]] == 0;
		return validPos;
	}
}
