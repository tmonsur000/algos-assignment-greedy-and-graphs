/**
 * Public Transit
 * Author: Tayeba Monsur and Carolyn Yao
 * Does this compile? Y/N - Y */ 

public class FastestRoutePublicTransit { 

	public int myShortestTravelTime( int S, int T, int startTime, int[][] lengths, int[][] first, int[][] freq) { 
	   int numVertices = lengths[0].length; 
	   int[] times = new int[numVertices]; 
	   
	   // processed[i] will hold true if vertex i's shortest time is already finalized 
	   Boolean[] processed = new Boolean[numVertices];
	   
	   for (int v = 0; v < numVertices; v++) {
              times[v] = Integer.MAX_VALUE; 
	            processed[v] = false; }

	   //we have to calculate the start time from 5:30AM which is source s 
	   //we have to add variable startTime to the source vertex to calculate the actual start time

	   int sourceST = times[S] + startTime; 
	   times[S] = sourceST; 
	   int resultTime;
	   
	 
	   // Finds shortest path to all the vertices 
	   for (int count = 0; count < numVertices - 1 ; count++) {
     
        // Pick the min distance vertex from the not processed array and mark the processed vertext as "processed"
	      int u = findNextToProcess(times, processed); 
	      processed[u] = true;
          
        // Update all the adjacent vertices of the picked vertex. 
        for (int v = 0; v < numVertices; v++) {
        	
		        //condition for if the start time is actually greater than the first train 
		        if(times[u] > first[u][v]) {
                if(freq[u][v]==0){ 
				          resultTime = times[u] + lengths[u][v];
                    } 
			          else resultTime = times[u] + freq[u][v]; 
                } 
		
		       //if time of first train is greater than start time just add that time 
		       else {
                 int travelTime = first[u][v] - times[u]; 
		             resultTime = times[u] + lengths[u][v] + travelTime; 
              }
          
                
		       // Update time[v] iff it's not processed yet,  
		      if (!processed[v] && lengths[u][v]!=0 && times[u] != Integer.MAX_VALUE && resultTime < times[v]) {
                	times[v] = resultTime; } 
			          } 
		        }
	 
		    //return final value minus the startTime 
		    return times[T] - startTime; } 
   
   public int findNextToProcess(int[] times, Boolean[] processed) { 
		  int min = Integer.MAX_VALUE; 
		  int minIndex = -1; 
		  for (int i = 0; i < times.length; i++) {
      			if (processed[i] == false && times[i] <= min) {
        		min = times[i]; 
			minIndex = i; } } 
			return minIndex; } 
	
   public void printShortestTimes(int times[]) { 
		System.out.println("Vertex Distances (time) from Source"); 
		for (int i = 0; i < times.length; i++)
        		System.out.println(i + ": " + times[i] + " minutes"); 
      } 

   public void shortestTime(int graph[][], int source) { 
		  int numVertices = graph[0].length;  
	
	    // This is the array where we'll store all the final shortest times 
	    int[] times = new int[numVertices]; 
	
	   // processed[i] will true if vertex i's shortest time is already finalized 
	   Boolean[] processed = new Boolean[numVertices]; 
	
	  // Initialize all distances as INFINITE and processed[] as false 
	  for (int v = 0; v < numVertices; v++) {
      		times[v] = Integer.MAX_VALUE; 
		      processed[v] = false; 
        } 
	
	  // Distance of source vertex from itself is always 0 
	  times[source] = 0; 
	
	  // Find shortest path to all the vertices 
	  for (int count = 0; count < numVertices - 1 ; count++) {
      
	  // Pick the minimum distance vertex from the set of vertices not yet processed. 
	  // u is always equal to source in first iteration. 
	  // Mark u as processed. 
	  int u = findNextToProcess(times, processed); 
	  processed[u] = true; 
	
	  // Update time value of all the adjacent vertices of the picked vertex. 
	  for (int v = 0; v < numVertices; v++) {
        // Update time[v] only if is not processed yet, there is an edge from u to v, 
	      // and total weight of path from source to v through u is smaller than current value of time[v] 
	    if (!processed[v] && graph[u][v]!=0 && times[u] != Integer.MAX_VALUE && times[u]+graph[u][v] < times[v]) {
          	times[v] = times[u] + graph[u][v]; } 
		      } 
	     } 
	  printShortestTimes(times); 
	  } 

public static void main (String[] args) { 
	        /* length(e) */ 
	int lengthTimeGraph[][] = new int[][]{
  {0, 4, 0, 0, 0, 0, 0, 8, 0}, 
	{4, 0, 8, 0, 0, 0, 0, 11, 0}, 
	{0, 8, 0, 7, 0, 4, 0, 0, 2}, 
	{0, 0, 7, 0, 9, 14, 0, 0, 0}, 
	{0, 0, 0, 9, 0, 10, 0, 0, 0}, 
	{0, 0, 4, 14, 10, 0, 2, 0, 0}, 
	{0, 0, 0, 0, 0, 2, 0, 1, 6}, 
	{8, 11, 0, 0, 0, 0, 1, 0, 7}, 
	{0, 0, 2, 0, 0, 0, 6, 7, 0}}; 
	
	int freq[][] = new int[][]{
  {0, 5, 0, 3, 2, 5, 0, 4, 0},
	{0, 4, 2, 6, 5,  4, 5, 1, 9}, 
	{0, 7, 9, 2, 1, 0, 11, 1, 6},  
	{8, 11, 0, 0, 0, 0, 1, 0, 7},
	{0, 4, 2, 6, 4, 12, 3, 6, 9},
	{0, 5, 2, 7, 2, 0, 8, 0, 1},
	{2, 4, 5, 1, 5, 7, 9, 0, 9}, 
	{0, 0, 0, 0, 0, 2, 0, 1, 6},
	{0, 2, 5, 7, 9, 2, 8, 9, 0}}; 
	
	int first[][] = new int[][]{
  {2,3, 6, 2, 1, 5, 3, 1, 4},
	{3, 5, 2, 0, 2, 9, 0,1 ,4},
	{0, 2, 5, 3, 5, 0, 11, 17, 19},
	{2, 4, 6, 3, 5, 2, 6, 7, 3},
	{0, 1, 5, 2, 6, 3, 4, 9, 1},
	{12, 3, 1, 4, 5, 1, 2, 4, 0},
	{9, 2, 4, 0, 1, 3, 6, 8, 9}, 
	{0, 1, 4, 5, 15, 12, 4, 6, 9},
	{0, 2, 0, 0, 2, 3, 4, 5, 9}};
 
	FastestRoutePublicTransit t = new FastestRoutePublicTransit(); 
	t.shortestTime(lengthTimeGraph, 0);
    
    	//Extra credit
	FastestRoutePublicTransit t2 = new FastestRoutePublicTransit(); 
  int shortestTime = t2.myShortestTravelTime(0, 3, 1, lengthTimeGraph, first, freq); 
	System.out.println("My Shortest Travel Time between " + 0 + " and " + 3 + " is: " + shortestTime);
  }
}

