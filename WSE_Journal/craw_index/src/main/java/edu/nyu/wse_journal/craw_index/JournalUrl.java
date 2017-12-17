package edu.nyu.wse_journal.craw_index;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
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
