SELECT a.test as all, DISTINCTROW SQL_SMALL_RESULT SQL_NO_CACHE (1 - 2.0) + 3 + 4 * 5 * 6 + 7 * 8 + 1 / 2 - 3 + 7                      AS test,
                                                 user.id + 2 + t0.name                                                AS lala,
                                                 COUNT(user_id),
                                                 PI()                                                                 AS pi,
                                                 3.14                                                                 AS float_pi,
                                                 TRUE,
                                                 NULL,
                                                 COUNT(s.id) + SQRT(12)                                               AS aaa,
                                                 MATCH(title,
                                                       body) AGAINST("~string -exclusion +test +test2 -test4")        AS selectVar,
                                                 10                                                                   AS one,
                                                 20 + 30 + 40                                                         AS two,
                                                 100 + 200 + 300                                                      AS tri,
                                                 NULL,
                                                 SUM(DISTINCT LENGTH(SQRT(s.name)) / COUNT(s.id)),
                                                 COS(u.id) * 3,
                                                 2 * SIN(users.balance),
                                                 TAN(u.id),
                                                 1 + 2                                                                AS three,
                                                 SQRT(RAND(100)) / COUNT(s.id) + SUM(s.balance)                       AS tough,
                                                 (SELECT 123 AS int_type, 1.23 AS float_type
                                                  FROM innter_table)                                                  AS sub_id,
                                                 SQRT(2 / 3 * RAND(100)) * 123 + 777 / COUNT(s.id) + SUM(s.balance)
FROM (SELECT 123 AS int_type, 1.23 AS float_type, 1 + 2 * 3 / 1 + 1 * 2 + 3 FROM innter_table) AS t0
       RIGHT JOIN tableName AS t0 ON ((t0.id <= t1.subId OR t1.id >= 1) AND 1 = 1)
       INNER JOIN tableName AS t0 ON ((t0.cnt / 10 - 3 = SUM(DISTINCT u.cnt) OR ROUND(PI(), 2) = 3.14))