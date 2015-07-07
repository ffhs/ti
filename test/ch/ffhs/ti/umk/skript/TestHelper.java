package ch.ffhs.ti.umk.skript;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestHelper {

	public static final String UNEXP_RESULT = "Unexpected Result for input ";

	public static String write(String... lines) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines.length; i++) {
			sb.append(lines[i]).append("\n");
		}
		return sb.toString();
	}
	
	public static String evalFile(String filename) throws URISyntaxException, IOException, Exception {
		URI uri = FileTest.class.getClassLoader().getResource(filename).toURI();
		BufferedReader reader = Files.newBufferedReader(Paths.get(uri), Charset.forName("UTF-8"));
		return ScannerCompiler.evaluateInput(reader);
	}
	
}
