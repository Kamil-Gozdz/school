INSERT INTO students(id, name, surname, email, age, field) VALUES
            (1,'John','Wayne','co.@com.pl',23,'BIOLOGY'),
            (2,'Mike','Tyson','co.@com.pl',25,'CHEMISTRY'),
            (3,'Rick','Morty','co.@com.pl',30,'LOGISTICS');
INSERT INTO teachers(id, name, surname, email, age, subject) VALUES
            (1,'Jack','Black','co.@com.pl',43,'DATABASES'),
            (2,'Elon','Musk','co.@com.pl',54,'MACROECONOMICS'),
            (3,'Joe','Biden','co.@com.pl',39,'LABOR_LAW');
INSERT INTO student_teacher(student_id, teacher_id) VALUES
            (1,1),
            (1,2),
            (2,3);