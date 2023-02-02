package webdriver.maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class testing {

	public static void main(String[] args) {
		 Multimap<String, String> multimap = ArrayListMultimap.create();

		 multimap.put("Department Code","1111");
		 multimap.put("Charge Code","5465158");
		 multimap.put("Department Code","2016");
		 multimap.put("Charge Code","8460206");
		 multimap.put("Department Code","2111");
		 multimap.put("Charge Code","90118");
		 multimap.put("Department Code","2220");
		 multimap.put("Charge Code","117");
		 multimap.put("Department Code","2269");
		 multimap.put("Charge Code","8141517");
		 multimap.put("Department Code","2330");
		 multimap.put("Charge Code","7715139");
		 multimap.put("Department Code","3030");
		 multimap.put("Charge Code","2783033");
		 multimap.put("Department Code","4021");
		 multimap.put("Charge Code","8407066");
		 for (Map.Entry<String,String> entry : multimap.entries()) {
	            System.out.println("Key = " + entry.getKey() +
	                             ", Value = " + entry.getValue());
	            List<String> list = new ArrayList<>();
		 }
	}

}
