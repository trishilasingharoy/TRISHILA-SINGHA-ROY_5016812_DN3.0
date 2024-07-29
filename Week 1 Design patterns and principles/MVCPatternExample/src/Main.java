class MVCPatternExample {
    public static void main(String[] args) {
        // Create a student
        Student student = new Student("Meghna Sarkar", "98765", "A");

        // Create a view to show student details
        StudentView view = new StudentView();

        // Create a controller
        StudentController controller = new StudentController(student, view);

        // Display initial details
        controller.updateView();

        // Update student details
        controller.setStudentName("Ria Lama");
        controller.setStudentGrade("B");

        // Display updated details
        controller.updateView();
    }
}

// Student.java
class Student {
    private String name;
    private final String id;
    private String grade;

    public Student(String name, String id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

// StudentView.java
class StudentView {
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("Student Details:");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);
        System.out.println("Grade: " + studentGrade);
    }
}

// StudentController.java
class StudentController {
    private final Student model;
    private final StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}