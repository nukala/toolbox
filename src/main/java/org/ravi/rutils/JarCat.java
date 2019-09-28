package org.ravi.rutils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarCat {

	private static boolean contains(String seq, String part) {
		if (seq == null || part == null) {
			return false;
		}

		return seq.contains(part);
	}

	private static List<String> findMatches(ZipFile zf, String entryName) {
		List<String> matchesList = new ArrayList<>();
		Enumeration<? extends ZipEntry> zeEnum = zf.entries();
		while (zeEnum.hasMoreElements()) {
			ZipEntry next = zeEnum.nextElement();

			String name = next.getName();
			if (contains(name, entryName)) {
				matchesList.add(name);
			}
		}

		return matchesList;
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.out.println("usage: java -jar jarcat.jar <jarfile> <path_of_jarfile_entry>");
			System.out.println("example: java -jar jarcat.jar myjar.jar \"META-INF/MANIFEST.MF\"");
			return;
		}
		String filename = args[0];
		String entryname = args[1];
		try (ZipFile zf = new ZipFile(filename)) {
			ZipEntry ze = zf.getEntry(entryname);
			if (ze == null) {
				System.err.printf("JarFile [%s] does not contain [%s]%n", filename, entryname);

				List<String> matches = findMatches(zf, entryname);
				if (matches.isEmpty()) {
					return;
				} else if (matches.size() > 1) {
					for (String m : matches) {
						System.out.printf("\t%s%n", m);
					}
					return;
				}

				ze = zf.getEntry(matches.get(0));
			}

			InputStream in = zf.getInputStream(ze);
			byte[] buf = new byte[1024 * 8];
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 8);
			for (int len = 0;;) {
				len = in.read(buf);
				if (len == -1) {
					break;
				}
				bos.write(buf, 0, len);
			}
			String str = new String(bos.toByteArray());
			System.out.printf("=== %s ===%n%s--- %s --- %n", ze.getName(), str, ze.getName());
		}
	}
}
