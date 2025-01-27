//import packages and classes
package arrays;
import java.util.*;
import java.io.*;
import java.util.stream.IntStream;


public class University {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Setting the limit to 100 students
        Student[] students = new Student[101];
        // Initialising the array
        initialise(students);

        // Validation for the Main Menu
        int choice = 0;
        while (choice != 9) {
            mainMenu();
            System.out.print("Enter your choice: ");
            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                // Clear invalid input
                input.next();
                continue;
            }

            // Main Menu
            switch (choice) {
                case 1:
                    checkAvailableSeats(students);
                    break;
                case 2:
                    registerStudent(students);
                    break;
                case 3:
                    deleteStudent(students);
                    break;
                case 4:
                    findStudent(students);
                    break;
                case 5:
                    storeStudentDetails(students);
                    break;
                case 6:
                    loadStudentDetails(students);
                    break;
                case 7:
                    viewStudents(students);
                    break;
                case 8:
                    additionalOptions(students);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    //process for initialising students array
    public static void initialise(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            students[i] = null;
        }
    }

    // Main Menu
    public static void mainMenu() {
        System.out.println("");
        System.out.println("---MENU---");
        System.out.println("");
        System.out.println("1. Check available seats");
        System.out.println("2. Register student");
        System.out.println("3. Delete student");
        System.out.println("4. Find student");
        System.out.println("5. Store student details into a file");
        System.out.println("6. Load student details from the file");
        System.out.println("7. View list of students");
        System.out.println("8. Additional Options");
        System.out.println("9. Exit");
        System.out.println("");
    }

    // To count the number of students registered, so available seats can be counted
    public static int countOccupiedSeats(Student[] students) {
        int count = 0;
        for (Student student : students) {
            if (student != null) {
                count++;
            }
        }
        return count;
    }

    // Process for checking the available seats
    public static void checkAvailableSeats(Student[] students) {
        int availableSeats = 100 - countOccupiedSeats(students);
        System.out.println("Available seats: " + availableSeats);
    }

    // Process to register a student
    public static void registerStudent(Student[] students) {
        System.out.println("Seats available:");
        for (int i = 1; i < students.length; i++) {
            if (students[i] == null) {
                System.out.println("Seat " + i);
            }
        }
        // Validation for registering a student
        boolean validSeat = false;
        Scanner input = new Scanner(System.in);

        while (!validSeat) {
            try {
                System.out.print("Enter seat number (1-100) to register student: ");
                int seatNum = input.nextInt();
                if (seatNum < 1 || seatNum >= students.length) {
                    System.out.println("Invalid seat number! Please enter a number between 1 and 100.");
                } else if (students[seatNum] != null) {
                    System.out.println("Seat is already registered! Please choose a different seat.");
                } else {
                    int idNumber = 1234566;
                    String studentID = String.format("w%07d", idNumber + seatNum);
                    students[seatNum] = new Student(studentID);
                    System.out.println("Student registered successfully with ID: " + studentID);
                    validSeat = true; // Registers the student
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid seat number.");
                input.next();
            }
        }
    }

    // Process to delete a student
    public static void deleteStudent(Student[] students) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter seat number (1-100) to delete student: ");
        // Validation for the process
        try {
            int seatNum = input.nextInt();
            if (seatNum < 1 || seatNum >= students.length) {
                System.out.println("Invalid seat number!");
                return;
            }

            if (students[seatNum] != null) {
                System.out.println("Student found in seat " + seatNum + ": ID = " + students[seatNum].getId() + ", Name = " + students[seatNum].getName());
                System.out.print("Please confirm to delete the student (Yes/no) : ");
                String confirmation = input.next().toLowerCase();
                if(confirmation.equals("yes")) {
                    students[seatNum] = null;
                    System.out.println("Student deleted successfully!");
                }else if(confirmation.equals("no")) {
                    System.out.println("Confirmation Declined !");
                }
                else {
                    System.out.println("Invalid Input !");
                }
            } else {
                System.out.println("No student registered in this seat!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please try again.");
            input.next();
        }
    }

    // Process to find a student
    public static void findStudent(Student[] students) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter seat number (1-100) to find student: ");
        // Validation to find the student
        try {
            int seatNum = input.nextInt();
            if (seatNum < 1 || seatNum >= students.length) {
                System.out.println("Invalid seat number!");
                return;
            }

            if (students[seatNum] != null) {
                System.out.println("Student found in seat " + seatNum + ": ID = " + students[seatNum].getId() + ", Name = " + students[seatNum].getName());
            } else {
                System.out.println("No student registered in this seat!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please try again.");
            input.next();
        }
    }

    // Process to store details into a file
    public static void storeStudentDetails(Student[] students) {
        // Validation for the process
        try (PrintWriter writer = new PrintWriter("student_details.txt")) {
            writer.printf("%-12s | %-10s | %-20s | %-17s | %-12s%n", "Seat Number", "Student ID", "Student Name", "Module Marks", "Grade");
            writer.println("--------------------------------------------------------------------------------------------");

            for (int i = 1; i < students.length; i++) {
                if (students[i] != null) {
                    String marks = Arrays.toString(students[i].getModule().getModuleMarks());
                    String grade = students[i].getModule().getGrade();
                    writer.printf("%-12d | %-10s | %-20s | %-17s | %-12s%n", i, students[i].getId(), students[i].getName(), marks, grade);
                }
            }
            System.out.println("Student details stored to file successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Error storing student details to file: " + e.getMessage());
        }
    }

    // Process to load details from a file
    public static void loadStudentDetails(Student[] students) {
        // Validation for the process
        try (BufferedReader reader = new BufferedReader(new FileReader("student_details.txt"))) {
            String line;
            reader.readLine();
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int seatNum = Integer.parseInt(parts[0].trim());
                String id = parts[1].trim();
                String name = parts[2].trim();
                int[] marks = Arrays.stream(parts[3].replaceAll("[\\[\\] ]", "").split(",")).mapToInt(Integer::parseInt).toArray();
                String grade = parts[4].trim();
                students[seatNum] = new Student(id);
                students[seatNum].setName(name);
                students[seatNum].getModule().setModuleMarks(marks);
                students[seatNum].getModule().setGrade(grade);
            }
            System.out.println("Student details loaded from file successfully!");
        } catch (IOException e) {
            System.out.println("Error loading student details from file: " + e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Error : " + e.getMessage());
        }
    }

    // Process to view students
    public static void viewStudents(Student[] students) {
        System.out.println("List of registered students:");
        for (int i = 1; i < students.length; i++) {
            if (students[i] != null) {
                System.out.println("Seat " + i + ": ID = " + students[i].getId() + ", Name = " + students[i].getName());
            }
        }
    }

    // Additional options menu
    public static void additionalOptions(Student[] students) {
        Scanner input = new Scanner(System.in);
        String choice2 = "";
        while (!choice2.equals("e")) {
            mainMenu2();
            System.out.print("Enter your choice: ");
            choice2 = input.next();
            switch (choice2) {
                case "a":
                    addStudentName(students);
                    break;
                case "b":
                    moduleMarks(students);
                    break;
                case "c":
                    studentSummary(students);
                    break;
                case "d":
                    studentReport(students);
                    break;
                case "e":
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    //additional mainMenu
    public static void mainMenu2() {
        System.out.println("");
        System.out.println("---ADDITIONAL MENU---");
        System.out.println("");
        System.out.println("a. Add Student Name");
        System.out.println("b. Module marks 1, 2, and 3");
        System.out.println("c. Student Summary");
        System.out.println("d. Student Report");
        System.out.println("e. Back");
        System.out.println("");
    }

    //process to add students name
    public static void addStudentName(Student[] students) {
        Scanner input = new Scanner(System.in);

        //validation for the process
        System.out.print("Enter seat number (1-100) to find student: ");
        try {
            int seatNum = input.nextInt();
            if (seatNum < 1 || seatNum >= students.length) {
                System.out.println("Invalid seat number!");
                return;
            }

            if (students[seatNum] != null) {
                System.out.println("Student found in seat " + seatNum + ": ID = " + students[seatNum].getId() + ", Name = " + students[seatNum].getName());
                System.out.print("Enter name: ");
                String name = input.next();

                students[seatNum].setName(name);
                System.out.println("Student name updated successfully.");
            } else {
                System.out.println("No student registered in this seat!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please try again.");
            input.next();
        }
    }

    //process to enter module marks
    public static void moduleMarks(Student[] students) {
        Scanner input = new Scanner(System.in);

        //validation for the process
        System.out.print("Enter seat number (1-100) to find student: ");
        try {
            int seatNum = input.nextInt();
            if (seatNum < 1 || seatNum >= students.length) {
                System.out.println("Invalid seat number!");
                return;
            }

            if (students[seatNum] != null) {
                System.out.println("Student found in seat " + seatNum + ": ID = " + students[seatNum].getId() + ", Name = " + students[seatNum].getName());

                int[] marks = new int[3];
                boolean validInput = false;
                while(!validInput) {
                    try {
                        for (int i = 0; i < 3; i++) {
                            while (true) {
                                System.out.print("Enter marks for module " + (i + 1) + ": ");
                                marks[i] = input.nextInt();
                                if (marks[i] >= 0 && marks[i] <= 100) {
                                    break;
                                } else {
                                    System.out.println("Module Marks should be in between 0 and 100");
                                }
                            }
                        }
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input");
                        input.next();
                    }
                }
                students[seatNum].getModule().setModuleMarks(marks);
                System.out.println("Module marks updated successfully");

            } else {
                System.out.println("No student registered in this seat!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please try again.");
            input.next();
        }
    }

    public static void studentSummary(Student[] students) {
        System.out.println("Total Number of student registrations : " + countOccupiedSeats(students));
        System.out.println("-------------------------------------------------------------------------------");

        int totalStudents = countOccupiedSeats(students);

        if (totalStudents == 0) {
            System.out.println("No students data available.");
            return;
        }

        int moduleCount = 0;

        for (Student student : students) {
            if (student != null && student.getModule() != null) {
                moduleCount = student.getModule().getModuleMarks().length;
                break;
            }
        }

        int[] counts = new int[moduleCount];

        for (Student student : students) {
            if (student != null && student.getModule() != null) {
                int[] marks = student.getModule().getModuleMarks();
                if (marks != null) {
                    for (int i = 0; i < moduleCount; i++) {
                        if (marks[i] > 40) {
                            counts[i]++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println("Total number of students who scored more than 40 marks in Module " + (i + 1) + ": " + counts[i]);
        }
    }


    public static void studentReport(Student[] students) {
        // Create an array to store non-null students
        Student[] nonNullStudents = Arrays.stream(students).filter(Objects::nonNull).toArray(Student[]::new);

        // Sort the array based on average marks, highest to lowest
        Arrays.sort(nonNullStudents, (s1, s2) -> Double.compare(s2.getModule().calculateAverage(), s1.getModule().calculateAverage()));

        // Print the header
        System.out.printf("%-10s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s%n", "Student ID", "Student Name", "Module 1", "Module 2", "Module 3", "Total", "Average", "Grade");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        // Print the student details
        for (Student student : nonNullStudents) {
            int total = IntStream.of(student.getModule().getModuleMarks()).sum();
            double average = student.getModule().calculateAverage();
            System.out.printf("%-10s | %-20s | %-10d | %-10d | %-10d | %-10d | %-10.1f | %-10s%n", student.getId(), student.getName(), student.getModule().getModuleMarks()[0], student.getModule().getModuleMarks()[1], student.getModule().getModuleMarks()[2], total, average, student.getModule().getGrade());
        }
    }

}
