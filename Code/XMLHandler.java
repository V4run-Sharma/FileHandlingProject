import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XMLHandler implements FileHandler {
    private MyCollection myCollection;

    public XMLHandler(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    public void read() {
        try {
            File file = new File("/Users/varunsharma/Documents/FileHandlingProject/Files/dataset.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("record");

            for (int i = 0; i < 100; i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Employee employee = new Employee();
                    employee.setFirstName(getTagValue("firstName", element));
                    employee.setLastName(getTagValue("lastName", element));
                    Date dob = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    dob = sdf.parse(getTagValue("dateOfBirth", element));
                    employee.setDateOfBirth(dob);
                    employee.setExperience(Double.parseDouble(getTagValue("experience", element)));
                    myCollection.addEmployee(employee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0).getChildNodes().item(0);
            if (node != null) {
                return node.getNodeValue();
            }
        }
        return "";
    }

    public void write() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element rootElement = doc.createElement("employees");
            doc.appendChild(rootElement);

            for (int i = 200; i < 300; i++) {
                Employee employee = myCollection.getEmployee(i);
                if (employee != null) {
                    Element employeeElement = doc.createElement("employee");
                    rootElement.appendChild(employeeElement);

                    Element firstName = doc.createElement("firstName");
                    firstName.appendChild(doc.createTextNode(employee.getFirstName()));
                    employeeElement.appendChild(firstName);

                    Element lastName = doc.createElement("lastName");
                    lastName.appendChild(doc.createTextNode(employee.getLastName()));
                    employeeElement.appendChild(lastName);

                    Element dateOfBirth = doc.createElement("dateOfBirth");
                    dateOfBirth.appendChild(doc.createTextNode(sdf.format(employee.getDateOfBirth())));
                    employeeElement.appendChild(dateOfBirth);

                    Element experience = doc.createElement("experience");
                    experience.appendChild(doc.createTextNode(String.valueOf(employee.getExperience())));
                    employeeElement.appendChild(experience);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("/Users/varunsharma/Documents/FileHandlingProject/Files/OUTPUT_DATA.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}