package getLicense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class OutputFile {
	 public String outputFile(InputStream in) throws IOException {
	        try (BufferedReader br = new BufferedReader(new InputStreamReader(in));
	               ) {
	        		StringBuilder sb = new StringBuilder();
	        		String line= null;
	        		while((line=br.readLine())!=null) {
	        			sb.append(line);
	        		}

	        		return sb.toString();
//	            for (int len; (len = bin.read(buf)) >= 0;) {
//	                bout.write(buf, 0, len);
//	            }
	        }
	    }

}
