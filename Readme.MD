Mi-am propus sa implementez o aplicatie Java cu Spring Framework
si Baza de date MySql (Bdtest) in Netbeans.

Creare Baza de date :
  - am definit 3 tabele Student, Curs si Student_Curs ( pentru definirea relatiei
    many to many)
  - cu scriptul Studentcurs.sql ( din folderul sql) creez tabele in Bdtest.

Definirea model date :
  - a creat 3 clase Student.java, Curs.java si Student_Curs.java

Definire DAO
  - Am creat 3 clase StudentDAO.java, CursDAO.java si Student_CursDAO.java care implementeaza metodele :
     	insertStudent
	updateStudent
	deleteStudent
	listAllStudents
	getStudent
     si similar pentru Curs
	