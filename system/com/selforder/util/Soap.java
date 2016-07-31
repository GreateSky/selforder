package com.selforder.util;

import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;

public class Soap {
	public static void main(String args[]) {

		try {

			// 创建连接
			// ==================================================
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory
					.newInstance(); 
			SOAPConnection connection = soapConnFactory.createConnection();

			//  创建消息对象
			// ===========================================
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();
//			message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "gb2312");

			// 创建soap消息主体==========================================
			SOAPPart soapPart = message.getSOAPPart();// 创建soap部分
			SOAPEnvelope envelope = soapPart.getEnvelope();//获取soap体
			//envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
			SOAPElement envelope_ = envelope.addChildElement(envelope.createName("Envelope", "soapenv", "http://schemas.xmlsoap.org/soap/envelope/"));
			envelope_.addNamespaceDeclaration("sch", "http://api.jasperwireless.com/ws/schema");
			SOAPElement header_ = envelope_.addChildElement(envelope.createName("soapenv"));
			SOAPHeader header = envelope.getHeader();//获取soap头部分
			SOAPBody body = envelope.getBody();//获取soap体部分
			
			String head = "";
			head += "<wsse:UsernameToken xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"UsernameToken-16847597\">";
			head += "<wsse:Username>gaoqiang</wsse:Username>                                                                                                            ";
			head += "<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">147258</wsse:Password>       ";
			head += "</wsse:UsernameToken>                                                                                                                              ";
			
			SOAPElement securityElement = header.addChildElement(envelope.createName("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"));
			SOAPElement usernameTokenElement = securityElement.addChildElement(envelope.createName("UsernameToken", "wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"));
			usernameTokenElement.addChildElement("Username").addTextNode("gaoqiang");
			usernameTokenElement.addChildElement("Password").addTextNode("147258");
			//  根据要传给mule的参数，创建消息body内容。具体参数的配置可以参照应用集成接口技术规范1.1版本
			// =====================================
			String bodyStr = "";
			bodyStr += "<sch:messageId> </sch:messageId>                                     ";
			bodyStr += "<sch:version>1</sch:version>                                         ";
			bodyStr += "<sch:licenseKey>06d074a4-c884-496a-92b6-aac6efbe6aa0</sch:licenseKey>";
			bodyStr += "<sch:sentToIccid>14571753505</sch:sentToIccid>                       ";
			bodyStr += "<sch:messageText>九阳豆业</sch:messageText>                          ";
			bodyStr += "<sch:tpvp>10</sch:tpvp>                                              ";
			SOAPElement bodyElement = body.addChildElement(envelope.createName("SendSMSRequest"));
			bodyElement.addChildElement("messageId").addTextNode("10000061");
			bodyElement.addChildElement("version").addTextNode("1");
			bodyElement.addChildElement("licenseKey").addTextNode("06d074a4-c884-496a-92b6-aac6efbe6aa0");
			bodyElement.addChildElement("sentToIccid").addTextNode("89860616020006912617");
			bodyElement.addChildElement("messageText").addTextNode("Soap服务端调用");
			bodyElement.addChildElement("tpvp").addTextNode("10");
			// Save the message
			message.saveChanges();
			// 打印客户端发出的soap报文，做验证测试
			System.out.println(" REQUEST: ");
			message.writeTo(System.out);
			System.out.println(" ");
			/*
			 * 实际的消息是使用 call()方法发送的，该方法接收消息本身和目的地作为参数，并返回第二个 SOAPMessage 作为响应。
			 * call方法的message对象为发送的soap报文，url为mule配置的inbound端口地址。
			 */
			URL url = new URL("https://api.10646.cn/ws/service/Sms");
			System.out.println(url);
			// 响应消息
			// ===========================================================================
			SOAPMessage reply = connection.call(message, url);
			//reply.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "gb2312");
			// 打印服务端返回的soap报文供测试
			System.out.println("RESPONSE:");
			// ==================创建soap消息转换对象
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// Extract the content of the reply======================提取消息内容
			Source sourceContent = reply.getSOAPPart().getContent();
			// Set the output for the transformation
			StreamResult result = new StreamResult(System.out);
			transformer.transform(sourceContent, result);
			// Close the connection 关闭连接 ==============
			System.out.println("");
			connection.close();
			/*
			 * 模拟客户端A，异常处理测试
			 */
			SOAPBody ycBody = reply.getSOAPBody();
			Node ycResp = ycBody.getFirstChild();
			System.out.print("returnValue:"+ycResp.getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
