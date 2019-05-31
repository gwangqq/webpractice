function getXMLHttpRequest(){
	if(window.ActiveXObject){
		try{
			return new ActiveXObject("Msxml2.XMLHTTP");//신버전 
		}catch(e1){
			try{
				return new ActiveXObject("Microsoft.XMLHTTP");//인터넷 익스플로어 7버전 이하
			}catch(e2){
				return null;
			}
		}
	}else if(window.XMLHttpRequest){
		return new XMLHttpRequest();//서버와 통신 할 수 있는 자바 객체
	}else{
		return null;
	}
}

var httpRequest = null;

function sendRequest(url, params, callback, method){
	httpRequest = getXMLHttpRequest();
	
	var httpMethod = method ? method : 'GET';
	if(httpMethod != 'GET' && httpMethod != 'POST'){
		httpMethod = 'GET'; 
	}
	var httpParams = (params == null || params == '') ? null : params; //자바의 null-point-exception 막는 중!
	var httpUrl = url;
	if(httpMethod == 'GET' && httpParams != null){
		httpUrl = httpUrl + "?" + httpParams;//GET방식은 url에 붙여서
	}
	
	
	//alert("method == " + httpMethod + "\turl == " + httpUrl + "\tparam == " + httpParams);
	httpRequest.open(httpMethod, httpUrl, true);//true 비동기, false. 연결하는 것이다
	//이하 세줄이 가장 중요한 요소이다
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	httpRequest.onreadystatechange = callback; //
	//alert(httpMethod == 'POST' ? httpParams : null);
	httpRequest.send(httpMethod == 'POST' ? httpParams : null); //post방식은 httpParams함수 써서
}