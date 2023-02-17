insert IGNORE into roles (role_id,name) values (1, "User"),(2, "Admin"),(3, "Super");
insert IGNORE into users (pseudo,first_name,last_name,email,password,birthdate,street,city,country,zip,phone,role_id,activate) values
    ("Supreme","Zeus","Dzeus","super@super.com","$2y$10$9g0.G4j0aCELiPRUG/5BQOFSJTkwBc87A8SA08JQJVVWry0rkr.lm","2000-12-20","01 Mont Olympes","grece","Grèce","00000","0600118218",3,true);
-- super@super.com super
