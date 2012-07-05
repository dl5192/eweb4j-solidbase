package xml2json;

import org.dom4j.DocumentException;
import org.eweb4j.util.JsonConverter;
import org.eweb4j.util.xml.XmlUtil;

public class Xml2JsonAction {

	private String xml = "";

	public String xmlToJson() throws DocumentException {
		return JsonConverter.convert(XmlUtil.Str2Map(xml));
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

}
