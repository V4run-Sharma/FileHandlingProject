public class MyCollection {
    final private Employee[] employees = new Employee[300];

    private int readCount = 0;
    private int writeCount = 0;

    public int getReadCount() {
        return readCount;
    }
    public int getWriteCount() {
        return writeCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public synchronized void addEmployee(Employee employee) {
        employees[writeCount++] = employee;
    }

    public synchronized Employee getEmployee() {
        return employees[readCount++];
    }

    public synchronized Employee getEmployee(int index) {
        return employees[index];
    }
}
