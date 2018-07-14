public class TeacherVO {
    private Long id;
    private String name;
    private Integer age;
    private String sol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSol() {
        return sol;
    }

    public void setSol(String sol) {
        this.sol = sol;
    }

    @Override
    public String toString() {
        return "TeacherVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sol='" + sol + '\'' +
                '}';
    }
}
