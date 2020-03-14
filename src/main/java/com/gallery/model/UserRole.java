package com.gallery.model;

import javax.persistence.*;
import java.io.Serializable;



public class UserRole  {
    @Id
    Long user_id;

    @Id
    Long role_id;

}
