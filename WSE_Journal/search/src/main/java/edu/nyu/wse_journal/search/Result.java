package edu.nyu.wse_journal.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {

    String title = "";
    String abs = "";
    String authors = "";
    String keywords = "";

    //String fieldName, String readerStr, String arg0
    String titleHighlighted = "";
    String abstractHighlighted = "";
    String authorsHighlighted = "";
    String keywordsHighlighted = "";

    String journaltitle = "";
    String type = "";
    String fullurl = "";
    String dateTime = "";
    String doi = "";
    String images = "";
}
