package com.bhuvan.linkfolio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pages")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String displayName;

    private String bio;

    private String backgroundType; // "image" or "color"

    private String backgroundImageUrl; // URL to background image

    private String backgroundColor; // Background color

    private int pageViewCount=0;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private List<SocialMedia> buttons;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private List<Link> links;
}
