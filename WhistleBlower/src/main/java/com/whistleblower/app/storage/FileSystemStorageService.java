package com.whistleblower.app.storage;

import com.whistleblower.app.exceptionHandling.exeption.CryptoException;
import com.whistleblower.app.util.CryptoUtil;
import com.whistleblower.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Objects;

import static com.whistleblower.app.security.SecurityConstants.CRYPTO_KEY;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;
	private final static String FILE_EXTENSION = ".encrypted";

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file, String tempUserId) {
		File tempFile = null;
		File encrypted = null;

		try {
            Path newLocation = this.rootLocation.resolve(tempUserId);
			Files.createDirectory(newLocation);

		    tempFile = File.createTempFile("saveFile",null);
			tempFile.deleteOnExit();
			file.transferTo(tempFile);
			encrypted = new File(file.getOriginalFilename() + FILE_EXTENSION);
			try {
				Utility.removeEXIF(tempFile, file.getOriginalFilename());
			}catch (Exception e){
				System.out.println("Not an image");
			}

			System.out.println("tempFILE: " + tempFile.toString());
			 CryptoUtil.encrypt(CRYPTO_KEY,tempFile,encrypted);

			Files.move(encrypted.toPath(), newLocation.resolve(Objects.requireNonNull(encrypted.getName())));
		} catch (IOException e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		} catch (CryptoException e) {
			e.printStackTrace();
		}finally {
			tempFile.delete();
		}

	}




	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path path = load(filename + FILE_EXTENSION);
		    File tempFile = File.createTempFile("file",filename.substring(filename.lastIndexOf(".")));
			tempFile.deleteOnExit();

			CryptoUtil.decrypt(CRYPTO_KEY,path.toFile(),tempFile);

			Resource resource = new UrlResource(tempFile.toPath().toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		} catch (IOException | CryptoException e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public void init() {
		try {
			if(!Files.exists(rootLocation)){
				Files.createDirectory(rootLocation);
			}
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
