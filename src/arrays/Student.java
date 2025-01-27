package arrays;

public class Student {
    private arrays.Module module;
    private String id;
    private String name;


    public Student(String id) {
        this.id = id;
        this.module = new arrays.Module();
    }


    public arrays.Module getModule() {
        return module;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}