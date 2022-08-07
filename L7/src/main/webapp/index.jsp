<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>

<html>
   <head>
      <title>Using JSTL Functions</title>
   </head>
  
   <body>
<h3> Task 1 </h3>
<c:set var = "math"  value = "${5*2}"/> <!-- создание переменнной math и присваивания ей значения 10 (5*2) -->
     Before remove<br>
     Math: 5 * 2 =  <c:out value = "${math}"/> <!-- на экран выводится значение переменной math -->
<br><br>
<c:remove var = "math"/> <!-- удаляет переменную math из области видимости -->
      After remove <br>
      Math: 5 * 2 =  <c:out value = "${math}"/>  <!-- на экран выводится значение переменной math, но так как она удалена, то вывести нечего -->

<br><br>

<h3> Task 2 </h3> <!-- подключение драйвера для связи с БД, адрес БД, логин и пароль пользователя
<!-- создание запроса на выборку и хранение полученных от БД данных в переменной result -->
<!-- сам запрос на выборку -->

     <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/Books"
         user = "root"  password = "user1234"/>

      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * FROM books;
      </sql:query>

      <table border = "1" width = "100%"> <!-- создание таблицы -->
         <tr>
            <th>Book ID</th> <!-- создание столбцов таблицы -->
            <th>Author</th>
            <th>Genre</th>
            <th>Name</th>
            <th>Price</th>
            <th>Rate</th>
            <th>Year</th>
         </tr>

         <c:forEach var = "row" items = "${result.rows}"> <!-- в цикле проходятся все данные из result и выводятся в таблицу -->
            <tr>
               <td><c:out value = "${row.BookId}"/></td> <!-- тег </td> определяет ячейку таблицы -->
               <td><c:out value = "${row.Author}"/></td>
               <td><c:out value = "${row.Genre}"/></td>
               <td><c:out value = "${row.Name}"/></td>
               <td><c:out value = "${row.Price}"/></td>
               <td><c:out value = "${row.Rate}"/></td>
               <td><c:out value = "${row.Year}"/></td>
            </tr>
         </c:forEach>
      </table>

<br><br>

<h3> Task 3 </h3>
<c:set var = "string1" value = "Love leaves it's own mark, not a scar, no visible sign, to have been loved so deeply, even though the person who loved us is gone, will give us some protection forever"/>
        <c:if test = "${fn:endsWith(string1, '123')}"> <!-- строкой выше создаётся переменная string1 и если она заканчивается на 123, то выводится строка -->
                 <p>Condition false<p>
              </c:if>
        <c:if test = "${fn:endsWith(string1, 'forever')}"> <!-- если она заканчивается на pain!, то строка делится на массив элементов по разделителю пробел -->
                 <c:set var = "string2" value = "${fn:split(string1, ' ')}" />
                       <c:set var = "string3" value = "${fn:join(string2, '-')}" /> <!-- и между каждыми двумя элементами ставится дефис -->
                       <p>Condition true - > Final String : ${string3}</p> <!-- вывод изменённой строки на экран  -->
              </c:if>

   </body>
</html>