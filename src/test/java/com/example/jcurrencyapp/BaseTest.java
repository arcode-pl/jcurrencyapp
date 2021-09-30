package com.example.jcurrencyapp;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
private AutoCloseable closeable;
	
	@BeforeClass
	public void openMocks() {
		closeable = MockitoAnnotations.openMocks(this);
	}

	@AfterClass public void releaseMocks() throws Exception {
        closeable.close();
    }
}
