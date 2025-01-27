package arrays;

public class Module {

    private int[] moduleMarks;
    private String grade;


    public Module() {

        this.moduleMarks = new int[3]; // Assuming 3 modules
        this.grade = "Fail";

    }

    public int[] getModuleMarks() {
        return moduleMarks;
    }

    public void setModuleMarks(int[] marks) {
        if (marks.length == 3) {
            boolean validMark = true;
            for (int mark : marks) {
                if (mark < 0 || mark > 100) {
                    validMark = false;
                    System.out.println("Marks should be between 0 and 100");
                    break;
                }
            }

            if (validMark) {
                this.moduleMarks = marks;
                calculateGrade();
            } else {
                System.out.println("Error: Exactly 3 module marks are required.");
            }
        }
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = this.grade;
    }



    private void calculateGrade() {
        double average = calculateAverage();
        if (average >= 80) {
            this.grade = "Distinction";
        } else if (average >= 70) {
            this.grade = "Merit";
        } else if (average >= 40) {
            this.grade = "Pass";
        } else {
            this.grade = "Fail";
        }
    }

    double calculateAverage() {
        int sum = 0;
        for (int mark : moduleMarks) {
            sum += mark;
        }
        return sum / 3.0;
    }

}
