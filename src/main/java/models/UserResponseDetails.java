package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDetails {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
