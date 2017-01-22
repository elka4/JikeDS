package Brother.XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**SAX 解析的内容处理器 SAX最重要就是这个
 * 相当于监听器
 * 五个事件方法
 * Created by tzh on 1/22/17.
 */
public class PersonHandler extends DefaultHandler {

    private List<Teacher> teachers = null;

    //XML文档开始解析时调用的方法

    @Override
    public void startDocument() throws SAXException {
        teachers = new ArrayList<>();


        //super.startDocument();
    }

    //开始解析元素时
    //uri :标签的命名空间
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
    }

    //结束解析元素时
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    //解析文本内容时调用的方法
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    //XML文档解析完成时调用的方法
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }


}
