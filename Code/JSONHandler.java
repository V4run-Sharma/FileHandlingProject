import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONHandler implements FileHandler {
    private MyCollection myCollection;

    public JSONHandler(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    public void read() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat(dateFormat);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            Employee[] employees = objectMapper.readValue(new File("/Users/varunsharma/Documents/FileHandlingProject/Files/MOCK_DATA.json"), Employee[].class);
            for (int i = 0; i < 100; i++) {
                myCollection.addEmployee(employees[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat(dateFormat);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            List<Employee> employeesToWrite = new ArrayList<>();
            for (int i = 100; i < 200; i++) {
                employeesToWrite.add(myCollection.getEmployee(i));
                myCollection.setReadCount(myCollection.getReadCount() + 1);
            }
            objectMapper.writeValue(new File("/Users/varunsharma/Documents/FileHandlingProject/Files/OUTPUT_DATA.json"), employeesToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
