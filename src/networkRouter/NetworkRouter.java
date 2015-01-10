package networkRouter;

import java.util.ArrayList;
import java.util.List;

public class NetworkRouter {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		//Go through the input data and place into relevant variables
		int numRouters = 0;
		try{
			numRouters = Integer.parseInt(args[0]);
		} catch(NumberFormatException e) { 
			System.out.println("Not valid number of integers");
		}
		int[][] distance = new int[numRouters][numRouters];
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
			}
		}
		
		//Search for the shortest path
		while(destinationFound == 0 && !openList.isEmpty()){
			int closest = -1;
			int closestNode = -1;
			int listLocation = -1;
			//Find the closest node, one with the smallest F value
			for(int i=0; i<openList.size(); i++){
				if(closest == -1){
					closest = openList.get(i)[2];
					closestNode = openList.get(i)[0];
					listLocation = i;
				}
				if(openList.get(i)[2] < closest){
					closest = openList.get(i)[2];
					closestNode = openList.get(i)[0];
					listLocation = i;
				}
			}
			
			//Remove from openList, add to closedList
			closedList.add(openList.get(listLocation));
			int[] temp = openList.get(listLocation);
			openList.remove(listLocation);
			
			//Check if the node is the end point
			if(closestNode == ((endLocation.charAt(0))-'A')){
				destinationFound = 1;
			}
			
			//Find new adjacent tiles, check if at the end
			for(int i=0; i<numRouters; i++){
				int duplicate = 0;
				for(int j=0; j<closedList.size(); j++){
					if(closedList.get(j)[0] == i){
						duplicate = 1;
					}
				}
				if(distance[i][closestNode] != -1 && duplicate == 0){
					int openDuplicate = 0;
					for(int j=0; j<openList.size(); j++){
						if(openList.get(j)[0] == i){
							if(openList.get(j)[2] > (distance[i][closestNode]+closest)){
								openList.remove(j);
							}else{
								openDuplicate = 1;
							}
							
						}
					}
					if(openDuplicate == 0){
						int[] initialNode = {i, i, distance[i][closestNode]+closest, closestNode, closestNode};
						openList.add(initialNode);
					}
				}
			}
		}
		
		//Backtrack to from the final location back to the starting point
		String path = "";
		int foundStart = 0;
		int activeNode = endLocation.charAt(0)-'A';
		while(foundStart == 0){
			for(int i=0; i<closedList.size(); i++){
				if(closedList.get(i)[0] == activeNode){
					activeNode = closedList.get(i)[3];
					path += (char)(closedList.get(i)[0]+'A');
					
				}
			}
			
			if(activeNode == startLocation.charAt(0)-'A'){
				path += startLocation.charAt(0);
				foundStart = 1;
			}
		}
		//Reverse the string 
		path = new StringBuilder(path).reverse().toString();
		System.out.println(path);
	}
}
