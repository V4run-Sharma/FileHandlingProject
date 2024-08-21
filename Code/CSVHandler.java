import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVHandler implements FileHandler {
    private final MyCollection myCollection;

    public CSVHandler(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    public void read() {
        String csvFile = "/Users/varunsharma/Documents/FileHandlingProject/Files/MOCK_DATA.csv";
        String line;
        String csvSplitBy = ",";
        int lineNumber = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (lineNumber < 2 || lineNumber > 101) {
                    continue;
                }

                String[] employeeData = line.split(csvSplitBy);
                Employee employee = new Employee();

                employee.setFirstName(employeeData[0]);

                employee.setLastName(employeeData[1]);

                Date dob = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    dob = sdf.parse(employeeData[2]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                employee.setDateOfBirth(dob);

                employee.setExperience(Double.parseDouble(employeeData[3]));

                myCollection.addEmployee(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        String outputCsvFile = "/Users/varunsharma/Documents/FileHandlingProject/Files/OUTPUT_DATA.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputCsvFile))) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < 100; i++) {
                Employee employee = myCollection.getEmployee();
                if (employee != null) {
                    String line = employee.getFirstName() + "," + employee.getLastName() + "," + sdf.format(employee.getDateOfBirth()) + "," + employee.getExperience();
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
