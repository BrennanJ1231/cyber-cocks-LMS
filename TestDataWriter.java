import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDataWriter {

    private ArrayList<String> emptyList;
    private DataWriter dataWriter;

    
    @BeforeClass
    public void oneTimeSetup() {

    }

    @AfterClass
	public static void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public static void setup() {
		//runs before each test
        emptyList = new ArrayList<>();
        dataWriter = new DataWriter();
	}
	
	@AfterEach
	public static void tearDown() {
		//runs after each test
	}


    public void testWriterWithEmptyList() {
        // arrange
        String filePath = "emptyList.txt";

        // act
        dataWriter.writeToFile(filePath, emptyList);

        // assert
        File file = new File(filePath);
        assertTrue(file.exists(), "File was not created.");
        assertTrue(file.length() == 0, "File was not empty.");
    }

    @Test
    public void testWithNonEmptyList() {
        // arrange
        String filePath = "nonEmptyList.txt";
        ArrayList<String> nonEmptyList = new ArrayList<>(Arrays.asList("one", "two", "three"));

        // act
        dataWriter.writeToFile(filePath, nonEmptyList);

        // assert
        File file = new File(filePath);
        assertTrue(file.exists(), "File was not created.");
        assertTrue(file.length() > 0, "File was empty.");
    }

    @Test
    public void testWriterWithNullFilePath() {
        // arrange
        ArrayList<String> nonEmptyList = new ArrayList<>(Arrays.asList("one", "two", "three"));

        // act
        assertThrows(NullPointerException.class, () -> {
            dataWriter.writeToFile(null, nonEmptyList);
        });

        // assert - not necessary, since the exception is expected to be thrown
    }

    @Test
    public void testWriterWithNullList() {
        // arrange
        String filePath = "nullList.txt";

        // act
        assertThrows(NullPointerException.class, () -> {
            dataWriter.writeToFile(filePath, null);
        });

        // assert - not necessary, since the exception is expected to be thrown
    }

    @Test
    public void testWriterWithInvalidFilePath() {
        // arrange
        String filePath = "\0/invalid/path.txt";
        ArrayList<String> nonEmptyList = new ArrayList<>(Arrays.asList("one", "two", "three"));

        // act
        assertThrows(IllegalArgumentException.class, () -> {
            dataWriter.writeToFile(filePath, nonEmptyList);
        });

        // assert - not necessary, since the exception is expected to be thrown
    }
}