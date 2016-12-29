import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;


public class Test {

	public static void main(String[] args) {
		String xmlString = "<root><people>ACELY</people></root>";
		
		try {
			
			
			
			Document document = DocumentHelper.parseText(xmlString);
			
			System.out.println(document.asXML());
			
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
