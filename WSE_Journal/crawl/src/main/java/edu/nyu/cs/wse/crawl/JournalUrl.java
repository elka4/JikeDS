package edu.nyu.cs.wse.crawl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

//@Component
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
