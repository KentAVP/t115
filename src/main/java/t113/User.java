package t113;

import javax.persistence.*;

@Entity
@Table(name="uers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "age")
    protected int age;

    @Column(name = "role")
    protected String role;

    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;


    public User() {
    }


    public User(String name, int age, String role) {
        super();
        this.name = name;
        this.age = age;
        this.role=role;
    }
    public User(String name, int age, String role,String username,String password) {
        super();
        this.name = name;
        this.age = age;
        this.role=role;
        this.username=username;
        this.password=password;
    }

    public User(int id, String name, int age, String role) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.role=role;
    }
    public User(String username,String password) {
        super();
        this.username=username;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
