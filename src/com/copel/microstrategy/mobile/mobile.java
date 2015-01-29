package com.copel.microstrategy.mobile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class mobile
 */
public class mobile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String device = "";
		String dtDevice = "";

		Enumeration en = request.getParameterNames();
        
		// Verifica se foi forçado a verificar os elementos de um determinado dispositivo via parâmetro
        while (en.hasMoreElements()) {
            String paramName = (String) en.nextElement();
            if(paramName.equalsIgnoreCase("decode")){
        		request.setAttribute("decode", "true");
            } else if(paramName.equalsIgnoreCase("iphone")){
    			device = "iPhone";
    			dtDevice = "1";
            } else if(paramName.equalsIgnoreCase("ipod")){
    			device = "iPhone";
    			dtDevice = "1";
            } else if(paramName.equalsIgnoreCase("ipad")){
    			device = "iPad";
    			dtDevice = "2";
            } else if(paramName.equalsIgnoreCase("androidm")){
    			device = "Android";
    			dtDevice = "3";
            } if(paramName.equalsIgnoreCase("android")){
    			device = "Tablet Android";
    			dtDevice = "4";
            }
        }
		
        if(device.equals("")){
			String agent = request.getHeader("User-Agent");
			if (agent.contains("iPhone")) {
				device = "iPhone";
				dtDevice = "1";
			} else if (agent.contains("iPod")) {
				device = "iPhone";
				dtDevice = "1";
			} else if (agent.contains("iPad")) {
				device = "iPad";
				dtDevice = "2";
			} else if (agent.contains("Android")) {
				if(agent.contains("Mobile")){
					device = "Android";
					dtDevice = "3";
				} else {
					device = "Tablet Android";
					dtDevice = "4";
				}
			}
        }

		// realizando a leitura do xml

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File(Messages.getString("mobile.xmlfile")));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<String> listN = new ArrayList<String>();
		ArrayList<String> listCid = new ArrayList<String>();

		NodeList nodeList = document.getElementsByTagName("cnf");
		for (int x = 0, size = nodeList.getLength(); x < size; x++) {
			
			String dt = nodeList.item(x).getAttributes().getNamedItem("dt").getNodeValue();
			String n = nodeList.item(x).getAttributes().getNamedItem("n").getNodeValue();
			String cid = nodeList.item(x).getAttributes().getNamedItem("cid").getNodeValue();
			
			if (dt.equals(dtDevice) && !n.startsWith("*")) {
				listN.add(n);
				listCid.add(cid);
			} 
		}

		
		String protocolo = "mstr";
		if(device.equalsIgnoreCase("ipad")){
			protocolo = "mstripad";
		}
		
		request.setAttribute("device", device);
		request.setAttribute("protocolo", protocolo);
		request.setAttribute("servername", request.getServerName());
		request.setAttribute("listN", listN);
		request.setAttribute("listCid", listCid);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/mobile.jsp");
		dispatcher.forward(request, response);
	}

}
