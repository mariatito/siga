using System;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using System.Drawing;
using System.IO;
using System.Text;
using System.Threading;
using System.Web;
using System.Web.SessionState;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.HtmlControls;

namespace AjaxTest
{
	/// <summary>
	/// Summary description for WebForm1.
	/// </summary>
	public class WebForm1 : System.Web.UI.Page
	{
		private void Page_Load(object sender, System.EventArgs e)
		{
			HandleAjaxEvent();



		}


		/// <summary>
		/// Checks the query string to see if the request is an ajax event.
		/// If it is will return only the spcific data needed and then call Response.End() so as not to render the rest of the page in the response.
		/// </summary>
		private void HandleAjaxEvent()
		{
			string bNum = Request.QueryString["button"];
			if(bNum != null && bNum.Length > 0)
			{
				switch (bNum)
				{
					case "1": //Example 1 - GetLabel
						//demonstrates the use of the GetHtml() function.
						//if you wanted to use controls that are created on the aspx page, you can set their visibility to false initially.
						//then temporarily set visibility to true, GetHtml() and then set the visibility back to false after returning the response.
						Label l =  new Label();
						l.Text = "Here is some Text Only";
						l.BackColor = Color.Honeydew;
						l.BorderWidth = 6;
						l.BorderStyle = BorderStyle.Groove;
						l.BorderColor = Color.Goldenrod;
						Response.Write(GetHtml(l));
						Response.End();
						break;
				
					case "2": //Exmplae 1 - GetTable
						//Just return some dynamic html.
						//Trying to create a repeater in code really is a pain. this is so much easier..
						StringBuilder sb = new StringBuilder();
						sb.Append("<table border=3><tr><td colspan=2><H1>My Strings</H1></td></tr>");//Header
						string it = "<tr bgcolor={2}><td>{0}</td><td>{1}</td></tr>"; //Item Template.
						string[] col = {"Hello","There","Something","Else"};
						string[] col2 = {"Hello2","There2","Something2","Else2"};
						string bgcol;
						for(int i=0;i<col.Length;i++)
						{
							bgcol = (i%2 == 0)?"cyan":"lightblue";
							sb.Append(String.Format(it, col[i], col2[i], bgcol));
						}
						sb.Append("</table>");//Footer.
						Response.Write(sb.ToString());
						Response.End();
						break;
					
					case "3": //Example1 GetDropDownList
						//Bind data to a DropDownList and return the Html.
						ArrayList al = new ArrayList();
						for(int i=0;i<20;i++)
						{
							al.Add("Select "+i);
						}
						DropDownList dl = new DropDownList();
						dl.DataSource = al;
						dl.DataBind();
						Response.Write(GetHtml(dl));
						Response.End();
						break;
				
					case "4": //Example 1
						//LongAjaxCall , just sleep and return a string.
						Thread.Sleep(5000);
						Response.Write("Finally! Got something.");
						Response.End();
						break;


					case "5": //Example 2 - Get Customer Details from select box / name
						//Call a method to get the details for the response.
						string name=Request.QueryString["name"];
						Response.Write(GetCustomerDetailsByName(name));
						Response.End();
						break;

					case "6": //Example 2 - InitData - create the select box
						//Bind data to a drop down list and add a javascript event to it to call the Ajax method.
						dl = new DropDownList();
						dl.DataSource = names;
						dl.DataBind();
						dl.AutoPostBack = false;
						dl.Attributes.Add("onchange","FindCustomer();");//javascript method
						dl.Attributes.Add("id","cust_select");
						Response.Write(GetHtml(dl));
						Response.End();
						break;

					case "7": //Example 3
						//create a data grid using the age filter and return the html.
						DataGrid dg = new DataGrid();
						dg.AutoGenerateColumns = true;

						string min_age=Request.QueryString["min"];
						DataTable dt = CreateCustomersDataTable();
						DataView dv = new DataView(dt);
						dv.RowFilter="Age > "+min_age;
						dg.DataSource = dv;
						dg.DataBind();
						Response.Write(GetHtml(dg));
						Response.End();
						break;

					default: 
						throw new ArgumentException("No case for button '"+bNum+"' has been defined in HandleAjaxEvent().");
						//break;
				}
			}
		}


		//dummy database 
		string[] names = {"Nigel", "Sally", "Naomi", "Lucy", "Jarrad", "Daydyn", "Lisa"};
		string[] surnames = {"Liefrink","Sunami","Black","Blucie","Trudgen","Liefrink","Lee"};
		int[] ages = {27,23,30,32,26,10,23};


		/// <summary>
		/// Creates a table from the dummy database.
		/// </summary>
		/// <returns></returns>
		private DataTable CreateCustomersDataTable()
		{
			DataTable dt = new DataTable("customers");

			dt.Columns.Add(new DataColumn("Id"));
			dt.Columns.Add(new DataColumn("Name"));
			dt.Columns.Add(new DataColumn("Surname"));
			dt.Columns.Add(new DataColumn("Age"));

			for(int i=0;i<names.Length;i++)
			{
				object[] values = {i,names[i],surnames[i],ages[i]};
				dt.Rows.Add(values);
			}
			return dt;
		}


		/// <summary>
		/// Returns the Ajax customer details string to return to the client.
		/// </summary>
		/// <param name="name"></param>
		/// <returns></returns>
		public string GetCustomerDetailsByName(string name)
		{
			//Just pretend to get a resultset from the database..
			//going to use the arrays as db.
			int index = Array.IndexOf(names,name);
			if(index >=0)
			{
				return ""+index+";"+names[index]+";"+surnames[index]+";"+ages[index];
			} else {
				StringBuilder sb = new StringBuilder();
				foreach(string n in names)
				{
					sb.Append(n+",");
				}
				sb.Length--;
				return "Could not find the name in the database try "+sb.ToString();
			}
		}


		/// <summary>
		/// Helper to get a html string representation of the passed Control.
		/// </summary>
		/// <param name="c">Control to return Html for</param>
		/// <returns>Html of control</returns>
		private string GetHtml(Control c)
		{
			StringBuilder sb = new StringBuilder();
			HtmlTextWriter tw = new HtmlTextWriter(new StringWriter(sb));
			try 
			{
				c.RenderControl(tw);
			} 
			finally 
			{
				tw.Close();
			}
			return sb.ToString();
		}


		#region Web Form Designer generated code
		override protected void OnInit(EventArgs e)
		{
			//
			// CODEGEN: This call is required by the ASP.NET Web Form Designer.
			//
			InitializeComponent();
			base.OnInit(e);
		}
		
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{    
			this.Load += new System.EventHandler(this.Page_Load);
		}
		#endregion
	}
}
