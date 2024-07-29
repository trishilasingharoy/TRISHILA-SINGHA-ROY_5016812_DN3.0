
class Task {
    int taskId;
    String taskName;
    String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Task Name: " + taskName + ", Status: " + status;
    }
}
 class TaskLinkedList {
    private Node head;

    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    // Method to add a task to the linked list
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to search for a task by taskId
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.taskId == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // Method to traverse and print all tasks
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Method to delete a task by taskId
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false;
        }
        if (head.task.taskId == taskId) {
            head = head.next;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.task.taskId == taskId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        // Adding tasks
        taskList.addTask(new Task(1, "Complete yoga", "Pending"));
        taskList.addTask(new Task(2, "Go to appointment", "In Progress"));
        taskList.addTask(new Task(3, "Water plants", "Completed"));

        // Traversing tasks
        System.out.println("All Tasks:");
        taskList.traverseTasks();

        // Searching for a task
        System.out.println("\nSearching for Task ID 2:");
        Task task = taskList.searchTask(2);
        if (task != null) {
            System.out.println("Found: " + task);
        } else {
            System.out.println("Task not found.");
        }

        // Deleting a task
        System.out.println("\nDeleting Task ID 1:");
        boolean isDeleted = taskList.deleteTask(1);
        if (isDeleted) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }

        // Traversing tasks after deletion
        System.out.println("\nAll Tasks after deletion:");
        taskList.traverseTasks();
    }
}

