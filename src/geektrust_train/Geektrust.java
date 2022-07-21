package geektrust_train;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Geektrust {

	public static void main(String[] args) {
//        List<String> trainA = new ArrayList<>(Arrays.asList("ENGINE","SLM","BLR","KRN","HYB", "SLM", "NGG","ITP"));
//        List<String> trainB = new ArrayList<>(Arrays.asList("ENGINE","SSR","MAO","NJP","PNE", "PTA"));
//        String[] trainA = {"ENGINE","SLM","BLR","KRN","HYB", "SLM", "NGP","ITJ"};
//        String[] trainB = {"ENGINE","SRR","MAO","NJP","PNE", "PTA"};

	List<String> train = new ArrayList<String>();
	String[] trainA = null;
	String[] trainB = null;
	File file = new File(args[0]);

	// Note: Double backquote is to avoid compiler
	// interpret words
	// like \test as \t (ie. as a escape sequence)

	// Creating an object of BufferedReader class

	try {
		BufferedReader br = new BufferedReader(new FileReader(file));

		// Declaring a string variable
		String st;
		// Condition holds true till
		// there is character in a string
		try {
			while ((st = br.readLine()) != null) {

				train.add(st);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String train1 = train.get(0);
	String train2 = train.get(1);

	trainA = train1.split(" ");
	trainB = train2.split(" ");

	TrainMerge trainAB= new TrainMerge();
	trainAB.showMergeRoute(trainA, trainB);

//        String[] trainA = {"ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP" ,"BLR"};
//        String[] trainB = {"ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA"};


	}

}
