import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Interval implements Comparable<Interval>{
	int begin;
	int end;
	int weight;
	Interval(int begin, int end, int weight){
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}
	public int compareTo(Interval b) {
		if(this.end > b.end) return 1; 
	    if(this.end < b.end) return -1;
	    else return 0;
	}	
}

public class WeightedInterval {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int instances = scan.nextInt();
		while(instances-- >0) {
			//Retrieve data
			int numJobs = scan.nextInt();
			ArrayList<Interval> schedule = new ArrayList<Interval>();
			for(int i = 0; i < numJobs; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			Interval job;
			job = new Interval(a,b,c);
			schedule.add(job);
			}
			Collections.sort(schedule);//O(N*Log(N)) Sorted sigma
			ArrayList <Long> minweight = new ArrayList<Long>();
			minweight.add((long) 0);
			for(int j = 1; j <= numJobs; ++j) {
				//FIND index i
				int job_before = 0;
				for(int i = j-2; i>=0; i--) {
					if(schedule.get(i).end<=schedule.get(j-1).begin) {
						job_before = i+1;
						break;
					}
				}
				//Bellman Equation
				if(minweight.get(j-1)>(minweight.get(job_before)+schedule.get(j-1).weight)) {
					minweight.add(minweight.get(j-1));
				}
				else {
					minweight.add(minweight.get(job_before)+schedule.get(j-1).weight);
				}
			}
			System.out.println(minweight.get(numJobs));			
		}
		scan.close();
	}
}
