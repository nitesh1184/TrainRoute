package geektrust_train;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TrainMerge {
	public static Map<String, Integer> trainA_map = new HashMap<>();
	public static Map<String, Integer> trainB_map = new HashMap<>();
	public static String[] trainA_Route = { "CHN", "TRAIN_A", "SLM", "BLR", "KRN", "HYB", "NGP", "ITJ", "BPL", "AGA",
			"NDL", "ENGINE", "PTA", "NJP", "GHY" };
	public static int[] trainA_Distance = { 0, 001, 350, 550, 900, 1200, 1600, 1900, 2000, 2500, 2700, 9999, 3800, 4200,
			4700 };
	public static String[] trainB_Route = { "TVC", "SRR", "TRAIN_B", "MAQ", "MAO", "PNE", "HYB", "NGP", "ITJ", "BPL",
			"PTA", "NJP", "GHY", "ENGINE", "AGA", "NDL" };
	public static int[] trainB_Distance = { 0, 300, 001, 600, 1000, 1400, 2000, 2400, 2700, 2800, 3800, 4200, 4700,
			9999, 2500, 2700 };

	public static void showMergeRoute(String[] routeA, String[] routeB) {

		for (int i = 0; i < trainA_Route.length; i++) {
			trainA_map.put(trainA_Route[i], trainA_Distance[i]);
		}

		for (int i = 0; i < trainB_Route.length; i++) {
			trainB_map.put(trainB_Route[i], trainB_Distance[i]);
		}
		List<String> trainA_list = new ArrayList<String>();
		List<String> trainB_List = new ArrayList<String>();
		for (String route : routeA) {

			if (trainA_map.get(route) >= trainA_map.get("HYB"))
				trainA_list.add(route);
		}
		for (String route : routeB) {
			if (trainB_map.get(route) >= trainB_map.get("HYB"))
				trainB_List.add(route);
		}
		Iterator trainA = trainA_list.iterator();
		System.out.print("ARRIVAL TRAIN_A ");
		while (trainA.hasNext())
			System.out.print(trainA.next() + " ");

		Iterator trainB = trainB_List.iterator();
		System.out.println("");
		System.out.print("ARRIVAL TRAIN_B ");
		while (trainB.hasNext())
			System.out.print(trainB.next() + " ");

		List<String> trainAB_list = mergeTrainRoutes(trainA_list, trainB_List);

		Iterator trainAB = trainAB_list.iterator();
		System.out.println("");
		while (trainAB.hasNext())
			System.out.print(trainAB.next() + " ");
	}

	private static List<String> mergeTrainRoutes(List<String> trainA_list, List<String> trainB_list) {

		Collections.sort(trainA_list, (a, b) -> {
			return (trainA_map.get(b) - trainA_map.get("HYB")) - (trainA_map.get(a) - trainA_map.get("HYB"));
		});
		Collections.sort(trainB_list, (a, b) -> {
			return (trainB_map.get(b) - trainB_map.get("HYB")) - (trainB_map.get(a) - trainB_map.get("HYB"));
		});

		List<String> departure = new ArrayList<>();
		departure.add("DEPARTURE TRAIN_AB");
		int idxA = 0, idxB = 0;
		while (idxA < trainA_list.size() && idxB < trainB_list.size()) {
			int hybA = trainA_map.get("HYB");
			int hybB = trainB_map.get("HYB");
			String routeA = trainA_list.get(idxA);
			String routeB = trainB_list.get(idxB);

			if (routeA == routeB) {
				departure.add(routeB);
				departure.add(routeA);
				idxA++;
				idxB++;
				continue;
			}
			if (trainA_map.get(routeA) - hybA >= trainB_map.get(routeB) - hybB) {
				departure.add(routeA);
				idxA++;
			}
			if (trainA_map.get(routeA) - hybA < trainB_map.get(routeB) - hybB) {
				departure.add(routeB);
				idxB++;
			}
		}
		while (idxA < trainA_list.size())
			departure.add(trainA_list.get(idxA++));
		while (idxB < trainB_list.size())
			departure.add(trainB_list.get(idxB++));
		departure.remove("HYB");
		return departure;
	}

	public static void main(String[] args) throws IOException {
//	        List<String> trainA = new ArrayList<>(Arrays.asList("ENGINE","SLM","BLR","KRN","HYB", "SLM", "NGG","ITP"));
//	        List<String> trainB = new ArrayList<>(Arrays.asList("ENGINE","SSR","MAO","NJP","PNE", "PTA"));
//	        String[] trainA = {"ENGINE","SLM","BLR","KRN","HYB", "SLM", "NGP","ITJ"};
//	        String[] trainB = {"ENGINE","SRR","MAO","NJP","PNE", "PTA"};

		List<String> train = new ArrayList<String>();
		String[] trainA = null;
		String[] trainB = null;
		File file = new File("E:\\Java Project\\Geektrust\\src\\train_challenge\\train");

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
			while ((st = br.readLine()) != null) {

				train.add(st);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String train1 = train.get(0);
		String train2 = train.get(1);

		trainA = train1.split(" ");
		trainB = train2.split(" ");

		showMergeRoute(trainA, trainB);

//	        String[] trainA = {"ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP" ,"BLR"};
//	        String[] trainB = {"ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA"};

	}
}
