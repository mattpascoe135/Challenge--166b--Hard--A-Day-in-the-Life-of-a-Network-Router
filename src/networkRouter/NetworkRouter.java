package networkRouter;

import java.util.ArrayList;
import java.util.List;

public class NetworkRouter {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		int numRouters = 0;
		try{
			numRouters = Integer.parseInt(args[0]);
		} catch(NumberFormatException e) { 
			System.out.println("Not valid number of integers");
		}
		int[][] distance = new int[numRouters][numRouters];
		System.out.println(""+numRouters);
		for(int i=0; i<numRouters; i++){
			String[] values = args[1+i].split(",");
			for(int j=0; j<numRouters; j++){
				try{
					distance[i][j] = Integer.parseInt(values[j]);
				} catch(NumberFormatException e) { 
					System.out.println("Not a valid integer");
				}
			}
		}
		
		//Get the start and end nodes
		String startLocation = args[1+numRouters];
		String endLocation = args[2+numRouters];

		System.out.println(startLocation + ": " + ((startLocation.charAt(0))-'A'));
		System.out.println(endLocation + ": " + ((endLocation.charAt(0))-'A'));
		
		// Determine the shortest path to exit
		//Hold a list in the format <[x, y, F, Xp, Yp], [...], ...>
		List<int[]> openList = new ArrayList<int[]>();
		//Hold a list in the format <[x, y, F, Xp, Yp], [...], ...>
		List<int[]> closedList = new ArrayList<int[]>();
		
		int[] startNode = {(startLocation.charAt(0))-'A', (startLocation.charAt(0))-'A', 0, (startLocation.charAt(0))-'A', (startLocation.charAt(0))-'A'};
		closedList.add(startNode);
		
		int destinationFound = 0;
		//Find adjacent tiles, add into openlist
		for(int i=0; i<numRouters; i++){
			if(distance[i][(startLocation.charAt(0))-'A'] != -1){
				if(i == ((endLocation.charAt(0))-'A')){
					System.out.println(startLocation.charAt(0) + endLocation.charAt(0));
					return;
				}
				int[] initialNode = {i, i, distance[i][(startLocation.charAt(0))-'A'], (startLocation.charAt(0))-'A', (startLocation.charAt(0))-'A'};
				openList.add(initialNode);
				System.out.println((char)(i+'A') + ": " + distance[i][(startLocation.charAt(0))-'A']);
			}
		}
		
		//Search for the shortest path
		while(destinationFound == 0 || !openList.isEmpty()){
			//Find the closest node, one with the smallest F value
			for(int i=0; i<openList.size()-1; i++){
				
			}
		}
	}
}
