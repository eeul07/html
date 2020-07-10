/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.5
 * Generated at: 2020-04-28 07:33:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.courses;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import ex0428.*;
import java.util.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<title>강좌목록</title>\r\n");
      out.write("\t<script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\r\n");
      out.write("\t<script src=\"https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h1>[ COURSES LIST ]</h1>\r\n");
      out.write("\t<form action=\"list.jsp\">\r\n");
      out.write("\t\t<select name=\"type\">\r\n");
      out.write("\t\t\t<option value=\"lname\">강좌명</option>\r\n");
      out.write("\t\t\t<option value=\"room\">강의실</option>\r\n");
      out.write("\t\t\t<option value=\"pname\">담당교수명</option>\r\n");
      out.write("\t\t\t<option value=\"dept\">교수학과</option>\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t<input type=\"text\" name=\"word\">\r\n");
      out.write("\t\t<input type=\"submit\" value=\"검색\">\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<table id=\"tbl\" width=700 border=1>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>강좌번호</td>\r\n");
      out.write("\t\t\t<td>강좌명</td>\r\n");
      out.write("\t\t\t<td>강의실</td>\r\n");
      out.write("\t\t\t<td>담당교수명</td>\r\n");
      out.write("\t\t\t<td>교수학과</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t");

			String type=request.getParameter("type")==null?"lcode"
				:request.getParameter("type"); // type이 null이면 lcode, 아니면 type값을 출력
			String word=request.getParameter("word")==null?""
				:request.getParameter("word"); // word가 null이면 공백, 아니면 word값을 출력
			
			EDAO dao=new EDAO();
			ArrayList<CVO> list=dao.clist(type, word);
			for(CVO vo:list){
				out.print("<tr class=row>");
				out.print("<td class=lcode>" + vo.getLcode() + "</td>");
				out.print("<td class=lname>" + vo.getLname() + "</td>");
				out.print("<td>" + vo.getRoom() + "</td>");
				out.print("<td>" + vo.getPname() + "</td>");
				out.print("<td>" + vo.getDept() + "</td>");
				out.print("</tr>");
			}
		
      out.write("\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<div id=enroll>\r\n");
      out.write("\t\t<h1><span id=\"lname\"></span><span id=\"total\"></span></h1>\r\n");
      out.write("\t\t<table id=\"tbl1\" width=700 border=1></table>\r\n");
      out.write("\t\t<script id=\"temp\" type=\"text/x-handlebars-template\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>학번</td>\r\n");
      out.write("\t\t\t\t<td>학생명</td>\r\n");
      out.write("\t\t\t\t<td>강좌번호</td>\r\n");
      out.write("\t\t\t\t<td>강좌명</td>\r\n");
      out.write("\t\t\t\t<td>수강신청일</td>\r\n");
      out.write("\t\t\t\t<td>성적</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t{{#each items}}\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>{{scode}}</td>\r\n");
      out.write("\t\t\t\t\t<td>{{sname}}</td>\r\n");
      out.write("\t\t\t\t\t<td>{{lcode}}</td>\r\n");
      out.write("\t\t\t\t\t<td>{{lname}}</td>\r\n");
      out.write("\t\t\t\t\t<td>{{edate}}</td>\r\n");
      out.write("\t\t\t\t\t<td>{{grade}}</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t{{/each}}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("\t$(\"#tbl\").on(\"click\", \".row\", function(){\r\n");
      out.write("\t\tvar lname=$(this).find(\".lname\").html();\r\n");
      out.write("\t\t$(\"#lname\").html(lname + \" 수강신청한 학생목록\");\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar lcode=$(this).find(\".lcode\").html();\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype:\"get\",\r\n");
      out.write("\t\t\turl:\"enroll.jsp\",\r\n");
      out.write("\t\t\tdataType:\"json\",\r\n");
      out.write("\t\t\tdata:{\"lcode\":lcode},\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t$(\"#total\").html(data.total);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tvar temp=Handlebars.compile($(\"#temp\").html());\r\n");
      out.write("\t\t\t\t$(\"#tbl1\").html(temp(data));\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}