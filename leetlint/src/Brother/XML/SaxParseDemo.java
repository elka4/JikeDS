package Brother.XML;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

/**读的时候就开始处理
 * SAX API 主要有四种处理事件的接口
 *以事件的方式，需要监听器
 * Created by tzh on 1/22/17.
 */
public class SaxParseDemo {

    @Test
    public void test01() throws Exception, SAXException {
        //创建SAX解析器工厂对
        SAXParserFactory spf = SAXParserFactory.newInstance();

        //使用解析器工厂创建解析器实例
        SAXParser saxParser = spf.newSAXParser();

        InputStream is = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("Brother/XML/person.xml");

        //创建SAX解析器要使用的时间监听器对象
       //PersonHandler handler = new PersonHandler;

        //开始解析
        PersonHandler dh = new PersonHandler();
        saxParser.parse(is, dh);
    }


}
