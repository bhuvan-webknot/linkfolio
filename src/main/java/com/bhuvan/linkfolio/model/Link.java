package com.bhuvan.linkfolio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String link;

    private int iconClickCount=0;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private Page page;
}
