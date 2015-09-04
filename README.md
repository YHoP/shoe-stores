# shoe-stores
Java Advanced Database Code Review<br>

Author name - Yvonne Peng<br>
Program name - Shoe Stores<br>
<br>
### Discription -<br>
Epicodus Java 2015 Summer Class week 4 CodeReview<br>
Write a program to list out local shoe stores and the brands of shoes they carry. <br>
Make a Store class and a Brand class.<br>
<br>
### Setup PSQL instructions-<br>
1. In PSQL:
2. CREATE DATABASE shoes;
3. CREATE TABLE stores (id serial PRIMARY KEY, name varchar);
4. CREATE TABLE brands (id serial PRIMARY KEY, name varchar);
5. CREATE TABLE brands_stores (id serial PRIMARY KEY, brand_id int, store_id int);
6. CREATE DATABASE shoes_test WITH TEMPLATE shoes;

<br>
![alt tag](https://raw.githubusercontent.com/YHoP/shoe-stores/master/src/main/resources/public/img/sql-table-design.png)

### Setup instructions -<br>
1. Download this repository files to your computer.<br>
2. Navigate to the folder directory.<br>
3. Open command prompt in Windows or terminal in Mac.<br>
4. If you don't have PostgreSQL installed in your computer, check out <a href="https://www.learnhowtoprogram.com/lessons/installing-postgres">this link</a> and follow the instruction.
5. Create a database: "shoes" in your computer.<br>
6. Run the following command "psql shoes < shoes.sql"<br>
7. Run the following command "Gradle run". (If you have not installed Gradle, check out the <a href="https://gradle.org/getting-started-gradle-java/">Gradle website</a> )<br>
8. Open your internet browser and navigate to <a href="http://localhost:4567/">http://localhost:4567/</a><br>
9. The webpage would load automatically. You can manage your own local shoes store!!<br>
<br>
Copyright: YHoP<br>
<br>
License information: Open source licensing<br>
