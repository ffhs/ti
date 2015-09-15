package ch.ffhs.ti.umk.skript;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class TerminalRunner {

	public TerminalRunner() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("... Type command ... 2+6; ... Execute by pressing enter key 2x...");
		for (;;)
        {
            try
            {
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                for (;;)
                {
                    String line = br.readLine();
                    if (line.trim().length() == 0) break;
                    sb.append(line);
                    sb.append('\n');
                }
                String script = sb.toString();
                if (script.trim().length() == 0)
                {
                    System.out.println("DONE");
                    return;
                }
        		System.out.println(ScannerCompiler.evaluateInput(new StringReader(script)));	
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
	}

}
