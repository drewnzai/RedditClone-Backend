package com.drew.Reddit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
@Data
@Entity
@Table(name = "trending")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrendingItem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToOne
    private Subreddit subreddit;
    @Column(name = "images")
    private String image_src;
    @Column(name = "title")
    private String title;
}
