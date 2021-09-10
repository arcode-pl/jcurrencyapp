package pl.arcode.jcurrencyapp.data.provider.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileController {
	@SuppressWarnings("unused")
	private static String readFile(String filePath) throws IOException {
		return Files.readString(Path.of(filePath), Charset.defaultCharset());
	}
}
