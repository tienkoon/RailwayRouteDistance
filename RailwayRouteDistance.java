 import java.util.*;
 //set path=c:\Program Files\Java\jdk-12.0.1\bin 	
 class Q1 {
	static String[][] input_graph = {
	{"A", "B", "5"}, 
	{"B", "C", "4"}, 
	{"C", "D", "8"}, 
	{"D", "E", "6"}, 
	{"A", "D", "5"}, 
	{"C", "E", "2"}, 
	{"E", "B", "3"}, 
	{"A", "E", "7"}
	};
	public static void main(String args[]){
		String a;
		String dest;
		String source;
		String distance;
		int counter;
				
		Scanner in = new Scanner(System.in);
		counter = 0;
		do{
			System.out.println("Enter an input graph (end with -1):");
			a = in.nextLine();
			String[] input_array = a.split("-");
			for (int i=0; i<input_array.length; i++){
				// System.out.println(input_array[i]);
				if(input_array[i].length() != 1){
					System.out.println("NO SUCH ROUTE");
					break;
				}
			}		
			String route = planRoute(a,5);

			if(route.indexOf("###")>-1){
				System.out.println("NO SUCH ROUTE");
				continue;
			}
			int numOfTrips = countChar(route,'-');
			System.out.println("Shortest Route: "+route);
			int pointer  =0 ;
			int total_dist = 0;
			int res_dist = 0;
			for(int i = 0; i < numOfTrips; i++){
				String trip = route.substring(route.indexOf('-',pointer)-1,route.indexOf('-',pointer)+2);
				String[] tmp_array = trip.split("-");
				
				res_dist = findTripDist(tmp_array[0],tmp_array[1]);
				
				if(res_dist == -1){
					System.out.println("NO SUCH ROUTE");
					break;
				}
				total_dist += res_dist;
				System.out.println(trip+": "+res_dist);
				pointer = route.indexOf('-',pointer)+1;
			}
			if(res_dist != -1){
				System.out.println("TOTAL DISTANCE: "+total_dist);
			}
			
		}while(!a.equals("-1"));
			
		System.out.println("You have completed your input");

	}

	private static int findTripDist(String start_pt, String end_pt){
		int ret = 0;
		
		for(int i=0; i< input_graph.length; i++){
			if(input_graph[i][0].equals(start_pt)){
				if(input_graph[i][1].equals(end_pt)){
					ret = Integer.parseInt(input_graph[i][2]);
					return ret;			
				}				
			}
		}
		return ret;
	}

	private static int countChar(String haystack, char needle){
		int count = 0;
		for(int i=0; i< haystack.length(); i++){
			if(haystack.charAt(i) == needle){
				count++;
			}
		}
		return count;
	}

	private static String planRoute(String in_route, int r_level){
		String ret = "0";
		List<String> ret2 = new ArrayList<String>();
		List<Integer> possibleRoute = new ArrayList<Integer>();
		if(r_level == 0){
			return "###";
		}
		String[] tmp_array = in_route.split("-");
		String start_pt = tmp_array[0];
		String end_pt = tmp_array[1];
		
		for(int i=0; i< input_graph.length; i++){
			if(input_graph[i][0].equals(start_pt)){
				possibleRoute.add(i);
			}
		}
			
		for(int i=0; i< possibleRoute.size(); i++){
			ret = start_pt;
			String[] tmp_stpt = input_graph[possibleRoute.get(i)];
			if(tmp_stpt[0].equals(start_pt)){
				if(tmp_stpt[1].equals(end_pt)){
					ret = ret+"-"+tmp_stpt[1];
					return ret;			
				}else{
					int tmp_level = r_level -1;
					ret = ret+"-" +planRoute(tmp_stpt[1]+"-"+end_pt,tmp_level);
				}
					
			}
			if(r_level==5){
				ret2.add(ret);
			}
		} 	
				
		if(r_level==5){
			System.out.println("Possible Routes"+ret2);
		
		}
		int tempcounter = 0;
		for(int i = 0; i < ret2.size(); i++){
			tempcounter = countChar(ret,'-');
			if(countChar(ret2.get(i),'-') < countChar(ret,'-')){
					ret = ret2.get(i);
			}
		}
		return ret;
	}
}







