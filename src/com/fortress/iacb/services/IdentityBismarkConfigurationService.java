package com.fortress.iacb.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Bismark Atta FRIMPONG
 *
 */
public class IdentityBismarkConfigurationService {

	private Properties properties;

	private static IdentityBismarkConfigurationService instance;

	private IdentityBismarkConfigurationService(String filePathToConfiguration) {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File(filePathToConfiguration)));
		} catch (final IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static IdentityBismarkConfigurationService getInstance() {
		if (instance == null) {
			instance = new IdentityBismarkConfigurationService(System.getProperty("conf"));
		}
		return instance;
	}

	public String getConfigurationValue(String propertyKey) {
		return properties.getProperty(propertyKey);
	}

}
