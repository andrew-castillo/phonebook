import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Phonebook {
	private static Formatter output;
	private static Scanner input;
	
	public static void main(String[] args){
		// function call for testing (checks "new" since that's what was in the project description)
		order_Check(selection_Sort(writeFirst_Array(Reader(), "new")));
		timeTracker();
		}
	
	//1//
	public static ArrayList<String[]> Reader() {
		ArrayList<String[]> first_array = new ArrayList<String[]>();
		try{
			input = new Scanner((Paths.get("Assignment5_phonebook.txt")));
			
		}catch(IOException ioException){
			System.out.println("Error");
		}
		while(input.hasNext()){
			String number = input.next();
			String first = input.next();
			String last = input.next();
			String[] entry = {number, first, last};
			
			first_array.add(entry);
		}
		if (input != null)
			input.close();
		return first_array;
	}
	
	
	//2//
	
	//Sources: Stackoverflow <http://stackoverflow.com/questions/21554109/write-the-contents-of-an-arraylist-to-a-text-file>
	// <http://stackoverflow.com/questions/2275004/in-java-how-do-i-check-if-a-string-contains-a-substring-ignoring-case>
	// <https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html>
	// <http://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the>
	// Class lecture slides

	public static ArrayList<String[]> writeFirst_Array(ArrayList<String[]> sortedArray, String match){
		try{
		output = new Formatter("Output.txt");
		}catch(FileNotFoundException notFound){
			System.out.println("Error");
			System.exit(1);
		}
		ArrayList<String[]> transfer_array = new ArrayList<String[]>();
		
		for (int place = 0; place < sortedArray.size(); place++){
			
			if ((sortedArray.get(place)[1].indexOf(match)) >= 0){
				transfer_array.add(sortedArray.get(place));
			}else if((sortedArray.get(place)[2].indexOf(match)) >= 0){
				transfer_array.add(sortedArray.get(place));
			}else{
				continue;
			}
		}
		
		for (String[] entry : transfer_array){
			output.format("%s %s %s\n", entry[0], entry[1], entry[2]);
		}
		
		if(null != output)
			output.close();
		return transfer_array;
	}
	
	public static ArrayList<String[]> ArrayListPreserver(ArrayList<String[]> original){
		ArrayList<String[]> preserved_array = new ArrayList<String[]>();
		for(String[] ln : original){
			preserved_array.add(ln.clone());
		}
		return preserved_array;
	}

	
	//3//
	//Sources: Stackoverflow <http://stackoverflow.com/questions/8362640/java-selection-sort-algorithm>
	// <http://stackoverflow.com/questions/9720533/implementation-of-selection-sort-in-java>
	// <http://stackoverflow.com/questions/19508159/selection-sort-java>
	// math.cs.emory.edu <http://www.mathcs.emory.edu/~cheung/Courses/170/Syllabus/09/sel-sort.html>
	// tutorialspoint.com <https://www.tutorialspoint.com/data_structures_algorithms/selection_sort_algorithm.htm>
	// Class lecture slides
	
	
    public static ArrayList<String[]> selection_Sort(ArrayList<String[]> unsortedArray)
    {
	ArrayList<String[]> select_array = ArrayListPreserver(unsortedArray);
	int tracker = 0;
	int low = 0;
	String[] lowest = select_array.get(0);
	
	for (int i = 0; i < select_array.size() - 1; i++){
	    for (int j = tracker; j < select_array.size(); j++){
	    	
		if (lowest[1].compareTo(select_array.get(j)[1]) > 0){
		    lowest = select_array.get(j);
		    Collections.swap(select_array, j, tracker);
		    low = j;
		}
	}
	    tracker++;
	    low = tracker;
	    lowest = select_array.get(low);
	}
	return select_array;
}
	
	
	//4//
	//Sources: Stackoverflow <http://stackoverflow.com/questions/13727030/mergesort-in-java>
	// <http://stackoverflow.com/questions/25830101/merge-sort-example>
	// <http://stackoverflow.com/questions/1604869/java-recursion-and-merge-sort>
	// <http://stackoverflow.com/questions/1735863/merge-sort-java>
	// tutorialspoint.com <https://www.tutorialspoint.com/data_structures_algorithms/merge_sort_algorithm.htm>
	// java2notice.come <http://www.java2novice.com/java-sorting-algorithms/merge-sort/>
	// Math 232 algorithm
	// Class lecture slides
	
	
    public static ArrayList<String[]> merge(ArrayList<String[]> first_array, ArrayList<String[]> second_array){
	ArrayList<String[]> complete = new ArrayList<String[]>();
	while(!(first_array.isEmpty() || second_array.isEmpty())){
	    if(first_array.get(0)[1].compareTo(second_array.get(0)[1]) <= 0){
	    	complete.add(first_array.remove(0));
	    }else{
	    	complete.add(second_array.remove(0));
	    }
	}
	for (String[] entry : first_array){
		complete.add(entry);
	}
	for (String[] entry : second_array){
		complete.add(entry);
	}
	return complete;
}   
    
    public static ArrayList<String[]> mergeSort(ArrayList<String[]> unsortedArray){
    
	ArrayList<String[]> first_array = ArrayListPreserver(unsortedArray);
	ArrayList<String[]> second_array = new ArrayList<String[]>();
	ArrayList<String[]> third_array = new ArrayList<String[]>();
	
	if (first_array.size() == 1){
	    return first_array;
	}
	
	for (int i = 0; i < Math.floorDiv(first_array.size(), 2); i++){ //Using floored division as per instruction in math 232 class
	    second_array.add(first_array.get(i));
	}
	
	for (int i = Math.floorDiv(first_array.size(), 2); i < first_array.size(); i++){
		third_array.add(first_array.get(i));
	}
	
	return merge(mergeSort(second_array), mergeSort(third_array));
    }
    
    
	//5//
    public static Boolean order_Check(ArrayList<String[]> array){
    String iv = "e";
	Boolean ordered = true;
	
	for (String[] line : array){
	    if (0 < line[1].compareTo(iv)){
	    	iv = line[1];
	    	continue;
	    }
	    ordered = false;
	    iv = line[1];
	}
	return ordered;
    }
	
	//6//
    public static void timeTracker(){
	ArrayList<String[]> first_array = Reader();
	long ssStart = System.currentTimeMillis();
	selection_Sort(first_array);
	long ssEnd = System.currentTimeMillis();
	String selectionTime1 = String.valueOf((ssEnd - ssStart) / (Math.pow(10, 3)));
	
	System.out.println("Selection Sort: " + selectionTime1 + " s");
	
	
	ssStart = System.currentTimeMillis();
	mergeSort(first_array);
	ssEnd = System.currentTimeMillis();
	String selectionTime2 = String.valueOf((ssEnd - ssStart) / (Math.pow(10, 3)));
	
	System.out.println("Merge Sort: " + selectionTime2 + " s");
    }
}
