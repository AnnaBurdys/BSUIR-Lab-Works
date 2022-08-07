<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Tomcat Example</title>
	</head>
	<body>
		<form action="hello" method="POST">

		    Angle: <input type = "number" name = "angle" />
        	<br><br>
        	Angle type: <input type = "radio" name = "angle-type" value = "Radians"/>Radians
        	        <input type = "radio" name = "angle-type" value = "Degrees"/>Degrees
        	        <br><br>
        	Math functions:
        	<input type = "checkbox" name = "math-function" value = "sin" /> Sine
        	<input type = "checkbox" name = "math-function" value = "cos" /> Cosine
        	<input type = "checkbox" name = "math-function" value = "tg" /> Tangent
        	<input type = "checkbox" name = "math-function" value = "ctg" /> Cotangent
        	<br><br>


        	Columns: <input type = "number" name = "columns" />
        	Rows: <input type = "number" name = "rows" />
        	Title: <input type = "text" name = "title" />
	        Color: <input type = "text" name = "color" />


        	<input type = "submit" value = "Submit" />
		</form>
	</body>
</html>