1)SELECT Н_ЛЮДИ.ИМЯ, Н_СЕССИЯ.ЧЛВК_ИД
  FROM Н_ЛЮДИ
  RIGHT JOIN Н_СЕССИЯ ON Н_ЛЮДИ.ИД = Н_СЕССИЯ.ЧЛВК_ИД
  WHERE Н_ЛЮДИ.ОТЧЕСТВО = 'Владимирович' AND Н_СЕССИЯ.ЧЛВК_ИД < 151200;

2) SELECT Н_ЛЮДИ.ФАМИЛИЯ, Н_ВЕДОМОСТИ.ЧЛВК_ИД, Н_СЕССИЯ.УЧГОД
   FROM Н_ЛЮДИ
   RIGHT JOIN Н_ВЕДОМОСТИ ON Н_ВЕДОМОСТИ.ЧЛВК_ИД = Н_ЛЮДИ.ИД
   RIGHT JOIN Н_СЕССИЯ ON Н_СЕССИЯ.ЧЛВК_ИД = Н_ВЕДОМОСТИ.ЧЛВК_ИД
   WHERE Н_ЛЮДИ.ОТЧЕСТВО = 'Александрович'
   AND Н_ВЕДОМОСТИ.ЧЛВК_ИД < 105590
   AND Н_СЕССИЯ.УЧГОД < '2011/2012';

3)  SELECT Н_ЛЮДИ.ИД FROM Н_ЛЮДИ
    WHERE date_part('year', age(Н_ЛЮДИ.ДАТА_РОЖДЕНИЯ)) > 25;