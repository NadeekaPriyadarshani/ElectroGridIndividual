package com;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/meterreadingAPI")
public class meterreadingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	MeterReading itemObj = new MeterReading();
	
    public meterreadingAPI() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String output = itemObj.insertmeterunit(request.getParameter("UserID"),
				 		request.getParameter("FullName"),
				 		request.getParameter("City"),
				 		request.getParameter("MobileNumber"),
				 		request.getParameter("Unit"),
				 		request.getParameter("Remark"));
					response.getWriter().write(output);
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Map paras = getParasMap(request);
		String output = itemObj.updatemeterunit(paras.get("hiduseridSave").toString(),
		paras.get("UserID").toString(),
		paras.get("FullName").toString(),
		paras.get("City").toString(),
		paras.get("MobileNumber").toString(),
		paras.get("Unit").toString(),
		paras.get("Remark").toString());
		response.getWriter().write(output);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		 String output = itemObj.deletemeterunit(paras.get("id").toString());
		response.getWriter().write(output);
	}
	
    // Convert request parameters to a Map
    private static Map getParasMap(HttpServletRequest request)
    {
    	Map<String, String> map = new HashMap<String, String>();
    	try
    	{
    		Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
    		String queryString = scanner.hasNext() ?
    				scanner.useDelimiter("\\A").next() : "";
    		scanner.close();
    		String[] params = queryString.split("&");
    		for (String param : params)
    		{ 
    			String[] p = param.split("=");
    			map.put(p[0], p[1]);
    		}
    	}
    	catch (Exception e)
    	{
    	}
    	return map;
    }
	

}
