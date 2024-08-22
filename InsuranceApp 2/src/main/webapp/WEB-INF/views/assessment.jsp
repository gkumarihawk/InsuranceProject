<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="./js/assessment.js"></script>
</head>
<body>

	<form id="insuranceForm">
        <label for="name">Enter Name:</label>
        <input type="text" id="name" name="name"><br>

        <label for="age">Enter Age:</label>
        <input type="text" id="age" name="age"><br>

        <label for="salary">Enter Salary:</label>
        <input type="text" id="salary" name="salary"><br>

        <label for="noOfMembers">No. of members to be insured:</label>
        <input type="text" id="noOfMembers" name="noOfMembers"><br>

        <label for="province">Select Province:</label>
        <select id="province" name="province">
            
        </select><br>
        
        <label for="city">Select City:</label>
        <select id="city" name="city">
 
        </select><br>

        <button type="button" id="saveButton">Save</button>
    </form>

</body>
</html>