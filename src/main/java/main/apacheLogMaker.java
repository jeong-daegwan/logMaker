package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class apacheLogMaker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//211.47.122.86 - - [11/Apr/2013:20:23:35 +0900] "GET /cabledr/update/CDrUpdate.inf HTTP/1.1" 200 4299
		final Charset UTF8 = Charset.forName("UTF-8");
		Writer fileOut;
		try {
			try {
				String filePath = args[0];
				File f = new File(filePath);
				if (!f.getParentFile().exists()) f.getParentFile().mkdirs();
				FileOutputStream fos = new FileOutputStream(f);
				fileOut = new OutputStreamWriter(fos, UTF8);
		        fileOut.write(65279); // BOM write
			} catch (Exception e) {
	        	System.out.println("=============================");
	        	System.out.println("usage: filePath rows type");
	        	System.out.println("example: /home/20150101.log 1000000");
	        	System.out.println("=============================");
	        	throw new RuntimeException();
	        }
	        
	        int cnt = 0;
	        try {
		        String count = args[1];
	        	cnt = Integer.parseInt(count);
	        } catch (NumberFormatException nfe) {
	        	System.out.println("=============================");
	        	System.out.println("usage: filePath rows type");
	        	System.out.println("example: /home/20150101.log 1000000");
	        	System.out.println("=============================");
	        	throw new RuntimeException();
	        }
	        
	        for (int i=0; i<cnt; i++) {
	        	
		        StringBuffer sb = new StringBuffer();
		        
		        Random rnd = new Random();
		        
		        sb
		        .append(rnd.nextInt(255))
		        .append(".")
		        .append(rnd.nextInt(255))
		        .append(".")
		        .append(rnd.nextInt(255))
		        .append(".")
		        .append(rnd.nextInt(255));
		        
		        sb.append(" - - ");
		        
		        sb
		        .append("[")
		        .append(new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss").format(new Date()))
		        .append(" +0900] \"GET /cabledr/update/CDrUpdate.inf HTTP/1.1\" ");
		        
		        sb
		        .append(rnd.nextInt(599))
		        .append(" ")
		        .append(rnd.nextInt(99999));
		        
		        fileOut.write(sb.toString());
		        fileOut.write("\n");
		        fileOut.flush();
		        System.out.println(i + " / " + cnt);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
