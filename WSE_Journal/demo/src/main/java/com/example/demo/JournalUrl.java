package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class JournalUrl {
    private String name = "";
    private String link = "";
    private String flpre = "";
    private String flpost = "";
    private String secpre = "";
    private String secpost = "";
    private int level = 1;
}
