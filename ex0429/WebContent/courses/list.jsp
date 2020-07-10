<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0429.*, java.util.*, org.json.simple.*" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>강좌목록</title>
   <link rel="stylesheet" href="../home.css">
   <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
   <div id="menu">
      <a href="/ex0429/professors/list.jsp">교수관리</a>
      <a href="/ex0429/students/list.jsp">학생관리</a>
      <a href="/ex0429/courses/list.jsp">강좌관리</a> 	
   </div>
   <h1>[ COURSES LIST ]</h1>
   <table id="tbl" width=700 border=1>
      <tr class="title">
         <td>강좌번호</td>
         <td>강좌명</td>
         <td>강의실</td>
         <td>담당교수</td>
         <td>성적입력</td>
      </tr>
      <%
         CDAO cdao=new CDAO();
         ArrayList<CVO> list=cdao.list("%%");
         for(CVO vo:list){
            out.println("<tr class=row>");
            out.println("<td class=lcode>" + vo.getLcode() + "</td>");
            out.println("<td class=lname>" + vo.getLname() + "</td>");
            out.println("<td>" + vo.getRoom() + "</td>");
            out.println("<td>" + vo.getInstructor() + "</td>");
            out.println("<td><button>성적입력</button></td>");
            out.println("</tr>");
         }
      %>
   </table>
   <div id="enroll">
      <div id="courses">
         강좌번호: <span id="lcode" style="margin-right:50px;"></span>
         강좌명: <span id="lname"></span>
      </div>
      <input type="button" value="선택수정" id=btnSave>
	  <table id="etbl" width=700 border=1></table>
	  <script id="temp" type="text/x-handlebars-template">
		  <tr class=title>
			 <td><input type=checkbox id=chkAll></td>
	 		 <td>학번</td>
     		 <td>학생명</td>
			  <td>점수</td>
			  <td>수강신청일</td>
			  <td>수정</td>
		  </tr>
		  {{#each elist}}
			  <tr class=row>
				  <td><input type=checkbox class=chk></td>
				  <td class=scode>{{scode}}</td>
				  <td class=sname>{{sname}}</td>
				  <td><input size=2 type=text class=grade value={{grade}}></td>
				  <td class=date>{{edate}}</td>
				  <td><button>수정</button></td>
			  </tr>
	 	 {{/each}}
	  </script>
   </div>
</body>
<script>
	var lcode;
	getList();

   $("#tbl").on("click", ".row button", function(){
      var row=$(this).parent().parent();
      lcode=row.find(".lcode").html();
      var lname=row.find(".lname").html();
      $("#lcode").html(lcode);
      $("#lname").html(lname);
      $("#enroll").show();
      getList();
   });
   
	function getList(){
		lcode=$("#lcode").html();
		$.ajax({
			type:"get",
			url:"json.jsp",
			data:{"lcode":lcode},
			dataType:"json",
			success:function(data){
				var temp=Handlebars.compile($("#temp").html());
				$("#etbl").html(temp(data));
			}
		});
	}
	
	// 전체선택 버튼을 클릭했을 때
	$("#etbl").on("click", "#chkAll", function(){
		if($(this).is(":checked")){
			$("#etbl .row .chk").each(function(){
				$(this).prop("checked", true);
			});
		}else{
			$("#etbl .row .chk").each(function(){
				$(this).prop("checked", false);
			});
		}
	});

	// 선택수정 버튼을 클릭했을 때
	$("#btnSave").on("click", function(){
		if(confirm("선택수정하시겠습니까?")==false) return;
		$("#etbl .row .chk:checked").each(function(){
			var row=$(this).parent().parent();
			var scode=row.find(".scode").html();
			var grade=row.find(".grade").val();
			$(this).prop("checked", false);
			$.ajax({
				type:"post",
				url:"update.jsp",
				data:{"scode":scode, "lcode":lcode, "grade":grade},
				success:function(){}
			});
		});
		$("#etbl #chkAll").prop("checked", false);
		alert("수정되었습니다.");
		getList();
	});

	// 수정버튼을 클릭했을때
	$("#etbl").on("click", ".row button", function(){
	   if(confirm("수정하시겠습니까?")==false) return;
	   lcode=$("#lcode").html();
	   var row=$(this).parent().parent();
	   var scode=row.find(".scode").html();
	   var grade=row.find(".grade").val();
	   $.ajax({
	      type:"post",
	      url:"update.jsp",
	      data:{"scode":scode, "lcode":lcode, "grade":grade},
	      success:function(){
	         alert("수정되었습니다.");
	         getList();
	      },
	      error:function(){
	         alert("수정실패했습니다.");
	      }
	   });
	});
</script>
</html>