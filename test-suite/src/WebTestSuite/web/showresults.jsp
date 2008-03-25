<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="sbml.test.sbmlTestselection" %>
<%@ page import="sbml.test.TestResultDetails" %>
<%@ page import="sbml.test.sbmlTestcase" %>
<%@ page import="java.io.*" %>
	
	<%
	Vector results = (Vector)request.getAttribute("tests");
	%>
	<%! 	TestResultDetails test;
		String name;
		String plot;
		String description;
		String warnings;
		int result;
		int fail_count;
		int abort_count;
		
	%>

	<HTML>
	<BODY>
	
	<CENTER><BIG>SBML Test Suite Results</BIG></CENTER>
	<TABLE BORDER="0" CELLSPACING="0" WIDTH="80%" ALIGN="center">
	<TR>
	<%-- For each test in the test vector - get the testname, description, plot path, result --%>
		<%-- implement a counter and when counter mod 30 = 0 start a new row --%>
		
	<%
		fail_count=0;
		abort_count=0;
		for(int i=0;i<results.size() ; i++) {	
		  if(i % 30 == 0) {
			// start a new row
			out.println("</TR>");
			out.println("<TR>");
		  }
		
			test = (TestResultDetails)results.elementAt(i);
			name = test.getTestname();
			plot = test.getPlot();
			description = test.getDescription();
			result = test.getResult();
			warnings = test.getWarnings();
		
		
			if(result>0) {			
		
				out.println("<TD>");
				out.println("<a href=\"/test_suite/web/testdetails.jsp?testname=" + name +"&result=" + result + "&plot=" + plot + "&description=" +description  + "&warnings=" + warnings +"\" target=\"_blank\">");
				out.println("<IMG SRC=\"/test_suite/web/images/red.jpg\"  border=\"0\"/>");
				out.println("</a>");
				out.println("</TD>");
				fail_count++;
			}	
			if(result == 0) {			
		
				out.println("<TD>");
				out.println("<a href=\"/test_suite/web/testdetails.jsp?testname=" + name +"&result=" + result + "&plot=" + plot + "&description=" +description + "&warnings=" + warnings +"\" target=\"_blank\">");
				out.println("<IMG SRC=\"/test_suite/web/images/green.jpg\" border=\"0\"/>");
				out.println("</a>");
				out.println("</TD>");
			}
			if(result == -1) {			
		
				out.println("<TD>");
				out.println("<a href=\"/test_suite/web/testdetails.jsp?testname=" + name +"&result=" + result + "&plot=" + plot + "&description=" +description + "&warnings=" + warnings +"\" target=\"_blank\">");
				out.println("<IMG SRC=\"/test_suite/web/images/red.jpg\" border=\"0\"/>");
				out.println("</a>");
				out.println("</TD>");
				abort_count++;
			}		
	     } // end of for loop
	     // if size of vector is not equally dividable by 30  - fill in in the remaining table with grey squares
	     
	     for(int m = results.size()%30; m<31; m++) {
				
			out.println("<TD>");
			out.println("<IMG SRC=\"/test_suite/web/images/grey.jpg\" border=\"0\"/>");
			out.println("</TD>");
	     }	
	%>	
	</TR>
	</TABLE>
	<BR>
	<BR>
	Number of tests taken  : <%=results.size()%><BR>
	Number of tests failed : <%=fail_count%><BR>
	Number of tests aborted: <%=abort_count%>
	</BODY>
	</HTML>
