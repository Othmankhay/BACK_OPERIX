IF NOT EXISTS (SELECT 1 FROM sys.sql_logins WHERE name = N'operix_app')
    EXEC('CREATE LOGIN operix_app WITH PASSWORD = ''OperixDev123!''');
GO
IF DB_ID(N'operix') IS NULL
    CREATE DATABASE operix;
GO
USE operix;
GO
IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = N'operix_app')
    CREATE USER operix_app FOR LOGIN operix_app;
GO
IF IS_ROLEMEMBER(N'db_owner', N'operix_app') <> 1
    ALTER ROLE db_owner ADD MEMBER operix_app;
GO
SELECT name AS login_name FROM sys.sql_logins WHERE name = N'operix_app';
SELECT name AS database_name FROM sys.databases WHERE name = N'operix';

