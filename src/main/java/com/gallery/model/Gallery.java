package com.gallery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String galleryName;

    @ManyToMany(mappedBy = "galleries")
    private List< User > users;

    @OneToMany(mappedBy = "gallery")
    private List<Image> images;
}
