package apriori;

import java.io.*;
import helper.DataHelper;

public class Main {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		
		Double minsupport = 0.008;
		Double minconfidence = 0.5;
		String filename = "TestData.csv";
		//String filename = "test.csv";
		//Data_preprocessing5_noid.csv
		String writename = "text2.txt";
		
		DataHelper.init(minsupport,minconfidence);
		Apriori apriorihelper = new Apriori();
		apriorihelper.doApriori(minsupport, minconfidence, filename,writename);
		
	}

}
