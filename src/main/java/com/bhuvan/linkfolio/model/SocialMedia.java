package com.bhuvan.linkfolio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private SocialMediaName name;

    @Column(nullable = false)
    private String link;

    private int iconClickCount=0;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private Page page;
}
