package models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DBDetails {
    private String name;
    private String address;
    private int age;

    @Override
    public String toString() {
        return "DBDetails{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
