//Tianhui Zhu tz406@nyu.edu
/**
 * This JTidyHTMLHandler is a HTML parser modified based on Program 1.
 */

import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import org.w3c.dom.*;

import org.w3c.tidy.Tidy;

import java.io.*;


@SuppressWarnings("all")
public class JTidyHTMLHandler {

    /**
     * Given a InputStream and return a org.apache.lucene.document.Document
     * @param is InputStream
     * @return org.apache.lucene.document.Document
     */
    public org.apache.lucene.document.Document getDocument(InputStream is) {

        Tidy tidy = new Tidy();
        tidy.setQuiet(true);
        tidy.setShowWarnings(false);
        org.w3c.dom.Document root = tidy.parseDOM(is, null);
        Element rawDoc = root.getDocumentElement();

        org.apache.lucene.document.Document doc =
                new org.apache.lucene.document.Document();

        String title = getTitle(rawDoc);
        String body = getBody(rawDoc);

        //String path;
        if ((title != null) && (!title.equals(""))) {
            doc.add(new TextField("title", title, Field.Store.YES));
        }
        if ((body != null) && (!body.equals(""))) {
            doc.add(new TextField("contents", body, Field.Store.YES));
        }
        //doc.add(new TextField("path", path, Field.Store.YES));

        return doc;
    }

    /**
     * Gets the title text of the HTML document.
     *
     * @rawDoc the DOM Element to extract title Node from
     * @return the title text
     */
    protected String getTitle(Element rawDoc) {
        if (rawDoc == null) {
            return null;
        }

        String title = "";

        NodeList children = rawDoc.getElementsByTagName("title");
        if (children.getLength() > 0) {
            Element titleElement = ((Element) children.item(0));
            Text text = (Text) titleElement.getFirstChild();
            if (text != null) {
                title = text.getData();
            }
        }
        return title;
    }

    /**
     * Gets the body text of the HTML document.
     *
     * @rawDoc the DOM Element to extract body Node from
     * @return the body text
     */
    protected String getBody(Element rawDoc) {
        if (rawDoc == null) {
            return null;
        }

        String body = "";
        NodeList children = rawDoc.getElementsByTagName("body");
        if (children.getLength() > 0) {
            body = getText(children.item(0));
        }
        return body;
    }

    /**
     * Extracts text from the DOM node.
     *
     * @param node a DOM node
     * @return the text value of the node
     */
    protected String getText(Node node) {
        NodeList children = node.getChildNodes();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            switch (child.getNodeType()) {
                case Node.ELEMENT_NODE:
                    sb.append(getText(child));
                    sb.append(" ");
                    break;
                case Node.TEXT_NODE:
                    sb.append(((Text) child).getData());
                    break;
            }
        }
        return sb.toString();
    }

/////////////////////////////////////////////////////////////////////////////////
    public NodeList getAnchors(Element rawDoc) {
        if (rawDoc == null) {
            return null;
        }
        return rawDoc.getElementsByTagName("a");
    }


    public Element getRawDoc(Reader page)  {
        Tidy tidy = new Tidy();
        tidy.setQuiet(true);
        tidy.setShowWarnings(false);
        Document root = tidy.parseDOM(page, null);
        return root.getDocumentElement();
    }


/////////////////////////////////////////////////////////////////////////////////




}
