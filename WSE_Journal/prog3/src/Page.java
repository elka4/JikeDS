//Tianhui Zhu tz406@nyu.edu

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import lombok.*;

@Data
public class Page {
    private double base;
    private double score;
    private double newScore;
    private final int id;
    private static int counter;
    private String path;
    private HashMap<String, Integer> outLinks = new HashMap<String, Integer>();


    public Page(File file) throws IOException {
        JTidyHTMLHandler handler = new JTidyHTMLHandler();
        Element document = handler.getRawDoc(new FileReader(file));
        NodeList anchors = handler.getAnchors(document);
        String content = handler.getText(document).trim();

        base = Math.log(content.split("\\s").length)/Math.log(2);
        id = counter++;
        path = file.getName();

        String url;
        Node parent;
        int score;
        //all html elements for score to increase
        String[] arr = new String[]{"h1", "h2", "h3", "b", "em"};
        Set<String> mySet = new HashSet<>(Arrays.asList(arr));

        for (int i = 0; i < anchors.getLength(); i++) {
            Element e = (Element) anchors.item(i);
            url = e.getAttribute("href");
            score = (outLinks.get(url) == null) ? 1 : outLinks.get(url) + 1;
            parent = e.getParentNode();

            while(parent.getNodeName() != null && !parent.getNodeName().equals("html") ) {
                if (mySet.contains(parent.getNodeName())){
                    score++;
                    break;
                }
                parent = parent.getParentNode();
            }
            outLinks.put(url, score);
        }
    }
}
