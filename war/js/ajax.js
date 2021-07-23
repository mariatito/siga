
function AjaxHttp() {
	xmlHttp = null;
	try{
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		 try{
			 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		 }catch(e){
			 xmlHttp = new XMLHttpRequest();
		 }
	}
	return xmlHttp;
}
