package Brother.XML;

import implementation.list.DoublyLinkedList;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tzh on 1/22/17.
 */
public class DomParseDemo {



    @Test
    public void test01() throws Exception {
        //创建dom解析器工厂
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //通过解析器工厂创建解析器
        DocumentBuilder builder = dbf.newDocumentBuilder();
        //构造一个指向指定文件的输入流
        InputStream is = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("Brother/XML/person.xml");
        //开始解析,并返回Document对象
        Document doc = builder.parse(is);

        List<Teacher> teachers = new ArrayList<>();

        //根据文档的节点获取数据
        NodeList nodeList = doc.getElementsByTagName("teacher");
        Teacher t = null;
        for(int i = 0; i < nodeList.getLength(); i++) {
            t = new Teacher();
            Node teacherNode = nodeList.item(i);
            //获取当前节点的属性id的值
            String id = teacherNode.getAttributes().getNamedItem("id").getNodeValue();
            System.out.println(id);
            t.setId(Integer.parseInt(id));

            //获取当前节点下的所有子节点列表
            NodeList childNodeList = teacherNode.getChildNodes();
            for (int j = 0; j < childNodeList.getLength(); j++) {
                Node childNode = childNodeList.item(j);
                //要判断是什么属性
                String tagName = childNode.getNodeName();
                if("name".equals(tagName)){
                    //获取teacher节点下的其中一个子节点
                    t.setName(childNode.getFirstChild().getNodeValue());
                } else if ("desc".equals(tagName)){
                    t.setName(childNode.getFirstChild().getNodeValue());
                }

            }
            teachers.add(t);
            System.out.println(t);


        }
        System.out.println(teachers);
        //builder.parse("Brother/XML/person.xml");
        //db.parse("Brother/XML/person.xml");
        //NodeList node_person = doc.get


    }
}
