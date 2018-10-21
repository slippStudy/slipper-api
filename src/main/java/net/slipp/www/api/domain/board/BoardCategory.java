package net.slipp.www.api.domain.board;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "slipp_board_category")
public class BoardCategory {

    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String name;

    private String descriptions;
}
