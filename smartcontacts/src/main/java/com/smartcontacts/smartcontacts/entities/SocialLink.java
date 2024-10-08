package com.smartcontacts.smartcontacts.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SocialLink {

    @Id
    private long id;
    private String link;
    private String title;

    // @ManyToOne
    // private Contact contact;

}
