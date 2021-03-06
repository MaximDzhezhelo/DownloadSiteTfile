package ua.kiev.makson.work_in_site;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.log4j.Logger;

public class FileWrite {
	private static final Logger LOGGER = Logger.getLogger(FileWrite.class);

	public void writeInFile(String docPage, File rootDirectory, String charset, String defaultReadName) {
		rootDirectory = new File(rootDirectory, defaultReadName);

		ifExists(rootDirectory);
		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(rootDirectory), charset));) {
			bw.write(docPage);
			bw.flush();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
		LOGGER.info("write Page of Site in " + charset);
	}

	public void readAndWrite(HttpEntity entity, File directory) {

		try (BufferedInputStream bis = new BufferedInputStream(entity.getContent())) {
			try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(directory))) {
				int count;
				int size = 0;
				byte[] data = new byte[1024];
				while ((count = bis.read(data, 0, 1024)) != -1) {
					bos.write(data, 0, count);
					size = size + count;
				}
			} catch (IOException ex) {
				LOGGER.error(ex.getMessage());
			}
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	private void ifExists(File rootDirectory) {
		if (!rootDirectory.exists()) {
			try {
				rootDirectory.createNewFile();
			} catch (IOException ex) {
				LOGGER.error(ex.getMessage());
			}
		}
	}
}
