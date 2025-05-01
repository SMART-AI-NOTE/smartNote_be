package com.smartnote.backend;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class AiRequestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "note_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_ai_log_note")
    )
    private Note note;

    @Column(nullable = false, length = 20)
    private String type;

    @Lob
    @Column(name = "request_text", columnDefinition = "LONGTEXT")
    private String requestText;

    @Lob
    @Column(name = "result_text", columnDefinition = "LONGTEXT")
    private String resultText;

    @Column(name = "create_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt;
}
