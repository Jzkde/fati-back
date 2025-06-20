@echo off
set MYSQLDUMP_PATH="C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqldump.exe"  
set DB_USER=root
set DB_NAME=fatideco
set BACKUP_DIR=backups
set TIMESTAMP=%DATE:~10,4%%DATE:~4,2%%DATE:~7,2%_%TIME:~0,2%%TIME:~3,2%%TIME:~6,2%
set BACKUP_FILE=%BACKUP_DIR%\%DB_NAME%_backup_%TIME:~3,2%%TIME:~6,2%.sql


if not exist %BACKUP_DIR% (
    mkdir %BACKUP_DIR%
)


%MYSQLDUMP_PATH% -u%DB_USER% %DB_NAME% > %BACKUP_FILE%

REM Verificar si mysqldump tuvo éxito
if %errorlevel% neq 0 (
    echo Backup failed.
) else (
    echo Backup successful: %BACKUP_FILE%
)