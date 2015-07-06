package ch.ffhs.ti.umk.skript;

public class TestHelper {

	public static String lines(String... lines) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines.length; i++) {
			sb.append(lines[i]);
			sb.append("\n");
		}
		return sb.toString();
	}
}
