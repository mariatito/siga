<%@ Page language="c#" Codebehind="AjaxExample.aspx.cs" AutoEventWireup="false" Inherits="AjaxTest.WebForm1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" > 

<html>
  <head>
    <title>WebForm1</title>
    <meta name="GENERATOR" Content="Microsoft Visual Studio .NET 7.1">
    <meta name="CODE_LANGUAGE" Content="C#">
    <meta name=vs_defaultClientScript content="JavaScript">
    <meta name=vs_targetSchema content="http://schemas.microsoft.com/intellisense/ie5">
    
    <script language="javascript" src="ajax.js">
    </script>
    
    <script language="javascript">
    
    
    //Called by body.onload to populate the select box with data from the server.
    function InitData(){
			SetInnerHTMLFromAjaxResponse('?button=6','cust_select_div');
    }
    
    //Adds name to the request to the Ajax request.
    //uses the PassAjaxResponseToFunction(url,callbackFunction) function.
    //Note: we could have just written onchange="PassAjaxResponseToFunction("?button=5&name="+this.value, 'FindCustomerCallBack')
    //in the select box(what select box? the one in created in InitData() from the .cs file), but I wanted to show that you can call this from within a function. (you may need more parameters to pass to the url).
    function FindCustomer(){
			var name = document.getElementById("cust_select").value;
			if(name != ""){
				PassAjaxResponseToFunction("?button=5&name="+name, 'FindCustomerCallBack');
			}
    }
    
    //split the response string into its different values and populate the table divs..
    function FindCustomerCallBack(r)
    {
      //we are expecting something like '1;nigel;liefink;27' as a response.
			var s = r.split(';');
			for(var i=0;i<s.length;i++)
			{
			  //populate the data divs.
				document.getElementById("cust_"+i).innerHTML = s[i];	
			}
    }
    
    </script>
    
    
  </head>
  <body MS_POSITIONING="GridLayout" onload="javascript:InitData();">
    <form id="Form1" method="post" runat="server">
    
    <h1>Test the Ajax way.</h1>
    
    <h2>Example 1 - Simple Ajax calls</h2>
    
    <p>These buttons that will fetch dynamic data from the server without the need to re-fetch the whole page as per normal html request/response.</p>
    <p>Interesting to note that the LongAjaxCall button freezes all other ajax calls until its process has finished. If threading were possible in javascript, this could be fixed.<br>
       If anyone can figure out how to stop this behaviour, be sure to let me know.</p>
    <!-- 
    SetInnerHTMLFromAjaxResponse is a javascript function contained in ajax.js.
    -->
    <input type="button" onclick="SetInnerHTMLFromAjaxResponse('?button=1','r1')" value = "GetASPLabel" />
    <input type="button" onclick="SetInnerHTMLFromAjaxResponse('?button=2','r2')" value = "GetDataTable" />
    <input type="button" onclick="SetInnerHTMLFromAjaxResponse('?button=3','r1')" value = "GetDropDownList" />
    <input type="button" onclick="SetInnerHTMLFromAjaxResponse('?button=4','r2')" value = "LongAjaxCall" />
    
    <table border="1" cellpadding="0" cellspacing="0"><tr><td> <div id="r1"></div> </td><td> <div id="r2"></div> </td></tr></table>
   
   <hr/>
   
		<H2>Example 2 - Select box refresh</H2>
		<p>
		Select a customer to look up their details from the database.
		</p>
		
		<!-- this select box is populated from the database and created on the server. 
				 (see the aspx.cs file and the javascript InitData() function in this file.) -->
		<div id="cust_select_div"></div>

		<table border=1 width="50%">
			<tr><td>id</td><td>Name</td><td>Surname</td><td>Age</td></tr>
			<tr>
				<td><div id="cust_0"></div></td>
				<td><div id="cust_1"></div></td>
				<td><div id="cust_2"></div></td>
				<td><div id="cust_3"></div></td>
			</tr>
		</table>
		
		<hr/>
		
		
		<h2>
		Example 3 - Filter on key up event.
		</h2>
		<p>
		Data is continually refreshed from the server on key up event. the result is a rendered datagrid returned from the server.</p>
		Enter a minimum age: <input type="text" size="3" id="min_age" onkeyup="SetInnerHTMLFromAjaxResponse('?button=7&min='+this.value,'cust_dg_div')" /><br/>
		<div id="cust_dg_div"></div>
    </form>
  </body>
</html>
