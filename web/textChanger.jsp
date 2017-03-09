<%-- 
    Document   : textChanger
    Created on : 09-Mar-2017, 12:57:31
    Author     : daniellewilliams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <script type="text/javascript" src="/js/jquery-1.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {

	//min font size
	var min=9; 	

	//max font size
	var max=16;	
	
	//grab the default font size
	var reset = $('p').css('fontSize'); 
	
	//font resize these elements
	var elm = $('p.intro, p.ending');  
	
	//set the default font size and remove px from the value
	var size = str_replace(reset, 'px', ''); 
	
	//Increase font size
	$('a.fontSizePlus').click(function() {
		
		//if the font size is lower or equal than the max value
		if (size<=max) {
			
			//increase the size
			size++;
			
			//set the font size
			elm.css({'fontSize' : size});
		}
		
		//cancel a click event
		return false;	
		
	});

	$('a.fontSizeMinus').click(function() {

		//if the font size is greater or equal than min value
		if (size>=min) {
			
			//decrease the size
			size--;
			
			//set the font size
			elm.css({'fontSize' : size});
		}
		
		//cancel a click event
		return false;	
		
	});
	
	//Reset the font size
	$('a.fontReset').click(function () {
		
		//set the default font size	
		 elm.css({'fontSize' : reset});		
	});
		
});

//A string replace function
function str_replace(haystack, needle, replacement) {
	var temp = haystack.split(needle);
	return temp.join(replacement);
}
</script>
<style type="text/css">
body {
	font-size:12px; 
	font-family:arial;
}

a {
	color:#c00; 
	text-decoration:none;
}

a:hover {
	color:#f00; 
	text-decoration:underline;
}

</style>
</head>
<body>

<a href="#" class="fontSizePlus">A+</a> | 
<a href="#" class="fontReset">Reset</a> | 
<a href="#" class="fontSizeMinus">A-</a>

<p class="intro"> HELLO WORLD</p>

<script type="text/javascript" src="http://www.queness.com/js/bsa.js"></script>

</body>
</html>
