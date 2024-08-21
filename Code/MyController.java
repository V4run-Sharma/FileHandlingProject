class CSVReadThread extends Thread {
    private MyCollection myCollection;

    public CSVReadThread(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public void run() {
        CSVHandler csvHandler = new CSVHandler(myCollection);
        csvHandler.read();
    }
}

class CSVWriteThread extends Thread {
    private MyCollection myCollection;

    public CSVWriteThread(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public void run() {
        CSVHandler csvHandler = new CSVHandler(myCollection);
        csvHandler.write();
    }
}

class JSONReadThread extends Thread {
    private MyCollection myCollection;

    public JSONReadThread(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public void run() {
        JSONHandler jsonHandler = new JSONHandler(myCollection);
        jsonHandler.read();
    }
}

class JSONWriteThread extends Thread {
    private MyCollection myCollection;

    public JSONWriteThread(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public void run() {
        JSONHandler jsonHandler = new JSONHandler(myCollection);
        jsonHandler.write();
    }
}

class XMLReadThread extends Thread {
    private MyCollection myCollection;

    public XMLReadThread(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public void run() {
        XMLHandler xmlHandler = new XMLHandler(myCollection);
        xmlHandler.read();
    }
}

class XMLWriteThread extends Thread {
    private MyCollection myCollection;

    public XMLWriteThread(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public void run() {
        XMLHandler xmlHandler = new XMLHandler(myCollection);
        xmlHandler.write();
    }
}

public class MyController {
    public static void main(String[] args) {
        MyCollection myCollection = new MyCollection();

        CSVReadThread csvReadThread = new CSVReadThread(myCollection);
        JSONReadThread jsonReadThread = new JSONReadThread(myCollection);
        XMLReadThread xmlReadThread = new XMLReadThread(myCollection);

        CSVWriteThread csvWriteThread = new CSVWriteThread(myCollection);
        JSONWriteThread jsonWriteThread = new JSONWriteThread(myCollection);
        XMLWriteThread xmlWriteThread = new XMLWriteThread(myCollection);

        csvReadThread.start();
        try {
            csvReadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        jsonReadThread.start();
        try {
            jsonReadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        xmlReadThread.start();
        try {
            xmlReadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        csvWriteThread.start();
        try {
            csvWriteThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jsonWriteThread.start();
        try {
            jsonWriteThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        xmlWriteThread.start();
        try {
            xmlWriteThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myCollection.setReadCount(0);
        for (int i = 0; i < 300; i++) {
            Employee employee = myCollection.getEmployee();
            System.out.println(i + " " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getDateOfBirth() + " " + employee.getExperience());
        }
    }
}
